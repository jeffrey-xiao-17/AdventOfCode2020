import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day6 {

    public static void main(String[] args) {
        try {
            List<Set<Character>> input = new ArrayList<Set<Character>>();
            BufferedReader br = new BufferedReader(new FileReader("../files/Day6Input.txt"));
            input.add(new HashSet<Character>());
            boolean first = true;
            while (br.ready()) {
                String s = br.readLine();
                if (s.equals("")) {
                    input.add(new HashSet<Character>());
                    first = true;
                } else {
                    if (first) {
                        for (char c : s.toCharArray()) {
                            input.get(input.size() - 1).add(c);
                        }
                        first = false;
                    } else {
                        Set<Character> curr = new HashSet<Character>();
                        for (char c : s.toCharArray()) {
                            curr.add(c);
                        }
                        for (int i = 0; i < 26; i++) {
                            char c = (char) ((int) 'a' + i);
                            if ((input.get(input.size() - 1).contains(c) && !curr.contains(c)) || 
                                (!input.get(input.size() - 1).contains(c) && curr.contains(c))) {
                                input.get(input.size() - 1).remove(c);
                            }
                        } 
                    }
                    
                }
            }
            System.out.println("Sum of Counts: "+sumCounts(input));
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int sumCounts(List<Set<Character>> input) {
        int sum = 0;
        for (Set<Character> s : input) {
            sum += s.size();
        }
        return sum;
    }
}