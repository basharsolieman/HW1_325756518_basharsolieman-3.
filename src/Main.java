
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Message> messages =
            new ArrayList<Message>();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        addInitialMessages();

        int choice;

        do {
            printMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addMessage();
                    break;
                case 2:
                    deleteMessage();
                    break;
                case 3:
                    printAllMessages();
                    break;
                case 4:
                    countMessagesByWords();
                    break;
                case 5:
                    printDigitalMessages();
                    break;
                case 6:
                    printPreviews();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        scanner.close();
    }

    // Create two messages of each type
    private static void addInitialMessages() {
        try {
            messages.add(new BoardMessage(
                    "Dana", "Important project update",
                    false, "Students Board"));

            messages.add(new BoardMessage(
                    "Noa", "Exam next week",
                    false, "Course Board"));

            messages.add(new EmailMessage(
                    "Rina", "Please submit your homework",
                    false, "Homework Submission"));

            messages.add(new EmailMessage(
                    "David", "Meeting tomorrow morning",
                    false, "Meeting Reminder"));

            messages.add(new SmsMessage(
                    "Omar", "Hello, how are you?",
                    false, "0541234567"));

            messages.add(new SmsMessage(
                    "Ali", "See you tomorrow",
                    false, "0521234567"));

        } catch (SmsException e) {
            System.out.println(e.getMessage());
        }
    }

    // Print the menu
    private static void printMenu() {
        System.out.println("\n===== MESSAGING SYSTEM =====");
        System.out.println("1. Add message");
        System.out.println("2. Delete message");
        System.out.println("3. Print all messages");
        System.out.println("4. Count messages containing words");
        System.out.println("5. Print digital messages");
        System.out.println("6. Print message previews");
        System.out.println("0. Exit");
    }

    // Read an integer safely
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    // Add a new message
    private static void addMessage() {
        System.out.println("\nChoose message type:");
        System.out.println("1. Board message");
        System.out.println("2. Email message");
        System.out.println("3. SMS message");

        int type = readInt("Type: ");

        if (type < 1 || type > 3) {
            System.out.println("Invalid message type.");
            return;
        }

        System.out.print("Sender: ");
        String sender = scanner.nextLine();

        System.out.print("Content: ");
        String content = scanner.nextLine();

        try {
            Message message;

            switch (type) {
                case 1:
                    System.out.print("Board name: ");
                    String board = scanner.nextLine();

                    message = new BoardMessage(
                            sender, content, false, board);
                    break;

                case 2:
                    System.out.print("Email subject: ");
                    String subject = scanner.nextLine();

                    message = new EmailMessage(
                            sender, content, false, subject);
                    break;

                case 3:
                    System.out.print("Phone number: ");
                    String phone = scanner.nextLine();

                    message = new SmsMessage(
                            sender, content, false, phone);
                    break;

                default:
                    return;
            }

            messages.add(message);
            System.out.println("Message added successfully!");

        } catch (IllegalArgumentException | SmsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Delete a message by its number
    private static void deleteMessage() {
        if (messages.isEmpty()) {
            System.out.println("No messages to delete.");
            return;
        }

        printAllMessages();

        int index = readInt("Enter message number to delete: ");

        if (index < 1 || index > messages.size()) {
            System.out.println("Invalid message number.");
            return;
        }

        messages.remove(index - 1);
        System.out.println("Message deleted successfully!");
    }

    // Print the entire message collection
    private static void printAllMessages() {
        if (messages.isEmpty()) {
            System.out.println("No messages found.");
            return;
        }

        System.out.println("\n===== ALL MESSAGES =====");

        for (int i = 0; i < messages.size(); i++) {
            System.out.println((i + 1) + ". "
                    + messages.get(i));
        }
    }

    // Count messages containing searched words
    private static void countMessagesByWords() {
        ArrayList<String> words = new ArrayList<String>();

        System.out.print("Enter words separated by spaces: ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("No words entered.");
            return;
        }

        for (String word : input.split("\\s+")) {
            words.add(word);
        }

        int count = 0;

        for (Message message : messages) {
            if (message.find(words)) {
                count++;
            }
        }

        System.out.println("Matching messages: " + count);
    }

    // Print only digital messages
    private static void printDigitalMessages() {
        System.out.println("\n===== DIGITAL MESSAGES =====");

        boolean found = false;

        for (Message message : messages) {
            if (message instanceof IDigital) {
                found = true;
                System.out.println(message);
                System.out.println(
                    ((IDigital) message).printCommunicationMethod());
            }
        }

        if (!found) {
            System.out.println("No digital messages found.");
        }
    }

    // Additional menu option
    private static void printPreviews() {
        System.out.println("\n===== MESSAGE PREVIEWS =====");

        if (messages.isEmpty()) {
            System.out.println("No messages found.");
            return;
        }

        for (Message message : messages) {
            System.out.println(message.generatePreview());
        }
    }
}
