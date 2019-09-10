/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Esfera.Esfera;
import Esfera.Esfera.EsferaBuilder;
import Factory.EsferaFactory;
import ObjectPool.ConcreteObjectPool;
import Prototype.PrototypeFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 *
 * @author Gabriel
 */
public class Ventana extends JFrame {
    
    Animacion canvas;
    
    int xCanvas = 240;
    int yCanvas = 10;
    int largoCanvas = 620;
    int anchoCanvas = 420;
    
    JButton botonCrear;
    JButton botonNuevo;
    JPanel contenedor;
    JLabel labelContenedor;
    
    JComboBox<String> comboDireccion;
    JComboBox<Integer> comboVelocidad;
    JComboBox<String> comboPatron;
    JSlider red,green,blue;
    JTextField cantidad;
    
    Color colorEsfera;
    
    private ArrayList<String> direcciones = new ArrayList<>();
    private ArrayList<Integer> velocidades = new ArrayList<>();
    private ArrayList<String> patrones = new ArrayList<>();
    private int velocidadMinima = 1;
    private int velocidadMaxima = 10;
     
    public Ventana(){
        cargarVentana();
        cargarBotones();
        cargarDireccion();
        cargarSliders();
        cargarVelocidades();
        cargarCanvas();
        cargarPatron();
        cargarCantidad();
        incializarPrototype();
        cambiarColor();
        mostrarVentana();
    }
    
    public void iniciarAnimacion() {
      canvas.iniciarAnimacion();
    }
    
