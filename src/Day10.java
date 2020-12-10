
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day10 {

    public static void main(String[] args) {
        try {
            List<Integer> input = new ArrayList<Integer>();
            BufferedReader br = new BufferedReader(new FileReader("../files/Day10Input.txt"));
            while (br.ready()) {
                input.add(Integer.parseInt(br.readLine()));
            }
            Collections.sort(input);
            System.out.println("Distribution: "+findDist(input));
            System.out.println("Number ways: "+numWays(input));
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int findDist(List<Integer> input) {
        int i = 0;
        int ones = 0;
        int threes = 1;
        int prev = 0;
        while (i < input.size()) {
            int smallest = input.get(i++);
            if (smallest - prev == 1) {
                ones++;
            } else if (smallest - prev == 3) {
                threes++;
            }
            prev = smallest;
        }
        return ones * threes;
    }

    public static long numWays(List<Integer> input) {
        input.add(0, 0);
        input.add(input.get(input.size() - 1) + 3);
        Map<Integer, Long> memo = new HashMap<Integer, Long>();
        memo.put(input.size() - 1, (long) 1);
        for (int i = input.size() - 2; i >= 0; i--) {
            long sum = 0;
            for (int j = i + 1; j < input.size(); j++) {
                if (input.get(j) - input.get(i) < 4) {
                    sum += memo.get(j);
                }
            }
            memo.put(i, sum);
        }
        return memo.get(0);
    } 
}