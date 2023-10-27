package parsers;

import exceptions.WitsParseException;

public interface Parser<T, U> {
    T parse(U data) throws WitsParseException;
}
