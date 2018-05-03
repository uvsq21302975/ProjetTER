
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ordinateur extends JPanel {
	
	public JLabel label = new JLabel("");
	public Case[][] cas = new Case[4][4];
	
	public Ordinateur() {
		
	}
	
	public void init_Case_plateau() {
		  int i,j;
		  for(i=0;i<4;i++) {
			  for(j=0;j<4;j++) {
				  cas[i][j] = new Case();
				  cas[i][j].petit = 0;
				  cas[i][j].moyen = 0;
				  cas[i][j].grand = 0;
				  cas[i][j].mega = 0;
			  }
		  }
	  }
	 
	 public void Affiche_console_plateu(Case c[][]) {
		  int i,j;
		  for(i=0;i<4;i++) {
			  for(j=0;j<4;j++) {
				  System.out.print(Taille_case(c[i][j]));
				  System.out.print("|");
			  }
			  System.out.println();
		  }
		  System.out.println("*****");
	  }
	 
	Point jouer_ordi(Case jeu[][],int profondeur,Reserve r,Reserve r2) {
		 
	     int max = -10000;
	     int Tpp = 0, Tpr = 0;
	     int i,j,k,m,tmp=0,maxi=0,maxj=0;
	     int taille_reste_reserve=0;
	     int reste_r = 0;
	     boolean deplace_sur_plateau = false;
	     Point p = new Point();
	     Point p_parcour = new Point();
	     Point case_modifier = new Point(0,0);
	     Point p_ancien = new Point(0,0);
	     int alea = (int) Math.random();
	     //init_Case_plateau(); //init c le jeu copier
	     cas = jeu;
	    
	     
	     for(k=0;k<3;k++) {	//reserve
	    	 if(k==0)taille_reste_reserve = r2.reste1;
	    	 if(taille_reste_reserve == 0) k++;
	    	 if(k==1)taille_reste_reserve = r2.reste2;
	    	 if(taille_reste_reserve == 0) k++;
	    	 if(k==2)taille_reste_reserve = r2.reste3;
	    	 if(taille_reste_reserve == 0) break;
	    	 
		     for(i=0;i<4;i++)
		     {
		    	p.x=i;
		          for(j=0;j<4;j++)
		          {
		        	  p.y=j;
		        	  if(Case_vide(jeu,p))
		                {
		        		  		if(k==0) r2.reste1--;
		        		  		if(k==1) r2.reste2--;
		        		  		if(k==2) r2.reste3--;
		                      Ajout_pion(taille_reste_reserve,p,false);
		                      
		                      tmp = Min(cas,profondeur-1,r,r2);
		                     System.out.println("RESERVE["+i+"]["+j+"]  :");
		                     System.out.println(tmp);
		                     // Affiche_console_valeur_tmp(tmp);
		                      Retire_pion(taille_reste_reserve,p);
		                      
		                      	if(k==0) r2.reste1++;
		        		  		if(k==1) r2.reste2++;
		        		  		if(k==2) r2.reste3++;
		                      if(tmp > max || (tmp==max && alea==0))
		                      {
		                    	  	reste_r = -(k+1);
		                            max = tmp;
		                            maxi = i;
		                            maxj = j;
		                            Tpr = taille_reste_reserve;
		                      }
		    
		                }
		          }
		     }
		     
		     
	     }
	     System.out.println("maxi "+maxi);
	     System.out.println("maxj "+maxj);
	     int taille_case = 0;
	     int taille_case2 = 0;
	     
	     for(k=0;k<4;k++) {	//PLATEAU
	    	 p_parcour.x=k;
	    	 for(m=0;m<4;m++) {
	    		 p_parcour.y=m;
	    		 
	    		 if(LastPionCaseJoueur1(cas,p_parcour) == 2) {//Joueur2
	    			 
	    			 taille_case = Taille_case(cas[k][m]);
	    			 taille_case = InitTaille(taille_case);
	    		 
				     for(i=0;i<4;i++)
				     {
				    	p.x=i;
				          for(j=0;j<4;j++)
				          {
				        	  p.y=j;
				        	  taille_case2 = Taille_case(cas[i][j]);
				        	  taille_case2 = InitTaille(taille_case2);
				        	  if((Case_vide(jeu,p) || taille_case > taille_case2) && p != p_parcour)
				                {
			                      Ajout_pion(taille_case,p,false);
			                      Retire_pion(taille_case,p_parcour);
			                      tmp = Min(cas,profondeur-1,r,r2);
			                      Retire_pion(taille_case,p);
			                      Ajout_pion(taille_case,p_parcour,false);
			                      
			                      System.out.println("PLATEAU["+i+"]["+j+"] :");
					        	  System.out.println(tmp);
					        	  if(tmp > max || (tmp==max && alea==0))
			                      {
			                    	  	p_ancien.x = p_parcour.x; //k
			                    	  	p_ancien.y = p_parcour.y; //m
			                            max = tmp;
			                            maxi = i;
			                            maxj = j;
			                            deplace_sur_plateau = true;
			                            Tpp = taille_case;
			                      }
				                }
				          }
				     }
	    		 }// FIN if
	    	 }
	     }
	     //cas = jeu;
	     p.x = maxi;
	     p.y = maxj;
	    
	     if(deplace_sur_plateau) {
	    	 
             case_modifier.x = Conversion_point_numero(p_ancien);
             case_modifier.y = Conversion_point_numero(p);
	     }
	     else {
	    
	    	 case_modifier.x = reste_r;
	    	 case_modifier.y = Conversion_point_numero(p);
	     }
	     return case_modifier;
	}
	
	public int Conversion_point_numero(Point p) {
		int num = p.x * 4 + p.y; 
		return num;
	}
	
	public void Ajout_pion(int reste, Point matrice,boolean joueur) {
		  if(joueur) {
			if(reste == 4) { cas[matrice.x][matrice.y].mega = 1; }
		  	if(reste == 3) { cas[matrice.x][matrice.y].grand = 1; }
		  	if(reste == 2) { cas[matrice.x][matrice.y].moyen = 1; }
		  	if(reste == 1) { cas[matrice.x][matrice.y].petit = 1; }
		  }
		  else {
			  if(reste == 4) { cas[matrice.x][matrice.y].mega = 2; }
			  if(reste == 3) { cas[matrice.x][matrice.y].grand = 2; }
			  if(reste == 2) { cas[matrice.x][matrice.y].moyen = 2; }
			  if(reste == 1) { cas[matrice.x][matrice.y].petit = 2; }
		  }
	  }
	
	public void Retire_pion(int reste, Point matrice) {
		if(reste == 4) { cas[matrice.x][matrice.y].mega = 0; }
	  	if(reste == 3) { cas[matrice.x][matrice.y].grand = 0; }
	  	if(reste == 2) { cas[matrice.x][matrice.y].moyen = 0; }
	  	if(reste == 1) { cas[matrice.x][matrice.y].petit = 0; }
  }
	
	
	int Max(Case jeu[][],int profondeur,Reserve r,Reserve r2)
	{
	     if(profondeur == 0 || fin_du_jeu(jeu)!=0)
	     {
	          return eval(jeu);
	     }
	     
	     int alea = (int) Math.random();
	     int max = -10000;
	     int i,j,k,m,tmp=0;
	     int taille_reste_reserve=0;
	     Point p = new Point();
	     Point p_parcour = new Point();
	     
	     
	     for(k=0;k<3;k++) {	//reserve
	    	 if(k==0)taille_reste_reserve = r2.reste1;
	    	 if(taille_reste_reserve == 0) k++;
	    	 if(k==1)taille_reste_reserve = r2.reste2;
	    	 if(taille_reste_reserve == 0) k++;
	    	 if(k==2)taille_reste_reserve = r2.reste3;
	    	 if(taille_reste_reserve == 0) break;
	    	 
		     for(i=0;i<4;i++)
		     {
		    	p.x=i;
		          for(j=0;j<4;j++)
		          {
		        	  p.y=j;
		        	  if(Case_vide(jeu,p))
		                {
		        		  		if(k==0) r2.reste1--;
		        		  		if(k==1) r2.reste2--;
		        		  		if(k==2) r2.reste3--;
		                      Ajout_pion(taille_reste_reserve,p,false);
		                      tmp = Min(cas,profondeur-1,r,r2);
		                      Retire_pion(taille_reste_reserve,p);
		                      	if(k==0) r2.reste1++;
		        		  		if(k==1) r2.reste2++;
		        		  		if(k==2) r2.reste3++;
		                      if(tmp > max || (tmp==max && alea==0))
		                      {
		                            max = tmp;
		                      }
		    
		                }
		          }
		     }
	     }
	     
	     int taille_case = 0;
	     int taille_case2 = 0;
	     for(k=0;k<4;k++) {	//PLATEAU
	    	 p_parcour.x=k;
	    	 for(m=0;m<4;m++) {
	    		 p_parcour.y=m;
	    		 
	    		 if(LastPionCaseJoueur1(cas,p_parcour) == 2) {//Joueur2
	    			 
	    			 taille_case = Taille_case(cas[k][m]);
	    			 taille_case = InitTaille(taille_case);
	    		 
				     for(i=0;i<4;i++)
				     {
				    	p.x=i;
				          for(j=0;j<4;j++)
				          {
				        	  p.y=j;
				        	  taille_case2 = Taille_case(cas[i][j]);
				        	  taille_case2 = InitTaille(taille_case2);
				        	  if((Case_vide(jeu,p) || taille_case > taille_case2) && p != p_parcour)
				                {
			                      Ajout_pion(taille_case,p,false);
			                      Retire_pion(taille_case,p_parcour);				                      
			                      tmp = Min(cas,profondeur-1,r,r2);
			                      Retire_pion(taille_case,p);
			                      Ajout_pion(taille_case,p_parcour,false);
			                      
			                      if(tmp > max || (tmp==max && alea==0))
			                      {
			                         max = tmp;
			                      }
				                }
				          }
				     }
	    		 }// FIN if
	    	 }
	     }
	     
	     return max;
	     
	}
	
	int Min(Case jeu[][],int profondeur,Reserve r,Reserve r2)
	{
		
	     if(profondeur == 0 || fin_du_jeu(jeu)!=0)
	     {
	          return eval(jeu);
	     }
	
	     int min = 10000;
	     int i,j,m,k,tmp=0;
	     int taille_reste_reserve=0;
	     Point p = new Point();
	     Point p_parcour = new Point();
	  
	     
	     for(k=0;k<3;k++) {	//reserve
	    	 if(k==0)taille_reste_reserve = r.reste1;
	    	 if(taille_reste_reserve == 0) k++;
	    	 if(k==1)taille_reste_reserve = r.reste2;
	    	 if(taille_reste_reserve == 0) k++;
	    	 if(k==2)taille_reste_reserve = r.reste3;
	    	 if(taille_reste_reserve == 0) break;
	    	 
		     for(i=0;i<4;i++)
		     {
		    	p.x=i;
		          for(j=0;j<4;j++)
		          {
		        	  p.y=j;
		        	  
		        	  if(Case_vide(jeu,p))
		                {
		        		  		if(k==0) r.reste1--;
		        		  		if(k==1) r.reste2--;
		        		  		if(k==2) r.reste3--;
		                      Ajout_pion(taille_reste_reserve,p,true);
		                     
		                      tmp = Max(cas,profondeur-1,r,r2);
		                      Retire_pion(taille_reste_reserve,p);
		                      	if(k==0) r.reste1++;
		        		  		if(k==1) r.reste2++;
		        		  		if(k==2) r.reste3++;
		                      if(tmp < min)
		                      {
		                            min = tmp;
		                      }
		    
		                }
		          }
		     }
	     }
	    
	     int taille_case = 0;
	     int taille_case2 = 0;
	    
	     for(k=0;k<4;k++) {	//PLATEAU
	    	 p_parcour.x=k;
	    	 for(m=0;m<4;m++) {
	    		 p_parcour.y=m;
	    		 
	    		 if(LastPionCaseJoueur1(cas,p_parcour) == 1) {//Joueur1
	    			 taille_case = Taille_case(cas[k][m]);
	    			 taille_case = InitTaille(taille_case);
	    		 
				     for(i=0;i<4;i++)
				     {
				    	p.x=i;
				          for(j=0;j<4;j++)
				          {
				        	  p.y=j;
				        	  taille_case2 = Taille_case(cas[i][j]);
				        	  taille_case2 = InitTaille(taille_case2);
				        	  if((Case_vide(jeu,p) || taille_case > taille_case2) && p != p_parcour)
				                {
			                      Ajout_pion(taille_case,p,true);
			                      Retire_pion(taille_case,p_parcour);				                      
			                      tmp = Max(cas,profondeur-1,r,r2);
			                      Retire_pion(taille_case,p);
			                      Ajout_pion(taille_case,p_parcour,true);
			                      if(tmp < min )
			                      {
			                           min = tmp;
			                      }
				                }
				          }
				     }
	    		 }// FIN if
	    	 }
	     }
	     
	     return min;
	     
	}

	public int InitTaille(int t) {
		  if(t>4) return t/10;
		  return t;
	  }
	
	  public int Taille_case(Case c) {
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
	  
	  public boolean Case_vide(Case c[][],Point p) {
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
	  
	  public int LastPionCaseJoueur1(Case c[][], Point matrice) {
		  int joueur = Taille_case(c[matrice.x][matrice.y]);
		  if(joueur > 4) return 2;
		  else if(joueur <= 4 && joueur > 0) return 1;
		  else return 0;
	  }
	  
	 public int fin_du_jeu(Case c[][]) {
		  int i,j,k ,ligne=0,colonne=0, diago=0, diago2=0;
		  Point p = new Point(0,0);
		  int joueur = 1;
		  
		  for(k=1;k<3;k++) {
			  
			  for(i=0;i<4;i++) {
				  p.x=i;
				  for(j=0;j<4;j++) {
					  p.y=j;
					  if(!Case_vide(c,p)) {
						  if(LastPionCaseJoueur1(c,p) == joueur)  ligne++;
						  if(i == j) {
							  if(LastPionCaseJoueur1(c,p) == joueur)  diago++;
						  }
						  if(i+j == 3) {
							  if(LastPionCaseJoueur1(c,p) == joueur)  diago2++;
						  }
					  }
				  }
				  if(ligne == 4) return k;
				  ligne = 0;
			  }
			  if(diago == 4 || diago2 == 4) return k;
			  diago = 0;
			  diago2 = 0;
			  for(i=0;i<4;i++) {
				  p.y=i;
				  for(j=0;j<4;j++) {
					  p.x=j;
					  if(!Case_vide(c,p)) {
						  if(LastPionCaseJoueur1(c,p) == joueur)  colonne++;
					  }
				  }
				  if(colonne == 4) return k;
				  colonne = 0;
			  }
			  joueur = 2;
	  	}
		//Si le jeu n'est pas fini et que personne n'a gagné, on renvoie 0
          for(i=0;i<4;i++)
          {
        	  p.x=i;
               for(j=0;j<4;j++)
               {
            	   p.y=j;
                    if(Case_vide(c,p))
                    {
                         return 0;
                    }
               }
          }
          
		  return 3;
	  }
	 
	 
	int eval(Case jeu[][]){
		
		int vainqueur,nb_de_pions = 0;
	 int i,j;
	Point p = new Point(0,0);
	 //On compte le nombre de pions présents sur le plateau
	 for(i=0;i<4;i++)
	 {
		 p.x=i;
	      for(j=0;j<4;j++)
	      {
	    	  p.y=j;
	           if(!Case_vide(jeu,p))
	           {
	                nb_de_pions++;
	           }
	      }
	 }
	
	 if((vainqueur=fin_du_jeu(jeu)) != 0)
	 {
	      if( vainqueur == 2 )
	      {
	           return 1000 - nb_de_pions;
	      }
	      else if( vainqueur == 1 )
	      {
	           return -1000 + nb_de_pions;
	      }
	      else
	      {
	           return 0;
	      }
	 }
	
	 //On compte le nombre de séries de 2 pions alignés de chacun des joueurs
	     int series_j1 = 0, series_j2 = 0;
	     
	  
	
	     return series_j1 - series_j2;

	}
	
}

