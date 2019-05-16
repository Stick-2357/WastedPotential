/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wastedpotential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
/**
 *
 * @author apowers987
 */
public class WastedPotential {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        String line = "";
       URL url = new URL("http://www.mapquestapi.com/directions/v2/route?key=2D8iqF8UC43KY06X3cHIPWDoTGP1AR9d&from=1101+NW+Innovation+Parkway,+Lee's+Summit,+MO+64086&to=2801+NE+McBaine+Dr,+Lee's+Summit,+MO+64064");

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
            String[] array = line.split(",");
        
        for (String array1 : array) {
             System.out.println(array1);
        }
        }
      
    }
    
}
