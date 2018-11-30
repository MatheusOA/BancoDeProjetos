
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Cliente;



public class ClienteDAO {
    private Connection conn;
    private PreparedStatement statement;
    private Statement st;
    private ResultSet rs;
    private ArrayList<Cliente> listaCliente = new ArrayList<Cliente>(); 
    
    public ClienteDAO(){
        conn = new ConnectionFactory().getConnection();
    }
    
    public void inserir (Cliente cliente){
        
        String sql="INSERT INTO cliente (nome, cnpj, estado, cidade, rua, numero, tipo) VALUES(?,?,?,?,?,?,?)";
        try{
            
            statement = conn.prepareStatement(sql);
            statement.setString(1, cliente.getNome());
            statement.setString(2,cliente.getCnpj());
            statement.setString(3,cliente.getEstado() );
            statement.setString(4,cliente.getCidade());
            statement.setString(5, cliente.getRua());
            statement.setInt(6,cliente.getNumero());
            statement.setString(7, cliente.getTipo());
            statement.execute();
            statement.close();
            }
        catch(Exception erro){
            throw new RuntimeException ("erro 2: "+erro);
        }
              
    }
    
    public void alterar (Cliente cliente){
        
        String sql="UPDATE cliente SET nome = ?, cnpj = ?, estado = ?, cidade = ?, rua = ?, numero = ?, tipo = ? WHERE ID  = ?";
        try{
            
            
            statement = conn.prepareStatement(sql);
            statement.setString(1, cliente.getNome());
            statement.setString(2,cliente.getCnpj());
            statement.setString(3,cliente.getEstado() );
            statement.setString(4,cliente.getCidade());
            statement.setString(5, cliente.getRua());
            statement.setInt(6,cliente.getNumero());
            statement.setString(7, cliente.getTipo());
            statement.setInt(8, cliente.getId());
            statement.execute();
            statement.close();
        }
        catch(Exception erro){
            throw new RuntimeException ("erro 3: "+erro);
        }
              
    }
    
    public void excluir (int id){
        
        String sql="DELETE FROM cliente  WHERE ID=" + id;
        try{
            st= conn.createStatement();
            st.execute(sql);
            st.close();
        }
        catch(Exception erro){
            throw new RuntimeException ("erro 4: "+erro);
        }
              
    }
    
    public ArrayList<Cliente> ListarTodos(){
        String sql="SELECT * FROM cliente";
        try{
            st = conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                Cliente cliente  = new Cliente();
                cliente.setId(rs.getInt("ID"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCnpj(rs.getString("cnpj"));
                cliente.setEstado(rs.getString("estado"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setRua(rs.getString("rua"));
                cliente.setNumero(rs.getInt("numero"));
                cliente.setTipo(rs.getString("tipo"));
                listaCliente.add(cliente);
            }
        }
        catch(Exception erro){
            throw new RuntimeException ("erro 5: "+erro);
        }
        
        return listaCliente;
    }
    
    public ArrayList<Cliente> ListarPesquisados( String pesquisa){
        String sql="SELECT * FROM cliente WHERE nome LIKE '%"+pesquisa+"%'";
        try{
            st = conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                Cliente cliente  = new Cliente();
                cliente.setId(rs.getInt("ID"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCnpj(rs.getString("cnpj"));
                cliente.setEstado(rs.getString("estado"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setRua(rs.getString("rua"));
                cliente.setNumero(rs.getInt("numero"));
                cliente.setTipo(rs.getString("tipo"));
                listaCliente.add(cliente);
            }
        }
        catch(Exception erro){
            throw new RuntimeException ("erro 6: "+erro);
        }
        
        return listaCliente;
    }
    
    
    public ArrayList<String> ListarClientesFinais(){
        ArrayList<String> listaClientesFinais = new ArrayList<String>();
        String sql="SELECT nome FROM cliente WHERE tipo LIKE 'Cliente Final'";
        try{
            st = conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
               listaClientesFinais.add(rs.getString("nome"));
            }
        }
        catch(Exception erro){
            throw new RuntimeException ("erro 101: "+erro);
        }
        return listaClientesFinais;
    }
    
    public ArrayList<String> ListarClientesIntegradores(){
        ArrayList<String> listaClientesIntegradores = new ArrayList<String>();
        String sql="SELECT nome FROM cliente WHERE tipo LIKE 'Cliente Integrador'";
        try{
            st = conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
               listaClientesIntegradores.add(rs.getString("nome"));
            }
        }
        catch(Exception erro){
            throw new RuntimeException ("erro 102: "+erro);
        }
        return listaClientesIntegradores;
    }
    
    public int BuscarID(String nome){
        int idEncontrado = 0;
        String sql = "SELECT ID FROM cliente WHERE nome LIKE '%" + nome +"%'";
        try{
            st = conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
               idEncontrado = rs.getInt("ID");
            }
        }
        catch(Exception erro){
            throw new RuntimeException ("erro 103: "+erro);
        }
        return idEncontrado;
    }
    
    public String DevolverNome(int idEnviado){
        String nomeSolicitado ="";
        String sql = "SELECT nome FROM cliente WHERE ID LIKE " + idEnviado ;
        try{
            st = conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
               nomeSolicitado = rs.getString("nome");
            }
        }
        catch(Exception erro){
            throw new RuntimeException ("erro 104: "+erro);
        }
        return nomeSolicitado;
    }
    
    public boolean VerificarCnpj(String cnpj){
        String encontrado ="";
        String sql = "SELECT nome FROM cliente WHERE cnpj LIKE '" + cnpj+"'" ;
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
