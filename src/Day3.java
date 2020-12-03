import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

    public static void main(String[] args) {
        try {
            List<String> input = new ArrayList<String>();
            BufferedReader br = new BufferedReader(new FileReader("../files/Day3Input.txt"));
            while (br.ready()) {
                input.add(br.readLine());
            }
            System.out.println("Number of slope trees: " + Day3.countSlopeTrees(input, 3, 1));
            System.out.println("Product of trees in multiple slopes: " + Day3.multipleSlopes(input));
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int countSlopeTrees(List<String> input, int right, int down) {
        int row = down;
        int col = right;
        int rowLen = input.get(0).length();
        int count = 0;
        while (row < input.size()) {
            count += input.get(row).charAt(col % rowLen) == '#' ? 1 : 0;
            row += down;
            col += right;
        }
        return count;
    }

    public static long multipleSlopes(List<String> input) {
        int[][] slopes = new int[][] {{1,1}, {3,1}, {5,1}, {7,1}, {1,2}};
        long product = 1;
        for (int[] arr : slopes) {
            product *= Day3.countSlopeTrees(input, arr[0], arr[1]);
        }
        return product;
    }
}