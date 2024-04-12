package text_loader;

@FunctionalInterface
public interface FileTextLoader {
    String load(String path);
}
