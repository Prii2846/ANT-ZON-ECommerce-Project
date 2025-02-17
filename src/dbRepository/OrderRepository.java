package dbRepository;
import database.DbConnection;
import models.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Constant.SqlQueries;

public class OrderRepository {
    private final Connection connection;
    private final PlatformRepository platformRepository;

    // Constructor that initializes the connection to the database and platform repository
    public OrderRepository() throws ClassNotFoundException, SQLException {
        this.connection = DbConnection.getConnection();
        this.platformRepository = new PlatformRepository();
    }

    // Places an order by saving it to the database, updating product stock, and generating an invoice
    public boolean placeOrder(String username, List<CartItem> cartItems, double totalAmount, String transactionId, int platformId) throws ClassNotFoundException {
        try {
            // Begin transaction to ensure all database changes are successful before committing
            connection.setAutoCommit(false);

            // Save the order information into the orders table
            try (PreparedStatement orderStmt = connection.prepareStatement(SqlQueries.SAVE_ORDER, PreparedStatement.RETURN_GENERATED_KEYS)) {
                orderStmt.setString(1, username);
                orderStmt.setDouble(2, totalAmount);
                orderStmt.setString(3, transactionId);
                orderStmt.setInt(4, platformId);

                // Execute the order save query
                int affectedRows = orderStmt.executeUpdate();
                if (affectedRows == 0) {
                    connection.rollback(); // Rollback if no rows were affected
                    return false;
                }

                // Retrieve the generated order ID
                try (ResultSet generatedKeys = orderStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int orderId = generatedKeys.getInt(1);

                        // Save the items in the order and update product stock
                        try (PreparedStatement orderProductStmt = connection.prepareStatement(SqlQueries.SAVE_ORDER_ITEMS);
                             PreparedStatement updateStockStmt = connection.prepareStatement(SqlQueries.UPDATE_PRODUCT_STOCK)) {

                            for (CartItem item : cartItems) {
                                // Save each cart item to the order items table
                                orderProductStmt.setInt(1, orderId);
                                orderProductStmt.setInt(2, item.getProductId());
                                orderProductStmt.setInt(3, item.getQuantity());
                                orderProductStmt.setDouble(4, item.getPrice());
                                orderProductStmt.setInt(5, platformId);
                                orderProductStmt.addBatch(); // Add to batch for efficient execution

                                // Update the stock for each product in the order
                                updateStockStmt.setInt(1, item.getQuantity());
                                updateStockStmt.setInt(2, item.getProductId());
                                updateStockStmt.setInt(3, platformId);
                                updateStockStmt.addBatch(); // Add to batch
                            }

                            // Execute batch updates
                            orderProductStmt.executeBatch();
                            updateStockStmt.executeBatch();
                        }
                    }
                }
            }

            // Commit transaction if all queries are successful
            connection.commit();
            // Generate an invoice for the order
            generateInvoice(username, transactionId, totalAmount, cartItems, platformId);
            // Clear cart after successful order placement
            cartItems.clear();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback(); // Rollback transaction if there is any error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            return false;
        }
    }

    // Fetches the order history for a specific user from the database
    public List<Order> getOrderHistory(String username, int platformId) {
        List<Order> orderList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.GET_ORDER_HISTORY)) {
            stmt.setString(1, username);
            stmt.setInt(2, platformId);

            try (ResultSet rs = stmt.executeQuery()) {
                // Loop through the result set and create Order objects
                while (rs.next()) {
                    Order order = new Order(
                        rs.getInt("order_id"),
                        rs.getString("username"),
                        rs.getDouble("total_amount"),
                        rs.getString("transaction_id"),
                        getOrderItems(rs.getInt("order_id"), platformId) // Fetch order items
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

    // Fetches the order items for a specific order from the database
    private List<OrderItem> getOrderItems(int orderId, int platformId) {
        List<OrderItem> orderItems = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SqlQueries.GET_ORDER_ITEMS)) {
            stmt.setInt(1, orderId);
            stmt.setInt(2, platformId);

            try (ResultSet rs = stmt.executeQuery()) {
                // Loop through the result set and create OrderItem objects
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

    // Generates an invoice for the order and writes it to a file
    public void generateInvoice(String username, String transactionId, double totalAmount, List<CartItem> cartItems, int platformId) throws ClassNotFoundException {
        // Generate the filename based on username and current timestamp
        String filename = username + "_invoice_" + System.currentTimeMillis() + ".txt";

        // Retrieve the platform name based on the platform ID
        Platform platform = platformRepository.getPlatformById(platformId); 
        String platformName = (platform != null) ? platform.getPlatformName() : "Unknown Platform";

        // Write the invoice to a text file
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
