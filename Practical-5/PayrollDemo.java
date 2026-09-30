abstract class Employee {
    protected String name;
    protected int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    abstract double monthlySalary();
}

class FullTime extends Employee {
    private double fixedSalary;

    public FullTime(String name, int id, double fixedSalary) {
        super(name, id);
        this.fixedSalary = fixedSalary;
    }

    @Override
    double monthlySalary() {
        return fixedSalary;
    }
}

class PartTime extends Employee {
    private double hoursWorked;
    private double hourlyRate;

    public PartTime(String name, int id, double hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    double monthlySalary() {
        return hoursWorked * hourlyRate;
    }
}

class Intern extends Employee {
    private double stipend;

    public Intern(String name, int id, double stipend) {
        super(name, id);
        this.stipend = stipend;
    }

    @Override
    double monthlySalary() {
        return stipend;
    }
}

public class PayrollDemo {
    public static void main(String[] args) {
        Employee[] employees = {
            new FullTime("Rahul", 101, 50000),
            new PartTime("Priya", 102, 80, 300),
            new Intern("Aman", 103, 10000)
        };

        double totalSalary = 0;

        for (Employee e : employees) {
            double salary = e.monthlySalary();
            System.out.print(e.getName() + " (" + e.getClass().getSimpleName() + ") - Salary: " + salary);

            if (e instanceof Intern) {
                System.out.print(" [Note: Intern stipend is subject to university evaluation]");
            }
            System.out.println();

            totalSalary += salary;
        }

        System.out.println("Total Monthly Payroll: " + totalSalary);
    }
}