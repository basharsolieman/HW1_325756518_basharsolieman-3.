
import java.util.Date;

public class SmsMessage extends Message implements IDigital {

    private String phoneNumber;

    // Constructor with all fields
    public SmsMessage(String sender, String content,
                      Date sendDate, boolean isRead,
                      String phoneNumber) throws SmsException {

        super(sender, content, sendDate, isRead);
        setPhoneNumber(phoneNumber);
    }

    // Constructor with current date
    public SmsMessage(String sender, String content,
                      boolean isRead, String phoneNumber)
                      throws SmsException {

        this(sender, content, new Date(), isRead, phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
            throws SmsException {

        if (phoneNumber == null ||
            !phoneNumber.matches("\\+?[0-9]{9,15}")) {

            throw new SmsException(
                "Invalid phone number");
        }

        this.phoneNumber = phoneNumber;
    }

    // Additional method
    public int getMessageLength() {
        return getContent().length();
    }

    @Override
    public String printCommunicationMethod() {
        return "Sent via SMS Network";
    }

    @Override
    public String generatePreview() {
        String content = getContent();

        if (content.length() > 15) {
            content = content.substring(0, 15) + "...";
        }

        return "[SMS] " + getSender() + ": " + content;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Phone Number: " + phoneNumber;
    }
}
