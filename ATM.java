/*  Author:     Ricardo Mokveld
    Date:       01-04-2019
    Studentnr:  0971051
*/
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Thread.sleep;

public class ATM {
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private Date date = new Date();
    private ATMScreen as;
    private DisplayText text = new DisplayText("Insert", new Point(10, 50));
    private DisplayText text2 = new DisplayText("Insert", new Point(10, 300));
    private DisplayText textTen = new DisplayText("textTen", new Point(250, 150));
    private DisplayText textTwenty = new DisplayText("textTwenty", new Point(250, 180));
    private DisplayText textFifty = new DisplayText("textFifty", new Point(250, 210));
    private CardReader card;
    private ReceiptPrinter printer;
    private Keypad keypad;
    private String input;
    private String cardnumber = "";
    private int money;
    private int pinAttempts;
    private int ten = 0;
    private int twenty = 0;
    private int fifty = 0;
    private boolean pinCorrect = false;
    private boolean delete = false;
    private String wachtwoord = "";
    private String chosenAmount = "";
    private boolean moneyTaken = false;
    private boolean textAdded = false;
    private ScreenButton button1 = new ScreenButton("1", new Point(100, 130));
    private ScreenButton button2 = new ScreenButton("2", new Point(130, 130));
    private ScreenButton button3 = new ScreenButton("3", new Point(160, 130));
    private ScreenButton button4 = new ScreenButton("4", new Point(100, 160));
    private ScreenButton button5 = new ScreenButton("5", new Point(130, 160));
    private ScreenButton button6 = new ScreenButton("6", new Point(160, 160));
    private ScreenButton button7 = new ScreenButton("7", new Point(100, 190));
    private ScreenButton button8 = new ScreenButton("8", new Point(130, 190));
    private ScreenButton button9 = new ScreenButton("9", new Point(160, 190));
    private ScreenButton button0 = new ScreenButton("0", new Point(130, 220));
    private ScreenButton deleteButton = new ScreenButton("delete", new Point(160, 220));
    private ScreenButton okayButton = new ScreenButton("okay", new Point(160, 220));
    private ScreenButton buttonDeposit = new ScreenButton("Deposit", new Point(100, 150));
    private ScreenButton buttonWithdraw = new ScreenButton("Withdraw", new Point(200, 150));
    private ScreenButton buttonCheckBalance = new ScreenButton("Check Balance", new Point(100, 175));
    private ScreenButton buttonQuickCash = new ScreenButton("Quick cash 70", new Point(100, 225));
    private ScreenButton Quitbutton = new ScreenButton("Quit", new Point(330, 300));
    private ScreenButton button20 = new ScreenButton("20", new Point(100, 150));
    private ScreenButton button50 = new ScreenButton("50", new Point(200, 150));
    private ScreenButton button100 = new ScreenButton("100", new Point(100, 225));
    private ScreenButton button200 = new ScreenButton("200", new Point(200, 225));
    private ScreenButton buttonChooseAmount = new ScreenButton("Choose amount", new Point(100, 255));
    private ScreenButton buttonja = new ScreenButton(" JA", new Point(100, 150));
    private ScreenButton buttonnee = new ScreenButton("NEE", new Point(200, 150));
    private ScreenButton buttonMinTen = new ScreenButton("-10", new Point(110, 150));
    private ScreenButton buttonMinTwenty = new ScreenButton("-20", new Point(110, 180));
    private ScreenButton buttonMinFifty = new ScreenButton("-50", new Point(110, 210));
    private ScreenButton buttonPlusTen = new ScreenButton("+10", new Point(170, 150));
    private ScreenButton buttonPlusTwenty = new ScreenButton("+20", new Point(170, 180));
    private ScreenButton buttonPlusFifty = new ScreenButton("+50", new Point(170, 210));
    private ScreenButton buttonOkay = new ScreenButton("okay", new Point(120, 240));

