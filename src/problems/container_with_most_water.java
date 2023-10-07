package problems;

public class container_with_most_water {
    /**
     * You are given an integer array height of length n.
     * There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
     *
     * Find two lines that together with the x-axis form a container, such that the container contains the most water.
     *
     * Return the maximum amount of water a container can store.
     *
     * Notice that you may not slant the container.
     * @param args
     */
    public static void main(String[] args) {
        int [] numbers = {1,8,6,2,5,4,8,3,7};
        int maxArea = maxArea(numbers);
        System.out.println(maxArea);
    }

    public static int maxArea(int[] height) {

        int indexStart = 0, highStart = 0;
        int indexEnd = 0, highEnd = 0;
        int length = height.length;

        for (int i = 0; i < length/2; i++) {
            if (height[i] > highStart && indexStart < i){
                highStart = height[i];
                indexStart = i;
            }

        }

        for (int j = length-1; j >= length/2; j--) {
            if (height[j] > highEnd && indexEnd < j){
                highEnd = height[j];
                indexEnd = j;
            }
        }
        System.out.println(highStart +""+ highEnd);
        return 0;
    }
}
