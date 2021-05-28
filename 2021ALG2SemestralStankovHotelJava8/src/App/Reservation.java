package App;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author justf
 */
public class Reservation {

    private int roomNumberReservation;
    private List<String> roomsnumbers = new ArrayList<>();
    private final Room number1 = new Room(1,2, 0, true, 3000);
    private final Room number2 = new Room(2,1, 0, false, 1500);
    private final Room number3 = new Room(3,2, 0, false, 2500);
    private final Room number4 = new Room(4,2, 1, true,3250);
    private final Room number5 = new Room(5,2, 2, true,3500);
    private final Room number6 = new Room(6,2, 1, false,2750);
    private final Room number7 = new Room(7,1, 2, false,2000);

    /**
     * The main method responsible for selecting a room by the user
     * @param places a places for adults
     * @param kids a places for kids
     * @param lux lux room or not
     * @return successful reservation or not
     */

    public boolean roomChoising(int places, int kids, String lux) throws IOException {
        Reservation a = new Reservation();
        List<Room> luxrooms = new ArrayList<>();
        List<Room> notluxrooms = new ArrayList<>();
        luxrooms.add(number1);
        luxrooms.add(number4);
        luxrooms.add(number5);
        notluxrooms.add(number2);
        notluxrooms.add(number3);
        notluxrooms.add(number6);
        notluxrooms.add(number7);
        boolean choice = false;
        if (lux.equals("yes")) {
            for (int i = 0; i < luxrooms.size(); i++) {
                if (places == luxrooms.get(i).getPlaces() && kids == luxrooms.get(i).getKids()) {
                    roomNumberReservation = luxrooms.get(i).getNumber();
                    if (rezervationCheck()) {
                        System.out.println(luxRoomOutput(places,kids) + " will be cost " + luxrooms.get(i).getPrice() + " per night");
                        luxImageRoom();
                        choice = true;
                        break;
                    }
                }
            }
        } else {
            for (int i = 0; i < notluxrooms.size(); i++) {
                if (places == notluxrooms.get(i).getPlaces() && kids == notluxrooms.get(i).getKids()) {
                    roomNumberReservation = notluxrooms.get(i).getNumber();
                    if (rezervationCheck()) {
                        System.out.println(notLuxRoomOutput(places,kids) + " will be cost " + notluxrooms.get(i).getPrice() + " per night");
                        nonLuxImageRoom();
                        choice = true;
                        break;
                    }
                }
            }
        }
        if (choice) {
            a.temp(roomNumberReservation);
        }
        return choice;
    }

    /**
     * Output of the text about the reservation of luxury rooms
     * @param places a places for adults
     * @param kids a places for kids
     * @return string with text
     */

    private String luxRoomOutput(int places, int kids) {
        return "We think this room is right for you " + places + " places for adults " + kids + " for kids, free wifi, bar and parking";
    }

    /**
     * Output of the text about the reservation of non-luxury rooms
     * @param places a places for adults
     * @param kids a places for kids
     * @return string with text
     */
    private String notLuxRoomOutput(int places, int kids) {
        return "We think this room is right for you " + places + " places for adults " + kids + " for kids, free wifi and parking";
    }

    /**
     * Displaying a photo of a luxury room
     */

    private static void luxImageRoom() throws IOException {
        BufferedImage img= ImageIO.read(new File("2021ALG2SemestralStankovHotelJava8/lux.jpg"));
        ImageIcon icon= new ImageIcon(img);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(1024,681);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Displaying a photo of a non-luxury room
     */

    private static void nonLuxImageRoom() throws IOException {
        BufferedImage img=ImageIO.read(new File("2021ALG2SemestralStankovHotelJava8/nonlux.jpg"));
        ImageIcon icon=new ImageIcon(img);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(1024,681);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Calculating the price for the entire stay at the hotel
     * @param days for which the room is reserved
     * @return value int with price
     */


    public int lastPrice(int days) {
        List<Room> rooms = new ArrayList<>();
        int price = 0;
        rooms.add(number1);
        rooms.add(number2);
        rooms.add(number3);
        rooms.add(number4);
        rooms.add(number5);
        rooms.add(number6);
        rooms.add(number7);
        for (int i = 0; i < rooms.size(); i++) {
            if (roomNumberReservation == rooms.get(i).getNumber()) {
                price = rooms.get(i).getPrice() * days;
                break;

            }
        }
        return price;
    }

    /**
     * Checking the room reservation by the user in roomChoising method
     * @return Successful or not
     */

    private boolean rezervationCheck() throws IOException {
        roomsnumbers.clear();
        String trash;
        Scanner scanner = new Scanner(new File("2021ALG2SemestralStankovHotelJava8/roomRezervation.txt"));
        try {
            do {
                trash = scanner.useDelimiter(":").next();
                roomsnumbers.add(scanner.useDelimiter(":").nextLine().replace(":",""));
            } while (scanner.hasNextLine());
            scanner.close();
        } catch (NoSuchElementException ex) {
            System.out.print("");
        }
        boolean output = true;
        for (int i = 0; i < roomsnumbers.size(); i++) {
            if (roomsnumbers.get(i).equals(Integer.toString(roomNumberReservation))) {
                output = false;
                break;
            }
        } return output;
    }

    /**
     * Write email to the database of bath reservations
     * @param email a email
     */

    public void writeRezervationEmail(String email) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("2021ALG2SemestralStankovHotelJava8/roomRezervation.txt", true));
        writer.newLine();
        writer.append(email);
        writer.close();
    }

    /**
     * Write to temporary file that is constantly changing and has only the last value of the reserved room in it
     * @param n room number
     * @throws IOException
     */

    protected void temp(int n) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("2021ALG2SemestralStankovHotelJava8/temp.txt", false));
        //writer.newLine();
        writer.append("");
        writer.append(Integer.toString(n));
        writer.close();
    }

    /**
     * @return the room number
     */
    public int rezervationRoom(){
        return this.roomNumberReservation;
    }

    public void writeRezervationNumber(int n) throws IOException {
        Scanner scanner = new Scanner(new File("2021ALG2SemestralStankovHotelJava8/temp.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("2021ALG2SemestralStankovHotelJava8/roomRezervation.txt", true));
        String temp = scanner.next();
        //writer.newLine();
        String concat = ":" + temp;
        writer.append(concat);
        writer.close();
        scanner.close();
    }

    /**
     * Checking the room reservation from the user menu
     * @param email a email
     * @return a room number
     */

    public String rezervationCheck(String email) throws IOException {
        List<String> emailFromDB = new ArrayList<>();
        List<String> rooms = new ArrayList<>();
        String answer = "Nothing";
        Scanner scanner = new Scanner(new File("2021ALG2SemestralStankovHotelJava8/roomRezervation.txt"));
        try {
            do {
                emailFromDB.add(scanner.useDelimiter(":").next());
                rooms.add(scanner.useDelimiter(":").nextLine().replace(":",""));
            } while (scanner.hasNextLine());
            scanner.close();
        } catch (NoSuchElementException ex) {
            System.out.print("");
        }
        try {
            for (int i = 0; i < emailFromDB.size(); i++) {
                if (emailFromDB.get(i).equals(email)) {
                    answer = rooms.get(i);
                }
            }
        }catch (IndexOutOfBoundsException ex) {
            answer = rooms.get(0);
        }
        return answer;
    }
}