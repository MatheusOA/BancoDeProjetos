
package table;

import model.Usuario;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class UsuarioTableModel extends AbstractTableModel {

    public static final int COL_ID_USUARIO = 0;
    public static final int COL_NOME_USUARIO= 1;
    public static final int COL_TELEFONE_USUARIO = 2;
    public static final int COL_EMAIL_USUARIO = 3;
    public static final int COL_LOGIN_USUARIO = 4;
    public static final int COL_SENHA_USUARIO= 5;
    public ArrayList<Usuario>  lista;
    
    public UsuarioTableModel(ArrayList<Usuario> list){
        lista= new ArrayList<Usuario>(list);
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario usuario =lista.get(rowIndex);
                
        if(columnIndex == COL_ID_USUARIO) return usuario.getId();
        if(columnIndex == COL_NOME_USUARIO) return usuario.getNome();
        if(columnIndex == COL_TELEFONE_USUARIO) return usuario.getTelefone();
        if(columnIndex == COL_EMAIL_USUARIO) return usuario.getEmail();
        if(columnIndex == COL_LOGIN_USUARIO) return usuario.getLogin();
        if(columnIndex == COL_SENHA_USUARIO) return usuario.getSenha();
        return "";
    }
    
    @Override
    public String getColumnName(int columnIndex){
        
        if(columnIndex == COL_ID_USUARIO) return "ID";
        if(columnIndex == COL_NOME_USUARIO) return "Nome";
        if(columnIndex == COL_TELEFONE_USUARIO) return "Telefone";
        if(columnIndex == COL_EMAIL_USUARIO) return "E-mail";
        if(columnIndex == COL_LOGIN_USUARIO) return "Login";
        if(columnIndex == COL_SENHA_USUARIO) return "Senha";
                    
        return "";
    }

   
    
}
