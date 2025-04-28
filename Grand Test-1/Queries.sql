/* 1) Write a SQL query to calculate the total revenue per customer, excluding cancelled orders, 
 for those with at least two orders between October 10 and October 15, 2024.
 
 Expected Output Columns:
 ------------------------
 +----------------+--------------+
 | Name           | TotalRevenue |
 +----------------+--------------+
 
 Note: 
 -----
 Database name: customer_orders
 
 Tables in customer_orders:  Customers                                                                       
 OrderItems                                                                      
 Orders                                                                          
 Products
 
 */
USE customer_orders;

SELECT 
    Customers.Name,
    SUM(Orders.TotalCost) AS TotalRevenue
FROM 
    Customers
JOIN 
    Orders ON Customers.CustomerID = Orders.CustomerID
WHERE 
    Orders.Status <> 'Cancelled'
    AND Orders.OrderDate BETWEEN '2024-10-10' AND '2024-10-15'
GROUP BY 
    Customers.CustomerID
HAVING 
    COUNT(Orders.OrderID) >= 2;


/* 2) Write a SQL query to find the customer with the highest average order cost, 
 excluding cancelled orders.
 
 Expected Output Columns:
 ------------------------
 +--------------+-------------+
 | Name         | AvgCost     |
 +--------------+-------------+
 
 Note: 
 -----
 Database name: customer_orders
 
 Tables in customer_orders:  Customers                                                                       
 OrderItems                                                                      
 Orders                                                                          
 Products
 
 */
USE customer_orders;

SELECT 
    Customers.Name,
    AVG(Orders.TotalCost) AS AvgCost
FROM 
    Customers
JOIN 
    Orders ON Customers.CustomerID = Orders.CustomerID
WHERE 
    Orders.Status <> 'Cancelled'
GROUP BY 
    Customers.CustomerID
ORDER BY 
    AvgCost DESC
LIMIT 1;

/*3) Write a SQL query to find the customer who ordered the most keyboards, 
 using pattern matching and aggregate functions.
 
 Expected Output Columns:
 ------------------------
 +---------------+---------------+
 | Name          | KeyboardCount |
 +---------------+---------------+
 
 Note: 
 -----
 Database name: customer_orders
 
 Tables in customer_orders:  Customers                                                                       
 OrderItems                                                                      
 Orders                                                                          
 Products
 
 */
USE customer_orders;

SELECT 
    Customers.Name,
    SUM(OrderItems.Quantity) AS KeyboardCount
FROM 
    Customers
JOIN 
    Orders ON Customers.CustomerID = Orders.CustomerID
JOIN 
    OrderItems ON Orders.OrderID = OrderItems.OrderID
JOIN 
    Products ON OrderItems.ProductID = Products.ProductID
WHERE 
    Products.Name LIKE '%Keyboard%'
GROUP BY 
    Customers.CustomerID
ORDER BY 
    KeyboardCount DESC
LIMIT 1;

/* 4)Write a SQL query to list products ordered in both 'Shipped' and 'Processing' statuses 
 but not 'Cancelled'.
 
 Expected Output Columns:
 ------------------------
 +------------+
 | Name       |
 +------------+
 
 Note: 
 -----
 Database name: customer_orders
 
 Tables in customer_orders:  Customers                                                                       
 OrderItems                                                                      
 Orders                                                                          
 Products
 
 */
 USE customer_orders;

SELECT 
    p.Name
FROM 
    Products p
JOIN 
    OrderItems oi ON p.ProductID = oi.ProductID
JOIN 
    Orders o ON oi.OrderID = o.OrderID
GROUP BY 
    p.ProductID
HAVING 
    SUM(CASE WHEN o.Status = 'Shipped' THEN 1 ELSE 0 END) > 0
    AND SUM(CASE WHEN o.Status = 'Processing' THEN 1 ELSE 0 END) > 0
    AND SUM(CASE WHEN o.Status = 'Cancelled' THEN 1 ELSE 0 END) = 0;

/* 5) Write a SQL query to find the top 3 most expensive products never ordered.
 
 Expected Output Columns:
 ------------------------
 +------------+--------+
 | Name       | Price  |
 +------------+--------+
 
 Note: 
 -----
 Database name: customer_orders
 
 Tables in customer_orders:  Customers                                                                       
 OrderItems                                                                      
 Orders                                                                          
 Products
 
 */
USE customer_orders;

SELECT 
    Products.Name,
    Products.Price
FROM 
    Products
LEFT JOIN 
    OrderItems ON Products.ProductID = OrderItems.ProductID
WHERE 
    OrderItems.OrderID IS NULL
ORDER BY 
    Products.Price DESC
LIMIT 3;

/* 6) Write a SQL Query Using a Correlated Subquery to Get the Latest Order for Each Customer
 
 Expected Output Columns:
 ------------------------
 +---------+------------+------------+-----------+
 | OrderID | CustomerID | OrderDate  | TotalCost |
 +---------+------------+------------+-----------+
 
 Note: 
 -----
 Database name: customer_orders
 
 Tables in customer_orders:  Customers                                                                       
 OrderItems                                                                      
 Orders                                                                          
 Products
 
 */
USE customer_orders;

SELECT 
    o.OrderID,
    o.CustomerID,
    o.OrderDate,
    o.TotalCost
FROM 
    Orders o
WHERE 
    o.OrderDate = (
        SELECT MAX(o2.OrderDate)
        FROM Orders o2
        WHERE o2.CustomerID = o.CustomerID
    );

/* 7) Write a SQL Query to Find the Most Frequently Ordered Product.
 
 Expected Output Columns:
 ------------------------
 +-------+---------------+
 | Name  | TotalQuantity |
 +-------+---------------+
 
 Note: 
 -----
 Database name: customer_orders
 
 Tables in customer_orders:  Customers                                                                       
 OrderItems                                                                      
 Orders                                                                          
 Products
 
 */
-- Your implied query:
use customer_orders;
SELECT 
    Products.Name, 
    SUM(OrderItems.Quantity) AS TotalQuantity
FROM 
    Products
JOIN 
    OrderItems ON Products.ProductID = OrderItems.ProductID
GROUP BY 
    Products.ProductID
ORDER BY 
    TotalQuantity DESC
LIMIT 1;


/* 8) Write a SQL Query to Find orders that were placed on weekends.

Expected Output Columns:
------------------------
+---------+------------+------------+
| OrderID | CustomerID | OrderDate  |
+---------+------------+------------+

Note: 
-----
Database name: customer_orders

Tables in customer_orders:  Customers                                                                       
                            OrderItems                                                                      
                            Orders                                                                          
                            Products

*/
use customer_orders;
SELECT Orders.OrderID, Orders.CustomerID, Orders.OrderDate
FROM Orders
WHERE WEEKDAY(Orders.OrderDate) IN (5,6);
