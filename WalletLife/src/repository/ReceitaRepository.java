package repository;

import enumerators.TipoDespesaEReceita;
import exceptions.BancoDeDadosException;
import modelos.Receita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceitaRepository implements Repositorio<Integer, Receita> {

    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT seq_receita.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    @Override
    public Receita adicionar(Receita receita) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            receita.setId(proximoId);

            String sql = "INSERT INTO RECEITA\n" +
                    "(seq_receita.nextval, TIPO, VALOR, DESCRICAO, BANCO, EMPRESA, ID_USUARIO)\n" +
                    "VALUES(?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, receita.getId());
            stmt.setString(2, receita.getTipo().toString());
            stmt.setDouble(3, receita.getValor());
            stmt.setString(4, receita.getDescricao());
            stmt.setString(5, receita.getBanco());
            stmt.setString(6, receita.getEmpresa());
            stmt.setString(7, receita.getBanco());

            int res = stmt.executeUpdate();
            System.out.println("adicionarReceita.res=" + res);
            return receita;
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

            String sql = "DELETE FROM RECEITA WHERE id_receita = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerReceitaPorId.res=" + res);

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
    public boolean editar(Integer id, Receita receita) throws BancoDeDadosException {

        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE RECEITA SET ");
            sql.append(" tipo = ?,");
            sql.append(" valor = ? ");
            sql.append(" descricao = ? ");
            sql.append(" banco = ? ");
            sql.append(" empresa = ? ");
            sql.append(" id_usuario = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, receita.getTipo().toString());
            stmt.setDouble(2, receita.getValor());
            stmt.setString(3, receita.getDescricao());
            stmt.setString(4, receita.getBanco());
            stmt.setString(5, receita.getEmpresa());
            stmt.setInt(5, receita.getIdFK());

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarInvestimento.res=" + res);

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
    public List<Receita> listar(Integer id) throws BancoDeDadosException {

        List<Receita> receitas = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM RECEITA";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Receita receita = new Receita();
                receita.setId(res.getInt("id_receita"));
                receita.setTipo(TipoDespesaEReceita.valueOf("tipo"));
                receita.setValor(res.getDouble("valor"));
                receita.setDescricao(res.getString("descricao"));
                receita.setBanco(res.getString("banco"));
                receita.setEmpresa(res.getString("empresa"));
                receita.setIdFK(res.getInt("id_usuario"));
                receitas.add(receita);
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
        return receitas;
    }
}
