import java.util.Scanner;

public class Calc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        boolean useDegrees = true; // Default mode is Degrees

        System.out.println("==========================================");
        System.out.println("     Java Trigonometric Calculator        ");
        System.out.println("==========================================");

        while (running) {
            System.out.println("\n--- Menu ---");
            System.out.println("Current Angle Mode: " + (useDegrees ? "Degrees" : "Radians"));
            System.out.println("1. Sine (sin)");
            System.out.println("2. Cosine (cos)");
            System.out.println("3. Tangent (tan)");
            System.out.println("4. Arcsine (asin)");
            System.out.println("5. Arccosine (acos)");
            System.out.println("6. Arctangent (atan)");
            System.out.println("7. Toggle Degrees/Radians");
            System.out.println("8. Exit");
            System.out.print("Choose an option (1-8): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.next(); // clear invalid input
                continue;
            }

            int choice = scanner.nextInt();

            if (choice == 8) {
                running = false;
                System.out.println("Exiting calculator. Goodbye!");
                break;
            }

            if (choice == 7) {
                useDegrees = !useDegrees;
                System.out.println("-> Mode switched to: " + (useDegrees ? "Degrees" : "Radians"));
                continue;
            }

            if (choice >= 1 && choice <= 6) {
                System.out.print("Enter the input value: ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Please enter a valid numeric value.");
                    scanner.next();
                    continue;
                }
                double input = scanner.nextDouble();
                double result = 0.0;

                switch (choice) {
                    case 1: // sin
                        double sinInput = useDegrees ? Math.toRadians(input) : input;
                        result = Math.sin(sinInput);
                        break;
                    case 2: // cos
                        double cosInput = useDegrees ? Math.toRadians(input) : input;
                        result = Math.cos(cosInput);
                        break;
                    case 3: // tan
                        double tanInput = useDegrees ? Math.toRadians(input) : input;
                        result = Math.tan(tanInput);
                        break;
                    case 4: // asin
                        result = Math.asin(input);
                        if (useDegrees) result = Math.toDegrees(result);
                        break;
                    case 5: // acos
                        result = Math.acos(input);
                        if (useDegrees) result = Math.toDegrees(result);
                        break;
                    case 6: // atan
                        result = Math.atan(input);
                        if (useDegrees) result = Math.toDegrees(result);
                        break;
                }

                System.out.println("Result: " + result);
            } else {
                System.out.println("Invalid choice. Please select an option between 1 and 8.");
            }
        }
        scanner.close();
    }
}