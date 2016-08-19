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
public class StringState extends State {

    String value;

    /**
     *
     * @param value
     */
    public StringState(String value) {
        this.type = 2;
        this.value = value;

    }

    /**
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    public void setValue(String value) {
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
            StringState iS = (StringState) s;
            return iS.getValue().equals(this.value);

        }
        throw new UnsupportedOperationException("Wrong type");
    }

    @Override
    public String toString() {

        return value;
    }

}
