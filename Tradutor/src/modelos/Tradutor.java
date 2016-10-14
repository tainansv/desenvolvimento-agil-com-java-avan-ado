package modelos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Tradutor {

	String _caminho = new File("./palavras.txt").getCanonicalPath();
	File _arquivo = new File(_caminho);
	
    public Tradutor() throws IOException {
    	super();
	}

	public String traduz(String palavra) {
		try(Scanner _sc = new Scanner(_arquivo).useDelimiter("-")) {
			
			while(_sc.hasNextLine()){
				if(_sc.hasNext(palavra)){
					String traducao = _sc.nextLine().split("-")[1];
					return traducao;
				}else
					_sc.nextLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return palavra;
	}
}
