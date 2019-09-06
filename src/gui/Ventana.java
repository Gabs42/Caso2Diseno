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
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 *
 * @author Gabriel
 */
public class Ventana extends JFrame{
    JButton botonCrear;
    JButton botonNuevo;
    JPanel contenedor;
    JLabel labelContenedor;
    
    JComboBox<String> comboDireccion;
    JComboBox<Integer> comboVelocidad;
    JComboBox<String> comboPatron;
    JSlider red,green,blue;
    
    Color colorEsfera;
    
    private int pintar = 0;
    private ArrayList<String> direcciones = new ArrayList<>();
    private ArrayList<Integer> velocidades = new ArrayList<>();
    private ArrayList<String> patrones = new ArrayList<>();
    private int velocidadMinima = 1;
    private int velocidadMaxima = 10;
     
    public Ventana(){
        cargarVentana();
	cargarBotones();
	//cargarMostrador();
        cargarDireccion();
        cargarSliders();
        cargarVelocidades();
        cargarPatron();
        incializarPrototype();
	mostrarVentana();
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
                
                agregarEsfera();
	}          
    	});
	add(botonNuevo);

    }
    
    private void cargarMostrador() {
	contenedor = new JPanel();
	contenedor.setBackground(Color.white);
	labelContenedor = new JLabel("");
	contenedor.add(labelContenedor);
        add(contenedor);
    }
    
    private void mostrarVentana() {
        setLayout(null);
	setVisible(true);
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        if(pintar == 0){
            
        }
        else{
           g.setColor(colorEsfera);
           g.fillOval(450,240,10,10); 
        }
        
    }
    
    private void agregarEsfera(){
        pintar = 1;
        repaint();
    }

    private void cargarDireccion() {
        direcciones.add("0º");
        direcciones.add("45º");
        direcciones.add("90º");
        direcciones.add("135º");
        direcciones.add("180º");
        direcciones.add("225º");
        direcciones.add("270º");
        direcciones.add("315º");
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
        patrones.add("ObjecPool");
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
    
    private ArrayList<Esfera> createEsferasBuilder(int velocidad,Color color,int direccion,Vector posicion,int cantidad){
        EsferaBuilder builder = new EsferaBuilder();
        ArrayList<Esfera> newEsferas = new ArrayList<>();
        
        while(cantidad!=0){
            Esfera esferaNew = builder.setColor(color).setDireccion(direccion).setColor(color).setVelocidad(velocidad).build();
            newEsferas.add(esferaNew);
            builder = new EsferaBuilder();
            cantidad-=1;
        }
        return newEsferas;
    }
    
    private ArrayList<Esfera> createEsferasFactory(int velocidad,Color color,int direccion,Vector posicion,int cantidad){
         ArrayList<Esfera> newEsferas = new ArrayList<>();
         EsferaFactory factory =  new EsferaFactory();
         
         while(cantidad!=0){
             Esfera esferaNew = factory.CreateEsfera(velocidad, color, direccion, posicion);
             newEsferas.add(esferaNew);
             cantidad-=1;
         }
         return newEsferas;
    }
    
    private ArrayList<Esfera> createEsferasPrototype(int velocidad,Color color,int direccion,Vector posicion,int cantidad){
         ArrayList<Esfera> newEsferas = new ArrayList<>();
         
         while(cantidad!=0){
             Esfera esferaNew = (Esfera)PrototypeFactory.obtenerPrototipo("template");
             esferaNew.setColor(color);
             esferaNew.setDireccion(direccion);
             esferaNew.setPosicion(posicion);
             esferaNew.setVelocidad(velocidad);
             newEsferas.add(esferaNew);
             cantidad-=1;
         }
         return newEsferas;
    }
    
    private ArrayList<Esfera> createEsferasObjectPool(int velocidad,Color color,int direccion,Vector posicion,int cantidad){
         ArrayList<Esfera> newEsferas = new ArrayList<>();
         ConcreteObjectPool pool = new ConcreteObjectPool(0,10000,25);
         
         while(cantidad!=0){
             Esfera esferaNew = (Esfera)pool.getObject(velocidad, color, direccion, posicion);
             newEsferas.add(esferaNew);
             cantidad-=1;
         }
         return newEsferas;
    }

    private void incializarPrototype() {
        Esfera esf = new Esfera(0,Color.RED,0,new Vector(0,0));
        PrototypeFactory.anadirPrototipo("template",esf);
    }

     
}
