package kakuro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
class valueException extends Exception{
	public valueException() {
		super("the value entered are too big or too low");
		}
	}
/**
 * Cette classe permet l'affichage de la fenêtre graphique 
 */
public class Frames {
	Boolean display;
	public static JFrame j;
	public static JButton b[][];
	public static JButton gen,sol,chk,hard;
	public static JPanel panel;
	public static JPanel table;
	public static JLabel message;
	public static JButton selected;
	Frames(){
		display=true;
		j=new JFrame("Kakuro");
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setLayout(null);
		table=new JPanel();
		panel= new JPanel();
		selected=null;
		j.add(table);
		j.add(panel);
	}
	
	
	/**
	 * Méthode qui permet d'afficher une grille de taille x, y, en faisant appel à la méthode setPredGame et grilleRand
	 * @param x,y de type int
	 */
	public void setGame(int x,int y) throws prohibitedMoveException, valueException {
		Grille g = Grille.grilleRand(x, y);
		setPredGame(g);
	}
	/**
	 * Méthode permettant la création des bouttons generate, solve et check. Ainsi l'entrée des tailles X ey Y avec leur label. 
	 * La méthode permet aussi d'afficher un message lorsqu'il manque, ou qu'une valeur est mise deux fois ou que le jeu est incorrect.Mais pas que
	 * elle affiche aussi un message quand le jeu est gagné.
	 * @param g de type Grilles
	 */
	public void setPredGame(Grille g) throws valueException{
		Grille g1=Grille.copie(g);
		int x=g.getX(),y=g.getY();		
		Dimension screen=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		b=new JButton[g.getX()][g.getY()];
		j.setSize(Math.min(screen.width, Math.max(900, g.getX()*60+200)),Math.min(screen.height-100, 150+60*g.getY()));
		panel.setBounds(0,0,Math.min(screen.width, Math.max(900, g.getX()*60+200)),50);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
		JLabel lb=new JLabel("taille X");
		JTextField tf=new JTextField(5);
		JLabel lb1=new JLabel("taille Y");
		JTextField tf1=new JTextField(5);
		panel.add(lb);panel.add(tf);panel.add(lb1);panel.add(tf1);
		gen=new JButton("generate");
		sol=new JButton("solve");
		hard=new JButton("Make it harder");
		chk=new JButton("check");
		hard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display=!display;
				if(display) {
					hard.setText("Make it harder");
					for(int i=0;i<g1.getX();i++) {
						for(int j=0;j<g1.getY();j++) {
							if(g1.getJeu()[i][j]!=null&&g1.getJeu()[i][j].type==0&&((Morph)g1.getJeu()[i][j]).getValue()!=0) {
								b[i][j].setText(((Morph)g1.getJeu()[i][j]).getValue()+"");
							}
						}
					}
				}else {
					hard.setText("Easier ?");
					for(int i=0;i<g1.getX();i++) {
						for(int j=0;j<g1.getY();j++) {
							if(g1.getJeu()[i][j]!=null&&g1.getJeu()[i][j].type==0) {
								b[i][j].setText("");
							}
						}
					}
				}
			}
		});
		sol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<g.getX();i++) {
					for(int j=0;j<g.getY();j++) {
						if(g.getJeu()[i][j]!=null&&g.getJeu()[i][j].type==0) {
							b[i][j].setText(((Morph)g.getJeu()[i][j]).getValue()+"");
							((Morph)g1.getJeu()[i][j]).setValue(((Morph)g.getJeu()[i][j]).getValue());
						}
					}
				}
				
			}
		});
		chk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					switch(g1.check()) {
					case 0:
						message.setForeground(Color.green);
						message.setText("Victoire !!!");
						break;
					case 1:
						message.setForeground(Color.red);
						message.setText("Numero duplique");
						break;
					case 2:
						message.setForeground(Color.red);
						message.setText("somme incorrecte");
						break;
					case 3:
						message.setForeground(Color.black);
						message.setText("Cases manquantes");
						break;
					default:
						
						break;
					}
				} catch (prohibitedMoveException e1) {
					e1.printStackTrace();
				}
			}
		});
		gen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				j.remove(panel);
				j.remove(table);
				table=new JPanel();
				panel= new JPanel();
				panel.setBounds(0,0,0,0);
				table.setBounds(0,0,0,0);
				j.add(panel);
				j.add(table);
				int a=0;
				int b=0;
				int i=0;
				int c;
				while(i<tf.getText().length()&& (c=tf.getText().charAt(i))>47&&c<58) {
					a=a*10+(c-48);
					i++;
				}
				i=0;
				while(i<tf1.getText().length()&& (c=tf1.getText().charAt(i))>47&&c<58) {
					b=b*10+(c-48);
					i++;
				}
				if(a>50||a<5||b>50||b<5) {
					try {
					throw new valueException();
					}catch(valueException f) {
						f.printStackTrace();
						b=5;
						a=5;
					}
					}
				try {
					setGame(a,b);
				} catch (prohibitedMoveException | valueException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(gen);panel.add(sol);panel.add(hard);panel.add(chk);
		message=new JLabel();
		panel.add(message);
		table.setBounds(100,50,Math.min(screen.width-200,60*x),Math.min(screen.height-200, 60*y));
		table.setLayout(new GridLayout(x,y,5,5));
		for(int i=0;i<x;i++) {
			for(int k=0;k<y;k++) {
				if(g1.getJeu()[i][k]!=null&&g1.getJeu()[i][k].type==0) {
				b[i][k]=new JButton();
				((Morph)g1.getJeu()[i][k]).setValue(0);
				int tempx=i,tempy=k;
				b[i][k].addKeyListener(new KeyListener() {
					@Override
					public void keyTyped(KeyEvent e) {
						if(selected!=null&&e.getKeyChar()<58&&e.getKeyChar()>48&&g.state!=true) {
						if(display) {
						selected.setText(e.getKeyChar()+"");
						}
						((Morph)g1.getJeu()[tempx][tempy]).setValue(e.getKeyChar()-48);
						selected=null;
						}
						
					}

					@Override
					public void keyPressed(KeyEvent e) {
						
					}

					@Override
					public void keyReleased(KeyEvent e) {
						
					}
				});
				table.add(b[i][k]);
				JButton bu=b[i][k];
				b[i][k].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selected=bu;
					}
				});
				}else if(g.getJeu()[i][k]!=null&&g.getJeu()[i][k].type==1){
				if(((Triangle)g.getJeu()[i][k]).getDown()==0&&((Triangle)g.getJeu()[i][k]).getUp()==0) {
				JPanel l=new JPanel();
				table.add(l);
				
				l.setBackground(new Color(0,0,0));
				}else {
				FormePoly l=new FormePoly(((Triangle)g.getJeu()[i][k]).getUp(),((Triangle)g.getJeu()[i][k]).getDown());
				table.add(l);
				l.setBackground(new Color(152,0,0));
				}
				}else if(g.getJeu()[i][k]!=null&&g.getJeu()[i][k].type==2){
					FormeData l=new FormeData(((Morph)g.getJeu()[i][k]).getValue());
					table.add(l);
					l.setBackground(Color.black);	
				
				}else {
				JPanel l=new JPanel();
				table.add(l);
				l.setBackground(new Color(0,0,0));
				}
			}
		}
		
		j.setVisible(true);
		
	}
}
