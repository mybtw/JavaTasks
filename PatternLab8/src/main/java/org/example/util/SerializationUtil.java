package org.example.util;

import org.example.proxy.cache.FileSaveObj;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class SerializationUtil {
    /**
     * сериализация файла
     * @param filename  путь к файлу
     * @param fileSaveObj  объект для сериализации
     */
    public static <T> void serializeFileSaveObj(String filename, FileSaveObj<T> fileSaveObj) throws IOException {
        if (filename.endsWith(".zip")) {
            // Создание временного файла для сериализации
            File tempFile = File.createTempFile("temp", ".bin");
            try {
                // Сериализация объекта во временный файл
                serializeObjectToFile(tempFile, fileSaveObj);
                // Создание ZIP-архива и добавление в него сериализованного файла
                try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(filename))) {
                    zos.putNextEntry(new ZipEntry(tempFile.getName()));
                    try (FileInputStream fis = new FileInputStream(tempFile)) {
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = fis.read(buffer)) > 0) {
                            zos.write(buffer, 0, length);
                        }
                        zos.closeEntry();
                    }
                }
            } catch (IOException e) {
                throw new IOException("Ошибка при создании zip архива", e);
            } finally {
                // Удаление временного файла
                tempFile.delete();
            }
        } else {
            // Прямая сериализация объекта в файл
            serializeObjectToFile(new File(filename), fileSaveObj);
        }
    }

    private static <T> void serializeObjectToFile(File file, FileSaveObj<T> object) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(object);
        } catch (NotSerializableException e) {
            throw new IOException("Ошибка сериализации: объект не сериализуемый", e);
        } catch (IOException e) {
            throw new IOException("Ошибка ввода/вывода при сериализации", e);
        }
    }

    /**
     * десериализация файла
     * @param filename  путь к файлу
     * @return  объект после десериализации
     */
    public static <T> FileSaveObj<T> deserializeFileSaveObj(String filename) throws IOException,
            ClassNotFoundException {
        if (filename.endsWith(".zip")) {
            // Извлечение и десериализация из ZIP-архива
            try (ZipInputStream zis = new ZipInputStream(new FileInputStream(filename))) {
                ZipEntry entry = zis.getNextEntry();
                if (entry != null) {
                    try (ObjectInputStream ois = new ObjectInputStream(zis)) {
                        return (FileSaveObj<T>) ois.readObject();
                    }
                }
            } catch (FileNotFoundException e) {
                throw new IOException("Файл не найден: " + filename, e);
            } catch (IOException e) {
                throw new IOException("Ошибка ввода/вывода при десериализации", e);
            } catch (ClassNotFoundException e) {
                throw new ClassNotFoundException("Класс объекта для десериализации не найден", e);
            }
        } else {
            // Прямая десериализация из файла
            try (FileInputStream fis = new FileInputStream(filename);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                return (FileSaveObj<T>) ois.readObject();
            } catch (FileNotFoundException e) {
                throw new IOException("Файл не найден: " + filename, e);
            } catch (IOException e) {
                throw new IOException("Ошибка ввода/вывода при десериализации", e);
            } catch (ClassNotFoundException e) {
                throw new ClassNotFoundException("Класс объекта для десериализации не найден", e);
            }
        }
        return null;
    }
}
