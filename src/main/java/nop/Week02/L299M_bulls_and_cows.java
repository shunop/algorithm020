package nop.Week02;

/*
你在和朋友一起玩 猜数字（Bulls and Cows）游戏，该游戏规则如下：

你写出一个秘密数字，并请朋友猜这个数字是多少。
朋友每猜测一次，你就会给他一个提示，告诉他的猜测数字中有多少位属于数字和确切位置都猜对了（称为“Bulls”, 公牛），有多少位属于数字猜对了但是位置不对（称为“Cows”, 奶牛）。
朋友根据提示继续猜，直到猜出秘密数字。
请写出一个根据秘密数字和朋友的猜测数返回提示的函数，返回字符串的格式为 xAyB ，x 和 y 都是数字，A 表示公牛，用B表示奶牛。

xA 表示有 x 位数字出现在秘密数字中，且位置都与秘密数字一致。
yB 表示有 y 位数字出现在秘密数字中，但位置与秘密数字不一致。
请注意秘密数字和朋友的猜测数都可能含有重复数字，每位数字只能统计一次。

示例 1:

输入: secret = "1807", guess = "7810"
输出: "1A3B"
解释: 1公牛和3奶牛。公牛是 8，奶牛是 0, 1和 7。
示例 2:

输入: secret = "1123", guess = "0111"
输出: "1A1B"
解释: 朋友猜测数中的第一个 1是公牛，第二个或第三个 1可被视为奶牛。

说明: 你可以假设秘密数字和朋友的猜测数都只包含数字，并且它们的长度永远相等。
链接：https://leetcode-cn.com/problems/bulls-and-cows
 */
public class L299M_bulls_and_cows {
    public String getHint(String secret, String guess) {
        int[] existingNumbers = new int[10];
        int len = secret.length();
        for (int i = 0; i < len; i++) {
            int c = Character.getNumericValue(secret.charAt(i));
            existingNumbers[c]++;
            /*if (existingNumbers[c]==0) {
                existingNumbers[c]=1;
                if ( ++tmp == 10) break;
            }*/
        }
        int a = 0, b = 0;
        for (int i = 0; i < len; i++) {
            char c = guess.charAt(i);
            if (secret.charAt(i) == c) {
                a++;
                if (existingNumbers[Character.getNumericValue(c)]-- <= 0) b--;
            } else {
                if (existingNumbers[Character.getNumericValue(c)]-- > 0) b++;
            }
        }
        return String.format("%dA%dB", a, b);
    }

    //题解
    public String getHint_tijie(String secret, String guess) {
        int[] bucket = new int[10];
        int bull = 0;
        int cow = 0;
        for(int i=0;i<secret.length();i++){
            if(secret.charAt(i)== guess.charAt(i)){
                bull++;
                continue;
            }
            bucket[secret.charAt(i) - '0'] += 1;
            bucket[guess.charAt(i) - '0'] -= 1;

        }
        //计算bucket中正值的个数
        for(int i=0;i<10;i++){
            if(bucket[i] > 0)
                cow+= bucket[i];
        }

        cow = secret.length() - cow - bull;
        String res = bull + "A" + cow + "B";
        return res;
    }

//    作者：bug-19
//    链接：https://leetcode-cn.com/problems/bulls-and-cows/solution/javafang-fa-zhi-shi-yong-yi-lei-tong-jiu-gou-liao-/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    public static void main(String[] args) {
        L299M_bulls_and_cows instance = new L299M_bulls_and_cows();
        System.out.println(instance.getHint("1807", "7810"));//1A3B
        System.out.println(instance.getHint("1123", "0111"));//1A1B
        System.out.println(instance.getHint("1234", "0111"));//0A1B
        System.out.println(instance.getHint("1122", "1222"));//3A0B
//        System.out.println((int)"1".charAt(0));
//        System.out.println(Character.getNumericValue("1".charAt(0)));
    }
}
