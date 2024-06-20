package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class SaveableDictionary {
    Map<String, String> dictionary;
    String fileName;

    public SaveableDictionary() {
        dictionary = new HashMap<>();
    }

    public SaveableDictionary(String fileName) {
        this();
        this.fileName = fileName;
    }

    public void add(String word, String translation) {
        this.dictionary.putIfAbsent(word, translation);
        this.dictionary.putIfAbsent(translation, word);
    }

    public String translate(String word) {
        return this.dictionary.get(word);
    }

    public void delete(String word) {
        this.dictionary.remove(this.dictionary.get(word));
        this.dictionary.remove(word);
    }

//    public boolean load() {
//        Path path = Paths.get(this.fileName);
//        try (Stream<String> file = Files.lines(path)) {
//            file
//                    .map(row -> row.split(":"))
//                    .forEach(rowArray->{
//                        this.dictionary.putIfAbsent(rowArray[0], rowArray[1]);
//                        this.dictionary.putIfAbsent(rowArray[1], rowArray[0]);
//            });
//            return true;
//        } catch(IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    public boolean load() {
        File file = new File(this.fileName);
        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNext()) {
                String row = scanner.nextLine();
                String[] parts = row.split(":");
                this.dictionary.putIfAbsent(parts[0], parts[1]);
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean save() {
        try {
            List<String> values = new ArrayList<>();
            PrintWriter writer = new PrintWriter(this.fileName);
            for (String key: this.dictionary.keySet()) {
                if (!(values.contains(key))) {
                    writer.println(key + ": " + this.dictionary.get(key));
                    values.add(this.dictionary.get(key));
                }
            }
            writer.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

    }

}
