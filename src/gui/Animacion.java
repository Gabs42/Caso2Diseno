package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import javax.swing.JPanel;

import Esfera.Esfera;

public class Animacion extends JPanel {
  
  Color colorSuperficie = Color.WHITE;
  Color colorBordes = Color.BLUE;
  
  int offsetX = 1;
  int offsetY = 1;
  
  int diametro = 10;
  
  int frames = 34;
  
  ArrayList<Esfera> esferas;
  
  Ciclo ciclo;
  
  public Animacion() {
    esferas = new ArrayList<Esfera>();
    ciclo = new Ciclo(this, frames);
  }
  
  public void iniciarAnimacion() {
    Thread thread = new Thread(ciclo);
    thread.start();
  }
  
  public int getDiametro() {
    return diametro;
  }
  
  public synchronized void setEsferas(ArrayList<Esfera> esferas) {
    this.esferas = esferas;
  }
  
  public synchronized ArrayList<Esfera> getEsferas() {
    return esferas;
  }
  
  public void paint(Graphics g) {
    super.paint(g);
    reiniciarSuperficie(g);
    dibujarEsferas(g);
    crearBordes(g);
  }
  
  private void reiniciarSuperficie(Graphics g) {
    g.setColor(colorSuperficie);
    g.fillRect(0, 0, getWidth(), getHeight());
  }
  
  private void crearBordes(Graphics g) {
    g.setColor(colorBordes);
    g.drawRect(0, 0, getWidth() - offsetX, getHeight() - offsetY);
  }
  
  private void dibujarEsferas(Graphics g) {
    for(int i = 0; i < esferas.size(); i++) {
      dibujarEsfera(esferas.get(i), g);
    }
  }
  
  private void dibujarEsfera(Esfera esfera, Graphics g) {
    g.setColor(esfera.getColor());
    g.fillOval((int)esfera.getPosicion().get(0), (int)esfera.getPosicion().get(1), diametro, diametro);
  }
  
}
