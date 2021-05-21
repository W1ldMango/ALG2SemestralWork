package app.App;


import utils.DateDisplay;
import app.Payment.Payment;
import app.RezervationData.Rezervation;
import utils.EmailSender;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 * The class responsible for the performance of the user interface
 */

public class App {

    static Scanner sc = new Scanner(System.in);
    private static Rezervation a = new Rezervation();
    private static Authorization one = new Authorization();

    /**
     * The method responsible for calling commands from the Rezervation class
     * to successfully or unsuccessfully reserve a room
     * @throws IOException
     */

    public void roomRezervation() throws IOException {
        DateDisplay b = new DateDisplay();
        int adults, kids,days;
        String lux, agreeornot;
        boolean rezervationCompleted = false;
        while (!rezervationCompleted) {
            System.out.println("How many adults?");
            adults = controlInt();
            System.out.println("How many kids?");
            kids = controlInt();
            System.out.println("Do you want a lux room? Yes/No");
            lux = sc.next().toLowerCase(Locale.ROOT);
            do {
                if (lux.equals("yes") || lux.equals("no")) {
                    if (a.roomChoising(adults, kids, lux)) {
                        System.out.println("Do you agree?");
                        agreeornot = sc.next().toLowerCase(Locale.ROOT);
                        if (agreeornot.equals("yes")) {
                            System.out.println("Today is \n" + b.displayCalendarNow());
                            System.out.println("How many days do you want to stay with us? ");
                            days = controlInt();
                            System.out.println("Your room reservation will be made before this date \n" + b.displayCalendarEnd(days));
                            System.out.println("It will cost: " + a.lastPrice(days) + " for " + days + " days");
                            System.out.println("Pay for it?");
                            agreeornot = sc.next().toLowerCase(Locale.ROOT);
                            if (agreeornot.equals("yes")) {
                                rezervationCompleted = true;
                                break;
                            } else {
                                System.out.println("Please correct your data");
                                break;
                            }
                        } else {
                            System.out.println("Please correct your data");
                            break;
                        }
                    } else {
                        System.out.println("Sorry, but this room is occupied, please choose another one");
                        break;
                    }
                } else {
                    System.out.println("Sorry. unreachable answer, please try again ");
                    break;

                }
            }while (!lux.equals("yes") || !lux.equals("no"));

        }
    }

    /**
     * A method that restricts the input of invalid data and crashes the entire program
     * @return int values
     */

    public int controlInt() {
        int n;
        while(true) {
            try {
                n = sc.nextInt();
                break;
            } catch (InputMismatchException ex) {
                System.out.println("Sorry. unreachable answer, please try again");
                sc.nextLine();
            }
        }
        return n;
    }

    /**
     * The method responsible for the payment,
     * the last stage of the reservation of the number
     * and sending an email with a message about the successful payment
     * @return a boolean value that indicates a successful or unsuccessful payment
     */

    public static boolean paymentAndRezervationCompleted() throws IOException {
        Payment b = new Payment();
        EmailSender post = new EmailSender();
        boolean completed = false;
        do {
            b.PaymentW();
            if (b.paymentStatus()) {
                System.out.println("Payment successful");
                completed = true;
                break;
            } else {
                System.out.println("Something went wrong");
            }
        } while (!completed);
        a.writeRezervationNumber(a.rezervationRoom());
        post.paymentEmail();
        return completed;
    }


    /**
     * Login interface, which includes a variety of choices
     * @return an int value that indicates the exit from the login menu.
     */

    public int loginMenu() throws IOException {
        App app = new App();
        String loginInfo = ("   1.Rezervation \n   2.Check rezervation status \n   3.Go home");
        String email;
        boolean nextStep = false;
        int choice;
        a.writeRezervationEmail(one.loginForm());
        do {
            System.out.println(loginInfo);
            choice = controlInt();
            switch (choice) {
                case 1 -> {
                    app.roomRezervation();
                    do {
                        if (paymentAndRezervationCompleted()) {
                            nextStep = true;
                            break;
                        }
                    } while (!nextStep);
                }
                case 2 -> {
                    System.out.println("Enter email");
                    email = sc.next();
                    if (a.rezervationCheck(email).equals("Nothing")) {
                        System.out.println("You have: " + a.rezervationCheck(email));
                    } else {
                        System.out.println("You have a registered room number: " + a.rezervationCheck(email));
                    }
                }
            }
        } while (choice != 3);
        return choice;
    }

    /**
     * The method responsible for the health of the user interface. Includes, a choice of all possibilities
     */


    public void hotelInterface() throws IOException {
        String firstMeet = ("If you don't have an account, you need to create one or please log in " +
                "\n   1.Registration \n   2.Login \n   3.Go home");
        int choice;
        do {
            System.out.println(firstMeet);
            choice = controlInt();
            switch (choice) {
                case 1 -> one.registrationForm();
                case 2 -> choice = loginMenu();
                case 3 -> System.out.println("Good Bye");
            }
        } while (choice != 3);
    }
}

