package dictionary;

public class Main {
    public static void main(String[] args) {
        SaveableDictionary saveableDictionary = new SaveableDictionary("src/main/java/dictionary/words.txt");
        boolean wasSuccessful = saveableDictionary.load();
        if (wasSuccessful) {
            System.out.println("Successfully loaded the dictionary from file");
        }
        System.out.println(saveableDictionary.translate("apina"));
        System.out.println(saveableDictionary.translate("ohjelmointi"));
        System.out.println(saveableDictionary.translate("alla oleva"));
    }
}
