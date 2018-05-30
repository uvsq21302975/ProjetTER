import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

 
public class Plateau extends JPanel implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JLabel label = new JLabel("");
	boolean fin = false;
	boolean ordinateur = false;
	boolean joueur1 = true;
	int victoire = 0;
	Reserve r = new Reserve();
	Reserve r2 = new Reserve(4);
	public int selectP = -1; 
	public Case[][] c = new Case[4][4];
	Ordinateur ordi = new Ordinateur();
	
	public Plateau(JLabel lab,Reserve r1,Reserve r2) {
		this.label = lab;
		this.r = r1;
		this.r2 = r2;
		this.addMouseListener(this); 
	}
	public Plateau(JLabel lab,Reserve r1,Reserve r2,int x) {
		this.addMouseListener(this);
		this.label = lab;
		this.r = r1;
		this.r2 = r2;
		ordinateur = true;
		
	}
	
	
	
  public void paintComponent(Graphics g){
	  
	  g.setColor(Color.black);
	  int uniteX = getWidth()/4;
	  int uniteY = getHeight()/4;
	  for(int i=0; i<5; i++){
		  if(i==4)g.drawLine(uniteX*i-1, 0, uniteX*i-1, getHeight());
		g.drawLine(uniteX*i, 0, uniteX*i, getHeight());
	  	g.drawLine(0, uniteY*i, getWidth(), uniteY*i);
	  }
	  init_Case_plateau();
	  Dessine_pion(g);
  } 
  
  public boolean Case_vide(Point p) {
	  if(c[p.x][p.y].mega >= 1) {
		  return false;
	  }
	  if(c[p.x][p.y].grand >= 1) {
		  return false;
	  }
	  if(c[p.x][p.y].moyen >= 1) {
		  return false;
	  }
	  if(c[p.x][p.y].petit >= 1) {
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
  public void init_Case_plateau(Case c[][]) {
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
	  if(c.mega == 2) {
		  return 40;
	  }
	  else if(c.mega == 1) {
		  return 4;
	  }
	  else if(c.grand == 2) {
		  return 30;
	  }
	  else if(c.grand == 1) {
		  return 3;
	  }
	  else if(c.moyen == 2) {
		  return 20;
	  }
	  else if(c.moyen == 1) {
		  return 2;
	  }
	  else if(c.petit == 2) {
		  return 10;
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
	  int cercle = 40;
	  g.fillOval(x,y,r,r);
	  g.setColor(Color.white); 
	  r=r-cercle;
	  x = x+(cercle/2);
	  y = y+(cercle/2);
	  g.fillOval(x, y, r, r);
	 
	}
  
  public void Ajout_pion(int reste, Point matrice,boolean joueur) {
	  if(joueur) {
		if(reste == 4) { c[matrice.x][matrice.y].mega = 1; }
	  	if(reste == 3) { c[matrice.x][matrice.y].grand = 1; }
	  	if(reste == 2) { c[matrice.x][matrice.y].moyen = 1; }
	  	if(reste == 1) { c[matrice.x][matrice.y].petit = 1; }
	  }
	  else {
		  if(reste == 4) { c[matrice.x][matrice.y].mega = 2; }
		  if(reste == 3) { c[matrice.x][matrice.y].grand = 2; }
		  if(reste == 2) { c[matrice.x][matrice.y].moyen = 2; }
		  if(reste == 1) { c[matrice.x][matrice.y].petit = 2; }
	  }
  }
  
  public void Retire_pion(int reste, Point matrice) {
		if(reste == 4) { c[matrice.x][matrice.y].mega = 0; }
	  	if(reste == 3) { c[matrice.x][matrice.y].grand = 0; }
	  	if(reste == 2) { c[matrice.x][matrice.y].moyen = 0; }
	  	if(reste == 1) { c[matrice.x][matrice.y].petit = 0; }
  }
  public void effacer(int r) {
	  Graphics g = getGraphics(); 
	  Point p = CoordonnerClik(r);
	  g.setColor(Color.black); 
	  g.drawRect(p.x, p.y, getWidth()/4, getHeight()/4);
	  selectP = -1;
  }
  
  public void dessiner(int r) {
	  Graphics g = getGraphics(); 
	  Point p = CoordonnerClik(r);
	  g.setColor(Color.green); 
	  g.drawRect(p.x, p.y, getWidth()/4, getHeight()/4);
	  selectP = r;
  }
  
  public boolean LastPionCaseJoueur1(Point matrice) {
	  int joueur = Point_case(c[matrice.x][matrice.y]);
	  if(joueur > 4) return false;
	  else return true;
  }
  public int InitTaille(int t) {
	  if(t>4) return t/10;
	  return t;
  }
  
  public void DeplaceCaseVersCase(Point PNouv) {
	  Point PAnc = matrice_case(selectP); 
	  int tailleAnc = Point_case(c[PAnc.x][PAnc.y]);
	  tailleAnc = InitTaille(tailleAnc);
	  int tailleNouv = Point_case(c[PNouv.x][PNouv.y]);
	  tailleNouv = InitTaille(tailleNouv);
	  if(tailleAnc > tailleNouv) {
		  Retire_pion(tailleAnc,PAnc);
		  Efface_pion(selectP);
		  Ajout_pion(tailleAnc,PNouv,joueur1);
		  joueur_suivant();
		  effacer(selectP);
	  }
  }
  
  public void Selectionner(MouseEvent e, Reserve r) { 
	  int where = WhereIsClik(e);
	  Point matrice = matrice_case(where);
	  boolean J = LastPionCaseJoueur1(matrice);
	  if(selectP >= 0) {
		  effacer(selectP);
	  }
	  if(!Case_vide(matrice) && r.select == 0 && joueur1 == J) {
		  dessiner(where);
	  }
	  else {
		  effacer(selectP);
	  }
  }
  
  public void Efface_pion(int r) {
	  int rayon = (getWidth()/8 - 20*4 + 20*4)+2;
	  Graphics g = getGraphics(); 
	  Point p = CoordonnerClik(r);
	  p.x += getWidth()/8;
	  p.y += getHeight()/8;
	  g.setColor(Color.white); 
	  drawCenteredCircle(g,p.x,p.y,rayon);
  }
  
  public void Dessine_pion(Graphics g) {
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
				if(ValeurCase == k || ValeurCase == k*10) {
					if(ValeurCase > 4) {
						g.setColor(Color.yellow);
					}
					else {
						g.setColor(Color.blue);
					}
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
  
  public void Affiche_console_plateu(Case c[][]) {
	  int i,j;
	  for(i=0;i<4;i++) {
		  for(j=0;j<4;j++) {
			  System.out.print(Point_case(c[i][j]));
			  System.out.print("|");
		  }
		  System.out.println();
	  }
	  System.out.println("*****");
  }
  
  public boolean fin_du_jeu() {
	  int i,j,k ,ligne=0,colonne=0, diago=0, diago2=0;
	  Point p = new Point(0,0);
	  boolean joueur = true;
	  
	  for(k=0;k<2;k++) {
		  if(joueur) victoire = 1;
		  else victoire = 2;
		  for(i=0;i<4;i++) {
			  p.x=i;
			  for(j=0;j<4;j++) {
				  p.y=j;
				  if(!Case_vide(p)) {
					  if(LastPionCaseJoueur1(p) == joueur)  ligne++;
					  if(i == j) {
						  if(LastPionCaseJoueur1(p) == joueur)  diago++;
					  }
					  if(i+j == 3) {
						  if(LastPionCaseJoueur1(p) == joueur)  diago2++;
					  }
				  }
			  }
			  if(ligne == 4) return true;
			  ligne = 0;
		  }
		  if(diago == 4 || diago2 == 4) return true;
		  diago = 0;
		  diago2 = 0;
		  for(i=0;i<4;i++) {
			  p.y=i;
			  for(j=0;j<4;j++) {
				  p.x=j;
				  if(!Case_vide(p)) {
					  if(LastPionCaseJoueur1(p) == joueur)  colonne++;
				  }
			  }
			  if(colonne == 4) return true;
			  colonne = 0;
		  }
		  joueur = false;
  	}
	  return false;
  }
  
  public void joueur_suivant() {
	  if(joueur1) {
		  joueur1 = false;
		  label.setForeground(Color.yellow);
		  if(ordinateur) label.setText("Ordinateur");
		  else label.setText("Joueur2");
	  }
	  else {
		  joueur1 = true;
		  label.setForeground(Color.blue);
		  label.setText("Joueur1");
	  }
	   
  }
  
  public void DeplaceSiReserveSelect(Reserve r,Point matrice) {
	  if(r.select == 1) {
		  r.effacer(1);
		  r.dessine_rectangle(1);
		  Ajout_pion(r.reste1,matrice,joueur1);
		  r.reste1--;
		  joueur_suivant();
	  }
	  if(r.select == 2) {
		  r.effacer(2);
		  r.dessine_rectangle(2);
		  Ajout_pion(r.reste2,matrice,joueur1);
		  r.reste2--;
		  joueur_suivant();
	  }
	  if(r.select == 3) {
		  r.effacer(3);
		  r.dessine_rectangle(3);
		  Ajout_pion(r.reste3,matrice,joueur1);
		  r.reste3--;
		  joueur_suivant();
	  }  
  }
  
  public void DeplaceSiCaseSelect(Reserve r,Point matrice) {
	  if(selectP >= 0) {
		  Point AncienneCase = matrice_case(selectP);
		  int taille = Point_case(c[AncienneCase.x][AncienneCase.y]);
		  if(taille > 4) taille/=10;
		  Retire_pion(taille,AncienneCase);
		  Efface_pion(selectP);
		  Ajout_pion(taille,matrice,joueur1);
		  joueur_suivant();
		  effacer(selectP);
	  }
  }
  
  public void Deplace_pion(MouseEvent e, Reserve r) {
	  Graphics g = getGraphics(); 
	  int where = WhereIsClik(e);
	  Point matrice = matrice_case(where);
	  if(Case_vide(matrice)) {
		  DeplaceSiReserveSelect(r,matrice);
		  DeplaceSiCaseSelect(r,matrice);
	  }
	  else {
		if(selectP >= 0) {
			DeplaceCaseVersCase(matrice);
		}
		else {
		if(r.select == 0 ){
		  Selectionner(e,r);
	  	}
		else {
			r.dessine_rectangle(r.select);
		}
		}

	  }
	  
	  r.select = 0;
	  Dessine_pion(g);
	  //r.Affiche_pion(g);
  }
  
  public void Deplace_pion_AI(Case ca[][],int profondeur, Reserve r,Reserve r2) {
	  Graphics g = getGraphics(); 
	  Point deplacer = new Point(0,0);
	  Point p_nouveau = new Point(0,0);
	  Point p_ancien = new Point(0,0);
	  Affiche_console_plateu(ca);
	  deplacer = ordi.jouer_ordi(ca,profondeur,r,r2);
	  System.out.println(deplacer.x);
	  System.out.println(deplacer.y);
	  p_nouveau = matrice_case(deplacer.y);
	
	  int Taille = 0;
	  
	  if(deplacer.x < 0) {
		  if(deplacer.x == -1) {
			  Taille = r2.reste1;
			  r2.reste1--;
		  }
		  if(deplacer.x == -2) {
			  Taille = r2.reste2;
			  r2.reste2--;
		  }
		  if(deplacer.x == -3) {
			  Taille = r2.reste3;
			  r2.reste3--;
		  }
		  r2.effacer(-deplacer.x);
		  r2.dessine_rectangle(-deplacer.x);
		  Ajout_pion(Taille,p_nouveau,false);
	  }
	  else {
		  p_ancien = matrice_case(deplacer.x);
		  Taille = Point_case(ca[p_ancien.x][p_ancien.y]);
		  Taille = InitTaille(Taille);
          Retire_pion(Taille,p_ancien);
		  Efface_pion(deplacer.y);
		  Efface_pion(deplacer.x);
		  Ajout_pion(Taille,p_nouveau,false);
	  }
	  joueur_suivant();
	  Dessine_pion(g);
	  
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
  int nbr_pion() {
		 int i,j,nb_de_pions = 0;
		 Point p = new Point(0,0);
		 for(i=0;i<4;i++)
		 {
			 p.x=i;
		      for(j=0;j<4;j++)
		      {
		    	  p.y=j;
		           if(!Case_vide(p))
		           {
		                nb_de_pions++;
		           }
		      }
		 }
		 return nb_de_pions;
	 }
  
//Méthode appelée lors du clic de souris
  public void mouseClicked(MouseEvent e) {
	  
	  if(!fin_du_jeu()) {
		  if(joueur1) {
			  Deplace_pion(e,r);
			  r.Selectionner(e);
		  }
	  	  else {
	  		  if(!ordinateur) {
	  			  Deplace_pion(e,r2);  
	  			  r2.Selectionner(e);
	  		  }
	  	}
	  }
	  if(fin_du_jeu()) {
		  label.setForeground(Color.black);
		  if(victoire == 1) label.setText("BRAVO JOUEUR 1");
		  else {
			  label.setText("BRAVO JOUEUR 2");
		  }
		  fin = true;
	  }
  }

  //Méthode appelée lors du survol de la souris
  public void mouseEntered(MouseEvent e) { }
  //Méthode appelée lorsque la souris sort de la zone du bouton
  public void mouseExited(MouseEvent event) { 
	  effacer(selectP);
  }

  //Méthode appelée lorsque l'on presse le bouton gauche de la souris
  public void mousePressed(MouseEvent event) { }

  //Méthode appelée lorsque l'on relâche le clic de souris
  public void mouseReleased(MouseEvent event) { } 
}