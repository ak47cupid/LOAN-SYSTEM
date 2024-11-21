package loansystem;

import java.util.Scanner;

public class Customer { 
    public void addCustomer(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("\nFirst Name: ");
        String fname = sc.nextLine();
        System.out.print("Last Name: ");
        String lname = sc.nextLine();
        System.out.print("Address: ");
        String address = sc.nextLine();
        System.out.print("Email: ");
        String eml = sc.nextLine();
        System.out.print("Contact Number: ");
        String cn = sc.nextLine();
        while (!cn.matches("\\d{11}")) {
            System.out.println("Invalid input. Please enter a valid 11-digit phone number.");
            System.out.print("Enter Contact Number Again: ");
            cn = sc.nextLine();
        }
        
        String sql = "INSERT INTO customer (c_fname, c_lname, c_add, c_eml, c_cn) VALUES (?, ?, ?, ?, ?)";
        conf.addRecord(sql, fname, lname, address, eml, cn);
    }
    
    public void viewCustomer() {   
        String qry = "SELECT * FROM customer ";
        String[] hdrs = {"Customer ID", "First Name", "Last Name", "Address", "Email", "Contact Number"};
        String[] clms = {"c_id", "c_fname", "c_lname", "c_add", "c_eml", "c_cn"};

        config conf = new config();
        conf.viewRecords(qry, hdrs, clms);
    }
    
    public void updateCustomer(){
        Scanner sc= new Scanner(System.in);
        config conf = new config();
        int id;
        
        while (true) {
            System.out.print("Enter Customer ID to Update: ");
            if (sc.hasNextInt()) {
                id = sc.nextInt();
                break; 
            } else {
                System.out.println("Invalid input. Please enter a valid ID.");
                sc.next();
            }
        }
       
        while((conf.getSingleValue("SELECT c_id FROM customer WHERE c_id = ?", id)) == 0){
            System.out.println("Customer ID doesn't exist!");
            while (true) {
            System.out.print("Enter Customer ID again: ");
            if (sc.hasNextInt()) {
                id = sc.nextInt();
                break; 
            } else {
                System.out.println("Invalid input. Please enter a valid ID.");
                sc.next();
            }
            }
       }
       
        sc.nextLine();
        System.out.print("New Address: ");
        String address = sc.nextLine();
        System.out.print("New Email: ");
        String eml = sc.nextLine();
        System.out.print("New Contact Number: ");
        String cn = sc.nextLine();
        while (!cn.matches("\\d{11}")) {
            System.out.println("Invalid input. Please enter a valid 11-digit phone number.");
            System.out.print("Enter Contact Number Again: ");
            cn = sc.nextLine();
        }
        
        String qry = "UPDATE customer SET c_add = ?, c_eml = ?, c_cn = ? WHERE c_id = ?";
        conf.updateRecord(qry, address, eml, cn, id);       
    }
    
    public void deleteCustomer(){
        Scanner sc= new Scanner(System.in);
        config conf = new config();
        int id;
        
        while (true) {
            System.out.print("Enter Customer ID to Delete: ");
            if (sc.hasNextInt()) {
                id = sc.nextInt();
                break; 
            } else {
                System.out.println("Invalid input. Please enter a valid ID.");
                sc.next();
            }
        }
       
        while((conf.getSingleValue("SELECT c_id FROM customer WHERE c_id = ?", id)) == 0){
            System.out.println("Customer ID doesn't exist!");
            while (true) {
            System.out.print("Enter Customer ID again: ");
            if (sc.hasNextInt()) {
                id = sc.nextInt();
                break; 
            } else {
                System.out.println("Invalid input. Please enter a valid ID.");
                sc.next();
            }
            }
       }
       
        String qry = "DELETE FROM customer WHERE c_id = ?";
        conf.deleteRecord(qry, id);
    }
    
    public void customer(){
    Scanner sc = new Scanner(System.in);
    Customer cmt = new Customer();
        boolean exit = true;
        int opt;
        
        do{
            System.out.println("\n---------- WELCOME TO THE SYSTEM ---------");
            System.out.println("1. ADD CUSTOMER");
            System.out.println("2. VIEW LIST OF CUSTOMER");
            System.out.println("3. UPDATE CUSTOMER DETAILS");
            System.out.println("4. DELETE CUSTOMER");
            System.out.println("5. EXIT");
            System.out.println("------------------------------------------");
            while (true) {
            System.out.print("Select Option: ");
            if (sc.hasNextInt()) {
                opt = sc.nextInt();
                break; 
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next();
            }
            }
            
            switch(opt){
                case 1:
                   cmt.addCustomer();
                break;
                    
                case 2:
                    System.out.println("\n---- CUSTOMER INFORMATION ----");
                    cmt.viewCustomer();
                break;
                
                case 3:
                    System.out.println("\n---- CUSTOMER INFORMATION ----");
                    cmt.viewCustomer();
                    cmt.updateCustomer();
                    System.out.println("\n---- CUSTOMER INFORMATION ----");
                    cmt.viewCustomer();
                break;
                
                case 4:
                    System.out.println("\n---- CUSTOMER INFORMATION ----");
                    cmt.viewCustomer();
                    cmt.deleteCustomer();
                    System.out.println("\n---- CUSTOMER INFORMATION ----");
                    cmt.viewCustomer();
                break;
                
                case 5:
                    System.out.print("Do you want to exit? (yes/no): ");
                        String resp = sc.next();
                        if(resp.equalsIgnoreCase("yes")){
                        exit = false;
                        }
                    break;

                    default:
                        System.out.println("Option Error, Try Again");      
            }
        }while(exit);
    }
}
