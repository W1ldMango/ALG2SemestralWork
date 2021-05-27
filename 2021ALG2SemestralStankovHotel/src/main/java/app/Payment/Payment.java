package app.Payment;

import app.App.App;

import java.util.Scanner;

/**
 * The class that is fully responsible for the payment
 */

public class Payment {

    static Scanner sc = new Scanner(System.in);
    App control = new App();

    /**
     * The main method of the class that reproduces all manipulations with the user
     * If you specify admin when entering your first or last name, then part of the payment will be skipped
     * @return returns a string with data
     */


    public String PaymentW() {
        String firstname, lastname, creditCardNum, expirationDate;
        int cvv;
        System.out.println("Please input your credit card info");
        System.out.print("First name: ");
        firstname = sc.next();
        System.out.print("Last name: ");
        lastname = sc.next();
        if (firstname.equals("admin") || lastname.equals("admin")){
            PaymentInfo admin = new PaymentInfo(firstname,lastname);
            return admin.toString();
        }
        System.out.print("Card number: ");
        do {
            creditCardNum = sc.next();
            if (creditCardFormat(creditCardNum)) {
                System.out.println("This card number is not valid");
                System.out.print("Card number: ");
            }
        } while (creditCardFormat(creditCardNum));
        System.out.print("Expiration date: ");
        do {
            expirationDate = sc.next();
            if (!expirationFormatCheck(expirationDate)) {
                System.out.println("This expiration date is not valid");
                System.out.print("Expiration date: ");
            }
        } while (!expirationFormatCheck(expirationDate));
        System.out.print("Please fill out a CVV: ");
        do {
            cvv = control.controlInt();
            if (cvvCheck(cvv)) {
                System.out.println("Invalid format");
                System.out.print("Please fill out a CVV: ");
            }
        }while(cvvCheck(cvv));
        PaymentInfo a = new PaymentInfo(creditCardNum,firstname,lastname,expirationDate,cvv);
        return a.toString();
    }

    /**
     * Checking the validity of the credit card number
     * 4-Visa, 5-MasterCard, 1-AmericanExpress
     * @param creditCardNum a credit card number
     * @return successful or not
     */

    private boolean creditCardFormat(String creditCardNum) {
        if ((!(creditCardNum.startsWith("4") || creditCardNum.startsWith("5") || creditCardNum.startsWith("1"))) || creditCardNum.length() != 16) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Validation check
     * @param exp date of expiration
     * @return successful or not
     */

    private boolean expirationFormatCheck(String exp) {
        String[] parts = exp.split("/");
        String[] months = {"01","02","03","04","05","06","07","08","09","10","11","12",};
        int year = Integer.parseInt(parts[1]);
        boolean answer = false;
        for (int i = 0; i < months.length; i++) {
            System.out.println(parts[1] + months[i]);
            if (parts[0].contains(months[i])) {
                if (exp.length() == 5 && exp.contains("/") &&  year > 20) {
                    answer = true;
                }
            }
        } return answer;
    }

    /**
     * CVV validation check
     * @param cvv a cvv cod
     * @return successful or not
     */

    private boolean cvvCheck(int cvv) {
        int len = String.valueOf(cvv).length();
        if (len == 3) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * A small game with a probability of successful payment
     * @return successful or not
     */

    public boolean paymentStatus() {
        double a = Math.random()*2;
        if (a < 1) {
            return true;
        } else {
            return false;
        }
    }


}
