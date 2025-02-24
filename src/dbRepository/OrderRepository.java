package dbRepository;
import database.DbConnection;
import models.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Constant.SqlQueries;
/*
*******************************************************************************************************
*   @Class Name         : OrderRepository
*   @Author             : Priyanka Kumari (priyanka.kumari@antrazal.com)
*   @Company            : Antrazal
*   @Date               : 22-02-2025
*   @Description        : This class handles order-related database operations, including placing orders,
*                         retrieving order history, and generating invoices.
*******************************************************************************************************
*/
public class OrderRepository {
    private final Connection connection;
    private final PlatformRepository platformRepository;

  /*
    *********************************************************
    *  @Constructor    : OrderRepository
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Establishes a database connection for order-related operations.
    *********************************************************
    */
    public OrderRepository() throws ClassNotFoundException, SQLException {
        this.connection = DbConnection.getConnection();
        this.platformRepository = new PlatformRepository();
    }

  /*
    *********************************************************
    *  @Method Name    : placeOrder
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Places an order for the specified user and updates stock levels.
    *  @param          : String username - The username placing the order.
    *  @param          : List<CartItem> cartItems - Items in the cart to be ordered.
    *  @param          : double totalAmount - The total amount of the order.
    *  @param          : String transactionId - Unique transaction ID for the order.
    *  @param          : int platformId - The platform on which the order is placed.
    *  @return         : boolean - Returns true if the order is placed successfully, false otherwise.
    *********************************************************
    */
    public boolean placeOrder(String username, List<CartItem> cartItems, double totalAmount, String transactionId, int platformId) throws ClassNotFoundException {
        try {

            connection.setAutoCommit(false);

        
            try (PreparedStatement orderStmt = connection.prepareStatement(SqlQueries.SAVE_ORDER, PreparedStatement.RETURN_GENERATED_KEYS)) {
                orderStmt.setString(1, username);
                orderStmt.setDouble(2, totalAmount);
                orderStmt.setString(3, transactionId);
                orderStmt.setInt(4, platformId);

              
                int affectedRows = orderStmt.executeUpdate();
                if (affectedRows == 0) {
                    connection.rollback(); 
                    return false;
                }

            
                try (ResultSet generatedKeys = orderStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int orderId = generatedKeys.getInt(1);

                  
                        try (PreparedStatement orderProductStmt = connection.prepareStatement(SqlQueries.SAVE_ORDER_ITEMS);
                             PreparedStatement updateStockStmt = connection.prepareStatement(SqlQueries.UPDATE_PRODUCT_STOCK)) {

                            for (CartItem item : cartItems) {
                                orderProductStmt.setInt(1, orderId);
                                orderProductStmt.setInt(2, item.getProductId());
                                orderProductStmt.setInt(3, item.getQuantity());
                                orderProductStmt.setDouble(4, item.getPrice());
                                orderProductStmt.setInt(5, platformId);
                                orderProductStmt.addBatch(); 

                                
                                updateStockStmt.setInt(1, item.getQuantity());
                                updateStockStmt.setInt(2, item.getProductId());
                                updateStockStmt.setInt(3, platformId);
                                updateStockStmt.addBatch(); 
                            }

                           
                            orderProductStmt.executeBatch();
                            updateStockStmt.executeBatch();
                        }
                    }
                }
            }

            connection.commit();
           
            generateInvoice(username, transactionId, totalAmount, cartItems, platformId);
          
            cartItems.clear();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback(); 
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            return false;
        }
    }

  /*
     * *************************************************************************************************
     * @Method Name   : getOrderHistory
     * @Author       : Priyanka Kumari (priyanka.kumari@antrazal.com)
     * @Company      : Antrazal
     * @Description  : Retrieves order history for a specific user and platform.
     * @param username   : The username of the buyer.
     * @param platformId : The ID of the platform.
     * @return List<Order>: Returns a list of Order objects.
     * *************************************************************************************************
     */
    public List<Order> getOrderHistory(String username, int platformId) {
        List<Order> orderList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.GET_ORDER_HISTORY)) {
            stmt.setString(1, username);
            stmt.setInt(2, platformId);

            try (ResultSet rs = stmt.executeQuery()) {
         
                while (rs.next()) {
                    Order order = new Order(
                        rs.getInt("order_id"),
                        rs.getString("username"),
                        rs.getDouble("total_amount"),
                        rs.getString("transaction_id"),
                        getOrderItems(rs.getInt("order_id"), platformId)
                    );
                    orderList.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return orderList;
    }

  /*
     * *************************************************************************************************
     * @Method Name   : getOrderItems
     * @Author       : Priyanka Kumari (priyanka.kumari@antrazal.com)
     * @Company      : Antrazal
     * @Description  : Retrieves order items for a specific order ID and platform.
     * @param orderId    : The ID of the order.
     * @param platformId : The ID of the platform.
     * @return List<OrderItem>: Returns a list of OrderItem objects.
     * *************************************************************************************************
     */
    private List<OrderItem> getOrderItems(int orderId, int platformId) {
        List<OrderItem> orderItems = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.GET_ORDER_ITEMS)) {
            stmt.setInt(1, orderId);
            stmt.setInt(2, platformId);

            try (ResultSet rs = stmt.executeQuery()) {
          
                while (rs.next()) {
                    OrderItem orderItem = new OrderItem(
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                    );
                    orderItems.add(orderItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

/*
    *********************************************************
    *  @Method Name    : generateInvoice
    *  @Author         : Priyanka Kumari (priyanka.kumari@antrazal.com)
    *  @Company        : Antrazal
    *  @Description    : Generates an invoice for the completed order.
    *  @param          : String username - The username placing the order.
    *  @param          : String transactionId - Unique transaction ID for the order.
    *  @param          : double totalAmount - The total amount of the order.
    *  @param          : List<CartItem> cartItems - List of items in the order.
    *  @param          : int platformId - The platform where the order was placed.
    *********************************************************
    */
    public void generateInvoice(String username, String transactionId, double totalAmount, List<CartItem> cartItems, int platformId) throws ClassNotFoundException {

        String filename = username + "_invoice_" + System.currentTimeMillis() + ".txt";

    
        Platform platform = platformRepository.getPlatformById(platformId); 
        String platformName = (platform != null) ? platform.getPlatformName() : "Unknown Platform";

    
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("=============== INVOICE ===============\n");
            writer.write("Platform: " + platformName + "\n");
            writer.write("Username: " + username + "\n");
            writer.write("Transaction ID: " + transactionId + "\n\n");
            writer.write("Items Purchased:\n");
            for (CartItem item : cartItems) {
                writer.write("- " + item.getProductName() + " (x" + item.getQuantity() + ") - $" + item.getPrice() + "\n");
            }
            writer.write("\nTotal Amount (after discount): $" + String.format("%.2f", totalAmount) + "\n");
            writer.write("===============================================\n");
            writer.write("Thank you for shopping with us!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
