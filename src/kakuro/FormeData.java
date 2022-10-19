package kakuro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
/**
 * @param value de type int
 * Classe qui modifie la valeur des triangles du jeu
 *
 */
public class FormeData extends JPanel{
	int value;
	FormeData(int value){
		this.value=value;
	}

	/**
	 * @param g de type Graphics
	 * Méthode qui permet de créer, de colorer la bordure et le fond 
	 * Elle permet aussi d'afficher la valeur des cases avec des nombres (pas celle à remplir)
	 */
	public void paint(Graphics g) {
		Graphics2D g2d=(Graphics2D)g.create();
		g2d.setPaint(new Color(222,232,243));
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setPaint(new Color(122,138,153));
		g2d.drawRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setPaint(Color.black);
		g2d.drawString(this.value+"", this.getWidth()/2-3, this.getHeight()/2+3);
	}
}
