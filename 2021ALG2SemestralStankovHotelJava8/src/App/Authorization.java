package App;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author justf
 */
public class Authorization {

    private static Scanner sc = new Scanner(System.in);
    private List<String> dbemails = new ArrayList<>();
    private List<String> dbpass = new ArrayList<>();

    /**
     * The method that is fully responsible for registration
     * (recording the email and password in the database,
     * sending messages to the mail about successful registration)
     */


    public void registrationForm() throws IOException {
        readDB();
        String email;
        String pass;
        String concat;
        System.out.println("Enter your gmail address");
        do {
            email = sc.next();
            if (!setEmail(email)) {
                System.out.println("Email is already used or does not contain @gmail.com");
                System.out.println("Please try another");
            } else {
                break;
            }
        } while(!setEmail(email));
        System.out.println("Enter password");
        do {
            pass = sc.next();
            if (!setPassword(pass)) {
                System.out.println("Your password can't be less then 8 symbols, try again");
            } else {
                System.out.println("Registration completed successfully");
                break;
            }
        } while(!setPassword(pass));
        concat = email + ":" + pass;
        writeDB(concat);
    }

    /**
     * Responsible for the health of the login form, checking the username and password from the database
     * @return email;
     */

    public String loginForm() throws IOException {
        String password;
        String email;
        boolean suc;
        do {
            System.out.println("Enter email");
            email = sc.next();
            System.out.println("Enter password");
            password = sc.next();
            if (emailCheck(email) && passwordCheck(password)){
                System.out.println("Login successfully");
                suc = true;
            } else {
                System.out.println("Incorrect email or password, please try again");
                suc = false;
            }

        }while(!suc);
        return email;
    }

    /**
     * Reading the database
     */


    public void readDB() throws IOException {
        Scanner scanner = new Scanner(new File("2021ALG2SemestralStankovHotelJava8/db.txt"));
        try {
            do {
                dbemails.add(scanner.useDelimiter(":").next());
                dbpass.add(scanner.useDelimiter(":").nextLine().replace(":", ""));
            } while(scanner.hasNextLine());
            scanner.close();
        } catch (NoSuchElementException ex) {
            System.out.print("");
        }

    }

    /**
     * Write to the database
     * @param a username and password string with a separator ':'
     * @throws IOException
     */


    public void writeDB(String a) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("2021ALG2SemestralStankovHotelJava8/db.txt", true));
        writer.newLine();
        writer.append(a);
        writer.close();
    }

    private boolean setEmail(String email) throws IOException {
        readDB();
        boolean suc;
        if (!email.contains("@gmail.com") || emailCheck(email)) {
            suc = false;
        } else {
            dbemails.add(email);
            suc = true;
        }
        return suc;
    }

    /**
     * Setting a password during registration
     * @param password a password
     * @return successful or not
     */

    private boolean setPassword(String password) throws IOException {
        readDB();
        boolean suc;
        if (password.length() < 8) {
            suc = false;
        } else {
            dbpass.add(password);
            suc = true;
        }
        return suc;
    }

    /**
     * Checking email in the database
     * @param email a email
     * @return successful or not
     */

    public boolean emailCheck(String email) throws IOException {
        readDB();
        boolean condition = false;
        for (int i = 0; i < dbemails.size(); i++) {
            if (dbemails.get(i).equals(email)) {
                condition = true;
                break;
            }
        } return condition;
    }

    /**
     * Checking password in the database
     * @param password a password
     * @return successful or not
     */

    public boolean passwordCheck(String password) throws IOException {
        readDB();
        boolean condition = false;
        for (int i = 0; i < dbpass.size(); i++) {
            if (dbpass.get(i).equals(password)) {
                condition = true;
                break;
            }
        } return condition;
    }
}