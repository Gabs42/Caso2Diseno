/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Esfera;

import Builder.IBuilder;
import ObjectPool.IPoolableObject;
import Prototype.IPrototype;
import java.awt.Color;
import java.util.Vector;

/**
 *
 * @author Gabriel
 */
public class Esfera implements IPrototype,IPoolableObject,IFigura{
    private int velocidad;
    private Color color;
    private int direccion;
    private Vector posicion;

    public Esfera(int velocidad, Color color, int direccion,Vector posicion) {
        this.velocidad = velocidad;
        this.color = color;
        this.direccion = direccion;
        this.posicion = posicion;
    }
    
    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public Vector getPosicion() {
        return posicion;
    }

    public void setPosicion(Vector posicion) {
        this.posicion = posicion;
    }
    
    
    @Override
    public IPrototype clonar() {
        Esfera copia = new Esfera(this.velocidad,this.color,this.direccion,this.posicion);
        return copia;
    }

    @Override
    public void actualizar() {
        
    }
    
    public static class EsferaBuilder implements IBuilder{
        private int velocidad;
        private Color color;
        private int direccion;
        private Vector posicion;
        
        public EsferaBuilder setVelocidad(int velocidad){
            this.velocidad = velocidad;
            return this;
        }
        
        public EsferaBuilder setColor(Color color){
            this.color = color;
            return this;
        }
        
        public EsferaBuilder setDireccion(int direccion){
            this.direccion = direccion;
            return this;
        }
        
        public EsferaBuilder  setPosicion(Vector posicion){
            this.posicion = posicion;
            return this;
        }
        @Override
        public Object build() {
            return new Esfera(this.velocidad,this.color,this.direccion,this.posicion);
        }
        
        
    }
}
