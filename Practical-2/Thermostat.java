public class Thermostat {
    private String location;
    private int temperature;
    private static int MIN = 16;
    private static int MAX = 30;
    private static int activeCount = 0;

    Thermostat(String location, int temperature) {
        this.location = location;
        this.temperature = temperature;
        activeCount++;
    }

    Thermostat(String location) {
        this(location, 22);
    }

    public String getLocation() {
        return location;
    }

    public int getTemperature() {
        return temperature;
    }

    public static int getActiveCount() {
        return activeCount;
    }

    public void raise() {
        if (temperature < MAX) {
            temperature++;
        } 
        else {
            System.out.println("Already at maximum (30)");
        }
    }

    public void lower() {
        if (temperature > MIN) {
            temperature--;
        } 
        else {
            System.out.println("Already at minimum (16)");
        }
    }

     public static void main(String[] args) {
        Thermostat thermostat1 = new Thermostat("Living Room", 22);
        Thermostat thermostat2 = new Thermostat("Bedroom");

        for (int i = 0; i < 10; i++) {
            thermostat1.raise();
            System.out.println("Thermostat 1 Temperature: " + thermostat1.getTemperature());
        }

        for (int i = 0; i < 20; i++) {
            thermostat2.lower();
            System.out.println("Thermostat 2 Temperature: " + thermostat2.getTemperature());
        }

        System.out.println("Active Thermostats: " + Thermostat.getActiveCount());
    }


}