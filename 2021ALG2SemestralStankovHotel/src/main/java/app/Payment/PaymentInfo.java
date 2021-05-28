package app.Payment;


/**
 * Data for payment
 */

public class PaymentInfo {

    private String creditCardNum;
    private String firstname;
    private String lastname;
    private String expirationDate;
    private int cvv;

    public PaymentInfo(String creditCardNum, String firstname, String lastname, String expirationDate,int cvv) {
        this.creditCardNum = creditCardNum;
        this.firstname = firstname;
        this.lastname = lastname;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    public PaymentInfo(String firstname,String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "PaymentInfo{" +
                "creditCardNum='" + creditCardNum + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvv='" + cvv + '\'' +
                '}';
    }
}
