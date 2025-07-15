public class BinarySearch {
    int BS(int [] nums, int k){
        int low =0, high = nums.length-1;
        
        while(low <= high){
            int mid = (low+high)/2;
            if(nums[mid] == k){
                return mid;
            } else if(nums[mid] > k){
                high = mid-1;
            }else {
                low = mid+1;
            }
        }
        return -1;                
    }

    public static void main(String[] args) {
        int arr[] = new int[]{10, 25 ,58,8569 ,74};
        BinarySearch bs = new BinarySearch();
       System.out.println(bs.BS(arr, 3));
        
    }
}