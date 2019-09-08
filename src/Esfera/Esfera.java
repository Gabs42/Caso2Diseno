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
    private Vector<Integer> posicion;

    private int velX;
    private int velY;
    
    public Esfera(int velocidad, Color color, int direccion, Vector<Integer> posicion) {
        this.velocidad = velocidad;
        this.color = color;
        this.direccion = direccion;
        this.posicion = posicion;
        calcularVelocidad();
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
    public void actualizar(int largo, int ancho, int diametro) {
      comprobarColision(largo, ancho, diametro);
      posicion.set(0, clamp(posicion.get(0) + velX, 0, largo));
      posicion.set(1, clamp(posicion.get(1) - velY, 0, ancho));
    }
    
    private int clamp(int value, int min, int max) {
      if(value < min) {
        value = min;
      }
      else if(value > max) {
        value = max;
      }
      return value;
    }
    
    private void comprobarColision(int largo, int ancho, int diametro) {
      int p1X = posicion.get(0) + diametro;
      int p1Y = posicion.get(1) + diametro;
      if(posicion.get(0) <= 0 || p1X >= largo) {
        velX *= -1;
      }
      else if(posicion.get(1) <= 0 || p1Y >= ancho) {
        velY *= -1;
      }
    }
    
    private void calcularVelocidad() {
      double radianes = direccion * Math.PI / 180;
      velX = (int) (velocidad * Math.cos(radianes));
      velY = (int) (velocidad * Math.sin(radianes));
    }
    
    public static class EsferaBuilder implements IBuilder {
        private int velocidad;
        private Color color;
        private int direccion;
        private Vector<Integer> posicion;
        
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
        
        public EsferaBuilder  setPosicion(Vector<Integer> posicion){
            this.posicion = posicion;
            return this;
        }
        @Override
        public Esfera build() {
            return new Esfera(this.velocidad,this.color,this.direccion,this.posicion);
        }        
        
    }
}
