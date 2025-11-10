package project212;
import java.io.File;
import java.util.Scanner;

public class Products {
    private LinkedList<Product> products;    
    public Products(LinkedList<Product> input_products) {
        products = input_products;
    }
    // Time Complexity: O(1)
    public Products() {
        products = new LinkedList<>();
    }
    // Time Complexity: O(1)
    public LinkedList<Product> get_Products()
    {
    return products;
    }
    // Time Complexity: O(n)
 public Product SearchProductById(int id){
     if (products.empty()){
         return null;
     }
     else{
         products.findfirst();
           while(true){
              if (products.retrieve().getProductId()==id)
              {
                  return products.retrieve();
              }
               if (products.last())
                   break;
               else
                   products.findenext();
         }
       }
     return null;
 }
    
    //add new product - Time Complexity: O(n)
    public void addProduct(Product p) {
        if (SearchProductById(p.getProductId())==null) { 
            products.addLast(p);
            System.out.println("Product added: " + p.getName());
        } else {
            System.out.println("Product with ID " + p.getProductId() + " already exists!");
        }
    }

	//remove a product by ID - Time Complexity: O(n)
    public void removeProduct(int id) {
  
             if (SearchProductById(id)!=null) {
                products.remove();
                System.out.println("Product removed: " + id);
            }
           else
        System.out.println("Product ID not found");
    }

    // Time Complexity: O(n)
    public void updateProduct(int id, Product p) {
       Product old=SearchProductById(id);
       if(old==null)
            System.out.println("not exist to make update");
       else
           old.UpdateProduct(p);
    }

  

    //Track out-of-stock products - Time Complexity: O(n)
    public void displayOutOfStock() {      
        System.out.println("Out of stock products:"); 
        
         if (products.empty()){
             System.out.println("no products exist");        
         }
        else{
             boolean found = false;
             products.findfirst();
           while(true){
              if (products.retrieve().getStock()==0)
              {
                   products.retrieve().toString();
                   found=true;
              }
               if (products.last())
                   break;
               else
                   products.findenext();
         }
            if (!found) System.out.println("All products in stock");
       }
     
    }

    //Display all products - Time Complexity: O(n)
    public void displayAllProducts() {
        System.out.println("All Products");
       if (products.empty()){
           System.out.println("no products exist");
         return ;
     }
     else{
         products.findfirst();
           while(true){
               Product p=products.retrieve();
                   p.toString(); 
                   p.displayReviews();
               if (products.last())
                   break;
               else
                   products.findenext();
         }
       }
    
    }
    // Time Complexity: O(n)
     public  void assign2(Review r){
           if (!products.empty()){
              products.findfirst();
              while(true){
                  Product p1= products.retrieve();
                  if(p1.getProductId()==r.getProductID()){
                      p1.addReview(r);
                  }
                  if(products.last())
                      break;
                  else
                    products.findenext();
              }              
           }
     }
     // Time Complexity: O(n)
      public  void assign(Review r){
       Product p=  SearchProductById(r.getProductID());
       if(p==null)
              System.out.println("not exist to assign review "+r.getReviewID()+"to it");
       else
           p.addReview(r);
     }
      public static Product convert_String_to_product(String Line)
      {
      String a[]=Line.split(",");
      Product p=new Product(Integer.parseInt(a[0]), a[1],Double.parseDouble(a[2]),Integer.parseInt(a[3]));
      return p;
      }
   public  void loadProducts(String fileName) {
          try {
              File f = new File(fileName);
              Scanner read = new Scanner(f);

              System.out.println("Reading file: " + fileName);
             read.nextLine().trim();
              while (read.hasNextLine()) {
                  String line = read.nextLine().trim();
                  if (!line.isEmpty()) {                  
                    Product  p=convert_String_to_product(line);
                    products.addLast(p);
                   
                  }
              }

              read.close();
              System.out.println("File loaded successfully!\n");
          } catch (Exception e) {
              System.out.println("Error reading file: " + e.getMessage());
          }
      }



}