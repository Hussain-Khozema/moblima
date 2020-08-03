package Entity;

import java.io.Serializable;

/**
 * Payment class contains information when customer books a ticket
 * @author SS3 Group 8
 *
 */
public class Payment implements Serializable{

    /**
     * Unique Transaction ID when payment is successful
     */
    private String transactionID;

    /**
     * Customer's Name, used when searching for transaction history
     */
    private String customerName;

    /**
     * Customer's Phone Number
     */
    private String phoneNumber;

    /**
     * Customer's Email Address
     */
    private String emailAddress;

    /**
     * Total Payment for seats booked 
     */
    private double paymentAmt;

    /**
     * Constructor for Payment Object
     * @param transactionID Transaction ID when booking
     * @param customerName Customer's Name
     * @param phoneNumber Customer's Phone Number
     * @param email Customer's Email
     * @param paymentAmt Total amount paid
     */
    public Payment(String transactionID, String customerName, String phoneNumber, String email, double paymentAmt) {
        this.transactionID = transactionID;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = email;
        this.paymentAmt = paymentAmt;
    } // Constructor

    /**
     * Gets Transaction ID
     * @return String containing Transaction ID
     */
    public String getTransactionID() {
        return transactionID;
    } // return get transaction ID

    /**
     * Gets Customer's Name
     * @return String containing Customer's Name
     */
    public String getCustName() {
        return customerName;
    } // get Customer Name
 
    /**
     * Gets Phone Number
     * @return String containing Customer's Phone Number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    } // get Customer Phone Number

    /**
     * Gets Customer's Email
     * @return String containing Customer's Email
     */
    public String getEmail() {
        return emailAddress;
    } // get email

    /**
     * Gets Total amount paid
     * @return Double value of total amount paid
     */
    public double getAmount() {
        return paymentAmt;
    } // get payment amount

    /**
     * Generates String with Payment information
     * @return String with Payment information
     */
    public String displayPayment() {
        return "TransactionID: " + transactionID + "\nName: " + customerName + "\nMobile Number: " + phoneNumber + "\nEmail: " + emailAddress;
    } // Display payment information
}