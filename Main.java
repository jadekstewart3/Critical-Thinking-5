import java.util.*;

public class Main {
  public static void main(String[] args){
    final ArrayList<String> weekDays = new ArrayList<>(Arrays.asList("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"));
    ArrayList<Double> temps = new ArrayList<>();
    String userInput;
    int index;

    Scanner scnr = new Scanner(System.in);
    loadTemps(scnr, weekDays, temps);
    System.out.println("Enter a day, 'week', or 'quit': ");
    userInput = scnr.nextLine().trim();

    while(!userInput.equalsIgnoreCase("quit")){
      if(userInput.equalsIgnoreCase("week")){
        if(temps.size() != weekDays.size()){
          System.out.println("Temperature data is incomplete.");
        } else {
          double weekAverage = weeklyTempAverage(temps);
          for(index = 0; index < weekDays.size(); index++){
            System.out.println(weekDays.get(index) + ": " + temps.get(index) + "F |");
          }
          System.out.println("Weekly average: %.1f%n " + weekAverage);
        }
      } else {
        index = findDayIndex(weekDays, userInput);
        if(index == -1){
          System.out.println("Please enter a valid weekday: ");
        } else if(index < temps.size()){
          System.out.println(weekDays.get(index) + ": " + temps.get(index));
        } else {
          System.out.println("No temperature entered for " + weekDays.get(index));
        }
      }

      System.out.println("Enter a day, 'week', or 'quit': ");
      userInput = scnr.nextLine().trim();
    }
    scnr.close();
  }

  private static double weeklyTempAverage(ArrayList<Double> temps){
    float total = 0.0f;
    float average = 0.0f;

    for(double temp : temps){
      total += temp;
    }
    average = total / temps.size();
    return average;
  }

  private static int findDayIndex(ArrayList<String> weekDays, String userInput){
   return weekDays.indexOf(userInput);
  }

  private static void loadTemps(Scanner scnr, ArrayList<String> weekDays, ArrayList<Double> temps){
    for(String day : weekDays){
      boolean valid = false;
      while(!valid){
      System.out.println("Please enter the average temperature for " + day + ": ");
      String tempInput = scnr.nextLine().trim();
        try{
          double tempAvg = Double.parseDouble(tempInput);
          temps.add(tempAvg);
          valid = true;
        } catch (NumberFormatException e){
          System.out.println("Error: Please ensure you enter a numeric value");
        }
      }
    }
  }
}