import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day9 {

    public static void main(String[] args) {
        try {
            List<Long> input = new ArrayList<Long>();
            BufferedReader br = new BufferedReader(new FileReader("../files/Day9Input.txt"));
            while (br.ready()) {
                input.add(Long.parseLong(br.readLine()));
            }
            System.out.println("First num to break property: "+notProperty(input));
            System.out.println("Contiguous: "+contiguous(input));
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Long notProperty(List<Long> input) {
        for (int i = 25; i < input.size(); i++) {
            boolean satisfied = false;
            for (int j = i - 25; j < i-1; j++) {
                for (int k = j + 1; k < i; k++) {
                    if (input.get(j) + input.get(k) == input.get(i)) {
                        satisfied = true;
                    }
                }
            }
            if (!satisfied) {
                return input.get(i);
            }
        }
        return (long) -1;
    }

    public static Long contiguous(List<Long> input) {
        int low = 0;
        int high = 0;
        int highestIndex = 0;
        long sum = input.get(0);
        List<long[]> lowest = new ArrayList<long[]>();
        lowest.add(new long[] {input.get(0), 0});

        Comparator<long[]> comp = new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                return o1[0] < o2[0] ? 1 : 0;
            }
        };

        while (sum != 373803594) {
            if (sum < 373803594) {
                high++;
                if (input.get(high) > input.get(highestIndex)) {
                    highestIndex = high;
                }
                sum += input.get(high);
                lowest.add(new long[] {input.get(high), high});
                Collections.sort(lowest, comp);
            } else {
                low++;
                for (int i = 0; i < lowest.size(); i++) {
                    if (lowest.get(i)[1] == low - 1) {
                        long[] a = lowest.remove(0);
                        sum -= a[0];
                        break;
                    }
                }
            }
        }
        return input.get(highestIndex) + input.get((int) lowest.get(0)[1]);
    }
    
}