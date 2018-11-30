
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Usuario;



public class UsuarioDAO {
    private Connection conn;
    private PreparedStatement statement;
    private Statement st;
    private ResultSet rs;
    private ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>(); 
    
    public UsuarioDAO(){
        conn = new ConnectionFactory().getConnection();
    }
    
    public void inserir (Usuario usuario){
        
        String sql="INSERT INTO usuario (nome, telefone, email, login, senha) VALUES(?,?,?,?,?)";
        try{
            
            statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2,usuario.getTelefone());
            statement.setString(3,usuario.getEmail() );
            statement.setString(4,usuario.getLogin());
            statement.setString(5, usuario.getSenha());
            statement.execute();
            statement.close();
            }
        catch(Exception erro){
            throw new RuntimeException ("erro 2: "+erro);
        }
              
    }
    
    public void alterar (Usuario usuario){
        
        String sql="UPDATE usuario SET nome = ?, telefone = ?, email = ?, login = ?, senha = ? WHERE ID  = ?";
        try{
            
            
            statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2,usuario.getTelefone());
            statement.setString(3,usuario.getEmail() );
            statement.setString(4,usuario.getLogin());
            statement.setString(5, usuario.getSenha());
            statement.setInt(6, usuario.getId());
            statement.execute();
            statement.close();
        }
        catch(Exception erro){
            throw new RuntimeException ("erro 3: "+erro);
        }
              
    }
    
    public void excluir (int id){
        
        String sql="DELETE FROM usuario  WHERE ID=" + id;
        try{
            st= conn.createStatement();
            st.execute(sql);
            st.close();
        }
        catch(Exception erro){
            throw new RuntimeException ("erro 4: "+erro);
        }
              
    }
    
    public ArrayList<Usuario> ListarTodos(){
        String sql="SELECT * FROM usuario";
        try{
            st = conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                Usuario usuario  = new Usuario();
                usuario.setId(rs.getInt("ID"));
                usuario.setNome(rs.getString("nome"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setEmail(rs.getString("email"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                listaUsuario.add(usuario);
            }
        }
        catch(Exception erro){
            throw new RuntimeException ("erro 5: "+erro);
        }
        
        return listaUsuario;
    }
    
    public ArrayList<Usuario> ListarPesquisados( String pesquisa){
        String sql="SELECT * FROM usuario WHERE nome LIKE '%"+pesquisa+"%'";
        try{
            st = conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                Usuario usuario  = new Usuario();
                usuario.setId(rs.getInt("ID"));
                usuario.setNome(rs.getString("nome"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setEmail(rs.getString("email"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                listaUsuario.add(usuario);
            }
        }
        catch(Exception erro){
            throw new RuntimeException ("erro 5: "+erro);
        }
        
        return listaUsuario;
    }
    
    public ArrayList<String> ListarUsuarios(){
        ArrayList<String> listaUsuario= new ArrayList<String>();
        String sql="SELECT nome FROM usuario";
        try{
            st = conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
               listaUsuario.add(rs.getString("nome"));
            }
        }
        catch(Exception erro){
            throw new RuntimeException ("erro 101: "+erro);
        }
        return listaUsuario;
    }
    
    public int BuscarID(String nome){
        int idEncontrado = 0;
        String sql = "SELECT ID FROM usuario WHERE nome LIKE '%" + nome +"%'";
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
        String sql = "SELECT nome FROM usuario WHERE ID LIKE " + idEnviado ;
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
    
    public boolean VerificarLogin(String login){
        String encontrado ="";
        String sql = "SELECT nome FROM usuario WHERE login LIKE '" + login+"'" ;
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
        
        if(encontrado =="")
            return false;
        else
            return true;
    }
    
    public boolean VerificarAcesso(String login, String senha){
        String encontrado ="";
        String sql = "SELECT nome FROM usuario WHERE login LIKE '" + login+"' AND senha LIKE '"+senha+"'" ;
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
