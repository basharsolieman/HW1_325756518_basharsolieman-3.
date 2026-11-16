
import java.util.ArrayList;
import java.util.Date;

public class EmailMessage extends Message implements IDigital {

    private String subject;
    private ArrayList<File> attachments;

    // Constructor with all fields
    public EmailMessage(String sender, String content,
                        Date sendDate, boolean isRead,
                        String subject, ArrayList<File> attachments) {

        super(sender, content, sendDate, isRead);
        setSubject(subject);
        setAttachments(attachments);
    }

    // Constructor with current date and empty attachments
    public EmailMessage(String sender, String content,
                        boolean isRead, String subject) {

        this(sender, content, new Date(), isRead,
             subject, new ArrayList<File>());
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Subject cannot be empty");
        }
        this.subject = subject;
    }

    public ArrayList<File> getAttachments() {
        return new ArrayList<File>(attachments);
    }

    public void setAttachments(ArrayList<File> attachments) {
        if (attachments == null) {
            throw new IllegalArgumentException(
                "Attachments cannot be null");
        }

        if (attachments.contains(null)) {
            throw new IllegalArgumentException(
                "Attachments cannot contain null");
        }

        this.attachments = new ArrayList<File>(attachments);
    }

    // Add attachment
    public void addAttachment(File file) {
        if (file == null) {
            throw new IllegalArgumentException(
                "Attachment cannot be null");
        }

        attachments.add(file);
    }

    // Remove all matching attachments
    public void removeAttachment(File file)
            throws AttachmentException {

        if (file == null) {
            throw new IllegalArgumentException(
                "Attachment cannot be null");
        }

        boolean removed = attachments.removeIf(
            attachment ->
                attachment.getFilename().equals(file.getFilename())
                && attachment.getFileType().equals(file.getFileType())
        );

        if (!removed) {
            throw new AttachmentException(
                "Attachment not found: " + file.getFilename());
        }
    }

    @Override
    public String printCommunicationMethod() {
        return "Sent via Email Server";
    }

    @Override
    public String generatePreview() {
        return "[Email] Subject: " + subject
                + " | From: " + getSender();
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Subject: " + subject
                + ", Attachments: " + attachments;
    }
}
