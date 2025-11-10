package pro;


import java.io.File;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import java.util.Scanner;

import java.util.LinkedList;


/**

* OrdersManager: manages Order objects and links them to Customers.

* NOTE: Method signatures remain unchanged.

*/

public class OrdersManager {

private LinkedList<Order> ordersList;

private Customers customersList;

private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");


// Keep original constructor signatures

public OrdersManager(LinkedList<Customer> customers, LinkedList<Order> orders) {

this.customersList = new Customers(customers);

this.ordersList = orders;

}


public OrdersManager() {

this.customersList = new Customers();

this.ordersList = new LinkedList<>();

}


public LinkedList<Order> getOrders() {

return ordersList;

}


public Order findOrderById(int id) {

if (ordersList.empty()) return null;


ordersList.findFirst();

while (true) {

Order current = ordersList.retrieve();

if (current.getOrderId() == id) return current;

if (ordersList.last()) break;

ordersList.findNext();

}

return null;

}


public void attachOrderToCustomer(Order order) {

Customer cust = customersList.searchById(order.getCustomerId());

if (cust == null) {

System.out.println("No matching customer for ID " + order.getCustomerId() + " — cannot attach order.");

return;

}

cust.addOrder(order);

}


public void addNewOrder(Order order) {

if (findOrderById(order.getOrderId()) != null) {

System.out.println("Order with ID " + order.getOrderId() + " already exists.");

return;

}

ordersList.addLast(order);

attachOrderToCustomer(order);

System.out.println("Order added: " + order.getOrderId());

}


public static Order parseOrderFromCsvLine(String line) {

String[] parts = line.split(",");

int orderId = Integer.parseInt(parts[0].trim().replace(""", ""));

int customerId = Integer.parseInt(parts[1].trim().replace(""", ""));

String productIds = parts[2].trim().replace(""", "");

double total = Double.parseDouble(parts[3].trim());

LocalDate date = LocalDate.parse(parts[4].trim(), DATE_FMT);

String status = parts[5].trim();

return new Order(orderId, customerId, productIds, total, date, status);

}


public void loadFromFile(String filePath) {

try {

File f = new File(filePath);

Scanner sc = new Scanner(f);

System.out.println("\nReading file: " + filePath);

if (sc.hasNextLine()) sc.nextLine(); // skip header if present

while (sc.hasNextLine()) {

String line = sc.nextLine().trim();

if (line.isEmpty()) continue;

Order ord = parseOrderFromCsvLine(line);

addNewOrder(ord);

}

sc.close();

System.out.println("Orders loaded successfully.\n");

} catch (Exception ex) {

System.out.println("Failed to load orders: " + ex.getMessage());

}

}


public void printAllOrders() {

if (ordersList.empty()) {

System.out.println("No orders to display.");

return;

}

System.out.println("OrderID\tCustomerID\tProducts\tTotal\tDate\tStatus");

System.out.println("---------------------------------------------------------");

ordersList.findFirst();

while (true) {

ordersList.retrieve().display();

if (ordersList.last()) break;

ordersList.findNext();

}

System.out.println("---------------------------------------------------------");

}


public static void demoAddManual() {

OrdersManager mgr = new OrdersManager();

mgr.addNewOrder(new Order(501, 101, "201:202:203", 4999.99, LocalDate.of(2024,1,1), "Delivered"));

mgr.addNewOrder(new Order(502, 102, "301:302", 1899.50, LocalDate.of(2024,1,1), "Pending"));

mgr.printAllOrders();

}


public static void demoLoadFromCsv() {

OrdersManager mgr = new OrdersManager();

mgr.loadFromFile("C:\\Users\\win\\Documents\\NetBeansProjects\\212project2025\\orders.csv");

mgr.printAllOrders();

}


public static void main(String[] args) {
	demoLoadFromCsv();

	}

	}
}
