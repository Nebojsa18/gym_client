/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.Gender;
import domain.Member;
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
public class MemberTableModel extends AbstractTableModel{
    
    private final String[] columnNames = {"ID", "Firstname", "Lastname", "Birthday","Gender","Phone"};
    private final List<Member> members;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");

    public MemberTableModel(List<Member> members) {
        this.members = members;
    }

    @Override
    public int getRowCount() {
        if(members==null) return 0;
        return members.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Member member = members.get(rowIndex);
        switch (columnIndex) {
            case 0: return member.getId();
            case 1: return member.getFirstname();
            case 2: return member.getLastname();
            case 3: return member.getBirthday();
            case 4: return member.getGender();
            case 5: return member.getPhone();
                
            default:
                return "n/a";
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public List<Member> getMembers() {
        return members;
    }
    
    public Member getMember(int row){
        return members.get(row);
    }

    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            Member member = members.get(rowIndex);
            switch (columnIndex) {
                case 1: member.setFirstname((String)aValue); break;
                case 2: member.setLastname((String) aValue);break;
                case 3: member.setBirthday(sdf.parse((String) aValue));
                case 4: member.setGender((Gender) aValue);
                case 5: member.setPhone((String) aValue);
                default:
            }
        } catch (ParseException ex) {
            Logger.getLogger(MemberTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
