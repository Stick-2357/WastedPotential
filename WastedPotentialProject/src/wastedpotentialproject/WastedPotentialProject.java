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

public class WastedPotentialProject {

    /**
     * @param args the command line arguments
     * @throws java.net.MalformedURLException
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
        String genericURL = "http://www.mapquestapi.com/directions/v2/optimizedroute?key=2D8iqF8UC43KY06X3cHIPWDoTGP1AR9d&from=";
        ArrayList<String> addresses = new ArrayList<>();
        //adds addresses to arraylist
        //TODO read addresses from DB
        addresses.add("1101+NW+Innovation+Parkway,+Lee's+Summit,+MO");
        addresses.add("555+SW+Trailpark+DR,+Lee's+Summit,+MO");
        addresses.add("117+N+Eastglen+Dr,+Raymore,+MO");
        addresses.add("2801+NE+McBaine+Dr,+Lee's+Summit,+MO");
        addresses.add("554+SW+Trailpark+DR,+Lee's+Summit,+MO");
        addresses.add("1306+Tyler+LN,+Greenwood,+MO");
        //concats all of the addresses
        String adressesString = "";  
        for (String address : addresses) {
            adressesString += address + "&to=";
        }
        adressesString += "\b\b\b\b ";
        //makes URL
        URL url = new URL(genericURL + adressesString);
        // Get the input stream through URL Connection
        URLConnection con = url.openConnection();
        InputStream is =con.getInputStream();

        //reads from input stream
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        // read order, and return addresses in that order
        String line;
        while ((line = br.readLine()) != null) {
            int indexOf = (line.indexOf("locationSequence") + 19);
            String order = line.substring(indexOf, line.indexOf("]", indexOf));
            String[] orderOfAddressIndexs = order.split(",");
            ArrayList<String> orderOfAddresses = new ArrayList<>();
            
            for (String address : orderOfAddressIndexs) {
                orderOfAddresses.add(addresses.get(Integer.parseInt(address)));
            }
            for (String address : orderOfAddresses) {
                System.out.println(address.replaceAll("\\+", " "));
            }
        }
    }
}
