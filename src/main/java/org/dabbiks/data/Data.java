package org.dabbiks.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.dabbiks.Main;
import org.dabbiks.item.Audiobook;
import org.dabbiks.item.Book;
import org.dabbiks.person.Employee;
import org.dabbiks.person.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Data {

    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static <T> void toJson(DataType dataType, String fileName, T object) throws IOException {
        String directoryPath = System.getProperty("user.dir") + "/" + dataType.name().toLowerCase();
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = directoryPath + "/" + fileName + ".json";

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(object, writer);
        }
    }

    public static <T> T fromJson(DataType dataType, String fileName, Class<T> object) throws IOException {
        String filePath = System.getProperty("user.dir") + "/" + dataType.name().toLowerCase() + "/" + fileName + ".json";

        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, object);
        }
    }

    public static <T> List<T> loadAll(DataType dataType, Class<T> object) throws IOException {
        String filePath = System.getProperty("user.dir") + "/" + dataType.name().toLowerCase();
        File fileFolder = new File(filePath);
        List<T> objects = new ArrayList<>();

        if (!fileFolder.exists() || !fileFolder.isDirectory()) {
            return objects;
        }

        File[] files = fileFolder.listFiles();
        if (files == null) return objects;

        for (File file : files) {
            if (!file.getName().endsWith(".json")) continue;

            try (FileReader reader = new FileReader(file.getAbsolutePath())) {
                objects.add(gson.fromJson(reader, object));
            } catch (IOException ignored) {
            }
        }

        return objects;
    }

    public static <T> void saveAll(DataType dataType, List<T> objects) throws IOException {
        String directoryPath = System.getProperty("user.dir") + "/" + dataType.name().toLowerCase();
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        for (T object : objects) {
            String fileName = "default";
            if (object instanceof Book book) fileName = book.generateFileName();
            if (object instanceof Audiobook audiobook) fileName = audiobook.generateFileName();
            if (object instanceof Employee employee) fileName = employee.generateFileName();
            if (object instanceof User user) fileName = user.generateFileName();
            String filePath = System.getProperty("user.dir") + "/" + dataType.name().toLowerCase() + "/" + fileName + ".json";

            try (FileWriter writer = new FileWriter(filePath)) {
                gson.toJson(object, writer);
            }
        }

    }

    public static void clearAll(DataType dataType) {
        String filePath = System.getProperty("user.dir") + "/" + dataType.name().toLowerCase();
        File fileFolder = new File(filePath);
        if (!fileFolder.exists() || !fileFolder.isDirectory()) return;
        for (File file : Objects.requireNonNull(fileFolder.listFiles())) {
            file.delete();
        }
    }

}
