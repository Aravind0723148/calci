import java.util.Scanner;

public class Calc {
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
            System.out.println("3. Equation Solver");
            System.out.println("4. Exit");
            System.out.print("Choose a module (1-4): ");

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
                    runEquationSolver(scanner);
                    break;
                case 4:
                    mainRunning = false;
                    System.out.println("Exiting calculator. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select between 1 and 4.");
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
                    double[][] A = inputMatrix(scanner, "A");
                    double[][] B = inputMatrix(scanner, "B");
                    if (A.length != B.length || A[0].length != B[0].length) {
                        System.out.println("Error: Matrices must have the same dimensions.");
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
                    double[][] A = inputMatrix(scanner, "A");
                    double[][] B = inputMatrix(scanner, "B");
                    if (A.length != B.length || A[0].length != B[0].length) {
                        System.out.println("Error: Matrices must have the same dimensions.");
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
                    double[][] A = inputMatrix(scanner, "A");
                    double[][] res = new double[A[0].length][A.length];
                    for (int i = 0; i < A.length; i++)
                        for (int j = 0; j < A[0].length; j++)
                            res[j][i] = A[i][j];
                    printMatrix(res);
                    break;
                }
                case 5: { // Determinant
                    double[][] A = inputMatrix(scanner, "A");
                    if (A.length != A[0].length) {
                        System.out.println("Error: Matrix must be square.");
                        break;
                    }
                    double det = calculateDeterminant(A);
                    System.out.println("Determinant: " + det);
                    break;
                }
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ==================== EQUATIONS MODULE ====================
    public static void runEquationSolver(Scanner scanner) {
        boolean eqRunning = true;

        while (eqRunning) {
            System.out.println("\n--- Equation Solver Menu ---");
            System.out.println("1. Quadratic Equation (ax^2 + bx + c = 0)");
            System.out.println("2. System of Linear Equations (2 variables: 2x2)");
            System.out.println("3. System of Linear Equations (3 variables: 3x3)");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option (1-4): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            if (choice == 4) {
                eqRunning = false;
                break;
            }

            switch (choice) {
                case 1: { // Quadratic Equation
                    System.out.println("\nSolving: ax^2 + bx + c = 0");
                    System.out.print("Enter a: ");
                    double a = scanner.nextDouble();
                    System.out.print("Enter b: ");
                    double b = scanner.nextDouble();
                    System.out.print("Enter c: ");
                    double c = scanner.nextDouble();

                    if (a == 0) {
                        System.out.println("Not a quadratic equation ('a' cannot be 0).");
                        if (b != 0) {
                            System.out.println("Linear root x = " + (-c / b));
                        } else {
                            System.out.println("No solution or infinite solutions.");
                        }
                        break;
                    }

                    double discriminant = (b * b) - (4 * a * c);

                    if (discriminant > 0) {
                        double r1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                        double r2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                        System.out.println("Two distinct real roots:");
                        System.out.println("x1 = " + r1);
                        System.out.println("x2 = " + r2);
                    } else if (discriminant == 0) {
                        double r = -b / (2 * a);
                        System.out.println("One real repeated root:");
                        System.out.println("x = " + r);
                    } else {
                        double realPart = -b / (2 * a);
                        double imagPart = Math.sqrt(-discriminant) / (2 * a);
                        System.out.println("Two complex roots:");
                        System.out.println("x1 = " + realPart + " + " + imagPart + "i");
                        System.out.println("x2 = " + realPart + " - " + imagPart + "i");
                    }
                    break;
                }
                case 2: { // 2x2 Linear System
                    System.out.println("\nSolving 2x2 System:");
                    System.out.println("a1*x + b1*y = c1");
                    System.out.println("a2*x + b2*y = c2");
                    
                    System.out.print("Enter a1, b1, c1: ");
                    double a1 = scanner.nextDouble(), b1 = scanner.nextDouble(), c1 = scanner.nextDouble();
                    System.out.print("Enter a2, b2, c2: ");
                    double a2 = scanner.nextDouble(), b2 = scanner.nextDouble(), c2 = scanner.nextDouble();

                    double det = (a1 * b2) - (a2 * b1);
                    if (det == 0) {
                        System.out.println("No unique solution (Determinant is 0).");
                    } else {
                        double x = ((c1 * b2) - (c2 * b1)) / det;
                        double y = ((a1 * c2) - (a2 * c1)) / det;
                        System.out.println("Solution found:");
                        System.out.println("x = " + x);
                        System.out.println("y = " + y);
                    }
                    break;
                }
                case 3: { // 3x3 Linear System using Cramer's Rule
                    System.out.println("\nSolving 3x3 System:");
                    System.out.println("a1*x + b1*y + c1*z = d1");
                    System.out.println("a2*x + b2*y + c2*z = d2");
                    System.out.println("a3*x + b3*y + c3*z = d3");

                    double[][] mat = new double[3][3];
                    double[] d = new double[3];

                    System.out.println("Enter row 1 (a1 b1 c1 d1): ");
                    mat[0][0] = scanner.nextDouble(); mat[0][1] = scanner.nextDouble(); mat[0][2] = scanner.nextDouble(); d[0] = scanner.nextDouble();
                    
                    System.out.println("Enter row 2 (a2 b2 c2 d2): ");
                    mat[1][0] = scanner.nextDouble(); mat[1][1] = scanner.nextDouble(); mat[1][2] = scanner.nextDouble(); d[1] = scanner.nextDouble();
                    
                    System.out.println("Enter row 3 (a3 b3 c3 d3): ");
                    mat[2][0] = scanner.nextDouble(); mat[2][1] = scanner.nextDouble(); mat[2][2] = scanner.nextDouble(); d[2] = scanner.nextDouble();

                    double D = calculateDeterminant(mat);
                    if (D == 0) {
                        System.out.println("No unique solution (Determinant D is 0).");
                        break;
                    }

                    // Replace columns to find Dx, Dy, Dz
                    double[][] matX = replaceColumn(mat, d, 0);
                    double[][] matY = replaceColumn(mat, d, 1);
                    double[][] matZ = replaceColumn(mat, d, 2);

                    double x = calculateDeterminant(matX) / D;
                    double y = calculateDeterminant(matY) / D;
                    double z = calculateDeterminant(matZ) / D;

                    System.out.println("Solution found:");
                    System.out.println("x = " + x);
                    System.out.println("y = " + y);
                    System.out.println("z = " + z);
                    break;
                }
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ==================== HELPER METHODS ====================
    public static double[][] inputMatrix(Scanner scanner, String name) {
        System.out.print("Enter number of rows for Matrix " + name + ": ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns for Matrix " + name + ": ");
        int cols = scanner.nextInt();

        double[][] matrix = new double[rows][cols];
        System.out.println("Enter elements for Matrix " + name + " row by row:");
        for (int i = 0; i < rows; i++) {
            for (int j =