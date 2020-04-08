package cat.kaminght.phobos.leetcode;

/**
 * <p>
 * 反转整数
 * </p>
 *
 * @author kaminght
 * @since 2020/04/01
 */
public class ReverseInteger {
    public int reverse(int x) {
        if (x == 0)
            return 0;
        long number;
        int result = 0;
        if (x < 0) {
            String str = String.valueOf(x);
            StringBuilder sbOne = new StringBuilder(str);
            String rts = sbOne.reverse().toString();
            int idx = rts.indexOf("-");
            String subs = rts.substring(0, idx);
            number = Long.parseLong(subs);
            try{
                result = Math.toIntExact(Long.parseLong("-" + number));
            }catch(Exception ex){
                // ignore
            }
        }else {
            StringBuilder sbTwo = new StringBuilder(String.valueOf(x));
            String reverse = sbTwo.reverse().toString();
            number = Long.parseLong(reverse);
            try{
                result = Math.toIntExact(number);
            }catch(Exception ex){
                // ignore
            }
        }
        return result;
    }
}
