/*
 * Name: Jade Stewart
 * Course: CSC320
 * Assignment: Module 5 Critical Thinking
 * Date: October 15, 2026
 *
 * Description: Collects the average temperature for each day of the week
 * from the user. The days are stored in an array and the temperatures are
 * stored in an ArrayList. The user can then enter a day of the week to see
 * that day's temperature, enter "week" to see every day's temperature along
 * with the weekly average, or enter "quit" to exit the program.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    final String[] weekDays = { "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday" };
    ArrayList<Double> temps = new ArrayList<>();
    String userInput;
    int index;

    Scanner scnr = new Scanner(System.in);

    // Collect a temperature for each day before allowing lookups
    loadTemps(scnr, weekDays, temps);

    // Priming read for the lookup loop
    System.out.println("Enter a day, 'week', or 'quit': ");
    userInput = scnr.nextLine().trim().toLowerCase();

    while (!userInput.equals("quit")) {
      if (userInput.equals("week")) {
        // Display every day's temperature, then the weekly average
        double weekAverage = weeklyTempAverage(temps);
        for (index = 0; index < weekDays.length; index++) {
          System.out.println(weekDays[index] + ": " + temps.get(index) + "F");
        }
        System.out.printf("Weekly average: %.1fF%n", weekAverage);
      } else {
        // Look up a single day and display its temperature
        index = findDayIndex(weekDays, userInput);
        if (index == -1) {
          System.out.println("Invalid entry.");
        } else {
          System.out.println(weekDays[index] + ": " + temps.get(index) + "F");
        }
      }

      System.out.println("Enter a day, 'week', or 'quit': ");
      userInput = scnr.nextLine().trim().toLowerCase();
    }

    scnr.close();
  }

  /*
   * Calculates the average of all temperatures in the list.
   * Returns the average as a double.
   */
  private static double weeklyTempAverage(ArrayList<Double> temps) {
    double total = 0.0;
    double average = 0.0;

    for (double temp : temps) {
      total += temp;
    }
    average = total / temps.size();
    return average;
  }

  /*
   * Searches the week days array for the day the user entered.
   * Returns the index of the matching day, or -1 if there is no match.
   */
  private static int findDayIndex(String[] weekDays, String userInput) {
    for (int i = 0; i < weekDays.length; i++) {
      if (weekDays[i].equals(userInput)) {
        return i;
      }
    }
    return -1;
  }

  /*
   * Prompts the user for the average temperature of each day of the week
   * and adds each value to the temps list. Re-prompts for the same day
   * until a valid number is entered.
   */
  private static void loadTemps(Scanner scnr, String[] weekDays, ArrayList<Double> temps) {
    for (String day : weekDays) {
      boolean valid = false;
      while (!valid) {
        System.out.println("Please enter the average temperature for " + day + ": ");
        String tempInput = scnr.nextLine().trim();
        try {
          double tempAvg = Double.parseDouble(tempInput);
          temps.add(tempAvg);
          valid = true;
        } catch (NumberFormatException e) {
          System.out.println("Error: Please ensure you enter a numeric value.");
        }
      }
    }
  }
}