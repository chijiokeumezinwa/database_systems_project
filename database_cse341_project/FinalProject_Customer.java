/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package pkgfinal.project;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author chijiokeumezinwa
 */
public class FinalProject_Customer {

    /**
     * @param args the command line arguments
     */
    public static String name;
    public static int id;
    public static int street_number;
    public static String street_name;
    public static Integer apt_number;
    public static String city;
    public static String state;
    public static int zip_code;
    public static String country;
    public static String planet;
    public static String email_addr;
    private static long num = 0;
    public static BigDecimal totalpricepaid;
    public static BigDecimal initialPrice;
    public static String initialModelType;
    public static String initialModelName;
    public static int initialModelID;
    public static int initialModelYear;
    public static String packageSelected;
    public static ArrayList <String> optionSelected = new ArrayList<String>();
    public static int priceofHy=0;
    public static int priceofCh=0;
    public static BigDecimal baseprice;
    public static BigDecimal pricePack;
    public static PreparedStatement ps;
    public static String insert_into_C;
    public static PreparedStatement ps2;
    public static PreparedStatement ps3;
    public static PreparedStatement ps4;
    public static PreparedStatement ps5;
    public static PreparedStatement ps6;
    public static PreparedStatement ps7;
    public static FinalProject_Customer a;
    public static ResultSet result;
    public static ResultSet result2;
    public static ResultSet result3;
    public static ResultSet result4;
    public static ResultSet result5;
    public static ResultSet result6;
    //public static Connection con;
    public FinalProject_Customer(){
        //myInstanceVariable ="A Customer";
        name = null;
        id =0;
        street_number = 0;
        street_name = null;
        apt_number=null;
        city=null;
        state=null;
        zip_code=0;
        country=null;
        planet=null;
        email_addr=null;
        ps= null;
        insert_into_C=null;
    }
    public static void main(String[] args)
        throws SQLException, IOException, java.lang.ClassNotFoundException{
        Scanner krb = new Scanner(System.in);
        String username=null;
        String password=null;
        int id_global;
        try{
            System.out.print("Type in your Username: ");
            username=krb.nextLine();
            
        }catch(NoSuchElementException e){
            System.out.println("\nBlank input");
        }
        try{
            System.out.print("Type in your password: ");
            password=krb.nextLine();
        }catch(NoSuchElementException e){
            System.out.println("\nBlank input");
        }
        // TODO code application logic here
        try (Connection con=DriverManager.getConnection
            ("jdbc:oracle:thin:@edgar0.cse.lehigh.edu:1521:cse241",username,password);
            ){
            System.out.println("Login was successful.");
            //Find information about a customer
            String ts="";
            String multiplenames_op="";
            result = null;
            result2 = null;
            String ts1 = null;
            String name_initial;
            //call my method here
            a = new FinalProject_Customer();
            name_initial=a.enter_first_last_name();
            if(name_initial == null){
                System.out.println("There must be a name entered.");
                return;
            }
            id_global= a.getId();
            String q;
            int i;

            q = "select * from customer where name =? and id = ?";
            ps =con.prepareStatement(q);
            ps.setString(1, name_initial);
            ps.setInt(2, id_global);
            
            result = ps.executeQuery();
            //the while condition represents the return of zero results.
            while(!result.next()){
                
                id = FinalProject_Customer.getRandomNumberInRange(10000, 900000);
                System.out.println ("Empty result.");
                try{
                    System.out.print("It seems like you are a new customer. If you are interested in filling out a new customer account please say yes. Otherwise say no: ");
                    ts = krb.nextLine();
                    ts = ts.toLowerCase();
                    int mistake =0;
                    while(mistake == 0){
                        if(ts.equals("yes")){
                            mistake = 1;
                            String name_initial2= name_initial.replace("''", "'");
                            System.out.print("New Customer -> ");
                            System.out.println(name_initial2);
                            //method to deal with customers.

                            name=name_initial2;
                            System.out.print("Enter Street Number: ");

                            while (!krb.hasNextInt() ) { 
                                System.out.println("only integers! Type in your street number. "); 
                                System.out.print("Enter your Street Number: ");
                                krb.next(); // discard 
                            } 

                            street_number = krb.nextInt();

                            krb.nextLine();
                            System.out.print("Enter Street Name: ");

                            street_name = krb.nextLine();
                            while (!street_name.matches("[a-zA-ZÀ-ÿ_\\-\\. ]+")
                                    ||street_name.isEmpty()
                                    ||street_name.length() < 2) {
                                System.out.println("Invalid street name try again.");
                                System.out.print("Enter your steet name: ");
                                street_name = krb.nextLine();
                            }

                            insert_into_C = "insert into customer values(?,?,?,?,?,?,?,?,?,?,?)";
                            ps = con.prepareStatement(insert_into_C);
                            System.out.print("Do you want to enter an apartment number? ");
                            String response = krb.nextLine();
                            response =response.toLowerCase();
                            int e = 0;
                            while (e ==0){
                                if(response.equals("yes") ){
                                    e=1;
                                    System.out.print("Type apartment number: ");
                                    
                                    while (!krb.hasNextInt()) { 
                                        System.out.println("only integers! Type in your apartment number. "); 
                                        System.out.print("Enter your Apartment Number: ");
                                        krb.next(); // discard 
                                    } 
                                    apt_number = krb.nextInt();
                                    krb.nextLine();
                                    ps.setInt(5, apt_number);
                                }
                                else if(response.equals("no")){
                                    e=1;
                                    ps.setNull(5, Types.INTEGER);
                                }
                                else{
                                    e=0;
                                    System.out.print("We did not get what you had said. Do you want to enter an apartment number? ");
                                    response = krb.nextLine();
                                    response =response.toLowerCase();
                                }
                            }
                            System.out.print("Enter City: ");
                            city = krb.nextLine();
                            
                            while (!city.matches("[a-zA-ZÀ-ÿ_\\- ]+")
                                    ||city.isEmpty()
                                     || city.length() < 2) {
                                System.out.println("Invalid city name try again.");
                                System.out.print("Enter your city name: ");
                                city = krb.nextLine();
                            }                       
                            
                            System.out.print("Enter State/Province: ");
                            state = krb.nextLine();
                            while (!state.matches("[a-zA-ZÀ-ÿ_\\- ]+")
                                    ||state.isEmpty()
                                    ||state.length() < 2) {
                                System.out.println("Invalid state/province name try again.");
                                System.out.print("Enter your state/province name: ");
                                state = krb.nextLine();
                            }                         
                            System.out.print("Enter zip code: ");
                            while (!krb.hasNextInt()) { 
                                 System.out.println("only integers! Type in your zip code number. "); 
                                 System.out.print("Enter your zip code Number: ");
                                 krb.next(); // discard 
                            }  
                            
                            zip_code= krb.nextInt();
                            
                            krb.nextLine();
                            System.out.print("Enter Country: ");
                            country = krb.nextLine();
                            while (!country.matches("[a-zA-ZÀ-ÿ_\\- ]+")
                                    ||country.isEmpty()
                                    ||country.length() < 2) {
                                System.out.println("Invalid country name try again.");
                                System.out.print("Enter your country name: ");
                                country = krb.nextLine();
                            } 
                            
                            System.out.print("Enter Planet: ");
                            planet = krb.nextLine();
                            while (!planet.matches("[a-zA-ZÀ-ÿ_\\- ]+")
                                    ||planet.isEmpty()
                                    ||planet.length() < 2) {
                                System.out.println("Invalid planet name try again.");
                                System.out.print("Enter your planet name: ");
                                planet = krb.nextLine();
                            } 
                            System.out.print("Enter email address: ");
                            email_addr= krb.next();


                            ps.setString(1,name);
                            ps.setInt(2,id);
                            ps.setInt(3,street_number);
                            ps.setString(4,street_name);
                            ps.setString(6,city);
                            ps.setString(7,state);
                            ps.setInt(8,zip_code);
                            ps.setString(9,email_addr);
                            ps.setString(10,country);
                            ps.setString(11,planet);
                            System.out.println(email_addr);
                            
                            System.out.print("Enter your credit card number or debit card number(18 number limit): ");
                           
                            while (!krb.hasNextLong() ) { 
                                System.out.println("Please enter a numeric format under 18 characters.");
                                System.out.print("Enter your credit card number or debit card number: ");
                                krb.next();
                            }
                            num = krb.nextLong();
                            krb.nextLine();
                            a.confirmNewAccount();
                            System.out.println("Credit/Debit Number: " + num);
                            System.out.print("Is this correct? Type in confirm to submit your information. "
                                    + "\nOtherwise type in no to opt out of setting up your customer account: ");
                            
                            String confirm = krb.next();
                            confirm = confirm.toLowerCase();
                            int d=0;
                            krb.nextLine();
                            while(d==0){
                                if(confirm.equals("confirm")){
                                    ps.executeUpdate();
                                    d = 1;
                                    String insert_payment = "insert into customer_payment values(?,?,?,?)";
                                    ps6 = con.prepareStatement(insert_payment);
                                    ps6.setString(1, name);
                                    ps6.setLong(2, num);
                                    ps6.setInt(3, id);
                                    ps6.setString(4, email_addr);
                                    ps6.executeUpdate();
                                    
                                    
                                    
                                }
                                else if(confirm.equals("no")){
                                    System.out.println("Customer account not created.");
                                    d=1;
                                    return;
                                }
                                else{
                                    d=0;
                                    System.out.println("We did not understand what you had typed in. Type in confirm to submit your information."
                                            + "\nOtherwise type in no to opt out of setting up your customer account: ");
                                    confirm = krb.next();
                                    confirm = confirm.toLowerCase();
                                }
                            }
                            //result =ps.executeQuery();
                            
                        }
                        else if(ts.equals("no")){
                            System.out.print("Are you a returning customer? If you forgot your pin, "
                                    + "you would have to contact our customer representatives in order to gain"
                                    + "access to your customer account. ");
                            System.out.println("Thank you and goodbye");
                            return;
                        }

                        else{
                            System.out.print("Your input was not understood. Do you want to exit? Type in yes, "
                                    + "otherwise type any other letter to continue writing your address. ");
                            ts = krb.nextLine();
                            ts = ts.toLowerCase();
                            if(ts.equals("yes")){
                                mistake = 1;
                                return;
                            }
                            else{
                               mistake = 0; 
                               System.out.print("It seems like you are a new customer. If you are interested in filling out a new customer account please say yes. Otherwise say no: ");
                               ts = krb.nextLine();
                               ts = ts.toLowerCase();
                            }
                            
                        }
                    }
                    //con.commit();
                }catch(Exception e){
                    e.printStackTrace();
                }
                
                q = "select * from customer where name =? and id = ?";
                ps =con.prepareStatement(q);
                ps.setString(1, name);
                ps.setInt(2, id);
                result = ps.executeQuery();
            }
            do{
                name = result.getString("name");
                id = result.getInt("id");
                street_number=result.getInt("street_number");
                street_name=result.getString("street_name");
                apt_number = null;
                city=result.getString("city");
                state=result.getString("state");
                zip_code=result.getInt("zip_code");
                email_addr=result.getString("email_c");
                country= result.getString("country");
                planet =result.getString("planet");
                
                int myValue = result.getInt("apt_number");
                
                if(result.wasNull()){
                    
                    System.out.println("Name:"+result.getString("name") 
                            +" ID: "+result.getString("ID")+ "\n" 
                                    + "Address:"+result.getString("street_number") 
                            + " "+
                        result.getString("street_name") +" "+
                        result.getString("city") +", "+ result.getString("state")
                            + " " + result.getString("zip_code") +"\nEmail Address: " + result.getString("EMAIL_C")
                        +"\nCountry: " + result.getString("country") + "\nPlanet: " + result.getString("planet") );
                }
                else{
                    apt_number = myValue;
                    System.out.println("Name:"+result.getString("name") 
                            +" ID: "+result.getString("ID")+ "\n" 
                                    + "Address:"+result.getString("street_number") 
                            + " "+
                    result.getString("street_name") +" Apt Number: "+ myValue + " "+
                    result.getString("city") +", "+ result.getString("state") + " " + result.getString("zip_code") 
                            +"\nEmail Address: " + result.getString("EMAIL_C")
                            +"\nCountry: " + result.getString("country") + "\nPlanet: " + result.getString("planet"));
                }
                
                String old_name = result.getString("name");
                String old_email=result.getString("email_c");
                String get_c_num = "select * from customer_payment where name = ? and id = ?";
                ps= con.prepareStatement(get_c_num);
                ps.setString(1, old_name);
                ps.setInt(2, id);
                result6 = ps.executeQuery();
                long oldc_num = 0;
                long oldc_num_used = 0;
                if(result6.next()){
                    //System.out.println("Card Info: " + result6.getLong("credit_debit_number"));
                    oldc_num =result6.getLong("credit_debit_number");
                }
                get_c_num = "select * from customer_payment_used where name = ? and id = ?";
                ps= con.prepareStatement(get_c_num);
                ps.setString(1, old_name);
                ps.setInt(2, id);
                result6 = ps.executeQuery();
                //oldc_num = 0;
                
                if(result6.next()){
                    //System.out.println("Card Info: " + result6.getLong("credit_debit_number"));
                    oldc_num_used =result6.getLong("credit_debit_number");
                }
                
                System.out.println("Welcome back " + name+" to our customer interface.");
                System.out.print("Do you want to update your information? Type yes if you want to. Otherwise type no: ");
                String option="";
                option = krb.next();
                option=option.toLowerCase();
                int go=0;

                if(option.equals("yes") ){

                    System.out.println("Name:"+name);
                    num =0;
                   
                    a.updateInfo();

                    a.confirmUpdatedAccount();
                    
                    long whichC = 0;
                    if(num == 0){
                        if(oldc_num != 0 ){
                            System.out.println("Card Info: " + oldc_num);
                            whichC = oldc_num;
                        }
                        else if( oldc_num_used != 0){
                            System.out.println("Card Info: " + oldc_num_used);
                            whichC = oldc_num_used;
                        }
                    }
                    
                    else{
                        System.out.println("Card Info: " + num);
                        whichC = num;
                    }
                   
                    
                    System.out.print("Is this information correct? Type in yes to confirm this change. Otherwise type in no to exit out of updating your information : ");
                    String confirm_D = krb.next();
                    confirm_D = confirm_D.toLowerCase();

                    int ex = 0;
                    while(ex ==0){
                        if(confirm_D.equals("yes")){
                            ex =1;

                            if(!(old_name.equals(name))){
                                //This procedure allows me to edit tables that have the name of the customer
                                //as foreign keys constraints
                                CallableStatement stmt = con.prepareCall("{call CASCADE_C_CBN_CBU_CR(?,?)}");
                                stmt.setString(1, old_name);
                                stmt.setString(2,name);

                                boolean hadResults = stmt.execute();

                                while (hadResults) {
                                    ResultSet rs = stmt.getResultSet();

                                    // process result set

                                    hadResults = stmt.getMoreResults();
                                }
                            }
                            if(!(old_email.equals(email_addr))){
                                CallableStatement stmt = con.prepareCall("{call REPLACE_C_EMAIL(?,?)}");
                                stmt.setString(1, old_email);
                                stmt.setString(2, email_addr);
                                boolean hadResults = stmt.execute();

                                while (hadResults) {
                                    ResultSet rs = stmt.getResultSet();

                                    // process result set

                                    hadResults = stmt.getMoreResults();
                                }

                            }
                            //next i should be able to update my address and email address without calling the procedure

                            String updateRecord ="UPDATE CUSTOMER SET STREET_NUMBER = ?,"
                                    + "STREET_NAME=?,APT_NUMBER = ?, CITY = ?, STATE = ?, ZIP_CODE = ?"
                                    + ", COUNTRY=?,PLANET=?"
                                    + " WHERE ID=?";
                            ps2 = con.prepareStatement(updateRecord);
                            ps2.setInt(1, street_number);
                            ps2.setString(2,street_name);
                            if(apt_number == null){
                                ps2.setNull(3, Types.INTEGER);
                            }
                            else{
                                ps2.setInt(3, apt_number);
                            }
                            ps2.setString(4, city);
                            ps2.setString(5, state);
                            ps2.setInt(6,zip_code);
                            ps2.setString(7,country);
                            ps2.setString(8,planet);
                            ps2.setInt(9,id);
                            ps2.executeUpdate();

                            if(num !=0){
                                String updateCredit = "UPDATE CUSTOMER_PAYMENT SET CREDIT_DEBIT_NUMBER = ? WHERE name = ? and id = ?"; 
                                ps7 = con.prepareStatement(updateCredit);
                                ps7.setLong(1, num);
                                ps7.setString(2, name);
                                ps7.setInt(3,id);
                                ps7.executeUpdate();
                                String updateCredit_used = "UPDATE CUSTOMER_PAYMENT_USED SET CREDIT_DEBIT_NUMBER = ? WHERE name = ? and id = ?"; 
                                ps7 = con.prepareStatement(updateCredit_used);
                                ps7.setLong(1, num);
                                ps7.setString(2, name);
                                ps7.setInt(3,id);
                                ps7.executeUpdate();
                            }
                            else if(num ==0){
                                String updateCredit = "UPDATE CUSTOMER_PAYMENT SET CREDIT_DEBIT_NUMBER = ? WHERE name = ? and id = ?"; 
                                ps7 = con.prepareStatement(updateCredit);
                                ps7.setLong(1, whichC);
                                ps7.setString(2, name);
                                ps7.setInt(3,id);
                                ps7.executeUpdate();
                                String updateCredit_used = "UPDATE CUSTOMER_PAYMENT_USED SET CREDIT_DEBIT_NUMBER = ? WHERE name = ? and id = ?"; 
                                ps7 = con.prepareStatement(updateCredit_used);
                                ps7.setLong(1, whichC);
                                ps7.setString(2, name);
                                ps7.setInt(3,id);
                                ps7.executeUpdate();
                            }
                        }
                        else if(confirm_D.equals("no")){
                            System.out.println("Customer account is not updated.");
                            ex =1;
                        }
                        else{
                            System.out.println("We did not understand what you had said. If you want to exit, type in exit. "
                                    + "\nOtherwise type in yes to confirm your account or no to not confirm the changes. :");
                            confirm_D = krb.next();
                            confirm_D = confirm_D.toLowerCase();
                            
                            if(confirm_D.equals("exit")){
                                return;
                            }
                            ex = 0;
                        }
                    }
                    go= 1;
                }
                if(option.equals("no") || go ==1){
                    
                    System.out.println("\nNew cars currently...");
                    //find if the customer is the owner of a used or new car.
                    String customer_query = "select * from customer_buys_new where name =? and id = ?";
                    ps3 =con.prepareStatement(customer_query);
                    ps3.setString(1, name);
                    ps3.setInt(2, id);

                    result2 = ps3.executeQuery();
                    int count = 0;
                    while(result2.next() && count ==0){
                        customer_query = "select * from customer_buys_new natural join v_model natural join new_vehicle "
                                + "where name =? and id = ?";
                        ps4 =con.prepareStatement(customer_query);
                        ps4.setString(1, name);
                        ps4.setInt(2, id);
                        
                        result3 = ps4.executeQuery();
                        ArrayList <BigDecimal>v_id = new ArrayList<BigDecimal>();
                        while(result3.next()){
                            System.out.println ("\nVehicle Id: "+
                                    result3.getBigDecimal("v_id") 
                                    +
                                 "\nModel id: " + result3.getInt("m_id") +
                                    "\nModel Name: " + result3.getString("modelname")
                                    + "\nModel Type: "+result3.getString("model_type")
                                    +"\nLocation: "+result3.getString("location")
                                    +"\nPrice Paid: $" + result3.getBigDecimal("price_paid"));
                            v_id.add(result3.getBigDecimal("v_id"));
                                   
                        }
                        
                        //traverse through each v_id
                        int lim = 0;
                        while (lim < v_id.size()){
                            String package_q = "select * from v_packages natural join packages where v_id = ? ";
                            ps4 = con.prepareStatement(package_q);
                            BigDecimal a = v_id.get(lim); 
                            ps4.setBigDecimal(1, a);
                            result3 = ps4.executeQuery();
                            
                            while(result3.next()){
                                System.out.println("\nPackage selected for vehicle " +result3.getBigDecimal("v_id") + ": " + result3.getString("package_name"));
                            }
                            
                            String option_q = "select * from vehicle_options where v_id = ? ";
                            ps4 = con.prepareStatement(option_q);
                            ps4.setBigDecimal(1, a);
                            result3 = ps4.executeQuery();
                            
                            while(result3.next()){
                                System.out.println("\nOption selected for vehicle " + result3.getBigDecimal("v_id") + ": " + result3.getString("option_name"));
                            }
                            lim +=1;
                        }
                        count =1;
                    }
                    
                    
                    if(!result2.next() && count == 0){
                        //System.out.println("These are the available cars we have on record. If there are discrepancies please contact our representatives.");
                        System.out.println("We have no available new cars under your account at the moment. "
                                + "If there are discrepancies please contact our representatives.");
                    }
                    
                    System.out.println();
                    System.out.println("Used cars currently...");
                    customer_query = "select * from customer_buys_used where name =? and id = ?";
                    ps3 =con.prepareStatement(customer_query);
                    ps3.setString(1, name);
                    ps3.setInt(2, id);
                    result2 = ps3.executeQuery();
                    count = 0;
                    while(result2.next() && count ==0){
                        customer_query = "select * from customer_buys_used natural join used_vehicles natural join v_model_used "
                            + "where name =? and id = ?";
                        ps3 =con.prepareStatement(customer_query);
                        ps3.setString(1, name);
                        ps3.setInt(2, id);
                        result3 = ps3.executeQuery();
                        ArrayList <BigDecimal> v_id_used = new ArrayList <BigDecimal>();
                        while(result3.next()){
                            System.out.println ("\nVehicle Id: "+
                                        result3.getBigDecimal("v_id") 
                                        + "\nModel id: " + result3.getInt("m_id") +
                                        "\nModel Name: " + result3.getString("modelname")
                                        + "\nModel Type: "+result3.getString("model_type")
                                        +"\nLocation: "+result3.getString("location")
                                        +"\nPrice Paid: $" + result3.getBigDecimal("price_paid"));
                            v_id_used.add(result3.getBigDecimal("v_id"));
                        }
                                                //traverse through each v_id
                        int lim = 0;
                        while (lim < v_id_used.size()){
                            String package_q = "select * from v_packages_used natural join packages where v_id = ? ";
                            ps4 = con.prepareStatement(package_q);
                            BigDecimal b=  v_id_used.get(lim);
                            ps4.setBigDecimal(1, b);
                            result3 = ps4.executeQuery();
                            
                            while(result3.next()){
                                System.out.println("\nPackage selected for vehicle " +result3.getBigDecimal("v_id") + ": " + result3.getString("package_name"));
                            }
                            String option_q = "select * from vehicle_options where v_id = ? ";
                            ps4 = con.prepareStatement(option_q);
                            ps4.setBigDecimal(1, b);
                            result3 = ps4.executeQuery();
                            
                            while(result3.next()){
                                System.out.println("\nOption selected for vehicle " + result3.getBigDecimal("v_id") + ": " + result3.getString("option_name"));
                            }
                            lim +=1;
                        }
                        count  =1;
                    }
                    if(!result2.next() && count == 0){
                        //System.out.println("These are the available cars we have on record. If there are discrepancies please contact our representatives.");
                        System.out.println("We have no available used cars under your account at the moment. "
                                + "If there are discrepancies please contact our representatives.");
                    }
                    System.out.println();
                    //I should print a list of their most recent repairs.
                    String repairs = "select * from customer_repair where name = ? and id = ? ";
                    ps3 = con.prepareStatement(repairs);
                    ps3.setString(1, name);
                    ps3.setInt(2, id);
                    result3 = ps3.executeQuery();
                    count = 0;
                    while(result3.next() && count == 0){
                        System.out.println("\nMost recent repairs: \nLocation:" + result3.getString("location") +
                                "\nDate of repair: " + result3.getDate("date_repair")+ "\nCost: "+ result3.getBigDecimal("cost"));
                        count =1;
                    }
                    
                    if(!result3.next()&& count == 0){
                        System.out.println("\nNo recent repair appointments under your account.");
                    }
                    
                    // Ask if they want to submit a request for an appointment.
                    
                    
                    //Go to the process of buying a new car.
                    //select to go to the option of a new car or a used car
                    //detail cost of each car.
                    //Steps of buying a new car?
                    //1.) Ask the type of model they are looking for.
                    //2.) print out the model descriptions so they get an idea of what they are looking for
                    
                    System.out.print("\nDo you want to purchase a new car? Type in yes to continue. Otherwise type in no to exit: ");
                    option = krb.next();
                    option=option.toLowerCase();
                    int wrong_key=0;
                    while ( wrong_key == 0){
                        if(option.equals("yes")){
                            BigInteger new_vid = new BigInteger(66,new Random());
                            int package_ch =2 ;
                            wrong_key = 1;
                            System.out.println("The model types that we have available are:\n"
                                    + "Model K, the “Kart”, a small electric vehicle for use on the surface of the earth.\n"
                                    + "Model U, the undersea vehicle, used as a personal submarine.\n"
                                    + "Model M, the moon vehicle, used to ride on the surface of the moon.\n"
                                    + "Model S, the spacecraft, used for space travel. This vehicle is capable of landing on most planets and moons,"
                                    + " and, after refueling, can re-launch into space.");
                            System.out.print("Select M for Model M, select U for Model U, select S for Model S, select K for Model K: ");

                            String model_selected = krb.next();
                            model_selected = model_selected.toLowerCase();
                            int wrong_key2=0;
                            while( wrong_key2 ==0){
                                if(model_selected.equals("m")){
                                    String car_query = "select * from model where model_type = ? ";
                                    ps5 = con.prepareStatement(car_query);
                                    ps5.setString(1,"Model M");
                                    result2 = ps5.executeQuery();

                                    while(result2.next()){

                                        System.out.println();
                                        System.out.println("Model ID: " + result2.getInt("m_id")+"\nModel name: "+ result2.getString("modelname")+
                                        "\nYear: " + result2.getInt("year") + "\nModel Type: " + result2.getString("model_type") +
                                        "\nPrice: $" + result2.getBigDecimal("list_price"));
                                    }

                                    if(!result2.next()){
                                        System.out.println("That is all of the Model M vehicles being sold right now.");
                                    }

                                    //Next have them enter the name of the vehicle and year they want to get.
                                    System.out.println("Please enter the model id of the vehicle you wish to purchase: ");
                                    System.out.print("Model ID: ");
                                    int car_wish_to_own  =0;
                                    while (!krb.hasNextInt()) { 
                                        System.out.println("only numbers! Type in the model id. "); 
                                        System.out.print("Enter the model id of the vehicle you wish to purchase: ");
                                        krb.next(); // discard 
                                    }

                                    car_wish_to_own = krb.nextInt();
                                    krb.nextLine();
                                    int z = 0;
                                    int t = 0;
                                    while ( z ==0){
                                        String specific_model = "select * from model where m_id = ? and model_type = ?";
                                        ps5 = con.prepareStatement(specific_model);
                                        ps5.setInt(1,car_wish_to_own);
                                        ps5.setString(2, "Model M");

                                        result4 = ps5.executeQuery();
                                        while(result4.next()){
                                            z = 1;t = 1;
                                        }

                                        while( t == 0 && !result4.next()){
                                            z=0;
                                            System.out.print("We don't recognize the id that you wrote. If you wish to exit, please type in exit. \n"
                                                    + "Otherwise type in any other character to continue: ");
                                            String dec = krb.next();
                                            dec =dec.toLowerCase();
                                            if(dec.equals("exit")){
                                                return;
                                            }
                                            else{
                                                System.out.print("Please enter a model id of the vehicle you wish to purchase: ");
                                                while (!krb.hasNextInt()) { 
                                                    System.out.println("only numbers! Type in the model id. "); 
                                                    System.out.print("Enter your model id number: ");
                                                    krb.next(); // discard 
                                                }
                                                car_wish_to_own = krb.nextInt();
                                                specific_model = "select * from model where m_id = ? and model_type = ?";
                                                ps5 = con.prepareStatement(specific_model);
                                                ps5.setInt(1,car_wish_to_own);
                                                ps5.setString(2, "Model M");
                                            }
                                            result4 = ps5.executeQuery();
                                        }
                                    }
                                    String specific_model = "select * from model where m_id = ? and model_type = ?";
                                    ps5 = con.prepareStatement(specific_model);
                                    ps5.setInt(1,car_wish_to_own);
                                    ps5.setString(2, "Model M");

                                    result4 = ps5.executeQuery();
                                    while(result4.next()){
                                        z=1;
                                        System.out.println("\nThe base price of this vehicle" + " the "+
                                                result4.getString("modelname")+ " is $"+result4.getBigDecimal("list_price"));
                                        baseprice = result4.getBigDecimal("list_price");
                                        initialPrice = baseprice;
                                        initialModelID= result4.getInt("M_ID");
                                        initialModelName =result4.getString("modelname");
                                        initialModelYear = result4.getInt("YEAR");
                                        initialModelType = result4.getString("MODEL_TYPE");

                                    }


                                    System.out.println();

                                    System.out.println("Do you want to purchase a package? The packages available with Model M are:"
                                            + "\nthe Superman Package, which outfits your vehicle as a large truck able to navigate "
                                            + "the rough terrain of Mars "
                                            + "\nthe Luxury Package, which determines your vehicle to be as cozy as classy to your liking."
                                            + "\nFinally, you can purchase the Amenities package which guarantees you a comfortable experience "
                                            + "in your new car.");
                                    System.out.println();

                                    String print_out_options = "WITH RESULT AS ( select option_name as options_name,package_id from options_in_packages where package_id=1 ) SELECT * FROM OPTIONS NATURAL JOIN RESULT";
                                    ps5 = con.prepareStatement(print_out_options);
                                    result4 = ps5.executeQuery();
                                    System.out.println("The Superman package includes: ");
                                    while(result4.next()){
                                        System.out.println("a " + result4.getString("OPTIONS_NAME") + ": " + result4.getString("DESCRIPTION") );
                                    }
                                    System.out.println();
                                    print_out_options = "WITH RESULT AS ( select option_name as options_name,package_id from options_in_packages where package_id=3 ) SELECT * FROM OPTIONS NATURAL JOIN RESULT";
                                    ps5 = con.prepareStatement(print_out_options);
                                    result4 = ps5.executeQuery();
                                    System.out.println("The Luxury package includes: ");
                                    while(result4.next()){
                                        System.out.println("a " + result4.getString("OPTIONS_NAME") + ": " + result4.getString("DESCRIPTION") );
                                    }
                                    System.out.println();
                                    print_out_options = "WITH RESULT AS ( select option_name as options_name,package_id from options_in_packages where package_id=2 ) SELECT * FROM OPTIONS NATURAL JOIN RESULT";
                                    ps5 = con.prepareStatement(print_out_options);
                                    result4 = ps5.executeQuery();
                                    System.out.println("The Amenities package includes: ");
                                    while(result4.next()){
                                        System.out.println("a " + result4.getString("OPTIONS_NAME") + ": " + result4.getString("DESCRIPTION") );
                                    }
                                    System.out.print("\nwhich package do you wish to purchase? Type in superman for the superman package"
                                            + ", type in luxury for the luxury package, or type in amenities for the amenities package."
                                            + "\nThe Amenities package is chosen for you already but you can upgrade it to the luxury or superman package"
                                            + ": ");

                                    String decision = "";
                                    decision = krb.next();
                                    decision = decision.toLowerCase();
                                    int exit_loop = 0;
                                    String insert_package;
                                    String insert_vehicles;
                                    String package_chosen="";

                                    while( exit_loop==0 ){
                                        if(decision.equals("superman")){
                                            exit_loop=1;
                                            wrong_key2=1;
                                            System.out.println("superman");
                                            package_chosen = decision;
                                        }
                                        else if(decision.equals("luxury")){
                                            exit_loop=1;
                                            wrong_key2=1;
                                            System.out.println("luxury");
                                            package_chosen = decision;
                                        }
                                        else if(decision.equals("amenities")){
                                            exit_loop=1;
                                            wrong_key2=1;
                                            System.out.println("amenities");
                                            package_chosen = decision;
                                        }

                                        else{
                                            System.out.print("Did you mean to type that? Type in exit to not purchase the car. Type in amenities to exit out of choosing an upgrade in packages.\n"
                                                   
                                                    + "Otherwise type in your preferred package upgrade of superman or luxury: ");
                                            decision = krb.next();
                                            decision = decision.toLowerCase();
                                            if(decision.equals("exit")){
                                                return;
                                            }
                                            if(decision.equals("amenities")){
                                                exit_loop=1;
                                                wrong_key2=1;
                                                package_chosen = "amenities";
                                            }
                                            else{
                                                exit_loop=0;
                                            }
                                        }
                                    }
                                    String obtain_package_price ="";
                                    pricePack = null;

                                    if(package_chosen.equals("superman")){
                                        package_ch =1;
                                        obtain_package_price = "select * from packages where package_id = ? ";
                                        ps5 = con.prepareStatement(obtain_package_price);
                                        ps5.setInt(1, package_ch);
                                        result4=ps5.executeQuery();
                                        //pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                        while(result4.next()){
                                            pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                        }
                                    }

                                    else if(package_chosen.equals("luxury")){
                                        package_ch =3;
                                        obtain_package_price = "select * from packages where package_id = ? ";
                                        ps5 = con.prepareStatement(obtain_package_price);
                                        ps5.setInt(1, package_ch);
                                        result4=ps5.executeQuery();
                                        while(result4.next()){
                                            pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                        }
                                    }
                                    else if(package_chosen.equals("amenities") || package_ch ==2){
                                        package_ch =2;
                                        obtain_package_price = "select * from packages where package_id = ? ";
                                        ps5 = con.prepareStatement(obtain_package_price);
                                        ps5.setInt(1, package_ch);
                                        result4=ps5.executeQuery();
                                        while(result4.next()){
                                            pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                        }
                                    }

                                    packageSelected = package_chosen;
                                    totalpricepaid = baseprice;

                                    if(package_ch == 2 ){
                                        //the amenities package was selected for them or they chose it.
                                        //You can choose to add the hybrid as well as bedazzling your wheels with 20'' chrome wheels.
                                        //add the price of the package amenities

                                        totalpricepaid = totalpricepaid.add(pricePack);
                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                        System.out.print("We have a hybrid option available for this vehicle. "
                                                + "Do you want to know more about it? ");
                                        String op_decision = krb.next();
                                        op_decision = op_decision.toLowerCase();
                                        int op_d = 0;

                                        while(op_d == 0){
                                            if(op_decision.equals("yes")){
                                                op_d =1;
                                                String choose_hybrid = "SELECT * FROM OPTIONS where options_name = ?";
                                                ps5 = con.prepareStatement(choose_hybrid);
                                                ps5.setString(1, "Hybrid ");
                                                result4 = ps5.executeQuery();
                                                priceofHy=0;
                                                String op_name;
                                                String op_descr;
                                                while(result4.next()){
                                                    System.out.println(result4.getString("OPTIONS_NAME") +"$"+ result4.getInt("LIST_PRICE") + " Description: " + result4.getString("DESCRIPTION"));
                                                    priceofHy = result4.getInt("LIST_PRICE");
                                                    op_name = result4.getString("OPTIONS_NAME");
                                                    op_descr = result4.getString("DESCRIPTION");
                                                }
                                                System.out.print("Do you want to purchase this option? ");
                                                String purchase_hy = krb.next();
                                                purchase_hy = purchase_hy.toLowerCase();
                                                int a = 0;
                                                while( a == 0){
                                                    if(purchase_hy.equals("yes")){
                                                        a =1 ;
                                                        //add to v_options
                                                        //get price of option and to total price paid
                                                        //optionSelected[0]= "Hybrid";
                                                        optionSelected.add("Hybrid");
                                                        totalpricepaid = totalpricepaid.add(new BigDecimal(priceofHy));
                                                        System.out.println("The price of your vehicle with this option is right now currently is $" + totalpricepaid);
                                                    }
                                                    else if(purchase_hy.equals("no")){
                                                        a =1 ;
                                                        System.out.println("The price of your vehicle without this option is right now currently is $" + totalpricepaid);

                                                    }
                                                    else{
                                                        a=0;
                                                        System.out.print("We did not get what you were trying to say. Can you enter yes "
                                                                + "if you want to purchase the hybrid option, otherwise say no. ");
                                                        purchase_hy = krb.next();
                                                        purchase_hy = purchase_hy.toLowerCase();
                                                    }
                                                }

                                            }
                                            else if(op_decision.equals("no") || op_decision.equals("exit")){
                                                op_d =1;

                                            }
                                            else{
                                                op_d=0;
                                                System.out.println("We did not understand what you had typed. If you want to exit, type in exit."
                                                        + "\nIf you want to know more about the hybrid type in yes. ");
                                                op_decision = krb.next();
                                                op_decision = op_decision.toLowerCase();
                                            }
                                        }

                                        System.out.print("We have a option to add 20'' or larger chrome wheels to this vehicle. "
                                            + "Do you want to know more about it? ");
                                        op_decision = krb.next();
                                        op_decision = op_decision.toLowerCase();
                                        op_d = 0;
                                        while(op_d == 0){
                                            if(op_decision.equals("yes")){
                                                op_d =1;
                                                String choose_hybrid = "SELECT * FROM OPTIONS where options_name = ?";
                                                ps5 = con.prepareStatement(choose_hybrid);
                                                ps5.setString(1, "Chrome wheels 20” or Larger");
                                                result4 = ps5.executeQuery();
                                                priceofCh=0;
                                                while(result4.next()){
                                                    System.out.println(result4.getString("OPTIONS_NAME") + " $" + result4.getInt("LIST_PRICE") + " " + result4.getString("DESCRIPTION"));
                                                    priceofCh = result4.getInt("LIST_PRICE");
                                                }
                                                System.out.print("Do you want to purchase this option? ");
                                                String purchase_hy = krb.next();
                                                purchase_hy = purchase_hy.toLowerCase();
                                                int a = 0;
                                                while( a == 0){
                                                    if(purchase_hy.equals("yes")){
                                                        a =1 ;
                                                        //add to v_options
                                                        //get price of option and to total price paid
                                                        //optionSelected[1] = "Chrome wheels 20” or Larger";
                                                        optionSelected.add("Chrome wheels 20” or Larger");
                                                        totalpricepaid = totalpricepaid.add(new BigDecimal(priceofCh));
                                                        System.out.println("The price of your vehicle right with this option is now currently is $" + totalpricepaid);
                                                    }
                                                    else if(purchase_hy.equals("no")){
                                                        a =1 ;

                                                    }
                                                    else{
                                                        a=0;
                                                        System.out.println("We did not get what you were trying to say. Can you enter yes"
                                                                + "if you want to purchase the hybrid option, otherwise say no. ");
                                                        purchase_hy = krb.next();
                                                        purchase_hy = purchase_hy.toLowerCase();
                                                    }
                                                }
                                            }
                                            else if(op_decision.equals("no") || op_decision.equals("exit")){
                                                op_d =1;

                                            }
                                            else{
                                                op_d=0;
                                                System.out.println("We did not understand what you had typed. If you want to exit, type in exit."
                                                        + "\nIf you want to know more about the chrome wheels option type in yes. ");
                                                op_decision = krb.next();
                                                op_decision = op_decision.toLowerCase();
                                            }
                                        }
                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                    }
                                    else{
                                        //they chose a package other than the amenities package.
                                        //totalpricepaid= pricePack + totalpricepaid;
                                        totalpricepaid = totalpricepaid.add(pricePack);
                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                    }
                                    System.out.println();
                                    //list a summary of the model that the customer is about to purchase.
                                    //ask to confirm if this correct. if they say yes, the records will all be updated.
                                    a.SummaryInfo();
                                    
                                    if(oldc_num != 0 ){
                                        System.out.println("Card Info: " + oldc_num);
                                    }
                                    else if( oldc_num_used != 0){
                                        System.out.println("Card Info: " + oldc_num_used);
                                    }
                                    System.out.print("Is this correct? Type in confirm to place your order. Otherwise type in no to opt out of ordering: ");
                                    String final_decision = krb.next();
                                    final_decision = final_decision.toLowerCase();
                                    int ab=0;
                                    while (ab ==0){
                                        if(final_decision.equals("confirm")){
                                            ab =1;
                                            //do all my inserts here.
                                            if(oldc_num == 0 && oldc_num_used != 0 ){
                                                String insert_Custom = "insert into customer_payment  values(?,?,?,?)";
                                                ps5 = con.prepareStatement(insert_Custom);
                                                ps5.setString(1, name);
                                                ps5.setLong(2, oldc_num_used);
                                                ps5.setInt(3, id);
                                                ps5.setString(4, email_addr);
                                                ps5.executeUpdate();
                                            }
                                            
                                            
                                            //insert vehicle, v_packages, vehicle_options if there are chrome wheels or hybrid options,
                                            //customer_buys_new , v_model, and new_vehicle
                                            insert_vehicles = "insert into vehicle values(?)";
                                            ps5 = con.prepareStatement(insert_vehicles);
                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                            ps5.executeUpdate();

                                            insert_package="insert into v_packages values(?,?)";
                                            ps5 = con.prepareStatement(insert_package);
                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                            ps5.setInt(2, package_ch);
                                            ps5.executeUpdate();

                                            //insert into vehicle_options
                                            if(priceofCh !=0){
                                                CallableStatement stmt2 = con.prepareCall("{call options_to_have(?,?,?)}");
                                                stmt2.setBigDecimal(1, new BigDecimal(new_vid));
                                                stmt2.setString(2, "Chrome wheels 20” or Larger");
                                                stmt2.setInt(3, priceofCh);
                                                boolean hadResults2 = stmt2.execute();

                                                while (hadResults2) {
                                                    ResultSet rs = stmt2.getResultSet();

                                                    // process result set

                                                    hadResults2 = stmt2.getMoreResults();
                                                }
                                            }
                                            if(priceofHy !=0){
                                                CallableStatement stmt2 = con.prepareCall("{call options_to_have(?,?,?)}");
                                                stmt2.setBigDecimal(1, new BigDecimal(new_vid));
                                                stmt2.setString(2, "Hybrid ");
                                                stmt2.setInt(3, priceofHy);
                                                boolean hadResults2 = stmt2.execute();

                                                while (hadResults2) {
                                                    ResultSet rs = stmt2.getResultSet();

                                                    // process result set

                                                    hadResults2 = stmt2.getMoreResults();
                                                }
                                            }
                                            String insert_cust_buys_new = "insert into customer_buys_new values(?,?,?,?,?)";
                                            ps5 = con.prepareStatement(insert_cust_buys_new);
                                            ps5.setString(1, name);
                                            ps5.setInt(2, id);
                                            ps5.setBigDecimal(3,new BigDecimal(new_vid));
                                            ps5.setString(4, email_addr);
                                            ps5.setBigDecimal(5,totalpricepaid);
                                            ps5.executeUpdate();

                                            String insert_vmodel = "insert into v_model values(?,?,?)";
                                            ps5 = con.prepareStatement(insert_vmodel);
                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                            ps5.setInt(2, initialModelID);
                                            ps5.setString(3, initialModelName);
                                            ps5.executeUpdate();

                                            String insert_newvehicle = "insert into new_vehicle values(?,?,?)";
                                            ps5 = con.prepareStatement(insert_newvehicle);
                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                            ps5.setString(2, "Moon Exploration");
                                            ps5.setString(3, initialModelType);
                                            ps5.executeUpdate();
                                            System.out.println("Order has been submitted.");
                                            System.out.println("Your order will be available for pickup at the Moon Exploration center.");

                                        }
                                        else if(final_decision.equals("no")){
                                            System.out.println("No new car was purchased.");
                                            ab =1;
                                            //exit out of here
                                        }
                                        else{
                                            System.out.print("We did not understand what you had typed. Please type in confirm to place your order."
                                                    + "\nOtherwise type in no to opt of ordering. ");
                                            final_decision = krb.next();
                                            final_decision = final_decision.toLowerCase();
                                        }
                                    }
                                }
                                else if(model_selected.equals("u")){
                                    String car_query = "select * from model where model_type=?";
                                    ps5 = con.prepareStatement(car_query);
                                    ps5.setString(1,"Model U");
                                    result2 = ps5.executeQuery();
                                    
                                    while(result2.next()){
                                        System.out.println();
                                        System.out.println("Model ID: " + result2.getInt("m_id")+"\nModel name: "+ result2.getString("modelname")+
                                        "\nYear: " + result2.getInt("year") + "\nModel Type: " + result2.getString("model_type") +
                                        "\nPrice: $" + result2.getBigDecimal("list_price"));
                                    }
                                    if(!result2.next()){
                                        System.out.println("That is all of the Model U vehicles being sold right now.");
                                    }
                                    //Next have them enter the name of the vehicle and year they want to get.
                                    System.out.println("Please enter the model id of the vehicle you wish to purchase: ");
                                    System.out.print("Model ID: ");
                                    int car_wish_to_own  =0;
                                    while (!krb.hasNextInt()) { 
                                        System.out.println("only numbers! Type in the model id. "); 
                                        System.out.print("Enter your model id number: ");
                                        krb.next(); // discard 
                                    }
                                    car_wish_to_own = krb.nextInt();
                                    krb.nextLine();
                                    
                                    int z = 0;
                                    int t = 0;
                                    while ( z ==0){
                                        String specific_model = "select * from model where m_id = ? and model_type = ?";
                                        ps5 = con.prepareStatement(specific_model);
                                        ps5.setInt(1,car_wish_to_own);
                                        ps5.setString(2, "Model U");

                                        result4 = ps5.executeQuery();
                                        while(result4.next()){
                                            z = 1;t = 1;
                                        }

                                        while( t == 0 && !result4.next()){
                                            z=0;
                                            System.out.print("We don't recognize the id that you wrote. If you wish to exit, please type in exit. \n"
                                                    + "Otherwise type in any other character to continue: ");
                                            String dec = krb.next();
                                            dec =dec.toLowerCase();
                                            if(dec.equals("exit")){
                                                return;
                                            }
                                            else{
                                                System.out.print("Please enter a model id of the vehicle you wish to purchase: ");
                                                while (!krb.hasNextInt()) { 
                                                    System.out.println("only numbers! Type in the model id. "); 
                                                    System.out.print("Enter your model id number: ");
                                                    krb.next(); // discard 
                                                }
                                                car_wish_to_own = krb.nextInt();
                                                specific_model = "select * from model where m_id = ? and model_type = ?";
                                                ps5 = con.prepareStatement(specific_model);
                                                ps5.setInt(1,car_wish_to_own);
                                                ps5.setString(2, "Model U");
                                            }
                                            result4 = ps5.executeQuery();
                                        }
                                    }
                                    
                                    
                                    
                                    String specific_model = "select * from model where m_id = ? and model_type = ? ";
                                    ps5 = con.prepareStatement(specific_model);
                                    ps5.setInt(1,car_wish_to_own);
                                    ps5.setString(2,"Model U");
                                    
                                    result4 = ps5.executeQuery();
                                    while(result4.next()){
                                        System.out.println("The base price of this vehicle" + " the "+
                                                result4.getString("modelname")+ " is $"+result4.getBigDecimal("list_price"));
                                        baseprice = result4.getBigDecimal("list_price");
                                        initialPrice = baseprice;
                                        initialModelID= result4.getInt("M_ID");
                                        initialModelName =result4.getString("modelname");
                                        initialModelYear = result4.getInt("YEAR");
                                        initialModelType = result4.getString("MODEL_TYPE");
                                    }
                                    
                                    System.out.println();
                                    
                                    System.out.println("Do you want to purchase a package? The packages available with Model U are:"
                                            + "\nthe Luxury Package, which determines your vehicle to be as cozy as classy to your liking."
                                            + "\nFinally, you can purchase the Amenities package which guarantees you a comfortable experience "
                                            + "in your new undersea vehicle.");
                                    System.out.println();
                                    
                                    String print_out_options = "WITH RESULT AS ( select option_name as options_name,package_id from options_in_packages where package_id=3 ) SELECT * FROM OPTIONS NATURAL JOIN RESULT";
                                    ps5 = con.prepareStatement(print_out_options);
                                    result4 = ps5.executeQuery();
                                    System.out.println("The Luxury package includes: ");
                                    while(result4.next()){
                                        System.out.println("a " + result4.getString("OPTIONS_NAME") + ": " + result4.getString("DESCRIPTION") );
                                    }
                                    System.out.println();
                                    print_out_options = "WITH RESULT AS ( select option_name as options_name,package_id from options_in_packages where package_id=2 ) SELECT * FROM OPTIONS NATURAL JOIN RESULT";
                                    ps5 = con.prepareStatement(print_out_options);
                                    result4 = ps5.executeQuery();
                                    System.out.println("The Amenities package includes: ");
                                    while(result4.next()){
                                        System.out.println("a " + result4.getString("OPTIONS_NAME") + ": " + result4.getString("DESCRIPTION") );
                                    }
                                    
                                    System.out.print("\nwhich package do you wish to purchase? "
                                            + "Type in luxury for the luxury package, or type in amenities for the amenities package."
                                            + "\nThe Amenities package is chosen for you already but you can upgrade it to the luxury package."
                                            + ": ");
                                    String decision = "";
                                    decision = krb.next();
                                    decision = decision.toLowerCase();
                                    
                                    int exit_loop = 0;
                                    String insert_package;
                                    String insert_vehicles;
                                    String package_chosen="";
                                    
                                    while( exit_loop==0 ){
                                        if(decision.equals("luxury")){
                                            exit_loop=1;
                                            wrong_key2=1;
                                            System.out.println("luxury");
                                            package_chosen = decision;
                                        }
                                        else if(decision.equals("amenities")){
                                            exit_loop=1;
                                            wrong_key2=1;
                                            System.out.println("amenities");
                                            package_chosen = decision;
                                        }
                                        else{
                                            System.out.print("Did you mean to type that? type in amenities to exit out of choosing an upgrade in packages.\n"
                                                   
                                                    + "Otherwise type in your preferred package upgrade of luxury: ");
                                            decision = krb.next();
                                            decision = decision.toLowerCase();
                                            if(decision.equals("amenities")){
                                                exit_loop=1;
                                                wrong_key2=1;
                                                package_chosen = "amenities";
                                            }
                                            else{
                                                exit_loop=0;
                                            }
                                        }
                                    }
                                    
                                    String obtain_package_price ="";
                                    pricePack = null;
                                    
                                    if(package_chosen.equals("luxury")){
                                        package_ch =3;
                                        obtain_package_price = "select * from packages where package_id = ? ";
                                        ps5 = con.prepareStatement(obtain_package_price);
                                        ps5.setInt(1, package_ch);
                                        result4=ps5.executeQuery();
                                        while(result4.next()){
                                            pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                        }
                                    }
                                    else if(package_chosen.equals("amenities") || package_ch ==2){
                                        package_ch =2;
                                        obtain_package_price = "select * from packages where package_id = ? ";
                                        ps5 = con.prepareStatement(obtain_package_price);
                                        ps5.setInt(1, package_ch);
                                        result4=ps5.executeQuery();
                                        while(result4.next()){
                                            pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                        }
                                    }
                                    packageSelected = package_chosen;
                                    totalpricepaid = baseprice;
                                    
                                    if(package_ch == 2 ){
                                        //the amenities package was selected for them or they chose it.
                                        //You can choose to add the hybrid option as well.
                                        totalpricepaid = totalpricepaid.add(pricePack);
                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                        System.out.print("We have a hybrid option available for this vehicle. "
                                                + "Do you want to know more about it? ");
                                        String op_decision = krb.next();
                                        op_decision = op_decision.toLowerCase();
                                        int op_d = 0;
                                        while(op_d == 0){
                                            if(op_decision.equals("yes")){
                                                op_d =1;
                                                String choose_hybrid = "SELECT * FROM OPTIONS where options_name = ?";
                                                ps5 = con.prepareStatement(choose_hybrid);
                                                ps5.setString(1, "Hybrid ");
                                                result4 = ps5.executeQuery();
                                                priceofHy=0;
                                                String op_name;
                                                String op_descr;
                                                while(result4.next()){
                                                    System.out.println(result4.getString("OPTIONS_NAME") + "$" +result4.getInt("LIST_PRICE") + " " + result4.getString("DESCRIPTION"));
                                                    priceofHy = result4.getInt("LIST_PRICE");
                                                    op_name = result4.getString("OPTIONS_NAME");
                                                    op_descr = result4.getString("DESCRIPTION");
                                                }
                                                System.out.print("Do you want to purchase this option? ");
                                                String purchase_hy = krb.next();
                                                purchase_hy = purchase_hy.toLowerCase();
                                                int a = 0;
                                                while( a == 0){
                                                    if(purchase_hy.equals("yes")){
                                                        //optionSelected[0]= "Hybrid";
                                                        optionSelected.add("Hybrid");
                                                        a =1 ;
                                                        totalpricepaid = totalpricepaid.add(new BigDecimal(priceofHy));
                                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                                    }
                                                    else if(purchase_hy.equals("no")){
                                                      a =1 ;
                                                      System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                                      
                                                    }
                                                    else{
                                                        a=0;
                                                        System.out.print("We did not get what you were trying to say. Can you enter yes "
                                                                + "if you want to purchase the hybrid option, otherwise say no. ");
                                                        purchase_hy = krb.next();
                                                        purchase_hy = purchase_hy.toLowerCase();
                                                    }
                                                }
                                            }
                                            else if(op_decision.equals("no") || op_decision.equals("exit")){
                                              op_d =1;
                                              
                                            }
                                            else{
                                                op_d=0;
                                                System.out.println("We did not understand what you had typed. If you want to exit, type in exit."
                                                        + "\nIf you want to know more about the hybrid type in yes. ");
                                                op_decision = krb.next();
                                                op_decision = op_decision.toLowerCase();
                                            }
                                        }
                                    }
                                    else{
                                        //they chose a package other than the amenities package.
                                        //totalpricepaid= pricePack + totalpricepaid;
                                        totalpricepaid = totalpricepaid.add(pricePack);
                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                    }
                                    System.out.println();
                                    
                                    //list a summary of the model that the customer is about to purchase.
                                    //ask to confirm if this correct. if they say yes, the records will all be updated.
                                    a.SummaryInfo();
                                   
                                    
                                    if(oldc_num != 0 ){
                                        System.out.println("Card Info: " + oldc_num);
                                    }
                                    else if( oldc_num_used != 0){
                                        System.out.println("Card Info: " + oldc_num_used);
                                    }
                                    System.out.print("Is this correct? Type in confirm to place your order. Otherwise type in no to opt out of ordering: ");
                                    String final_decision = krb.next();
                                    final_decision = final_decision.toLowerCase();
                                    int ab=0;
                                    
                                    while (ab ==0){
                                        if(final_decision.equals("confirm")){
                                            ab =1;
                                            //do all my inserts here.
                                            if(oldc_num == 0 && oldc_num_used != 0 ){
                                                String insert_Custom = "insert into customer_payment  values(?,?,?,?)";
                                                ps5 = con.prepareStatement(insert_Custom);
                                                ps5.setString(1, name);
                                                ps5.setLong(2, oldc_num_used);
                                                ps5.setInt(3, id);
                                                ps5.setString(4, email_addr);
                                                ps5.executeUpdate();
                                            }
                                            //insert vehicle, v_packages, vehicle_options if there are chrome wheels or hybrid options,
                                            //customer_buys_new , v_model, and new_vehicle
                                            insert_vehicles = "insert into vehicle values(?)";
                                            ps5 = con.prepareStatement(insert_vehicles);
                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                            ps5.executeUpdate();

                                            insert_package="insert into v_packages values(?,?)";
                                            ps5 = con.prepareStatement(insert_package);
                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                            ps5.setInt(2, package_ch);
                                            ps5.executeUpdate();
                                            
                                            //insert into vehicle_options
                                            if(priceofHy !=0){
                                                CallableStatement stmt2 = con.prepareCall("{call options_to_have(?,?,?)}");
                                                stmt2.setBigDecimal(1, new BigDecimal(new_vid));
                                                stmt2.setString(2, "Hybrid ");
                                                stmt2.setInt(3, priceofHy);
                                                boolean hadResults2 = stmt2.execute();

                                                while (hadResults2) {
                                                    ResultSet rs = stmt2.getResultSet();

                                                    // process result set

                                                    hadResults2 = stmt2.getMoreResults();
                                                }
                                            }
                                            
                                            String insert_cust_buys_new = "insert into customer_buys_new values(?,?,?,?,?)";
                                            ps5 = con.prepareStatement(insert_cust_buys_new);
                                            ps5.setString(1, name);
                                            ps5.setInt(2, id);
                                            ps5.setBigDecimal(3,new BigDecimal(new_vid));
                                            ps5.setString(4, email_addr);
                                            ps5.setBigDecimal(5,totalpricepaid);
                                            ps5.executeUpdate();

                                            String insert_vmodel = "insert into v_model values(?,?,?)";
                                            ps5 = con.prepareStatement(insert_vmodel);
                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                            ps5.setInt(2, initialModelID);
                                            ps5.setString(3, initialModelName);
                                            ps5.executeUpdate();
                                            
                                            String insert_newvehicle = "insert into new_vehicle values(?,?,?)";
                                            ps5 = con.prepareStatement(insert_newvehicle);
                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                            ps5.setString(2, "Pacific Overlook");
                                            ps5.setString(3, initialModelType);
                                            ps5.executeUpdate();
                                            System.out.println("The order has been submitted.");
                                            System.out.println("Your order will be available for pickup at the Pacific Overlook center.");
                                        }
                                        
                                        else if(final_decision.equals("no")){
                                            System.out.println("No new car was purchased.");
                                            ab =1;
                                            //exit out of here
                                        }
                                        else{
                                            System.out.print("We did not understand what you had typed. Please type in confirm to place your order."
                                                    + "\nOtherwise type in no to opt of ordering: ");
                                            final_decision = krb.next();
                                            final_decision = final_decision.toLowerCase();
                                        }
                                    }
                                                 
                                }
                                else if(model_selected.equals("s")){
                                   String car_query = "select * from model where model_type=?";
                                    ps5 = con.prepareStatement(car_query);
                                    ps5.setString(1,"Model S");
                                    result2 = ps5.executeQuery();
                                    
                                    while(result2.next()){
                                        System.out.println();
                                        System.out.println("Model ID: " + result2.getInt("m_id")+"\nModel name: "+ result2.getString("modelname")+
                                        "\nYear: " + result2.getInt("year") + "\nModel Type: " + result2.getString("model_type") +
                                        "\nPrice: $" + result2.getBigDecimal("list_price"));
                                    }
                                    if(!result2.next()){
                                        System.out.println("That is all of the Model S vehicles being sold right now.");
                                    } 
                                    
                                    //Next have them enter the name of the vehicle and year they want to get.
                                    System.out.println("Please enter the model id of the vehicle you wish to purchase: ");
                                    System.out.print("Model ID: ");
                                    int car_wish_to_own  =0;
                                    while (!krb.hasNextInt()) { 
                                        System.out.println("only numbers! Type in the model id. "); 
                                        System.out.print("Enter your model id number: ");
                                        krb.next(); // discard 
                                    }
                                    car_wish_to_own = krb.nextInt();
                                    krb.nextLine();
                                    
                                    int z = 0;
                                    int t = 0;
                                    while ( z ==0){
                                        String specific_model = "select * from model where m_id = ? and model_type = ?";
                                        ps5 = con.prepareStatement(specific_model);
                                        ps5.setInt(1,car_wish_to_own);
                                        ps5.setString(2, "Model S");

                                        result4 = ps5.executeQuery();
                                        while(result4.next()){
                                            z = 1;t = 1;
                                        }

                                        while( t == 0 && !result4.next()){
                                            z=0;
                                            System.out.print("We don't recognize the id that you wrote. If you wish to exit, please type in exit. \n"
                                                    + "Otherwise type in any other character to continue: ");
                                            String dec = krb.next();
                                            dec =dec.toLowerCase();
                                            if(dec.equals("exit")){
                                                return;
                                            }
                                            else{
                                                System.out.print("Please enter a model id of the vehicle you wish to purchase: ");
                                                while (!krb.hasNextInt()) { 
                                                    System.out.println("only numbers! Type in the model id. "); 
                                                    System.out.print("Enter your model id number: ");
                                                    krb.next(); // discard 
                                                }
                                                car_wish_to_own = krb.nextInt();
                                                specific_model = "select * from model where m_id = ? and model_type = ?";
                                                ps5 = con.prepareStatement(specific_model);
                                                ps5.setInt(1,car_wish_to_own);
                                                ps5.setString(2, "Model S");
                                            }
                                            result4 = ps5.executeQuery();
                                        }
                                    }
                                    
                                    String specific_model = "select * from model where m_id = ? and model_type = ? ";
                                    ps5 = con.prepareStatement(specific_model);
                                    ps5.setInt(1,car_wish_to_own);
                                    ps5.setString(2, "Model S");
                                    
                                    result4 = ps5.executeQuery();
                                    
                                    while(result4.next()){
                                        System.out.println("The base price of this vehicle" + " the "+
                                                result4.getString("modelname")+ " is $"+result4.getBigDecimal("list_price"));
                                        baseprice = result4.getBigDecimal("list_price");
                                        initialPrice = baseprice;
                                        initialModelID= result4.getInt("M_ID");
                                        initialModelName =result4.getString("modelname");
                                        initialModelYear = result4.getInt("YEAR");
                                        initialModelType = result4.getString("MODEL_TYPE");
                                    }
                                    
                                    System.out.println();
                                    
                                    System.out.println("Do you want to purchase a package? The packages available with Model U are:"
                                            + "\nthe Luxury Package, which determines your vehicle to be as cozy as classy to your liking."
                                            + "\nFinally, you can purchase the Amenities package which guarantees you a comfortable experience "
                                            + "in your new undersea vehicle.");
                                    System.out.println();
                                    
                                    String print_out_options = "WITH RESULT AS ( select option_name as options_name,package_id from options_in_packages where package_id=3 ) SELECT * FROM OPTIONS NATURAL JOIN RESULT";
                                    ps5 = con.prepareStatement(print_out_options);
                                    result4 = ps5.executeQuery();
                                    System.out.println("The Luxury package includes: ");
                                    while(result4.next()){
                                        System.out.println("a " + result4.getString("OPTIONS_NAME") + ": " + result4.getString("DESCRIPTION") );
                                    }
                                    System.out.println();
                                    print_out_options = "WITH RESULT AS ( select option_name as options_name,package_id from options_in_packages where package_id=2 ) SELECT * FROM OPTIONS NATURAL JOIN RESULT";
                                    ps5 = con.prepareStatement(print_out_options);
                                    result4 = ps5.executeQuery();
                                    System.out.println("The Amenities package includes: ");
                                    while(result4.next()){
                                        System.out.println("a " + result4.getString("OPTIONS_NAME") + ": " + result4.getString("DESCRIPTION") );
                                    }
                                    
                                    System.out.print("\nwhich package do you wish to purchase? "
                                            + "Type in luxury for the luxury package, or type in amenities for the amenities package."
                                            + "\nThe Amenities package is chosen for you already but you can upgrade it to the luxury package."
                                            + ": ");
                                    String decision = "";
                                    decision = krb.next();
                                    decision = decision.toLowerCase();
                                    
                                    int exit_loop = 0;
                                    String insert_package;
                                    String insert_vehicles;
                                    String package_chosen="";
                                    
                                    while( exit_loop==0 ){
                                        if(decision.equals("luxury")){
                                            exit_loop=1;
                                            wrong_key2=1;
                                            System.out.println("luxury");
                                            package_chosen = decision;
                                        }
                                        else if(decision.equals("amenities")){
                                            exit_loop=1;
                                            wrong_key2=1;
                                            System.out.println("amenities");
                                            package_chosen = decision;
                                        }
                                        else{
                                            System.out.print("Did you mean to type that? type in amenities to exit out of choosing an upgrade in packages.\n"
                                                   
                                                    + "Otherwise type in your preferred package upgrade of luxury: ");
                                            decision = krb.next();
                                            decision = decision.toLowerCase();
                                            if(decision.equals("amenities")){
                                                exit_loop=1;
                                                wrong_key2=1;
                                                package_chosen = "amenities";
                                            }
                                            else{
                                                exit_loop=0;
                                            }
                                        }
                                    }
                                    
                                    String obtain_package_price ="";
                                    pricePack = null;
                                    
                                    if(package_chosen.equals("luxury")){
                                        package_ch =3;
                                        obtain_package_price = "select * from packages where package_id = ? ";
                                        ps5 = con.prepareStatement(obtain_package_price);
                                        ps5.setInt(1, package_ch);
                                        result4=ps5.executeQuery();
                                        while(result4.next()){
                                            pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                        }
                                    }
                                    else if(package_chosen.equals("amenities") || package_ch ==2){
                                        package_ch =2;
                                        obtain_package_price = "select * from packages where package_id = ? ";
                                        ps5 = con.prepareStatement(obtain_package_price);
                                        ps5.setInt(1, package_ch);
                                        result4=ps5.executeQuery();
                                        while(result4.next()){
                                            pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                        }
                                    }
                                    packageSelected = package_chosen;
                                    totalpricepaid = baseprice;
                                    
                                    if(package_ch == 2 ){
                                        //the amenities package was selected for them or they chose it.
                                        //You can choose to add the hybrid option as well.
                                        totalpricepaid = totalpricepaid.add(pricePack);
                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                        System.out.print("We have a hybrid option available for this vehicle. "
                                                + "Do you want to know more about it? ");
                                        String op_decision = krb.next();
                                        op_decision = op_decision.toLowerCase();
                                        int op_d = 0;
                                        while(op_d == 0){
                                            if(op_decision.equals("yes")){
                                                op_d =1;
                                                String choose_hybrid = "SELECT * FROM OPTIONS where options_name = ?";
                                                ps5 = con.prepareStatement(choose_hybrid);
                                                ps5.setString(1, "Hybrid ");
                                                result4 = ps5.executeQuery();
                                                priceofHy=0;
                                                String op_name;
                                                String op_descr;
                                                while(result4.next()){
                                                    System.out.println(result4.getString("OPTIONS_NAME") +"$"+ result4.getInt("LIST_PRICE") + " " + result4.getString("DESCRIPTION"));
                                                    priceofHy = result4.getInt("LIST_PRICE");
                                                    op_name = result4.getString("OPTIONS_NAME");
                                                    op_descr = result4.getString("DESCRIPTION");
                                                }
                                                System.out.print("Do you want to purchase this option? ");
                                                String purchase_hy = krb.next();
                                                purchase_hy = purchase_hy.toLowerCase();
                                                int a = 0;
                                                while( a == 0){
                                                    if(purchase_hy.equals("yes")){
                                                        //optionSelected[0]= "Hybrid";
                                                        optionSelected.add("Hybrid");
                                                        a =1 ;
                                                        totalpricepaid = totalpricepaid.add(new BigDecimal(priceofHy));
                                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                                    }
                                                    else if(purchase_hy.equals("no")){
                                                      a =1 ;
                                                      System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                                      
                                                    }
                                                    else{
                                                        a=0;
                                                        System.out.print("We did not get what you were trying to say. Can you enter yes "
                                                                + "if you want to purchase the hybrid option, otherwise say no. ");
                                                        purchase_hy = krb.next();
                                                        purchase_hy = purchase_hy.toLowerCase();
                                                    }
                                                }
                                            }
                                            else if(op_decision.equals("no") || op_decision.equals("exit")){
                                              op_d =1;
                                              
                                            }
                                            else{
                                                op_d=0;
                                                System.out.println("We did not understand what you had typed. If you want to exit, type in exit."
                                                        + "\nIf you want to know more about the hybrid type in yes. ");
                                                op_decision = krb.next();
                                                op_decision = op_decision.toLowerCase();
                                            }
                                        }
                                    }
                                    
                                    else{
                                        //they chose a package other than the amenities package.
                                        //totalpricepaid= pricePack + totalpricepaid;
                                        totalpricepaid = totalpricepaid.add(pricePack);
                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                    }
                                    System.out.println();
                                    
                                    //list a summary of the model that the customer is about to purchase.
                                    //ask to confirm if this correct. if they say yes, the records will all be updated.
                                    a.SummaryInfo();
                                    
                                    if(oldc_num != 0 ){
                                        System.out.println("Card Info: " + oldc_num);
                                    }
                                    else if( oldc_num_used != 0){
                                        System.out.println("Card Info: " + oldc_num_used);
                                    }
                                    
                                    System.out.print("Is this correct? Type in confirm to place your order. Otherwise type in no to opt out of ordering. ");
                                    String final_decision = krb.next();
                                    final_decision = final_decision.toLowerCase();
                                    int ab=0;
                                    
                                    while (ab ==0){
                                        if(final_decision.equals("confirm")){
                                            ab =1;
                                            //do all my inserts here.
                                            if(oldc_num == 0 && oldc_num_used != 0 ){
                                                String insert_Custom = "insert into customer_payment  values(?,?,?,?)";
                                                ps5 = con.prepareStatement(insert_Custom);
                                                ps5.setString(1, name);
                                                ps5.setLong(2, oldc_num_used);
                                                ps5.setInt(3, id);
                                                ps5.setString(4, email_addr);
                                                ps5.executeUpdate();
                                            }
                                            //insert vehicle, v_packages, vehicle_options if there are chrome wheels or hybrid options,
                                            //customer_buys_new , v_model, and new_vehicle
                                            insert_vehicles = "insert into vehicle values(?)";
                                            ps5 = con.prepareStatement(insert_vehicles);
                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                            ps5.executeUpdate();

                                            insert_package="insert into v_packages values(?,?)";
                                            ps5 = con.prepareStatement(insert_package);
                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                            ps5.setInt(2, package_ch);
                                            ps5.executeUpdate();
                                            
                                            //insert into vehicle_options
                                            if(priceofHy !=0){
                                                CallableStatement stmt2 = con.prepareCall("{call options_to_have(?,?,?)}");
                                                stmt2.setBigDecimal(1, new BigDecimal(new_vid));
                                                stmt2.setString(2, "Hybrid ");
                                                stmt2.setInt(3, priceofHy);
                                                boolean hadResults2 = stmt2.execute();

                                                while (hadResults2) {
                                                    ResultSet rs = stmt2.getResultSet();

                                                    // process result set

                                                    hadResults2 = stmt2.getMoreResults();
                                                }
                                            }
                                            
                                            String insert_cust_buys_new = "insert into customer_buys_new values(?,?,?,?,?)";
                                            ps5 = con.prepareStatement(insert_cust_buys_new);
                                            ps5.setString(1, name);
                                            ps5.setInt(2, id);
                                            ps5.setBigDecimal(3,new BigDecimal(new_vid));
                                            ps5.setString(4, email_addr);
                                            ps5.setBigDecimal(5,totalpricepaid);
                                            ps5.executeUpdate();

                                            String insert_vmodel = "insert into v_model values(?,?,?)";
                                            ps5 = con.prepareStatement(insert_vmodel);
                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                            ps5.setInt(2, initialModelID);
                                            ps5.setString(3, initialModelName);
                                            ps5.executeUpdate();
                                            
                                            String insert_newvehicle = "insert into new_vehicle values(?,?,?)";
                                            ps5 = con.prepareStatement(insert_newvehicle);
                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                            ps5.setString(2, "Mars Bars Center");
                                            ps5.setString(3, initialModelType);
                                            ps5.executeUpdate();
                                            System.out.println("The order has been submitted.");
                                            System.out.println("Your order will be available for pickup at the Mars Bars Center center.");
                                        }
                                        
                                        else if(final_decision.equals("no")){
                                            System.out.println("No new car was purchased.");
                                            ab =1;
                                            //exit out of here
                                        }
                                        else{
                                            System.out.print("We did not understand what you had typed. Please type in confirm to place your order."
                                                    + "\nOtherwise type in no to opt of ordering. ");
                                            final_decision = krb.next();
                                            final_decision = final_decision.toLowerCase();
                                        }

                                    }
                                }
                                else if(model_selected.equals("k")){
                                    String car_query = "select * from model where model_type=?";
                                    ps5 = con.prepareStatement(car_query);
                                    ps5.setString(1,"Model K");
                                    result2 = ps5.executeQuery();

                                    while(result2.next()){
                                        System.out.println();
                                        System.out.println("Model ID: " + result2.getInt("m_id")+"\nModel name: "+ result2.getString("modelname")+
                                        "\nYear: " + result2.getInt("year") + "\nModel Type: " + result2.getString("model_type") +
                                        "\nPrice: $" + result2.getBigDecimal("list_price"));
                                    }
                                    if(!result2.next()){
                                        System.out.println("That is all of the Model K vehicles being sold right now.");
                                    }
                                    
                                    //Next have them enter the name of the vehicle and year they want to get.
                                    System.out.println("Please enter the model id of the vehicle you wish to purchase: ");
                                    System.out.print("Model ID: ");
                                    int car_wish_to_own  =0;
                                    while (!krb.hasNextInt()) { 
                                        System.out.println("only numbers! Type in the model id. "); 
                                        System.out.print("Enter your model id number: ");
                                        krb.next(); // discard 
                                    }
                                    car_wish_to_own = krb.nextInt();
                                    krb.nextLine();
                                    int z = 0;
                                    int t = 0;
                                    while ( z ==0){
                                        String specific_model = "select * from model where m_id = ? and model_type = ?";
                                        ps5 = con.prepareStatement(specific_model);
                                        ps5.setInt(1,car_wish_to_own);
                                        ps5.setString(2, "Model K");

                                        result4 = ps5.executeQuery();
                                        while(result4.next()){
                                            z = 1;t = 1;
                                        }

                                        while( t == 0 && !result4.next()){
                                            z=0;
                                            System.out.print("We don't recognize the id that you wrote. If you wish to exit, please type in exit. \n"
                                                    + "Otherwise type in any other character to continue: ");
                                            String dec = krb.next();
                                            dec =dec.toLowerCase();
                                            if(dec.equals("exit")){
                                                return;
                                            }
                                            else{
                                                System.out.print("Please enter a model id of the vehicle you wish to purchase: ");
                                                while (!krb.hasNextInt()) { 
                                                    System.out.println("only numbers! Type in the model id. "); 
                                                    System.out.print("Enter your model id number: ");
                                                    krb.next(); // discard 
                                                }
                                                car_wish_to_own = krb.nextInt();
                                                specific_model = "select * from model where m_id = ? and model_type = ?";
                                                ps5 = con.prepareStatement(specific_model);
                                                ps5.setInt(1,car_wish_to_own);
                                                ps5.setString(2, "Model K");
                                            }
                                            result4 = ps5.executeQuery();
                                        }
                                    }
                                    
                                    
                                    String specific_model = "select * from model where m_id = ? and model_type = ? ";
                                    ps5 = con.prepareStatement(specific_model);
                                    ps5.setInt(1,car_wish_to_own);
                                    ps5.setString(2, "Model K");
                                    
                                    result4 = ps5.executeQuery();
                                    while(result4.next()){
                                        System.out.println("The base price of this vehicle" + " the "+
                                                result4.getString("modelname")+ " is $"+result4.getBigDecimal("list_price"));
                                        baseprice = result4.getBigDecimal("list_price");
                                        initialPrice = baseprice;
                                        initialModelID= result4.getInt("M_ID");
                                        initialModelName =result4.getString("modelname");
                                        initialModelYear = result4.getInt("YEAR");
                                        initialModelType = result4.getString("MODEL_TYPE");
                                        
                                    }
                                    
                                    System.out.println();
                                    
                                    System.out.println("Do you want to purchase a package? The packages available with Model K are:"
                                            + "\nthe Superman Package, which outfits your vehicle as a large truck able to navigate "
                                            + "the roads of our native planet Earth. "
                                            + "\nthe Luxury Package, which determines your vehicle to be as cozy as classy to your liking."
                                            + "\nFinally, you can purchase the Amenities package which guarantees you a comfortable experience "
                                            + "in your new car.");
                                    System.out.println();
                                    String print_out_options = "WITH RESULT AS ( select option_name as options_name,package_id from options_in_packages where package_id=1 ) SELECT * FROM OPTIONS NATURAL JOIN RESULT";
                                    ps5 = con.prepareStatement(print_out_options);
                                    result4 = ps5.executeQuery();
                                    System.out.println("The Superman package includes: ");
                                    while(result4.next()){
                                        System.out.println("a " + result4.getString("OPTIONS_NAME") + ": " + result4.getString("DESCRIPTION") );
                                    }
                                    System.out.println();
                                    print_out_options = "WITH RESULT AS ( select option_name as options_name,package_id from options_in_packages where package_id=3 ) SELECT * FROM OPTIONS NATURAL JOIN RESULT";
                                    ps5 = con.prepareStatement(print_out_options);
                                    result4 = ps5.executeQuery();
                                    System.out.println("The Luxury package includes: ");
                                    while(result4.next()){
                                        System.out.println("a " + result4.getString("OPTIONS_NAME") + ": " + result4.getString("DESCRIPTION") );
                                    }
                                    System.out.println();
                                    print_out_options = "WITH RESULT AS ( select option_name as options_name,package_id from options_in_packages where package_id=2 ) SELECT * FROM OPTIONS NATURAL JOIN RESULT";
                                    ps5 = con.prepareStatement(print_out_options);
                                    result4 = ps5.executeQuery();
                                    System.out.println("The Amenities package includes: ");
                                    while(result4.next()){
                                        System.out.println("a " + result4.getString("OPTIONS_NAME") + ": " + result4.getString("DESCRIPTION") );
                                    }
                                    
                                    System.out.print("\nwhich package do you wish to purchase? Type in superman for the superman package"
                                            + ", type in luxury for the luxury package, or type in amenities for the amenities package."
                                            + "\nThe Amenities package is chosen for you already but you can upgrade it to the luxury or superman package."
                                            + ": ");
                                    String decision = "";
                                    decision = krb.next();
                                    decision = decision.toLowerCase();
                                    int exit_loop = 0;
                                    String insert_package;
                                    String insert_vehicles;
                                    String package_chosen="";
                                    while( exit_loop==0 ){
                                        if(decision.equals("superman")){
                                            exit_loop=1;
                                            wrong_key2=1;
                                            System.out.println("superman");
                                            package_chosen = decision;
                                        }
                                        else if(decision.equals("luxury")){
                                            exit_loop=1;
                                            wrong_key2=1;
                                            System.out.println("luxury");
                                            package_chosen = decision;
                                        }
                                        else if(decision.equals("amenities")){
                                            exit_loop=1;
                                            wrong_key2=1;
                                            System.out.println("amenities");
                                            package_chosen = decision;
                                        }

                                        else{
                                            System.out.print("Did you mean to type that? type in amenities to exit out of choosing an upgrade in packages.\n"
                                                   
                                                    + "Otherwise type in your preferred package upgrade of superman or luxury: ");
                                            decision = krb.next();
                                            decision = decision.toLowerCase();
                                            if(decision.equals("amenities")){
                                                exit_loop=1;
                                                wrong_key2=1;
                                                package_chosen = "amenities";
                                            }
                                            else{
                                                exit_loop=0;
                                            }
                                        }
                                    }
                                    
                                    String obtain_package_price ="";
                                    pricePack = null;
                                    if(package_chosen.equals("superman")){
                                        package_ch =1;
                                        obtain_package_price = "select * from packages where package_id = ? ";
                                        ps5 = con.prepareStatement(obtain_package_price);
                                        ps5.setInt(1, package_ch);
                                        result4=ps5.executeQuery();
                                        //pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                        while(result4.next()){
                                            pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                        }
                                    }
                                    else if(package_chosen.equals("luxury")){
                                        package_ch =3;
                                        obtain_package_price = "select * from packages where package_id = ? ";
                                        ps5 = con.prepareStatement(obtain_package_price);
                                        ps5.setInt(1, package_ch);
                                        result4=ps5.executeQuery();
                                        while(result4.next()){
                                            pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                        }
                                    }
                                    else if(package_chosen.equals("amenities") || package_ch ==2){
                                        package_ch =2;
                                        obtain_package_price = "select * from packages where package_id = ? ";
                                        ps5 = con.prepareStatement(obtain_package_price);
                                        ps5.setInt(1, package_ch);
                                        result4=ps5.executeQuery();
                                        while(result4.next()){
                                            pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                        }
                                    }
                                    packageSelected = package_chosen;
                                    totalpricepaid = baseprice;
                                    if(package_ch == 2 ){
                                        //the amenities package was selected for them or they chose it.
                                        //You can choose to add the hybrid as well as bedazzling your wheels with 20'' chrome wheels.
                                        //add the price of the package amenities
                                        totalpricepaid = totalpricepaid.add(pricePack);
                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                        System.out.print("We have a hybrid option available for this vehicle. "
                                                + "Do you want to know more about it? ");
                                        String op_decision = krb.next();
                                        op_decision = op_decision.toLowerCase();
                                        int op_d = 0;
                                        
                                        while(op_d == 0){
                                            if(op_decision.equals("yes")){
                                                op_d =1;
                                                String choose_hybrid = "SELECT * FROM OPTIONS where options_name = ?";
                                                ps5 = con.prepareStatement(choose_hybrid);
                                                ps5.setString(1, "Hybrid ");
                                                result4 = ps5.executeQuery();
                                                priceofHy=0;
                                                String op_name;
                                                String op_descr;
                                                while(result4.next()){
                                                    System.out.println(result4.getString("OPTIONS_NAME") + "$"+ result4.getInt("LIST_PRICE") + " " + result4.getString("DESCRIPTION"));
                                                    priceofHy = result4.getInt("LIST_PRICE");
                                                    op_name = result4.getString("OPTIONS_NAME");
                                                    op_descr = result4.getString("DESCRIPTION");
                                                }
                                                System.out.print("Do you want to purchase this option? ");
                                                String purchase_hy = krb.next();
                                                purchase_hy = purchase_hy.toLowerCase();
                                                int a = 0;
                                                while( a == 0){
                                                    if(purchase_hy.equals("yes")){
                                                        //optionSelected[0]= "Hybrid";
                                                        optionSelected.add("Hybrid");
                                                        a =1 ;
                                                        //add to v_options
                                                        //get price of option and to total price paid
                                                        totalpricepaid = totalpricepaid.add(new BigDecimal(priceofHy));
                                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                                    }
                                                    else if(purchase_hy.equals("no")){
                                                        a =1 ;
                                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);

                                                    }
                                                    else{
                                                        a=0;
                                                        System.out.print("We did not get what you were trying to say. Can you enter yes "
                                                                + "if you want to purchase the hybrid option, otherwise say no. ");
                                                        purchase_hy = krb.next();
                                                        purchase_hy = purchase_hy.toLowerCase();
                                                    }
                                                }
                                            }
                                            
                                            else if(op_decision.equals("no") || op_decision.equals("exit")){
                                                op_d =1;

                                            }
                                            else{
                                                op_d=0;
                                                System.out.println("We did not understand what you had typed. If you want to exit, type in exit."
                                                        + "\nIf you want to know more about the hybrid type in yes. ");
                                                op_decision = krb.next();
                                                op_decision = op_decision.toLowerCase();
                                            }
                                        }
                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                    }
                                    
                                    else{
                                        //they chose a package other than the amenities package.
                                        //totalpricepaid= pricePack + totalpricepaid;
                                        totalpricepaid = totalpricepaid.add(pricePack);
                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                    }
                                    System.out.println();
                                    //list a summary of the model that the customer is about to purchase.
                                    //ask to confirm if this correct. if they say yes, the records will all be updated.
                                    a.SummaryInfo();
                                    
                                    if(oldc_num != 0 ){
                                        System.out.println("Card Info: " + oldc_num);
                                    }
                                    else if( oldc_num_used != 0){
                                        System.out.println("Card Info: " + oldc_num_used);
                                    }
                                    
                                    System.out.print("Is this correct? Type in confirm to place your order. Otherwise type in no to opt out of ordering: ");
                                    String final_decision = krb.next();
                                    final_decision = final_decision.toLowerCase();
                                    int ab=0;
                                    while (ab ==0){
                                        if(final_decision.equals("confirm")){
                                            ab =1;
                                            //do all my inserts here.
                                            if(oldc_num == 0 && oldc_num_used != 0 ){
                                                String insert_Custom = "insert into customer_payment  values(?,?,?,?)";
                                                ps5 = con.prepareStatement(insert_Custom);
                                                ps5.setString(1, name);
                                                ps5.setLong(2, oldc_num_used);
                                                ps5.setInt(3, id);
                                                ps5.setString(4, email_addr);
                                                ps5.executeUpdate();
                                            }
                                            //insert vehicle, v_packages, vehicle_options if there are chrome wheels or hybrid options,
                                            //customer_buys_new , v_model, and new_vehicle
                                            insert_vehicles = "insert into vehicle values(?)";
                                            ps5 = con.prepareStatement(insert_vehicles);
                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                            ps5.executeUpdate();

                                            insert_package="insert into v_packages values(?,?)";
                                            ps5 = con.prepareStatement(insert_package);
                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                            ps5.setInt(2, package_ch);
                                            ps5.executeUpdate();

                                            if(priceofHy !=0){
                                                CallableStatement stmt2 = con.prepareCall("{call options_to_have(?,?,?)}");
                                                stmt2.setBigDecimal(1, new BigDecimal(new_vid));
                                                stmt2.setString(2, "Hybrid ");
                                                stmt2.setInt(3, priceofHy);
                                                boolean hadResults2 = stmt2.execute();

                                                while (hadResults2) {
                                                    ResultSet rs = stmt2.getResultSet();

                                                    // process result set

                                                    hadResults2 = stmt2.getMoreResults();
                                                }
                                            }
                                            String insert_cust_buys_new = "insert into customer_buys_new values(?,?,?,?,?)";
                                            ps5 = con.prepareStatement(insert_cust_buys_new);
                                            ps5.setString(1, name);
                                            ps5.setInt(2, id);
                                            ps5.setBigDecimal(3,new BigDecimal(new_vid));
                                            ps5.setString(4, email_addr);
                                            ps5.setBigDecimal(5,totalpricepaid);
                                            ps5.executeUpdate();

                                            String insert_vmodel = "insert into v_model values(?,?,?)";
                                            ps5 = con.prepareStatement(insert_vmodel);
                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                            ps5.setInt(2, initialModelID);
                                            ps5.setString(3, initialModelName);
                                            ps5.executeUpdate();
                                            
                                            krb.nextLine();
                                            System.out.print("What Alset service center do you want your vehicle to be scheduled for pickup? \nType in pacific or pacific overlook for"
                                                    + " Pacific Overlook or southside bethlehem or southside for Southside Bethlehem: ");
                                            String what_center =krb.nextLine();
                                            while (!what_center.matches("[a-zA-Z_\\- ]+")
                                                    ||what_center.isEmpty()) {
                                                System.out.println("\nInvalid. Remember to type in letters only.");
                                                System.out.print("Enter the Alset service center you want your vehicle to be scheduled for pickup? \nType in pacific or pacific overlook for"
                                                    + "Pacific Overlook. Type in southside bethlehem or southside for Southside Bethlehem: ");
                                                what_center = krb.nextLine();
                                            }
                                            what_center = what_center.toLowerCase();
                                            int y = 0 ; 
                                            while ( y == 0){
                                                
                                                if(what_center.equals("southside") || what_center.equals("southside bethlehem")){
                                                    y = 1;
                                                    String insert_newvehicle = "insert into new_vehicle values(?,?,?)";
                                                    ps5 = con.prepareStatement(insert_newvehicle);
                                                    ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                                    ps5.setString(2, "Southside Bethlehem");
                                                    ps5.setString(3, initialModelType);
                                                    ps5.executeUpdate();
                                                    
                                                    System.out.println("The order has been submitted.");
                                                    System.out.println("Your order will be available for pickup at the Southside Bethlehem center.");
                                                }
                                                else if(what_center.equals("pacific") || what_center.equals("pacific overlook")){
                                                    y=1;
                                                    String insert_newvehicle = "insert into new_vehicle values(?,?,?)";
                                                    ps5 = con.prepareStatement(insert_newvehicle);
                                                    ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                                    ps5.setString(2, "Pacific Overlook");
                                                    ps5.setString(3, initialModelType);
                                                    ps5.executeUpdate();
                                                    System.out.println("The order has been submitted.");
                                                    System.out.println("Your order will be available for pickup at the Pacific Overlook center.");
                                                }
                                                else{
                                                    y=0;
                                                    System.out.print("We did not recognize your entry. \nEnter the Alset service center you want your vehicle to be scheduled for pickup? \nType in pacific or pacific overlook for"
                                                    + "Pacific Overlook. Type in southside bethlehem or southside for Southside Bethlehem: ");
                                                    what_center = krb.nextLine();
                                                    what_center=what_center.toLowerCase();
                                                }
                                            }
                                            
                                        }
                                        else if(final_decision.equals("no")){
                                            System.out.println("No new car was purchased.");
                                            ab =1;
                                            //exit out of here
                                        }
                                        else{
                                            System.out.print("We did not understand what you had typed. Please type in confirm to place your order."
                                                    + "\nOtherwise type in no to opt of ordering a new car. ");
                                            final_decision = krb.next();
                                            final_decision = final_decision.toLowerCase();
                                        }
                                    }
                                    
                                }
                                else{
                                    wrong_key2 =0;
                                    System.out.print("Did you mean to type that? type in exit to exit out of purchasing a new car. otherwise type in the first letter to the package you prefer."
                                            + "\nSelect M for Model M, select U for Model U, select S for Model S, select K for Model K: ");
                                    //String s = "";
                                    model_selected = krb.next();
                                    model_selected = model_selected.toLowerCase();
                                    if(model_selected.equals("exit")){
                                        wrong_key2 =1;
                                    }

                                }
                            }
                        }
                        else if(option.equals("no")){
                            System.out.println("No new car was purchased.");
                            wrong_key = 1;
                        }
                        else{
                            wrong_key =0;
                            System.out.print("Did you mean to type that? Type in exit or no to exit. otherwise type yes to continue to choose a vehicle: ");
                            //String s = "";
                            option = krb.next();
                            option = option.toLowerCase();
                            if(option.equals("exit")){
                                System.out.println("No new vehicle was chosen.");
                                wrong_key =1;
                            }
                        }
                    }
                    
                    System.out.print("\nDo you want to purchase an available used car? Type in yes to continue. Otherwise type in no to exit: ");
                    option = krb.next();
                    option=option.toLowerCase();
                    wrong_key=0;
                    while(wrong_key == 0 ){
                        if(option.equals("yes")){
                            wrong_key =1;
                            // have the user input the model type he is looking for.
                            System.out.println("What model type are you looking for? We can show what models you might be interested in purchasing. ");
                            System.out.println("The model types that we have available are:\n"
                                    + "Model K, the “Kart”, a small electric vehicle for use on the surface of the earth.\n"
                                    + "Model U, the undersea vehicle, used as a personal submarine.\n"
                                    + "Model M, the moon vehicle, used to ride on the surface of the moon.\n"
                                    + "Model S, the spacecraft, used for space travel. This vehicle is capable of landing on most planets and moons,"
                                    + " and, after refueling, can re-launch into space.");
                            System.out.print("Select M for Model M, select U for Model U, select S for Model S, select K for Model K: ");
                            String model_selected = krb.next();
                            model_selected = model_selected.toLowerCase();
                            BigDecimal new_price = null;
                            int wrong_key2=0;
                            while( wrong_key2 ==0){
                                if(model_selected.equals("m")){
                                    wrong_key2 = 1;
                                    
                                    String cars_used_available = "SELECT v_id,new_price FROM S_L_USED WHERE NOT EXISTS( SELECT v_id FROM customer_buys_used WHERE S_L_USED.v_id = customer_buys_used.v_id ) "
                                            + "AND MODEL_TYPE = ? ";
                                    
                                    ps5 =con.prepareStatement(cars_used_available);
                                    ps5.setString(1, "Model M");
                                    result2 = ps5.executeQuery();
                                   
                                    System.out.println("The available models are: ");
                                    int count2 = 0;
                                    while(result2.next() && count2 == 0){
                                        
                                        System.out.println();
       
                                        BigDecimal a = result2.getBigDecimal("v_id");
                                        new_price = result2.getBigDecimal("NEW_PRICE");

                                        String find_vehicle_details = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id";
                                        ps4 = con.prepareStatement(find_vehicle_details);
                                        ps4.setBigDecimal(1, a);
                                        result3 = ps4.executeQuery();
                                        while(result3.next()){
                                            System.out.println("Vehicle ID: " + a + "\tModel ID: "+ result3.getInt("m_id") + "\tModel Name: "+ result3.getString("model_name") +"\tModel Year: "
                                                    +result3.getInt("year") +"\tModel Type: "+ result3.getString("model_type") +"\nOriginal Price: $"+ result3.getBigDecimal("list_price") + 
                                                    "\tNew Price: $"+ new_price);
                                            
                                        }
                                        String package_av = "select * from v_packages_used natural join packages where v_id = ? ";
                                        ps4 = con.prepareStatement(package_av);
                                        ps4.setBigDecimal(1, a);
                                        result3 = ps4.executeQuery();
                                        
                                        while(result3.next()){
                                            System.out.println("Package selected for vehicle " +a + ": " + result3.getString("package_name"));
                                        }
                                    } 
                                    if(!result2.next() && count2 == 0){
                                        count2 =1;
                                        System.out.println("There are no more available used vehicles. Please call our office location during our regular business hours if you have any questions.");
                                        //return;
                                    }
                                    System.out.println("Please enter the vehicle id of the vehicle you wish to purchase: ");
                                    System.out.print("Vehicle ID: ");
                                    BigDecimal car_wish_to_own;
                                    while (!krb.hasNextBigDecimal()) { 
                                        System.out.println("only numbers! Type in the vehicle id. "); 
                                        System.out.print("Enter the vehicle id of the vehicle you wish to purchase: ");
                                        krb.next(); // discard 
                                    }
                                    car_wish_to_own = krb.nextBigDecimal();
                                    krb.nextLine();
                                    int z = 0;
                                    int t = 0;
                                    while ( z ==0){
                                        String specific_model = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id ";
                                        ps5 = con.prepareStatement(specific_model);
                                        ps5.setBigDecimal(1,car_wish_to_own);
                                        //ps5.setString(2, "Model M");
                                        
                                        result4 = ps5.executeQuery();
                                        while(result4.next()){
                                            z = 1;t = 1;
                                            System.out.println("\nVehicle ID: " + car_wish_to_own + "\tModel ID: "+ result4.getInt("m_id") + "\tModel Name: "+ result4.getString("model_name") +"\tModel Year: "
                                                    +result4.getInt("year") +"\tModel Type: "+ result4.getString("model_type") +"\nOriginal Price: $"+ result4.getBigDecimal("list_price") + 
                                                    "\tNew Price: $"+ new_price);
                                            if(oldc_num != 0 ){
                                                System.out.println("Card Info: " + oldc_num);
                                            }
                                            else if( oldc_num_used != 0){
                                                System.out.println("Card Info: " + oldc_num_used);
                                            }
                                            System.out.print("Is this the vehicle you want to purchase? Type in confirm to purchase this vehicle: ");
                                            
                                            String final_decision = krb.next();
                                            final_decision = final_decision.toLowerCase();
                                            int ab=0;
                                            while (ab ==0){
                                                if(final_decision.equals("confirm")){
                                                    ab=1;
                                                    if(oldc_num != 0 && oldc_num_used == 0 ){
                                                        String insert_Custom = "insert into customer_payment_used  values(?,?,?,?)";
                                                        ps6 = con.prepareStatement(insert_Custom);
                                                        ps6.setString(1, name);
                                                        ps6.setLong(2, oldc_num);
                                                        ps6.setInt(3, id);
                                                        ps6.setString(4, email_addr);
                                                        ps6.executeUpdate();
                                                    }
                                                    
                                                    //I already inserted into vehiclesused,used_vehicles so i need to insert customer_buys_used
                                                    String insert_cust_buys_used = "insert into customer_buys_used values(?,?,?,?,?,?,?)";
                                                    ps6 = con.prepareStatement(insert_cust_buys_used);
                                                    ps6.setString(1, name);
                                                    ps6.setInt(2, id);
                                                    ps6.setBigDecimal(3, car_wish_to_own);
                                                    ps6.setString(4, email_addr);
                                                    ps6.setBigDecimal(5, new_price);
                                                    ps6.setBigDecimal(6, new_price);
                                                    ps6.setBigDecimal(7, result4.getBigDecimal("list_price"));
                                                    ps6.executeUpdate();
                                                    
                                                    System.out.println("Order has been submitted.");
                                                    System.out.println("Your order will be available for pickup at the Moon Exploration center.");
                                                }
                                                else if(final_decision.equals("no")){
                                                    ab =1;
                                                    //exit out of here
                                                }
                                                else{
                                                    ab=0;
                                                    System.out.print("We did not understand what you had typed. Please type in confirm to place your order."
                                                            + "\nOtherwise type in no to opt of ordering. ");
                                                    final_decision = krb.next();
                                                    final_decision = final_decision.toLowerCase();
                                                }
                                            }
                                        }
                                        
                                        while( t == 0 && !result4.next()){
                                            z=0;
                                            System.out.print("We don't recognize the id that you wrote. If you wish to exit, please type in exit. \n"
                                                    + "Otherwise type in any other character to continue: ");
                                            String dec = krb.next();
                                            dec =dec.toLowerCase();
                                            if(dec.equals("exit")){
                                                return;
                                            }
                                            else{
                                                System.out.print("Please enter a vehicle id of the vehicle you wish to purchase: ");
                                                while (!krb.hasNextBigDecimal()) { 
                                                    System.out.println("only numbers! Type in the model id. "); 
                                                    System.out.print("Enter your vehicle id number: ");
                                                    krb.next(); // discard 
                                                }
                                                car_wish_to_own = krb.nextBigDecimal();
                                                specific_model = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id ";
                                                ps5 = con.prepareStatement(specific_model);
                                                ps5.setBigDecimal(1,car_wish_to_own);
                                                //ps5.setString(2, "Model M");
                                            }
                                            result4 = ps5.executeQuery();
                                        }
                                    }
                                    
                                    
                                }
                                else if(model_selected.equals("u")){
                                    wrong_key2 = 1;
                                    
                                    String cars_used_available = "SELECT v_id,new_price FROM S_L_USED WHERE NOT EXISTS( SELECT v_id FROM customer_buys_used WHERE S_L_USED.v_id = customer_buys_used.v_id ) "
                                            + "AND MODEL_TYPE = ? ";
                                    
                                    ps5 =con.prepareStatement(cars_used_available);
                                    ps5.setString(1, "Model U");
                                    result2 = ps5.executeQuery();
                                   
                                    System.out.println("The available models are: ");
                                    int count2 = 0;
                                    while(result2.next() && count2 == 0){
                                        System.out.println();
                                        
                                        BigDecimal a = result2.getBigDecimal("v_id");
                                        new_price = result2.getBigDecimal("NEW_PRICE");

                                        String find_vehicle_details = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id";
                                        ps4 = con.prepareStatement(find_vehicle_details);
                                        ps4.setBigDecimal(1, a);
                                        result3 = ps4.executeQuery();
                                        while(result3.next()){
                                            System.out.println("Vehicle ID: " + a + "\tModel ID: "+ result3.getInt("m_id") + "\tModel Name: "+ result3.getString("model_name") +"\tModel Year: "
                                                    +result3.getInt("year") +"\tModel Type: "+ result3.getString("model_type") +"\nOriginal Price: $"+ result3.getBigDecimal("list_price") + 
                                                    "\tNew Price: $"+ new_price);
                                            
                                        }
                                        String package_av = "select * from v_packages_used natural join packages where v_id = ? ";
                                        ps4 = con.prepareStatement(package_av);
                                        ps4.setBigDecimal(1, a);
                                        result3 = ps4.executeQuery();
                                        
                                        while(result3.next()){
                                            System.out.println("Package selected for vehicle " +a + ": " + result3.getString("package_name"));
                                        }
                                    } 
                                    if(!result2.next() && count2 == 0){
                                        count2=1;
                                        System.out.println("There are no more available used vehicles. Please call our office location during our regular business hours if you have any questions.");
                                        //return;
                                    }
                                    System.out.println("Please enter the vehicle id of the vehicle you wish to purchase: ");
                                    System.out.print("Vehicle ID: ");
                                    BigDecimal car_wish_to_own;
                                    while (!krb.hasNextBigDecimal()) { 
                                        System.out.println("only numbers! Type in the vehicle id. "); 
                                        System.out.print("Enter the vehicle id of the vehicle you wish to purchase: ");
                                        krb.next(); // discard 
                                    }
                                    car_wish_to_own = krb.nextBigDecimal();
                                    krb.nextLine();
                                    int z = 0;
                                    int t = 0;
                                    while ( z ==0){
                                        String specific_model = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id ";
                                        ps5 = con.prepareStatement(specific_model);
                                        ps5.setBigDecimal(1,car_wish_to_own);
                                        //ps5.setString(2, "Model M");
                                        
                                        result4 = ps5.executeQuery();
                                        while(result4.next()){
                                            z = 1;t = 1;
                                            System.out.println("\nVehicle ID: " + car_wish_to_own + "\tModel ID: "+ result4.getInt("m_id") + "\tModel Name: "+ result4.getString("model_name") +"\tModel Year: "
                                                    +result4.getInt("year") +"\tModel Type: "+ result4.getString("model_type") +"\nOriginal Price: $"+ result4.getBigDecimal("list_price") + 
                                                    "\tNew Price: $"+ new_price);
                                            if(oldc_num != 0 ){
                                                System.out.println("Card Info: " + oldc_num);
                                            }
                                            else if( oldc_num_used != 0){
                                                System.out.println("Card Info: " + oldc_num_used);
                                            }
                                            System.out.print("Is this the vehicle you want to purchase? Type in confirm to purchase this vehicle: ");
                                            
                                            String final_decision = krb.next();
                                            final_decision = final_decision.toLowerCase();
                                            int ab=0;
                                            while (ab ==0){
                                                if(final_decision.equals("confirm")){
                                                    ab=1;
                                                    if(oldc_num != 0 && oldc_num_used == 0 ){
                                                        String insert_Custom = "insert into customer_payment_used  values(?,?,?,?)";
                                                        ps6 = con.prepareStatement(insert_Custom);
                                                        ps6.setString(1, name);
                                                        ps6.setLong(2, oldc_num);
                                                        ps6.setInt(3, id);
                                                        ps6.setString(4, email_addr);
                                                        ps6.executeUpdate();
                                                    }
                                                    
                                                    //I already inserted into vehiclesused,used_vehicles so i need to insert customer_buys_used
                                                    String insert_cust_buys_used = "insert into customer_buys_used values(?,?,?,?,?,?,?)";
                                                    ps6 = con.prepareStatement(insert_cust_buys_used);
                                                    ps6.setString(1, name);
                                                    ps6.setInt(2, id);
                                                    ps6.setBigDecimal(3, car_wish_to_own);
                                                    ps6.setString(4, email_addr);
                                                    ps6.setBigDecimal(5, new_price);
                                                    ps6.setBigDecimal(6, new_price);
                                                    ps6.setBigDecimal(7, result4.getBigDecimal("list_price"));
                                                    ps6.executeUpdate();
                                                    
                                                    System.out.println("Order has been submitted.");
                                                    System.out.println("Your order will be available for pickup at the Moon Exploration center.");
                                                }
                                                else if(final_decision.equals("no")){
                                                    ab =1;
                                                    //exit out of here
                                                }
                                                else{
                                                    ab=0;
                                                    System.out.print("We did not understand what you had typed. Please type in confirm to place your order."
                                                            + "\nOtherwise type in no to opt of ordering. ");
                                                    final_decision = krb.next();
                                                    final_decision = final_decision.toLowerCase();
                                                }
                                            }
                                        }
                                        
                                        while( t == 0 && !result4.next()){
                                            z=0;
                                            System.out.print("We don't recognize the id that you wrote. If you wish to exit, please type in exit. \n"
                                                    + "Otherwise type in any other character to continue: ");
                                            String dec = krb.next();
                                            dec =dec.toLowerCase();
                                            if(dec.equals("exit")){
                                                return;
                                            }
                                            else{
                                                System.out.print("Please enter a vehicle id of the vehicle you wish to purchase: ");
                                                while (!krb.hasNextBigDecimal()) { 
                                                    System.out.println("only numbers! Type in the model id. "); 
                                                    System.out.print("Enter your vehicle id number: ");
                                                    krb.next(); // discard 
                                                }
                                                car_wish_to_own = krb.nextBigDecimal();
                                                specific_model = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id ";
                                                ps5 = con.prepareStatement(specific_model);
                                                ps5.setBigDecimal(1,car_wish_to_own);
                                                //ps5.setString(2, "Model M");
                                            }
                                            result4 = ps5.executeQuery();
                                        }
                                    } 
                                    
                                    
                                }
                                else if(model_selected.equals("s")){
                                    wrong_key2 = 1;
                                    
                                    String cars_used_available = "SELECT v_id,new_price FROM S_L_USED WHERE NOT EXISTS( SELECT v_id FROM customer_buys_used WHERE S_L_USED.v_id = customer_buys_used.v_id ) "
                                            + "AND MODEL_TYPE = ? ";
                                    
                                    ps5 =con.prepareStatement(cars_used_available);
                                    ps5.setString(1, "Model S");
                                    result2 = ps5.executeQuery();
                                   
                                    System.out.println("The available models are: ");
                                    int count2 = 0;
                                    while(result2.next() && count2==0){
                                        System.out.println();
                                        
                                        BigDecimal a = result2.getBigDecimal("v_id");
                                        new_price = result2.getBigDecimal("NEW_PRICE");

                                        String find_vehicle_details = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id";
                                        ps4 = con.prepareStatement(find_vehicle_details);
                                        ps4.setBigDecimal(1, a);
                                        result3 = ps4.executeQuery();
                                        while(result3.next()){
                                            System.out.println("Vehicle ID: " + a + "\tModel ID: "+ result3.getInt("m_id") + "\tModel Name: "+ result3.getString("model_name") +"\tModel Year: "
                                                    +result3.getInt("year") +"\tModel Type: "+ result3.getString("model_type") +"\nOriginal Price: $"+ result3.getBigDecimal("list_price") + 
                                                    "\tNew Price: $"+ new_price);
                                            
                                        }
                                        String package_av = "select * from v_packages_used natural join packages where v_id = ? ";
                                        ps4 = con.prepareStatement(package_av);
                                        ps4.setBigDecimal(1, a);
                                        result3 = ps4.executeQuery();
                                        
                                        while(result3.next()){
                                            System.out.println("Package selected for vehicle " +a + ": " + result3.getString("package_name"));
                                        }
                                    } 
                                    if(!result2.next() && count2 == 0){
                                        count2=1;
                                        System.out.println("There are no more available used vehicles. Please call our office location during our regular business hours if you have any questions.");
                                        //return;
                                    }
                                    System.out.println("Please enter the vehicle id of the vehicle you wish to purchase: ");
                                    System.out.print("Vehicle ID: ");
                                    BigDecimal car_wish_to_own;
                                    while (!krb.hasNextBigDecimal()) { 
                                        System.out.println("only numbers! Type in the vehicle id. "); 
                                        System.out.print("Enter the vehicle id of the vehicle you wish to purchase: ");
                                        krb.next(); // discard 
                                    }
                                    car_wish_to_own = krb.nextBigDecimal();
                                    krb.nextLine();
                                    int z = 0;
                                    int t = 0;
                                    while ( z ==0){
                                        String specific_model = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id ";
                                        ps5 = con.prepareStatement(specific_model);
                                        ps5.setBigDecimal(1,car_wish_to_own);
                                        //ps5.setString(2, "Model M");
                                        
                                        result4 = ps5.executeQuery();
                                        while(result4.next()){
                                            z = 1;t = 1;
                                            System.out.println("\nVehicle ID: " + car_wish_to_own + "\tModel ID: "+ result4.getInt("m_id") + "\tModel Name: "+ result4.getString("model_name") +"\tModel Year: "
                                                    +result4.getInt("year") +"\tModel Type: "+ result4.getString("model_type") +"\nOriginal Price: $"+ result4.getBigDecimal("list_price") + 
                                                    "\tNew Price: $"+ new_price);
                                            if(oldc_num != 0 ){
                                                System.out.println("Card Info: " + oldc_num);
                                            }
                                            else if( oldc_num_used != 0){
                                                System.out.println("Card Info: " + oldc_num_used);
                                            }
                                            System.out.print("Is this the vehicle you want to purchase? Type in confirm to purchase this vehicle: ");
                                            
                                            String final_decision = krb.next();
                                            final_decision = final_decision.toLowerCase();
                                            int ab=0;
                                            while (ab ==0){
                                                if(final_decision.equals("confirm")){
                                                    ab=1;
                                                    if(oldc_num != 0 && oldc_num_used == 0 ){
                                                        String insert_Custom = "insert into customer_payment_used  values(?,?,?,?)";
                                                        ps6 = con.prepareStatement(insert_Custom);
                                                        ps6.setString(1, name);
                                                        ps6.setLong(2, oldc_num);
                                                        ps6.setInt(3, id);
                                                        ps6.setString(4, email_addr);
                                                        ps6.executeUpdate();
                                                    }
                                                    
                                                    //I already inserted into vehiclesused,used_vehicles so i need to insert customer_buys_used
                                                    String insert_cust_buys_used = "insert into customer_buys_used values(?,?,?,?,?,?,?)";
                                                    ps6 = con.prepareStatement(insert_cust_buys_used);
                                                    ps6.setString(1, name);
                                                    ps6.setInt(2, id);
                                                    ps6.setBigDecimal(3, car_wish_to_own);
                                                    ps6.setString(4, email_addr);
                                                    ps6.setBigDecimal(5, new_price);
                                                    ps6.setBigDecimal(6, new_price);
                                                    ps6.setBigDecimal(7, result4.getBigDecimal("list_price"));
                                                    ps6.executeUpdate();
                                                    
                                                    System.out.println("Order has been submitted.");
                                                    System.out.println("Your order will be available for pickup at the Moon Exploration center.");
                                                }
                                                else if(final_decision.equals("no")){
                                                    ab =1;
                                                    //exit out of here
                                                }
                                                else{
                                                    ab=0;
                                                    System.out.print("We did not understand what you had typed. Please type in confirm to place your order."
                                                            + "\nOtherwise type in no to opt of ordering. ");
                                                    final_decision = krb.next();
                                                    final_decision = final_decision.toLowerCase();
                                                }
                                            }
                                        }
                                        
                                        while( t == 0 && !result4.next()){
                                            z=0;
                                            System.out.print("We don't recognize the id that you wrote. If you wish to exit, please type in exit. \n"
                                                    + "Otherwise type in any other character to continue: ");
                                            String dec = krb.next();
                                            dec =dec.toLowerCase();
                                            if(dec.equals("exit")){
                                                return;
                                            }
                                            else{
                                                System.out.print("Please enter a vehicle id of the vehicle you wish to purchase: ");
                                                while (!krb.hasNextBigDecimal()) { 
                                                    System.out.println("only numbers! Type in the model id. "); 
                                                    System.out.print("Enter your vehicle id number: ");
                                                    krb.next(); // discard 
                                                }
                                                car_wish_to_own = krb.nextBigDecimal();
                                                specific_model = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id ";
                                                ps5 = con.prepareStatement(specific_model);
                                                ps5.setBigDecimal(1,car_wish_to_own);
                                                //ps5.setString(2, "Model M");
                                            }
                                            result4 = ps5.executeQuery();
                                        }
                                    }
                                }
                                else if(model_selected.equals("k")){
                                    wrong_key2 = 1;
                                    
                                    String cars_used_available = "SELECT v_id,new_price FROM S_L_USED WHERE NOT EXISTS( SELECT v_id FROM customer_buys_used WHERE S_L_USED.v_id = customer_buys_used.v_id ) "
                                            + "AND MODEL_TYPE = ? ";
                                    
                                    ps5 =con.prepareStatement(cars_used_available);
                                    ps5.setString(1, "Model K");
                                    result2 = ps5.executeQuery();
                                   
                                    System.out.println("The available models are: ");
                                    int count2 = 0;
                                    while(result2.next() && count2 == 0){
                                        System.out.println();
                                        
                                        BigDecimal a = result2.getBigDecimal("v_id");
                                        new_price = result2.getBigDecimal("NEW_PRICE");

                                        String find_vehicle_details = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id";
                                        ps4 = con.prepareStatement(find_vehicle_details);
                                        ps4.setBigDecimal(1, a);
                                        result3 = ps4.executeQuery();
                                        while(result3.next()){
                                            System.out.println("Vehicle ID: " + a + "\tModel ID: "+ result3.getInt("m_id") + "\tModel Name: "+ result3.getString("model_name") +"\tModel Year: "
                                                    +result3.getInt("year") +"\tModel Type: "+ result3.getString("model_type") +"\nOriginal Price: $"+ result3.getBigDecimal("list_price") + 
                                                    "\tNew Price: $"+ new_price);
                                            
                                        }
                                        String package_av = "select * from v_packages_used natural join packages where v_id = ? ";
                                        ps4 = con.prepareStatement(package_av);
                                        ps4.setBigDecimal(1, a);
                                        result3 = ps4.executeQuery();
                                        
                                        while(result3.next()){
                                            System.out.println("Package selected for vehicle " +a + ": " + result3.getString("package_name"));
                                        }
                                    } 
                                    if(!result2.next() && count2 == 0){
                                        count2=1;
                                        System.out.println("There are no more available used vehicles. Please call our office location during our regular business hours if you have any questions.");
                                        //return;
                                    }
                                    System.out.println("Please enter the vehicle id of the vehicle you wish to purchase: ");
                                    System.out.print("Vehicle ID: ");
                                    BigDecimal car_wish_to_own;
                                    while (!krb.hasNextBigDecimal()) { 
                                        System.out.println("only numbers! Type in the vehicle id. "); 
                                        System.out.print("Enter the vehicle id of the vehicle you wish to purchase: ");
                                        krb.next(); // discard 
                                    }
                                    car_wish_to_own = krb.nextBigDecimal();
                                    krb.nextLine();
                                    int z = 0;
                                    int t = 0;
                                    while ( z ==0){
                                        String specific_model = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id ";
                                        ps5 = con.prepareStatement(specific_model);
                                        ps5.setBigDecimal(1,car_wish_to_own);
                                        //ps5.setString(2, "Model M");
                                        
                                        result4 = ps5.executeQuery();
                                        while(result4.next()){
                                            z = 1;t = 1;
                                            System.out.println("\nVehicle ID: " + car_wish_to_own + "\tModel ID: "+ result4.getInt("m_id") + "\tModel Name: "+ result4.getString("model_name") +"\tModel Year: "
                                                    +result4.getInt("year") +"\tModel Type: "+ result4.getString("model_type") +"\nOriginal Price: $"+ result4.getBigDecimal("list_price") + 
                                                    "\tNew Price: $"+ new_price);
                                            if(oldc_num != 0 ){
                                                System.out.println("Card Info: " + oldc_num);
                                            }
                                            else if( oldc_num_used != 0){
                                                System.out.println("Card Info: " + oldc_num_used);
                                            }
                                            System.out.print("Is this the vehicle you want to purchase? Type in confirm to purchase this vehicle: ");
                                            
                                            String final_decision = krb.next();
                                            final_decision = final_decision.toLowerCase();
                                            int ab=0;
                                            while (ab ==0){
                                                if(final_decision.equals("confirm")){
                                                    ab=1;
                                                    if(oldc_num != 0 && oldc_num_used == 0 ){
                                                        String insert_Custom = "insert into customer_payment_used  values(?,?,?,?)";
                                                        ps6 = con.prepareStatement(insert_Custom);
                                                        ps6.setString(1, name);
                                                        ps6.setLong(2, oldc_num);
                                                        ps6.setInt(3, id);
                                                        ps6.setString(4, email_addr);
                                                        ps6.executeUpdate();
                                                    }
                                                    
                                                    //I already inserted into vehiclesused,used_vehicles so i need to insert customer_buys_used
                                                    String insert_cust_buys_used = "insert into customer_buys_used values(?,?,?,?,?,?,?)";
                                                    ps6 = con.prepareStatement(insert_cust_buys_used);
                                                    ps6.setString(1, name);
                                                    ps6.setInt(2, id);
                                                    ps6.setBigDecimal(3, car_wish_to_own);
                                                    ps6.setString(4, email_addr);
                                                    ps6.setBigDecimal(5, new_price);
                                                    ps6.setBigDecimal(6, new_price);
                                                    ps6.setBigDecimal(7, result4.getBigDecimal("list_price"));
                                                    ps6.executeUpdate();
                                                    
                                                    System.out.println("Order has been submitted.");
                                                    System.out.println("Your order will be available for pickup at the Moon Exploration center.");
                                                }
                                                else if(final_decision.equals("no")){
                                                    ab =1;
                                                    //exit out of here
                                                }
                                                else{
                                                    ab=0;
                                                    System.out.print("We did not understand what you had typed. Please type in confirm to place your order."
                                                            + "\nOtherwise type in no to opt of ordering. ");
                                                    final_decision = krb.next();
                                                    final_decision = final_decision.toLowerCase();
                                                }
                                            }
                                        }
                                        
                                        while( t == 0 && !result4.next()){
                                            z=0;
                                            System.out.print("We don't recognize the id that you wrote. If you wish to exit, please type in exit. \n"
                                                    + "Otherwise type in any other character to continue: ");
                                            String dec = krb.next();
                                            dec =dec.toLowerCase();
                                            if(dec.equals("exit")){
                                                return;
                                            }
                                            else{
                                                System.out.print("Please enter a vehicle id of the vehicle you wish to purchase: ");
                                                while (!krb.hasNextBigDecimal()) { 
                                                    System.out.println("only numbers! Type in the model id. "); 
                                                    System.out.print("Enter your vehicle id number: ");
                                                    krb.next(); // discard 
                                                }
                                                car_wish_to_own = krb.nextBigDecimal();
                                                specific_model = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id ";
                                                ps5 = con.prepareStatement(specific_model);
                                                ps5.setBigDecimal(1,car_wish_to_own);
                                                //ps5.setString(2, "Model M");
                                            }
                                            result4 = ps5.executeQuery();
                                        }
                                    }
                                }
                            }
                        }
                        else if(option.equals("exit") || option.equals("no")){
                            wrong_key =1;
                            System.out.println("No used car was purchased here.");
                        }
                        else{
                            System.out.print("We did not understand what you typed. If you want to exit type in 'exit'. "
                                    + "\nOtherwise type in yes if you want to purchase a used car: ");
                            option = krb.next();
                            option=option.toLowerCase();
                        }
                    }     
                }                
                else{
                    System.out.print("We did not understand what you said. Are you sure you want to exit? Type in yes or exit to exit. otherwise type in no "
                            + "to continue to update your information: ");
                    String secondoption = krb.next();
                    secondoption = secondoption.toLowerCase();
                    int abcd = 0;
                    while (abcd ==0 ){
                        if(secondoption.equals("yes") ||secondoption.equals("exit") ){
                            System.out.println("Program is ended.");
                            return;
                        }
                        else if(secondoption.equals("no")){
                            abcd =1;
                            System.out.print("Do you want to update your information? Type yes if you want to. Otherwise type no: ");
                            //String option="";
                            option = krb.next();
                            option=option.toLowerCase();
                            int abc = 0;
                            go= 0;
                            
                            while (abc == 0){
                                
                                if(option.equals("yes") ){
                                    System.out.println("Name:"+name);
                                    num =0;

                                    a.updateInfo();

                                    a.confirmUpdatedAccount();

                                    long whichC = 0;
                                    if(num == 0){
                                        if(oldc_num != 0 ){
                                            System.out.println("Card Info: " + oldc_num);
                                            whichC = oldc_num;
                                        }
                                        else if( oldc_num_used != 0){
                                            System.out.println("Card Info: " + oldc_num_used);
                                            whichC = oldc_num_used;
                                        }
                                    }

                                    else{
                                        System.out.println("Card Info: " + num);
                                        whichC = num;
                                    }


                                    System.out.print("Is this information correct? Type in yes to confirm this change. Otherwise type in no to exit out of updating your information : ");
                                    String confirm_D = krb.next();
                                    confirm_D = confirm_D.toLowerCase();

                                    int ex = 0;
                                    while(ex ==0){
                                        if(confirm_D.equals("yes")){
                                            ex =1;

                                            if(!(old_name.equals(name))){
                                                //This procedure allows me to edit tables that have the name of the customer
                                                //as foreign keys constraints
                                                CallableStatement stmt = con.prepareCall("{call CASCADE_C_CBN_CBU_CR(?,?)}");
                                                stmt.setString(1, old_name);
                                                stmt.setString(2,name);

                                                boolean hadResults = stmt.execute();

                                                while (hadResults) {
                                                    ResultSet rs = stmt.getResultSet();

                                                    // process result set

                                                    hadResults = stmt.getMoreResults();
                                                }
                                            }
                                            if(!(old_email.equals(email_addr))){
                                                CallableStatement stmt = con.prepareCall("{call REPLACE_C_EMAIL(?,?)}");
                                                stmt.setString(1, old_email);
                                                stmt.setString(2, email_addr);
                                                boolean hadResults = stmt.execute();

                                                while (hadResults) {
                                                    ResultSet rs = stmt.getResultSet();

                                                    // process result set

                                                    hadResults = stmt.getMoreResults();
                                                }

                                            }
                                            //next i should be able to update my address and email address without calling the procedure

                                            String updateRecord ="UPDATE CUSTOMER SET STREET_NUMBER = ?,"
                                                    + "STREET_NAME=?,APT_NUMBER = ?, CITY = ?, STATE = ?, ZIP_CODE = ?"
                                                    + ", COUNTRY=?,PLANET=?"
                                                    + " WHERE ID=?";
                                            ps2 = con.prepareStatement(updateRecord);
                                            ps2.setInt(1, street_number);
                                            ps2.setString(2,street_name);
                                            if(apt_number == null){
                                                ps2.setNull(3, Types.INTEGER);
                                            }
                                            else{
                                                ps2.setInt(3, apt_number);
                                            }
                                            ps2.setString(4, city);
                                            ps2.setString(5, state);
                                            ps2.setInt(6,zip_code);
                                            ps2.setString(7,country);
                                            ps2.setString(8,planet);
                                            ps2.setInt(9,id);
                                            ps2.executeUpdate();

                                            if(num !=0){
                                                String updateCredit = "UPDATE CUSTOMER_PAYMENT SET CREDIT_DEBIT_NUMBER = ? WHERE name = ? and id = ?"; 
                                                ps7 = con.prepareStatement(updateCredit);
                                                ps7.setLong(1, num);
                                                ps7.setString(2, name);
                                                ps7.setInt(3,id);
                                                ps7.executeUpdate();
                                                String updateCredit_used = "UPDATE CUSTOMER_PAYMENT_USED SET CREDIT_DEBIT_NUMBER = ? WHERE name = ? and id = ?"; 
                                                ps7 = con.prepareStatement(updateCredit_used);
                                                ps7.setLong(1, num);
                                                ps7.setString(2, name);
                                                ps7.setInt(3,id);
                                                ps7.executeUpdate();
                                            }
                                            else if(num ==0){
                                                String updateCredit = "UPDATE CUSTOMER_PAYMENT SET CREDIT_DEBIT_NUMBER = ? WHERE name = ? and id = ?"; 
                                                ps7 = con.prepareStatement(updateCredit);
                                                ps7.setLong(1, whichC);
                                                ps7.setString(2, name);
                                                ps7.setInt(3,id);
                                                ps7.executeUpdate();
                                                String updateCredit_used = "UPDATE CUSTOMER_PAYMENT_USED SET CREDIT_DEBIT_NUMBER = ? WHERE name = ? and id = ?"; 
                                                ps7 = con.prepareStatement(updateCredit_used);
                                                ps7.setLong(1, whichC);
                                                ps7.setString(2, name);
                                                ps7.setInt(3,id);
                                                ps7.executeUpdate();
                                            }
                                        }
                                        else if(confirm_D.equals("no")){
                                            System.out.println("Customer account is not updated.");
                                            ex =1;
                                        }
                                        else{
                                            System.out.println("We did not understand what you had said. If you want to exit, type in exit. "
                                                    + "\nOtherwise type in yes to confirm your account or no to not confirm the changes. :");
                                            confirm_D = krb.next();
                                            confirm_D = confirm_D.toLowerCase();

                                            if(confirm_D.equals("exit")){
                                                return;
                                            }
                                            ex = 0;
                                        }
                                    }
                                    go= 1;
                                }
                                if(option.equals("no") || go == 1 ){
                                    abc=1;
                                    System.out.println("\nNew cars currently...");
                                    //find if the customer is the owner of a used or new car.
                                    String customer_query = "select * from customer_buys_new where name =? and id = ?";
                                    ps3 =con.prepareStatement(customer_query);
                                    ps3.setString(1, name);
                                    ps3.setInt(2, id);

                                    result2 = ps3.executeQuery();
                                    int count = 0;
                                    while(result2.next() && count ==0){
                                        customer_query = "select * from customer_buys_new natural join v_model natural join new_vehicle "
                                                + "where name =? and id = ?";
                                        ps4 =con.prepareStatement(customer_query);
                                        ps4.setString(1, name);
                                        ps4.setInt(2, id);

                                        result3 = ps4.executeQuery();
                                        ArrayList <BigDecimal>v_id = new ArrayList<BigDecimal>();
                                        while(result3.next()){
                                            System.out.println ("\nVehicle Id: "+
                                                    result3.getBigDecimal("v_id") 
                                                    +
                                                 "\nModel id: " + result3.getInt("m_id") +
                                                    "\nModel Name: " + result3.getString("modelname")
                                                    + "\nModel Type: "+result3.getString("model_type")
                                                    +"\nLocation: "+result3.getString("location")
                                                    +"\nPrice Paid: $" + result3.getBigDecimal("price_paid"));
                                            v_id.add(result3.getBigDecimal("v_id"));

                                        }

                                        //traverse through each v_id
                                        int lim = 0;
                                        while (lim < v_id.size()){
                                            String package_q = "select * from v_packages natural join packages where v_id = ? ";
                                            ps4 = con.prepareStatement(package_q);
                                            BigDecimal a = v_id.get(lim); 
                                            ps4.setBigDecimal(1, a);
                                            result3 = ps4.executeQuery();

                                            while(result3.next()){
                                                System.out.println("\nPackage selected for vehicle " +result3.getBigDecimal("v_id") + ": " + result3.getString("package_name"));
                                            }

                                            String option_q = "select * from vehicle_options where v_id = ? ";
                                            ps4 = con.prepareStatement(option_q);
                                            ps4.setBigDecimal(1, a);
                                            result3 = ps4.executeQuery();

                                            while(result3.next()){
                                                System.out.println("\nOption selected for vehicle " + result3.getBigDecimal("v_id") + ": " + result3.getString("option_name"));
                                            }
                                            lim +=1;
                                        }
                                        count =1;
                                    }


                                    if(!result2.next() && count == 0){
                                        //System.out.println("These are the available cars we have on record. If there are discrepancies please contact our representatives.");
                                        System.out.println("We have no available new cars under your account at the moment. "
                                                + "If there are discrepancies please contact our representatives.");
                                    }

                                    System.out.println();
                                    System.out.println("Used cars currently...");
                                    customer_query = "select * from customer_buys_used where name =? and id = ?";
                                    ps3 =con.prepareStatement(customer_query);
                                    ps3.setString(1, name);
                                    ps3.setInt(2, id);
                                    result2 = ps3.executeQuery();
                                    count = 0;
                                    while(result2.next() && count ==0){
                                        customer_query = "select * from customer_buys_used natural join used_vehicles natural join v_model_used "
                                            + "where name =? and id = ?";
                                        ps3 =con.prepareStatement(customer_query);
                                        ps3.setString(1, name);
                                        ps3.setInt(2, id);
                                        result3 = ps3.executeQuery();
                                        ArrayList <BigDecimal> v_id_used = new ArrayList <BigDecimal>();
                                        while(result3.next()){
                                            System.out.println ("\nVehicle Id: "+
                                                        result3.getBigDecimal("v_id") 
                                                        + "\nModel id: " + result3.getInt("m_id") +
                                                        "\nModel Name: " + result3.getString("modelname")
                                                        + "\nModel Type: "+result3.getString("model_type")
                                                        +"\nLocation: "+result3.getString("location")
                                                        +"\nPrice Paid: $" + result3.getBigDecimal("price_paid"));
                                            v_id_used.add(result3.getBigDecimal("v_id"));
                                        }
                                                                //traverse through each v_id
                                        int lim = 0;
                                        while (lim < v_id_used.size()){
                                            String package_q = "select * from v_packages_used natural join packages where v_id = ? ";
                                            ps4 = con.prepareStatement(package_q);
                                            BigDecimal b=  v_id_used.get(lim);
                                            ps4.setBigDecimal(1, b);
                                            result3 = ps4.executeQuery();

                                            while(result3.next()){
                                                System.out.println("\nPackage selected for vehicle " +result3.getBigDecimal("v_id") + ": " + result3.getString("package_name"));
                                            }
                                            String option_q = "select * from vehicle_options where v_id = ? ";
                                            ps4 = con.prepareStatement(option_q);
                                            ps4.setBigDecimal(1, b);
                                            result3 = ps4.executeQuery();

                                            while(result3.next()){
                                                System.out.println("\nOption selected for vehicle " + result3.getBigDecimal("v_id") + ": " + result3.getString("option_name"));
                                            }
                                            lim +=1;
                                        }
                                        count  =1;
                                    }
                                    if(!result2.next() && count == 0){
                                        //System.out.println("These are the available cars we have on record. If there are discrepancies please contact our representatives.");
                                        System.out.println("We have no available used cars under your account at the moment. "
                                                + "If there are discrepancies please contact our representatives.");
                                    }
                                    System.out.println();
                                    //I should print a list of their most recent repairs.
                                    String repairs = "select * from customer_repair where name = ? and id = ? ";
                                    ps3 = con.prepareStatement(repairs);
                                    ps3.setString(1, name);
                                    ps3.setInt(2, id);
                                    result3 = ps3.executeQuery();
                                    count = 0;
                                    while(result3.next() && count == 0){
                                        System.out.println("\nMost recent repairs: \nLocation:" + result3.getString("location") +
                                                "\nDate of repair: " + result3.getDate("date_repair")+ "\nCost: "+ result3.getBigDecimal("cost"));
                                        count =1;
                                    }

                                    if(!result3.next()&& count == 0){
                                        System.out.println("\nNo recent repair appointments under your account.");
                                    }

                                    // Ask if they want to submit a request for an appointment.


                                    //Go to the process of buying a new car.
                                    //select to go to the option of a new car or a used car
                                    //detail cost of each car.
                                    //Steps of buying a new car?
                                    //1.) Ask the type of model they are looking for.
                                    //2.) print out the model descriptions so they get an idea of what they are looking for

                                    System.out.print("\nDo you want to purchase a new car? Type in yes to continue. Otherwise type in no to exit: ");
                                    option = krb.next();
                                    option=option.toLowerCase();
                                    int wrong_key=0;
                                    while ( wrong_key == 0){
                                        if(option.equals("yes")){
                                            BigInteger new_vid = new BigInteger(66,new Random());
                                            int package_ch =2 ;
                                            wrong_key = 1;
                                            System.out.println("The model types that we have available are:\n"
                                                    + "Model K, the “Kart”, a small electric vehicle for use on the surface of the earth.\n"
                                                    + "Model U, the undersea vehicle, used as a personal submarine.\n"
                                                    + "Model M, the moon vehicle, used to ride on the surface of the moon.\n"
                                                    + "Model S, the spacecraft, used for space travel. This vehicle is capable of landing on most planets and moons,"
                                                    + " and, after refueling, can re-launch into space.");
                                            System.out.print("Select M for Model M, select U for Model U, select S for Model S, select K for Model K: ");

                                            String model_selected = krb.next();
                                            model_selected = model_selected.toLowerCase();
                                            int wrong_key2=0;
                                            while( wrong_key2 ==0){
                                                if(model_selected.equals("m")){
                                                    String car_query = "select * from model where model_type = ? ";
                                                    ps5 = con.prepareStatement(car_query);
                                                    ps5.setString(1,"Model M");
                                                    result2 = ps5.executeQuery();

                                                    while(result2.next()){

                                                        System.out.println();
                                                        System.out.println("Model ID: " + result2.getInt("m_id")+"\nModel name: "+ result2.getString("modelname")+
                                                        "\nYear: " + result2.getInt("year") + "\nModel Type: " + result2.getString("model_type") +
                                                        "\nPrice: $" + result2.getBigDecimal("list_price"));
                                                    }

                                                    if(!result2.next()){
                                                        System.out.println("That is all of the Model M vehicles being sold right now.");
                                                    }

                                                    //Next have them enter the name of the vehicle and year they want to get.
                                                    System.out.println("Please enter the model id of the vehicle you wish to purchase: ");
                                                    System.out.print("Model ID: ");
                                                    int car_wish_to_own  =0;
                                                    while (!krb.hasNextInt()) { 
                                                        System.out.println("only numbers! Type in the model id. "); 
                                                        System.out.print("Enter the model id of the vehicle you wish to purchase: ");
                                                        krb.next(); // discard 
                                                    }

                                                    car_wish_to_own = krb.nextInt();
                                                    krb.nextLine();
                                                    int z = 0;
                                                    int t = 0;
                                                    while ( z ==0){
                                                        String specific_model = "select * from model where m_id = ? and model_type = ?";
                                                        ps5 = con.prepareStatement(specific_model);
                                                        ps5.setInt(1,car_wish_to_own);
                                                        ps5.setString(2, "Model M");

                                                        result4 = ps5.executeQuery();
                                                        while(result4.next()){
                                                            z = 1;t = 1;
                                                        }

                                                        while( t == 0 && !result4.next()){
                                                            z=0;
                                                            System.out.print("We don't recognize the id that you wrote. If you wish to exit, please type in exit. \n"
                                                                    + "Otherwise type in any other character to continue: ");
                                                            String dec = krb.next();
                                                            dec =dec.toLowerCase();
                                                            if(dec.equals("exit")){
                                                                return;
                                                            }
                                                            else{
                                                                System.out.print("Please enter a model id of the vehicle you wish to purchase: ");
                                                                while (!krb.hasNextInt()) { 
                                                                    System.out.println("only numbers! Type in the model id. "); 
                                                                    System.out.print("Enter your model id number: ");
                                                                    krb.next(); // discard 
                                                                }
                                                                car_wish_to_own = krb.nextInt();
                                                                specific_model = "select * from model where m_id = ? and model_type = ?";
                                                                ps5 = con.prepareStatement(specific_model);
                                                                ps5.setInt(1,car_wish_to_own);
                                                                ps5.setString(2, "Model M");
                                                            }
                                                            result4 = ps5.executeQuery();
                                                        }
                                                    }
                                                    String specific_model = "select * from model where m_id = ? and model_type = ?";
                                                    ps5 = con.prepareStatement(specific_model);
                                                    ps5.setInt(1,car_wish_to_own);
                                                    ps5.setString(2, "Model M");

                                                    result4 = ps5.executeQuery();
                                                    while(result4.next()){
                                                        z=1;
                                                        System.out.println("\nThe base price of this vehicle" + " the "+
                                                                result4.getString("modelname")+ " is $"+result4.getBigDecimal("list_price"));
                                                        baseprice = result4.getBigDecimal("list_price");
                                                        initialPrice = baseprice;
                                                        initialModelID= result4.getInt("M_ID");
                                                        initialModelName =result4.getString("modelname");
                                                        initialModelYear = result4.getInt("YEAR");
                                                        initialModelType = result4.getString("MODEL_TYPE");

                                                    }


                                                    System.out.println();

                                                    System.out.println("Do you want to purchase a package? The packages available with Model M are:"
                                                            + "\nthe Superman Package, which outfits your vehicle as a large truck able to navigate "
                                                            + "the rough terrain of Mars "
                                                            + "\nthe Luxury Package, which determines your vehicle to be as cozy as classy to your liking."
                                                            + "\nFinally, you can purchase the Amenities package which guarantees you a comfortable experience "
                                                            + "in your new car.");
                                                    System.out.println();

                                                    String print_out_options = "WITH RESULT AS ( select option_name as options_name,package_id from options_in_packages where package_id=1 ) SELECT * FROM OPTIONS NATURAL JOIN RESULT";
                                                    ps5 = con.prepareStatement(print_out_options);
                                                    result4 = ps5.executeQuery();
                                                    System.out.println("The Superman package includes: ");
                                                    while(result4.next()){
                                                        System.out.println("a " + result4.getString("OPTIONS_NAME") + ": " + result4.getString("DESCRIPTION") );
                                                    }
                                                    System.out.println();
                                                    print_out_options = "WITH RESULT AS ( select option_name as options_name,package_id from options_in_packages where package_id=3 ) SELECT * FROM OPTIONS NATURAL JOIN RESULT";
                                                    ps5 = con.prepareStatement(print_out_options);
                                                    result4 = ps5.executeQuery();
                                                    System.out.println("The Luxury package includes: ");
                                                    while(result4.next()){
                                                        System.out.println("a " + result4.getString("OPTIONS_NAME") + ": " + result4.getString("DESCRIPTION") );
                                                    }
                                                    System.out.println();
                                                    print_out_options = "WITH RESULT AS ( select option_name as options_name,package_id from options_in_packages where package_id=2 ) SELECT * FROM OPTIONS NATURAL JOIN RESULT";
                                                    ps5 = con.prepareStatement(print_out_options);
                                                    result4 = ps5.executeQuery();
                                                    System.out.println("The Amenities package includes: ");
                                                    while(result4.next()){
                                                        System.out.println("a " + result4.getString("OPTIONS_NAME") + ": " + result4.getString("DESCRIPTION") );
                                                    }
                                                    System.out.print("\nwhich package do you wish to purchase? Type in superman for the superman package"
                                                            + ", type in luxury for the luxury package, or type in amenities for the amenities package."
                                                            + "\nThe Amenities package is chosen for you already but you can upgrade it to the luxury or superman package"
                                                            + ": ");

                                                    String decision = "";
                                                    decision = krb.next();
                                                    decision = decision.toLowerCase();
                                                    int exit_loop = 0;
                                                    String insert_package;
                                                    String insert_vehicles;
                                                    String package_chosen="";

                                                    while( exit_loop==0 ){
                                                        if(decision.equals("superman")){
                                                            exit_loop=1;
                                                            wrong_key2=1;
                                                            System.out.println("superman");
                                                            package_chosen = decision;
                                                        }
                                                        else if(decision.equals("luxury")){
                                                            exit_loop=1;
                                                            wrong_key2=1;
                                                            System.out.println("luxury");
                                                            package_chosen = decision;
                                                        }
                                                        else if(decision.equals("amenities")){
                                                            exit_loop=1;
                                                            wrong_key2=1;
                                                            System.out.println("amenities");
                                                            package_chosen = decision;
                                                        }

                                                        else{
                                                            System.out.print("Did you mean to type that? Type in exit to not purchase the car. Type in amenities to exit out of choosing an upgrade in packages.\n"

                                                                    + "Otherwise type in your preferred package upgrade of superman or luxury: ");
                                                            decision = krb.next();
                                                            decision = decision.toLowerCase();
                                                            if(decision.equals("exit")){
                                                                return;
                                                            }
                                                            if(decision.equals("amenities")){
                                                                exit_loop=1;
                                                                wrong_key2=1;
                                                                package_chosen = "amenities";
                                                            }
                                                            else{
                                                                exit_loop=0;
                                                            }
                                                        }
                                                    }
                                                    String obtain_package_price ="";
                                                    pricePack = null;

                                                    if(package_chosen.equals("superman")){
                                                        package_ch =1;
                                                        obtain_package_price = "select * from packages where package_id = ? ";
                                                        ps5 = con.prepareStatement(obtain_package_price);
                                                        ps5.setInt(1, package_ch);
                                                        result4=ps5.executeQuery();
                                                        //pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                                        while(result4.next()){
                                                            pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                                        }
                                                    }

                                                    else if(package_chosen.equals("luxury")){
                                                        package_ch =3;
                                                        obtain_package_price = "select * from packages where package_id = ? ";
                                                        ps5 = con.prepareStatement(obtain_package_price);
                                                        ps5.setInt(1, package_ch);
                                                        result4=ps5.executeQuery();
                                                        while(result4.next()){
                                                            pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                                        }
                                                    }
                                                    else if(package_chosen.equals("amenities") || package_ch ==2){
                                                        package_ch =2;
                                                        obtain_package_price = "select * from packages where package_id = ? ";
                                                        ps5 = con.prepareStatement(obtain_package_price);
                                                        ps5.setInt(1, package_ch);
                                                        result4=ps5.executeQuery();
                                                        while(result4.next()){
                                                            pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                                        }
                                                    }

                                                    packageSelected = package_chosen;
                                                    totalpricepaid = baseprice;

                                                    if(package_ch == 2 ){
                                                        //the amenities package was selected for them or they chose it.
                                                        //You can choose to add the hybrid as well as bedazzling your wheels with 20'' chrome wheels.
                                                        //add the price of the package amenities

                                                        totalpricepaid = totalpricepaid.add(pricePack);
                                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                                        System.out.print("We have a hybrid option available for this vehicle. "
                                                                + "Do you want to know more about it? ");
                                                        String op_decision = krb.next();
                                                        op_decision = op_decision.toLowerCase();
                                                        int op_d = 0;

                                                        while(op_d == 0){
                                                            if(op_decision.equals("yes")){
                                                                op_d =1;
                                                                String choose_hybrid = "SELECT * FROM OPTIONS where options_name = ?";
                                                                ps5 = con.prepareStatement(choose_hybrid);
                                                                ps5.setString(1, "Hybrid ");
                                                                result4 = ps5.executeQuery();
                                                                priceofHy=0;
                                                                String op_name;
                                                                String op_descr;
                                                                while(result4.next()){
                                                                    System.out.println(result4.getString("OPTIONS_NAME") +"$"+ result4.getInt("LIST_PRICE") + " Description: " + result4.getString("DESCRIPTION"));
                                                                    priceofHy = result4.getInt("LIST_PRICE");
                                                                    op_name = result4.getString("OPTIONS_NAME");
                                                                    op_descr = result4.getString("DESCRIPTION");
                                                                }
                                                                System.out.print("Do you want to purchase this option? ");
                                                                String purchase_hy = krb.next();
                                                                purchase_hy = purchase_hy.toLowerCase();
                                                                int a = 0;
                                                                while( a == 0){
                                                                    if(purchase_hy.equals("yes")){
                                                                        a =1 ;
                                                                        //add to v_options
                                                                        //get price of option and to total price paid
                                                                        //optionSelected[0]= "Hybrid";
                                                                        optionSelected.add("Hybrid");
                                                                        totalpricepaid = totalpricepaid.add(new BigDecimal(priceofHy));
                                                                        System.out.println("The price of your vehicle with this option is right now currently is $" + totalpricepaid);
                                                                    }
                                                                    else if(purchase_hy.equals("no")){
                                                                        a =1 ;
                                                                        System.out.println("The price of your vehicle without this option is right now currently is $" + totalpricepaid);

                                                                    }
                                                                    else{
                                                                        a=0;
                                                                        System.out.print("We did not get what you were trying to say. Can you enter yes "
                                                                                + "if you want to purchase the hybrid option, otherwise say no. ");
                                                                        purchase_hy = krb.next();
                                                                        purchase_hy = purchase_hy.toLowerCase();
                                                                    }
                                                                }

                                                            }
                                                            else if(op_decision.equals("no") || op_decision.equals("exit")){
                                                                op_d =1;

                                                            }
                                                            else{
                                                                op_d=0;
                                                                System.out.println("We did not understand what you had typed. If you want to exit, type in exit."
                                                                        + "\nIf you want to know more about the hybrid type in yes. ");
                                                                op_decision = krb.next();
                                                                op_decision = op_decision.toLowerCase();
                                                            }
                                                        }

                                                        System.out.print("We have a option to add 20'' or larger chrome wheels to this vehicle. "
                                                            + "Do you want to know more about it? ");
                                                        op_decision = krb.next();
                                                        op_decision = op_decision.toLowerCase();
                                                        op_d = 0;
                                                        while(op_d == 0){
                                                            if(op_decision.equals("yes")){
                                                                op_d =1;
                                                                String choose_hybrid = "SELECT * FROM OPTIONS where options_name = ?";
                                                                ps5 = con.prepareStatement(choose_hybrid);
                                                                ps5.setString(1, "Chrome wheels 20” or Larger");
                                                                result4 = ps5.executeQuery();
                                                                priceofCh=0;
                                                                while(result4.next()){
                                                                    System.out.println(result4.getString("OPTIONS_NAME") + " $" + result4.getInt("LIST_PRICE") + " " + result4.getString("DESCRIPTION"));
                                                                    priceofCh = result4.getInt("LIST_PRICE");
                                                                }
                                                                System.out.print("Do you want to purchase this option? ");
                                                                String purchase_hy = krb.next();
                                                                purchase_hy = purchase_hy.toLowerCase();
                                                                int a = 0;
                                                                while( a == 0){
                                                                    if(purchase_hy.equals("yes")){
                                                                        a =1 ;
                                                                        //add to v_options
                                                                        //get price of option and to total price paid
                                                                        //optionSelected[1] = "Chrome wheels 20” or Larger";
                                                                        optionSelected.add("Chrome wheels 20” or Larger");
                                                                        totalpricepaid = totalpricepaid.add(new BigDecimal(priceofCh));
                                                                        System.out.println("The price of your vehicle right with this option is now currently is $" + totalpricepaid);
                                                                    }
                                                                    else if(purchase_hy.equals("no")){
                                                                        a =1 ;

                                                                    }
                                                                    else{
                                                                        a=0;
                                                                        System.out.println("We did not get what you were trying to say. Can you enter yes"
                                                                                + "if you want to purchase the hybrid option, otherwise say no. ");
                                                                        purchase_hy = krb.next();
                                                                        purchase_hy = purchase_hy.toLowerCase();
                                                                    }
                                                                }
                                                            }
                                                            else if(op_decision.equals("no") || op_decision.equals("exit")){
                                                                op_d =1;

                                                            }
                                                            else{
                                                                op_d=0;
                                                                System.out.println("We did not understand what you had typed. If you want to exit, type in exit."
                                                                        + "\nIf you want to know more about the chrome wheels option type in yes. ");
                                                                op_decision = krb.next();
                                                                op_decision = op_decision.toLowerCase();
                                                            }
                                                        }
                                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                                    }
                                                    else{
                                                        //they chose a package other than the amenities package.
                                                        //totalpricepaid= pricePack + totalpricepaid;
                                                        totalpricepaid = totalpricepaid.add(pricePack);
                                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                                    }
                                                    System.out.println();
                                                    //list a summary of the model that the customer is about to purchase.
                                                    //ask to confirm if this correct. if they say yes, the records will all be updated.
                                                    a.SummaryInfo();

                                                    if(oldc_num != 0 ){
                                                        System.out.println("Card Info: " + oldc_num);
                                                    }
                                                    else if( oldc_num_used != 0){
                                                        System.out.println("Card Info: " + oldc_num_used);
                                                    }
                                                    System.out.print("Is this correct? Type in confirm to place your order. Otherwise type in no to opt out of ordering: ");
                                                    String final_decision = krb.next();
                                                    final_decision = final_decision.toLowerCase();
                                                    int ab=0;
                                                    while (ab ==0){
                                                        if(final_decision.equals("confirm")){
                                                            ab =1;
                                                            //do all my inserts here.
                                                            if(oldc_num == 0 && oldc_num_used != 0 ){
                                                                String insert_Custom = "insert into customer_payment  values(?,?,?,?)";
                                                                ps5 = con.prepareStatement(insert_Custom);
                                                                ps5.setString(1, name);
                                                                ps5.setLong(2, oldc_num_used);
                                                                ps5.setInt(3, id);
                                                                ps5.setString(4, email_addr);
                                                                ps5.executeUpdate();
                                                            }


                                                            //insert vehicle, v_packages, vehicle_options if there are chrome wheels or hybrid options,
                                                            //customer_buys_new , v_model, and new_vehicle
                                                            insert_vehicles = "insert into vehicle values(?)";
                                                            ps5 = con.prepareStatement(insert_vehicles);
                                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                                            ps5.executeUpdate();

                                                            insert_package="insert into v_packages values(?,?)";
                                                            ps5 = con.prepareStatement(insert_package);
                                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                                            ps5.setInt(2, package_ch);
                                                            ps5.executeUpdate();

                                                            //insert into vehicle_options
                                                            if(priceofCh !=0){
                                                                CallableStatement stmt2 = con.prepareCall("{call options_to_have(?,?,?)}");
                                                                stmt2.setBigDecimal(1, new BigDecimal(new_vid));
                                                                stmt2.setString(2, "Chrome wheels 20” or Larger");
                                                                stmt2.setInt(3, priceofCh);
                                                                boolean hadResults2 = stmt2.execute();

                                                                while (hadResults2) {
                                                                    ResultSet rs = stmt2.getResultSet();

                                                                    // process result set

                                                                    hadResults2 = stmt2.getMoreResults();
                                                                }
                                                            }
                                                            if(priceofHy !=0){
                                                                CallableStatement stmt2 = con.prepareCall("{call options_to_have(?,?,?)}");
                                                                stmt2.setBigDecimal(1, new BigDecimal(new_vid));
                                                                stmt2.setString(2, "Hybrid ");
                                                                stmt2.setInt(3, priceofHy);
                                                                boolean hadResults2 = stmt2.execute();

                                                                while (hadResults2) {
                                                                    ResultSet rs = stmt2.getResultSet();

                                                                    // process result set

                                                                    hadResults2 = stmt2.getMoreResults();
                                                                }
                                                            }
                                                            String insert_cust_buys_new = "insert into customer_buys_new values(?,?,?,?,?)";
                                                            ps5 = con.prepareStatement(insert_cust_buys_new);
                                                            ps5.setString(1, name);
                                                            ps5.setInt(2, id);
                                                            ps5.setBigDecimal(3,new BigDecimal(new_vid));
                                                            ps5.setString(4, email_addr);
                                                            ps5.setBigDecimal(5,totalpricepaid);
                                                            ps5.executeUpdate();

                                                            String insert_vmodel = "insert into v_model values(?,?,?)";
                                                            ps5 = con.prepareStatement(insert_vmodel);
                                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                                            ps5.setInt(2, initialModelID);
                                                            ps5.setString(3, initialModelName);
                                                            ps5.executeUpdate();

                                                            String insert_newvehicle = "insert into new_vehicle values(?,?,?)";
                                                            ps5 = con.prepareStatement(insert_newvehicle);
                                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                                            ps5.setString(2, "Moon Exploration");
                                                            ps5.setString(3, initialModelType);
                                                            ps5.executeUpdate();
                                                            System.out.println("Order has been submitted.");
                                                            System.out.println("Your order will be available for pickup at the Moon Exploration center.");

                                                        }
                                                        else if(final_decision.equals("no")){
                                                            System.out.println("No new car was purchased.");
                                                            ab =1;
                                                            //exit out of here
                                                        }
                                                        else{
                                                            System.out.print("We did not understand what you had typed. Please type in confirm to place your order."
                                                                    + "\nOtherwise type in no to opt of ordering. ");
                                                            final_decision = krb.next();
                                                            final_decision = final_decision.toLowerCase();
                                                        }
                                                    }
                                                }
                                                else if(model_selected.equals("u")){
                                                    String car_query = "select * from model where model_type=?";
                                                    ps5 = con.prepareStatement(car_query);
                                                    ps5.setString(1,"Model U");
                                                    result2 = ps5.executeQuery();

                                                    while(result2.next()){
                                                        System.out.println();
                                                        System.out.println("Model ID: " + result2.getInt("m_id")+"\nModel name: "+ result2.getString("modelname")+
                                                        "\nYear: " + result2.getInt("year") + "\nModel Type: " + result2.getString("model_type") +
                                                        "\nPrice: $" + result2.getBigDecimal("list_price"));
                                                    }
                                                    if(!result2.next()){
                                                        System.out.println("That is all of the Model U vehicles being sold right now.");
                                                    }
                                                    //Next have them enter the name of the vehicle and year they want to get.
                                                    System.out.println("Please enter the model id of the vehicle you wish to purchase: ");
                                                    System.out.print("Model ID: ");
                                                    int car_wish_to_own  =0;
                                                    while (!krb.hasNextInt()) { 
                                                        System.out.println("only numbers! Type in the model id. "); 
                                                        System.out.print("Enter your model id number: ");
                                                        krb.next(); // discard 
                                                    }
                                                    car_wish_to_own = krb.nextInt();
                                                    krb.nextLine();

                                                    int z = 0;
                                                    int t = 0;
                                                    while ( z ==0){
                                                        String specific_model = "select * from model where m_id = ? and model_type = ?";
                                                        ps5 = con.prepareStatement(specific_model);
                                                        ps5.setInt(1,car_wish_to_own);
                                                        ps5.setString(2, "Model U");

                                                        result4 = ps5.executeQuery();
                                                        while(result4.next()){
                                                            z = 1;t = 1;
                                                        }

                                                        while( t == 0 && !result4.next()){
                                                            z=0;
                                                            System.out.print("We don't recognize the id that you wrote. If you wish to exit, please type in exit. \n"
                                                                    + "Otherwise type in any other character to continue: ");
                                                            String dec = krb.next();
                                                            dec =dec.toLowerCase();
                                                            if(dec.equals("exit")){
                                                                return;
                                                            }
                                                            else{
                                                                System.out.print("Please enter a model id of the vehicle you wish to purchase: ");
                                                                while (!krb.hasNextInt()) { 
                                                                    System.out.println("only numbers! Type in the model id. "); 
                                                                    System.out.print("Enter your model id number: ");
                                                                    krb.next(); // discard 
                                                                }
                                                                car_wish_to_own = krb.nextInt();
                                                                specific_model = "select * from model where m_id = ? and model_type = ?";
                                                                ps5 = con.prepareStatement(specific_model);
                                                                ps5.setInt(1,car_wish_to_own);
                                                                ps5.setString(2, "Model U");
                                                            }
                                                            result4 = ps5.executeQuery();
                                                        }
                                                    }



                                                    String specific_model = "select * from model where m_id = ? and model_type = ? ";
                                                    ps5 = con.prepareStatement(specific_model);
                                                    ps5.setInt(1,car_wish_to_own);
                                                    ps5.setString(2,"Model U");

                                                    result4 = ps5.executeQuery();
                                                    while(result4.next()){
                                                        System.out.println("The base price of this vehicle" + " the "+
                                                                result4.getString("modelname")+ " is $"+result4.getBigDecimal("list_price"));
                                                        baseprice = result4.getBigDecimal("list_price");
                                                        initialPrice = baseprice;
                                                        initialModelID= result4.getInt("M_ID");
                                                        initialModelName =result4.getString("modelname");
                                                        initialModelYear = result4.getInt("YEAR");
                                                        initialModelType = result4.getString("MODEL_TYPE");
                                                    }

                                                    System.out.println();

                                                    System.out.println("Do you want to purchase a package? The packages available with Model U are:"
                                                            + "\nthe Luxury Package, which determines your vehicle to be as cozy as classy to your liking."
                                                            + "\nFinally, you can purchase the Amenities package which guarantees you a comfortable experience "
                                                            + "in your new undersea vehicle.");
                                                    System.out.println();

                                                    String print_out_options = "WITH RESULT AS ( select option_name as options_name,package_id from options_in_packages where package_id=3 ) SELECT * FROM OPTIONS NATURAL JOIN RESULT";
                                                    ps5 = con.prepareStatement(print_out_options);
                                                    result4 = ps5.executeQuery();
                                                    System.out.println("The Luxury package includes: ");
                                                    while(result4.next()){
                                                        System.out.println("a " + result4.getString("OPTIONS_NAME") + ": " + result4.getString("DESCRIPTION") );
                                                    }
                                                    System.out.println();
                                                    print_out_options = "WITH RESULT AS ( select option_name as options_name,package_id from options_in_packages where package_id=2 ) SELECT * FROM OPTIONS NATURAL JOIN RESULT";
                                                    ps5 = con.prepareStatement(print_out_options);
                                                    result4 = ps5.executeQuery();
                                                    System.out.println("The Amenities package includes: ");
                                                    while(result4.next()){
                                                        System.out.println("a " + result4.getString("OPTIONS_NAME") + ": " + result4.getString("DESCRIPTION") );
                                                    }

                                                    System.out.print("\nwhich package do you wish to purchase? "
                                                            + "Type in luxury for the luxury package, or type in amenities for the amenities package."
                                                            + "\nThe Amenities package is chosen for you already but you can upgrade it to the luxury package."
                                                            + ": ");
                                                    String decision = "";
                                                    decision = krb.next();
                                                    decision = decision.toLowerCase();

                                                    int exit_loop = 0;
                                                    String insert_package;
                                                    String insert_vehicles;
                                                    String package_chosen="";

                                                    while( exit_loop==0 ){
                                                        if(decision.equals("luxury")){
                                                            exit_loop=1;
                                                            wrong_key2=1;
                                                            System.out.println("luxury");
                                                            package_chosen = decision;
                                                        }
                                                        else if(decision.equals("amenities")){
                                                            exit_loop=1;
                                                            wrong_key2=1;
                                                            System.out.println("amenities");
                                                            package_chosen = decision;
                                                        }
                                                        else{
                                                            System.out.print("Did you mean to type that? type in amenities to exit out of choosing an upgrade in packages.\n"

                                                                    + "Otherwise type in your preferred package upgrade of luxury: ");
                                                            decision = krb.next();
                                                            decision = decision.toLowerCase();
                                                            if(decision.equals("amenities")){
                                                                exit_loop=1;
                                                                wrong_key2=1;
                                                                package_chosen = "amenities";
                                                            }
                                                            else{
                                                                exit_loop=0;
                                                            }
                                                        }
                                                    }

                                                    String obtain_package_price ="";
                                                    pricePack = null;

                                                    if(package_chosen.equals("luxury")){
                                                        package_ch =3;
                                                        obtain_package_price = "select * from packages where package_id = ? ";
                                                        ps5 = con.prepareStatement(obtain_package_price);
                                                        ps5.setInt(1, package_ch);
                                                        result4=ps5.executeQuery();
                                                        while(result4.next()){
                                                            pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                                        }
                                                    }
                                                    else if(package_chosen.equals("amenities") || package_ch ==2){
                                                        package_ch =2;
                                                        obtain_package_price = "select * from packages where package_id = ? ";
                                                        ps5 = con.prepareStatement(obtain_package_price);
                                                        ps5.setInt(1, package_ch);
                                                        result4=ps5.executeQuery();
                                                        while(result4.next()){
                                                            pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                                        }
                                                    }
                                                    packageSelected = package_chosen;
                                                    totalpricepaid = baseprice;

                                                    if(package_ch == 2 ){
                                                        //the amenities package was selected for them or they chose it.
                                                        //You can choose to add the hybrid option as well.
                                                        totalpricepaid = totalpricepaid.add(pricePack);
                                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                                        System.out.print("We have a hybrid option available for this vehicle. "
                                                                + "Do you want to know more about it? ");
                                                        String op_decision = krb.next();
                                                        op_decision = op_decision.toLowerCase();
                                                        int op_d = 0;
                                                        while(op_d == 0){
                                                            if(op_decision.equals("yes")){
                                                                op_d =1;
                                                                String choose_hybrid = "SELECT * FROM OPTIONS where options_name = ?";
                                                                ps5 = con.prepareStatement(choose_hybrid);
                                                                ps5.setString(1, "Hybrid ");
                                                                result4 = ps5.executeQuery();
                                                                priceofHy=0;
                                                                String op_name;
                                                                String op_descr;
                                                                while(result4.next()){
                                                                    System.out.println(result4.getString("OPTIONS_NAME") + "$" +result4.getInt("LIST_PRICE") + " " + result4.getString("DESCRIPTION"));
                                                                    priceofHy = result4.getInt("LIST_PRICE");
                                                                    op_name = result4.getString("OPTIONS_NAME");
                                                                    op_descr = result4.getString("DESCRIPTION");
                                                                }
                                                                System.out.print("Do you want to purchase this option? ");
                                                                String purchase_hy = krb.next();
                                                                purchase_hy = purchase_hy.toLowerCase();
                                                                int a = 0;
                                                                while( a == 0){
                                                                    if(purchase_hy.equals("yes")){
                                                                        //optionSelected[0]= "Hybrid";
                                                                        optionSelected.add("Hybrid");
                                                                        a =1 ;
                                                                        totalpricepaid = totalpricepaid.add(new BigDecimal(priceofHy));
                                                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                                                    }
                                                                    else if(purchase_hy.equals("no")){
                                                                      a =1 ;
                                                                      System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);

                                                                    }
                                                                    else{
                                                                        a=0;
                                                                        System.out.print("We did not get what you were trying to say. Can you enter yes "
                                                                                + "if you want to purchase the hybrid option, otherwise say no. ");
                                                                        purchase_hy = krb.next();
                                                                        purchase_hy = purchase_hy.toLowerCase();
                                                                    }
                                                                }
                                                            }
                                                            else if(op_decision.equals("no") || op_decision.equals("exit")){
                                                              op_d =1;

                                                            }
                                                            else{
                                                                op_d=0;
                                                                System.out.println("We did not understand what you had typed. If you want to exit, type in exit."
                                                                        + "\nIf you want to know more about the hybrid type in yes. ");
                                                                op_decision = krb.next();
                                                                op_decision = op_decision.toLowerCase();
                                                            }
                                                        }
                                                    }
                                                    else{
                                                        //they chose a package other than the amenities package.
                                                        //totalpricepaid= pricePack + totalpricepaid;
                                                        totalpricepaid = totalpricepaid.add(pricePack);
                                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                                    }
                                                    System.out.println();

                                                    //list a summary of the model that the customer is about to purchase.
                                                    //ask to confirm if this correct. if they say yes, the records will all be updated.
                                                    a.SummaryInfo();


                                                    if(oldc_num != 0 ){
                                                        System.out.println("Card Info: " + oldc_num);
                                                    }
                                                    else if( oldc_num_used != 0){
                                                        System.out.println("Card Info: " + oldc_num_used);
                                                    }
                                                    System.out.print("Is this correct? Type in confirm to place your order. Otherwise type in no to opt out of ordering: ");
                                                    String final_decision = krb.next();
                                                    final_decision = final_decision.toLowerCase();
                                                    int ab=0;

                                                    while (ab ==0){
                                                        if(final_decision.equals("confirm")){
                                                            ab =1;
                                                            //do all my inserts here.
                                                            if(oldc_num == 0 && oldc_num_used != 0 ){
                                                                String insert_Custom = "insert into customer_payment  values(?,?,?,?)";
                                                                ps5 = con.prepareStatement(insert_Custom);
                                                                ps5.setString(1, name);
                                                                ps5.setLong(2, oldc_num_used);
                                                                ps5.setInt(3, id);
                                                                ps5.setString(4, email_addr);
                                                                ps5.executeUpdate();
                                                            }
                                                            //insert vehicle, v_packages, vehicle_options if there are chrome wheels or hybrid options,
                                                            //customer_buys_new , v_model, and new_vehicle
                                                            insert_vehicles = "insert into vehicle values(?)";
                                                            ps5 = con.prepareStatement(insert_vehicles);
                                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                                            ps5.executeUpdate();

                                                            insert_package="insert into v_packages values(?,?)";
                                                            ps5 = con.prepareStatement(insert_package);
                                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                                            ps5.setInt(2, package_ch);
                                                            ps5.executeUpdate();

                                                            //insert into vehicle_options
                                                            if(priceofHy !=0){
                                                                CallableStatement stmt2 = con.prepareCall("{call options_to_have(?,?,?)}");
                                                                stmt2.setBigDecimal(1, new BigDecimal(new_vid));
                                                                stmt2.setString(2, "Hybrid ");
                                                                stmt2.setInt(3, priceofHy);
                                                                boolean hadResults2 = stmt2.execute();

                                                                while (hadResults2) {
                                                                    ResultSet rs = stmt2.getResultSet();

                                                                    // process result set

                                                                    hadResults2 = stmt2.getMoreResults();
                                                                }
                                                            }

                                                            String insert_cust_buys_new = "insert into customer_buys_new values(?,?,?,?,?)";
                                                            ps5 = con.prepareStatement(insert_cust_buys_new);
                                                            ps5.setString(1, name);
                                                            ps5.setInt(2, id);
                                                            ps5.setBigDecimal(3,new BigDecimal(new_vid));
                                                            ps5.setString(4, email_addr);
                                                            ps5.setBigDecimal(5,totalpricepaid);
                                                            ps5.executeUpdate();

                                                            String insert_vmodel = "insert into v_model values(?,?,?)";
                                                            ps5 = con.prepareStatement(insert_vmodel);
                                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                                            ps5.setInt(2, initialModelID);
                                                            ps5.setString(3, initialModelName);
                                                            ps5.executeUpdate();

                                                            String insert_newvehicle = "insert into new_vehicle values(?,?,?)";
                                                            ps5 = con.prepareStatement(insert_newvehicle);
                                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                                            ps5.setString(2, "Pacific Overlook");
                                                            ps5.setString(3, initialModelType);
                                                            ps5.executeUpdate();
                                                            System.out.println("The order has been submitted.");
                                                            System.out.println("Your order will be available for pickup at the Pacific Overlook center.");
                                                        }

                                                        else if(final_decision.equals("no")){
                                                            System.out.println("No new car was purchased.");
                                                            ab =1;
                                                            //exit out of here
                                                        }
                                                        else{
                                                            System.out.print("We did not understand what you had typed. Please type in confirm to place your order."
                                                                    + "\nOtherwise type in no to opt of ordering: ");
                                                            final_decision = krb.next();
                                                            final_decision = final_decision.toLowerCase();
                                                        }
                                                    }

                                                }
                                                else if(model_selected.equals("s")){
                                                   String car_query = "select * from model where model_type=?";
                                                    ps5 = con.prepareStatement(car_query);
                                                    ps5.setString(1,"Model S");
                                                    result2 = ps5.executeQuery();

                                                    while(result2.next()){
                                                        System.out.println();
                                                        System.out.println("Model ID: " + result2.getInt("m_id")+"\nModel name: "+ result2.getString("modelname")+
                                                        "\nYear: " + result2.getInt("year") + "\nModel Type: " + result2.getString("model_type") +
                                                        "\nPrice: $" + result2.getBigDecimal("list_price"));
                                                    }
                                                    if(!result2.next()){
                                                        System.out.println("That is all of the Model S vehicles being sold right now.");
                                                    } 

                                                    //Next have them enter the name of the vehicle and year they want to get.
                                                    System.out.println("Please enter the model id of the vehicle you wish to purchase: ");
                                                    System.out.print("Model ID: ");
                                                    int car_wish_to_own  =0;
                                                    while (!krb.hasNextInt()) { 
                                                        System.out.println("only numbers! Type in the model id. "); 
                                                        System.out.print("Enter your model id number: ");
                                                        krb.next(); // discard 
                                                    }
                                                    car_wish_to_own = krb.nextInt();
                                                    krb.nextLine();

                                                    int z = 0;
                                                    int t = 0;
                                                    while ( z ==0){
                                                        String specific_model = "select * from model where m_id = ? and model_type = ?";
                                                        ps5 = con.prepareStatement(specific_model);
                                                        ps5.setInt(1,car_wish_to_own);
                                                        ps5.setString(2, "Model S");

                                                        result4 = ps5.executeQuery();
                                                        while(result4.next()){
                                                            z = 1;t = 1;
                                                        }

                                                        while( t == 0 && !result4.next()){
                                                            z=0;
                                                            System.out.print("We don't recognize the id that you wrote. If you wish to exit, please type in exit. \n"
                                                                    + "Otherwise type in any other character to continue: ");
                                                            String dec = krb.next();
                                                            dec =dec.toLowerCase();
                                                            if(dec.equals("exit")){
                                                                return;
                                                            }
                                                            else{
                                                                System.out.print("Please enter a model id of the vehicle you wish to purchase: ");
                                                                while (!krb.hasNextInt()) { 
                                                                    System.out.println("only numbers! Type in the model id. "); 
                                                                    System.out.print("Enter your model id number: ");
                                                                    krb.next(); // discard 
                                                                }
                                                                car_wish_to_own = krb.nextInt();
                                                                specific_model = "select * from model where m_id = ? and model_type = ?";
                                                                ps5 = con.prepareStatement(specific_model);
                                                                ps5.setInt(1,car_wish_to_own);
                                                                ps5.setString(2, "Model S");
                                                            }
                                                            result4 = ps5.executeQuery();
                                                        }
                                                    }

                                                    String specific_model = "select * from model where m_id = ? and model_type = ? ";
                                                    ps5 = con.prepareStatement(specific_model);
                                                    ps5.setInt(1,car_wish_to_own);
                                                    ps5.setString(2, "Model S");

                                                    result4 = ps5.executeQuery();

                                                    while(result4.next()){
                                                        System.out.println("The base price of this vehicle" + " the "+
                                                                result4.getString("modelname")+ " is $"+result4.getBigDecimal("list_price"));
                                                        baseprice = result4.getBigDecimal("list_price");
                                                        initialPrice = baseprice;
                                                        initialModelID= result4.getInt("M_ID");
                                                        initialModelName =result4.getString("modelname");
                                                        initialModelYear = result4.getInt("YEAR");
                                                        initialModelType = result4.getString("MODEL_TYPE");
                                                    }

                                                    System.out.println();

                                                    System.out.println("Do you want to purchase a package? The packages available with Model U are:"
                                                            + "\nthe Luxury Package, which determines your vehicle to be as cozy as classy to your liking."
                                                            + "\nFinally, you can purchase the Amenities package which guarantees you a comfortable experience "
                                                            + "in your new undersea vehicle.");
                                                    System.out.println();

                                                    String print_out_options = "WITH RESULT AS ( select option_name as options_name,package_id from options_in_packages where package_id=3 ) SELECT * FROM OPTIONS NATURAL JOIN RESULT";
                                                    ps5 = con.prepareStatement(print_out_options);
                                                    result4 = ps5.executeQuery();
                                                    System.out.println("The Luxury package includes: ");
                                                    while(result4.next()){
                                                        System.out.println("a " + result4.getString("OPTIONS_NAME") + ": " + result4.getString("DESCRIPTION") );
                                                    }
                                                    System.out.println();
                                                    print_out_options = "WITH RESULT AS ( select option_name as options_name,package_id from options_in_packages where package_id=2 ) SELECT * FROM OPTIONS NATURAL JOIN RESULT";
                                                    ps5 = con.prepareStatement(print_out_options);
                                                    result4 = ps5.executeQuery();
                                                    System.out.println("The Amenities package includes: ");
                                                    while(result4.next()){
                                                        System.out.println("a " + result4.getString("OPTIONS_NAME") + ": " + result4.getString("DESCRIPTION") );
                                                    }

                                                    System.out.print("\nwhich package do you wish to purchase? "
                                                            + "Type in luxury for the luxury package, or type in amenities for the amenities package."
                                                            + "\nThe Amenities package is chosen for you already but you can upgrade it to the luxury package."
                                                            + ": ");
                                                    String decision = "";
                                                    decision = krb.next();
                                                    decision = decision.toLowerCase();

                                                    int exit_loop = 0;
                                                    String insert_package;
                                                    String insert_vehicles;
                                                    String package_chosen="";

                                                    while( exit_loop==0 ){
                                                        if(decision.equals("luxury")){
                                                            exit_loop=1;
                                                            wrong_key2=1;
                                                            System.out.println("luxury");
                                                            package_chosen = decision;
                                                        }
                                                        else if(decision.equals("amenities")){
                                                            exit_loop=1;
                                                            wrong_key2=1;
                                                            System.out.println("amenities");
                                                            package_chosen = decision;
                                                        }
                                                        else{
                                                            System.out.print("Did you mean to type that? type in amenities to exit out of choosing an upgrade in packages.\n"

                                                                    + "Otherwise type in your preferred package upgrade of luxury: ");
                                                            decision = krb.next();
                                                            decision = decision.toLowerCase();
                                                            if(decision.equals("amenities")){
                                                                exit_loop=1;
                                                                wrong_key2=1;
                                                                package_chosen = "amenities";
                                                            }
                                                            else{
                                                                exit_loop=0;
                                                            }
                                                        }
                                                    }

                                                    String obtain_package_price ="";
                                                    pricePack = null;

                                                    if(package_chosen.equals("luxury")){
                                                        package_ch =3;
                                                        obtain_package_price = "select * from packages where package_id = ? ";
                                                        ps5 = con.prepareStatement(obtain_package_price);
                                                        ps5.setInt(1, package_ch);
                                                        result4=ps5.executeQuery();
                                                        while(result4.next()){
                                                            pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                                        }
                                                    }
                                                    else if(package_chosen.equals("amenities") || package_ch ==2){
                                                        package_ch =2;
                                                        obtain_package_price = "select * from packages where package_id = ? ";
                                                        ps5 = con.prepareStatement(obtain_package_price);
                                                        ps5.setInt(1, package_ch);
                                                        result4=ps5.executeQuery();
                                                        while(result4.next()){
                                                            pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                                        }
                                                    }
                                                    packageSelected = package_chosen;
                                                    totalpricepaid = baseprice;

                                                    if(package_ch == 2 ){
                                                        //the amenities package was selected for them or they chose it.
                                                        //You can choose to add the hybrid option as well.
                                                        totalpricepaid = totalpricepaid.add(pricePack);
                                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                                        System.out.print("We have a hybrid option available for this vehicle. "
                                                                + "Do you want to know more about it? ");
                                                        String op_decision = krb.next();
                                                        op_decision = op_decision.toLowerCase();
                                                        int op_d = 0;
                                                        while(op_d == 0){
                                                            if(op_decision.equals("yes")){
                                                                op_d =1;
                                                                String choose_hybrid = "SELECT * FROM OPTIONS where options_name = ?";
                                                                ps5 = con.prepareStatement(choose_hybrid);
                                                                ps5.setString(1, "Hybrid ");
                                                                result4 = ps5.executeQuery();
                                                                priceofHy=0;
                                                                String op_name;
                                                                String op_descr;
                                                                while(result4.next()){
                                                                    System.out.println(result4.getString("OPTIONS_NAME") +"$"+ result4.getInt("LIST_PRICE") + " " + result4.getString("DESCRIPTION"));
                                                                    priceofHy = result4.getInt("LIST_PRICE");
                                                                    op_name = result4.getString("OPTIONS_NAME");
                                                                    op_descr = result4.getString("DESCRIPTION");
                                                                }
                                                                System.out.print("Do you want to purchase this option? ");
                                                                String purchase_hy = krb.next();
                                                                purchase_hy = purchase_hy.toLowerCase();
                                                                int a = 0;
                                                                while( a == 0){
                                                                    if(purchase_hy.equals("yes")){
                                                                        //optionSelected[0]= "Hybrid";
                                                                        optionSelected.add("Hybrid");
                                                                        a =1 ;
                                                                        totalpricepaid = totalpricepaid.add(new BigDecimal(priceofHy));
                                                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                                                    }
                                                                    else if(purchase_hy.equals("no")){
                                                                      a =1 ;
                                                                      System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);

                                                                    }
                                                                    else{
                                                                        a=0;
                                                                        System.out.print("We did not get what you were trying to say. Can you enter yes "
                                                                                + "if you want to purchase the hybrid option, otherwise say no. ");
                                                                        purchase_hy = krb.next();
                                                                        purchase_hy = purchase_hy.toLowerCase();
                                                                    }
                                                                }
                                                            }
                                                            else if(op_decision.equals("no") || op_decision.equals("exit")){
                                                              op_d =1;

                                                            }
                                                            else{
                                                                op_d=0;
                                                                System.out.println("We did not understand what you had typed. If you want to exit, type in exit."
                                                                        + "\nIf you want to know more about the hybrid type in yes. ");
                                                                op_decision = krb.next();
                                                                op_decision = op_decision.toLowerCase();
                                                            }
                                                        }
                                                    }

                                                    else{
                                                        //they chose a package other than the amenities package.
                                                        //totalpricepaid= pricePack + totalpricepaid;
                                                        totalpricepaid = totalpricepaid.add(pricePack);
                                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                                    }
                                                    System.out.println();

                                                    //list a summary of the model that the customer is about to purchase.
                                                    //ask to confirm if this correct. if they say yes, the records will all be updated.
                                                    a.SummaryInfo();

                                                    if(oldc_num != 0 ){
                                                        System.out.println("Card Info: " + oldc_num);
                                                    }
                                                    else if( oldc_num_used != 0){
                                                        System.out.println("Card Info: " + oldc_num_used);
                                                    }

                                                    System.out.print("Is this correct? Type in confirm to place your order. Otherwise type in no to opt out of ordering. ");
                                                    String final_decision = krb.next();
                                                    final_decision = final_decision.toLowerCase();
                                                    int ab=0;

                                                    while (ab ==0){
                                                        if(final_decision.equals("confirm")){
                                                            ab =1;
                                                            //do all my inserts here.
                                                            if(oldc_num == 0 && oldc_num_used != 0 ){
                                                                String insert_Custom = "insert into customer_payment  values(?,?,?,?)";
                                                                ps5 = con.prepareStatement(insert_Custom);
                                                                ps5.setString(1, name);
                                                                ps5.setLong(2, oldc_num_used);
                                                                ps5.setInt(3, id);
                                                                ps5.setString(4, email_addr);
                                                                ps5.executeUpdate();
                                                            }
                                                            //insert vehicle, v_packages, vehicle_options if there are chrome wheels or hybrid options,
                                                            //customer_buys_new , v_model, and new_vehicle
                                                            insert_vehicles = "insert into vehicle values(?)";
                                                            ps5 = con.prepareStatement(insert_vehicles);
                                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                                            ps5.executeUpdate();

                                                            insert_package="insert into v_packages values(?,?)";
                                                            ps5 = con.prepareStatement(insert_package);
                                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                                            ps5.setInt(2, package_ch);
                                                            ps5.executeUpdate();

                                                            //insert into vehicle_options
                                                            if(priceofHy !=0){
                                                                CallableStatement stmt2 = con.prepareCall("{call options_to_have(?,?,?)}");
                                                                stmt2.setBigDecimal(1, new BigDecimal(new_vid));
                                                                stmt2.setString(2, "Hybrid ");
                                                                stmt2.setInt(3, priceofHy);
                                                                boolean hadResults2 = stmt2.execute();

                                                                while (hadResults2) {
                                                                    ResultSet rs = stmt2.getResultSet();

                                                                    // process result set

                                                                    hadResults2 = stmt2.getMoreResults();
                                                                }
                                                            }

                                                            String insert_cust_buys_new = "insert into customer_buys_new values(?,?,?,?,?)";
                                                            ps5 = con.prepareStatement(insert_cust_buys_new);
                                                            ps5.setString(1, name);
                                                            ps5.setInt(2, id);
                                                            ps5.setBigDecimal(3,new BigDecimal(new_vid));
                                                            ps5.setString(4, email_addr);
                                                            ps5.setBigDecimal(5,totalpricepaid);
                                                            ps5.executeUpdate();

                                                            String insert_vmodel = "insert into v_model values(?,?,?)";
                                                            ps5 = con.prepareStatement(insert_vmodel);
                                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                                            ps5.setInt(2, initialModelID);
                                                            ps5.setString(3, initialModelName);
                                                            ps5.executeUpdate();

                                                            String insert_newvehicle = "insert into new_vehicle values(?,?,?)";
                                                            ps5 = con.prepareStatement(insert_newvehicle);
                                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                                            ps5.setString(2, "Mars Bars Center");
                                                            ps5.setString(3, initialModelType);
                                                            ps5.executeUpdate();
                                                            System.out.println("The order has been submitted.");
                                                            System.out.println("Your order will be available for pickup at the Mars Bars Center center.");
                                                        }

                                                        else if(final_decision.equals("no")){
                                                            System.out.println("No new car was purchased.");
                                                            ab =1;
                                                            //exit out of here
                                                        }
                                                        else{
                                                            System.out.print("We did not understand what you had typed. Please type in confirm to place your order."
                                                                    + "\nOtherwise type in no to opt of ordering. ");
                                                            final_decision = krb.next();
                                                            final_decision = final_decision.toLowerCase();
                                                        }

                                                    }
                                                }
                                                else if(model_selected.equals("k")){
                                                    String car_query = "select * from model where model_type=?";
                                                    ps5 = con.prepareStatement(car_query);
                                                    ps5.setString(1,"Model K");
                                                    result2 = ps5.executeQuery();

                                                    while(result2.next()){
                                                        System.out.println();
                                                        System.out.println("Model ID: " + result2.getInt("m_id")+"\nModel name: "+ result2.getString("modelname")+
                                                        "\nYear: " + result2.getInt("year") + "\nModel Type: " + result2.getString("model_type") +
                                                        "\nPrice: $" + result2.getBigDecimal("list_price"));
                                                    }
                                                    if(!result2.next()){
                                                        System.out.println("That is all of the Model K vehicles being sold right now.");
                                                    }

                                                    //Next have them enter the name of the vehicle and year they want to get.
                                                    System.out.println("Please enter the model id of the vehicle you wish to purchase: ");
                                                    System.out.print("Model ID: ");
                                                    int car_wish_to_own  =0;
                                                    while (!krb.hasNextInt()) { 
                                                        System.out.println("only numbers! Type in the model id. "); 
                                                        System.out.print("Enter your model id number: ");
                                                        krb.next(); // discard 
                                                    }
                                                    car_wish_to_own = krb.nextInt();
                                                    krb.nextLine();
                                                    int z = 0;
                                                    int t = 0;
                                                    while ( z ==0){
                                                        String specific_model = "select * from model where m_id = ? and model_type = ?";
                                                        ps5 = con.prepareStatement(specific_model);
                                                        ps5.setInt(1,car_wish_to_own);
                                                        ps5.setString(2, "Model K");

                                                        result4 = ps5.executeQuery();
                                                        while(result4.next()){
                                                            z = 1;t = 1;
                                                        }

                                                        while( t == 0 && !result4.next()){
                                                            z=0;
                                                            System.out.print("We don't recognize the id that you wrote. If you wish to exit, please type in exit. \n"
                                                                    + "Otherwise type in any other character to continue: ");
                                                            String dec = krb.next();
                                                            dec =dec.toLowerCase();
                                                            if(dec.equals("exit")){
                                                                return;
                                                            }
                                                            else{
                                                                System.out.print("Please enter a model id of the vehicle you wish to purchase: ");
                                                                while (!krb.hasNextInt()) { 
                                                                    System.out.println("only numbers! Type in the model id. "); 
                                                                    System.out.print("Enter your model id number: ");
                                                                    krb.next(); // discard 
                                                                }
                                                                car_wish_to_own = krb.nextInt();
                                                                specific_model = "select * from model where m_id = ? and model_type = ?";
                                                                ps5 = con.prepareStatement(specific_model);
                                                                ps5.setInt(1,car_wish_to_own);
                                                                ps5.setString(2, "Model K");
                                                            }
                                                            result4 = ps5.executeQuery();
                                                        }
                                                    }


                                                    String specific_model = "select * from model where m_id = ? and model_type = ? ";
                                                    ps5 = con.prepareStatement(specific_model);
                                                    ps5.setInt(1,car_wish_to_own);
                                                    ps5.setString(2, "Model K");

                                                    result4 = ps5.executeQuery();
                                                    while(result4.next()){
                                                        System.out.println("The base price of this vehicle" + " the "+
                                                                result4.getString("modelname")+ " is $"+result4.getBigDecimal("list_price"));
                                                        baseprice = result4.getBigDecimal("list_price");
                                                        initialPrice = baseprice;
                                                        initialModelID= result4.getInt("M_ID");
                                                        initialModelName =result4.getString("modelname");
                                                        initialModelYear = result4.getInt("YEAR");
                                                        initialModelType = result4.getString("MODEL_TYPE");

                                                    }

                                                    System.out.println();

                                                    System.out.println("Do you want to purchase a package? The packages available with Model K are:"
                                                            + "\nthe Superman Package, which outfits your vehicle as a large truck able to navigate "
                                                            + "the roads of our native planet Earth. "
                                                            + "\nthe Luxury Package, which determines your vehicle to be as cozy as classy to your liking."
                                                            + "\nFinally, you can purchase the Amenities package which guarantees you a comfortable experience "
                                                            + "in your new car.");
                                                    System.out.println();
                                                    String print_out_options = "WITH RESULT AS ( select option_name as options_name,package_id from options_in_packages where package_id=1 ) SELECT * FROM OPTIONS NATURAL JOIN RESULT";
                                                    ps5 = con.prepareStatement(print_out_options);
                                                    result4 = ps5.executeQuery();
                                                    System.out.println("The Superman package includes: ");
                                                    while(result4.next()){
                                                        System.out.println("a " + result4.getString("OPTIONS_NAME") + ": " + result4.getString("DESCRIPTION") );
                                                    }
                                                    System.out.println();
                                                    print_out_options = "WITH RESULT AS ( select option_name as options_name,package_id from options_in_packages where package_id=3 ) SELECT * FROM OPTIONS NATURAL JOIN RESULT";
                                                    ps5 = con.prepareStatement(print_out_options);
                                                    result4 = ps5.executeQuery();
                                                    System.out.println("The Luxury package includes: ");
                                                    while(result4.next()){
                                                        System.out.println("a " + result4.getString("OPTIONS_NAME") + ": " + result4.getString("DESCRIPTION") );
                                                    }
                                                    System.out.println();
                                                    print_out_options = "WITH RESULT AS ( select option_name as options_name,package_id from options_in_packages where package_id=2 ) SELECT * FROM OPTIONS NATURAL JOIN RESULT";
                                                    ps5 = con.prepareStatement(print_out_options);
                                                    result4 = ps5.executeQuery();
                                                    System.out.println("The Amenities package includes: ");
                                                    while(result4.next()){
                                                        System.out.println("a " + result4.getString("OPTIONS_NAME") + ": " + result4.getString("DESCRIPTION") );
                                                    }

                                                    System.out.print("\nwhich package do you wish to purchase? Type in superman for the superman package"
                                                            + ", type in luxury for the luxury package, or type in amenities for the amenities package."
                                                            + "\nThe Amenities package is chosen for you already but you can upgrade it to the luxury or superman package."
                                                            + ": ");
                                                    String decision = "";
                                                    decision = krb.next();
                                                    decision = decision.toLowerCase();
                                                    int exit_loop = 0;
                                                    String insert_package;
                                                    String insert_vehicles;
                                                    String package_chosen="";
                                                    while( exit_loop==0 ){
                                                        if(decision.equals("superman")){
                                                            exit_loop=1;
                                                            wrong_key2=1;
                                                            System.out.println("superman");
                                                            package_chosen = decision;
                                                        }
                                                        else if(decision.equals("luxury")){
                                                            exit_loop=1;
                                                            wrong_key2=1;
                                                            System.out.println("luxury");
                                                            package_chosen = decision;
                                                        }
                                                        else if(decision.equals("amenities")){
                                                            exit_loop=1;
                                                            wrong_key2=1;
                                                            System.out.println("amenities");
                                                            package_chosen = decision;
                                                        }

                                                        else{
                                                            System.out.print("Did you mean to type that? type in amenities to exit out of choosing an upgrade in packages.\n"

                                                                    + "Otherwise type in your preferred package upgrade of superman or luxury: ");
                                                            decision = krb.next();
                                                            decision = decision.toLowerCase();
                                                            if(decision.equals("amenities")){
                                                                exit_loop=1;
                                                                wrong_key2=1;
                                                                package_chosen = "amenities";
                                                            }
                                                            else{
                                                                exit_loop=0;
                                                            }
                                                        }
                                                    }

                                                    String obtain_package_price ="";
                                                    pricePack = null;
                                                    if(package_chosen.equals("superman")){
                                                        package_ch =1;
                                                        obtain_package_price = "select * from packages where package_id = ? ";
                                                        ps5 = con.prepareStatement(obtain_package_price);
                                                        ps5.setInt(1, package_ch);
                                                        result4=ps5.executeQuery();
                                                        //pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                                        while(result4.next()){
                                                            pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                                        }
                                                    }
                                                    else if(package_chosen.equals("luxury")){
                                                        package_ch =3;
                                                        obtain_package_price = "select * from packages where package_id = ? ";
                                                        ps5 = con.prepareStatement(obtain_package_price);
                                                        ps5.setInt(1, package_ch);
                                                        result4=ps5.executeQuery();
                                                        while(result4.next()){
                                                            pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                                        }
                                                    }
                                                    else if(package_chosen.equals("amenities") || package_ch ==2){
                                                        package_ch =2;
                                                        obtain_package_price = "select * from packages where package_id = ? ";
                                                        ps5 = con.prepareStatement(obtain_package_price);
                                                        ps5.setInt(1, package_ch);
                                                        result4=ps5.executeQuery();
                                                        while(result4.next()){
                                                            pricePack = result4.getBigDecimal("TOT_PRICE_PACKAGE");
                                                        }
                                                    }
                                                    packageSelected = package_chosen;
                                                    totalpricepaid = baseprice;
                                                    if(package_ch == 2 ){
                                                        //the amenities package was selected for them or they chose it.
                                                        //You can choose to add the hybrid as well as bedazzling your wheels with 20'' chrome wheels.
                                                        //add the price of the package amenities
                                                        totalpricepaid = totalpricepaid.add(pricePack);
                                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                                        System.out.print("We have a hybrid option available for this vehicle. "
                                                                + "Do you want to know more about it? ");
                                                        String op_decision = krb.next();
                                                        op_decision = op_decision.toLowerCase();
                                                        int op_d = 0;

                                                        while(op_d == 0){
                                                            if(op_decision.equals("yes")){
                                                                op_d =1;
                                                                String choose_hybrid = "SELECT * FROM OPTIONS where options_name = ?";
                                                                ps5 = con.prepareStatement(choose_hybrid);
                                                                ps5.setString(1, "Hybrid ");
                                                                result4 = ps5.executeQuery();
                                                                priceofHy=0;
                                                                String op_name;
                                                                String op_descr;
                                                                while(result4.next()){
                                                                    System.out.println(result4.getString("OPTIONS_NAME") + "$"+ result4.getInt("LIST_PRICE") + " " + result4.getString("DESCRIPTION"));
                                                                    priceofHy = result4.getInt("LIST_PRICE");
                                                                    op_name = result4.getString("OPTIONS_NAME");
                                                                    op_descr = result4.getString("DESCRIPTION");
                                                                }
                                                                System.out.print("Do you want to purchase this option? ");
                                                                String purchase_hy = krb.next();
                                                                purchase_hy = purchase_hy.toLowerCase();
                                                                int a = 0;
                                                                while( a == 0){
                                                                    if(purchase_hy.equals("yes")){
                                                                        //optionSelected[0]= "Hybrid";
                                                                        optionSelected.add("Hybrid");
                                                                        a =1 ;
                                                                        //add to v_options
                                                                        //get price of option and to total price paid
                                                                        totalpricepaid = totalpricepaid.add(new BigDecimal(priceofHy));
                                                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                                                    }
                                                                    else if(purchase_hy.equals("no")){
                                                                        a =1 ;
                                                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);

                                                                    }
                                                                    else{
                                                                        a=0;
                                                                        System.out.print("We did not get what you were trying to say. Can you enter yes "
                                                                                + "if you want to purchase the hybrid option, otherwise say no. ");
                                                                        purchase_hy = krb.next();
                                                                        purchase_hy = purchase_hy.toLowerCase();
                                                                    }
                                                                }
                                                            }

                                                            else if(op_decision.equals("no") || op_decision.equals("exit")){
                                                                op_d =1;

                                                            }
                                                            else{
                                                                op_d=0;
                                                                System.out.println("We did not understand what you had typed. If you want to exit, type in exit."
                                                                        + "\nIf you want to know more about the hybrid type in yes. ");
                                                                op_decision = krb.next();
                                                                op_decision = op_decision.toLowerCase();
                                                            }
                                                        }
                                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                                    }

                                                    else{
                                                        //they chose a package other than the amenities package.
                                                        //totalpricepaid= pricePack + totalpricepaid;
                                                        totalpricepaid = totalpricepaid.add(pricePack);
                                                        System.out.println("The price of your vehicle right now currently is $" + totalpricepaid);
                                                    }
                                                    System.out.println();
                                                    //list a summary of the model that the customer is about to purchase.
                                                    //ask to confirm if this correct. if they say yes, the records will all be updated.
                                                    a.SummaryInfo();

                                                    if(oldc_num != 0 ){
                                                        System.out.println("Card Info: " + oldc_num);
                                                    }
                                                    else if( oldc_num_used != 0){
                                                        System.out.println("Card Info: " + oldc_num_used);
                                                    }

                                                    System.out.print("Is this correct? Type in confirm to place your order. Otherwise type in no to opt out of ordering: ");
                                                    String final_decision = krb.next();
                                                    final_decision = final_decision.toLowerCase();
                                                    int ab=0;
                                                    while (ab ==0){
                                                        if(final_decision.equals("confirm")){
                                                            ab =1;
                                                            //do all my inserts here.
                                                            if(oldc_num == 0 && oldc_num_used != 0 ){
                                                                String insert_Custom = "insert into customer_payment  values(?,?,?,?)";
                                                                ps5 = con.prepareStatement(insert_Custom);
                                                                ps5.setString(1, name);
                                                                ps5.setLong(2, oldc_num_used);
                                                                ps5.setInt(3, id);
                                                                ps5.setString(4, email_addr);
                                                                ps5.executeUpdate();
                                                            }
                                                            //insert vehicle, v_packages, vehicle_options if there are chrome wheels or hybrid options,
                                                            //customer_buys_new , v_model, and new_vehicle
                                                            insert_vehicles = "insert into vehicle values(?)";
                                                            ps5 = con.prepareStatement(insert_vehicles);
                                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                                            ps5.executeUpdate();

                                                            insert_package="insert into v_packages values(?,?)";
                                                            ps5 = con.prepareStatement(insert_package);
                                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                                            ps5.setInt(2, package_ch);
                                                            ps5.executeUpdate();

                                                            if(priceofHy !=0){
                                                                CallableStatement stmt2 = con.prepareCall("{call options_to_have(?,?,?)}");
                                                                stmt2.setBigDecimal(1, new BigDecimal(new_vid));
                                                                stmt2.setString(2, "Hybrid ");
                                                                stmt2.setInt(3, priceofHy);
                                                                boolean hadResults2 = stmt2.execute();

                                                                while (hadResults2) {
                                                                    ResultSet rs = stmt2.getResultSet();

                                                                    // process result set

                                                                    hadResults2 = stmt2.getMoreResults();
                                                                }
                                                            }
                                                            String insert_cust_buys_new = "insert into customer_buys_new values(?,?,?,?,?)";
                                                            ps5 = con.prepareStatement(insert_cust_buys_new);
                                                            ps5.setString(1, name);
                                                            ps5.setInt(2, id);
                                                            ps5.setBigDecimal(3,new BigDecimal(new_vid));
                                                            ps5.setString(4, email_addr);
                                                            ps5.setBigDecimal(5,totalpricepaid);
                                                            ps5.executeUpdate();

                                                            String insert_vmodel = "insert into v_model values(?,?,?)";
                                                            ps5 = con.prepareStatement(insert_vmodel);
                                                            ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                                            ps5.setInt(2, initialModelID);
                                                            ps5.setString(3, initialModelName);
                                                            ps5.executeUpdate();

                                                            krb.nextLine();
                                                            System.out.print("What Alset service center do you want your vehicle to be scheduled for pickup? \nType in pacific or pacific overlook for"
                                                                    + " Pacific Overlook or southside bethlehem or southside for Southside Bethlehem: ");
                                                            String what_center =krb.nextLine();
                                                            while (!what_center.matches("[a-zA-Z_\\- ]+")
                                                                    ||what_center.isEmpty()) {
                                                                System.out.println("\nInvalid. Remember to type in letters only.");
                                                                System.out.print("Enter the Alset service center you want your vehicle to be scheduled for pickup? \nType in pacific or pacific overlook for"
                                                                    + "Pacific Overlook. Type in southside bethlehem or southside for Southside Bethlehem: ");
                                                                what_center = krb.nextLine();
                                                            }
                                                            what_center = what_center.toLowerCase();
                                                            int y = 0 ; 
                                                            while ( y == 0){

                                                                if(what_center.equals("southside") || what_center.equals("southside bethlehem")){
                                                                    y = 1;
                                                                    String insert_newvehicle = "insert into new_vehicle values(?,?,?)";
                                                                    ps5 = con.prepareStatement(insert_newvehicle);
                                                                    ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                                                    ps5.setString(2, "Southside Bethlehem");
                                                                    ps5.setString(3, initialModelType);
                                                                    ps5.executeUpdate();

                                                                    System.out.println("The order has been submitted.");
                                                                    System.out.println("Your order will be available for pickup at the Southside Bethlehem center.");
                                                                }
                                                                else if(what_center.equals("pacific") || what_center.equals("pacific overlook")){
                                                                    y=1;
                                                                    String insert_newvehicle = "insert into new_vehicle values(?,?,?)";
                                                                    ps5 = con.prepareStatement(insert_newvehicle);
                                                                    ps5.setBigDecimal(1, new BigDecimal(new_vid));
                                                                    ps5.setString(2, "Pacific Overlook");
                                                                    ps5.setString(3, initialModelType);
                                                                    ps5.executeUpdate();
                                                                    System.out.println("The order has been submitted.");
                                                                    System.out.println("Your order will be available for pickup at the Pacific Overlook center.");
                                                                }
                                                                else{
                                                                    y=0;
                                                                    System.out.print("We did not recognize your entry. \nEnter the Alset service center you want your vehicle to be scheduled for pickup? \nType in pacific or pacific overlook for"
                                                                    + "Pacific Overlook. Type in southside bethlehem or southside for Southside Bethlehem: ");
                                                                    what_center = krb.nextLine();
                                                                    what_center=what_center.toLowerCase();
                                                                }
                                                            }

                                                        }
                                                        else if(final_decision.equals("no")){
                                                            System.out.println("No new car was purchased.");
                                                            ab =1;
                                                            //exit out of here
                                                        }
                                                        else{
                                                            System.out.print("We did not understand what you had typed. Please type in confirm to place your order."
                                                                    + "\nOtherwise type in no to opt of ordering a new car. ");
                                                            final_decision = krb.next();
                                                            final_decision = final_decision.toLowerCase();
                                                        }
                                                    }

                                                }
                                                else{
                                                    wrong_key2 =0;
                                                    System.out.print("Did you mean to type that? type in exit to exit out of purchasing a new car. otherwise type in the first letter to the package you prefer."
                                                            + "\nSelect M for Model M, select U for Model U, select S for Model S, select K for Model K: ");
                                                    //String s = "";
                                                    model_selected = krb.next();
                                                    model_selected = model_selected.toLowerCase();
                                                    if(model_selected.equals("exit")){
                                                        wrong_key2 =1;
                                                    }

                                                }
                                            }
                                        }
                                        else if(option.equals("no")){
                                            System.out.println("No new car was purchased.");
                                            wrong_key = 1;
                                        }
                                        else{
                                            wrong_key =0;
                                            System.out.print("Did you mean to type that? Type in exit or no to exit. otherwise type yes to continue to choose a vehicle: ");
                                            //String s = "";
                                            option = krb.next();
                                            option = option.toLowerCase();
                                            if(option.equals("exit")){
                                                System.out.println("No new vehicle was chosen.");
                                                wrong_key =1;
                                            }
                                        }
                                    }

