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
public class CellFactory {
    
    /**
     *
     * @param type
     * @param id
     * @param rule
     * @return
     */
    public Cell fabricate(int type,int id,boolean rule){
        
        switch (type) {
            case 1:                
                return new SquareCell(id,rule);
            case 2:
                return null;
            case 3:
                return null;    
         
        }

        return null;
    }
    
}
