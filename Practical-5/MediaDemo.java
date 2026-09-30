abstract class Media {
    protected String title;
    protected int lateDays;

    public Media(String title, int lateDays) {
        this.title = title;
        this.lateDays = lateDays;
    }

    public String getTitle() {
        return title;
    }

    public int getLateDays() {
        return lateDays;
    }

    abstract double lateFee();
}

class Book extends Media {
    public Book(String title, int lateDays) {
        super(title, lateDays);
    }

    @Override
    double lateFee() {
        return lateDays * 2.0;
    }
}

class DVD extends Media {
    public DVD(String title, int lateDays) {
        super(title, lateDays);
    }

    @Override
    double lateFee() {
        return lateDays * 5.0;
    }
}

class Magazine extends Media {
    public Magazine(String title, int lateDays) {
        super(title, lateDays);
    }

    @Override
    double lateFee() {
        return lateDays * 1.0;
    }
}

public class MediaDemo {
    public static void main(String[] args) {
        Media[] items = {
            new Book("Java Programming", 3),
            new DVD("Inception", 2),
            new Magazine("Tech Today", 5)
        };

        double totalLateFees = 0;

        for (Media m : items) {
            double fee = m.lateFee();
            System.out.println(m.getTitle() + " (" + m.getClass().getSimpleName() + ", " + m.getLateDays() + " days late) - Late Fee: " + fee);
            totalLateFees += fee;
        }

        System.out.println("Total Late Fees for Batch: " + totalLateFees);
    }
}