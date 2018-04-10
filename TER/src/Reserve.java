import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

 
public class Reserve extends JPanel implements MouseListener{
	public int taille;
	public boolean joueur2 = false;
	public int select = 0;
	private Case r1 = new Case();
	private Case r2 = new Case();
	private Case r3 = new Case();
	public int reste1 = Reste_reserve(r1);
	public int reste2 = Reste_reserve(r2);
	public int reste3 = Reste_reserve(r3);
	
	 public Reserve(){
		  this.addMouseListener(this);  
	  }
	 public Reserve(int a){
		 joueur2 = true;
		  this.addMouseListener(this);  
	  }
	public void drawCenteredCircle(Graphics g, int x, int y, int r) {
	  x = x-(r/2);
	  y = y-(r/2);
	  g.fillOval(x,y,r,r);
	}
	
  public void paintComponent(Graphics g){
	  g.setColor(Color.red);
	  int uniteX = getWidth()/4;
	  int uniteY = uniteX;
	  for(int i=0; i<3; i++){
		 g.drawRect(uniteX, uniteY, getWidth()/2, getWidth()/2);
		 uniteY += getHeight()/3;
	  }
	  Affiche_pion(g);
  }
  
  public void Affiche_pion(Graphics g) {
	  if(joueur2) g.setColor(Color.yellow);
	  else g.setColor(Color.blue);
	  Point p1 = CoordonnerClik(1);
	  Point c = new Point();
	  c.x = p1.x + getWidth()/4;
	  int rayon=getWidth()/2;
	  int diminution = 20;
	  int i;
	  for(i=4;i>0;i--) {
		  if(reste1 == i) {
			  drawCenteredCircle(g,c.x,c.x,rayon);
			  break;
		  }
		  rayon -= diminution;
	  }
	  rayon = getWidth()/2;
	  c.y = c.x + getHeight()/3;
	  for(i=4;i>0;i--) {
		  if(reste2 == i) {
			  drawCenteredCircle(g,c.x,c.y,rayon);
			  break;
		  }
		  rayon -= diminution;
	  }
	  rayon = getWidth()/2;
	  c.y = c.x + 2 * getHeight()/3;
	  for(i=4;i>0;i--) {
		  if(reste3 == i) {
			  drawCenteredCircle(g,c.x,c.y,rayon);
			  break;
		  }
		  rayon -= diminution;
	  }
  }
  
  public int WhereIsClik(MouseEvent e) {
	  if(e.getX() > getWidth()/4 && e.getX() < getWidth()/2 + getWidth()/4 ) {
		  if(e.getY() > getWidth()/4 && e.getY() < getWidth()/2 + getWidth()/4 ) {
			  if(reste1 > 0) {
				  return 1;
			  }
		  }
		  else if(e.getY() > getWidth()/4 + getHeight()/3 && e.getY() < getWidth()/2 + getWidth()/4 + getHeight()/3 ) {
			  if(reste2 > 0) {
				  return 2;
			  }
		  }
		  else if(e.getY() > getWidth()/4 + 2*getHeight()/3 && e.getY() < getWidth()/2 + getWidth()/4 + 2*getHeight()/3 ) {
			  if(reste3 > 0) {
				  return 3;
			  }
		  }
	  }
	  return 0;
  }
  public int Reste_reserve(Case r) {
	  if(r.mega == 1) {
		  taille = 4;
		  return 4;
	  }
	  else if(r.grand == 1) {
		  return 3;
	  }
	  else if(r.moyen == 1) {
		  return 2;
	  }
	  else if(r.petit == 1) {
		  return 1;
	  }
	  else {
		  return 0;
	  }
  }
  
  public Point CoordonnerClik(int point) {
	  Point p = new Point();
	  p.x = getWidth()/4;
	  if(point == 1) {
	  	p.y = getWidth()/4;
	  }
	  else if(point == 2) {
		p.y = getHeight()/3 + getWidth()/4;
	  }
	  else if(point == 3) {
		p.y = 2 * getHeight()/3 + getWidth()/4;
	  }
	  return p;
  }
  
  public void init_reserve() {
	  r1.mega = 1;
	  r1.grand = 1;
	  r1.moyen = 1;
	  r1.petit = 1;
	  
	  r2.mega = 1;
	  r2.grand = 1;
	  r2.moyen = 1;
	  r2.petit = 1;
	  
	  r3.mega = 1;
	  r3.grand = 1;
	  r3.moyen = 1;
	  r3.petit = 1;
  }
  
  public void Selectionner(MouseEvent e) {
	  Graphics g = getGraphics(); 
	  int r = WhereIsClik(e);
	  Point p = CoordonnerClik(r);
	  if(select == 1) {
		  effacer(1);
		  dessine_rectangle(1);
	  }
	  if(select == 2) {
		  effacer(2);
		  dessine_rectangle(2);
	  }
	  if(select == 3) {
		  effacer(3); 
		  dessine_rectangle(3);
	  }
	  if(r != 0 ){
		  effacer(r);
		  g.setColor(Color.green);
		  g.drawRect(p.x, p.y, getWidth()/2, getWidth()/2);
		  select = r;
	  }
	  else select = 0;
	  
	  Affiche_pion(g);
  }
  public void effacer(int r) {
	  Graphics g = getGraphics(); 
	  Point p = CoordonnerClik(r);
	  g.setColor(Color.white); 
	  g.fillRect(p.x, p.y, getWidth()/2, getWidth()/2);
  }
  
  public void dessine_rectangle(int r) {
	  Graphics g = getGraphics(); 
	  Point p = CoordonnerClik(r);
	  g.setColor(Color.red); 
	  g.drawRect(p.x, p.y, getWidth()/2, getWidth()/2);
  }
	  
  //Méthode appelée lors du clic de souris
  public void mouseClicked(MouseEvent e) { 
	  Selectionner(e);
  }

  //Méthode appelée lors du survol de la souris
  public void mouseEntered(MouseEvent e) { }
  //Méthode appelée lorsque la souris sort de la zone du bouton
  public void mouseExited(MouseEvent event) { }

  //Méthode appelée lorsque l'on presse le bouton gauche de la souris
  public void mousePressed(MouseEvent event) { }

  //Méthode appelée lorsque l'on relâche le clic de souris
  public void mouseReleased(MouseEvent event) { } 
  
}