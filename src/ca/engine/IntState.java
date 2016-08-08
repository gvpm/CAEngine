/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.engine;

/**
 *
 * @author gvpm
 */
public class IntState extends State {

    int value;

    /**
     *
     * @param value
     */
    public IntState(int value) {
        this.type = 1;
        this.value = value;

    }

    /**
     *
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     *
     * @param s
     * @return
     */
    @Override
    public boolean compare(State s) {

        if (s.getType() == this.type) {
            IntState iS = (IntState) s;
            return iS.getValue() == this.value;

        }
        throw new UnsupportedOperationException("Wrong type");
    }

    @Override
    public String toString() {

        return Integer.toString(value);
    }

}
