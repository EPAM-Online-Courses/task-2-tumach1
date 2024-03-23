package efs.task.syntax;
import java.util.Scanner;
import java.util.Random;

public class GuessNumberGame {

    // Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final int upperBound;
    private final int numberOfAttempts;
    public int attempt = 0;


    public GuessNumberGame(String argument) {
        int arg;
        try {
            arg = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException();
        }
        if (arg< 1 || arg > UsefulConstants.MAX_UPPER_BOUND) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException();
        }
        this.upperBound = arg;
        this.numberOfAttempts = (int) (Math.log(arg) / Math.log(2)) + 1;
    }

    public String printProgressBar() {
        StringBuilder progressBar = new StringBuilder("[");

        for (int i = 0; i < attempt + 1; i++) {
            progressBar.append("*");
        }

        for (int i = 0; i < numberOfAttempts - attempt - 1; i++) {
            progressBar.append(".");
        }

        progressBar.append("]");

        return progressBar.toString();
    }


    public void play() {
        System.out.println("Zagrajmy. Zgadnij liczbę z zakresu <1," + upperBound + ">");
        int target = new Random().nextInt(upperBound)+1;

        Scanner scanner = new Scanner(System.in);

        while (attempt < numberOfAttempts) {
            System.out.println("Twoje próby: " + printProgressBar());
            System.out.println(UsefulConstants.GIVE_ME);

            String input = scanner.next();

            try {
                int userGuess = Integer.parseInt(input);

                if (userGuess == target) {
                    System.out.println(UsefulConstants.YES + "!");
                    System.out.println(UsefulConstants.CONGRATULATIONS);
                    return;
                } else if (userGuess < target) {
                    System.out.println(UsefulConstants.TO_LESS);
                } else {
                    System.out.println(UsefulConstants.TO_MUCH);
                }

            } catch (NumberFormatException e) {
                System.out.println(UsefulConstants.NOT_A_NUMBER);
            }
            ++attempt;
        }

        System.out.println(UsefulConstants.UNFORTUNATELY + ", wyczerpałeś limit prób (" + numberOfAttempts + ") do odgadnięcia liczby " + target);
    }


}