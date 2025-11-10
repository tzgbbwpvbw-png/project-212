package project212;
import java.io.File;
import java.util.Scanner;
public class Reviews {
	 private static LinkedList<Review> reviews;    
	    private Products all_products;
	    private Customers all_Customers;
	     public Reviews(LinkedList<Review> reviews,LinkedList<Product> input_products,LinkedList<Customer> input_customers) {
	        this.reviews = reviews;
	        all_products=new Products(input_products);
	        all_Customers =new Customers(input_customers) ;
	    }
	    public Reviews() {
	        reviews = new LinkedList<>();
	        all_products=new Products();
	        all_Customers=new Customers();
	    }
	    public LinkedList<Review>get_all_Reviews()
	    {
	    return reviews;
	    }
	    public Products get_all_Products()
	    {
	    return all_products;
	    }
	    //Time Complexity: O(n) 
	 public Review SearchReviewById(int id){
	     if (reviews.empty()){
	         return null;
	     }
	     else{
	         reviews.findfirst();
	           while(true){
	              if (reviews.retrieve().getReviewID()==id)
	              {
	                  return reviews.retrieve();
	              }
	               if (reviews.last())
	                   break;
	               else
	                   reviews.findenext();
	         }
	       }
	     return null;
	 }
	// Time Complexity: O(n)
	    public  void assign_to_product(Review r){
	       Product p= all_products.SearchProductById(r.getProductID());
	        if(p!=null)
	           p.addReview(r);
	     } 
	 // Time Complexity: O(n)
	      public  void assign_to_customer(Review r){
	       Customer p= all_Customers.searchById(r.getCustomerID());
	       if(p!=null)
	           p.addReview(r);
	     } 
	    // Add new Review - Time Complexity: O(n) 
	    public void addReview(Review r) {
	        if (SearchReviewById(r.getReviewID())==null) { //not Exist
	            reviews.addLast(r);
	            assign_to_product(r);
	            assign_to_customer(r);
	           System.out.println("Review added: " + r.getReviewID());
	        } else {
	            System.out.println("Review with ID " + r.getReviewID() + " already exists");
	        }
	    }    
	  //Time Complexity: O(n) 
	 public void updateReview(int id, Review p) {
	       Review old=SearchReviewById(id);
	       if(old==null)
	            System.out.println("not exist to make update");
	       else
	           old.UpdateReview(p);
	    }
	 //Returns all reviews written by a specific customer -Time Complexity: O(n)
	 public LinkedList<Review> getReviewsByCustomer(int customerId){
	        LinkedList<Review> result = new LinkedList<>();
	        if (reviews.empty()) return result;
	        reviews.findfirst();
	        while(true){
	            if(reviews.retrieve().getCustomerID() == customerId)
	                result.addLast(reviews.retrieve());
	            if(reviews.last()) break;
	            reviews.findenext();
	        }
	        return result;
	    }

	    // Display all reviews - Time Complexity: O(n) 
	    public void displayAllReviews() {
	        System.out.println("All Reviews");
	       if (reviews.empty()){
	           System.out.println("no reviews exist");
	         return ;
	     }
	     else{
	         reviews.findfirst();
	           while(true){
	               Review p=reviews.retrieve();
	               System.out.println(p);
	               if (reviews.last())
	                   break;
	               else
	                   reviews.findenext();
	         }
	       }
	    
	    }
	     
	    
	    public static Review convert_String_to_Review(String Line) {
	        String[] a =Line.split(",", 5);
	        Review r = new Review(
	            Integer.parseInt(a[0].trim()), 
	            Integer.parseInt(a[1].trim()),  
	            Integer.parseInt(a[2].trim()), 
	            Integer.parseInt(a[3].trim()),  
	                a[4]
	            
	        );
	        return r;
	    }
	          public  void load_revews(String fileName) {
	            try {
	                File f = new File(fileName);
	                Scanner read = new Scanner(f);

	                System.out.println("Reading file: " + fileName);
	               read.nextLine().trim();
	                while (read.hasNextLine()) {
	                    String line = read.nextLine().trim();
	                    if (!line.isEmpty()) {                   
	                     Review  r=convert_String_to_Review(line);
	                     addReview(r);
	                                   
	                    }
	                }
	                read.close();
	                System.out.println("File loaded successfully!\n");
	            } catch (Exception e) {
	                System.out.println("Error reading file: " + e.getMessage());
	            }
	        }
	  

}