    private void cargarVentana() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(900, 480);
    }
    
    private void cargarBotones() {
      botonNuevo = new JButton("Crear Esferas");
	  botonNuevo.setBounds(20, 400, 130, 30);
	  botonNuevo.addActionListener(new ActionListener() {      
	    @Override
	    public void actionPerformed(ActionEvent e) {   
               
	      /*Color[] colores = {Color.RED, Color.BLUE, Color.YELLOW, Color.CYAN};
	      Random r = new Random();
	      ArrayList<Esfera> esferas = canvas.getEsferas();
	      for(int i = 0; i < 10; i++) {
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
	      canvas.setEsferas(esferas);*/ //Ejemplo de como crear y anadir esferas
              crearEsferas();
	      agregarEsfera();
	    }          
      });
	  add(botonNuevo);
    }
    
    private void mostrarVentana() {
        setLayout(null);
        setVisible(true);
    }
    
    
    private void agregarEsfera(){
        canvas.repaint();
    }

    private void cargarDireccion() {
        direcciones.add("0");
        direcciones.add("45");
        direcciones.add("90");
        direcciones.add("135");
        direcciones.add("180");
        direcciones.add("225");
        direcciones.add("270");
        direcciones.add("315");
        JLabel labelDirecciones = new JLabel("Direcciones:");
	labelDirecciones.setBounds(10,5,150,100);
        comboDireccion = new JComboBox<>();
        for(String s:direcciones){
            comboDireccion.addItem(s);
        }
        comboDireccion.setBounds(90, 40, 130, 30);
        add(labelDirecciones);
        add(comboDireccion);
    }
    
    private void cargarCantidad(){
        JLabel labelCantidad = new JLabel("Cantidad:");
        cantidad = new JTextField(30);
        
        labelCantidad.setBounds(10,325,130,80);
        cantidad.setBounds(90, 350, 70, 30);
        add(labelCantidad);
        add(cantidad);
    }

    private void cargarSliders() {
         class ColorSlider implements ChangeListener{

            @Override
            public void stateChanged(ChangeEvent e) {
                cambiarColor();
            }
             
         }
         ChangeListener listener = new ColorSlider();
         
         JLabel labelRed = new JLabel("Rojo:");
         red = new JSlider(0,255,0);
         red.addChangeListener(listener);
         
         JLabel labelBlue = new JLabel("Azul:");
         blue = new JSlider(0,255,0);
         blue.addChangeListener(listener);
         
         JLabel labelGreen = new JLabel("Verde:");
         green = new JSlider(0,255,0);
         green.addChangeListener(listener);
         
         labelRed.setBounds(10,130,130,30);
         red.setBounds(90, 130, 130, 30);
         
         labelBlue.setBounds(10,150,130,80);
         blue.setBounds(90, 150, 130, 80);
         
         labelGreen.setBounds(10,200,130,80);
         green.setBounds(90, 200, 130, 80);
         
         add(labelRed);
         add(red);
         
         add(labelBlue);
         add(blue);
         
         add(labelGreen);
         add(green);
    }
    public void cambiarColor(){
        int redColor = red.getValue();
        int blueColor = blue.getValue();
        int greenColor = green.getValue();
        
        colorEsfera = new Color(redColor,greenColor,blueColor);
    }

    private void cargarVelocidades() {       
        int contador = 0;
        while(contador!=velocidadMaxima){
            velocidades.add(contador+1);
            contador++;
        }
        
        JLabel labelVelocidades = new JLabel("Velocidad:");
	labelVelocidades.setBounds(10,45,150,100);
        comboVelocidad = new JComboBox<>();
        for(int i:velocidades){
            comboVelocidad.addItem(i);
        }
        comboVelocidad.setBounds(90, 80, 130, 30);
        add(labelVelocidades);
        add(comboVelocidad);
    }

    private void cargarPatron() {
        patrones.add("Builder");
        patrones.add("Factory");
        patrones.add("Prototype");
        patrones.add("ObjectPool");
        JLabel labelPatrones = new JLabel("Patron:");
	labelPatrones.setBounds(10,255,150,100);
        comboPatron = new JComboBox<>();
        for(String s:patrones){
            comboPatron.addItem(s);
        }
        comboPatron.setBounds(90, 290, 130, 30);
        add(labelPatrones);
        add(comboPatron);
    }
    
    private ArrayList<Esfera> createEsferasBuilder(int velocidad,Color color,int direccion,int cantidad){
        Random r = new Random();
        EsferaBuilder builder = new EsferaBuilder();
        ArrayList<Esfera> newEsferas = new ArrayList<>();
        
        while(cantidad!=0){
            int x = r.nextInt((300 - 30) + 1) + 30;
            int y = r.nextInt((400 - 30) + 1) + 30;
            Vector<Integer> vector = new Vector<Integer>(2, 1);
            vector.add(0, x);
            vector.add(1, y);
            Esfera esferaNew = builder.setColor(color).setDireccion(direccion).setPosicion(vector).setVelocidad(velocidad).build();
            newEsferas.add(esferaNew);
            builder = new EsferaBuilder();
            cantidad-=1;
        }
        return newEsferas;
    }
    
    private ArrayList<Esfera> createEsferasFactory(int velocidad,Color color,int direccion,int cantidad){
        Random r = new Random();
         ArrayList<Esfera> newEsferas = new ArrayList<>();
         EsferaFactory factory =  new EsferaFactory();
         
         while(cantidad!=0){
            int x = r.nextInt((300 - 30) + 1) + 30;
            int y = r.nextInt((400 - 30) + 1) + 30;
            Vector<Integer> vector = new Vector<Integer>(2, 1);
            vector.add(0, x);
            vector.add(1, y);
            Esfera esferaNew = factory.CreateEsfera(velocidad, color, direccion, vector);
            newEsferas.add(esferaNew);
            cantidad-=1;
         }
         return newEsferas;
    }
    
    private ArrayList<Esfera> createEsferasPrototype(int velocidad,Color color,int direccion,int cantidad){
        Random r = new Random();
        ArrayList<Esfera> newEsferas = new ArrayList<>();
         while(cantidad!=0){
            int x = r.nextInt((300 - 30) + 1) + 30;
            int y = r.nextInt((400 - 30) + 1) + 30;
            Vector<Integer> vector = new Vector<Integer>(2, 1);
            vector.add(0, x);
            vector.add(1, y);
            Esfera esferaNew = (Esfera)PrototypeFactory.obtenerPrototipo("template");
            esferaNew.setColor(color);
            esferaNew.setDireccion(direccion);
            esferaNew.setPosicion(vector);
            esferaNew.setVelocidad(velocidad);
            newEsferas.add(esferaNew);
            cantidad-=1;
         }
         return newEsferas;
    }
    
    private ArrayList<Esfera> createEsferasObjectPool(int velocidad,Color color,int direccion,int cantidad){
        Random r = new Random();
         ArrayList<Esfera> newEsferas = new ArrayList<>();
         ConcreteObjectPool pool = new ConcreteObjectPool(0,10000,25);
         
         while(cantidad!=0){
            int x = r.nextInt((300 - 30) + 1) + 30;
            int y = r.nextInt((400 - 30) + 1) + 30;
            Vector<Integer> vector = new Vector<Integer>(2, 1);
            vector.add(0, x);
            vector.add(1, y);
            Esfera esferaNew = (Esfera)pool.getObject(velocidad, color, direccion, vector);
            newEsferas.add(esferaNew);
            cantidad-=1;
         }
         return newEsferas;
    }

    private void incializarPrototype() {
        Esfera esf = new Esfera(1,Color.RED,0,new Vector(0,0));
        PrototypeFactory.anadirPrototipo("template",esf);
    }

     
    private void cargarCanvas() {
      canvas = new Animacion();
      canvas.setBounds(xCanvas, yCanvas, largoCanvas, anchoCanvas);
      add(canvas);
    }
    
    private void crearEsferas(){
        if(validarCantidad()){
        }
        else{
            long start = System.nanoTime();
            String patron = (String)this.comboPatron.getSelectedItem();
            int velocidad = (Integer)this.comboVelocidad.getSelectedItem();
            int direccion = Integer.parseInt((String)this.comboDireccion.getSelectedItem());
            int cantidad = Integer.parseInt(this.cantidad.getText());
            ArrayList<Esfera> newEsferas = new ArrayList<>();
            ArrayList<Esfera> esferasExistentes = canvas.getEsferas();
        
            switch(patron){
                case "Builder":
                    newEsferas = this.createEsferasBuilder(velocidad, colorEsfera, direccion, cantidad);
                    long end = System.nanoTime();
                    mostrarEmergente("Se tomo: "+Long.toString(end-start)+"ns");
                    esferasExistentes.addAll(newEsferas);
                    canvas.setEsferas(esferasExistentes);
                    break;
                
                case "Factory":
                    newEsferas = this.createEsferasFactory(velocidad, colorEsfera, direccion, cantidad);
                    long endFact = System.nanoTime();
                    mostrarEmergente("Se tomo: "+Long.toString(endFact-start)+"ns");
                    esferasExistentes.addAll(newEsferas);
                    
                    canvas.setEsferas(esferasExistentes);
                    break;
                
                case "Prototype":
                    newEsferas = this.createEsferasPrototype(velocidad, colorEsfera, direccion, cantidad);
                    long endProt = System.nanoTime();
                    mostrarEmergente("Se tomo: "+Long.toString(endProt-start)+"ns");
                    esferasExistentes.addAll(newEsferas);
                    canvas.setEsferas(esferasExistentes);
                    break;
                
                case "ObjectPool":
                    newEsferas = this.createEsferasObjectPool(velocidad, colorEsfera, direccion, cantidad);
                    long endObj = System.nanoTime();
                    mostrarEmergente("Se tomo: "+Long.toString(endObj-start)+"ns");
                    esferasExistentes.addAll(newEsferas);
                    canvas.setEsferas(esferasExistentes);
                    break;
                
            }
        }
        
    }
    
    private boolean validarCantidad(){
        try{
            int test = Integer.parseInt(cantidad.getText());
            return false;
        }catch(NumberFormatException e){
            mostrarEmergente("Debe de tener un integer en la cantidad");
            return true;
        }
    }
    
    public void mostrarEmergente(String mensaje) {
		Emergente emergente = new Emergente(mensaje);
		emergente.mostrar();
	}
    
}
