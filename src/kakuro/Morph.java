package kakuro;

public class Morph extends Case{
	private int value;
	Morph(int value){
		super(0);
		this.value=value;
	}
	public static Morph data(int value) {
		Morph m= new Morph(value);
		m.type=2;
		return m;
	}
	/**
	 * Méthode permettant de récuperer la valeur de value placé sous un private 
	 */
	public int getValue() {
		return value;
	}
	/**
	 * Méthode permettant de modifie la valeur de value
	 * @param value de type int
	 */
	public void setValue(int value) {
		this.value = value;
	}
}
