import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGameFaisal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int rounds = 3;
        int maxAttempts = 5;
        int totalScore = 0;

        System.out.println("Welcome to Guess the Number Game!");
        System.out.println("Try to guess the number (1-100) in as few attempts as possible.");

        for (int round = 1; round <= rounds; round++) {
            int randomNumber = rand.nextInt(100) + 1;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nRound " + round + " of " + rounds);
            System.out.println("You have " + maxAttempts + " attempts to guess the correct number.");

            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();
                attempts++;

                if (guess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    int points = maxAttempts - attempts + 1;
                    System.out.println("Points earned: " + points);
                    totalScore += points;
                    guessedCorrectly = true;
                } else if (guess < randomNumber) {
                    System.out.println("The number is higher than " + guess + ".");
                } else {
                    System.out.println("The number is lower than " + guess + ".");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Out of attempts! The correct number was: " + randomNumber);
            }
        }

        System.out.println("\nGame Over!");
        System.out.println("Total score: " + totalScore);
        sc.close();
    }
}

/*
Sample Output:

Welcome to Guess the Number Game!
Try to guess the number (1-100) in as few attempts as possible.

Round 1 of 3
You have 5 attempts to guess the correct number.
Enter your guess: 50
The number is higher than 50.
Enter your guess: 75
The number is lower than 75.
Enter your guess: 62
Congratulations! You guessed the correct number in 3 attempts.
Points earned: 3

Round 2 of 3
You have 5 attempts to guess the correct number.
Enter your guess: 30
The number is higher than 30.
Enter your guess: 45
The number is higher than 45.
Enter your guess: 70
The number is lower than 70.
Enter your guess: 60
Out of attempts! The correct number was: 65

Round 3 of 3
You have 5 attempts to guess the correct number.
Enter your guess: 25
The number is higher than 25.
Enter your guess: 80
The number is lower than 80.
Enter your guess: 50
The number is higher than 50.
Enter your guess: 65
Congratulations! You guessed the correct number in 4 attempts.
Points earned: 2

Game Over!
Total score: 5
*/
