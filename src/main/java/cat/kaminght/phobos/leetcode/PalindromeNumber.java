package cat.kaminght.phobos.leetcode;

/**
 * <p>
 * 判断是否回文数
 * </p>
 *
 * @author kaminght
 * @since 2020/04/01
 */
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x<0)
            return false;
        if (x==0)
            return true;
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();
        for (int i = 0, j = chars.length - 1; i <= j; i++, j--) {
            if (chars[i] != chars[j]) {
                return false;
            }
        }
        return true;
    }
}
