package repository;

import exceptions.BancoDeDadosException;
import modelos.Usuario;

import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository implements Repositorio<Integer, Usuario> {

    public boolean loginUsuario(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM USUARIO u " +
                     "WHERE u.email = '" + email + "' AND u.senha = '" + senha + "'";

        Statement stmt = ConexaoBancoDeDados.getConnection().createStatement();
        ResultSet res = stmt.executeQuery(sql);

        boolean loginPermitido = false;
        Usuario usuario = new Usuario();
        while(res.next()) {
            usuario.setId(res.getInt("id_usuario"));
            usuario.setNomeCompleto(res.getString("nome"));
            usuario.setCpf(res.getString("cpf"));
            loginPermitido = true;
        }

        return loginPermitido;
    }

    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT seq_usuario.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    @Override
    public Usuario adicionar(Usuario usuario) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Integer proxId = this.getProximoId(con);
            usuario.setId(proxId);

            String sql = "INSERT INTO USUARIO(ID_USUARIO, NOME, DATANACIMENTO, CPF, EMAIL, SENHA)" +
                    "VALUES(?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, proxId);
            stmt.setString(2, usuario.getNomeCompleto());
            stmt.setDate(3, Date.valueOf(usuario.getDataNascimento()));
            stmt.setString(4, usuario.getCpf()); // @TODO Possível erro.
            stmt.setString(5, usuario.getEmail());
            stmt.setString(6, usuario.getSenha());

            int res = stmt.executeUpdate();
            System.out.println("adicionarUsuario.res= " + res);
            return usuario;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM USUARIO WHERE ID_CONTATO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerUsuarioPorId.res= " + res);

            return res > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean editar(Integer id, Usuario usuario) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PESSOA SET ");
            sql.append(" nome = ?,");
            sql.append(" dataNascimento = ? ");
            sql.append(" cpf = ?,");
            sql.append(" email = ?,");
            sql.append(" senha = ?,");
            sql.append(" WHERE id_usuario = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setDate(2, Date.valueOf(usuario.getDataNascimento()));
            stmt.setString(3, usuario.getCpf());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getSenha());
            stmt.setInt(6, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarUsuario.res=" + res);

            return res > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Usuario> listar() throws BancoDeDadosException {
        List<Usuario> usuarios = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM PESSOA";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(res.getInt("id_pessoa"));
                usuario.setNomeCompleto(res.getString("nome"));
                usuario.setDataNascimento(res.getDate("data_nascimento").toLocalDate());
                usuario.setCpf(res.getString("cpf"));
                usuario.setEmail(res.getString("email"));
                usuario.setSenha(res.getString("senha"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usuarios;
    }
}
