package com.netcracker.avizhen.persistence.factory;
import com.netcracker.avizhen.persistence.utils.CustomTableModel;

import javax.swing.table.TableModel;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TableModelCreator {

    public static <T> TableModel createTableModel(Class<T> beanClass, final List<T> list) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
            final List<String> columns = new ArrayList<>();
            final List<Method> getters = new ArrayList<>();

            for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
                String name = pd.getName();
                if (name.equals("class")) {
                    continue;
                }
                name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                String[] s = name.split("(?=\\p{Upper})");
                String displayName = "";
                for (String s1 : s) {
                    displayName += s1 + " ";
                }

                columns.add(displayName);
                Method m = pd.getReadMethod();
                getters.add(m);
            }

            TableModel model = new CustomTableModel(columns, getters, list);
            return model;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}