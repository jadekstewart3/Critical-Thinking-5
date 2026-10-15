import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  public static void main(String[] args){
    final String[] weekDays = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
    float[] temps = new float[7];
    String userInput;
    int index;

    Scanner scnr = new Scanner(System.in);
    System.out.println("Enter a day, 'week', or 'quit': ");
    userInput = scnr.nextLine().trim();

    while(!userInput.equalsIgnoreCase("quit")){
      if(userInput.equalsIgnoreCase("week")){
        float weekAverage = weeklyTempAverage(temps);
        for(index =0; index < weekDays.length; index++){
          System.out.println(weekDays[index] + ": " + temps[index] + "F |");
          System.out.println("Weekly average: " + weekAverage);
        }
      }
    }
    scnr.close();
  }

  private static float weeklyTempAverage(float[] temps){
    float total = 0.0f;
    float average = 0.0f;

    for(float temp : temps){
      total += temp;
    }
    average = total / temps.length;
    return average;
  }
}