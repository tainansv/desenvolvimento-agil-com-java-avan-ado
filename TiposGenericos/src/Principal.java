import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class Principal {

	public static void main(String[] args) {
		Cesta<Fruta> frutas = new Cesta<>();
		frutas.adiciona(new Fruta("maça"));
		frutas.adiciona(new Fruta("banana"));
		frutas.adiciona(new Fruta("mamão"));
		frutas.adiciona(new Fruta("pera"));
		frutas.adiciona(new Fruta("uva"));
		frutas.adiciona(new FrutaVermelha("morango"));
		
		List<FrutaVermelha> vermelhas = new ArrayList<>();
		vermelhas.add(new FrutaVermelha("amora"));
		vermelhas.add(new FrutaVermelha("cereja"));
		
		frutas.adicionaTodos(vermelhas);
		
		while (frutas.temItens()) {
			System.out.println(frutas.retira());
		}
		
	}
}
