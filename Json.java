import org.json.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Json {
    //this function returns true if the call is succesfull
    public static Boolean getStatus(String json){
        JSONObject obj = new JSONObject(json);
        String status = obj.getString("status");
        return status.equals("0");
    }

    public static int getAttempts(String json){
        JSONObject obj = new JSONObject(json);
        try{
            int pinAttempts = obj.getInt("pin_attempts");
            return pinAttempts;
        }catch(Exception e){
            return 4; //card is blocked
        }
    }

    //this function returns the balance
    public static int getBalance(String json){
        JSONObject obj = new JSONObject(json);
        String status = obj.getString("status");
        int balance = obj.getInt("balance");
        if(status.equals("0")){
            return balance;
        }else {
            return -1;
        }
    }

    public static ArrayList getBills(String json){
        JSONObject obj = new JSONObject(json);
        String status = obj.getString("status");
        if(status.equals("0")){
            int ten = obj.getInt("ten");
            int twenty = obj.getInt("twenty");
            int fifty = obj.getInt("fifty");
            ArrayList<Integer> bills = new ArrayList<>();
            bills.add(ten);
            bills.add(twenty);
            bills.add(fifty);
            return bills;
        }else{
            return null;
        }
    }
}
