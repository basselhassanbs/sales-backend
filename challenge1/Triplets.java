import java.util.*;
 
public class triplets {
    
    public static List<List<Integer>> findTriplets(int[] nums, int sum)
    {
        Arrays.sort(nums);
        
        List<List<Integer> > pair = new ArrayList<>();
        TreeSet<String> set = new TreeSet<String>();
        List<Integer> triplets = new ArrayList<>();
 

        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
 
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] == sum) {
                    String str = nums[i] + ":" + nums[j] + ":" + nums[k];
                    if (!set.contains(str))
                    {
                        triplets.add(nums[i]);
                        triplets.add(nums[j]);
                        triplets.add(nums[k]);
                        pair.add(triplets);
                        triplets = new ArrayList<>();
                        set.add(str);
                    }
                    j++;
                    k--;
                }
                else if (nums[i] + nums[j] + nums[k] < sum)
                    j++;
                else
                    k--;
            }
        }
        return pair;
    }
 
    public static void main(String[] args)
    {
        int[] nums = { 10, 2, 5, 1, 7, 8 };
        int sum = 19;
 
        List<List<Integer> > triplets = findTriplets(nums, sum);
        
        if (!triplets.isEmpty()) {
            System.out.println(triplets);
        }
        else {
            System.out.println("No triplets");
        }
    }
}