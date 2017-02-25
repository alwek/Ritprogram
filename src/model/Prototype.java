package model;

import java.io.Serializable;

/**
 * Created by dani on 2017-02-25.
 */
public abstract class Prototype implements Cloneable, Serializable{
    @Override
    public abstract Object clone() throws CloneNotSupportedException;
}
