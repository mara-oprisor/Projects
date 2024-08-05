package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * The TableCreator class provides a static method to create a table from a list of objects in Swing applications.
 */
public class TableCreator {
    /**
     * Creates a JTable from a list of objects.
     *
     * @param <T>   The type of objects in the list.
     * @param list  The ArrayList containing objects to be displayed in the table.
     * @param table The JTable to be populated with data from the list.
     */
    public static <T> void createTable(ArrayList<T> list, JTable table) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);

        tableModel.setColumnCount(0);
        if (!list.isEmpty()) {
            T firstObject = list.getFirst();
            Field[] fields = firstObject.getClass().getDeclaredFields();
            for (Field field : fields) {
                tableModel.addColumn(field.getName());
            }
        }

        for (T data : list) {
            Object[] rowData = new Object[tableModel.getColumnCount()];
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                try {
                    String columnName = tableModel.getColumnName(i);
                    Field field = data.getClass().getDeclaredField(columnName);
                    field.setAccessible(true);
                    Object value = field.get(data);
                    rowData[i] = value;
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            tableModel.addRow(rowData);
        }
    }
}
