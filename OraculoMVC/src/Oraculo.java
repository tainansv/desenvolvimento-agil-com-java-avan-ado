import java.util.ArrayList;
import java.util.List;

public class Oraculo {

	public List<String> melhoresProdutos(String tipo){
		List<String> lista = new ArrayList<>();
		if(tipo.equals("doce de leite")){
			lista.add("viçosa");
			lista.add("boreau");
		}else if(tipo.endsWith("queijo mineiro")){
			lista.add("candido tostes");
			lista.add("humaitá");
			lista.add("da tia Fulana");
		}
		
		return lista;
	}
}
