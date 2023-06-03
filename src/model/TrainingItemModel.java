/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.TrainingItem;
import domain.TrainingSession;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Admin
 */
public class TrainingItemModel extends AbstractTableModel{
    
    
    private final String[] columnNames = {"Excercise", "Reps"};
    private final List<TrainingItem> items;

    public TrainingItemModel(List<TrainingItem> items) {
        this.items = items;
    }

   
    

    @Override
    public int getRowCount() {
        if(items!=null) {
            return items.size();
        }else{
            return 0;
        }
    }
        

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TrainingItem item = items.get(rowIndex);
        switch (columnIndex) {
            case 0: return item.getExcercise();
            case 1: return item.getReps();
                
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public List<TrainingItem> getItems() {
        return items;
    }
    
//    public TrainingItem getItem(int row){
//        return items.get(row);
//    }
    
    public void add(TrainingItem item){
        items.add(item);
        fireTableDataChanged();
    }
    
    public void update(){
        fireTableDataChanged();
    }

    
    public void delete(int row){
        items.remove(row);
        fireTableDataChanged();
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    
    
}
