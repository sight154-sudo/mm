package com.itheima.factory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author: tang
 * @date: Create in 16:53 2021/7/1
 * @description:创建service实现类的工厂类
 */
public class BeanFactory {

    private static final Map<String,Object> cache = new HashMap<String,Object>();

    static{
        try {
            InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            Properties prop = new Properties();
            prop.load(is);
            Set<String> strs = prop.stringPropertyNames();
            strs.forEach(str->{
                String className = prop.getProperty(str);
                Object obj = null;
                try {
                    obj = Class.forName(className).getConstructor().newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                cache.put(str,obj);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static <T> T getBean(String classname,Class<T> clazz){
        return (T)cache.get(classname);
    }

}
