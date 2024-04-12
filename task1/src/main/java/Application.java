import text_loader.TxtFileTextLoader;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        // args[0] for example "src\\main\\resources\\test_file.txt"
        List<String> words = fileParser.getUniqueWordsAndSort(args[0], TxtFileTextLoader::load);
        words.forEach(System.out::println);
    }
}
