import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.json.*;

public class Database{
    private static HttpURLConnection con;
    private static String json;

    public static boolean checkcard(String iban){
        String url = "https://monarchdouglasbank.tk/api/checkcard.php";
        String urlParameters = "iban=" + iban + "&location=Ricardo's-ATM";
        try{
            json = makeConnection(url, urlParameters);
        }catch(Exception e) {
            e.printStackTrace();
        }
        if(Json.getStatus(json)){
            return true;
        }else{
            return false;
        }
    }

    public static int checkpin(String iban, String pin){
        String url = "https://monarchdouglasbank.tk/api/checkpin.php";
        String urlParameters = "iban=" + iban + "&pin=" + pin;
        try{
            json = makeConnection(url, urlParameters);
        }catch(Exception e) {
            e.printStackTrace();
        }
        if(Json.getStatus(json)){
            return -1;
        }else{
            return Json.getAttempts(json);
        }
    }

    public static int checksaldo(String iban, String pin){
        String url = "https://monarchdouglasbank.tk/api/checksaldo.php";
        String urlParameters = "iban=" + iban + "&pin=" + pin;
        try{
            json = makeConnection(url, urlParameters);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return Json.getBalance(json);
    }

    public static boolean withdraw(String iban, String pin, String amount){
        String url = "https://monarchdouglasbank.tk/api/withdraw.php";
        String urlParameters = "iban=" + iban + "&pin=" + pin + "&amount=" + amount + "&location=Ricardo's-ATM";
        try{
            json = makeConnection(url, urlParameters);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return Json.getStatus(json);
    }

    public static boolean atm(int ten, int twenty, int fifty){
        String url = "https://monarchdouglasbank.tk/api/atm.php";
        String urlParameters = "ten=" + ten + "&twenty=" + twenty + "&fifty=" + fifty + "&atm_id=1";
        try{
            json = makeConnection(url, urlParameters);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return Json.getStatus(json);
    }

    public static ArrayList getbills(){
        String url = "https://monarchdouglasbank.tk/api/getbills.php";
        String urlParameters = "auth=e73B9rm0qeEPgQ";
        try{
            json = makeConnection(url, urlParameters);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return Json.getBills(json);
    }

    public static String makeConnection(String url, String urlParameters) throws IOException{
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

        try {
            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData);
            }
            StringBuilder content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            System.out.println(content.toString());
            return content.toString();
        } finally {
            con.disconnect();
        }
    }
}
