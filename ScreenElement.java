import java.awt.*;

public abstract class ScreenElement extends ATMElement {
    private Point pos;

    public ScreenElement(String name, Point pos){
    super(name);
    this.pos = pos;

    }

    public abstract void setContainer(Container java);
}
