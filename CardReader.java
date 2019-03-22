import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CardReader extends HardwareElement implements InputDevice {
    private BufferedReader reader;
    public CardReader(String name){
        super(name);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String getInput() {
        System.out.println("To simulate inserting card, enter card number");
        try{
            return reader.readLine();
        }
        catch (Exception e){
            return null;
        }
    }
}
