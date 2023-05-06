package org.stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Read {
    public static List<String> readFile(String path) throws FileNotFoundException {
        File inputFile = new File(path);
        Scanner scanner = new Scanner(inputFile);

        List<String> lineFile = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lineFile.add(!Objects.equals(line, "") ?line:null );
        }
        return lineFile;
    }
}