                                    System.out.print("\nDo you want to purchase an available used car? Type in yes to continue. Otherwise type in no to exit: ");
                                    option = krb.next();
                                    option=option.toLowerCase();
                                    wrong_key=0;
                                    while(wrong_key == 0 ){
                                        if(option.equals("yes")){
                                            wrong_key =1;
                                            // have the user input the model type he is looking for.
                                            System.out.println("What model type are you looking for? We can show what models you might be interested in purchasing. ");
                                            System.out.println("The model types that we have available are:\n"
                                                    + "Model K, the “Kart”, a small electric vehicle for use on the surface of the earth.\n"
                                                    + "Model U, the undersea vehicle, used as a personal submarine.\n"
                                                    + "Model M, the moon vehicle, used to ride on the surface of the moon.\n"
                                                    + "Model S, the spacecraft, used for space travel. This vehicle is capable of landing on most planets and moons,"
                                                    + " and, after refueling, can re-launch into space.");
                                            System.out.print("Select M for Model M, select U for Model U, select S for Model S, select K for Model K: ");
                                            String model_selected = krb.next();
                                            model_selected = model_selected.toLowerCase();
                                            BigDecimal new_price = null;
                                            int wrong_key2=0;
                                            while( wrong_key2 ==0){
                                                if(model_selected.equals("m")){
                                                    wrong_key2 = 1;

                                                    String cars_used_available = "SELECT v_id,new_price FROM S_L_USED WHERE NOT EXISTS( SELECT v_id FROM customer_buys_used WHERE S_L_USED.v_id = customer_buys_used.v_id ) "
                                                            + "AND MODEL_TYPE = ? ";

                                                    ps5 =con.prepareStatement(cars_used_available);
                                                    ps5.setString(1, "Model M");
                                                    result2 = ps5.executeQuery();

                                                    System.out.println("The available models are: ");
                                                    int count2 = 0;
                                                    while(result2.next() && count2 == 0){
                                                        
                                                        System.out.println();

                                                        BigDecimal a = result2.getBigDecimal("v_id");
                                                        new_price = result2.getBigDecimal("NEW_PRICE");

                                                        String find_vehicle_details = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id";
                                                        ps4 = con.prepareStatement(find_vehicle_details);
                                                        ps4.setBigDecimal(1, a);
                                                        result3 = ps4.executeQuery();
                                                        while(result3.next()){
                                                            System.out.println("Vehicle ID: " + a + "\tModel ID: "+ result3.getInt("m_id") + "\tModel Name: "+ result3.getString("model_name") +"\tModel Year: "
                                                                    +result3.getInt("year") +"\tModel Type: "+ result3.getString("model_type") +"\nOriginal Price: $"+ result3.getBigDecimal("list_price") + 
                                                                    "\tNew Price: $"+ new_price);

                                                        }
                                                        String package_av = "select * from v_packages_used natural join packages where v_id = ? ";
                                                        ps4 = con.prepareStatement(package_av);
                                                        ps4.setBigDecimal(1, a);
                                                        result3 = ps4.executeQuery();

                                                        while(result3.next()){
                                                            System.out.println("Package selected for vehicle " +a + ": " + result3.getString("package_name"));
                                                        }
                                                    } 
                                                    if(!result2.next() && count2 == 0){
                                                        count2=1;
                                                        System.out.println("There are no more available used vehicles. Please call our office location during our regular business hours if you have any questions.");
                                                        //return;
                                                    }
                                                    System.out.println("Please enter the vehicle id of the vehicle you wish to purchase: ");
                                                    System.out.print("Vehicle ID: ");
                                                    BigDecimal car_wish_to_own;
                                                    while (!krb.hasNextBigDecimal()) { 
                                                        System.out.println("only numbers! Type in the vehicle id. "); 
                                                        System.out.print("Enter the vehicle id of the vehicle you wish to purchase: ");
                                                        krb.next(); // discard 
                                                    }
                                                    car_wish_to_own = krb.nextBigDecimal();
                                                    krb.nextLine();
                                                    int z = 0;
                                                    int t = 0;
                                                    while ( z ==0){
                                                        String specific_model = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id ";
                                                        ps5 = con.prepareStatement(specific_model);
                                                        ps5.setBigDecimal(1,car_wish_to_own);
                                                        //ps5.setString(2, "Model M");

                                                        result4 = ps5.executeQuery();
                                                        while(result4.next()){
                                                            z = 1;t = 1;
                                                            System.out.println("\nVehicle ID: " + car_wish_to_own + "\tModel ID: "+ result4.getInt("m_id") + "\tModel Name: "+ result4.getString("model_name") +"\tModel Year: "
                                                                    +result4.getInt("year") +"\tModel Type: "+ result4.getString("model_type") +"\nOriginal Price: $"+ result4.getBigDecimal("list_price") + 
                                                                    "\tNew Price: $"+ new_price);
                                                            if(oldc_num != 0 ){
                                                                System.out.println("Card Info: " + oldc_num);
                                                            }
                                                            else if( oldc_num_used != 0){
                                                                System.out.println("Card Info: " + oldc_num_used);
                                                            }
                                                            System.out.print("Is this the vehicle you want to purchase? Type in confirm to purchase this vehicle: ");

                                                            String final_decision = krb.next();
                                                            final_decision = final_decision.toLowerCase();
                                                            int ab=0;
                                                            while (ab ==0){
                                                                if(final_decision.equals("confirm")){
                                                                    ab=1;
                                                                    if(oldc_num != 0 && oldc_num_used == 0 ){
                                                                        String insert_Custom = "insert into customer_payment_used  values(?,?,?,?)";
                                                                        ps6 = con.prepareStatement(insert_Custom);
                                                                        ps6.setString(1, name);
                                                                        ps6.setLong(2, oldc_num);
                                                                        ps6.setInt(3, id);
                                                                        ps6.setString(4, email_addr);
                                                                        ps6.executeUpdate();
                                                                    }

                                                                    //I already inserted into vehiclesused,used_vehicles so i need to insert customer_buys_used
                                                                    String insert_cust_buys_used = "insert into customer_buys_used values(?,?,?,?,?,?,?)";
                                                                    ps6 = con.prepareStatement(insert_cust_buys_used);
                                                                    ps6.setString(1, name);
                                                                    ps6.setInt(2, id);
                                                                    ps6.setBigDecimal(3, car_wish_to_own);
                                                                    ps6.setString(4, email_addr);
                                                                    ps6.setBigDecimal(5, new_price);
                                                                    ps6.setBigDecimal(6, new_price);
                                                                    ps6.setBigDecimal(7, result4.getBigDecimal("list_price"));
                                                                    ps6.executeUpdate();

                                                                    System.out.println("Order has been submitted.");
                                                                    System.out.println("Your order will be available for pickup at the Moon Exploration center.");
                                                                }
                                                                else if(final_decision.equals("no")){
                                                                    ab =1;
                                                                    //exit out of here
                                                                }
                                                                else{
                                                                    ab=0;
                                                                    System.out.print("We did not understand what you had typed. Please type in confirm to place your order."
                                                                            + "\nOtherwise type in no to opt of ordering. ");
                                                                    final_decision = krb.next();
                                                                    final_decision = final_decision.toLowerCase();
                                                                }
                                                            }
                                                        }

                                                        while( t == 0 && !result4.next()){
                                                            z=0;
                                                            System.out.print("We don't recognize the id that you wrote. If you wish to exit, please type in exit. \n"
                                                                    + "Otherwise type in any other character to continue: ");
                                                            String dec = krb.next();
                                                            dec =dec.toLowerCase();
                                                            if(dec.equals("exit")){
                                                                return;
                                                            }
                                                            else{
                                                                System.out.print("Please enter a vehicle id of the vehicle you wish to purchase: ");
                                                                while (!krb.hasNextBigDecimal()) { 
                                                                    System.out.println("only numbers! Type in the model id. "); 
                                                                    System.out.print("Enter your vehicle id number: ");
                                                                    krb.next(); // discard 
                                                                }
                                                                car_wish_to_own = krb.nextBigDecimal();
                                                                specific_model = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id ";
                                                                ps5 = con.prepareStatement(specific_model);
                                                                ps5.setBigDecimal(1,car_wish_to_own);
                                                                //ps5.setString(2, "Model M");
                                                            }
                                                            result4 = ps5.executeQuery();
                                                        }
                                                    }


                                                }
                                                else if(model_selected.equals("u")){
                                                    wrong_key2 = 1;

                                                    String cars_used_available = "SELECT v_id,new_price FROM S_L_USED WHERE NOT EXISTS( SELECT v_id FROM customer_buys_used WHERE S_L_USED.v_id = customer_buys_used.v_id ) "
                                                            + "AND MODEL_TYPE = ? ";

                                                    ps5 =con.prepareStatement(cars_used_available);
                                                    ps5.setString(1, "Model U");
                                                    result2 = ps5.executeQuery();

                                                    System.out.println("The available models are: ");
                                                    int count2 = 0;
                                                    while(result2.next() && count2 == 0){
                                                        System.out.println();
                                                        
                                                        BigDecimal a = result2.getBigDecimal("v_id");
                                                        new_price = result2.getBigDecimal("NEW_PRICE");

                                                        String find_vehicle_details = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id";
                                                        ps4 = con.prepareStatement(find_vehicle_details);
                                                        ps4.setBigDecimal(1, a);
                                                        result3 = ps4.executeQuery();
                                                        while(result3.next()){
                                                            System.out.println("Vehicle ID: " + a + "\tModel ID: "+ result3.getInt("m_id") + "\tModel Name: "+ result3.getString("model_name") +"\tModel Year: "
                                                                    +result3.getInt("year") +"\tModel Type: "+ result3.getString("model_type") +"\nOriginal Price: $"+ result3.getBigDecimal("list_price") + 
                                                                    "\tNew Price: $"+ new_price);

                                                        }
                                                        String package_av = "select * from v_packages_used natural join packages where v_id = ? ";
                                                        ps4 = con.prepareStatement(package_av);
                                                        ps4.setBigDecimal(1, a);
                                                        result3 = ps4.executeQuery();

                                                        while(result3.next()){
                                                            System.out.println("Package selected for vehicle " +a + ": " + result3.getString("package_name"));
                                                        }
                                                    } 
                                                    if(!result2.next() && count2 == 0){
                                                        count2 =1;
                                                        System.out.println("There are no more available used vehicles. Please call our office location during our regular business hours if you have any questions.");
                                                        //return;
                                                    }
                                                    System.out.println("Please enter the vehicle id of the vehicle you wish to purchase: ");
                                                    System.out.print("Vehicle ID: ");
                                                    BigDecimal car_wish_to_own;
                                                    while (!krb.hasNextBigDecimal()) { 
                                                        System.out.println("only numbers! Type in the vehicle id. "); 
                                                        System.out.print("Enter the vehicle id of the vehicle you wish to purchase: ");
                                                        krb.next(); // discard 
                                                    }
                                                    car_wish_to_own = krb.nextBigDecimal();
                                                    krb.nextLine();
                                                    int z = 0;
                                                    int t = 0;
                                                    while ( z ==0){
                                                        String specific_model = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id ";
                                                        ps5 = con.prepareStatement(specific_model);
                                                        ps5.setBigDecimal(1,car_wish_to_own);
                                                        //ps5.setString(2, "Model M");

                                                        result4 = ps5.executeQuery();
                                                        while(result4.next()){
                                                            z = 1;t = 1;
                                                            System.out.println("\nVehicle ID: " + car_wish_to_own + "\tModel ID: "+ result4.getInt("m_id") + "\tModel Name: "+ result4.getString("model_name") +"\tModel Year: "
                                                                    +result4.getInt("year") +"\tModel Type: "+ result4.getString("model_type") +"\nOriginal Price: $"+ result4.getBigDecimal("list_price") + 
                                                                    "\tNew Price: $"+ new_price);
                                                            if(oldc_num != 0 ){
                                                                System.out.println("Card Info: " + oldc_num);
                                                            }
                                                            else if( oldc_num_used != 0){
                                                                System.out.println("Card Info: " + oldc_num_used);
                                                            }
                                                            System.out.print("Is this the vehicle you want to purchase? Type in confirm to purchase this vehicle: ");

                                                            String final_decision = krb.next();
                                                            final_decision = final_decision.toLowerCase();
                                                            int ab=0;
                                                            while (ab ==0){
                                                                if(final_decision.equals("confirm")){
                                                                    ab=1;
                                                                    if(oldc_num != 0 && oldc_num_used == 0 ){
                                                                        String insert_Custom = "insert into customer_payment_used  values(?,?,?,?)";
                                                                        ps6 = con.prepareStatement(insert_Custom);
                                                                        ps6.setString(1, name);
                                                                        ps6.setLong(2, oldc_num);
                                                                        ps6.setInt(3, id);
                                                                        ps6.setString(4, email_addr);
                                                                        ps6.executeUpdate();
                                                                    }

                                                                    //I already inserted into vehiclesused,used_vehicles so i need to insert customer_buys_used
                                                                    String insert_cust_buys_used = "insert into customer_buys_used values(?,?,?,?,?,?,?)";
                                                                    ps6 = con.prepareStatement(insert_cust_buys_used);
                                                                    ps6.setString(1, name);
                                                                    ps6.setInt(2, id);
                                                                    ps6.setBigDecimal(3, car_wish_to_own);
                                                                    ps6.setString(4, email_addr);
                                                                    ps6.setBigDecimal(5, new_price);
                                                                    ps6.setBigDecimal(6, new_price);
                                                                    ps6.setBigDecimal(7, result4.getBigDecimal("list_price"));
                                                                    ps6.executeUpdate();

                                                                    System.out.println("Order has been submitted.");
                                                                    System.out.println("Your order will be available for pickup at the Moon Exploration center.");
                                                                }
                                                                else if(final_decision.equals("no")){
                                                                    ab =1;
                                                                    //exit out of here
                                                                }
                                                                else{
                                                                    ab=0;
                                                                    System.out.print("We did not understand what you had typed. Please type in confirm to place your order."
                                                                            + "\nOtherwise type in no to opt of ordering. ");
                                                                    final_decision = krb.next();
                                                                    final_decision = final_decision.toLowerCase();
                                                                }
                                                            }
                                                        }

                                                        while( t == 0 && !result4.next()){
                                                            z=0;
                                                            System.out.print("We don't recognize the id that you wrote. If you wish to exit, please type in exit. \n"
                                                                    + "Otherwise type in any other character to continue: ");
                                                            String dec = krb.next();
                                                            dec =dec.toLowerCase();
                                                            if(dec.equals("exit")){
                                                                return;
                                                            }
                                                            else{
                                                                System.out.print("Please enter a vehicle id of the vehicle you wish to purchase: ");
                                                                while (!krb.hasNextBigDecimal()) { 
                                                                    System.out.println("only numbers! Type in the model id. "); 
                                                                    System.out.print("Enter your vehicle id number: ");
                                                                    krb.next(); // discard 
                                                                }
                                                                car_wish_to_own = krb.nextBigDecimal();
                                                                specific_model = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id ";
                                                                ps5 = con.prepareStatement(specific_model);
                                                                ps5.setBigDecimal(1,car_wish_to_own);
                                                                //ps5.setString(2, "Model M");
                                                            }
                                                            result4 = ps5.executeQuery();
                                                        }
                                                    } 


                                                }
                                                else if(model_selected.equals("s")){
                                                    wrong_key2 = 1;

                                                    String cars_used_available = "SELECT v_id,new_price FROM S_L_USED WHERE NOT EXISTS( SELECT v_id FROM customer_buys_used WHERE S_L_USED.v_id = customer_buys_used.v_id ) "
                                                            + "AND MODEL_TYPE = ? ";

                                                    ps5 =con.prepareStatement(cars_used_available);
                                                    ps5.setString(1, "Model S");
                                                    result2 = ps5.executeQuery();

                                                    System.out.println("The available models are: ");
                                                    int count2 = 0;
                                                    while(result2.next() && count2==0){
                                                        System.out.println();
                                                        
                                                        BigDecimal a = result2.getBigDecimal("v_id");
                                                        new_price = result2.getBigDecimal("NEW_PRICE");

                                                        String find_vehicle_details = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id";
                                                        ps4 = con.prepareStatement(find_vehicle_details);
                                                        ps4.setBigDecimal(1, a);
                                                        result3 = ps4.executeQuery();
                                                        while(result3.next()){
                                                            System.out.println("Vehicle ID: " + a + "\tModel ID: "+ result3.getInt("m_id") + "\tModel Name: "+ result3.getString("model_name") +"\tModel Year: "
                                                                    +result3.getInt("year") +"\tModel Type: "+ result3.getString("model_type") +"\nOriginal Price: $"+ result3.getBigDecimal("list_price") + 
                                                                    "\tNew Price: $"+ new_price);

                                                        }
                                                        String package_av = "select * from v_packages_used natural join packages where v_id = ? ";
                                                        ps4 = con.prepareStatement(package_av);
                                                        ps4.setBigDecimal(1, a);
                                                        result3 = ps4.executeQuery();

                                                        while(result3.next()){
                                                            System.out.println("Package selected for vehicle " +a + ": " + result3.getString("package_name"));
                                                        }
                                                    } 
                                                    if(!result2.next() && count2 == 0){
                                                        count2=1;
                                                        System.out.println("There are no more available used vehicles. Please call our office location during our regular business hours if you have any questions.");
                                                        //return;
                                                    }
                                                    System.out.println("Please enter the vehicle id of the vehicle you wish to purchase: ");
                                                    System.out.print("Vehicle ID: ");
                                                    BigDecimal car_wish_to_own;
                                                    while (!krb.hasNextBigDecimal()) { 
                                                        System.out.println("only numbers! Type in the vehicle id. "); 
                                                        System.out.print("Enter the vehicle id of the vehicle you wish to purchase: ");
                                                        krb.next(); // discard 
                                                    }
                                                    car_wish_to_own = krb.nextBigDecimal();
                                                    krb.nextLine();
                                                    int z = 0;
                                                    int t = 0;
                                                    while ( z ==0){
                                                        String specific_model = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id ";
                                                        ps5 = con.prepareStatement(specific_model);
                                                        ps5.setBigDecimal(1,car_wish_to_own);
                                                        //ps5.setString(2, "Model M");

                                                        result4 = ps5.executeQuery();
                                                        while(result4.next()){
                                                            z = 1;t = 1;
                                                            System.out.println("\nVehicle ID: " + car_wish_to_own + "\tModel ID: "+ result4.getInt("m_id") + "\tModel Name: "+ result4.getString("model_name") +"\tModel Year: "
                                                                    +result4.getInt("year") +"\tModel Type: "+ result4.getString("model_type") +"\nOriginal Price: $"+ result4.getBigDecimal("list_price") + 
                                                                    "\tNew Price: $"+ new_price);
                                                            if(oldc_num != 0 ){
                                                                System.out.println("Card Info: " + oldc_num);
                                                            }
                                                            else if( oldc_num_used != 0){
                                                                System.out.println("Card Info: " + oldc_num_used);
                                                            }
                                                            System.out.print("Is this the vehicle you want to purchase? Type in confirm to purchase this vehicle: ");

                                                            String final_decision = krb.next();
                                                            final_decision = final_decision.toLowerCase();
                                                            int ab=0;
                                                            while (ab ==0){
                                                                if(final_decision.equals("confirm")){
                                                                    ab=1;
                                                                    if(oldc_num != 0 && oldc_num_used == 0 ){
                                                                        String insert_Custom = "insert into customer_payment_used  values(?,?,?,?)";
                                                                        ps6 = con.prepareStatement(insert_Custom);
                                                                        ps6.setString(1, name);
                                                                        ps6.setLong(2, oldc_num);
                                                                        ps6.setInt(3, id);
                                                                        ps6.setString(4, email_addr);
                                                                        ps6.executeUpdate();
                                                                    }

                                                                    //I already inserted into vehiclesused,used_vehicles so i need to insert customer_buys_used
                                                                    String insert_cust_buys_used = "insert into customer_buys_used values(?,?,?,?,?,?,?)";
                                                                    ps6 = con.prepareStatement(insert_cust_buys_used);
                                                                    ps6.setString(1, name);
                                                                    ps6.setInt(2, id);
                                                                    ps6.setBigDecimal(3, car_wish_to_own);
                                                                    ps6.setString(4, email_addr);
                                                                    ps6.setBigDecimal(5, new_price);
                                                                    ps6.setBigDecimal(6, new_price);
                                                                    ps6.setBigDecimal(7, result4.getBigDecimal("list_price"));
                                                                    ps6.executeUpdate();

                                                                    System.out.println("Order has been submitted.");
                                                                    System.out.println("Your order will be available for pickup at the Moon Exploration center.");
                                                                }
                                                                else if(final_decision.equals("no")){
                                                                    ab =1;
                                                                    //exit out of here
                                                                }
                                                                else{
                                                                    ab=0;
                                                                    System.out.print("We did not understand what you had typed. Please type in confirm to place your order."
                                                                            + "\nOtherwise type in no to opt of ordering. ");
                                                                    final_decision = krb.next();
                                                                    final_decision = final_decision.toLowerCase();
                                                                }
                                                            }
                                                        }

                                                        while( t == 0 && !result4.next()){
                                                            z=0;
                                                            System.out.print("We don't recognize the id that you wrote. If you wish to exit, please type in exit. \n"
                                                                    + "Otherwise type in any other character to continue: ");
                                                            String dec = krb.next();
                                                            dec =dec.toLowerCase();
                                                            if(dec.equals("exit")){
                                                                return;
                                                            }
                                                            else{
                                                                System.out.print("Please enter a vehicle id of the vehicle you wish to purchase: ");
                                                                while (!krb.hasNextBigDecimal()) { 
                                                                    System.out.println("only numbers! Type in the model id. "); 
                                                                    System.out.print("Enter your vehicle id number: ");
                                                                    krb.next(); // discard 
                                                                }
                                                                car_wish_to_own = krb.nextBigDecimal();
                                                                specific_model = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id ";
                                                                ps5 = con.prepareStatement(specific_model);
                                                                ps5.setBigDecimal(1,car_wish_to_own);
                                                                //ps5.setString(2, "Model M");
                                                            }
                                                            result4 = ps5.executeQuery();
                                                        }
                                                    }
                                                }
                                                else if(model_selected.equals("k")){
                                                    wrong_key2 = 1;

                                                    String cars_used_available = "SELECT v_id,new_price FROM S_L_USED WHERE NOT EXISTS( SELECT v_id FROM customer_buys_used WHERE S_L_USED.v_id = customer_buys_used.v_id ) "
                                                            + "AND MODEL_TYPE = ? ";

                                                    ps5 =con.prepareStatement(cars_used_available);
                                                    ps5.setString(1, "Model K");
                                                    result2 = ps5.executeQuery();

                                                    System.out.println("The available models are: ");
                                                    int count2 = 0;
                                                    while(result2.next() && count2 == 0){
                                                        System.out.println();
                                                        
                                                        BigDecimal a = result2.getBigDecimal("v_id");
                                                        new_price = result2.getBigDecimal("NEW_PRICE");

                                                        String find_vehicle_details = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id";
                                                        ps4 = con.prepareStatement(find_vehicle_details);
                                                        ps4.setBigDecimal(1, a);
                                                        result3 = ps4.executeQuery();
                                                        while(result3.next()){
                                                            System.out.println("Vehicle ID: " + a + "\tModel ID: "+ result3.getInt("m_id") + "\tModel Name: "+ result3.getString("model_name") +"\tModel Year: "
                                                                    +result3.getInt("year") +"\tModel Type: "+ result3.getString("model_type") +"\nOriginal Price: $"+ result3.getBigDecimal("list_price") + 
                                                                    "\tNew Price: $"+ new_price);

                                                        }
                                                        String package_av = "select * from v_packages_used natural join packages where v_id = ? ";
                                                        ps4 = con.prepareStatement(package_av);
                                                        ps4.setBigDecimal(1, a);
                                                        result3 = ps4.executeQuery();

                                                        while(result3.next()){
                                                            System.out.println("Package selected for vehicle " +a + ": " + result3.getString("package_name"));
                                                        }
                                                    } 
                                                    if(!result2.next() && count2 == 0){
                                                        count2=1;
                                                        System.out.println("There are no more available used vehicles. Please call our office location during our regular business hours if you have any questions.");
                                                        //return;
                                                    }
                                                    System.out.println("Please enter the vehicle id of the vehicle you wish to purchase: ");
                                                    System.out.print("Vehicle ID: ");
                                                    BigDecimal car_wish_to_own;
                                                    while (!krb.hasNextBigDecimal()) { 
                                                        System.out.println("only numbers! Type in the vehicle id. "); 
                                                        System.out.print("Enter the vehicle id of the vehicle you wish to purchase: ");
                                                        krb.next(); // discard 
                                                    }
                                                    car_wish_to_own = krb.nextBigDecimal();
                                                    krb.nextLine();
                                                    int z = 0;
                                                    int t = 0;
                                                    while ( z ==0){
                                                        String specific_model = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id ";
                                                        ps5 = con.prepareStatement(specific_model);
                                                        ps5.setBigDecimal(1,car_wish_to_own);
                                                        //ps5.setString(2, "Model M");

                                                        result4 = ps5.executeQuery();
                                                        while(result4.next()){
                                                            z = 1;t = 1;
                                                            System.out.println("\nVehicle ID: " + car_wish_to_own + "\tModel ID: "+ result4.getInt("m_id") + "\tModel Name: "+ result4.getString("model_name") +"\tModel Year: "
                                                                    +result4.getInt("year") +"\tModel Type: "+ result4.getString("model_type") +"\nOriginal Price: $"+ result4.getBigDecimal("list_price") + 
                                                                    "\tNew Price: $"+ new_price);
                                                            if(oldc_num != 0 ){
                                                                System.out.println("Card Info: " + oldc_num);
                                                            }
                                                            else if( oldc_num_used != 0){
                                                                System.out.println("Card Info: " + oldc_num_used);
                                                            }
                                                            System.out.print("Is this the vehicle you want to purchase? Type in confirm to purchase this vehicle: ");

                                                            String final_decision = krb.next();
                                                            final_decision = final_decision.toLowerCase();
                                                            int ab=0;
                                                            while (ab ==0){
                                                                if(final_decision.equals("confirm")){
                                                                    ab=1;
                                                                    if(oldc_num != 0 && oldc_num_used == 0 ){
                                                                        String insert_Custom = "insert into customer_payment_used  values(?,?,?,?)";
                                                                        ps6 = con.prepareStatement(insert_Custom);
                                                                        ps6.setString(1, name);
                                                                        ps6.setLong(2, oldc_num);
                                                                        ps6.setInt(3, id);
                                                                        ps6.setString(4, email_addr);
                                                                        ps6.executeUpdate();
                                                                    }

                                                                    //I already inserted into vehiclesused,used_vehicles so i need to insert customer_buys_used
                                                                    String insert_cust_buys_used = "insert into customer_buys_used values(?,?,?,?,?,?,?)";
                                                                    ps6 = con.prepareStatement(insert_cust_buys_used);
                                                                    ps6.setString(1, name);
                                                                    ps6.setInt(2, id);
                                                                    ps6.setBigDecimal(3, car_wish_to_own);
                                                                    ps6.setString(4, email_addr);
                                                                    ps6.setBigDecimal(5, new_price);
                                                                    ps6.setBigDecimal(6, new_price);
                                                                    ps6.setBigDecimal(7, result4.getBigDecimal("list_price"));
                                                                    ps6.executeUpdate();

                                                                    System.out.println("Order has been submitted.");
                                                                    System.out.println("Your order will be available for pickup at the Moon Exploration center.");
                                                                }
                                                                else if(final_decision.equals("no")){
                                                                    ab =1;
                                                                    //exit out of here
                                                                }
                                                                else{
                                                                    ab=0;
                                                                    System.out.print("We did not understand what you had typed. Please type in confirm to place your order."
                                                                            + "\nOtherwise type in no to opt of ordering. ");
                                                                    final_decision = krb.next();
                                                                    final_decision = final_decision.toLowerCase();
                                                                }
                                                            }
                                                        }

                                                        while( t == 0 && !result4.next()){
                                                            z=0;
                                                            System.out.print("We don't recognize the id that you wrote. If you wish to exit, please type in exit. \n"
                                                                    + "Otherwise type in any other character to continue: ");
                                                            String dec = krb.next();
                                                            dec =dec.toLowerCase();
                                                            if(dec.equals("exit")){
                                                                return;
                                                            }
                                                            else{
                                                                System.out.print("Please enter a vehicle id of the vehicle you wish to purchase: ");
                                                                while (!krb.hasNextBigDecimal()) { 
                                                                    System.out.println("only numbers! Type in the model id. "); 
                                                                    System.out.print("Enter your vehicle id number: ");
                                                                    krb.next(); // discard 
                                                                }
                                                                car_wish_to_own = krb.nextBigDecimal();
                                                                specific_model = "select v_id,v_model_used.m_id as m_id,model.modelname as model_name,year,model_type,list_price from v_model_used, "
                                                                + "model where v_id = ? and v_model_used.modelname = model.modelname and v_model_used.m_id = model.m_id ";
                                                                ps5 = con.prepareStatement(specific_model);
                                                                ps5.setBigDecimal(1,car_wish_to_own);
                                                                //ps5.setString(2, "Model M");
                                                            }
                                                            result4 = ps5.executeQuery();
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        else if(option.equals("exit") || option.equals("no")){
                                            wrong_key =1;
                                            System.out.println("No used car was purchased here.");
                                        }
                                        else{
                                            System.out.print("We did not understand what you typed. If you want to exit type in 'exit'. "
                                                    + "\nOtherwise type in yes if you want to purchase a used car: ");
                                            option = krb.next();
                                            option=option.toLowerCase();
                                        }
                                    }    
                                }
                                else{
                                    //return;
                                    abc =0;
                                    System.out.print("We did not understand what you said. Type in exit to exit. \nType in yes "
                                    + "to continue to update your information\nOtherwise type in no to not update your information: ");
                                    option = krb.next();
                                    option = option.toLowerCase();
                                    if(option.equals("exit")){
                                        return;
                                    }
                                }
                            }
                        }
                        else{
                            abcd =0;
                            System.out.print("We did not understand what you said. Are you sure you want to exit? Type in yes to exit. otherwise type in no "
                            + "to continue to update your information: ");
                            secondoption = krb.next();
                            secondoption = secondoption.toLowerCase();
                        }
                    }
                }
                
            }while(result.next());
            con.close();
        }catch(SQLException e){
            System.out.println("Unable to continue with the program.");
            //e.printStackTrace();
        }catch(NoSuchElementException e){
            System.out.println("Early exit of program.");
        }
        
    }
    
    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
	}

	Random r = new Random();
	return r.nextInt((max - min) + 1) + min;
    }
    
    
    public String enter_first_last_name(){
        Scanner inputOfNames = new Scanner(System.in);
        System.out.print("Please enter your first name or if you have"
                + " multiple first names enter them: ");
        String ts="";
        String ts1 = null;
        ts = inputOfNames.nextLine();
        ts1=ts;
        String name_initial;
        while ( !ts1.matches("[a-zA-ZÀ-ÿ\\-\\'\\. ]+")
            ||ts1.isEmpty()
            || ts1.length() < 2) {
            System.out.println("Invalid attempt. Try again with only alphabet characters"
                    + " and a length of greater than 2 characters.");

            System.out.print("Enter your first name or if you have"
                    + " multiple first names enter them: ");
            ts1 = inputOfNames.nextLine();
        }
        name_initial=ts1;
 
        System.out.print("Please enter your last name or if you have"
                + " multiple last names enter them: ");
        ts = inputOfNames.nextLine();
        ts1=ts;
        while ( !ts1.matches("[a-zA-ZÀ-ÿ\\-\\'\\. ]+")
            ||ts1.isEmpty()
            || ts1.length() < 2) {
            System.out.println("Invalid attempt. Try again with only alphabet characters"
                    + " and a length of greater than 2 characters.");

            System.out.print("Enter your last name: ");
            ts1 = inputOfNames.nextLine();
        }
        name_initial += " ";
        name_initial +=ts1;

        System.out.println(name_initial);
        return name_initial;
    }
    
    public int getId(){
        Scanner id_in = new Scanner(System.in);
        System.out.print("Please enter your userid: ");
        
        try{
            while (!id_in.hasNextInt() ) { 
                System.out.println("only numbers! Type in the model id. "); 
                System.out.print("Enter your userid number: ");
                id_in.next(); // discard 
            }
        }catch(InputMismatchException e){
            System.out.println("Exception caught");
        }
        int id_global = id_in.nextInt();
        return id_global;
    }
    
    public void updateInfo(){
        System.out.println("Update Info");
        Scanner inputOfNames = new Scanner(System.in);
        System.out.print("Do you want to update your name? Type in yes, otherwise type in no: ");
        String option = "";
        String ts = "";
        String ts1 = null;
        option = inputOfNames.next();
        option = option.toLowerCase();
        switch (option) {
            case "yes":
                name = a.enter_first_last_name();
                System.out.println("Name:"+name);
                break;
            case "no":
                System.out.println("Name is not updated.");
                //name = name;
                System.out.println("Name: "+name);
                break;
            default:
                System.out.println("Name is not updated.");
                System.out.println("Name: "+name);
                break;
        }
        System.out.print("Do you want to update your address? Type in yes, otherwise type in no: ");
        option = inputOfNames.next();
        option = option.toLowerCase();
        switch (option) {
            case "yes":
                //street num
                System.out.print("Enter Street Number: ");
                while (!inputOfNames.hasNextInt() ) {
                    System.out.println("only integers! Type in your street number. ");
                    System.out.print("Enter your Street Number: ");
                    inputOfNames.next(); // discard
                }   street_number = inputOfNames.nextInt();
                inputOfNames.nextLine();
                System.out.print("Enter Street Name: ");
                street_name = inputOfNames.nextLine();
                while (!street_name.matches("[a-zA-Z_\\-\\. ]+")
                        ||street_name.isEmpty()
                        ||street_name.length() < 2) {
                    System.out.println("Invalid street name try again.");
                    System.out.print("Enter your steet name: ");
                    street_name = inputOfNames.nextLine();
                }   System.out.print("Do you want to enter an apartment number? ");
                option= inputOfNames.nextLine();
                option = option.toLowerCase();
                if(option.equals("yes") ){
                    System.out.print("Type apartment number: ");
                    
                    while (!inputOfNames.hasNextInt()) {
                        System.out.println("only integers! Type in your apartment number. "); 
                        System.out.print("Enter your Apartment Number: ");
                        inputOfNames.next(); // discard
                    } 
                    apt_number = inputOfNames.nextInt();
                    inputOfNames.nextLine();
                }
                else if(option.equals("no")){
                    apt_number=null;
                    
                }
                else{
                    
                }   System.out.print("Enter City: ");
                city = inputOfNames.nextLine();
                while (!city.matches("[a-zA-Z_\\- ]+")
                        ||city.isEmpty()
                        ||city.length() < 2) {
                    System.out.println("Invalid city name try again.");
                    System.out.print("Enter your city name: ");
                    city = inputOfNames.nextLine();
                }   //krb.next();
                System.out.print("Enter State/Province: ");
                state = inputOfNames.nextLine();
                while (!state.matches("[a-zA-Z_\\- ]+")
                        ||state.isEmpty()
                        ||state.length() < 2) {
                    System.out.println("Invalid state/province name try again.");
                    System.out.print("Enter your state/province name: "); 
                    state = inputOfNames.nextLine();
                }   System.out.print("Enter zip code: ");
                while (!inputOfNames.hasNextInt()) {
                    System.out.println("only integers! Type in your zip code number. ");
                    System.out.print("Enter your zip code Number: ");
                    inputOfNames.next(); // discard
                }   zip_code= inputOfNames.nextInt();
                inputOfNames.nextLine();
                System.out.print("Enter Country: ");
                country = inputOfNames.nextLine();
                while (!country.matches("[a-zA-Z_\\- ]+")
                        ||country.isEmpty()
                        ||country.length() < 2) {
                    System.out.println("Invalid country name try again.");
                    System.out.print("Enter your country name: "); 
                    country = inputOfNames.nextLine();
                }
                System.out.print("Enter Planet: ");
                planet = inputOfNames.nextLine();
                while (!planet.matches("[a-zA-Z_\\- ]+")
                        ||planet.isEmpty()
                        ||country.length() < 2) {
                    System.out.println("Invalid planet name try again.");
                    System.out.print("Enter your planet name: "); 
                    planet = inputOfNames.nextLine();
                }
               
                break;
            case "no":
                System.out.println("Your address is not updated.");
                street_number=street_number;
                street_name=street_name;
                apt_number=apt_number;
                city=city;
                state=state;
                zip_code= zip_code;
                country=country;
                planet = planet;
                break;
                
            default:
                System.out.println("We did not understand what you wrote. Your address is not updated.");
                break;
        }

        System.out.print("Do you want to update your email address? Type in yes. Otherwise type any other key: ");
        option = inputOfNames.next();
        option=option.toLowerCase();
        if(option.equals("yes")){
            System.out.print("Enter email address: ");
            email_addr= inputOfNames.next(); 
        }
        else{
            System.out.println("Email address was not updated. ");
            email_addr=email_addr;
        }
        
        System.out.print("Do you want to update your credit card info? Type in yes, otherwise type in no: ");
        option = inputOfNames.next();
        option = option.toLowerCase();
        switch (option) {
            case "yes":
                System.out.print("Type in your new credit/debit number: ");
                while(!(inputOfNames.hasNextLong())){
                    System.out.println("Please enter a numeric format under 18 characters.");
                    System.out.print("Enter your credit card number or debit card number: ");
                    inputOfNames.next();
                }
                num = inputOfNames.nextLong();
                break;
            case "no":
                System.out.println("Credit/Debit card is not updated.");
                break;
            default:
                System.out.println("Credit/Debit card is not updated.");
                break;
                
        }
      
    }
    
    public void SummaryInfo(){
        System.out.println("The specifications of your vehicle:");
        if(optionSelected.isEmpty()){
            System.out.println("Model Type Selected: " + initialModelType + "\nModel ID: " + initialModelID +
                    "\nModel Name: " + initialModelName + "\nYear: " + initialModelYear +"\nPackage selected: " + packageSelected+
                    "\nInitial Price: $"+initialPrice + "\nFinal Price: $" + totalpricepaid);
        }
        else if(!optionSelected.isEmpty()){
            System.out.println("Model Type Selected: " + initialModelType + "\nModel ID: " + initialModelID + 
                "\nModel Name: " + initialModelName + "\nYear: " + initialModelYear + 
                "\nPackage selected: " + packageSelected+"\nAdditional options selected: "+Arrays.toString(optionSelected.toArray())+
                "\nInitial Price: $"+initialPrice + "\nFinal Price: $" + totalpricepaid);
        }

    }
    
    public void confirmNewAccount(){
        System.out.println("Summary of new account:");
        if(apt_number == null){
            System.out.println("Name: "+name+ " ID:" +id +   "\nAddress: "+street_number+ " " +street_name+ " "+city+", " +state+" " + zip_code
            +"\nCountry: "+country +" Planet: "+planet +" \nEmail address: "+email_addr );
        }
        else if(!(apt_number== null)){
            System.out.println("Name: "+name+ " ID: " +id+"\nAddress: "+street_number+ " " +street_name+ " Apt Number: "+apt_number+" " +city+", "+state+" " + zip_code
            +"\nCountry: "+country +" Planet: "+planet +" \nEmail address: "+email_addr);
        }
    }
    
    public void confirmUpdatedAccount(){
        System.out.println("Summary of updated account:");
        if(apt_number == null){
            System.out.println("Name: "+name+ " ID:" +id +   "\nAddress: "+street_number+ " " +street_name+ " "+city+", "+state+" " + zip_code
            +"\nCountry: "+country +" Planet: "+planet +" \nEmail address: "+email_addr );
        }
        else if(!(apt_number== null)){
            System.out.println("Name: "+name+ " ID: " +id+"\nAddress: "+street_number+ " " +street_name+ " Apt Number: "+apt_number+" " +city+", "+state+" " + zip_code
            +"\nCountry: "+country +" Planet: "+planet +" \nEmail address: "+email_addr);
        }
    }

}