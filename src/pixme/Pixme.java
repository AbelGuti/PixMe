/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pixme;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author abel
 */
public class Pixme {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        pixelear(100,"chepe.jpg");
    }

    public static void pixelear(int grado, String foto) throws IOException {

        //variables input
        
        File imagenSeleccionada = new File(foto);
        
        //leemos la imagen de origen
        BufferedImage imageActual;
        BufferedImage bmp = null;        
        bmp = ImageIO.read(imagenSeleccionada);
        imageActual = bmp;

        //double hipotenusa = Math.pow(Math.pow(imageActual.getHeight(), 2) + Math.pow(imageActual.getWidth(), 2), 1 / 2);
        double hipotenusa = Math.pow(Math.pow(imageActual.getHeight(), 2) + Math.pow(imageActual.getWidth(), 2), 0.5);
        int a = imageActual.getHeight();        
        int res = (int) hipotenusa / grado;
        int xCuadro = grado * imageActual.getWidth() / (int) hipotenusa;
        int yCuadro = grado * imageActual.getHeight() / (int) hipotenusa;
        int xPix = imageActual.getWidth() / xCuadro;
        int yPix = imageActual.getHeight() / yCuadro;
        int xActual = xPix;
        int yActual = yPix;
        int[][] colores = {/*Rojo*/{255, 0, 0}, {255, 126, 126}, {126, 0, 0} 
                /*Amarillo*/, {255, 255, 0}, {255, 255, 126}, {126, 126, 0} 
                /*Azul*/, {0, 0, 255}, {126, 126, 255}, {0, 0, 126}
                /*Verde*/, {0, 255, 0}, {126, 255, 126}, {0, 126, 0} 
                /*Morado*/, {255, 0, 255}, {255, 126, 255}, {126, 0, 126}
                /*Celeste*/, {0, 255, 255}, {126, 255, 255}, {0, 126, 126} 
                /*Pieles*/, {255, 126, 0}, {255, 189, 122}, {156, 78, 0} 
                /*Pieles*/, {119, 60, 0}, {127, 81, 35}, {255, 225, 194} 
                /*Otros*/, {255, 255, 255}, {0, 0, 0}, {153, 153, 153}, {70, 70, 70}
        };

        //Abrimos el file para la imagen de destino
        File fichero = new File("foto.jpg");
        String formato = "jpg";

        // Creamos la imagen para dibujar en ella.
        BufferedImage imagen = new BufferedImage(imageActual.getWidth(), imageActual.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = imagen.getGraphics();

        for (int ii = 0; ii < xCuadro; ii++) {
            for (int jj = 0; jj < yCuadro; jj++) {
                Color colorAux;
                int rojo = 0;
                int verde = 0;
                int azul = 0;
                for (int i = xPix - xActual; i < xPix; i++) {
                    for (int j = yPix - yActual; j < yPix; j++) {
                        colorAux = new Color(imageActual.getRGB(i, j));
                        rojo += colorAux.getRed();
                        verde += colorAux.getGreen();
                        azul += colorAux.getBlue();
                    }
                }
                rojo = rojo / (xActual * yActual);
                verde = verde / (xActual * yActual);
                azul = azul / (xActual * yActual);
                //Color elegido
                double menor=500;
                int rrojo=0;
                int rverde=0;
                int razul=0;
                for(int u=0;u<colores.length;u++){                
                double ans = Math.pow(Math.pow(colores[u][0] - (rojo), 2) + Math.pow(colores[u][1] - (verde), 2) + Math.pow(colores[u][2] - (azul), 2), 0.5);
                if(ans<menor){
                menor=ans;
                rrojo=colores[u][0];
                rverde=colores[u][1];
                razul=colores[u][2];
                }                
                }                
                System.out.println(rrojo+","+rverde+","+razul);
                g.setColor(new Color(rrojo, rverde, razul, 255));
                g.fillRect(xPix - xActual, yPix - yActual, xActual, yActual);
                yPix += yActual;
            }
            yPix = yActual;
            xPix += xActual;
        }
        try {
            ImageIO.write(imagen, formato, fichero);
        } catch (IOException e) {
            System.out.println("Error de escritura");
        }

    }

}
