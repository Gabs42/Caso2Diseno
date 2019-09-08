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
  
  Color[] colores = {Color.RED, Color.BLUE, Color.YELLOW, Color.CYAN};
  
  public Animacion() {
    esferas = new ArrayList<Esfera>();
    Random r = new Random();
    for(int i = 0; i < 1000; i++) {
      int x = r.nextInt((300 - 30) + 1) + 30;
      int y = r.nextInt((400 - 30) + 1) + 30;
      int angle = r.nextInt((360 - 0) + 1) + 0;
      int vel = r.nextInt((7 - 3) + 1) + 3;
      Color color = colores[r.nextInt((3 - 0) + 1) + 0];
      Vector<Integer> vector = new Vector<Integer>(2, 1);
      vector.add(0, x);
      vector.add(1, y);
      Esfera esfera = new Esfera(5, color, angle, vector);
      esferas.add(esfera);
    }
    ciclo = new Ciclo(this, esferas, frames);
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
