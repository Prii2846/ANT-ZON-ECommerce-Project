package Constant;


public class SqlQueries {
    
    public static final String CREATE_PLATFORM_TABLE = """
        CREATE TABLE IF NOT EXISTS Platforms (
        platform_id INT PRIMARY KEY AUTO_INCREMENT,
        platform_name VARCHAR(50) UNIQUE NOT NULL
        )

    """;
    
    public static final String INSERT_PLATFORMS = """
    INSERT INTO Platforms (platform_id, platform_name) VALUES
    (1, 'Amazon'),
    (2, 'Flipkart'),
    (3, 'ANT-ZON')
    ON DUPLICATE KEY UPDATE platform_name = VALUES(platform_name);
""";
  
   
    public static final String CREATE_USERS_TABLE = """
        CREATE TABLE IF NOT EXISTS Users(
        id INT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(100) UNIQUE NOT NULL, 
        password VARCHAR(100) NOT NULL,
        role ENUM('Admin','Seller','Buyer') NOT NULL,
        platform_id INT NOT NULL,
        FOREIGN KEY (platform_id) REFERENCES Platforms(platform_id)
        )
    """;

    public static final String CREATE_PRODUCT_TYPES = """
        CREATE TABLE IF NOT EXISTS producttypes (
        id INT AUTO_INCREMENT PRIMARY KEY,
        main_category VARCHAR(50) NOT NULL,
        sub_category VARCHAR(50) NOT NULL,
        product_type VARCHAR(50) NOT NULL,
        platform_id INT NOT NULL,
        FOREIGN KEY (platform_id) REFERENCES Platforms(platform_id)
        )
    """;

    public static final String CHECK_CONSTRAINT = """
        SELECT COUNT(*) FROM information_schema.table_constraints 
        WHERE table_name='producttypes' AND constraint_name='unique_category'
    """;

    public static final String CREATE_PRODUCTS_TABLE = """
        CREATE TABLE IF NOT EXISTS products(
        id INT AUTO_INCREMENT PRIMARY KEY,
        product_name VARCHAR(100) NOT NULL,
        main_category VARCHAR(50) NOT NULL,
        sub_category VARCHAR(50) NOT NULL,
        product_type VARCHAR(50) NOT NULL,
        stock INT NOT NULL,
        price DECIMAL(10,2) NOT NULL,
        sellerUsername VARCHAR(100) NOT NULL,
        platform_id INT NOT NULL,
        FOREIGN KEY (sellerUsername) REFERENCES Users(username) ON DELETE CASCADE,
        FOREIGN KEY (platform_id) REFERENCES Platforms(platform_id)
        )
    """;


    public static final String CREATE_CART_TABLE = """
    CREATE TABLE IF NOT EXISTS cart (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    product_id INT NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    platform_id INT NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    FOREIGN KEY (platform_id) REFERENCES Platforms(platform_id)
    )
     """;

     public static final String GET_CART_ITEMS_BY_USERNAME = """
        SELECT id, product_id, product_name, quantity, price, platform_id
        FROM cart WHERE username = ? AND platform_id = ?
    """;
     

    public static final String CREATE_WISHLIST_TABLE = """
         CREATE TABLE wishlist (
            wishlist_id INT AUTO_INCREMENT PRIMARY KEY,
            username VARCHAR(100) NOT NULL,
            product_id INT NOT NULL,
            platform_id INT NOT NULL,
            added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE,
            FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
            FOREIGN KEY (platform_id) REFERENCES Platforms(platform_id)
        )
        """;
    public static final String ADD_TO_WISHLIST = """
        INSERT INTO wishlist (username, product_id, platform_id) VALUES (?, ?, ?);
    """;
    public static final String GET_WISHLIST = """
        SELECT p.id, p.product_name, p.price 
        FROM wishlist w
        JOIN products p ON w.product_id = p.id
        WHERE w.username = ? AND p.platform_id = ?
        """;
    
    public static final String REMOVE_FROM_WISHLIST = """
      DELETE FROM wishlist WHERE username = ? AND product_id = ? AND platform_id = ?

            """;


