import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Keypad extends HardwareElement implements InputDevice {
    private BufferedReader reader;
    public Keypad(String name){
        super(name);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String getInput(){
        try {
            if(reader.ready()){
                return reader.readLine();
            }else{
                return null;
            }
        }catch (Exception e){
            return null;
        }

    }
}
