import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Terrain extends JFrame implements ActionListener{
	
	 private Reserve reserve2 = new Reserve();
	 private Reserve reserve = new Reserve();
	 private Plateau plateau = new Plateau(reserve);
  
	  private Bouton HvsH = new Bouton("OKKK");
	  
	  private Bouton HvsIA = new Bouton("Ok");
	  
	  private JPanel container = new JPanel();
	  

	  
	public Terrain(){
		this.setTitle("GOBBLET");
	    this.setSize(1000, 500);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    
	    container.add(plateau, BorderLayout.CENTER);
	    container.add(reserve, BorderLayout.WEST);
	    container.add(reserve2, BorderLayout.EAST);
	    
	    reserve.setPreferredSize(new Dimension(150, 50));
	    reserve2.setPreferredSize(new Dimension(150, 50));
	    
	    HvsH.addActionListener(this);
	 
	    
	    JPanel south = new JPanel();
	    
	    south.add(HvsH);
	    south.add(HvsIA);
	    //container.add(south, BorderLayout.SOUTH);
	    this.setContentPane(container);
	    this.setVisible(true);    
	}
	
	public void Init_terrain() {
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		    
    }
}
