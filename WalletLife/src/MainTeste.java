import repository.UsuarioRepository;

import java.sql.SQLException;

public class MainTeste {
    public static void main(String[] args) throws SQLException {

        UsuarioRepository usuarioRepository = new UsuarioRepository();

        System.out.println("Fez login? " + usuarioRepository.loginUsuario("jefferson.ilovedbc@dbc.com", "segura789"));

    }
}
