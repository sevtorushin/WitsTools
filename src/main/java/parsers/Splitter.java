package parsers;

public interface Splitter<T, U> {
    T[] split(U data);
}
