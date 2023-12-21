import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// generates the mixing of the IPv4 and IPv6 points
// pretty cool, does not use multithreading
// but the called functions use multithreading etc.
public class MixedDataCreator {
    private static final String OUTPUT_FILE_PATH = "mixedData.txt";

    public static void createMixedData(int totalDataPoints) {
        List<String> mixedData = new ArrayList<>();

        // Calculate the number of data points for IPv4 and IPv6
        int numIPv4DataPoints = (int) (totalDataPoints * 0.75);
        int numIPv6DataPoints = totalDataPoints - numIPv4DataPoints;

        // Generate IPv4 data points
        for (int i = 0; i < numIPv4DataPoints; i++) {
            mixedData.add(IPv4.generateIPv4Address());
        }

        // Generate IPv6 data points
        for (int i = 0; i < numIPv6DataPoints; i++) {
            mixedData.add(IPv6.generateIPv6Address());
        }

        // Shuffle the list to randomize the order of data points
        java.util.Collections.shuffle(mixedData);

        // Write the mixed data to the output file
        writeToFile(mixedData, OUTPUT_FILE_PATH);
    }

    private static void writeToFile(List<String> data, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String dataPoint : data) {
                writer.write(dataPoint);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
