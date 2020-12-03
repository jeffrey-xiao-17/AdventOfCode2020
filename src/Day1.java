import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 {

    public static void main(String[] args) {
        try {
            List<Integer> input = new ArrayList<Integer>();
            BufferedReader br = new BufferedReader(new FileReader("../files/Day1Input.txt"));
            while (br.ready()) {
                input.add(Integer.parseInt(br.readLine()));
            }
            int[] twoNums = Day1.twoSum(input, 0, input.size() - 1, 2020);
            System.out.println("The resulting product from 2 numbers is: " + 
                    (twoNums[0] * twoNums[1]));

            int[] threeNums = Day1.threeSum(input);
            System.out.println("The resulting product from 3 numbers is: " + 
                    (threeNums[0] * threeNums[1] * threeNums[2]));
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Find the two entries that sum to 2020; what do you get if you multiply them
     * together?
     */
    public static int[] twoSum(List<Integer> nums, int lower, int higher, int target) {
        Collections.sort(nums);
        while (lower < nums.size() && higher >= 0) {
            int currSum = nums.get(lower) + nums.get(higher);
            if (currSum == target) {
                return new int[] { nums.get(lower), nums.get(higher) };
            } else if (currSum > target) {
                higher--;
            } else {
                lower++;
            }
        }
        return null;
    }

    /**
     * Find the three entries that sum to 2020; what is the product of the three
     * entries that sum to 2020?
     */
    public static int[] threeSum(List<Integer> nums) {
        Collections.sort(nums);

        for (int i = 0; i < nums.size() - 2; i++) {
            int[] res = Day1.twoSum(nums, i + 1, nums.size() - 1, 2020 - nums.get(i));
            if (res != null) {
                return new int[] { nums.get(i), res[0], res[1] };
            }
        }

        return null;
    }
}