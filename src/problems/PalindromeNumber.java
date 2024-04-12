package problems;

public class PalindromeNumber {
    /**
     * Return if the given number is a palindrome
     * @param args
     */
    public static void main(String[] args) {
        boolean palindrome = isPalindrome(121);
        System.out.println(palindrome);
    }

    public static boolean isPalindrome(int x) {

        String number = Integer.toString(x);

        String[] split = number.split("");

        StringBuilder leftStart = new StringBuilder();
        StringBuilder rightStart = new StringBuilder();

        for (int i = 0; i < split.length; i++) {
            leftStart.append(split[i]);
        }

        for (int j = split.length-1; j >= 0; j--) {
            rightStart.append(split[j]);
        }

        return rightStart.toString().equals(leftStart.toString());
    }

}
