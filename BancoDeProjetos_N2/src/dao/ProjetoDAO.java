package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Projeto;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProjetoDAO {

    private Connection conn;
    private PreparedStatement statement;
    private Statement st;
    private ResultSet rs;
    private ArrayList<Projeto> listaProjeto = new ArrayList<Projeto>();

    public ProjetoDAO() {
        conn = new ConnectionFactory().getConnection();
    }

    public void inserir(Projeto projeto) {

        String sql = "INSERT INTO projeto (descricao, dataInicio, dataFim, clienteIntegrador, clienteFinal, custoEstimado, andamento, hardwareID, nome) VALUES(?,?,?,?,?,?,?,?, ?)";
        try {

            java.util.Date dataUtilInicio = projeto.getDataInicio();
            java.sql.Date dataSqlInicio = new java.sql.Date(dataUtilInicio.getTime());
            java.util.Date dataUtilFim = projeto.getDataFim();
            java.sql.Date dataSqlFim=new java.sql.Date(dataUtilFim.getTime());
            statement = conn.prepareStatement(sql);

            statement.setString(1, projeto.getDescricao());
            statement.setDate(2, dataSqlInicio);
            statement.setDate(3, dataSqlFim);
            statement.setInt(4, projeto.getClienteIntegrador());
            statement.setInt(5, projeto.getClienteFinal());
            statement.setFloat(6, projeto.getCustoEstimado());
            statement.setInt(7, projeto.getAndamento());
            statement.setInt(8, projeto.getHardwareID());
            statement.setString(9, projeto.getNome());
            statement.execute();
            statement.close();
        } catch (Exception erro) {
            throw new RuntimeException("erro 2: " + erro);
        }

    }

    public void alterar(Projeto projeto) {

        String sql = "UPDATE projeto SET descricao = ?, dataInicio = ?, dataFim = ?, clienteIntegrador = ?, clienteFinal = ?, custoEstimado = ?, andamento = ?, hardwareID = ?, nome =? WHERE ID  = ?";
        try {
            java.util.Date dataUtilInicio = projeto.getDataInicio();
            java.sql.Date dataSqlInicio = new java.sql.Date(dataUtilInicio.getTime());
            java.util.Date dataUtilFim = projeto.getDataFim();
            java.sql.Date dataSqlFim = new java.sql.Date(dataUtilFim.getTime());

            statement = conn.prepareStatement(sql);
            statement.setString(1, projeto.getDescricao());
            statement.setDate(2, dataSqlInicio);
            statement.setDate(3, dataSqlFim);
            statement.setInt(4, projeto.getClienteIntegrador());
            statement.setInt(5, projeto.getClienteFinal());
            statement.setFloat(6, projeto.getCustoEstimado());
            statement.setInt(7, projeto.getAndamento());
            statement.setInt(8, projeto.getHardwareID());
            statement.setString(9, projeto.getNome());
            statement.setInt(10, projeto.getId());
            statement.execute();
            statement.close();
        } catch (Exception erro) {
            throw new RuntimeException("erro 3: " + erro);
        }

    }

    public void excluir(int id) {

        String sql = "DELETE FROM projeto  WHERE ID=" + id;
        try {
            st = conn.createStatement();
            st.execute(sql);
            st.close();
        } catch (Exception erro) {
            throw new RuntimeException("erro 4: " + erro);
        }

    }

    public ArrayList<Projeto> ListarTodos() {
        String sql = "SELECT projeto.ID, projeto.descricao, projeto.dataInicio, projeto.dataFim, projeto.clienteIntegrador, projeto.clienteFinal, projeto.hardwareid,"
                + "(select cliente.nome from cliente where cliente.tipo = 'Cliente Integrador' AND "
                + "cliente.id = projeto.clienteintegrador) as 'Integrador', "
                + "(select cliente.nome from cliente where cliente.tipo ='Cliente Final' AND "
                + "cliente.id = projeto.clientefinal) as 'Final',"
                + "projeto.custoestimado, projeto.andamento,"
                + "(select hardware.nome from hardware where hardware.id = projeto.hardwareid) as 'Hardware', nome FROM projeto";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setId(rs.getInt("ID"));
                projeto.setDescricao(rs.getString("descricao"));
                projeto.setDataInicio(rs.getDate("dataInicio"));
                projeto.setDataFim(rs.getDate("dataFim"));
                projeto.setClienteIntegrador(rs.getInt("clienteIntegrador"));
                projeto.setClienteFinal(rs.getInt("clienteFinal"));
                projeto.setCustoEstimado(rs.getFloat("custoEstimado"));
                projeto.setAndamento(rs.getInt("andamento"));
                projeto.setHardwareID(rs.getInt("hardwareID"));
                projeto.setNomeFinal(rs.getString("Final"));
                projeto.setNomeIntegrador(rs.getString("Integrador"));
                projeto.setNomeHardware(rs.getString("Hardware"));
                projeto.setNome(rs.getString("nome"));
                listaProjeto.add(projeto);
            }
        } catch (Exception erro) {
            throw new RuntimeException("erro 5: " + erro);
        }

        return listaProjeto;
    }

    public ArrayList<Projeto> ListarPesquisados(String pesquisa) {
        String sql = "SELECT * FROM projeto WHERE descricao LIKE '%" + pesquisa + "%' OR nome LIKE '%" + pesquisa + "%'";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setId(rs.getInt("ID"));
                projeto.setDescricao(rs.getString("descricao"));
                projeto.setDataInicio(rs.getDate("dataInicio"));
                projeto.setDataFim(rs.getDate("dataFim"));
                projeto.setClienteIntegrador(rs.getInt("clienteIntegrador"));
                projeto.setClienteFinal(rs.getInt("clienteFinal"));
                projeto.setCustoEstimado(rs.getFloat("custoEstimado"));
                projeto.setAndamento(rs.getInt("andamento"));
                projeto.setHardwareID(rs.getInt("hardwareID"));
                projeto.setNome(rs.getString("nome"));
                listaProjeto.add(projeto);
            }
        } catch (Exception erro) {
            throw new RuntimeException("erro 5: " + erro);
        }

        return listaProjeto;
    }

    public ArrayList<String> ListarProjetos() {
        ArrayList<String> listaProjeto = new ArrayList<String>();
        String sql = "SELECT nome FROM projeto";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                listaProjeto.add(rs.getString("nome"));
            }
        } catch (Exception erro) {
            throw new RuntimeException("erro 101: " + erro);
        }
        return listaProjeto;
    }

    public int BuscarID(String nome) {
        int idEncontrado = 0;
        String sql = "SELECT ID FROM projeto WHERE nome LIKE '%" + nome + "%'";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                idEncontrado = rs.getInt("ID");
            }
        } catch (Exception erro) {
            throw new RuntimeException("erro 102: " + erro);
        }
        return idEncontrado;
    }

    public String DevolverNome(int idEnviado) {
        String nomeSolicitado = "";
        String sql = "SELECT nome FROM projeto WHERE ID LIKE " + idEnviado;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                nomeSolicitado = rs.getString("nome");
            }
        } catch (Exception erro) {
            throw new RuntimeException("erro 103: " + erro);
        }
        return nomeSolicitado;
    }
    
    public boolean VerificarTitulo(String titulo){
        String encontrado ="";
        String sql = "SELECT nome FROM projeto WHERE nome LIKE '" + titulo+"'" ;
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
