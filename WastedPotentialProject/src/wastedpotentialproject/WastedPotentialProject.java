/*        Team: Wasted Potential
 *     Project: ProDeo Router
 * Description: Takes mulitple addresses, and finds quickest route */
package wastedpotentialproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.sql.*;

public class WastedPotentialProject {
    final static String SELECT_QUERY = "SELECT * FROM address";

    /**
     * @param args the command line arguments
     * @throws java.net.MalformedURLException
     */
    public static void main(String[] args) throws MalformedURLException, IOException, ClassNotFoundException, SQLException {
        String genericURL = "http://www.mapquestapi.com/directions/v2/optimizedroute?key=2D8iqF8UC43KY06X3cHIPWDoTGP1AR9d&from=";
        ArrayList<String> addresses = new ArrayList<>();
        //adds addresses to arraylist
        //create our mysql database connection
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wastedpotential", "root", "yoast");
        PreparedStatement st = con.prepareStatement(SELECT_QUERY);
        ResultSet r1=st.executeQuery();

        //iterate through the java resultset
        while (r1.next()) {
          int id = r1.getInt("idaddress");
          String street = r1.getString("address");
          String city = r1.getString("city");
          String state = "MO";
          int zip = r1.getInt("zipcode");

          //add result to arraylist
          String tempAddress = street + ",+" + city + ",+" + state + ",+" + zip;
          addresses.add(tempAddress.replaceAll(" ", "\\+"));
        }
        st.close();
        String startingAddress = "1101 NW Innovation Parkway, Lee's Summit, MO, 64086";
        
        //concats all of the addresses
        String addressesString = startingAddress.replace(" ", "\\+") + "&to=";  
        for (String address : addresses) {
            addressesString += address + "&to=";
        }
        addressesString += "\b\b\b\b ";

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
            
             for (int i = 1; i < orderOfAddressIndexs.length; i++) {
                orderOfAddresses.add(addresses.get(Integer.parseInt(orderOfAddressIndexs[i]) - 1));
            }
            System.out.println(startingAddress);
            for (String address : orderOfAddresses) {
                    System.out.println(address.replaceAll("\\+", " "));
            }
        }
    }
}
