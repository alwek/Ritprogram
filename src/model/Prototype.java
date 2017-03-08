package model;

import java.io.Serializable;

/**
 * Created by dani on 2017-02-25.
 * Inspired by https://github.com/iluwatar/java-design-patterns%E2%80%8B
 */
public abstract class Prototype implements Cloneable, Serializable{
    @Override
    public abstract Object clone() throws CloneNotSupportedException;
}
