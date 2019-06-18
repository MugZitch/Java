/*  Author:     Ricardo Mokveld
    Date:       01-04-2019
    Studentnr:  0971051
*/
import java.awt.*;

public class ATMScreen extends java.awt.Container {
    public ATMScreen() {
        super();
        setLayout(null);
    }
    public void add(ScreenElement screenElement) {
        screenElement.setContainer(this);
    }
    public void clear(){
        removeAll();
    }
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.fillRoundRect(417, 330, 35, 35, 10, 10);
        g.fillRect(447, 360, 5, 5);
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 18));
        g.drawString("MD", 420, 350);
        g.setFont(new Font("SansSerif", Font.PLAIN, 12));
        g.drawString("bank", 421, 360);
    }
}