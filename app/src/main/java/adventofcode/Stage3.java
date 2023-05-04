package adventofcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Stage3 {
  public static void score(String path) throws FileNotFoundException {
    File inputFile = new File(path);
    Scanner scanner = new Scanner(inputFile);
    List<Character> listoferror = new ArrayList<>();
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String sub1 = line.substring(0, line.length() / 2);
      String sub2 = line.substring(line.length() / 2);

      listoferror.add(findCommmonChar(sub1, sub2));
    }

    System.out.println(
        "Stage 3 : the sum of the priorities of those item types = " + calculateScore(listoferror));
  }

  private static char findCommmonChar(String s1, String s2) {
    return s1.chars()
        .mapToObj(c -> (char) c)
        .filter(c -> s2.contains(Character.toString(c)))
        .distinct()
        .findFirst()
        .orElseThrow();
  }

  private static int calculateScore(List<Character> list) {
    return list.stream()
        .mapToInt(
            c -> {
              if (c >= 'a' && c <= 'z') {
                return c - 'a' + 1;
              } else if (c >= 'A' && c <= 'Z') {
                return c - 'A' + 27;
              } else {
                return 0; // Ignore other characters
              }
            })
        .sum();
  }
}
