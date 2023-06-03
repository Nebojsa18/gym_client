/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.Coach;
import domain.Gender;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Admin
 */
public class CoachTableModel extends AbstractTableModel{
    
    private final String[] columnNames = {"ID","Username", "Firstname", "Lastname", "Birthday","Gender","Phone"};
    private final List<Coach> coaches;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public CoachTableModel(List<Coach> coaches) {
        this.coaches = coaches;
    }

    
    
    @Override
    public int getRowCount() {
        if(coaches==null) return 0;
        return coaches.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Coach coach = coaches.get(rowIndex);
        switch (columnIndex) {
            case 0: return coach.getId();
            case 1: return coach.getUsername();
            case 2: return coach.getFirstname();
            case 3: return coach.getLastname();
            case 4: return coach.getBirthday();
            case 5: return coach.getGender();
            case 6: return coach.getPhone();
                
            default:
                return "n/a";
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public List<Coach> getMembers() {
        return coaches;
    }

    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            Coach coach = coaches.get(rowIndex);
            switch (columnIndex) {
                case 1: coach.setUsername((String)aValue);
                case 2: coach.setFirstname((String)aValue); break;
                case 3: coach.setLastname((String) aValue);break;
                case 4: coach.setBirthday(sdf.parse((String) aValue));
                case 5: coach.setGender((Gender) aValue);
                case 6: coach.setPhone((String) aValue);
                default:
            }
        } catch (ParseException ex) {
            Logger.getLogger(MemberTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Coach getCoach(int row) {
        return coaches.get(row);
    }
    
    
    
    
}
