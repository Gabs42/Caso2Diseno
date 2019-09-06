/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Esfera.Esfera;
import java.awt.Color;
import java.util.Vector;

/**
 *
 * @author Gabriel
 */
public interface EsferaFactoryMethod {
    
    public Esfera CreateEsfera(int velocidad,Color color,int direccion,Vector posicion); 
}
