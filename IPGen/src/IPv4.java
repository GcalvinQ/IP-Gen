// importing necessary libraries
// There is a lot due to the use of multithreading and multi-processing
// this allows each address to be created, not in concurrence but in parallel
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

//IPv4
// has been tested to up to 1.5 mil datapoints
public class IPv4 {
    private static final String OUTPUT_FILE_PATH = "outputIPv4.txt";

    public static void generateIPv4AddressesInParallel(int numAddresses) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Callable<Void>> tasks = new ArrayList<>();

        for (int i = 0; i < numAddresses; i++) {
            // Capture the index variable for the lambda
            final int addressIndex = i;
            tasks.add(() -> {
                String ipv4Address = generateIPv4Address();
                writeToFile(ipv4Address, OUTPUT_FILE_PATH);
                return null;
            });
        }

        try {
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    public static String generateIPv4Address() {
        StringBuilder ipv4Address = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int octet = (int) (Math.random() * 256);
            ipv4Address.append(octet);

            if (i < 3) {
                ipv4Address.append(".");
            }
        }

        return ipv4Address.toString();
    }

    private static void writeToFile(String address, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(address);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
