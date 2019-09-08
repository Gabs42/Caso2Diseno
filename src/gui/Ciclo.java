package gui;

import java.util.ArrayList;

import Esfera.Esfera;

public class Ciclo implements Runnable {
  
  Animacion canvas;
  int sleep;
  boolean continuar;
  
  public Ciclo(Animacion canvas, int sleep) {
    this.canvas = canvas;
    this.sleep = sleep;
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
    ArrayList<Esfera> esferas = canvas.getEsferas();
    for(int i = 0; i < esferas.size(); i++) {
      Esfera esfera = esferas.get(i);
      esfera.actualizar(canvas.getWidth(), canvas.getHeight(), canvas.getDiametro());
    }
  }
  
}
