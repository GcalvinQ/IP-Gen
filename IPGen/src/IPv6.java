// importing necessary libraries
// There is a lot due to the use of multithreading and multi-processing
// this allows each address to be created, not in concurrence but in parallel
// IPv6
// has been tested to up to 1.5 mil datapoints
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class IPv6 {
    private static final String OUTPUT_FILE_PATH = "outputIPv6.txt";

    public static void generateIPv6AddressesInParallel(int numAddresses) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Callable<Void>> tasks = new ArrayList<>();

        for (int i = 0; i < numAddresses; i++) {
            // Capture the index variable for the lambda
            final int addressIndex = i;
            tasks.add(() -> {
                String ipv6Address = generateIPv6Address();
                writeToFile(ipv6Address, OUTPUT_FILE_PATH);
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

    public static String generateIPv6Address() {
        Random rand = ThreadLocalRandom.current();
        StringBuilder ipv6Address = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int segmentValue = rand.nextInt(0xFFFF + 1);
            appendHexSegment(ipv6Address, segmentValue);

            if (i < 7) {
                ipv6Address.append(":");
            }
        }

        return ipv6Address.toString();
    }

    private static void appendHexSegment(StringBuilder builder, int value) {
        builder.append(String.format("%04x", value));
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
