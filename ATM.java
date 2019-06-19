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
    private DisplayText text3 = new DisplayText("Total", new Point(250, 100));
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
    int bills10;
    int bills20;
    int bills50;

    private ScreenButton deleteButton = new ScreenButton("delete", new Point(160, 220));
    private ScreenButton okayButton = new ScreenButton("okay", new Point(160, 220));
    private ScreenButton buttonDeposit = new ScreenButton("Deposit", new Point(100, 150));
    private ScreenButton buttonWithdraw = new ScreenButton("Withdraw", new Point(200, 150));
    private ScreenButton buttonCheckBalance = new ScreenButton("Check Balance", new Point(100, 175));
    private ScreenButton buttonQuickCash = new ScreenButton("Quick cash 70", new Point(100, 225));
    private ScreenButton Quitbutton = new ScreenButton("Quit", new Point(330, 340));
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

        ArrayList<Integer> bills = Database.getbills();
        bills10 = bills.get(0);
        bills20 = bills.get(1);
        bills50 = bills.get(2);

        do {
            cardnumber = card.getInput();
            //cardnumber = "SU13MODO609140";
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

        lijst.add(keypad);
        lijst.add(Quitbutton);
        lijst.add(okayButton);
        lijst.add(deleteButton);
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
                        if(pinAttempts > 3){
                            text.giveOutput("Uw kaart is geblokkeerd");
                            try {
                                sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            restart();
                        }
                        if (pinAttempts == -1) {
                            //de pin is correct
                            pinCorrect = true;
                            break;
                        }else {
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
        as.add(Quitbutton);

        //Communicatie met de server om te kijken of de biljetten aanwezig zijn.
        //Als dit niet zo is, dan is de optie quick cash niet beschikbaar.
        if((twenty + 1 < bills20) && (fifty + 1 < bills50)){
            as.add(buttonQuickCash);
        }

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
                chooseBills();
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
                    Database.atm(0, 1, 1);
                    break;
                }else{
                    text2.giveOutput("You don't have enough money");
                }
            }else if(input == "Quit"){
                restart();
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
        /* Deze method voegt de optie toe om zelf biljetten te kiezen
         * ook geeft deze aan of het briefje dat jij wilt er wel is.
          * of als er bijvoorbeeld maar 5 zijn dan zegt die dat nadat je al 5x 10 hebt gekozen*/
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
        as.add(Quitbutton);

        ArrayList<InputDevice> lijst = new ArrayList<InputDevice>();
        lijst.add(buttonPlusTen);
        lijst.add(buttonMinTen);
        lijst.add(buttonPlusTwenty);
        lijst.add(buttonMinTwenty);
        lijst.add(buttonPlusFifty);
        lijst.add(buttonMinFifty);
        lijst.add(buttonOkay);
        lijst.add(Quitbutton);

        while (true) {
            for (InputDevice Inputdevice : lijst) {
                input = Inputdevice.getInput();

                Thread.yield();
                if(input != null){
                    text2.giveOutput("");
                    as.add(text2);
                }

                if(input == "-10"){
                    if(ten > 0){
                        money = money - 10;
                        ten--;
                    }
                    textTen.giveOutput(ten + "x 10 = " + 10*ten);
                    as.add(textTen);
                    text3.giveOutput("Total = " + money);
                    as.add(text3);
                }
                if(input == "+10"){
                    if (money + 10 > 500){
                        text2.giveOutput("you can't go higher than 500");
                        as.add(text2);
                    }else if(ten + 1 > bills10){
                        text2.giveOutput("We are out of tens");
                        as.add(text2);
                    }
                    else {
                        money = money + 10;
                        ten++;
                        text3.giveOutput("Total = " + money);
                        as.add(text3);
                    }
                    textTen.giveOutput(ten + "x 10 = " + 10*ten);
                    as.add(textTen);
                }
                if(input == "-20"){
                    if(twenty > 0){
                        money = money - 20;
                        twenty--;
                    }
                    textTwenty.giveOutput(twenty + "x 20 = " + 20*twenty);
                    as.add(textTwenty);
                    text3.giveOutput("Total = " + money);
                    as.add(text3);
                }
                if(input == "+20"){
                    if (money + 20 > 500){
                        text2.giveOutput("you can't go higher than 500");
                        as.add(text2);
                    }else if(twenty + 1 > bills20){
                        text2.giveOutput("We are out of twenty's");
                        as.add(text2);
                    }
                    else{
                        money = money + 20;
                        twenty++;
                        text3.giveOutput("Total = " + money);
                        as.add(text3);
                    }
                    textTwenty.giveOutput(twenty + "x 20 = " + 20*twenty);
                    as.add(textTwenty);
                }
                if(input == "-50"){
                    if(fifty > 0){
                        money = money - 50;
                        fifty--;
                    }
                    textFifty.giveOutput(fifty + "x 50 = " + 50*fifty);
                    as.add(textFifty);
                    text3.giveOutput("Total = " + money);
                    as.add(text3);
                }
                if(input == "+50"){
                    if (money + 50 > 500){
                        text2.giveOutput("you can't go higher than 500");
                        as.add(text2);
                    }else if(fifty + 1 > bills50){
                        text2.giveOutput("We are out of fifty's");
                        as.add(text2);
                    }
                    else {
                        money = money + 50;
                        fifty++;
                        text3.giveOutput("Total = " + money);
                        as.add(text3);
                    }
                    textFifty.giveOutput(fifty + "x 50 = " + 50*fifty);
                    as.add(textFifty);
                }
                if(input == "okay"){
                    text3.giveOutput("Total = " + money);
                    as.add(text3);
                    moneyTaken = true;
                    chosenAmount = Integer.toString(money);
                    if(Database.withdraw(cardnumber, wachtwoord, chosenAmount)){
                        Database.atm(ten, twenty, fifty);
                        moneyTaken = true;
                    }
                    else if (money == 0){
                        text2.giveOutput("You have to chose an amount");
                        moneyTaken = false;
                    }
                    else{
                        text2.giveOutput("You don't have enough money");
                        moneyTaken = false;
                    }
                }
                if(input == "Quit"){
                    restart();
                }
                if(moneyTaken){
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


