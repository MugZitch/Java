import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScreenButton extends ScreenElement implements InputDevice, ActionListener{
    private Button button;
    private boolean inputAvailable;
    public ScreenButton(String name, Point pos){
        super(name, pos);
        button = new Button(name);
        button.setBounds(pos.x, pos.y, 10 + 15 * name.length(), 25);
        button.addActionListener(this);
    }

    @Override
    public void setContainer(Container java) {
        java.add(button);
    }

    public String getInput(){
        if (inputAvailable){
            inputAvailable = false;
            return name;
        }else{
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inputAvailable = true;
    }
}
