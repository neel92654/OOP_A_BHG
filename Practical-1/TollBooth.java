import java.util.Scanner;

public class TollBooth {
    record Vehicle(String number, String type) {
    }

    public static void main(String[] args) {
        Scanner n = new Scanner(System.in);

        int totalToll = 0;
        int bikeCount = 0;
        int carCount = 0;
        int truckCount = 0;

        while (true) {
            String number = n.next();
            if (number.equalsIgnoreCase("done")) {
                break;
            }
            String type = n.next();

            Vehicle vehicle = new Vehicle(number, type);

            int toll = switch (vehicle.type().toLowerCase()) {
                case "bike" -> {
                    bikeCount++;
                    yield 20;
                }
                case "car" -> {
                    carCount++;
                    yield 50;
                }
                case "truck" -> {
                    truckCount++;
                    yield 150;
                }
                default -> 0;
            };

            totalToll += toll;
        }

        System.out.println("Total toll: " + totalToll);

        String highestType = "none";
        int maxCount = 0;

        if (bikeCount > maxCount) {
            maxCount = bikeCount;
            highestType = "bike";
        }
        if (carCount > maxCount) {
            maxCount = carCount;
            highestType = "car";
        }
        if (truckCount > maxCount) {
            maxCount = truckCount;
            highestType = "truck";
        }

        System.out.println("Most frequent: " + highestType);

        n.close();
    }
}