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
            if(startingBalance<0||Balanceformatting(startingBalance)==false){
                throw new IllegalArgumentException("startingBalance: " + startingBalance + " is invalid, cannot create account");

            }
        }

        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public Boolean Balanceformatting(double balances){
        String format = ""+balances;
        int Decimals = format.substring(format.indexOf(".")+1).length();
        if (Decimals<3){
            return true;
        }
        else {
            return false;
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
    public void withdraw (double amount) throws InsufficientFundsException {
        if(Balanceformatting(amount)==false){
            throw new IllegalArgumentException("Amount: " + amount + " is invalid, cannot withdrawal .000 amounts");

        }
        if(amount==(-amount)){
            throw new IllegalArgumentException("Amount: " + amount + " is invalid, cannot withdrawal negative amounts");
        }
        else {
            if (amount >= 0) {
                if (amount > this.balance) {

                    throw new InsufficientFundsException("The amount you wish to withdraw exceeds your balance :" + this.balance);

                }
                else {
                    balance -= amount;

                }

            }
        }
    }


    public static boolean isEmailValid(String email){
        String Substring1=email.substring(0,email.indexOf("@"));
        String Substring2=email.substring(email.indexOf("@")+1,email.indexOf("."));
        String Substring3=email.substring(email.charAt(email.indexOf(".")+1));
        if (email.indexOf('@') == -1){
            return false;
        }
        else {
            if(prefixCheck(Substring1)==true&&prefixCheck(Substring2)==true&&endCheck(Substring3)==true){

            return true;


            }
            else{
                return false;
            }
        }

    }
    public static boolean prefixCheck(String subString1) {
        int i = 0;
        if (subString1.contains("_") == true) {
            int checking = subString1.indexOf("_") + 1;
            return charCheck((subString1.charAt(checking)));

        }

        if (subString1.contains("-") == true) {
            int checking = subString1.indexOf("-") + 1;
            return charCheck((subString1.charAt(checking)));

        }
        if (subString1.contains(".") == true) {
            int checking = subString1.indexOf(".") + 1;
            return charCheck((subString1.charAt(checking)));

        }
        while (i < subString1.length()) {
            char x1 = subString1.charAt(i);
            if ((((int) x1 >= 48 && (int) x1 < 58) || ((int) x1 >= 65 && (int) x1 < 91) || ((int) x1 >= 97 && (int) x1 < 123)) == true) {
                i = i + 1;

            }
            else {
                return false;
            }

        }
        return true;


    }

    public static boolean charCheck(char cCheck) {
        if ((((int) cCheck >= 48 && (int) cCheck < 58) || ((int) cCheck >= 65 && (int) cCheck < 91) || ((int) cCheck >= 97 && (int) cCheck < 123)) == true) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean endCheck(String subString3) {
        String end1 = "cc";
        String end2 = "com";
        String end3 = "org";
        if (subString3.equals(end1)) {
            return true;
        }
        if (subString3.equals(end2)) {
            return true;
        }
        if (subString3.equals(end3)) {
            return true;
        }

        return false;


    }

}
