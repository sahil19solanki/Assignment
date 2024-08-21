package interfaces;

import handlers.MyCollection;

import javax.xml.parsers.ParserConfigurationException;

public interface MyFileHandler {
    void read(MyCollection myCollection) throws ParserConfigurationException;
    void write(MyCollection myCollection);
}
