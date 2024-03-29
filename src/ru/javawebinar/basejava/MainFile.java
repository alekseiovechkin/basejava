package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    private static void printListFiles(File dir, String tab) {
        File[] files = dir.listFiles();
        for (File file : Objects.requireNonNull(files)) {
            if (file.isFile()) {
                System.out.println(tab + "F: " + file.getName());
            } else {
                System.out.println(tab + "D: " + file.getName().toUpperCase());
                printListFiles(file, "\t" + tab);
            }
        }
    }

    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\nСписок файлов в проекте:");
        printListFiles(dir, "");
    }
}
