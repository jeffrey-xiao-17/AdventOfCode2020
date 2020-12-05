import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day5 {

    public static void main(String[] args) {
        try {
            List<String> input = new ArrayList<String>();
            BufferedReader br = new BufferedReader(new FileReader("../files/Day5Input.txt"));
            while (br.ready()) {
                input.add(br.readLine());
            }
            br.close();
            System.out.println("Highest ID: "+findHighestID(input));
            System.out.println("Your ID: "+findYourId(input));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int findHighestID(List<String> input) {
        int highest = 0;
        for (String s : input) {
            int rLow = 0;
            int rHigh = 127;
            int cLow = 0;
            int cHigh = 7;
            for (int i = 0; i < 10; i++) {
                char c = s.charAt(i);
                if (i < 7) {
                    // row
                    if (c == 'B') {
                        rLow = (rHigh + rLow) / 2 + 1;
                    } else {
                        rHigh = (rHigh + rLow) / 2;
                    }
                } else {
                    // col
                    if (c == 'R') {
                        cLow = (cHigh + cLow) / 2 + 1;
                    } else {
                        cHigh = (cHigh + cLow) / 2;
                    }
                }
            }
            int id = rLow * 8 + cLow;
            highest = id > highest ? id : highest;
        }
        return highest;
    }

    public static int findYourId(List<String> input) {
        List<Integer> sorted = new ArrayList<Integer>();
        for (String s : input) {
            int rLow = 0;
            int rHigh = 127;
            int cLow = 0;
            int cHigh = 7;
            for (int i = 0; i < 10; i++) {
                char c = s.charAt(i);
                if (i < 7) {
                    if (c == 'B') {
                        rLow = (rHigh + rLow) / 2 + 1;
                    } else {
                        rHigh = (rHigh + rLow) / 2;
                    }
                } else {
                    if (c == 'R') {
                        cLow = (cHigh + cLow) / 2 + 1;
                    } else {
                        cHigh = (cHigh + cLow) / 2;
                    }
                }
            }
            int id = rLow * 8 + cLow;
            sorted.add(id);
        }

        Collections.sort(sorted);

        final int INDEX_FACTOR = sorted.get(0);
        int low = 0;
        int high = sorted.size() - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (sorted.get(mid) - INDEX_FACTOR > mid) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return high + INDEX_FACTOR;
    }
}