///*  Author:     Ricardo Mokveld
//    Date:       01-04-2019
//    Studentnr:  0971051
//*/
//public class Client {
//    private String name;
//    private String pin;
//    private int balance;
//
//    Client(String name, String pin, int balance){
//        this.name = name;
//        this.pin = pin;
//        this.balance = balance;
//    }
//
//    public String getName(){
//        return this.name;
//    }
//
//    public boolean checkPin(String PIN){
//        return PIN.equals(this.pin);
//        /*if (PIN.equals(this.pin) ){
//            return true;
//        }else{
//            return false;
//        }*/
//    }
//
//    public int getBalance(String PIN){
//        if (checkPin(PIN)){
//            return this.balance;
//        }else{
//            return Integer.MIN_VALUE;
//        }
//    }
//
//    public void deposit(int moneyIn){
//        this.balance += moneyIn;
//    }
//
//    public boolean withdraw(int moneyOut, String PIN){
//        if((moneyOut <= this.balance) && (checkPin(PIN))){
//            this.balance = this.balance - moneyOut;
//            return true;
//        }else{
//            return false;
//        }
//
//    }
//}
