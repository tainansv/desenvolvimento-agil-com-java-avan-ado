
public class Diferenca {

	private String propriedade;
	private Object valorVelho;
	private Object valorNovo;
	
	public Diferenca(String propriedade, Object valorVelho, Object valorNovo) {
		super();
		this.propriedade = propriedade;
		this.valorVelho = valorVelho;
		this.valorNovo = valorNovo;
	}
	@Override
	public String toString() {
		return "Diferenca [propriedade=" + propriedade + ", valorVelho=" + valorVelho + ", valorNovo=" + valorNovo
				+ "]";
	}
	
	
}
