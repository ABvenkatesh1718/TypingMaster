/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class TypingMaster{
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        
        String filePath = "D:\\typefile.txt"; 
        String[] paragraphs = getParagraphsFromFile(filePath);
        String paragraph = getRandomParagraph(paragraphs);

        // Display the paragraph and ask the user to type it
        System.out.println("Type the following paragraph:\n");
        System.out.println(paragraph);
        System.out.print("\nPress enter to start typing...");
        scanner.nextLine();

        // Start the timer and prompt the user to type the paragraph
        long startTime = System.currentTimeMillis();
        System.out.print("\nType the paragraph: ");
        String input = scanner.nextLine().trim();
        long endTime = System.currentTimeMillis();

        // Calculate the typing speed and accuracy
        double wpm = calculateWPM(input, endTime - startTime, paragraph);
        double accuracy = calculateAccuracy(input, paragraph);

        // Display the results
        System.out.println("\nYour typing speed is: " + wpm + " words per minute");
        System.out.println("Your accuracy is: " + accuracy + "%");
    }

    private static String[] getParagraphsFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine();
        StringBuilder sb = new StringBuilder();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = reader.readLine();
        }
        reader.close();

        return sb.toString().split("\n\n");
    }

    private static String getRandomParagraph(String[] paragraphs) {
        Random random = new Random();
        return paragraphs[random.nextInt(paragraphs.length)];
    }

    private static double calculateWPM(String input, long timeElapsed, String text) {
        int wordCount = text.trim().split("\\s+").length;
        int inputWordCount = input.trim().split("\\s+").length;
        double minutes = (double) timeElapsed / 60000;
        return (double) inputWordCount / minutes;
    }

    private static double calculateAccuracy(String input, String text) {
        String[] inputWords = input.trim().split("\\s+");
        String[] textWords = text.trim().split("\\s+");
        int matches = 0;

        for (int i = 0; i < inputWords.length; i++) {
            if (i < textWords.length && inputWords[i].equals(textWords[i])) {
                matches++;
            }
        }

        return (double) matches / textWords.length * 100;
    }
}
 