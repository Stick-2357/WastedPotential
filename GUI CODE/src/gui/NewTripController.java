/*     Team: Wasted Potential
    Project: ProDeo Router
       File: NewTripController.java
Description: Takes mulitple addresses, and finds quickest route */
package gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class NewTripController {
    static Parent root;
    
    FXMLLoader backLoader;
    FXMLLoader studentLoader;
    FXMLLoader driverLoader;
    
    @FXML private Button backButton;
    @FXML private TextField startLocationField;
    @FXML private TextField endLocationField;
    
    public static ArrayList<String> chaperones = new ArrayList<>();
    public static ArrayList<String> kids = new ArrayList<>();
    
    public void initialize() {
        backLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        studentLoader = new FXMLLoader(getClass().getResource("AddStudentForTrip.fxml"));
        driverLoader = new FXMLLoader(getClass().getResource("AddDriverForTrip.fxml"));
        startLocationField.setText("206 NE Chipman Rd, Lee's Summit, MO 64063");
    }
    
    @FXML void BackButtonPressed(ActionEvent event) {
        try {
            root = backLoader.load();
            Scene scene = ((Node)event.getSource()).getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.print("IOException occured while loading second view");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    @FXML void addRemoveStudent(ActionEvent event) {
        try {
            root = studentLoader.load();
            Scene scene = ((Node)event.getSource()).getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.print("IOException occured while loading second view");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    //moves to addStudentForTrip
    @FXML void addRemoveDriver(ActionEvent event) {
        try {
            root = driverLoader.load();
            Scene scene = ((Node)event.getSource()).getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.print("IOException occured while loading second view");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    @FXML void createTrip(ActionEvent event) throws SQLException, MalformedURLException, IOException {
        if (chaperones.size() < 2) {
            System.out.println("Not enough chaperones");
        } else if (startLocationField.getText() == "") {
            System.out.println("No start location");
        } else if (endLocationField.getText() == "") {
            System.out.println("not end location");
        } else {
            System.out.println("Working...");
            
            String genericURL = "http://www.mapquestapi.com/directions/v2/optimizedroute?key=2D8iqF8UC43KY06X3cHIPWDoTGP1AR9d&from=";
            ArrayList<String> addresses = new ArrayList<>();
            ArrayList<String> kidNames = new ArrayList<>();
            //adds addresses to arraylist
            //create our mysql database connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wastedpotential", "root", "yoast");
            PreparedStatement st = con.prepareStatement("SELECT * FROM address \n" +
                                                        "INNER JOIN kid_address \n" +
                                                        "ON address.idaddress = kid_address.idaddress\n" +
                                                        "INNER JOIN kids\n" +
                                                        "ON kid_address.KidID = kids.KidID");
            ResultSet r1=st.executeQuery();

            //iterate through the java resultset
            while (r1.next()) {
                int addressID = r1.getInt("idaddress");
                int kidID = r1.getInt("KidID");
                String street = r1.getString("address");
                String city = r1.getString("city");
                String state = "MO";
                int zip = r1.getInt("zipcode");
                String firstName = r1.getString("first");
                String lastName = r1.getString("last");
                
                if (kids.contains(firstName + " " + lastName)) {
                    //add result to arraylist
                    String tempAddress = street + ",+" + city + ",+" + state + ",+" + zip;
                    addresses.add(tempAddress.replaceAll(" ", "\\+"));
                    kidNames.add(firstName + " " + lastName);
                }
            }
            st.close();
            String startingAddress = startLocationField.getText();
            String endingAddress = endLocationField.getText();
        
            //concats all of the addresses
            String addressesString = startingAddress.replace(" ", "\\+") + "&to=";  
            for (String address : addresses) {
                addressesString += address + "&to=";
            }
            addressesString += endingAddress.replace(" ", "\\+");

            //get the input stream from the URL, and reads it        
            URL url = new URL(genericURL + addressesString);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            //read order, and return addresses in that order
            String line;
            while ((line = br.readLine()) != null) {
                int indexOf = (line.indexOf("locationSequence") + 19);
                String order = line.substring(indexOf, line.indexOf("]", indexOf));
                String[] orderOfAddressIndexs = order.split(",");
                ArrayList<String> orderOfAddresses = new ArrayList<>();
            
                for (int i = 1; i < orderOfAddressIndexs.length - 1; i++) {
                    orderOfAddresses.add(addresses.get(Integer.parseInt(orderOfAddressIndexs[i]) - 1));
                }
                String output = startingAddress;
                
                for (String address : orderOfAddresses) {
                    output += address.replaceAll("\\+", " ");
                }
                output += endingAddress;
                
                File fi = new File("output.txt");
                FileWriter wr = new FileWriter(fi, false); //create a file writer
                try (BufferedWriter w = new BufferedWriter(wr)) { //create buffered writer to write
                    w.write(output); //write
                              
                    w.flush();
                }
            }
        }
    }
    
    //function to add another kid to the kids array list
    public static void addToKids(String kidToAdd) {
        kids.add(kidToAdd);
    }
    
    //a function that returns all the kids in the kids array list as a string
    public static String getKids() {
        String allKids = new String();
      for (int x = 0; x < kids.size(); x++)  {
        allKids = kids.get(x);
      }
        return allKids;
    }
    
    //function to add another chaperone to the chaperones array list
    public static void addToChaperones(String chaperonesToAdd) {
        chaperones.add(chaperonesToAdd);
    }
    
    //function to return all the chaperones from the chaperones array list as a string
    public static String getChaperones() {
        String allChaperones = new String();
        for (int x = 0; x < chaperones.size(); x++) {
            allChaperones = chaperones.get(x);
        }
        
        return allChaperones;
    }
}
