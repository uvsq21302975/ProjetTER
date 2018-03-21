import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fenetre extends JFrame implements ActionListener{
	
  private Acceuil pan = new Acceuil();
  
  private Bouton HvsH = new Bouton("1 VS 1");
  
  private Bouton HvsIA = new Bouton("1 VS IA");
  
  private JPanel container = new JPanel();
  
  private JLabel label = new JLabel("Le JLabel");

  
  public Fenetre(){
    this.setTitle("GOBBLET");
    this.setSize(900, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    container.setBackground(Color.white);
    container.setLayout(new BorderLayout());
    container.add(pan, BorderLayout.CENTER);
    
    HvsH.setPreferredSize(new Dimension(150, 50));
    HvsIA.setPreferredSize(new Dimension(150, 50));
    
    HvsH.addActionListener(new HvsHListener());
    HvsIA.addActionListener(new HvsIAListener());
    
    JPanel south = new JPanel();
    
    south.add(HvsH);
    south.add(HvsIA);
    container.add(south, BorderLayout.SOUTH);
    container.add(label, BorderLayout.NORTH);
    this.setContentPane(container);
    this.setVisible(true);    
  }
  
  //Méthode qui sera appelée lors d'un clic sur le HvsH
  public void actionPerformed(ActionEvent arg0) {
	    
	  }  

	//Classe écoutant notre premier bouton
	class HvsHListener implements ActionListener{
	  //Redéfinition de la méthode actionPerformed()
	  public void actionPerformed(ActionEvent arg0) {
	    Terrain T = new Terrain();        
	  }
	}
	    
	//Classe écoutant notre second bouton
	class HvsIAListener implements ActionListener{
	  //Redéfinition de la méthode actionPerformed()
	  public void actionPerformed(ActionEvent e) {
	    label.setText("Vous avez cliqué sur le bouton 2");    
	  }
	}

}