package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDeDados {

    private static final String SERVER = "localhost";
    private static final String PORT = "1523"; // Porta TCP padrão do Oracle
    private static final String DATABASE = "xe";

    // Configuração dos parâmetros de autenticação
    private static final String USER = "system";
    private static final String PASS = "oracle";

    private static final String SCHEMA = "WalletLife";


    private static final String SERVERFIRMA = "vemser-hml.dbccompany.com.br";
    private static final String PORTFIRMA = "25000"; // Porta TCP padrão do Oracle
    private static final String DATABASEFIRMA = "xe";
    // Configuração dos parâmetros de autenticação
    private static final String USERFIRMA = "EQUIPE_5";
    private static final String PASSFIRMA = "oracle";
    private static final String SCHEMAFIRMA = "EQUIPE_5";

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@" + SERVER + ":" + PORT + ":" + DATABASE;
        // jdbc:oracle:thin:@localhost:1521:xe

        // Abre-se a conexão com o Banco de Dados
        Connection con = DriverManager.getConnection(url, USER, PASS);

        // sempre usar o schema vem_ser
        con.createStatement().execute("alter session set current_schema=" + SCHEMA);

        return con;
    }

    public static Connection getConnectionFirma() throws SQLException {
        String url = "jdbc:oracle:thin:@" + SERVERFIRMA + ":" + PORTFIRMA + ":" + DATABASEFIRMA;
        // jdbc:oracle:thin:@localhost:1521:xe

        // Abre-se a conexão com o Banco de Dados
        Connection con = DriverManager.getConnection(url, USERFIRMA, PASSFIRMA);

        // sempre usar o schema vem_ser
        con.createStatement().execute("alter session set current_schema=" + SCHEMAFIRMA);

        return con;
    }
}
