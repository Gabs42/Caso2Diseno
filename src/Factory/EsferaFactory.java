package Factory;

import Esfera.Esfera;
import java.awt.Color;
import java.util.Vector;

/**
 *
 * @author Gabriel
 */
public class EsferaFactory implements EsferaFactoryMethod{

    public EsferaFactory() {
    }
    
    @Override
    public Esfera CreateEsfera(int velocidad, Color color, int direccion,Vector posicion) {
        Esfera x = new Esfera(velocidad,color,direccion,posicion);
        return x;
    }
}
