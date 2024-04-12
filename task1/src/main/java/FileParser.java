import text_loader.FileTextLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileParser {
    public String getTextFromFile(String path, FileTextLoader fileTextLoader) {
        return fileTextLoader.load(path);
    }

    public String[] splitStringByPattern(String string, String pattern) {
        return string.split(pattern);
    }

    public List<String> getUniqueWords(String[] words) {
        List<String> uniqueWords = new ArrayList<>();
        for (int i = 0; i < words.length; i++){
            String testedWord = words[i];
            int count = 0;
            for (int j = 0; j < words.length; j++){
                if (testedWord.equals(words[j]))
                    count++;
            }
            if (count == 1 && !uniqueWords.contains(testedWord))
                uniqueWords.add(testedWord);
        }
        return uniqueWords;
    }

    public List<String> getUniqueWordsAndSort(String path, FileTextLoader fileTextLoader) {
        String text = getTextFromFile(path, fileTextLoader);
        String[] words = splitStringByPattern(text, "\\s|\\(|\\)|\\.|\\[|\\]|,|\\+|;|\\\\|\"|!|\\||/|=|\\*|@|<|>");
        List<String> uniqueWords = getUniqueWords(words);
        Collections.sort(uniqueWords);
        return uniqueWords;
    }
}
