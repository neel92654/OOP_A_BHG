abstract class Shape {
    abstract double area();
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    double area() {
        return length * width;
    }
}

class Triangle extends Shape {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    double area() {
        return 0.5 * base * height;
    }
}

public class ShapeDemo {
    public static void main(String[] args) {
        Shape[] shapes = {
            new Circle(5),
            new Rectangle(4, 6),
            new Triangle(8, 3)
        };

        double totalArea = 0;
        double largestArea = 0;

        for (Shape s : shapes) {
            double a = s.area();
            System.out.printf("%s Area: %.2f%n", s.getClass().getSimpleName(), a);
            totalArea += a;

            if (a > largestArea) {
                largestArea = a;
            }
        }

        System.out.printf("Total Area: %.2f%n", totalArea);
        System.out.printf("Largest Area: %.2f%n", largestArea);
    }
}