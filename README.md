# BotonImagen - Explicacion de metodos 

# 1. Importar las librerías correspondientes
   
![image](https://github.com/user-attachments/assets/8f81b3d1-10ab-4b0b-8a3f-fdffe219c7aa)

Al tratarse de una interfaz gráfica utilizamos java.awt.* y java.awt.event.*,
javax.swing.* para crear los controles visuales que utilizara nuestro componente,
entre otras como el java.io.File para manipular archivos. 

# 2. Instanciamos los valores

![image](https://github.com/user-attachments/assets/1bca4ede-649a-457b-a105-a7ca452086cf)

labelImagen: Que mostrará la imagen que cargaremos.

imagenOriginal: se encargará de almacenar la imagen cargada 

zoomActual: el nivel de zoom actual que se aplica a la imagen.

anguloRotacion: el ángulo de rotación.

# 3. Constructor 

![image](https://github.com/user-attachments/assets/e20362e9-6e6b-421a-9ef5-cb4d1a5e11ea)

Este bloque configura el panel Visor:
Se establece un BorderLayout, donde el área central (BorderLayout.CENTER) se utiliza para mostrar la imagen.
Se crea labelImagen y se alinea al centro horizontal y verticalmente.
labelImagen se agrega al centro del Visor, ocupando la mayor parte del espacio disponible.

# 4. Inicialización y creación de los botones

![image](https://github.com/user-attachments/assets/ae05c807-5736-4b74-af5d-6c1e78b71ba4)

Se crean los botones: 
Abrir Imagen: abre un diálogo para seleccionar una imagen desde el sistema.
Restaurar: restaura la imagen a su estado original (sin zoom ni rotación).
Rotar Izquierda y Rotar Derecha: rotan la imagen en incrementos de 90 grados hacia la izquierda o derecha.
Y el slider zoom que permite ajustar el zoom entre 1% y 300%. Al cambiar el valor

# 5. Panel de botones 

![image](https://github.com/user-attachments/assets/63f91740-9673-4000-86ce-1cf678b9d1c8)

El panelBotones contiene todos los botones y el slider de zoom, y se coloca en la parte inferior.

# 6. Método abrir imagen 

![image](https://github.com/user-attachments/assets/336bd8cd-7204-4eed-8121-63de8ad10ab0)


abrirImagen() abre un JFileChooser para que el usuario seleccione una imagen.
Si el usuario selecciona un archivo, se carga la imagen y se pasa a setImagen().

# 7. Método setImagen 

![image](https://github.com/user-attachments/assets/18c8a83c-cf99-4fd3-abf9-70b667760502)

setImagen() establece imagenOriginal y muestra la imagen en labelImagen.
También restablece zoomActual y anguloRotacion a sus valores originales.

# 8. Método restaurar imagen 

![image](https://github.com/user-attachments/assets/cd40d83a-41f2-4660-b0de-ae6062f58ce4)

restaurarImagen() devuelve la imagen a su tamaño y rotación originales.

# 9. Métodos para rotar imagen 

![image](https://github.com/user-attachments/assets/933193d8-8b3e-4a32-bff0-8a455258a7b5)

los metodos rotarIzquierda() y rotarDerecha() ajustan anguloRotacion en 90 grados en cada dirección y llaman a actualizarImagen() para aplicar los cambios.

# 10. Método actualizar imagen 

![image](https://github.com/user-attachments/assets/a86ed731-0f43-434b-961f-569df084e1a3)

actualizarImagen() ajusta el tamaño y la rotación de la imagen.
Primero, se escala la imagen usando zoomActual.
Luego, llama a rotarImagen() para rotarla según anguloRotacion, y la muestra en labelImagen.

# Video del Componente 
https://youtu.be/IsbdM2QoDZw?si=Fh8PyJtlpk1OCOph


