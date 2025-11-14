package prigect212;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Orders {

private LinkedList<Order> ordersList;
private Customers customersList;
static DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
public Orders(LinkedList<Customer> customers, LinkedList<Order> orders) {

this.customersList = new Customers(customers);
this.ordersList = orders;

}

public Orders() {
this.customersList = new Customers();
this.ordersList = new LinkedList<>();

}


public LinkedList<Order> getOrders() {
return ordersList;
}
public Order findOrderById(int id) {

if (ordersList.empty()) return null;
ordersList.findfirst();

while (true) {

Order current = ordersList.retrieve();
if (current.getOrderId() == id) return current;
if (ordersList.last()) break;
ordersList.findenext();
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

if (findOrderById(order.getOrderId()) == null) {
	ordersList.addLast(order);
	attachOrderToCustomer(order);
	System.out.println("Order added: " + order.getOrderId());


}else {


System.out.println("Order with ID " + order.getOrderId() + " already exists.");

}}


public static Order parseOrderFromCsvLine(String line) {

String[] parts = line.split(",");
int orderId = Integer.parseInt(parts[0].trim().replace("\\", ""));
int customerId = Integer.parseInt(parts[1].trim().replace("\\", ""));
String productIds = parts[2].trim().replace("\\", "");
double total = Double.parseDouble(parts[3].trim());
LocalDate date = LocalDate.parse(parts[4].trim(), DATE_FMT);
String status = parts[5].trim();
return new Order(orderId, customerId, productIds, total, date, status);

}


public void loadFromFile(String filePath) {
try {
File f = new File(filePath);
Scanner sc = new Scanner(f);
DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
System.out.println("\nReading file: " + filePath);
System.out.println("----------------------------------------------");
sc.nextLine();
while (sc.hasNextLine()) {
String line = sc.nextLine().trim();
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
ordersList.findfirst();
while (true) {
ordersList.retrieve().toString();
if (ordersList.last()) break;
ordersList.findenext();
}
System.out.println("---------------------------------------------------------");
}

public static void demoAddManual() {
Orders mgr = new Orders();
mgr.addNewOrder(new Order(501, 101, "201:202:203", 4999.99, LocalDate.of(2024,1,1), "Delivered"));
mgr.addNewOrder(new Order(502, 102, "301:302", 1899.50, LocalDate.of(2024,1,1), "Pending"));
mgr.printAllOrders();

}

public static void demoLoadFromCsv() {
Orders mgr = new Orders();
mgr.loadFromFile("C:\\Users\\deema\\Desktop\\.metadata\\prigect212\\src\\orders.csv");
mgr.printAllOrders();
}


public static void main(String[] args) {
	demoLoadFromCsv();

	}
	}


