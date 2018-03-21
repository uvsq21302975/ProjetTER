import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
 
public class Acceuil extends JPanel {
	
  public void paintComponent(Graphics g){
	  BufferedImage img;
	
    try {
      img = ImageIO.read(new File("img/gob.jpg"));
      //g.drawImage(img, (this.getWidth()-200)/2, (this.getHeight()-200)/2, this);
      //Pour une image de fond
      g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    } catch (IOException e) {
      e.printStackTrace();
    }                
  }               
}