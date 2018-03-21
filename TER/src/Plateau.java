import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

 
public class Plateau extends JPanel implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Reserve r = new Reserve();
	public int selectP = -1; 
	
	public Plateau(Reserve r1) {
		this.r = r1;
		this.addMouseListener(this); 
	}
	
	Case[][] c = new Case[4][4];
	
  public void paintComponent(Graphics g){
	  
	  g.setColor(Color.black);
	  int uniteX = getWidth()/4;
	  int uniteY = getHeight()/4;
	  for(int i=0; i<5; i++){
		g.drawLine(uniteX*i, 0, uniteX*i, getHeight());
	  	g.drawLine(0, uniteY*i, getWidth(), uniteY*i);
	  }
	  init_Case_plateau();
	  Dessine_pion(g);
  } 
  
  public boolean Case_vide(Point p) {
	  if(c[p.x][p.y].mega == 1) {
		  return false;
	  }
	  if(c[p.x][p.y].grand == 1) {
		  return false;
	  }
	  if(c[p.x][p.y].moyen == 1) {
		  return false;
	  }
	  if(c[p.x][p.y].petit == 1) {
		  return false;
	  }
	  return true;
  }
  
  public void init_Case_plateau() {
	  int i,j;
	  for(i=0;i<4;i++) {
		  for(j=0;j<4;j++) {
			  c[i][j] = new Case();
			  c[i][j].petit = 0;
			  c[i][j].moyen = 0;
			  c[i][j].grand = 0;
			  c[i][j].mega = 0;
		  }
	  }
  }
  public Point CoordonnerClik(int c) {
	  Point p = new Point();
	  int j = c / 4;
	  int i = c % 4;
	  p.x = i * getWidth()/4;
	  p.y = j * getHeight()/4;
	  return p;
  }
  
  public int Point_case(Case c) {
	  if(c.mega == 1) {
		  return 4;
	  }
	  else if(c.grand == 1) {
		  return 3;
	  }
	  else if(c.moyen == 1) {
		  return 2;
	  }
	  else if(c.petit == 1) {
		  return 1;
	  }
	  else {
		  return 0;
	  }
  }
  
  public void drawCenteredCircle(Graphics g, int x, int y, int r) {
	  x = x-(r/2);
	  y = y-(r/2);
	  g.fillOval(x,y,r,r);
	}
  
  public void Ajout_pion(int reste, Point matrice) {
	  if(reste == 4) { c[matrice.x][matrice.y].mega = 1; }
	  if(reste == 3) { c[matrice.x][matrice.y].grand = 1; }
	  if(reste == 2) { c[matrice.x][matrice.y].moyen = 1; }
	  if(reste == 1) { c[matrice.x][matrice.y].petit = 1; }
  }
  
  public void effacer(int r) {
	  Graphics g = getGraphics(); 
	  Point p = CoordonnerClik(r);
	  g.setColor(Color.black); 
	  g.drawRect(p.x, p.y, getWidth()/4, getHeight()/4);
  }
  
  public void dessiner(int r) {
	  Graphics g = getGraphics(); 
	  Point p = CoordonnerClik(r);
	  g.setColor(Color.green); 
	  g.drawRect(p.x, p.y, getWidth()/4, getHeight()/4);
  }
  
  public void Selectionner(MouseEvent e) { 
	  int where = WhereIsClik(e);
	  Point matrice = matrice_case(where);
	  if(selectP >= 0) {
		  effacer(selectP);
	  }
	  if(!Case_vide(matrice) && r.select == 0) {
		  dessiner(where);
		  selectP = where;
	  }
	  else {
		  effacer(selectP);
		  selectP = -1;
	  }
  }
  
  public void Dessine_pion(Graphics g) {
	  g.setColor(Color.blue);
	  Point centre = new Point();
	  centre.x = getWidth()/8;
	  centre.y = getHeight()/8;
	  int rayon=getWidth()/8;
	  int diminution = 20;
	  int ValeurCase = 0;
	  int i,j,k;
	  for(i=0;i<4;i++) {
		  for(j=0;j<4;j++) {
			  ValeurCase = Point_case(c[i][j]);
			  for(k=4;k>0;k--) {
				if(ValeurCase == k) {
				  drawCenteredCircle(g,centre.x,centre.y,rayon);
				  break;
			  	}
				rayon -= diminution;
			  }
			  rayon = getWidth()/8;
			  centre.x += getWidth()/4;
		  }
		  centre.x = getWidth()/8;
		  centre.y += getHeight()/4;
	  }
  }
  
  public void Deplace_pion(MouseEvent e) {
	  Graphics g = getGraphics(); 
	  int where = WhereIsClik(e);
	  Point matrice = matrice_case(where);
	  if(Case_vide(matrice)) {
		  if(r.select == 1) {
			  r.effacer(1);
			  r.dessine_rectangle(1);
			  Ajout_pion(r.reste1,matrice);
			  r.reste1--;
		  }
		  if(r.select == 2) {
			  r.effacer(2);
			  r.dessine_rectangle(2);
			  Ajout_pion(r.reste2,matrice);
			  r.reste2--;
		  }
		  if(r.select == 3) {
			  r.effacer(3);
			  r.dessine_rectangle(3);
			  Ajout_pion(r.reste3,matrice);
			  r.reste3--;
		  }
	  }
	  if(r.select == 0 ){
		  Selectionner(e);
	  }
	  else r.dessine_rectangle(r.select);
	  
	  r.select = 0;
	  Dessine_pion(g);
	  //r.Affiche_pion(g);
  }
  
  public Point matrice_case(int num) {
	  Point p = new Point();;
	  p.x = num / 4;
	  p.y = num % 4;
	  return p; 
  }
  
  public int WhereIsClik(MouseEvent e) {
	  int i;
	  int Hmax = getHeight()/4;
	  int Hmin = 0;
	  int Wmax = getWidth()/4;
	  int Wmin = 0;
	  for(i=0;i<16;i++) {
		  if(e.getX() < Wmax && e.getX() > Wmin && e.getY() < Hmax && e.getY() > Hmin) {
			  return i;
		  }
		  Wmax += getWidth()/4;
		  Wmin += getWidth()/4;
		  if(Wmin == getWidth()) {
			  Hmax += getHeight()/4;
			  Hmin += getHeight()/4;
			  Wmin = 0;
			  Wmax = getWidth()/4;
		  }
	  }
	  return 0;
  }
  
//Méthode appelée lors du clic de souris
  public void mouseClicked(MouseEvent e) { 
	  Deplace_pion(e);
  }

  //Méthode appelée lors du survol de la souris
  public void mouseEntered(MouseEvent e) { }
  //Méthode appelée lorsque la souris sort de la zone du bouton
  public void mouseExited(MouseEvent event) { 
	  effacer(selectP);
	  selectP = -1;
  }

  //Méthode appelée lorsque l'on presse le bouton gauche de la souris
  public void mousePressed(MouseEvent event) { }

  //Méthode appelée lorsque l'on relâche le clic de souris
  public void mouseReleased(MouseEvent event) { } 
}