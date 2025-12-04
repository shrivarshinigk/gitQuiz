//Rashida Kapadia, Shrivarshini Ganeshkumar, Aditi Chaugule
//Dec 4th, 2025

// Multi Threaded
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainQuiz { // FINALLY FINAL

    public static void main(String[] args) {

        String warAndPeaceInput = "warAndPeace.txt";
        String taleOfTwoCitiesInput = "taleOfTwo.txt";

        String warAndPeaceOutput = "war_and_peace_upper.txt";
        String taleOfTwoCitiesOutput = "tale_of_two_cities_upper.txt";

        // Start timer (same as single-threaded)
        long startTime = System.currentTimeMillis();

        // Thread pool of 2 threads → both books processed in parallel
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> convertFileToUppercase(warAndPeaceInput, warAndPeaceOutput));
        executor.submit(() -> convertFileToUppercase(taleOfTwoCitiesInput, taleOfTwoCitiesOutput));

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // End timer
        long endTime = System.currentTimeMillis();

        System.out.println("Finished multithreaded conversion (efficient).");
        System.out.println("Total time (ms): " + (endTime - startTime));
        System.out.println("Total time (seconds): " + ((endTime - startTime) / 1000.0));
    }

    public static void convertFileToUppercase(String inputPath, String outputPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {

            String line;

            while ((line = reader.readLine()) != null) {

                // Efficient string capitalization
                StringBuilder sb = new StringBuilder(line.length());
                for (int i = 0; i < line.length(); i++) {
                    sb.append(Character.toUpperCase(line.charAt(i)));
                }

                writer.write(sb.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error processing: " + inputPath);
            e.printStackTrace();
        }
    }
}




