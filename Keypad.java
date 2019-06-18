/*  Author:     Ricardo Mokveld
    Date:       01-04-2019
    Studentnr:  0971051
*/
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Keypad extends HardwareElement implements InputDevice {
    private BufferedReader reader;
    private static String input;
    public Keypad(String name){
        super(name);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String getInput() {
        input = null;
        Serial.listenSerial();
        return input;
    }

    public static void setInput(String data){
        input = data;
    }


}

