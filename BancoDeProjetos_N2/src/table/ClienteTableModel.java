
package table;

import model.Cliente;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ClienteTableModel extends AbstractTableModel {

    public static final int COL_ID_CLIENTE = 0;
    public static final int COL_NOME_CLIENTE = 1;
    public static final int COL_CNPJ_CLIENTE = 2;
    public static final int COL_ESTADO_CLIENTE = 3;
    public static final int COL_CIDADE_CLIENTE = 4;
    public static final int COL_RUA_CLIENTE = 5;
    public static final int COL_NUMERO_CLIENTE = 6;
    public static final int COL_TIPO_CLIENTE = 7;
    public ArrayList<Cliente>  lista;
    
    public ClienteTableModel(ArrayList<Cliente> list){
        lista= new ArrayList<Cliente>(list);
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente =lista.get(rowIndex);
 
        if(columnIndex == COL_ID_CLIENTE) return cliente.getId();
        if(columnIndex == COL_NOME_CLIENTE) return cliente.getNome();
        if(columnIndex == COL_CNPJ_CLIENTE) return cliente.getCnpj();
        if(columnIndex == COL_ESTADO_CLIENTE) return cliente.getEstado();
        if(columnIndex == COL_CIDADE_CLIENTE) return cliente.getCidade();
        if(columnIndex == COL_RUA_CLIENTE) return cliente.getRua();
        if(columnIndex == COL_NUMERO_CLIENTE) return cliente.getNumero();
        if(columnIndex == COL_TIPO_CLIENTE ) return cliente.getTipo();
        return "";
    }
    
    @Override
    public String getColumnName(int columnIndex){
        
        if(columnIndex == COL_ID_CLIENTE) return "ID";
        if(columnIndex == COL_NOME_CLIENTE) return "Nome";
        if(columnIndex == COL_CNPJ_CLIENTE) return "CNPJ";
        if(columnIndex == COL_ESTADO_CLIENTE) return "Estado";
        if(columnIndex == COL_CIDADE_CLIENTE) return "Cidade";
        if(columnIndex == COL_RUA_CLIENTE) return "Rua";
        if(columnIndex == COL_NUMERO_CLIENTE) return "Numero";
        if(columnIndex == COL_TIPO_CLIENTE ) return "Tipo";
                    
        return "";
    }

   
    
}
