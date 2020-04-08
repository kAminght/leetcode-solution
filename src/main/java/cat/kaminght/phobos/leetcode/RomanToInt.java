package cat.kaminght.phobos.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 转换罗马数字到整数
 * </p>
 * 4x 小大 1级
 * 5x 大
 * 3x 大大大
 * 2x 大大
 * 1x 大
 * 6x 大小 1级
 * 7x 大小小
 * 8x 大小小小
 * 9x 小大 2级
 *
 * @author kaminght
 * @since 2020/04/03
 */
public class RomanToInt {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

//        StringBuilder sb = new StringBuilder();
        int sum = 0;
        return sum += getNumber(s, map);
    }
    
    private int getNumber(String s, Map<Character, Integer> map) {
        if (s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return getSumAtStringBeginningByCount(s, map, 1);
        }
        if (s.length() == 2) {
            if (compareFirstAndSecondLetter(s, map) < 0) {
                return map.get(s.charAt(1)) - map.get(s.charAt(0));
            } else if (compareFirstAndSecondLetter(s, map) == 0) {
                return getSumAtStringBeginningByCount(s, map, 2);
            } else {
                if (checkIfBeTogother(s, map)) {
                    return getSumAtStringBeginningByCount(s, map, 2);
                } else {
                    return getSumAtStringBeginningByCount(s, map, 1)
                        + getNumber(s.substring(1), map);
                }
            }
        }
        if (compareFirstAndSecondLetter(s, map) < 0) {
            return map.get(s.charAt(1)) - map.get(s.charAt(0)) + getNumber(s.substring(2), map);
        } else if (compareFirstAndSecondLetter(s, map) == 0) {
            if (s.charAt(1) == s.charAt(2)) {
                return getSumAtStringBeginningByCount(s, map, 3)
                    + getNumber(s.substring(3), map);
            } else {
                return getSumAtStringBeginningByCount(s, map, 2) + getNumber(s.substring(2), map);
            }
        } else {
            if (checkIfBeTogother(s, map)) {
                if (checkIfContinuousEqualBehindFirstLetterByCount(s, 3)) {
                    return getSumAtStringBeginningByCount(s, map, 4) + getNumber(s.substring(4), map);
                } else if (checkIfContinuousEqualBehindFirstLetterByCount(s, 2)) {
                    return getSumAtStringBeginningByCount(s, map, 3)
                        + getNumber(s.substring(3), map);
                } else {
                    return getSumAtStringBeginningByCount(s, map, 2) + getNumber(s.substring(2), map);
                }
            } else {
                return getSumAtStringBeginningByCount(s, map, 1) + getNumber(s.substring(1), map);
            }
        }
    }
    
    private boolean checkIfContinuousEqualBehindFirstLetterByCount(String s, int count) {
        if (s.length() - 1 < count) {
            return false;
        }
        for (int i = 1; i < count; i++) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }
    
    private int getSumAtStringBeginningByCount(String s, Map<Character, Integer> map, int count) {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += map.get(s.charAt(i));
        }
        return sum;
    }
    
    private boolean checkIfBeTogother(String s, Map<Character, Integer> map) {
        if (String.valueOf(map.get(s.charAt(0))).length() != String.valueOf(map.get(s.charAt(1))).length()) {
            return false;
        }
        return String.valueOf((map.get(s.charAt(0)) - map.get(s.charAt(1)))).startsWith("4");
    }
    
    private int compareFirstAndSecondLetter(String s, Map<Character, Integer> map) {
        return map.get(s.charAt(0)) - map.get(s.charAt(1));
    }
    
}