    public static final String ADD_CART_ITEM = """
        INSERT INTO cart (username, product_id, product_name, quantity, price, platform_id) VALUES (?, ?, ?, ?, ?, ?)
                
     """;

    public static final String REMOVE_CART_ITEM = """
        DELETE FROM cart WHERE product_id = ? AND username = ? AND platform_id = ?
    """;

    public static final String CLEAR_CART = """
        DELETE FROM cart WHERE username = ? AND platform_id = ?
    """;


    public static final String CREATE_ORDERS_TABLE = """
        CREATE TABLE IF NOT EXISTS orders (
        order_id INT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(100) NOT NULL,
        order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        total_amount DECIMAL(10,2),
        transaction_id VARCHAR(100) UNIQUE,
        platform_id INT NOT NULL,
        FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE,
        FOREIGN KEY (platform_id) REFERENCES Platforms(platform_id)

    )
    """;

    public static final String CREATE_ORDER_ITEMS_TABLE = """
    CREATE TABLE IF NOT EXISTS order_items (
        order_item_id INT AUTO_INCREMENT PRIMARY KEY,
        order_id INT NOT NULL,
        product_id INT NOT NULL,
        quantity INT NOT NULL DEFAULT 1,
        price DECIMAL(10,2) NOT NULL,
        platform_id INT NOT NULL,
        FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
        FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
        FOREIGN KEY (platform_id) REFERENCES Platforms(platform_id)
    )
""";



    public static final String SAVE_ORDER = """
     INSERT INTO orders (username, total_amount, transaction_id, platform_id)  
    VALUES (?, ?, ?, ?)
    """;

    public static final String SAVE_ORDER_ITEMS = """
      INSERT INTO order_items (order_id, product_id, quantity, price, platform_id) VALUES (?, ?, ?, ?, ?)
    """;

    public static final String UPDATE_PRODUCT_STOCK = """
    UPDATE products SET stock = stock - ? WHERE id = ? AND platform_id = ?

    """;
    public static final String INSERT_PRODUCT = """
        INSERT INTO products (product_name, main_category, sub_category, product_type, stock, price, sellerUsername, platform_id) 
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    """;

    public static final String SELECT_PRODUCTS_BY_SELLER = """
        SELECT * FROM products WHERE sellerUsername = ? AND platform_id = ?
    """;

    public static final String SELECT_PRODUCT_BY_ID = """
        SELECT id, product_name, main_category, sub_category, product_type, stock, price, sellerUsername 
        FROM products WHERE id = ? AND platform_id = ?
    """;

    public static final String UPDATE_PRODUCT = """
        UPDATE products SET product_name = ?, main_category = ?, sub_category = ?, 
        product_type = ?, stock = ?, price = ? WHERE id = ? AND platform_id = ?
    """;

    public static final String DELETE_PRODUCT = """
        DELETE FROM products WHERE id = ? AND sellerUsername = ? AND platform_id = ?
    """;

    public static final String SELECT_ALL_PRODUCTS = """
        SELECT * FROM products p
        WHERE p.platform_id = ? AND stock > 0;
    """;

    public static final String SELECT_ALL_PRODUCT_TYPES = """
        SELECT main_category, sub_category, product_type FROM producttypes
    """;

    public static final String INSERT_PRODUCT_TYPE = """
        INSERT INTO producttypes(main_category, sub_category, product_type, platform_id ) 
        VALUES (?, ?, ?, ?)
    """;

    public static final String INSERT_USER = """
        INSERT INTO USERS(username, password, role, platform_id) VALUES(?, ?, ?, ?)
    """;

    public static final String CHECK_USERNAME_EXISTS = """
        SELECT id FROM Users WHERE username = ? AND platform_id = ?
    """;

    public static final String AUTHENTICATE_USER = """
        SELECT * FROM Users WHERE username = ? AND password = ? AND role = ? AND platform_id = ?
    """;


