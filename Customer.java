package pro;

  import java.io.File;

  import java.util.Scanner;


  public class Customer {

  private int customerId;

  private String name;

  private String email;

  private LinkedList<Order> orders;

  private LinkedList<Review> reviews;


  public Customer(int id, String name, String email) {

  this.customerId = id;

  this.name = name;

  this.email = email;

  this.orders = new LinkedList<>();

  reviews = new LinkedList<>();

  }


  public int getCustomerId() { return customerId; }

  public String getName() { return name; }

  public String getEmail() { return email; }


  public void addOrder(Order o) {

  orders.addLast(o);

  }


  public void addReview(Review r) {

  reviews.addLast(r);

  }


  public void displayReviews() {

  System.out.println("Reviews for " + name + ":");

  if (reviews.empty()) {

  System.out.println("No reviews yet.");

  } else {

  reviews.findFirst();

  while (true) {

  reviews.retrieve().display();

  if (reviews.last())

  break;

  reviews.findNext();

  }

  }

  }


  public void display() {

  System.out.println("Customer ID: " + customerId);

  System.out.println("Name: " + name);

  System.out.println("Email: " + email);

  System.out.println();

  displayOrders();

  displayReviews();

  }


  public void displayOrders() {

  if (orders.empty()) {

  System.out.println("No orders for customer " + name);

  return;

  }

  System.out.println("Orders for " + name + ":");

  orders.findFirst();

  while (true) {

  orders.retrieve().display();

  if (orders.last())

  break;

  orders.findNext();

  }

  }


  public static void test2() {

  Customer c1 = new Customer(201, "Omar Hassan", "omar.hassan@gmail.com");

  Customer c2 = new Customer(202, "Nour Adel", "nour.adel@yahoo.com");

  c1.display();

  c2.display();

  }


  public static void main(String[] args) {

	  test2();

	  }}