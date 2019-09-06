/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectPool;

import java.awt.Color;
import java.util.Vector;

/**
 *
 * @author Gabriel
 */
public interface IObjectFactory {
    
    public  IPoolableObject createNew(int velocidad,Color Color,int direccion,Vector posicion); 
}
