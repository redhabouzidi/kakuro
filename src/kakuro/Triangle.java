package kakuro;

public class Triangle extends Case{
	private int up,down;
	Triangle( int down,int up){
		
		super(1);
		this.up=up;
		this.down=down;
	}
	/**
	 * Méthode permettant de récuperer la valeur de down placé sous un private
	 * @return int 
	 */
	public int getDown() {
		return down;
	}
	/**
	 * Méthode permettant de récuperer la valeur de up placé sous un private
	 * @return int 
	 */
	public int getUp() {
		return up;
	}
	/**
	 * Méthode permettant de modifie la valeur de down
	 * @param down de type int
	 */
	public void setDown(int down) {
		this.down = down;
	}
	/**
	 * Méthode permettant de modifie la valeur de up
	 * @param up de type int
	 */ 
	public void setUp(int up) {
		this.up = up;
	}
}