    public ATM() {
        keypad = new Keypad("keypad");
        printer = new ReceiptPrinter("printer");
        card = new CardReader("Card");
        as = new ATMScreen();
        Frame f = new Frame("My ATM");
        f.setBounds(200, 200, 500, 450);
        f.setBackground(Color.darkGray);
        f.addWindowListener(new MyWindowAdapter(f));
        f.add(as);
        f.setVisible(true);
        doTransaction();
    }

    private void doTransaction() {
        /*
        In deze method worden de andere methods aangeroepen die nodig zijn voor een transactie.
        Ook worden hier de teksten toegevoegd aan het scherm, die nodig zijn voor de transactie.
         */
            as.clear();
            text.giveOutput("Press # and insert your card");
            as.add(text);
            checkClient();
            as.clear();
            text.giveOutput("Please enter your pin");
            as.add(text);
            addButtons();
            checkButtons();
            as.clear();
            Quickmenu();
            as.clear();
            text.giveOutput("Do you want a receipt");
            as.add(text);
            checkReceiptButtons();
            as.clear();
            text.giveOutput("Now dispensing " + money);
            as.add(text);
            text2.giveOutput("Please take your card and cash");
            as.add(text2);
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            restart();
    }
    private void checkClient(){
        /*
        Deze method checkt of het ingevoerde kaartnummer gelijk is aan één van de accounts die bekend is bij deze bank.
        Deze wordt herhaaldelijk aangeroepen zodat iemand niet verder kan als de client niet bij de bank bekend is.
         */

        do {
            cardnumber = card.getInput();
        }while(cardnumber == null);

//        if (!Database.checkcard(cardnumber)){
//                text.giveOutput("Sorry but this Card doesn't exist");
//                as.add(text);
//                try {
//                    sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                restart();
//        }
    }

    private void addButtons() {
        /*
        In deze method worden de buttons voor de pincode toegevoegd aan het scherm
         */
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
        as.add(Quitbutton);
        as.add(deleteButton);
        as.add(okayButton);
    }

    private void checkButtons() {
        /*
        Deze method checkt herhaaldelijk of de buttons zijn ingedrukt of niet.
        Deze method checkt ook of het ingevoerde pincode gelijk is aan de pincode die bekend is bij de bank.
        Als de pincode fout is dan krijgt de klant hierop een melding en kan hij of zij het opnieuw proberen.
         */
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
        lijst.add(Quitbutton);
        lijst.add(keypad);
        lijst.add(deleteButton);
        lijst.add(okayButton);
        String textBox = "";
        while (true) {
            for (InputDevice Inputdevice : lijst) {
                input = Inputdevice.getInput();

                Thread.yield();
                if (input == "Quit") {
                    restart();
                }
                if (input == "delete"){
                    if(wachtwoord.length() <= 1){
                        wachtwoord = "";
                        input= "";
                        textBox = "";
                        delete = true;
                    }else{
                        String strNew = wachtwoord.substring(0, wachtwoord.length() - 1);
                        wachtwoord = strNew;
                        input = "";
                        strNew = textBox.substring(0, textBox.length() - 2);
                        textBox = strNew;
                    }
                }
                if (input != null) {
                    wachtwoord += input;
                    if(delete){
                        text.giveOutput(textBox);
                        delete = false;
                    }
                    else {
                        text.giveOutput(textBox += "x");
                        as.add(text);
                    }
                    if (wachtwoord.length() >= 4) {
                        pinAttempts = Database.checkpin(cardnumber, wachtwoord);
                        if (pinAttempts == -1) {
                            //de pin is correct
                            pinCorrect = true;
                            break;
                        } else if (pinAttempts > 3) {
                            text.giveOutput("U heeft dit te vaak gedaan");
                            text2.giveOutput("Uw kaart is geblokeerd!");
                            as.add(text);
                            as.add(text2);
                            wachtwoord = "";
                            textBox = "";
                        } else {
                            text.giveOutput("U heeft nog " + (3 - pinAttempts) + " pogingen over. \n");
                            text2.giveOutput("Probeer opnieuw");
                            as.add(text);
                            as.add(text2);
                            wachtwoord = "";
                            textBox = "";
                        }
                    }
                }
            }
            if (pinCorrect) {
                break;
            }
        }
    }



