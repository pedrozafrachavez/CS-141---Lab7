import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;

public class OrderDetails{
    // Prints the total price of an OrderID
    public static void totalPriceOfOrder( String orderID[], String productID[], 
    double unitPrice[], int quantity[], double discount[]){
        try{
            PrintWriter outfile = new PrintWriter("TotalPrice.txt");
            outfile.println("This data for an OrderID of 10001");
            outfile.println("UnitPrice      Quantity     Discount       Total");
            for (int i = 0; i < orderID.length; i++){
                if(orderID[i].equals("10001")){
                    outfile.println(String.format
                    ("%.2f          %s           %.2f          $%.2f", 
                    //For some reason teh discount was not factored?? 
                    // I made it output the table in lab 7
                    unitPrice[i], quantity[i], discount[i], ((unitPrice[i] * quantity[i]))));
                } 
            }
            outfile.close();
        } catch (Exception e){
            System.out.print(e.getMessage());
        }
    }
    public static void main(String[] args){
        //Calls arrays..
        String[] orderID = new String[2820];
        String[] productID = new String[2820];
        int[] quantity = new int[2820];
        double[] unitPrice = new double[2820];
        double[] discount = new double[2820];
        try {
            File readFile = new File("OrderDetails.txt");
            Scanner in = new Scanner(readFile);
            // Quantity, Unit Price, OrderID, ProductID, Discount
            while (in.hasNext()){
                for (int i = 0; i < orderID.length; i++){
                    // Get and sort data, line by line
                    String line = in.nextLine();
                    String[] parts = line.split(",");
                    
                    // Assigns the values of the text to variable
                    // "static" changing data that will be indexed into the array
                    // With each while loop iteration
                    int sQuantity = Integer.valueOf(parts[0]);
                    double sUnitPrice = Double.valueOf(parts[1]);
                    String sOrderID = String.valueOf(parts[2]);
                    String sProductID = String.valueOf(parts[3]);
                    double sDiscount = Double.valueOf(parts[4]);
                    // We have to append each one of these to their own array..
                    // indexing invidual arrays..
                    orderID[i] = sOrderID;
                    productID[i] = sProductID;
                    quantity[i] = sQuantity;
                    unitPrice[i] = sUnitPrice;
                    discount[i] = sDiscount;   
                }
            }
            in.close(); // Close your file!
        } catch (Exception e){
            System.out.print(e.getMessage());
        }
        // Creates and formats OrderDetails.txt
        try {
            PrintWriter outfile = new PrintWriter("OrderDetailsSorted.txt");
            // Use print or println method for now
            // They both act as normal print and println methods
            outfile.println("Order ID    ProductID   UnitPrice    Quantity    Discount");
            for (int i = 0; i < orderID.length; i++){
                outfile.println(String.format
                ("%s       %s          $%.2f         %s         %s", 
                orderID[i], productID[i], unitPrice[i], quantity[i], discount[i]));
            }
            outfile.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        // Formats and creates PriceSorted.txt..
        try {
            PrintWriter outfile = new PrintWriter("PriceSorted.txt");
            //UnitPrice * Quantity - (Discount * Quantity * UnitPrice
            outfile.println("Order ID    Price");
            for (int i = 0; i < orderID.length; i++){
                outfile.println(String.format("%s       $%.2f", orderID[i], 
                (unitPrice[i] * quantity[i] - (discount[i] * quantity[i] * 
                unitPrice[i]))));
            }
            outfile.close(); 
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        totalPriceOfOrder(orderID, productID, unitPrice, quantity, discount);
    }
}
