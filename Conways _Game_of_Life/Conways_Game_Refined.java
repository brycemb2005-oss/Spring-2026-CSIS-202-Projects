import java.util.Scanner;

class Conways_Game {

    int gridSize = 12;

    char[][] cell = new char[gridSize][gridSize];

    public void setupGrid() {
        System.out.println();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                cell[i][j] = '#';
            }
        }
        for (int i = 1; i < (gridSize - 1); i++) {
            for (int j = 1; j < (gridSize - 1); j++) {
                cell[i][j] = '-';
            }
        }
    }

    public void printGrid() {
        for (int i = 0; i < 12; i++) {
            System.out.println();
            for (int j = 0; j < 12; j++) {
                System.out.print(cell[i][j]);
                System.out.print("  ");
            }
        }
        System.out.println("\n");
    }

    public void printSelection() {
        int[][] numbers = new int[10][10];
        int count = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                numbers[i][j] = count;
                count++;
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
            for (int j = 0; j < 10; j++) {
                System.out.print(numbers[i][j]);
                if (numbers[i][j] >= 10 && numbers[i][j] <= 99) {
                    System.out.print(" ");
                }
                else {
                    System.out.print("  ");
                }
            }
        }
    }

    public void arise() {

        System.out.println();
        System.out.println();

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while (!input.equals("done")) {

            try {
                if (Integer.parseInt(input) >= 0 && Integer.parseInt(input) < 144) {
                    int row = ((Integer.parseInt(input)) / 10) + 1;
                    int column = ((Integer.parseInt(input)) % 10) + 1;
                    cell[row][column] = '*';
                } else {
                    System.out.println("Invalid input, enter a number6 between 0 and 99 or type done");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number or type done");
            }
            input = sc.nextLine();
        }
    }

    public void timeShiftRules() {

        char[][] copy = new char[gridSize][gridSize];

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                copy[i][j] = cell[i][j];
            }
        }

        int count = 0;

        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if (copy[i - 1][j - 1] == '*') {
                    count = count + 1;
                }
                if (copy[i - 1][j] == '*') {
                    count = count + 1;
                }
                if (copy[i - 1][j + 1] == '*') {
                    count = count + 1;
                }
                if (copy[i][j - 1] == '*') {
                    count = count + 1;
                }
                if (copy[i][j + 1] == '*') {
                    count = count + 1;
                }
                if (copy[i + 1][j - 1] == '*') {
                    count = count + 1;
                }
                if (copy[i + 1][j] == '*') {
                    count = count + 1;
                }
                if (copy[i + 1][j + 1] == '*') {
                    count = count + 1;
                }
                if (count == 3) {
                    cell[i][j] = '*';
                }
                if (count < 2) {
                    cell[i][j] = '-';
                }
                if (count > 3) {
                    cell[i][j] = '-';
                }
                count = 0;
            }
        }
    }

    public void timeShift() {

        Scanner sc = new Scanner(System.in);

        int input;

        System.out.print("Enter a positive integer to iterate or 0 to terminate: ");

        while (true) {

            String line = sc.nextLine();

            try {
                input = (Integer.parseInt(line));
                if (input >= 0) {
                    break;
                }
                else {
                    System.out.print("Number must be greater than 0: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input, Enter a positive integer: ");
            }
        }

        while (input != 0) {
            for (int i = 0; i < input; i++) {
                timeShiftRules();
            }
            printGrid();

            System.out.print("Enter a positive integer to iterate or 0 to terminate: ");

            while (true) {

                String line = sc.nextLine();

                try {
                    input = (Integer.parseInt(line));
                    if (input >= 0) {
                        break;
                    }
                    else {
                        System.out.print("Number must be greater than 0: ");
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Invalid input, Enter a positive integer: ");
                }
            }
        }
    }

    public static void integer() {

        Scanner sc = new Scanner(System.in);

        int input;

        while (true) {

            System.out.print("Enter a positive integer, 0 = terminate: ");

            String line = sc.nextLine();

            try {
                input = (Integer.parseInt(line));
                if (input >= 0) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a positive integer, 0 = terminate: ");
            }
        }
    }

    void main(String[] args) {
        setupGrid();
        System.out.println("Current Cell Layout, all cells are currently dead (-)");
        printGrid();
        System.out.println("Select which Cells positions to bring to life: \nsay the number and then press enter for each live cell, \nwhen finished then type the word done.");
        printSelection();
        arise();
        printGrid();
        timeShift();
    }
}
