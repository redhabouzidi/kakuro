package kakuro;

import java.util.ArrayList;
import java.util.Random;
class prohibitedMoveException extends Exception{
	public prohibitedMoveException() {
		super("Called valeurAll with the position of a Triangle");
		}
	}


public class Grille {
	public Boolean state;
	private Case jeu[][];
	private int x,y;
	public Case[][] getJeu() {
		return jeu;
	}
	/**
	 * Méthode permettant de récuperer la valeur de x placé sous un private
	 */
	public int getX() {
		return x;
	}
	/**
	 * Méthode permettant de modifie la valeur de x
	 * @param x de type int 
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	
	/**
	 * Méthode qui permet de récuperer la valeur de y placé sous un private
	 */
	public int getY() {
		return y;
	}
	/**
	 * Méthode permettant de modifie la valeur de y
	 * @param y de type int
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * 
	 * @param x, y
	 * Classe qui permet d'associer à chque case de la grille, les valeurs x et y associé
	 */
	Grille(int x,int y){
		jeu = new Case[x][y];
		this.state=false;
		this.x=x;
		this.y=y;
	}
	
	/**
	 * Méthode permettant de vérifier si le jeu est correct
	 */
	public int check() throws prohibitedMoveException {
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if(this.jeu[i][j]!=null&&this.jeu[i][j].type==1) {
					if(((Triangle)this.jeu[i][j]).getDown()!=0) {
						int c=j+1;
						while(c<y&&this.jeu[i][c]!=null&&(this.jeu[i][c].type==0||this.jeu[i][c].type==2)) {
							ArrayList<Integer> choix=valeurAll(i,c,this);
							if(!(choix.contains(((Morph)this.jeu[i][c]).getValue())||((Morph)this.jeu[i][c]).getValue()==0 )) {
								return 1;
							}
							c++;
						}
					}
					if(((Triangle)this.jeu[i][j]).getUp()!=0) {
						int c=i+1;
						while(c<y&&this.jeu[c][j]!=null&&(this.jeu[c][j].type==0||this.jeu[c][j].type==2)) {
							ArrayList<Integer> choix=valeurAll(c,j,this);
							if(!(choix.contains(((Morph)this.jeu[c][j]).getValue())||((Morph)this.jeu[c][j]).getValue()==0)) {
								return 1;
							}
							c++;
						}
					}
				}
				
		}
		}
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if(this.jeu[i][j]!=null&&this.jeu[i][j].type==1) {
					
					if(((Triangle)this.jeu[i][j]).getDown()!=0) {
						int a=0;
						int c=j+1;
						while(c<y&&this.jeu[i][c]!=null&&(this.jeu[i][c].type==0||this.jeu[i][c].type==2)) {
							if(((Morph)this.jeu[i][c]).getValue()==0) {
								return 3;
							}
							a+=((Morph)this.jeu[i][c]).getValue();
							c++;
						}
						if(a!=((Triangle)this.jeu[i][j]).getDown()){
							
							return 2;
						}
					}
					if(((Triangle)this.jeu[i][j]).getUp()!=0) {
						int a=0;
						int c=i+1;
						while(c<x&&this.jeu[c][j]!=null&&(this.jeu[c][j].type==0||this.jeu[c][j].type==2)) {
							if(((Morph)this.jeu[c][j]).getValue()==0) {
								return 3;
							}
							a+=((Morph)this.jeu[c][j]).getValue();
							c++;
						}
						if(a!=((Triangle)this.jeu[i][j]).getUp()){
							return 2;
						}
					}
				}
				
		}
		}
		this.state = true;
		return 0;
	}
	/**
	 * Méthode qui permet de copier une grille 
	 * @param g de type Grille
	 */
	public static Grille copie(Grille g) {
		Grille g1=new Grille(g.x,g.y);
		for(int i=0;i<g.x;i++) {
			for(int j=0;j<g.y;j++) {
				if(g.getJeu()[i][j]!=null) {
					switch(g.getJeu()[i][j].type) {
					case 0:
						g1.getJeu()[i][j]=new Morph(0);
						break;
					case 1:
						g1.getJeu()[i][j]=new Triangle(((Triangle)g.getJeu()[i][j]).getDown(),((Triangle)g.getJeu()[i][j]).getUp());
						break;
					case 2:
						g1.getJeu()[i][j]=Morph.data(((Morph)g.getJeu()[i][j]).getValue());
						break;
					}
				}
			}
		}
		return g1;
	}
	/**
	 * Méthode qui permet de renvoyer le tableau des valeurs qu'on peut inserer a la positon x y.
	 * @param x, y de type int
	 */
