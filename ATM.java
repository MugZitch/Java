import java.awt.*;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

public class ATM {
    private Bank bank;
    private ATMScreen as;
    private Client client;
    private DisplayText text = new DisplayText("Insert", new Point(10, 50));
    private CardReader card;
    private ScreenButton button1 = new ScreenButton("1", new Point(100, 150));
    private ScreenButton button2 = new ScreenButton("2", new Point(130, 150));
    private ScreenButton button3 = new ScreenButton("3", new Point(160, 150));
    private ScreenButton button4 = new ScreenButton("4", new Point(100, 180));
    private ScreenButton button5 = new ScreenButton("5", new Point(130, 180));
    private ScreenButton button6 = new ScreenButton("6", new Point(160, 180));
    private ScreenButton button7 = new ScreenButton("7", new Point(100, 210));
    private ScreenButton button8 = new ScreenButton("8", new Point(130, 210));
    private ScreenButton button9 = new ScreenButton("9", new Point(160, 210));
    private ScreenButton button0 = new ScreenButton("0", new Point(130, 240));
    private Keypad keypad = new Keypad("keypad");

    public ATM(Bank bank) {
        card = new CardReader("Card");
        this.bank = bank;
        as = new ATMScreen();
        Frame f = new Frame("My ATM");
        f.setBounds(200, 200, 500, 450);
        f.setBackground(Color.BLUE);
        f.addWindowListener(new MyWindowAdapter(f));
        f.add(as);
        f.setVisible(true);
        doTransaction();
    }

    private void doTransaction() {
        text.giveOutput("Insert your card");
        as.add(text);
        while (client == null) {
        String input = card.getInput();
        client = bank.get(input);
            if (client != null) {
                text.giveOutput("Welcome " + client.getName());
                as.add(text);
            } else {
                text.giveOutput("Sorry but this Card doesn't exist");
                as.add(text);
            }
        }
        addKeypad();
        checkButtons();
    }
    private void addKeypad(){
        as.add(button1);
        as.add(button2);
        as.add(button3);
        as.add(button4);
        as.add(button5);
        as.add(button6);
        as.add(button7);
        as.add(button8);
        as.add(button9);
        as.add(button0);
    }

    private void checkButtons(){
        ArrayList<InputDevice> lijst = new ArrayList<InputDevice>();

        lijst.add(button1);
        lijst.add(button2);
        lijst.add(button3);
        lijst.add(button4);
        lijst.add(button5);
        lijst.add(button6);
        lijst.add(button7);
        lijst.add(button8);
        lijst.add(button9);
        lijst.add(button0);
        while(true) {
            for (InputDevice Inputdevice : lijst) {
                String input = Inputdevice.getInput();
                if(input == null){

                }else{
                    System.out.println(input);
                }
                Thread.yield();
            }
        }
    }
}
