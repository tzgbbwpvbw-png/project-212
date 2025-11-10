package project;
import java.io.File; 
import java.util.Scanner; 
 
public class Customers { 
    private LinkedList<Customer> customers; 
 
    public Customers() { 
        customers = new LinkedList<>(); 
    } 
 
    public Customers(LinkedList<Customer> input_customers) { 
        customers = input_customers; 
    } 
 
    public LinkedList<Customer> get_customers() { 
        return customers; 
    } 
 
    public Customer searchById(int id) { 
        if (customers.empty()) 
        	return null; 
 
        customers.findFirst(); 
        while (true) { 
            if (customers.retrieve().getCustomerId() == id) 
                return customers.retrieve(); 
 
            if (customers.last()) 
            	break; 
            customers.findNext(); 
        } 
        return null; 
    } 
 
    public void addCustomer(Customer c) { 
        if (searchById(c.getCustomerId()) == null) { 
            customers.addLast(c); 
            System.out.println("Customer added: " + c.getName()); 
        } else { 
            System.out.println("Customer with ID " + c.getCustomerId() + " already exists."); 
        } 
    } 
 
    public void displayAll() { 
        if (customers.empty()) { 
            System.out.println("There are no customers to display."); 
            return; 
        } 
        System.out.println("--- all Customers ---"); 
        customers.findFirst(); 
        while (true) { 
            customers.retrieve().display(); 
            if (customers.last()) break; 
            customers.findNext(); 
        } 
    } 
 
    public static Customer convert_String_to_Customer(String line) { 
        String[] a = line.split(","); 
        return new Customer( 
            Integer.parseInt(a[0].trim()), 
            a[1].trim(), 
            a[2].trim() 
        ); 
    } 
 
    public void loadCustomers(String fileName) { 
        try { 
            File f = new File(fileName); 
            Scanner read = new Scanner(f); 
 
            System.out.println("Loading customer data from: " + fileName); 
            read.nextLine(); 
 
            while (read.hasNextLine()) { 
                String line = read.nextLine().trim(); 
                if (line.isEmpty()) continue; 
                Customer c = convert_String_to_Customer(line); 
                customers.addLast(c); 
            } 
 
            read.close(); 
            System.out.println("Customer list loaded successfully."); 
        } catch (Exception e) { 
            System.out.println("Error while loading customers: " + e.getMessage()); 
        } 
    } 
 
    public static void test1() { 
        Customers all = new Customers(); 
        Customer c1 = new Customer(201, "Omar Hassan", "omar.hassan@gmail.com"); 
        Customer c2 = new Customer(202, "Nour Adel", "nour.adel@yahoo.com"); 
 
        all.addCustomer(c1); 
        all.addCustomer(c2); 
 
        System.out.println("After manual addition:"); 
        all.displayAll(); 
    } 
 
    public static void test2() { 
        Customers all = new Customers(); 
        all.loadCustomers("C:\Users\win\Documents\NetBeansProjects\212project2025\customers.csv"); 
        all.displayAll(); 
    } 
 
    public static void main(String[] args) { 
        test1(); 
        test2(); 
    } 
} 
 
