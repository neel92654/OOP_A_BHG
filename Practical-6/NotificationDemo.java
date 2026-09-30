@FunctionalInterface
interface Notifier {
    void send(String message);
}

// Marker interface to tag urgent notification channels
interface Urgent {
}

class UrgentEmailNotifier implements Notifier, Urgent {
    @Override
    public void send(String message) {
        System.out.println("[URGENT EMAIL] Sending: " + message);
    }
}

public class NotificationDemo {
    public static void main(String[] args) {
        Notifier emailNotifier = message -> System.out.println("[Standard Email] Sending: " + message);
        Notifier smsNotifier = message -> System.out.println("[SMS] Sending: " + message);
        Notifier urgentNotifier = new UrgentEmailNotifier();

        Notifier[] senders = {
            emailNotifier,
            smsNotifier,
            urgentNotifier
        };

        String broadcastMessage = "System maintenance scheduled at midnight.";
        System.out.println("Broadcasting message: \"" + broadcastMessage + "\"\n");

        for (Notifier sender : senders) {
            sender.send(broadcastMessage);

            // If marked as Urgent, send a second time
            if (sender instanceof Urgent) {
                System.out.print(" -> Urgent sender detected, resending: ");
                sender.send(broadcastMessage);
            }
        }
    }
}