    private void Quickmenu(){

        text.giveOutput("What would you like to do?");
        as.add(text);

        if(cardnumber.substring(4, 8).equals("MODO")){
            as.add(buttonCheckBalance);
        }

        as.add(buttonDeposit);
        as.add(buttonWithdraw);
        as.add(buttonQuickCash);
        as.add(Quitbutton);

        ArrayList<InputDevice> lijst = new ArrayList<InputDevice>();
        lijst.add(buttonDeposit);
        lijst.add(buttonWithdraw);
        lijst.add(buttonCheckBalance);
        lijst.add(buttonQuickCash);
        lijst.add(Quitbutton);

        while (true) {
            for (InputDevice Inputdevice : lijst) {
                input = Inputdevice.getInput();
                Thread.yield();
                if (input != null) {
                    break;
                }
            }
            if (input == "Withdraw"){
                as.clear();
                text.giveOutput("you may now choose an amount");
                as.add(text);
                addMoneyButtons();
                checkMoneyButtons();
                break;
            }
            else if(input == "Deposit"){
                as.clear();
                text.giveOutput("Coming soon...");
                as.add(text);
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                as.clear();
                Quickmenu();
            }
            else if(input == "Check Balance"){
                text2.giveOutput("Your saldo: " + Database.checksaldo(cardnumber, wachtwoord));
                as.add(text2);
            }else if(input == "Quick cash 70"){
                money = 70;
                if(Database.withdraw(cardnumber, wachtwoord, "70")){
                    break;
                }
            }else if(input == "Quit"){
                restart();
            }
        }
    }

    private void addMoneyButtons() {
        /*
        Deze method voegt de buttons toe die nodig zijn om een bedrag te kiezen.
         */
        as.add(button20);
        as.add(button50);
        as.add(button100);
        as.add(button200);
        as.add(Quitbutton);
        as.add(buttonChooseAmount);
    }

    private void checkMoneyButtons() {
        /*
        Deze method checkt herhaaldelijk of de buttons om een bedrag te kiezen zijn ingedrukt of niet.
        Verder kijkt deze method of het gekozen bedrag wel kan.
        Als de klant niet genoeg geld heeft dan krijgt de klant hierop een melding en kan deze het opnieuw proberen.
         */
        ArrayList<InputDevice> lijst = new ArrayList<InputDevice>();
        lijst.add(button20);
        lijst.add(button50);
        lijst.add(button100);
        lijst.add(button200);
        lijst.add(Quitbutton);
        lijst.add(buttonChooseAmount);
        while (true) {
            for (InputDevice Inputdevice : lijst) {
                input = Inputdevice.getInput();
                Thread.yield();
                if(input == "Quit"){
                    restart();
                }
                if(input == "Choose amount"){
                    //chooseAmount();
                    chooseBills();
                    break;
                }
                if (input != null) {
                    money = Integer.parseInt(input);
                    if(Database.withdraw(cardnumber, wachtwoord, input)){
                        moneyTaken = true;
                        break;
                    }else {
                        text.giveOutput("You don't have enough money..");
                        as.add(text);
                        if (!textAdded) {
                            as.add(text);
                            textAdded = true;
                        }
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        text.giveOutput("Please try again.");
                        as.add(text);
                    }
                }
            }
            if (moneyTaken) {
                break;
            }
        }
    }

    private void checkReceiptButtons() {
        /*
        Deze method voegt en checkt de buttons voor een bon.
        Deze bon wordt vervolgens ook uitgeprint op het console.
         */
        as.add(buttonja);
        as.add(buttonnee);

        ArrayList<InputDevice> lijst = new ArrayList<InputDevice>();
        lijst.add(buttonja);
        lijst.add(buttonnee);
        while (true) {
            for (InputDevice Inputdevice : lijst) {
                input = Inputdevice.getInput();
                Thread.yield();
                if (input != null) {
                    break;
                }
            }
            if (input == " JA") {
                printer.giveOutput("This is your receipt");
                printer.giveOutput("====================");
                printer.giveOutput("Money withdrawn: " + money);
                printer.giveOutput("====================");
                printer.giveOutput("Time of withdrawn: " + dateFormat.format(date));
                printer.giveOutput("====================");
                break;
            } else if (input == "NEE") {
                break;
            }
        }
    }

