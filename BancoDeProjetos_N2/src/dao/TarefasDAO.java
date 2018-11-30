package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Tarefas;

public class TarefasDAO {

    private Connection conn;
    private PreparedStatement statement;
    private Statement st;
    private ResultSet rs;
    private ArrayList<Tarefas> listaTarefas = new ArrayList<Tarefas>();

    public TarefasDAO() {
        conn = new ConnectionFactory().getConnection();
    }

    public void inserir(Tarefas tarefas) {

        String sql = "INSERT INTO tarefas (descricao, dataInicio, dataFim, andamento, status, projetoID, responsavelID, nome) VALUES(?,?,?,?,?,?,?,?)";
        try {

            java.util.Date dataUtilInicio = tarefas.getDataInicio();
            java.sql.Date dataSqlInicio = new java.sql.Date(dataUtilInicio.getTime());
            java.util.Date dataUtilFim = tarefas.getDataFim();
            java.sql.Date dataSqlFim = new java.sql.Date(dataUtilFim.getTime());

            statement = conn.prepareStatement(sql);
            statement.setString(1, tarefas.getDescricao());
            statement.setDate(2, dataSqlInicio);
            statement.setDate(3, dataSqlFim);
            statement.setString(4, tarefas.getAndamento());
            statement.setString(5, tarefas.getStatus());
            statement.setInt(6, tarefas.getProjetoID());
            statement.setInt(7, tarefas.getResponsavelID());
            statement.setString(8, tarefas.getNome());
            statement.execute();
            statement.close();
        } catch (Exception erro) {
            throw new RuntimeException("erro 2: " + erro);
        }

    }

    public void alterar(Tarefas tarefas) {

        String sql = "UPDATE tarefas SET descricao = ?, dataInicio = ?, dataFim = ?, andamento = ?, status = ?, projetoID = ?, responsavelID = ?, nome =? WHERE ID  = ?";
        try {
            java.util.Date dataUtilInicio = tarefas.getDataInicio();
            java.sql.Date dataSqlInicio = new java.sql.Date(dataUtilInicio.getTime());
            java.util.Date dataUtilFim = tarefas.getDataFim();
            java.sql.Date dataSqlFim = new java.sql.Date(dataUtilFim.getTime());

            statement = conn.prepareStatement(sql);
            statement.setString(1, tarefas.getDescricao());
            statement.setDate(2, dataSqlInicio);
            statement.setDate(3, dataSqlFim);
            statement.setString(4, tarefas.getAndamento());
            statement.setString(5, tarefas.getStatus());
            statement.setInt(6, tarefas.getProjetoID());
            statement.setInt(7, tarefas.getResponsavelID());
            statement.setString(8, tarefas.getNome());
            statement.setInt(9, tarefas.getId());
            statement.execute();
            statement.close();
        } catch (Exception erro) {
            throw new RuntimeException("erro 3: " + erro);
        }

    }

    public void excluir(int id) {

        String sql = "DELETE FROM tarefas  WHERE ID=" + id;
        try {
            st = conn.createStatement();
            st.execute(sql);
            st.close();
        } catch (Exception erro) {
            throw new RuntimeException("erro 4: " + erro);
        }

    }

