import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Terrain extends JFrame implements ActionListener{
	
	private JLabel label = new JLabel("Joueur1");
	
	 private Reserve reserve2 = new Reserve(4);
	 private Reserve reserve = new Reserve();
	 private Plateau plateau = new Plateau(label,reserve,reserve2);
  
	  private Bouton rejouer = new Bouton("Rejouer");
	  
	  private Bouton Home = new Bouton("Acceuil");
	  
	  private JPanel container = new JPanel();
	  
	  
	  
	public Terrain(){
		this.setTitle("GOBBLET");
	    this.setSize(1100, 600);
	   // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    
	    //Définition d'une police d'écriture
	    Font police = new Font("Tahoma", Font.BOLD, 25);
	    //On l'applique au JLabel
	    label.setFont(police);
	    //Changement de la couleur du texte
	    label.setForeground(Color.blue);
	    //On modifie l'alignement du texte grâce aux attributs statiques
	    //de la classe JLabel
	    label.setHorizontalAlignment(JLabel.CENTER);
	    
	    container.add(plateau, BorderLayout.CENTER);
	    container.add(reserve, BorderLayout.WEST);
	    container.add(reserve2, BorderLayout.EAST);
	    container.add(label, BorderLayout.NORTH);
	    
	    reserve.setPreferredSize(new Dimension(150, 50));
	    reserve2.setPreferredSize(new Dimension(150, 50));
	    
	    rejouer.setPreferredSize(new Dimension(150, 50));
	    Home.setPreferredSize(new Dimension(150, 50));
	    rejouer.addActionListener(new rejouer());
	    Home.addActionListener(new Home());
	 
	    
	    JPanel south = new JPanel();
	    south.add(rejouer);
	    south.add(Home);
	    container.add(south, BorderLayout.SOUTH);
	    
	    this.setContentPane(container);
	    
	    this.setVisible(true);
	    
	}
	
	public void Init_terrain() {
		this.dispose();
		Terrain T = new Terrain();

	}
	public void fermer() {
		this.dispose();
		Fenetre f = new Fenetre();
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	class rejouer implements ActionListener{
		  //Redéfinition de la méthode actionPerformed()
		  public void actionPerformed(ActionEvent arg0) {
			  Init_terrain();    
		  }
		}
		    
		//Classe écoutant notre second bouton
		class Home implements ActionListener{
		  //Redéfinition de la méthode actionPerformed()
		  public void actionPerformed(ActionEvent e) {
			   fermer();
		  }
		}
    
}
