
public class PrincipalInsert {

	public static void main(String[] args) {
		
		Usuario u = new Usuario();
		u.setLogin("tainan");
		u.setNome("TainanValentim");
		u.setEmail("tainansv@gmail.com");
		
		UsuarioDAO.inserirUsuario(u);
	}

}
