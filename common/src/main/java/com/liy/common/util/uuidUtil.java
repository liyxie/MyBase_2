package com.liy.common.util;

import java.util.UUID;

/**
 * @Author LiY
 * 随机数列生成
 */

public class uuidUtil {
    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUUID()
    {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID()
    {
        return UUID.randomUUID().toString();
    }

//    public String toString(boolean isSimple)
//    {
//        final StringBuilder builder = new StringBuilder(isSimple ? 32 : 36);
//        // time_low
//        builder.append(digits(mostSigBits >> 32, 8));
//        if (!isSimple)
//        {
//            builder.append('-');
//        }
//        // time_mid
//        builder.append(digits(mostSigBits >> 16, 4));
//        if (!isSimple)
//        {
//            builder.append('-');
//        }
//        // time_high_and_version
//        builder.append(digits(mostSigBits, 4));
//        if (!isSimple)
//        {
//            builder.append('-');
//        }
//        // variant_and_sequence
//        builder.append(digits(leastSigBits >> 48, 4));
//        if (!isSimple)
//        {
//            builder.append('-');
//        }
//        // node
//        builder.append(digits(leastSigBits, 12));
//
//        return builder.toString();
//    }

//    /**
//     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
//     *
//     * @return 随机UUID
//     */
//    public static String fastUUID()
//    {
//        return UUID.fastUUID().toString();
//    }
//
//    /**
//     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
//     *
//     * @return 简化的UUID，去掉了横线
//     */
//    public static String fastSimpleUUID()
//    {
//        return UUID.fastUUID().toString(true);
//    }
}
