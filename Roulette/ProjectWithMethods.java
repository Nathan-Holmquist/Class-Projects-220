/*
 * Nathan Holmquist
 * CPSC 220
 * I pledge
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ProjectWithMethods {
    public static void main(String[] args) {
        // import file
        String fileName = "inputText.txt";
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        // Keep a list of previous rolls
        ArrayList<Integer> rollHistory = new ArrayList<>();
       
        // Asking how many chips you want should only happen once so it is kept outside the loop
        System.out.print("Enter the number of chips you want to have (-1 to quit): ");
        int chips = scanner.nextInt();
       
        // Main loop
        while (true) {
            if (chips == -1) {
                break;
            }

            System.out.println("You have " + chips + " chips.");
            System.out.print("How many chips would you like to bet? (-1 to quit): ");
            int betChips = scanner.nextInt();
            // exit with -1
            if (betChips == -1) {
                break;
            }
            // Call getBetNumber method
            int number = getBetNumber(scanner, random);
            if (number == -1) {
                continue;
            }
            // Call spinRoulette method
            int result = spinRoulette(fileName);
            System.out.println("Result: " + result);
            if (number == -1) {
                System.out.println("You bet on color.");
            } else {
                System.out.println("You bet on " + number);
                if (result == number) {
                    System.out.println("You win!");
                    chips += betChips * 35; // Winning pays 35 to 1
                } else {
                    System.out.println("You lose!");
                    chips -= betChips;
                }
            }
            // Add roll result to rollHistory
            rollHistory.add(result);
        }
        // Print previous rolls
        System.out.println("Roll History:");
        for (int roll : rollHistory) {
            System.out.print(roll + " ");
        }
    }

  // Method to get the bet from the user
    private static int getBetNumber(Scanner scanner, Random random) {
        System.out.print("Do you want to bet on a specific number (0-37)? (Y/N): ");
        char choice = scanner.next().charAt(0);
        if (choice == 'Y' || choice == 'y') {
            System.out.print("Enter the number to bet on: ");
            int number = scanner.nextInt();
            if (number < 0 || number > 37) {
                System.out.println("Invalid number! Betting on color instead.");
                return -1;
            } else {
                return number;
            }
        } else {
            System.out.print("Do you want to bet on red or black? (R/B): ");
            char colorChoice = scanner.next().charAt(0);
            if (colorChoice == 'R' || colorChoice == 'r') {
                return random.nextInt(19) * 2; // Even numbers (0 to 36) are red
            } else if (colorChoice == 'B' || colorChoice == 'b') {
                // bound is the upper limit
                return random.nextInt(19) * 2 + 1; // Odd numbers (1 to 37) are black
            } else {
                System.out.println("Invalid choice! Exiting game.");
                return -1;
            }
        }
    }
    


    // Roll's the roulette wheel
    private static int spinRoulette(String fileName) {
        ArrayList<Integer> numbers = new ArrayList<>();

        // read numbers from file
        try {
            Scanner fin = new Scanner(new FileInputStream(fileName));

            while (fin.hasNext()) {
                String line = fin.nextLine();
                numbers.add(Integer.parseInt(line));
            }
            fin.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();;
            return -1;
        }

        int size = numbers.size();

        Random ran = new Random();
        int randomIndex = ran.nextInt(size);
        int spin =  numbers.get(randomIndex);
        return spin;
    } 
}
