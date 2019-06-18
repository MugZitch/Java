/*  Author:     Ricardo Mokveld
    Date:       01-04-2019
    Studentnr:  0971051
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CardReader extends HardwareElement implements InputDevice {
    private BufferedReader reader;
    private static String input;
    public CardReader(String name){
        super(name);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String getInput() {
        input = null;
        Serial.listenSerial();
        return input;
    }

    public static void setInput(String data){
        input = data;
    }
}
