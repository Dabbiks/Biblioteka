package org.dabbiks.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.List;

public interface Data {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static void saveToJson(List<Object> list, String path) throws IOException {
        try (Writer writer = new FileWriter(path)) {
            gson.toJson(list, writer);
        }
    }

    static List<Object> loadFromJson(String path, Object object) throws IOException {
        try (Reader reader = new FileReader(path)) {
            return gson.fromJson(reader, List<Object>);
        }
    }

    static <T> List<T> loadFromJson(String path, Class<T[]> loadedClass) throws IOException {
        try (Reader reader = new FileReader(path)) {
            T[] array = gson.fromJson(reader, loadedClass);
            return List.of(array);
        }
    }

}
