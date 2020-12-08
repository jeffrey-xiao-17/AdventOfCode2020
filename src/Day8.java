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
            System.out.println("Value of acc before infinite loop: " + findAcc(new ArrayList<String>(input)).getAcc());
            System.out.println("Value of acc after changing: " + accAfterFixing(new ArrayList<String>(input)));
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int accAfterFixing(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            String instruc = input.get(i);
            String type = instruc.substring(0, 3);
            Tuple res;
            switch (type) {
                case "jmp":
                    input.set(i, "nop"+instruc.substring(3));
                    res = findAcc(input);
                    if (!res.getInfinite()) {
                        return res.getAcc();
                    }
                    input.set(i, "jmp"+instruc.substring(3));
                    break;
                case "nop":
                    input.set(i, "jmp"+instruc.substring(3));
                    res = findAcc(input);
                    if (!res.getInfinite()) {
                        return res.getAcc();
                    }
                    input.set(i, "nop"+instruc.substring(3));
                    break;
                default:
                    break;
            }
        }
        return -1;
    }

    private static Tuple findAcc(List<String> input) {
        Set<Integer> seenLineNums = new HashSet<Integer>();
        int currLine = 0;
        Tuple res = new Tuple(0, false);
        while (currLine != input.size() - 1) {
            if (seenLineNums.contains(currLine)) {
                res.setInfinite(true);
                return res;
            }
            seenLineNums.add(currLine);
            String instruc = input.get(currLine);
            String type = instruc.substring(0, 3);
            Integer val = Integer.parseInt(instruc.substring(4));
            if (type.equals("acc")) {
                currLine++;
                res.updateAcc(val);
            } else if (type.equals("jmp")) {
                currLine += val;
            } else {
                currLine++;
            }
        }
        if (currLine == input.size() - 1) {
            String instruc = input.get(currLine);
            String type = instruc.substring(0, 3);
            Integer val = Integer.parseInt(instruc.substring(4));
            if (type.equals("acc")) {
                currLine++;
                res.updateAcc(val);
            }
        }
        return res;
    }

    static class Tuple {
        private int acc;
        private boolean infinite;
        public Tuple(int acc, boolean infinite) {
            this.acc = acc;
            this.infinite = infinite;
        }

        public int getAcc() {
            return acc;
        }

        public void updateAcc(int add) {
            acc += add;
        }

        public void setInfinite(boolean b) {
            infinite = b;
        }

        public boolean getInfinite() {
            return infinite;
        }
    }
}