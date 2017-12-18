package com.netcracker.avizhen.persistence.utils;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 18.12.2017.
 */
public class CustomTableModel extends AbstractTableModel {
    private List<String> columns = new ArrayList<>();
    private List<Method> getters = new ArrayList<>();
    private List list;

    public CustomTableModel(List<String> columns, List<Method> getters, List list) {
        this.columns = columns;
        this.getters = getters;
        this.list = list;
    }

    @Override
    public String getColumnName(int column) {
        return columns.get(column);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            return getters.get(columnIndex).invoke(list.get(rowIndex));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