static ArrayList<Integer> valeurAll(int x,int y,Grille g) throws prohibitedMoveException {
		
		if(g.getJeu()[x][y]!=null&&g.getJeu()[x][y].type==1) {
			throw new prohibitedMoveException();
		}
		ArrayList<Integer> list=new ArrayList<Integer>();
		for(int val=1;val<10;val++) {
			Boolean fini=true;
		int com=x-1;
		while(com>0&&g.getJeu()[com][y]!=null&&(g.getJeu()[com][y].type==0||g.getJeu()[com][y].type==2)&&fini!=false||com>0&&g.getJeu()[com][y]==null) {
			if(g.getJeu()[com][y]!=null&&((Morph)g.getJeu()[com][y]).getValue()==val) {
				fini=false;
			}
			com--;
		}
		com=x+1;
		while(com<g.x&&g.getJeu()[com][y]!=null&&(g.getJeu()[com][y].type==0||g.getJeu()[com][y].type==2)&&fini!=false||com<g.x&&g.getJeu()[com][y]==null) {
			if(g.getJeu()[com][y]!=null&&((Morph)g.getJeu()[com][y]).getValue()==val) {
				fini=false;
			}
			com++;
			
		}
		com=y+1;
		while(com<g.y&&g.getJeu()[x][com]!=null&&(g.getJeu()[x][com].type==0||g.getJeu()[x][com].type==2)&&fini!=false||com<g.y&&g.getJeu()[x][com]==null) {
			if(g.getJeu()[x][com]!=null&&((Morph)g.getJeu()[x][com]).getValue()==val) {
				fini=false;
			}
			com++;
			
		}
		com=y-1;
		while(com>0&&g.getJeu()[x][com]!=null&&(g.getJeu()[x][com].type==0||g.getJeu()[x][com].type==2)&&fini!=false||com>0&&g.getJeu()[x][com]==null) {
			if(g.getJeu()[x][com]!=null&&((Morph)g.getJeu()[x][com]).getValue()==val) {
				fini=false;
			}
			com--;
			
		}
		if(fini == true) {
			list.add(val);
		}
		}
		return list;
	}
	

	
	/**
	 * Méthode qui permet d'afficher une grille avec valeurs aléatoire.
	 * @param x, y de type int
	 */
