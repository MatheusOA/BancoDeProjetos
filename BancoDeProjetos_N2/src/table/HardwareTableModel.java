
package table;

import model.Hardware;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class HardwareTableModel extends AbstractTableModel {

    public static final int COL_ID_HARDWARE = 0;
    public static final int COL_NOME_HARDWARE= 1;
    public static final int COL_TIPO_HARDWARE = 2;
    public static final int COL_DETALHES_HARDWARE = 3;
    public ArrayList<Hardware>  lista;
    
    public HardwareTableModel(ArrayList<Hardware> list){
        lista= new ArrayList<Hardware>(list);
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Hardware hardware =lista.get(rowIndex);
        
        if(columnIndex == COL_ID_HARDWARE) return hardware.getId();
        if(columnIndex == COL_NOME_HARDWARE) return hardware.getNome();
        if(columnIndex == COL_TIPO_HARDWARE) return hardware.getTipo();
        if(columnIndex == COL_DETALHES_HARDWARE) return hardware.getDetalhes();
        return "";
    }
    
    @Override
    public String getColumnName(int columnIndex){
        
        if(columnIndex == COL_ID_HARDWARE) return "ID";
        if(columnIndex == COL_NOME_HARDWARE) return "Nome";
        if(columnIndex == COL_TIPO_HARDWARE) return "Tipo";
        if(columnIndex == COL_DETALHES_HARDWARE) return "Detalhes";
                    
        return "";
    }

   
    
}
