package org.example;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException, ClassNotFoundException {

        String sourceClassPath = "C:\\Users\\astaf\\IdeaProjects\\JavaTasks\\Lab7\\Plugin\\target\\classes\\org\\example\\TestEncryption.class";
        String encryptedClassPath = "C:\\Users\\astaf\\IdeaProjects\\JavaTasks\\Lab7\\encryptedClasses\\TestEncryption.class";
        String key = "myEncryptionKey";

        ClassEncryptor.decryptClassFile(sourceClassPath, key, encryptedClassPath);

        File decryptedClassFile = new File(encryptedClassPath);
        EncryptedClassLoader classLoader = new EncryptedClassLoader(key, decryptedClassFile.getParentFile(), ClassLoader.getSystemClassLoader());
        Class<?> testClass = classLoader.findClass("org.example.TestEncryption");
        Object testInstance = testClass.newInstance();
        Method sayHello = testClass.getDeclaredMethod("doUsefull");
        sayHello.invoke(testInstance);


    }
}