static Grille grilleRand(int x,int y) throws prohibitedMoveException {
	
	Random rand= new Random();
	Grille g = new Grille(x,y);
	for(int i=1;i<x;i++) {
		g.getJeu()[i][rand.nextInt(2)]=new Triangle(1,0);
	}
	for(int i=1;i<y;i++) {
		g.getJeu()[rand.nextInt(2)][i]=new Triangle(0,1);
	}
	for(int i=0;i<x-1;i++) {
			int place=0;
			while(place<y) {
			place=rand.nextInt(Math.min(y/2,9))+place+2;
			if(place<y)
			g.getJeu()[i][place]=new Triangle(0,1);
			}
		}
	for(int i=0;i<y-1;i++) {
				
				int place=0;
				while(place<x) {
				place=rand.nextInt(Math.min(y/2,9))+place+2;
				if(place<x) {
				if(g.getJeu()[place][i]!=null) {
				((Triangle)(g.getJeu()[place][i])).setDown(1);
				}else {
				g.getJeu()[place][i]=new Triangle(1,0);
				
				}
				}
				}
				}
	for (int i = 0; i < x; i++) {
		for (int j = 0; j < y; j++) {
			if(g.getJeu()[i][j]!=null&&g.getJeu()[i][j].type==1&&((Triangle)g.getJeu()[i][j]).getUp()==1&&i<x-1) {
				g.getJeu()[i+1][j]=null;
			}
			if(g.getJeu()[i][j]!=null&&g.getJeu()[i][j].type==1&&((Triangle)g.getJeu()[i][j]).getDown()==1&&j<y-1) {
				g.getJeu()[i][j+1]=null;
			}
		}
		
	}
	
	for (int i = 0; i < x; i++) {
		for (int j = 0; j < y; j++) {
			if(g.getJeu()[i][j]!=null&&g.getJeu()[i][j].type==1) {
				if(((Triangle)g.getJeu()[i][j]).getUp()==1) {
					int c=i+1;
					while(c<x&&c<i+5) {
						if(g.getJeu()[c][j]==null||g.getJeu()[c][j]!=null&&g.getJeu()[c][j].type!=1&&g.getJeu()[c][j].type!=0) {
							ArrayList<Integer> choix=valeurAll(c,j,g);
							if(choix.size()==0) {
								
								return grilleRand(x, y);
							}
							if(rand.nextInt(x*y)%(x*y/10)==0) {
								g.getJeu()[c][j]=Morph.data(choix.get(rand.nextInt(choix.size())));
							}
							g.getJeu()[c][j]=new Morph(choix.get(rand.nextInt(choix.size())));
							
						}else {
							break;
						}
						c++;
						
					}
				}
				if(((Triangle)g.getJeu()[i][j]).getDown()==1) {
					int c=j+1;
					while(c<y&&c<j+5) {
						if(g.getJeu()[i][c]==null||g.getJeu()[i][c]!=null&&g.getJeu()[i][c].type!=1&&g.getJeu()[i][c].type!=0) {
							ArrayList<Integer> choix=valeurAll(i,c,g);
							if(choix.size()==0) {
								return grilleRand(x, y);
							}
							if(rand.nextInt(x*y)%(x*y/10)==0) {
								g.getJeu()[i][c]=Morph.data(choix.get(rand.nextInt(choix.size())));	
							}else {
							g.getJeu()[i][c]=new Morph(choix.get(rand.nextInt(choix.size())));
							}
						}else {
							break;
						}
						c++;
					}
					}
				
			}
		}
		
	}
	for (int i = 0; i < x; i++) {
		for (int j = 0; j < y; j++) {
			if(g.getJeu()[i][j]!=null&&g.getJeu()[i][j].type==1) {
				if(((Triangle)g.getJeu()[i][j]).getDown()==1) {
					int c=j+1;
					int value=0;
					while(c<y&&g.getJeu()[i][c]!=null&&(g.getJeu()[i][c].type==0||g.getJeu()[i][c].type==2)) {
						value+=((Morph)g.getJeu()[i][c]).getValue();
						c++;
					}
					((Triangle)g.getJeu()[i][j]).setDown(value);
				}
				if(((Triangle)g.getJeu()[i][j]).getUp()==1) {
					int c=i+1;
					int value=0;
					while(c<x&&g.getJeu()[c][j]!=null&&(g.getJeu()[c][j].type==0||g.getJeu()[c][j].type==2)) {
						value+=((Morph)g.getJeu()[c][j]).getValue();
						c++;
					}
					((Triangle)g.getJeu()[i][j]).setUp(value);
				}
			}
		}
	}
	return g;
}
	public static void main(String args[]) {
		Frames f=new Frames();
		Grille g=new Grille(5,4);
		g.getJeu()[0][2]=new Triangle(0, 25);
		g.getJeu()[0][3]=new Triangle(0 , 2);
		g.getJeu()[1][1]=new Triangle(8 , 5);
		g.getJeu()[2][0]=new Triangle(11 ,0);
		g.getJeu()[3][0]=new Triangle(15 ,0);
		g.getJeu()[4][1]=new Triangle(3 , 0);
		g.getJeu()[2][3]=new Triangle(0 , 5);
		g.getJeu()[1][2]=new Morph(6);
		g.getJeu()[1][3]=new Morph(2);
		g.getJeu()[2][1]=new Morph(3);
		g.getJeu()[3][2]=new Morph(9);
		g.getJeu()[3][3]=new Morph(4);
		g.getJeu()[4][2]=new Morph(2);
		g.getJeu()[4][3]=new Morph(1);
		g.getJeu()[2][2]=Morph.data(8);
		g.getJeu()[3][1]=Morph.data(2);
		try {
			f.setPredGame(g);
		} catch (valueException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
