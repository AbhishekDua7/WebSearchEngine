package com.gooogle.se;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Utilities {

    public static List<Line> parseInput(String input) {
        String[] userInputQueries = input.split("\n");
        List<Line> userInputLines = new ArrayList<>();
        for (String userInput : userInputQueries) {
            if (userInput != null && !userInput.trim().isEmpty()) {
                try {
                    userInputLines.add(new Line(userInput));
                } catch (RuntimeException ex) {
                    System.out.println(ex.toString());
                }
            }
        }
        return userInputLines;
    }


    public static void storeLogs(String path, List<Line> inputLines) {
        try {
            File inputFile = new File(path);
            if (!inputFile.exists() && !inputFile.createNewFile()) {
                throw new UnableToCreateFileException("");
            }
            FileWriter fileWriter = new FileWriter(inputFile);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write("");
            for (Line line : inputLines) {
                writer.append(line.getLine());
                writer.newLine();
            }
            writer.close();
            fileWriter.close();
        } catch (Exception ex) {
            throw new UnableToCreateFileException(ex.toString());
        }
    }

    public static void storeLogsForCircularShiftedLines(String path, List<CircularShiftedLine> inputLinesList) {
        try {
            File inputFile = new File(path);
            if (!inputFile.exists() && !inputFile.createNewFile()) {
                throw new UnableToCreateFileException("");
            }
            FileWriter fileWriter = new FileWriter(inputFile);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write("");
            for (CircularShiftedLine inputLines: inputLinesList) {
                for (Line line : inputLines.getShiftedLines()) {
                    writer.append(line.getLine());
                    writer.newLine();
                }
            }
            writer.close();
            fileWriter.close();
        } catch (Exception ex) {
            throw new UnableToCreateFileException(ex.toString());
        }
    }
}