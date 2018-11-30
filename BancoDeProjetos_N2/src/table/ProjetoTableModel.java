
package table;

import model.Projeto;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ProjetoTableModel extends AbstractTableModel {

    public static final int COL_ID_PROJETO = 0;
    public static final int COL_NOME_PROJETO = 1;
    public static final int COL_DESCRICAO_PROJETO = 2;
    public static final int COL_DATAINICIO_PROJETO = 3;
    public static final int COL_DATAFIM_PROJETO = 4;
    public static final int COL_NOMEINTEGRADOR_PROJETO = 5;
    public static final int COL_NOMEFINAL_PROJETO = 6;
    public static final int COL_CUSTOESTIMADO_PROJETO = 7;
    public static final int COL_ANDAMENTO_PROJETO = 8;
    public static final int COL_NOMEHARDWARE_PROJETO = 9;
    public ArrayList<Projeto>  lista;
    
    public ProjetoTableModel(ArrayList<Projeto> list){
        lista= new ArrayList<Projeto>(list);
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Projeto projeto =lista.get(rowIndex);
        if(columnIndex == COL_ID_PROJETO) return projeto.getId();
        if(columnIndex == COL_NOME_PROJETO) return projeto.getNome();
        if(columnIndex == COL_DESCRICAO_PROJETO) return projeto.getDescricao();
        if(columnIndex == COL_DATAINICIO_PROJETO) return projeto.getDataInicio();
        if(columnIndex == COL_DATAFIM_PROJETO) return projeto.getDataFim();
        if(columnIndex == COL_NOMEINTEGRADOR_PROJETO) return projeto.getNomeIntegrador();
        if(columnIndex == COL_NOMEFINAL_PROJETO) return projeto.getNomeFinal();
        if(columnIndex == COL_CUSTOESTIMADO_PROJETO) return projeto.getCustoEstimado();
        if(columnIndex == COL_ANDAMENTO_PROJETO) return projeto.getAndamento();
        if(columnIndex == COL_NOMEHARDWARE_PROJETO) return projeto.getNomeHardware();
      
        return "";
    }
    
    @Override
    public String getColumnName(int columnIndex){
        
        if(columnIndex == COL_ID_PROJETO) return "ID";
        if(columnIndex == COL_NOME_PROJETO) return "Título do Projeto";
        if(columnIndex == COL_DESCRICAO_PROJETO) return "Descrição";
        if(columnIndex == COL_DATAINICIO_PROJETO) return "Data de Início";
        if(columnIndex == COL_DATAFIM_PROJETO) return "Data de Fim";
        if(columnIndex == COL_NOMEINTEGRADOR_PROJETO) return "Cliente Integrador";
        if(columnIndex == COL_NOMEFINAL_PROJETO) return "Cliente Final";
        if(columnIndex == COL_CUSTOESTIMADO_PROJETO) return "Custo Estimado";
        if(columnIndex == COL_ANDAMENTO_PROJETO) return "Andamento(%)";
        if(columnIndex == COL_NOMEHARDWARE_PROJETO) return "Hardware";
      
        return "";
    }
    
}
