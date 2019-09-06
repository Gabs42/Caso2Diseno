/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectPool;

import Esfera.Esfera;
import java.awt.Color;
import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author Gabriel
 */
public class ConcreteObjectPool extends AbstractObjectPool {
    
    private Stack<IPoolableObject> unused = new Stack();
    private Stack<IPoolableObject> inUse = new Stack();
    private Stack<IPoolableObject> returned = new Stack();
    
    public ConcreteObjectPool(int min,int max,int timeout) {
        super(min,max,timeout);
    }

    
    
    @Override
    public IPoolableObject getObject(int velocidad,Color color,int direccion,Vector posicion) {
        if(this.getMin() !=this.getMax()){
            ConcreteObjectFactory fact = new ConcreteObjectFactory();
            return fact.createNew(velocidad,color,direccion,posicion);
        }
        return unused.pop();
    }

    @Override
    public void releaseObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