    private void chooseBills(){
        as.clear();
        text.giveOutput("Choose an amount up to 500");
        as.add(text);
        as.add(buttonPlusTen);
        as.add(buttonMinTen);
        as.add(buttonPlusTwenty);
        as.add(buttonMinTwenty);
        as.add(buttonPlusFifty);
        as.add(buttonMinFifty);
        as.add(buttonOkay);

        ArrayList<InputDevice> lijst = new ArrayList<InputDevice>();
        lijst.add(buttonPlusTen);
        lijst.add(buttonMinTen);
        lijst.add(buttonPlusTwenty);
        lijst.add(buttonMinTwenty);
        lijst.add(buttonPlusFifty);
        lijst.add(buttonMinFifty);
        lijst.add(buttonOkay);

        while (true) {
            for (InputDevice Inputdevice : lijst) {
                input = Inputdevice.getInput();

                Thread.yield();

                if(input == "-10"){
                    if(ten > 0){
                        money = money - 10;
                        ten--;
                    }
                    textTen.giveOutput(ten + "x 10 = " + 10*ten);
                    as.add(textTen);
                    text2.giveOutput("Total = " + money);
                    as.add(text2);
                    System.out.println(money);
                }
                if(input == "+10"){
                    if (money + 10 > 500){
                        text2.giveOutput("you can't go higher than 500");
                        as.add(text2);
                    }else {
                        money = money + 10;
                        ten++;
                        text2.giveOutput("Total = " + money);
                        as.add(text2);
                    }
                    textTen.giveOutput(ten + "x 10 = " + 10*ten);
                    as.add(textTen);
                    System.out.println(money);
                }
                if(input == "-20"){
                    if(twenty > 0){
                        money = money - 20;
                        twenty--;
                    }
                    textTwenty.giveOutput(twenty + "x 20 = " + 20*twenty);
                    as.add(textTwenty);
                    text2.giveOutput("Total = " + money);
                    as.add(text2);
                    System.out.println(money);
                }
                if(input == "+20"){
                    if (money + 20 > 500){
                        text2.giveOutput("you can't go higher than 500");
                        as.add(text2);
                    }else{
                        money = money + 20;
                        twenty++;
                        text2.giveOutput("Total = " + money);
                        as.add(text2);
                    }
                    textTwenty.giveOutput(twenty + "x 20 = " + 20*twenty);
                    as.add(textTwenty);
                    System.out.println(money);
                }
                if(input == "-50"){
                    if(fifty > 0){
                        money = money - 50;
                        fifty--;
                    }
                    textFifty.giveOutput(fifty + "x 50 = " + 50*fifty);
                    as.add(textFifty);
                    text2.giveOutput("Total = " + money);
                    as.add(text2);
                    System.out.println(money);
                }
                if(input == "+50"){
                    if (money + 50 > 500){
                        text2.giveOutput("you can't go higher than 500");
                        as.add(text2);
                    }else {
                        money = money + 50;
                        fifty++;
                        text2.giveOutput("Total = " + money);
                        as.add(text2);
                    }
                    textFifty.giveOutput(fifty + "x 50 = " + 50*fifty);
                    as.add(textFifty);
                    System.out.println(money);
                }
                if(input == "okay"){
                    text2.giveOutput("Total = " + money);
                    as.add(text2);
                    moneyTaken = true;
                    break;
                }
            }if(moneyTaken){
                break;
            }
        }
    }


    private void restart(){
        money = 0;
        wachtwoord = "";
        input = "";
        cardnumber = "";
        pinAttempts = 0;
        chosenAmount = "";
        moneyTaken = false;
        pinCorrect = false;
        ten = 0;
        twenty = 0;
        fifty = 0;
        doTransaction();
    }
}


