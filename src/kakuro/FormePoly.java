package kakuro;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * @param up et down  de type int
 * Classe qui modifie la valeur des triangles du jeux
 *
 */
public class FormePoly extends JPanel{
	int up,down;
	FormePoly(int up,int down){
		this.up=up;
		this.down=down;
	}
	/**
	 * @param g de type Graphics
	 * Méthode qui permet de gérer les cases d'indications.
	 * Elle gère le contenu de la case, sa couleur et sa forme
	 * 
	 */
	public void paint(Graphics g) {
		Graphics2D g2d=(Graphics2D)g.create();
		
		g2d.setPaint(new Color(152,0,0));
		g2d.setStroke(new BasicStroke(5));
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setPaint(Color.black);
		g2d.setStroke(new BasicStroke(2));
		g2d.drawLine(0, 0, this.getWidth(), this.getHeight());
		g2d.setFont(new Font(null,Font.BOLD,12));
		if(this.up!=0) {
			g2d.drawString(this.up+"",5,this.getHeight()/2+10);
		}else {
			g2d.setPaint(Color.black);
			int []x={0,0,this.getWidth()},y={0,this.getHeight(),this.getHeight()};
			g2d.fillPolygon(x,y,3);
		}
		if(this.down!=0) {
			g2d.drawString(this.down+"",this.getWidth()/2+5,this.getHeight()/2-10);
		}else {
			g2d.setPaint(Color.black);
			int []x={0,this.getWidth(),this.getWidth()},y={0,0,this.getHeight()};
			g2d.fillPolygon(x,y,3);
		}
	}
}
