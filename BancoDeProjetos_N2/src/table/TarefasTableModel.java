
package table;

import model.Tarefas;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TarefasTableModel extends AbstractTableModel {

    public static final int COL_ID_TAREFAS = 0;
    public static final int COL_NOME_TAREFAS =1;
    public static final int COL_DESCRICAO_TAREFAS = 2;
    public static final int COL_DATAINICIO_TAREFAS = 3;
    public static final int COL_DATAFIM_TAREFAS = 4;
    public static final int COL_ANDAMENTO_TAREFAS = 5;
    public static final int COL_STATUS_TAREFAS = 6;
    public static final int COL_NOMEPROJETO_TAREFAS = 7;
    public static final int COL_NOMERESPONSAVEL_TAREFAS = 8;
    public ArrayList<Tarefas>  lista;
    
    public TarefasTableModel(ArrayList<Tarefas> list){
        lista= new ArrayList<Tarefas>(list);
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tarefas tarefas =lista.get(rowIndex);
        
        if(columnIndex == COL_ID_TAREFAS) return tarefas.getId();
        if(columnIndex == COL_NOME_TAREFAS) return tarefas.getNome();
        if(columnIndex == COL_DESCRICAO_TAREFAS) return tarefas.getDescricao();
        if(columnIndex == COL_DATAINICIO_TAREFAS) return tarefas.getDataInicio();
        if(columnIndex == COL_DATAFIM_TAREFAS) return tarefas.getDataFim();
        if(columnIndex == COL_ANDAMENTO_TAREFAS) return tarefas.getAndamento();
        if(columnIndex == COL_STATUS_TAREFAS) return tarefas.getStatus();
        if(columnIndex == COL_NOMEPROJETO_TAREFAS) return tarefas.getNomeProjeto();
        if(columnIndex == COL_NOMERESPONSAVEL_TAREFAS) return tarefas.getNomeResponsavel();
      
        return "";
    }
    
    @Override
    public String getColumnName(int columnIndex){
        
        if(columnIndex == COL_ID_TAREFAS) return "ID";
        if(columnIndex == COL_NOME_TAREFAS) return "Título da Tarefa";
        if(columnIndex == COL_DESCRICAO_TAREFAS) return "Descrição";
        if(columnIndex == COL_DATAINICIO_TAREFAS) return "Data de Início";
        if(columnIndex == COL_DATAFIM_TAREFAS) return "Data de Fim";
        if(columnIndex == COL_ANDAMENTO_TAREFAS) return "Andamento";
        if(columnIndex == COL_STATUS_TAREFAS) return "Status";
        if(columnIndex == COL_NOMEPROJETO_TAREFAS) return "Projeto";
        if(columnIndex == COL_NOMERESPONSAVEL_TAREFAS) return "Responsavel";
                    
        return "";
    }
    
}
