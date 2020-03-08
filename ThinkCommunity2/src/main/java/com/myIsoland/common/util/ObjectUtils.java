package com.myIsoland.common.util;


import com.myIsoland.common.component.ThinkRPCException;
import com.myIsoland.enums.CodeEnum;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class ObjectUtils {

    /**
     * 判断对象是否为空
     * @param o
     * @return
     * @author hw
     * @date 2018年12月11日
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        } else if (o instanceof String) {
            String s = (String) o;
            if ("".equals(s.trim())) {
                return true;
            } else {
                return false;
            }
        } else if (o instanceof Collection) {
            return ((Collection) o).isEmpty();
        } else if (o instanceof Map) {
            return ((Map) o).isEmpty();
        } else if (o.getClass().isArray()) {
            return Array.getLength(o) == 0;
        } else {
            return false;
        }
    }

    /**
     * 将对象序列化
     * @param o
     * @return
     * @author hw
     * @date 2018年12月14日
     */
    public static byte[] serialize(Object o) throws IOException {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        } finally {
            closeObjectOutputStream(oos);
        }
        return baos.toByteArray();
    }

    /**
     * 将对象反序列化
     * @param byteArr
     * @return
     * @author hw
     * @date 2018年12月14日
     */
    public static Object unSerialize(byte[] byteArr) throws IOException {
        ObjectInputStream ois = null;
        ByteArrayInputStream bais = new ByteArrayInputStream(byteArr);
        Object o = null;
        try {
            ois = new ObjectInputStream(bais);
            o = ois.readObject();
        } catch (Exception e) {
            throw new ThinkRPCException(CodeEnum.REDIS_EXCEPTION);
        } finally {
            closeObjectIutputStream(ois);
        }
        return o;
    }

    /**
     * 关闭对象输出流
     * @param oos
     * @author hw
     * @date 2018年12月14日
     */
    private static void closeObjectOutputStream(ObjectOutputStream oos) throws IOException {
        if (oos != null) {
                oos.close();
        }
        oos = null;
    }

    /**
     * 关闭对象输入流
     * @param ois
     * @author hw
     * @date 2018年12月14日
     */
    private static void closeObjectIutputStream(ObjectInputStream ois) throws IOException  {
        if (ois != null) {
                ois.close();
        }
        ois = null;
    }
}
