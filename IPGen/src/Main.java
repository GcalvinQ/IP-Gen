// asks for how many you want, it creates both files with that overall amount
// this will tie into the other written script that will allow for the two created .txt files to be taken in as input
// and "screen filter" the IP addresses to allow for the desired amount of fluctuation between IPv4 and IPv6 in for the
// defined dataset
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the overall number of mixed data points to generate: ");
        int overallNumDataPoints = scanner.nextInt();

        MixedDataCreator.createMixedData(overallNumDataPoints);

        scanner.close();
    }
}
