
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Hardware;



public class HardwareDAO {
    private Connection conn;
    private PreparedStatement statement;
    private Statement st;
    private ResultSet rs;
    private ArrayList<Hardware> listaHardware = new ArrayList<Hardware>(); 
    
    public HardwareDAO(){
        conn = new ConnectionFactory().getConnection();
    }
    
    public void inserir (Hardware hardware){
        
        String sql="INSERT INTO hardware (nome, tipo, detalhes) VALUES(?,?,?)";
        try{
            
            statement = conn.prepareStatement(sql);
            statement.setString(1, hardware.getNome());
            statement.setString(2,hardware.getTipo());
            statement.setString(3,hardware.getDetalhes());
            statement.execute();
            statement.close();
            }
        catch(Exception erro){
            throw new RuntimeException ("erro 2: "+erro);
        }
              
    }
    
    public void alterar (Hardware hardware){
        
        String sql="UPDATE hardware SET nome = ?, tipo = ?, detalhes = ? WHERE ID  = ?";
        try{
            
            
            statement = conn.prepareStatement(sql);
            statement.setString(1, hardware.getNome());
            statement.setString(2,hardware.getTipo());
            statement.setString(3,hardware.getDetalhes() );
            statement.setInt(4,hardware.getId());
            statement.execute();
            statement.close();
        }
        catch(Exception erro){
            throw new RuntimeException ("erro 3: "+erro);
        }
              
    }
    
    public void excluir (int id){
        
        String sql="DELETE FROM hardware  WHERE ID=" + id;
        try{
            st= conn.createStatement();
            st.execute(sql);
            st.close();
        }
        catch(Exception erro){
            throw new RuntimeException ("erro 4: "+erro);
        }
              
    }
    
    public ArrayList<Hardware> ListarTodos(){
        String sql="SELECT * FROM hardware";
        try{
            st = conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                Hardware hardware  = new Hardware();
                hardware.setId(rs.getInt("ID"));
                hardware.setNome(rs.getString("nome"));
                hardware.setTipo(rs.getString("tipo"));
                hardware.setDetalhes(rs.getString("detalhes"));
                listaHardware.add(hardware);
            }
        }
        catch(Exception erro){
            throw new RuntimeException ("erro 5: "+erro);
        }
        
        return listaHardware;
    }
    
    public ArrayList<Hardware> ListarPesquisados( String pesquisa){
        String sql="SELECT * FROM hardware WHERE nome LIKE '%"+pesquisa+"%'";
                try{
            st = conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                Hardware hardware  = new Hardware();
                hardware.setId(rs.getInt("ID"));
                hardware.setNome(rs.getString("nome"));
                hardware.setTipo(rs.getString("tipo"));
                hardware.setDetalhes(rs.getString("detalhes"));
                listaHardware.add(hardware);
            }
        }
        catch(Exception erro){
            throw new RuntimeException ("erro 5: "+erro);
        }
        
        return listaHardware;
    }
    
    public ArrayList<String> ListarHardwares(){
        ArrayList<String> listaHardware = new ArrayList<String>();
        String sql="SELECT nome FROM hardware";
        try{
            st = conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
               listaHardware.add(rs.getString("nome"));
            }
        }
        catch(Exception erro){
            throw new RuntimeException ("erro 101: "+erro);
        }
        return listaHardware;
    }
    
    public int BuscarID(String nome){
        int idEncontrado = 0;
        String sql = "SELECT ID FROM hardware WHERE nome LIKE '%" + nome +"%'";
        try{
            st = conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
               idEncontrado = rs.getInt("ID");
            }
        }
        catch(Exception erro){
            throw new RuntimeException ("erro 102: "+erro);
        }
        return idEncontrado;
    }
    
    public String DevolverNome(int idEnviado){
        String nomeSolicitado ="";
        String sql = "SELECT nome FROM hardware WHERE ID LIKE " + idEnviado ;
        try{
            st = conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
               nomeSolicitado = rs.getString("nome");
            }
        }
        catch(Exception erro){
            throw new RuntimeException ("erro 103: "+erro);
        }
        return nomeSolicitado;
    }
    
    public boolean VerificarTitulo(String titulo){
        String encontrado ="";
        String sql = "SELECT nome FROM hardware WHERE nome LIKE '" + titulo+"'" ;
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
