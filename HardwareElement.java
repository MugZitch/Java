/*  Author:     Ricardo Mokveld
    Date:       01-04-2019
    Studentnr:  0971051
*/
public abstract class HardwareElement extends ATMElement{
    private boolean power = false;

    public HardwareElement(String name){
        super(name);
    }

    public void powerOn(){
        this.power = true;
    }

    public void powerOff(){
        this.power = false;
    }
}
