package arden.java.interfaces;

import java.io.File;

public interface Parser<T> {
    T parse(File file);
}
