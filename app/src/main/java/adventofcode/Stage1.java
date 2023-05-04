package adventofcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Stage1 {
  public static void maxCalories(String path) throws FileNotFoundException {
    File inputFile = new File(path);
    Scanner scanner = new Scanner(inputFile);

    int maxCalories = 0;
    int currentCalories = 0;

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (line.isEmpty()) {
        maxCalories = Math.max(maxCalories, currentCalories);
        currentCalories = 0;
      } else {
        currentCalories += Integer.parseInt(line);
      }
    }

    maxCalories = Math.max(maxCalories, currentCalories);

    System.out.println("Stage 1 : Max calories = " + maxCalories);
  }
}
