/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.TrainingSession;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Admin
 */
public class TrainingSessionModel extends AbstractTableModel{

    private final String[] columnNames = {"ID", "Member", "Coach", "Date"};
    private final List<TrainingSession> sessions;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
    
    public TrainingSessionModel(List<TrainingSession> sessions) {
        this.sessions = sessions;
    }
    
    @Override
    public int getRowCount() {
        if(sessions!=null){
            return sessions.size();
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
        TrainingSession session = sessions.get(rowIndex);
        switch (columnIndex) {
            case 0: return session.getId();
            case 1: return session.getMember();
            case 2: return session.getCoach();
            case 3: return session.getDate();
            default:
                return "n/a";        
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public List<TrainingSession> getSessions() {
        return sessions;
    }

    public TrainingSession getSession(int row) {
        return sessions.get(row);
    }
    
    
    
}
