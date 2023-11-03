package parsers.splitters;

import exceptions.SplitException;

public interface Splitter<T, U> {
    T[] split(U data) throws SplitException;
}
