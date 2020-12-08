import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day8 {

    public static void main(String[] args) {
        try {
            List<String> input = new ArrayList<String>();
            BufferedReader br = new BufferedReader(new FileReader("../files/Day8Input.txt"));
            while (br.ready()) {
                input.add(br.readLine());
            }
            System.out.println("Value of acc before infinite loop: "+findAcc(input));
            System.out.println("Value of acc after changing: "+accAfterFixing(input));
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int findAcc(List<String> input) {
        int acc = 0;
        Set<Integer> seenLineNums = new HashSet<Integer>();
        int currLine = 0;
        while (!seenLineNums.contains(currLine) && currLine != input.size() - 1) {
            seenLineNums.add(currLine);
            String instruc = input.get(currLine);
            String type = instruc.substring(0, 3);
            Integer val = Integer.parseInt(instruc.substring(4));
            switch (type) {
                case "acc":
                    currLine++;
                    acc += val;
                    break;
                case "jmp":
                    currLine += val;
                    break;
                default:
                    currLine++;
            }
        }
        if (currLine == input.size() - 1) {
            String instruc = input.get(currLine);
            String type = instruc.substring(0, 3);
            Integer val = Integer.parseInt(instruc.substring(4));
            if (type.equals("acc")) {
                currLine++;
                acc += val;
            }
        }
        return acc;
    }

    public static int accAfterFixing(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            List<String> copy = new ArrayList<String>(input);
            String instruc = input.get(i);
            String type = instruc.substring(0, 3);
            switch (type) {
                case "jmp":
                    copy.set(i, "nop"+instruc.substring(3));
                    if (!isInfinite(copy)) {
                        return findAcc(copy);
                    }
                    break;
                case "nop":
                    copy.set(i, "jmp"+instruc.substring(3));
                    if (!isInfinite(copy)) {
                        return findAcc(copy);
                    }
                    break;
                default:
                    break;
            }
        }
        return -1;
    }

    private static boolean isInfinite(List<String> input) {
        Set<Integer> seenLineNums = new HashSet<Integer>();
        int currLine = 0;
        while (currLine != input.size() - 1) {
            if (seenLineNums.contains(currLine)) {
                return true;
            }
            seenLineNums.add(currLine);
            String instruc = input.get(currLine);
            String type = instruc.substring(0, 3);
            Integer val = Integer.parseInt(instruc.substring(4));
            if (type.equals("acc")) {
                currLine++;
            } else if (type.equals("jmp")) {
                currLine += val;
            } else {
                currLine++;
            }
        }
        return false;
    }
}