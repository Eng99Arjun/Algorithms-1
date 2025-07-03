
/**
 *
 * @author Dhananjay PC
 */
import java.util.Scanner;

public class Optimised_PrefixSum {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Length : ");
        int length = sc.nextInt();
        int [] arr = new int[length];

        for(int i = 0; i < length; i++){
            arr[i] =  sc.nextInt();
        }

        int [] prefixArr = new int[length];
        prefixArr[0] = arr[0];

        for (int i = 1; i < length; i++) {
            prefixArr[i] = prefixArr[i-1] + arr[i];
        }

        System.out.println("Prefix Sum : ");
        for(int i = 0; i < length; i++){
            System.out.print(prefixArr[i] + " ");
        }
        
        sc.close();
    }
}
