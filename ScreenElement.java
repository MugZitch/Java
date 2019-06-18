/*  Author:     Ricardo Mokveld
    Date:       01-04-2019
    Studentnr:  0971051
*/
import java.awt.*;

public abstract class ScreenElement extends ATMElement {
    private Point pos;

    public ScreenElement(String name, Point pos){
    super(name);
    this.pos = pos;

    }

    public abstract void setContainer(Container java);
}
