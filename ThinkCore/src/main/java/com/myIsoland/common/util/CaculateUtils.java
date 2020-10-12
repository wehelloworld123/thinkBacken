package com.myIsoland.common.util;
import java.security.SecureRandom;
import java.util.Random;

public class CaculateUtils {
    private static final String SYMBOLS = "0123456789"; // 数字

    private static final Random RANDOM = new SecureRandom();

    public static String translateId(String prefix, String no) {
        if (StringUtils.isEmpty(no)) {
            return null;
        }
        if (no.indexOf(prefix) != -1) {
            return no.substring(prefix.length());
        } else {
            return no;
        }
    }


    /**
     * 获取长度为 6 的随机数字
     *
     * @return 随机数字
     * @date 修改日志：由 space 创建于 2018-8-2 下午2:43:51
     */
    public static String getNonce_str(int length) {

        // 如果需要4位，那 new char[4] 即可，其他位数同理可得
        char[] nonceChars = new char[length];

        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }

        return new String(nonceChars);

    }
}
