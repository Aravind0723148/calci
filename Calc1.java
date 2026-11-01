import java.util.Scanner;

public class Calc1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean mainRunning = true;

        System.out.println("==================================================");
        System.out.println("      Welcome to Advanced Java Calculator         ");
        System.out.println("==================================================");

        while (mainRunning) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Scientific & Trigonometric Calculator");
            System.out.println("2. Matrix Operations Calculator");
            System.out.println("3. Exit");
            System.out.print("Choose a module (1-3): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.next();
                continue;
            }

            int mainChoice = scanner.nextInt();

            switch (mainChoice) {
                case 1:
                    runScientificCalculator(scanner);
                    break;
                case 2:
                    runMatrixCalculator(scanner);
                    break;
                case 3:
                    mainRunning = false;
                    System.out.println("Exiting calculator. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        }
        scanner.close();
    }

    // ==================== SCIENTIFIC MODULE ====================
    public static void runScientificCalculator(Scanner scanner) {
        boolean sciRunning = true;
        boolean useDegrees = true;

        while (sciRunning) {
            System.out.println("\n--- Scientific Calculator Menu ---");
            System.out.println("Current Angle Mode: " + (useDegrees ? "Degrees" : "Radians"));
            System.out.println("1. Sine (sin)");
            System.out.println("2. Cosine (cos)");
            System.out.println("3. Tangent (tan)");
            System.out.println("4. Arcsine (asin)");
            System.out.println("5. Arccosine (acos)");
            System.out.println("6. Arctangent (atan)");
            System.out.println("7. Toggle Degrees/Radians");
            System.out.println("8. Back to Main Menu");
            System.out.print("Choose an option (1-8): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            if (choice == 8) {
                sciRunning = false;
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
                    case 1:
                        result = Math.sin(useDegrees ? Math.toRadians(input) : input);
                        break;
                    case 2:
                        result = Math.cos(useDegrees ? Math.toRadians(input) : input);
                        break;
                    case 3:
                        result = Math.tan(useDegrees ? Math.toRadians(input) : input);
                        break;
                    case 4:
                        result = Math.asin(input);
                        if (useDegrees) result = Math.toDegrees(result);
                        break;
                    case 5:
                        result = Math.acos(input);
                        if (useDegrees) result = Math.toDegrees(result);
                        break;
                    case 6:
                        result = Math.atan(input);
                        if (useDegrees) result = Math.toDegrees(result);
                        break;
                }
                System.out.println("Result: " + result);
            } else {
                System.out.println("Invalid choice. Please select between 1 and 8.");
            }
        }
    }

    // ==================== MATRIX MODULE ====================
    public static void runMatrixCalculator(Scanner scanner) {
        boolean matrixRunning = true;

        while (matrixRunning) {
            System.out.println("\n--- Matrix Calculator Menu ---");
            System.out.println("1. Matrix Addition (A + B)");
            System.out.println("2. Matrix Subtraction (A - B)");
            System.out.println("3. Matrix Multiplication (A * B)");
            System.out.println("4. Matrix Transpose (A^T)");
            System.out.println("5. Determinant (2x2 or 3x3)");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose an option (1-6): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            if (choice == 6) {
                matrixRunning = false;
                break;
            }

            switch (choice) {
                case 1: { // Addition
                    System.out.println("\n--- Matrix Addition ---");
                    double[][] A = inputMatrix(scanner, "A");
                    double[][] B = inputMatrix(scanner, "B");
                    if (A.length != B.length || A[0].length != B[0].length) {
                        System.out.println("Error: Matrices must have the same dimensions for addition.");
                        break;
                    }
                    double[][] res = new double[A.length][A[0].length];
                    for (int i = 0; i < A.length; i++)
                        for (int j = 0; j < A[0].length; j++)
                            res[i][j] = A[i][j] + B[i][j];
                    printMatrix(res);
                    break;
                }
                case 2: { // Subtraction
                    System.out.println("\n--- Matrix Subtraction ---");
                    double[][] A = inputMatrix(scanner, "A");
                    double[][] B = inputMatrix(scanner, "B");
                    if (A.length != B.length || A[0].length != B[0].length) {
                        System.out.println("Error: Matrices must have the same dimensions for subtraction.");
                        break;
                    }
                    double[][] res = new double[A.length][A[0].length];
                    for (int i = 0; i < A.length; i++)
                        for (int j = 0; j < A[0].length; j++)
                            res[i][j] = A[i][j] - B[i][j];
                    printMatrix(res);
                    break;
                }
                case 3: { // Multiplication
                    System.out.println("\n--- Matrix Multiplication ---");
                    double[][] A = inputMatrix(scanner, "A");
                    double[][] B = inputMatrix(scanner, "B");
                    if (A[0].length != B.length) {
                        System.out.println("Error: Columns of Matrix A must match rows of Matrix B.");
                        break;
                    }
                    double[][] res = new double[A.length][B[0].length];
                    for (int i = 0; i < A.length; i++) {
                        for (int j = 0; j < B[0].length; j++) {
                            for (int k = 0; k < A[0].length; k++) {
                                res[i][j] += A[i][k] * B[k][j];
                            }
                        }
                    }
                    printMatrix(res);
                    break;
                }
                case 4: { // Transpose
                    System.out.println("\n--- Matrix Transpose ---");
                    double[][] A = inputMatrix(scanner, "A");
                    double[][] res = new double[A[0].length][A.length];
                    for (int i = 0; i < A.length; i++)
                        for (int j = 0; j < A[0].length; j++)
                            res[j][i] = A[i][j];
                    printMatrix(res);
                    break;
                }
                case 5: { // Determinant
                    System.out.println("\n--- Matrix Determinant ---");
                    double[][] A = inputMatrix(scanner, "A");
                    if (A.length != A[0].length) {
                        System.out.println("Error: Matrix must be square to calculate determinant.");
                        break;
                    }
                    double det = 0;
                    if (A.length == 2) {
                        det = (A[0][0] * A[1][1]) - (A[0][1] * A[1][0]);
                        System.out.println("Determinant: " + det);
                    } else if (A.length == 3) {
                        det = A[0][0] * (A[1][1] * A[2][2] - A[1][2] * A[2][1])
                            - A[0][1] * (A[1][0] * A[2][2] - A[1][2] * A[2][0])
                            + A[0][2] * (A[1][0] * A[2][1] - A[1][1] * A[2][0]);
                        System.out.println("Determinant: " + det);
                    } else {
                        System.out.println("Determinant calculation is currently supported for 2x2 and 3x3 matrices.");
                    }
                    break;
                }
                default:
                    System.out.println("Invalid choice. Please select between 1 and 6.");
            }
        }
    }

    // Helper method to read a matrix from user input
    public static double[][] inputMatrix(Scanner scanner, String name) {
        System.out.print("Enter number of rows for Matrix " + name + ": ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns for Matrix " + name + ": ");
        int cols = scanner.nextInt();

        double[][] matrix = new double[rows][cols];
        System.out.println("Enter elements for Matrix " + name + " row by row:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Element [" + i + "][" + j + "]: ");
                matrix[i][j] = scanner.nextDouble();
            }
        }
        return matrix;
    }

    // Helper method to print a matrix result
    public static void printMatrix(double[][] matrix) {
        System.out.println("\nResultant Matrix:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}