    public ArrayList<Tarefas> ListarTodos() {
        String sql = "SELECT tarefas.*, (select projeto.nome from projeto where projeto.id = tarefas.projetoId) "
                + "as 'Projeto', (select usuario.nome from usuario where usuario.id = tarefas.responsavelId)"
                + " as 'Responsavel' FROM tarefas";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Tarefas tarefas = new Tarefas();
                tarefas.setId(rs.getInt("ID"));
                tarefas.setDescricao(rs.getString("descricao"));
                tarefas.setDataInicio(rs.getDate("dataInicio"));
                tarefas.setDataFim(rs.getDate("dataFim"));
                tarefas.setAndamento(rs.getString("andamento"));
                tarefas.setStatus(rs.getString("status"));
                tarefas.setProjetoID(rs.getInt("projetoID"));
                tarefas.setResponsavelID(rs.getInt("responsavelID"));
                tarefas.setNomeProjeto(rs.getString("Projeto"));
                tarefas.setNomeResponsavel(rs.getString("Responsavel"));
                tarefas.setNome(rs.getString("nome"));
                listaTarefas.add(tarefas);
            }
        } catch (Exception erro) {
            throw new RuntimeException("erro 5: " + erro);
        }

        return listaTarefas;
    }

    public ArrayList<Tarefas> ListarPesquisados(String pesquisa) {
        String sql = "SELECT * FROM tarefas WHERE descricao LIKE '%" + pesquisa + "%' OR nome LIKE '%" + pesquisa + "%'";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Tarefas tarefas = new Tarefas();
                tarefas.setId(rs.getInt("ID"));
                tarefas.setDescricao(rs.getString("descricao"));
                tarefas.setDataInicio(rs.getDate("dataInicio"));
                tarefas.setDataFim(rs.getDate("dataFim"));
                tarefas.setAndamento(rs.getString("andamento"));
                tarefas.setStatus(rs.getString("status"));
                tarefas.setProjetoID(rs.getInt("projetoID"));
                tarefas.setResponsavelID(rs.getInt("responsavelID"));
                tarefas.setNome(rs.getString("nome"));
                listaTarefas.add(tarefas);
            }
        } catch (Exception erro) {
            throw new RuntimeException("erro 6: " + erro);
        }

        return listaTarefas;
    }
    
    public ArrayList<Tarefas> ListarKanban(String status, String idProjeto) {
        String sql = "SELECT *,(select projeto.nome from projeto where projeto.id = tarefas.projetoId) "
                + "as 'Projeto', (select usuario.nome from usuario where usuario.id = tarefas.responsavelId)"
                + " as 'Responsavel' FROM tarefas WHERE status LIKE '%" + status + "%' AND projetoID = " + idProjeto;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Tarefas tarefas = new Tarefas();
                tarefas.setId(rs.getInt("ID"));
                tarefas.setDescricao(rs.getString("descricao"));
                tarefas.setDataInicio(rs.getDate("dataInicio"));
                tarefas.setDataFim(rs.getDate("dataFim"));
                tarefas.setAndamento(rs.getString("andamento"));
                tarefas.setStatus(rs.getString("status"));
                tarefas.setProjetoID(rs.getInt("projetoID"));
                tarefas.setResponsavelID(rs.getInt("responsavelID"));
                tarefas.setNomeProjeto(rs.getString("Projeto"));
                tarefas.setNomeResponsavel(rs.getString("Responsavel"));
                tarefas.setNome(rs.getString("nome"));
                listaTarefas.add(tarefas);
            }
        } catch (Exception erro) {
            throw new RuntimeException("erro 200: " + erro);
        }

        return listaTarefas;
    }
    
    public Tarefas ListarSelecionado (int id){
         String sql = "SELECT * FROM tarefas WHERE ID = " + id;
         Tarefas tarefas = new Tarefas();
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                
                tarefas.setId(rs.getInt("ID"));
                tarefas.setDescricao(rs.getString("descricao"));
                tarefas.setDataInicio(rs.getDate("dataInicio"));
                tarefas.setDataFim(rs.getDate("dataFim"));
                tarefas.setAndamento(rs.getString("andamento"));
                tarefas.setStatus(rs.getString("status"));
                tarefas.setProjetoID(rs.getInt("projetoID"));
                tarefas.setResponsavelID(rs.getInt("responsavelID"));
                tarefas.setNome(rs.getString("nome"));
                
            }
        } catch (Exception erro) {
            throw new RuntimeException("erro 200: " + erro);
        }

        return tarefas;
    }
    
    public boolean VerificarTitulo(String titulo, int projetoID){
        String encontrado ="";
        String sql = "SELECT nome FROM tarefas WHERE nome LIKE '" + titulo+"' AND projetoID LIKE '"+projetoID+"'" ;
        try{
            st = conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
               encontrado = rs.getString("nome");
            }
        }
        catch(Exception erro){
            throw new RuntimeException ("erro 500: "+erro);
        }
        
        return !("".equals(encontrado));
        
    }
    
}
