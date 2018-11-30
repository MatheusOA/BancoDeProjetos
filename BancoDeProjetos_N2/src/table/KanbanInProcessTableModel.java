/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import dao.TarefasDAO;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.Tarefas;

public class KanbanInProcessTableModel extends AbstractTableModel {

    public static final int COL_ID_KANBAN = 0;
    public ArrayList<Tarefas>  lista;
    
    public KanbanInProcessTableModel (ArrayList<Tarefas> list){
        lista= new ArrayList<Tarefas>(list);
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }
    
    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tarefas tarefas =lista.get(rowIndex);
        if(columnIndex == COL_ID_KANBAN) return "<html>" + tarefas.getId() + " - " + tarefas.getNome() + "<br/>Responsável: " + tarefas.getNomeResponsavel() + "<br/>Andamento: " + tarefas.getAndamento()+ "%</html>";
        return "";
    }
    
    @Override
    public String getColumnName(int columnIndex){
        
        if(columnIndex == COL_ID_KANBAN) return "Tarefas";
        return "";
    }
}

