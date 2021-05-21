package utils;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    /**
     * Sending a message with successful registration
     * @param recipient = email
     */

       public void registrationEmail(String recipient) {
        // Add recipient
        String to = recipient ;

        // Add sender
        String from = "Hotel";
        final String username = "semestralworkhotel@gmail.com";//your Gmail username
        final String password = "Gjhju112233";//your Gmail password

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject
            message.setSubject("Registration successfully");

            // Put the content of your message
            message.setText("Thank you for registering, please log in to your account");

            // Send message
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sending a message with a successful payment
     * @throws FileNotFoundException which comes from the method buyerEmail()
     */

    public void paymentEmail() throws FileNotFoundException {
        // Add recipient
        EmailSender post = new EmailSender();
        String to = post.buyerEmail() ;

        // Add sender
        String from = "Hotel";
        final String username = "semestralworkhotel@gmail.com";//your Gmail username
        final String password = "Gjhju112233";//your Gmail password

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject
            message.setSubject("Payment successful");

            // Put the content of your message
            message.setText("Your payment was successful. You can view the room number that you paid for in your personal account");

            // Send message
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that takes an email from a file
     * @return email
     * @throws FileNotFoundException
     */

    public String buyerEmail() throws FileNotFoundException {
           Scanner sc = new Scanner(new File("roomRezervation.txt"));
           String email = "";
           String trash;
           while (sc.hasNext()) {
               email = sc.useDelimiter(":").next();
               trash = sc.useDelimiter(":").nextLine().replace(":", "");
           }
           sc.close();
           return email;
    }

}

