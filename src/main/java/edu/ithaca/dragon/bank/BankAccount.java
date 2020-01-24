package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount)  {
        balance -= amount;

    }


    public static boolean isEmailValid(String email){
        String acceptableChar = "abcdefghijklmnopqrstuvwxyz_.-";
        if (email.indexOf('@') == -1){
            return false;
        }else if (!checkEmailValidChar(email, acceptableChar)) {
            return false;
        }else if(email.indexOf('.') == -1){
            return false;
        }else if(!checkAdjacentNonAlphaChar(email, acceptableChar)){
            return false;
        }else{
            return true;
        }

    }

    public static boolean checkEmailValidChar(String email, String acceptableChar){
        for(int i = 0; i < email.length(); i++){
            if(email.charAt(i) != '@'  && acceptableChar.indexOf(email.toLowerCase().charAt(i)) == -1){
                return false;
            }
        }
        return true;
    }

    public static boolean checkAdjacentNonAlphaChar(String email, String acceptableChar){
        for(int i = 0; i < email.length(); i++){
            if(email.charAt(i) != '@'  && (i!=email.length()-1 && i!=0) && acceptableChar.substring(26,29).indexOf(email.toLowerCase().charAt(i)) != -1
            && (acceptableChar.substring(0,26).indexOf(email.toLowerCase().charAt(i-1)) == -1 ||
                    acceptableChar.substring(0,26).indexOf(email.toLowerCase().charAt(i+1)) == -1)){
                return false;
            }
        }
        return true;
    }
}
