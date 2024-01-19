package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class EncryptedClassLoader extends ClassLoader {
    private final String key;
    private final File dir;

    public EncryptedClassLoader(String key, File dir, ClassLoader parent) {
        super(parent);
        this.key = key;
        this.dir = dir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] classData = loadClassData(name);
            return defineClass(name, classData, 0, classData.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Class " + name + " not found", e);
        }
    }



    private byte[] loadClassData(String name) throws IOException {
        String classPath = convertToClassName(name);//"TestEncryption.class";
        File classFile = new File(dir, classPath);

        if (!classFile.exists()) {
            throw new IOException("Class file not found at " + classFile.getPath());
        }

        byte[] encryptedData = Files.readAllBytes(classFile.toPath());
        return decrypt(encryptedData);
    }
    public String convertToClassName(String fullName) {
        int lastDotIndex = fullName.lastIndexOf('.');
        if (lastDotIndex >= 0 && lastDotIndex < fullName.length() - 1) {
            return fullName.substring(lastDotIndex + 1) + ".class";
        } else {
            return fullName + ".class";
        }
    }


    private byte[] decrypt(byte[] data) {
        byte[] decrypted = new byte[data.length];
        int keyInt = 1;

        for (int i = 0; i < data.length; i++) {
            decrypted[i] = (byte) (data[i] + keyInt);
        }

        return decrypted;
    }
}
