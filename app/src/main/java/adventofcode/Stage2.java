package adventofcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Stage2 {

  public static void score(String path) throws FileNotFoundException {
    File inputFile = new File(path);
    Scanner scanner = new Scanner(inputFile);
    int totalScore = 0;

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine().trim();
      if (line.isEmpty()) {
        break;
      }

      String[] tokens = line.split(" ");
      char opponent = tokens[0].charAt(0);
      char myMove = tokens[1].charAt(0);

      int addScore = 0;
      if (myMove == 'X') {
        addScore = 1;
      } else if (myMove == 'Y') {
        addScore = 2;
      } else if (myMove == 'Z') {
        addScore = 3;
      }

      int roundScore;
      if (opponent == 'A' && myMove == 'Y'
          || opponent == 'B' && myMove == 'Z'
          || opponent == 'C' && myMove == 'X') {
        roundScore = 6;
      } else if (opponent == 'A' && myMove == 'Z'
          || opponent == 'B' && myMove == 'X'
          || opponent == 'C' && myMove == 'Y') {
        roundScore = 0;
      } else {
        // draw
        roundScore = 3;
      }

      totalScore += roundScore + addScore;
    }

    System.out.println("Stage 2 : Total score = " + totalScore);
  }
}
