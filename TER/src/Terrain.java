import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Terrain extends JFrame implements ActionListener{
	
	private JLabel label = new JLabel("Joueur1");
	
	 private Reserve reserve2 = new Reserve(4);
	 private Reserve reserve = new Reserve();
	 private Plateau plateau = new Plateau(label,reserve,reserve2);
	 private Plateau plateau_ordi = new Plateau(label,reserve,reserve2,4);
	
	 private boolean ordi = false;
	 int pronf = 3;
	  private Bouton rejouer = new Bouton("Rejouer");
	  private Bouton Home = new Bouton("Accueil");
	  private JButton Jouerordi = new JButton("MinMax");
	  
	  private JPanel container = new JPanel();
	  
	  
	public Terrain(int x) {
		this.setTitle("GOBBLET");
	    this.setSize(1100, 600);
	    //this.setResizable(false);
	   // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    ordi = true;
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
	    
	    container.add(plateau_ordi, BorderLayout.CENTER);
	    container.add(reserve, BorderLayout.WEST);
	    container.add(reserve2, BorderLayout.EAST);
	    container.add(label, BorderLayout.NORTH);
	    
	    reserve.setPreferredSize(new Dimension(150, 50));
	    reserve2.setPreferredSize(new Dimension(150, 50));
	    
	    rejouer.setPreferredSize(new Dimension(150, 50));
	    Home.setPreferredSize(new Dimension(150, 50));
	    Jouerordi.setPreferredSize(new Dimension(150, 50));
	    rejouer.addActionListener(new rejouer());
	    Home.addActionListener(new Home());
	    
	    Jouerordi.setBackground(Color.yellow);
	    Jouerordi.setContentAreaFilled(false);
        Jouerordi.setOpaque(true);
        
	    Jouerordi.addActionListener(new JouerOrdi());
	    
	    JPanel south = new JPanel();
	    south.add(rejouer);
	    south.add(Home);
	    south.add(Jouerordi);
	    container.add(south, BorderLayout.SOUTH);
	    
	    this.setContentPane(container);
	    
	    this.setVisible(true);
	}
	
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
		if(ordi) {
			Terrain T = new Terrain(5);
		}
		else {
			Terrain T = new Terrain();
		}

	}
	public void fermer() {
		this.dispose();
		Fenetre f = new Fenetre();
	}
	
	public void JouerAI() {
		
		if(!plateau_ordi.fin_du_jeu()) {
			if(!plateau_ordi.joueur1) {
				if(plateau_ordi.nbr_pion() > 5) pronf = 4;
				plateau_ordi.Deplace_pion_AI(plateau_ordi.c, pronf, reserve, reserve2);
				reserve2.Refreash_pion();
			}
		}
		if(plateau_ordi.fin_du_jeu()) {
			  label.setForeground(Color.black);
			  if(plateau_ordi.victoire == 1) label.setText("BRAVO JOUEUR 1");
			  else {
				 label.setText("L'ORDI GAGNE LA PARTIE");
			  }
			  plateau_ordi.fin = true;
		  }
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
		
		//Classe écoutant notre troisieme bouton
		class JouerOrdi implements ActionListener{
		  //Redéfinition de la méthode actionPerformed()
		  public void actionPerformed(ActionEvent e) {
			   JouerAI();
		  }
		}

}
