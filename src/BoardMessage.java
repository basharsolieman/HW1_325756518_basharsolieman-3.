
import java.util.Date;

public class BoardMessage extends Message {

    private PriorityType priority;
    private String boardName;

    // Constructor with all fields
    public BoardMessage(String sender, String content,
                        Date sendDate, boolean isRead,
                        PriorityType priority, String boardName) {
        super(sender, content, sendDate, isRead);
        setPriority(priority);
        setBoardName(boardName);
    }

    // Constructor with current date and REGULAR priority
    public BoardMessage(String sender, String content,
                        boolean isRead, String boardName) {
        this(sender, content, new Date(), isRead,
             PriorityType.REGULAR, boardName);
    }

    public PriorityType getPriority() {
        return priority;
    }

    public void setPriority(PriorityType priority) {
        if (priority == null) {
            throw new IllegalArgumentException(
                "Priority cannot be null");
        }
        this.priority = priority;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        if (boardName == null || boardName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Board name cannot be empty");
        }
        this.boardName = boardName;
    }

    // Additional method
    public boolean isUrgent() {
        return priority == PriorityType.URGENT;
    }

    @Override
    public String generatePreview() {
        String content = getContent();

        if (content.length() > 15) {
            content = content.substring(0, 15) + "...";
        }

        return "[Board] " + getSender() + ": " + content;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Priority: " + priority
                + ", Board Name: " + boardName;
    }



}
