
import java.util.Scanner;
public class Bruteforce_PrefixSum {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Length : ");
        int length = sc.nextInt();
        int [] arr = new int[length];

        for(int i = 1; i <= length; i++){
            arr[i] =  sc.nextInt();
        }

        int [] prefixArr = new int[length];

        for (int i = 0; i<length; i++){
            int sum =0;
            for(int j = 0; j <= i; j++){
                sum += arr[j];
            }
            prefixArr[i] = sum;
        }

        System.out.println("Prefix Sum : ");
        for(int i = 1; i <= length; i++){
            System.out.print(prefixArr[i] + " ");
        }
    }

}
