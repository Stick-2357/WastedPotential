/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wastedpotentialproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
/**
 *
 * @author apowers987
 */
public class WastedPotentialProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        String line = "";
        String genericURL = "http://www.mapquestapi.com/directions/v2/optimizedroute?key=2D8iqF8UC43KY06X3cHIPWDoTGP1AR9d&from=";
        ArrayList<String> addresses = new ArrayList<>();
        addresses.add("1101+NW+Innovation+Parkway,+Lee's+Summit,+MO");
        addresses.add("555+SW+Trailpark+DR,+Lee's+Summit,+MO");
        addresses.add("117+N+Eastglen+Dr,+Raymore,+MO");
        addresses.add("2801+NE+McBaine+Dr,+Lee's+Summit,+MO");
        addresses.add("554+SW+Trailpark+DR,+Lee's+Summit,+MO");
        addresses.add("1306+Tyler+LN,+Greenwood,+MO");
        String urlString = "";  
        for (String address : addresses) {
            urlString += address + "&to=";
        }
        urlString += "\b\b\b\b ";
        URL url = new URL(genericURL + urlString);
        // Get the input stream through URL Connection
        URLConnection con = url.openConnection();
        InputStream is =con.getInputStream();

        // Once you have the Input Stream, it's just plain old Java IO stuff.

        // For this case, since you are interested in getting plain-text web page
        // I'll use a reader and output the text content to System.out.

        // For binary content, it's better to directly read the bytes from stream and write
        // to the target file.

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        // read each line and write to System.out
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
