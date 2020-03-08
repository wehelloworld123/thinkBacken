package com.myIsoland.common.component;


import org.apache.commons.io.output.ByteArrayOutputStream;
import org.crazycake.shiro.RedisSerializer;
import org.crazycake.shiro.SerializationException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class StringRedisSerializer implements RedisSerializer{
    @Override
    public byte[] serialize(Object o) throws SerializationException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut;
        try {
            objOut = new ObjectOutputStream(byteOut);
            objOut.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteOut.toByteArray();
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if(bytes == null) return null;
        ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
        ObjectInputStream objIn;
        Object obj;
        try {
            objIn = new ObjectInputStream(byteIn);
            obj =objIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return obj;
    }
}
