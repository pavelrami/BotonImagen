package botonimagen;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class VisorDeImagen extends JFrame {
    private JLabel labelImagen;
    private Image imagenOriginal;
    private double factorZoom = 1.2;
    private double zoomActual = 1.0;

    public VisorDeImagen() {
        setTitle("Visor de Imagen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Panel para la imagen
        JPanel panelImagen = new JPanel();
        panelImagen.setLayout(new BorderLayout());
        labelImagen = new JLabel();
        labelImagen.setHorizontalAlignment(JLabel.CENTER);
        labelImagen.setVerticalAlignment(JLabel.CENTER);
        panelImagen.add(labelImagen, BorderLayout.CENTER);

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        
        JButton botonAbrir = new JButton("Abrir Imagen");
        botonAbrir.addActionListener(e -> abrirImagen());

        JButton botonZoomIn = new JButton("Zoom +");
        botonZoomIn.addActionListener(e -> hacerZoomIn());

        JButton botonZoomOut = new JButton("Zoom -");
        botonZoomOut.addActionListener(e -> hacerZoomOut());

        JButton botonRestaurar = new JButton("Restaurar");
        botonRestaurar.addActionListener(e -> restaurarImagen());

        JButton botonRotarIzquierda = new JButton("Rotar Izquierda");
        botonRotarIzquierda.addActionListener(e -> rotarIzquierda());

        JButton botonRotarDerecha = new JButton("Rotar Derecha");
        botonRotarDerecha.addActionListener(e -> rotarDerecha());

        // Añadir botones al panel de botones
        panelBotones.add(botonAbrir);
        panelBotones.add(botonZoomIn);
        panelBotones.add(botonZoomOut);
        panelBotones.add(botonRestaurar);
        panelBotones.add(botonRotarIzquierda);
        panelBotones.add(botonRotarDerecha);

        // Añadir paneles al frame
        add(panelImagen, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void abrirImagen() {
        JFileChooser selectorArchivos = new JFileChooser();
        selectorArchivos.setDialogTitle("Selecciona una imagen");
        int resultado = selectorArchivos.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = selectorArchivos.getSelectedFile();
            try {
                imagenOriginal = ImageIO.read(archivoSeleccionado);
                zoomActual = 1.0;
                actualizarImagen();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No se pudo cargar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void hacerZoomIn() {
        zoomActual *= factorZoom;
        actualizarImagen();
    }

    private void hacerZoomOut() {
        zoomActual /= factorZoom;
        actualizarImagen();
    }

    private void restaurarImagen() {
        zoomActual = 1.0;
        actualizarImagen();
    }

    private void rotarIzquierda() {
        labelImagen.setIcon(new ImageIcon(rotarImagen(-90)));
    }

    private void rotarDerecha() {
        labelImagen.setIcon(new ImageIcon(rotarImagen(90)));
    }

    private void actualizarImagen() {
        if (imagenOriginal != null) {
            int ancho = (int) (imagenOriginal.getWidth(null) * zoomActual);
            int alto = (int) (imagenOriginal.getHeight(null) * zoomActual);
            Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            labelImagen.setIcon(new ImageIcon(imagenEscalada));
        }
    }

    private Image rotarImagen(int grados) {
        int ancho = imagenOriginal.getWidth(null);
        int alto = imagenOriginal.getHeight(null);
        BufferedImage imagenRotada = new BufferedImage(alto, ancho, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imagenRotada.createGraphics();
        g2d.rotate(Math.toRadians(grados), alto / 2, alto / 2);
        g2d.drawImage(imagenOriginal, 0, 0, null);
        g2d.dispose();
        return imagenRotada;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VisorDeImagen visor = new VisorDeImagen();
            visor.setVisible(true);
        });
    }
}
