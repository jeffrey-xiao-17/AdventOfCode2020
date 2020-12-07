import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day7 {

    public static void main(String[] args) {
        try {
            Map<String, Map<String, Integer>> input = new HashMap<String, Map<String, Integer>>();
            BufferedReader br = new BufferedReader(new FileReader("../files/Day7Input.txt"));
            while (br.ready()) {
                String s = br.readLine();
                String one = s.substring(0, s.indexOf(" bags"));
                String reqs = s.substring(s.indexOf("contain ")+8);
                if (!reqs.equals("no other bags.")) {
                    String[] split = reqs.split(", ");
                    Map<String, Integer> corresponding = new HashMap<String, Integer>();
                    for (String req : split) {
                        String bag = req.substring(req.indexOf(" ")+1, req.indexOf(" bag"));
                        int num = Integer.parseInt(req.substring(0, req.indexOf(" ")));
                        corresponding.put(bag, num);
                    }
                    input.put(one, corresponding);
                }
            }
            System.out.println("Number of bags that contain Shiny Gold: " + containShinyGold(input));
            System.out.println("Number of bags in Shiny Gold: " + bagsInShinyGold(input));
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int containShinyGold(Map<String, Map<String, Integer>> input) {
        Set<String> seen = new HashSet<String>();
        Map<String, Set<String>> reversed = new HashMap<String, Set<String>>();
        for (String s : input.keySet()) {
            Map<String, Integer> corres = input.get(s);
            for (String key : corres.keySet()) {
                reversed.putIfAbsent(key, new HashSet<String>());
                reversed.get(key).add(s);
            }
        }
        Queue<String> queue = new LinkedList<String>();
        queue.add("shiny gold");
        while (!queue.isEmpty()) {
            String s = queue.poll();
            if (reversed.containsKey(s)) {
                for (String n : reversed.get(s)) {
                    if (seen.add(n)) {
                        queue.add(n);
                    }
                }
            } 
            
        }
        return seen.size();
    }

    public static int bagsInShinyGold(Map<String, Map<String, Integer>> input) {
        int count = -1;
        Stack<String> stack = new Stack<String>();
        Stack<Integer> nums = new Stack<Integer>();
        Stack<Integer> prevs = new Stack<Integer>();
        stack.push("shiny gold");
        nums.push(1);
        prevs.push(1);
        while (!stack.empty()) {
            String s = stack.pop();
            int num = nums.pop();
            int prev = prevs.pop();
            count += num * prev;
            if (input.containsKey(s)) {
                for (String inner : input.get(s).keySet()) {
                    stack.push(inner);
                    nums.push(input.get(s).get(inner));
                    prevs.push(num);
                }
            }
        }
        return count;
    }

}