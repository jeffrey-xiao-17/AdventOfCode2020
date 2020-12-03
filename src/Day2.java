import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day2 {

    public static void main(String[] args) {
        try {
            List<String> input = new ArrayList<String>();
            BufferedReader br = new BufferedReader(new FileReader("../files/Day2Input.txt"));
            while (br.ready()) {
                input.add(br.readLine());
            }
            System.out.println("Number of valid passwords (part 1): " + 
                    Day2.numValidPasswordsPart1(input));
            System.out.println("Number of valid passwords (part 2): " + 
                    Day2.numValidPasswordsPart2(input));
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int numValidPasswordsPart1(List<String> input) {
        int count = 0;
        for (String s : input) {
            int min = Integer.parseInt(s.substring(0, s.indexOf('-')));
            int max = Integer.parseInt(s.substring(s.indexOf('-') + 1, s.indexOf(' ')));
            char c = s.charAt(s.indexOf(' ') + 1);

            int index = s.indexOf(':') + 2;
            int cnt = 0;
            while (index < s.length()) {
                cnt += s.charAt(index++) == c ? 1 : 0;
            }
            count += cnt >= min && cnt <= max ? 1 : 0;
        }
        return count;
    }

    public static int numValidPasswordsPart2(List<String> input) {
        int count = 0;
        for (String s : input) {
            int min = Integer.parseInt(s.substring(0, s.indexOf('-')));
            int max = Integer.parseInt(s.substring(s.indexOf('-') + 1, s.indexOf(' ')));
            char c = s.charAt(s.indexOf(' ') + 1);

            int index = s.indexOf(':') + 1;
            count += (s.charAt(index + min) == c && s.charAt(index + max) != c)
                    || (s.charAt(index + min) != c && s.charAt(index + max) == c) ? 1 : 0;
        }
        return count;
    }
}