import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day4 {

    public static String[] required = new String[] {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
    public static Set<String> eyeColors = new HashSet<String>();

    public static void main(String[] args) {
        try {
            List<Map<String, String>> input = new ArrayList<Map<String, String>>();
            BufferedReader br = new BufferedReader(new FileReader("../files/Day4Input.txt"));
            input.add(new HashMap<String, String>());
            while (br.ready()) {
                String curr = br.readLine();
                String[] fields = curr.split(" ");
                if (!fields[0].equals("")) {
                  for (String f : fields) {
                    input.get(input.size() - 1).put(f.substring(0, 3), f.substring(4));
                  }
                } else {
                  input.add(new HashMap<String, String>());
                }
            }
            if (input.get(input.size() - 1).isEmpty()) {
              input.remove(input.size() - 1);
            }
            eyeColors.add("amb");
            eyeColors.add("blu");
            eyeColors.add("brn");
            eyeColors.add("gry");
            eyeColors.add("grn");
            eyeColors.add("hzl");
            eyeColors.add("oth");

            System.out.println("Number valid: " + Day4.countValid(input));
            System.out.println("Number valid2: " + Day4.countValidRestrictions(input));
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int countValid(List<Map<String, String>> input) {
      int count = 0;
      for (Map<String, String> m : input) {
        boolean containsAll = true;
        for (String r : required) {
          if (!m.containsKey(r)) {
            containsAll = false;
          }
        }
        count += containsAll ? 1 : 0;
      }
      return count;
    }

    public static int countValidRestrictions(List<Map<String, String>> input) {
      int count = 0;
      
      for (Map<String, String> m : input) {
        boolean containsAll = true;
        
        // byr
        containsAll = containsAll && m.containsKey("byr") && Integer.parseInt(m.get("byr")) >= 1920 && Integer.parseInt(m.get("byr")) <= 2002;

        // iyr
        containsAll = containsAll && m.containsKey("iyr") && Integer.parseInt(m.get("iyr")) >= 2010 && Integer.parseInt(m.get("iyr")) <= 2020;

        // eyr
        containsAll = containsAll && m.containsKey("eyr") && Integer.parseInt(m.get("eyr")) >= 2020 && Integer.parseInt(m.get("eyr")) <= 2030;

        // hgt
        if (m.containsKey("hgt")) {
          String hgt = m.get("hgt");
          String suffix = hgt.substring(hgt.length() - 2);
          if (suffix.equals("cm")) {
            Integer num = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
            containsAll = containsAll && num >= 150 && num <= 193;
          } else if (suffix.equals("in")) {
            Integer num = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
            containsAll = containsAll && num >= 59 && num <= 76;
          } else {
            containsAll = false;
          }
        } else {
          containsAll = false;
        }

        // hcl
        if (m.containsKey("hcl") && m.get("hcl").charAt(0) == '#') {
          String pid = m.get("hcl");
          for (int i = 1; i < 7; i++) {
            char c = pid.charAt(i);
            containsAll = containsAll && ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f'));
          }
          containsAll = containsAll && pid.length() == 7;
        } else {
          containsAll = false;
        }

        // ecl
        containsAll = containsAll && eyeColors.contains(m.get("ecl"));

        // pid
        if (m.containsKey("pid")) {
          String pid = m.get("pid");
          for (char c : pid.toCharArray()) {
            containsAll = containsAll && Character.isDigit(c);
          }
          containsAll = containsAll && pid.length() == 9;
        } else {
          containsAll = false;
        }

        count += containsAll ? 1 : 0;
      }
      return count;
    }    
}