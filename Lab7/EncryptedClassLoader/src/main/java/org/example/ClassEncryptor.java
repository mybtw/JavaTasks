package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ClassEncryptor {
    public static void decryptClassFile(String encryptedClassFilePath, String key, String outputFilePath) throws IOException {
        File encryptedFile = new File(encryptedClassFilePath);
        byte[] encryptedData = loadFileData(encryptedFile);
        byte[] decryptedData = decrypt(encryptedData, key);
        saveFileData(decryptedData, outputFilePath);
    }

    private static byte[] loadFileData(File file) throws IOException {
        try (FileInputStream in = new FileInputStream(file)) {
            byte[] data = new byte[(int) file.length()];
            in.read(data);
            return data;
        }
    }

    private static byte[] decrypt(byte[] data, String key) {
        int keyValue = 1;
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (data[i] - keyValue);
        }
        return data;
    }

    private static void saveFileData(byte[] data, String filePath) throws IOException {
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            out.write(data);
        }
    }
}
