import java.util.Scanner;

class Conways_Game {

    char[][] cell = new char[12][12];

    int time = 2;

    public void Setup_Grid() {
        System.out.println();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                cell[i][j] = '#';
            }
        }
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                cell[i][j] = '-';
            }
        }
    }

    public void Print_Grid() {
        for (int i = 0; i < 12; i++) {
            System.out.println();
            for (int j = 0; j < 12; j++) {
                System.out.print(cell[i][j]);
                System.out.print("  ");
            }
        }
        System.out.println("\n");
    }

    public void Print_Selection() {
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

    public void ARISE() {
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

    public void Time_Shift_Rules() {

        char[][] copy = new char[cell.length][cell.length];

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
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
//                for (int y = 1; y < 3; y++) {
//                    if (cell[i + (y - 2)][j - 1] == '*') {
//                        count = count + 1;
//                    }
//                    if (cell[i + y][j] == '*') {
//                        count = count + 1;
//                    }
//                    if (cell[i + y][j + 1] == '*') {
//                        count = count + 1;
//                    }
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

    public void Time_Shift() {

        System.out.println("Enter the amount of time/itterations that you would like to pass for the cells\n");

        Scanner sc = new Scanner(System.in);

        int input = Integer.parseInt(sc.nextLine());

        while (!(input == 0)) {
            try {
                for (int i = 0; i < input; i++) {
                    Time_Shift_Rules();
                    Print_Grid();
                }
                input = Integer.parseInt(sc.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a positive number or zero");
            }
        }
    }

    void main(String[] args) {
        Setup_Grid();
        System.out.println("Current Cell Layout, all cells are currently dead (-)");
        Print_Grid();
        System.out.println("Select which Cells positions to bring to life: \nsay the number and then press enter for each live cell, \nwhen finished then type the word done.");
        Print_Selection();
        ARISE();
        Print_Grid();
        Time_Shift_Rules();
        Print_Grid();
        Time_Shift();
    }
}
