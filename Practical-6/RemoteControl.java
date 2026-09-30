interface Switchable {
    void on();
    void off();

    default void toggle() {
        on();
    }
}

class Fan implements Switchable {
    @Override
    public void on() {
        System.out.println("Fan turned ON");
    }

    @Override
    public void off() {
        System.out.println("Fan turned OFF");
    }
}

class Light implements Switchable {
    @Override
    public void on() {
        System.out.println("Light turned ON");
    }

    @Override
    public void off() {
        System.out.println("Light turned OFF");
    }
}

@FunctionalInterface
interface SwitchRule {
    boolean maySwitchOn(Switchable device, int hour);
}

public class RemoteControl {
    public static void main(String[] args) {
        Switchable[] devices = {
            new Fan(),
            new Light()
        };

        System.out.println("--- Toggling all devices ---");
        for (Switchable device : devices) {
            device.toggle();
        }

        System.out.println("\n--- Testing Switch Rules ---");

        // Anonymous inner class implementation
        SwitchRule daytimeRule = new SwitchRule() {
            @Override
            public boolean maySwitchOn(Switchable device, int hour) {
                return hour >= 6 && hour <= 22;
            }
        };

        // Lambda expression implementation
        SwitchRule officeHourRule = (device, hour) -> hour >= 9 && hour <= 17;

        System.out.println("Daytime Rule at 10 AM (Fan): " + daytimeRule.maySwitchOn(devices[0], 10));
        System.out.println("Daytime Rule at 23 PM (Light): " + daytimeRule.maySwitchOn(devices[1], 23));
        System.out.println("Office Hour Rule at 20 PM (Light): " + officeHourRule.maySwitchOn(devices[1], 20));
        System.out.println("Office Hour Rule at 14 PM (Fan): " + officeHourRule.maySwitchOn(devices[0], 14));
    }
}