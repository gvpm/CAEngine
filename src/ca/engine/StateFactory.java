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
public class StateFactory {
    
    public State fabricate(int type, Object value){
        
        switch (type) {
            case 1:                
                return new IntState((int)value);
            case 2:
                return null;
            case 3:
                return null;    
         
        }

        return null;
    }
    
}