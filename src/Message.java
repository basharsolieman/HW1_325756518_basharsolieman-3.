
import java.util.ArrayList;
import java.util.Date;

public abstract class Message {

    private String sender;
    private String content;
    private Date sendDate;
    private boolean isRead;

    // Constructor with all fields
    public Message(String sender, String content,
                   Date sendDate, boolean isRead) {
        setSender(sender);
        setContent(content);
        setSendDate(sendDate);
        this.isRead = isRead;
    }

    // Constructor with current date
    public Message(String sender, String content,
                   boolean isRead) {
        this(sender, content, new Date(), isRead);
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        if (sender == null || sender.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Sender cannot be empty");
        }
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Content cannot be empty");
        }
        this.content = content;
    }

    public Date getSendDate() {
        return new Date(sendDate.getTime());
    }

    public void setSendDate(Date sendDate) {
        if (sendDate == null) {
            throw new IllegalArgumentException(
                "Send date cannot be null");
        }
        this.sendDate = new Date(sendDate.getTime());
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    // Search for at least one word in content
    public boolean find(ArrayList<String> words) {
        if (words == null) {
            throw new IllegalArgumentException(
                "Words list cannot be null");
        }

        for (String word : words) {
            if (word != null && !word.isEmpty()
                    && content.toLowerCase()
                    .contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    // Additional method
    public void markAsRead() {
        isRead = true;
    }

    @Override
    public String toString() {
        return "Sender: " + sender
                + ", Content: " + content
                + ", Send Date: " + sendDate
                + ", Is Read: " + isRead;
    }

    public abstract String generatePreview();
}
