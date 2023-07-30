package repository;

import enumerators.TipoDespesaEReceita;
import exceptions.BancoDeDadosException;
import modelos.Despesa;
import modelos.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DespesaRepository implements Repositorio<Integer, Despesa > {


    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        try {
            String sql = "SELECT seq_despesa.nextval mysequence from DUAL";
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if (res.next()) {
                return res.getInt("mysequence");
            }

            return null;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }

    @Override
    public Despesa adicionar(Despesa despesa) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            despesa.setId(proximoId);

            String sql = "INSERT INTO DESPESA\n" +
                    "(ID_DESPESA, TIPO, VALOR, DESCRICAO, DATA_PAGAMENTO, ID_USUARIO )\n" +
                    "VALUES(?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, despesa.getId());
            stmt.setString(2, despesa.getTipo().toString());
            stmt.setDouble(3,despesa.getValor());
            stmt.setString(4, despesa.getDescricao()); // RESIDENCIAL(1) //1
            stmt.setString(5, despesa.getDataPagamento());
            stmt.setInt(6, despesa.getIdFK());

            int res = stmt.executeUpdate();
            System.out.println("adicionarDespesa.res=" + res);
            return despesa;
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

            String sql = "DELETE FROM DESPESA WHERE ID_DESPESA = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerDespesaPorId.res=" + res);

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
    public boolean editar(Integer id, Despesa despesa) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE DESPESA SET ");
            sql.append(" tipo = ?,");
            sql.append(" valor = ?,");
            sql.append(" descricao = ? ");
            sql.append(" data_pagamento = ? ");
            sql.append(" id_usuario = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, despesa.getTipo().toString());
            stmt.setDouble(2, despesa.getValor());
            stmt.setString(3, despesa.getDescricao());
            stmt.setString(4, despesa.getDataPagamento() );
            stmt.setInt(5, despesa.getIdFK());


            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarDespesa.res=" + res);

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
    public List<Despesa> listar() throws BancoDeDadosException {
        List<Despesa> despesas = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM DESPESA";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Despesa despesa = new Despesa();
                despesa.setId(res.getInt("id_despesa"));
                despesa.setTipo(TipoDespesaEReceita.valueOf(res.getString("Tipo")));
                despesa.setValor(res.getDouble("valor"));
                despesa.setDecricao(res.getString("descricao"));
                despesa.setDataPagamento(("data_pagamento"));
                despesa.setIdFK(res.getInt("id_usuario"));
                despesas.add(despesa);
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
        return despesas;

    }
}
