package cat.kaminght.phobos.leetcode;

/**
 * <p>
 * 查找字符串数组最长公共串
 * </p>
 *
 * @author kaminght
 * @since 2020/04/03
 */
public class LongestCommonPrefixInStringArray {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        StringBuilder sb = new StringBuilder();
        String starter = strs[0];
        outer:
        for (int i = 0; i < starter.length(); i++) {
            char ch = starter.charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() < i + 1) {
                    break outer;
                }
                if (strs[j].charAt(i) != ch) {
                    break outer;
                }
            }
            sb.append(ch);
        }
        if (sb.length() == 0) {
            return "";
        }
        return sb.toString();
    }
}
