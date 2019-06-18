/*  Author:     Ricardo Mokveld
    Date:       01-04-2019
    Studentnr:  0971051
*/
public class ReceiptPrinter extends HardwareElement implements OutputDevice {

    public ReceiptPrinter(String name){
        super(name);
    }

    @Override
    public void giveOutput(String output) {
        System.out.println(output);
    }
}