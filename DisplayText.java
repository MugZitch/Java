/*  Author:     Ricardo Mokveld
    Date:       01-04-2019
    Studentnr:  0971051
*/
import java.awt.*;
public class DisplayText extends ScreenElement implements OutputDevice {
    private Label label;

    public DisplayText(String name, Point pos){
        super(name, pos);
        label = new Label();
        label.setForeground(Color.WHITE);
        if( name.equals("textTen") || name.equals("textTwenty") || name.equals("textFifty") ){
            label.setFont(new Font("SansSerif", Font.PLAIN, 15));
        }else{
            label.setFont(new Font("SansSerif", Font.BOLD, 30));
        }
        label.setBounds(pos.x, pos.y, 500, 35);
    }

    @Override
    public void setContainer(Container java) {
        java.add(label);
    }

    @Override
    public void giveOutput(String output) {
        label.setText(output);
    }
}
