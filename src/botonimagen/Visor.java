/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package botonimagen;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;

public class Visor extends JPanel {
    private JLabel labelImagen;
    private ImageIcon imagenOriginal;
    private double zoomActual = 1.0;
    private int anguloRotacion = 0;

    // Constructor
    public Visor() {
        setLayout(new BorderLayout());
        labelImagen = new JLabel();
        labelImagen.setHorizontalAlignment(JLabel.CENTER);
        labelImagen.setVerticalAlignment(JLabel.CENTER);
        add(labelImagen, BorderLayout.CENTER);

        // Crear botones
        JButton botonAbrir = crearBotonConIcono("", "/resources/imagen.png", 24, 24);
        botonAbrir.addActionListener(e -> abrirImagen());

        JButton botonRestaurar = crearBotonConIcono("", "/resources/restaurar.png", 24, 24);
        botonRestaurar.addActionListener(e -> restaurarImagen());

        JButton botonRotarIzquierda = crearBotonConIcono("", "/resources/izquierda.png", 24, 24);
        botonRotarIzquierda.addActionListener(e -> rotarIzquierda());

        JButton botonRotarDerecha = crearBotonConIcono("", "/resources/Rderecha.png", 24, 24);
        botonRotarDerecha.addActionListener(e -> rotarDerecha());

        // Crear slider de zoom
        JSlider sliderZoom = new JSlider(1, 300, 100); // Rango de 1% a 300% de zoom
        sliderZoom.addChangeListener(e -> {
            zoomActual = sliderZoom.getValue() / 100.0; // Convertir a porcentaje
            actualizarImagen();
        });

        // Panel para los botones y slider
        JPanel panelBotones = new JPanel();
        panelBotones.add(botonAbrir);
        panelBotones.add(sliderZoom);
        panelBotones.add(botonRotarIzquierda);
        panelBotones.add(botonRotarDerecha);
        panelBotones.add(botonRestaurar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    // Método para abrir y cargar la imagen
    private void abrirImagen() {
        JFileChooser selectorArchivos = new JFileChooser();
        selectorArchivos.setDialogTitle("Selecciona una imagen");
        int resultado = selectorArchivos.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = selectorArchivos.getSelectedFile();
            try {
                ImageIcon icono = new ImageIcon(ImageIO.read(archivoSeleccionado));
                setImagen(icono);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No se pudo cargar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para establecer la imagen en el JLabel
    public void setImagen(ImageIcon icono) {
        this.imagenOriginal = icono;
        labelImagen.setIcon(icono);
        this.zoomActual = 1.0;
        this.anguloRotacion = 0;
    }

    // Método para restaurar la imagen original
    private void restaurarImagen() {
        zoomActual = 1.0;
        anguloRotacion = 0;
        labelImagen.setIcon(imagenOriginal);
    }

    // Método para rotar la imagen hacia la izquierda
    private void rotarIzquierda() {
        anguloRotacion -= 90;
        actualizarImagen();
    }

    // Método para rotar la imagen hacia la derecha
    private void rotarDerecha() {
        anguloRotacion += 90;
        actualizarImagen();
    }

    // Método para actualizar la imagen con el zoom y la rotación aplicados
    private void actualizarImagen() {
        if (imagenOriginal != null) {
            int ancho = (int) (imagenOriginal.getIconWidth() * zoomActual);
            int alto = (int) (imagenOriginal.getIconHeight() * zoomActual);
            Image imagenEscalada = imagenOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

            // Crear imagen rotada usando AffineTransform
            Image imagenTransformada = rotarImagen(imagenEscalada, anguloRotacion);
            labelImagen.setIcon(new ImageIcon(imagenTransformada));
        }
    }

    // Método auxiliar para rotar la imagen
    private Image rotarImagen(Image imagen, int angulo) {
        int ancho = imagen.getWidth(null);
        int alto = imagen.getHeight(null);

        BufferedImage imagenRotada = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imagenRotada.createGraphics();
        g2d.rotate(Math.toRadians(angulo), ancho / 2, alto / 2);
        g2d.drawImage(imagen, 0, 0, null);
        g2d.dispose();
        return imagenRotada;
    }

    // Método para crear un botón con un icono
    private JButton crearBotonConIcono(String texto, String rutaIcono, int ancho, int alto) {
        JButton boton = new JButton(texto);
        java.net.URL url = getClass().getResource(rutaIcono);
        if (url != null) {
            ImageIcon icono = new ImageIcon(url);
            boton.setIcon(new ImageIcon(icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH)));
        } else {
            System.out.println("No se encontró el icono: " + rutaIcono);
        }
        return boton;
    }
}