    public static final String GET_USERS_BY_ROLE = """
        SELECT username FROM Users WHERE role = ? AND platform_id = ?
    """;

public static final String GET_STOCK_PRODUCT = """
    SELECT stock FROM products WHERE id = ? AND platform_id = ?
    """;

public static final String GET_ORDER_HISTORY = """
    SELECT o.order_id, o.username, o.total_amount, o.transaction_id, 
    p.product_name, oi.quantity, oi.price
    FROM orders o
    JOIN order_items oi ON o.order_id = oi.order_id
    JOIN products p ON oi.product_id = p.id
    WHERE o.username = ? AND p.platform_id = ?
    """;

public static final String GET_ORDER_ITEMS = """
    SELECT oi.order_item_id, oi.order_id, oi.product_id, p.product_name, oi.quantity, oi.price 
    FROM order_items oi 
    JOIN products p ON oi.product_id = p.id 
    WHERE oi.order_id = ? AND p.platform_id = ?
    """;
  


public static final String TOTAL_REVENUE_QUERY = """
    SELECT COALESCE(SUM(total_amount), 0) FROM orders
    WHERE platform_id = ?
""";

public static final String REVENUE_BY_CATEGORY_QUERY = """
    SELECT p.main_category, COALESCE(SUM(o.total_amount), 0) AS revenue
    FROM orders o
    JOIN order_items oi ON o.order_id = oi.order_id
    JOIN products p ON oi.product_id = p.id
    WHERE o.platform_id = ?
    GROUP BY p.main_category
""";

public static final String REVENUE_BY_SUBCATEGORY_QUERY = """
    SELECT p.sub_category, COALESCE(SUM(o.total_amount), 0) AS revenue
    FROM orders o
    JOIN order_items oi ON o.order_id = oi.order_id
    JOIN products p ON oi.product_id = p.id
    WHERE o.platform_id = ?
    GROUP BY p.sub_category
""";

public static final String BEST_SELLING_PRODUCTS_QUERY = """
    SELECT p.product_type, COUNT(*) AS total_sales
    FROM order_items oi
    JOIN products p ON oi.product_id = p.id
    WHERE p.platform_id = ?
    GROUP BY p.product_type
    ORDER BY total_sales DESC
    LIMIT 5
""";

public static final String MOST_LIKED_PRODUCTS_QUERY = """
    SELECT p.product_type, COUNT(*) AS likes
    FROM wishlist w
    JOIN products p ON w.product_id = p.id
    WHERE p.platform_id = ? AND p.stock > 0
    GROUP BY p.product_type
    ORDER BY likes DESC
    LIMIT 5
""";

public static final String TOP_SELLING_SELLERS_QUERY = """
    SELECT p.sellerUsername, COALESCE(SUM(o.total_amount), 0) AS total_sales
    FROM orders o
    JOIN order_items oi ON o.order_id = oi.order_id
    JOIN products p ON oi.product_id = p.id
    WHERE p.platform_id = ?
    GROUP BY p.sellerUsername
    ORDER BY total_sales DESC
    LIMIT 5
""";


public static final String LOW_STOCK_PRODUCTS_QUERY = """
   SELECT product_type, stock FROM products WHERE stock > 0 AND stock <= 10 AND platform_id = ?
        """;
 

public static final String OUT_OF_STOCK_PRODUCTS_QUERY = """
  SELECT id, product_name, main_category, sub_category, product_type, stock, price, sellerUsername FROM products WHERE stock = 0
    AND platform_id = ?
        """;
    
public static final String ALL_USERS_QUERY = """
        SELECT id, username, role FROM users
        ORDER BY role

       """;
public static final String APPROVE_DISABLE_USER = """
        UPDATE Users SET is_active = ? 
        WHERE user_id = ? AND platform_id = ?
        """;  
    
public static final String GET_ORDERS_BY_PLATFORM = """
         SELECT * FROM orders 
        WHERE platform_id = ?
            """; 
    
public static final String GET_SELLERS_AND_BUYERS = """
        SELECT username, role FROM Users 
        WHERE platform_id = ?
    """;

public static final String GET_USER = """
        SELECT * FROM users WHERE username = ? AND platform_id = ?
    """;

}