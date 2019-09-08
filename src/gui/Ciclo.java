package gui;

import java.util.ArrayList;

import Esfera.Esfera;

public class Ciclo implements Runnable {
  
  ArrayList<Esfera> esferas;
  Animacion canvas;
  int sleep;
  boolean continuar;
  
  public Ciclo(Animacion canvas, ArrayList<Esfera> esferas, int sleep) {
    this.canvas = canvas;
    this.sleep = sleep;
    this.esferas = esferas;
    this.continuar = true;
  }
  
  public synchronized void setContinuar(boolean continuar) {
    this.continuar = continuar;
  }
  
  @Override
  public void run() {
    while(continuar) {  
      try {
        ejecutarCiclo();
      } 
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void ejecutarCiclo() throws InterruptedException {
    actualizarEsferas();
    canvas.repaint();
    Thread.sleep(sleep);
  }

  private void actualizarEsferas() {
    for(int i = 0; i < esferas.size(); i++) {
      Esfera esfera = esferas.get(i);
      esfera.actualizar(canvas.getWidth(), canvas.getHeight(), canvas.getDiametro());
    }
  }
  
}
