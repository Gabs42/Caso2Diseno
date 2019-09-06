/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectPool;

/**
 *
 * @author Gabriel
 */
public abstract class AbstractObjectPool implements IObjectPool {
    private int max;
    private int min;
    private int timeout;

    public AbstractObjectPool(int max, int min, int timeout) {
        this.max = max;
        this.min = min;
        this.timeout = timeout;
    }

    public int getMax() {
        return max;
    }


    public int getMin() {
        return min;
    }


    public int getTimeout() {
        return timeout;
    }

    
    
    
}
