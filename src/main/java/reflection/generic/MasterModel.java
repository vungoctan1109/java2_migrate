package reflection.generic;

import reflection.myannotation.Table;
import util.ConnectionHelper;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MasterModel<T> {
    public void save (T obj) throws SQLException, IllegalAccessException {
        Class clazz = obj.getClass();
        Connection cnn = ConnectionHelper.getConnection();
        Statement stt = cnn.createStatement();
        String tableName = clazz.getSimpleName();
        Table table = (Table) clazz.getAnnotation(Table.class);
        if (!table.name().isEmpty()) {
            tableName = table.name();
        }
        StringBuilder fieldNamesBuilder = new StringBuilder();
        fieldNamesBuilder.append("(");
        Field[] fields = clazz.getDeclaredFields();
        for (Field value : fields) {
            fieldNamesBuilder.append(value.getName());
            fieldNamesBuilder.append(", ");
        }
        fieldNamesBuilder.setLength(fieldNamesBuilder.length() - 2);
        fieldNamesBuilder.append(")");
        StringBuilder fieldValuesBuilder = new StringBuilder();
        fieldValuesBuilder.append("(");
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType().getSimpleName().equals("String")) {
                fieldValuesBuilder.append("'");
                fieldValuesBuilder.append(field.get(obj));
                fieldValuesBuilder.append("', ");
            } else {
                fieldValuesBuilder.append(field.get(obj));
                fieldValuesBuilder.append(", ");
            }
        }
        fieldValuesBuilder.setLength(fieldValuesBuilder.length() - 2);
        fieldValuesBuilder.append(")");
        String sqlQuery = String.format("insert into %s %s values %s", tableName, fieldNamesBuilder.toString(), fieldValuesBuilder.toString());
        System.out.println(sqlQuery);
        try {
            stt.execute(sqlQuery);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }


}
