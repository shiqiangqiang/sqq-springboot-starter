package com.sqq.react;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: 反射场景类
 * 在阅读Class类文档时发现一个特点，以通过反射获得Method对象为例，一般会提供四种方法，getMethod(parameterTypes)、getMethods()、getDeclaredMethod(parameterTypes)和getDeclaredMethods()。getMethod(parameterTypes)用来获取某个公有的方法的对象，getMethods()获得该类所有公有的方法，getDeclaredMethod(parameterTypes)获得该类某个方法，getDeclaredMethods()获得该类所有方法。带有Declared修饰的方法可以反射到私有的方法，没有Declared修饰的只能用来反射公有的方法。其他的Annotation、Field、Constructor也是如此。
 *
 * 在ReflectClass类中还提供了两种反射PowerManager.shutdown()的方法，在调用的时候会输出如下log，提示没有相关权限。之前在项目中尝试反射其他方法的时候还遇到过有权限和没权限返回的值不一样的情况。如果源码中明确进行了权限验证，而你的应用又无法获得这个权限的话，建议就不要浪费时间反射了。
 *
 * 链接：https://www.jianshu.com/p/9be58ee20dee
 *
 * @author: shiqiangqiang
 * @createDate: 2020/3/26
 * @version: 1.0
 */
public class Client {
    private final static Logger log = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        try {
            // 创建对象
            ReflectClass.reflectNewInstance();

            // 反射私有的构造方法
            ReflectClass.reflectPrivateConstructor();

            // 反射私有属性
            ReflectClass.reflectPrivateField();

            // 反射私有方法
            ReflectClass.reflectPrivateMethod();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}