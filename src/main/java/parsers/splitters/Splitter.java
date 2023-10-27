package parsers.splitters;

public interface Splitter<T, U> {
    T[] split(U data);
}
