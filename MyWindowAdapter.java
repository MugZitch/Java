/*  Author:     Ricardo Mokveld
    Date:       01-04-2019
    Studentnr:  0971051
*/
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyWindowAdapter extends WindowAdapter {
    Frame f;
    MyWindowAdapter(Frame f) {
        this.f = f;
    }
    public void windowClosing(WindowEvent e) {
        f.dispose();
        System.exit(0);
    }
}
