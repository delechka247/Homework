package ru.itis.javalab.homework;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.*;

public class EntityManager {
    private DataSource dataSource;

    public EntityManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // createTable("account", User.class);
    public <T> void createTable(String tableName, Class<T> entityClass) {
        // сгенерировать CREATE TABLE на основе класса
        // create table account ( id integer, firstName varchar(255), ...))


        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE ").append(tableName).append(" ( ");

        Field fields[] = entityClass.getDeclaredFields();
        for (Field field : fields) {
            sql.append(field.getName());
            String fieldType = field.getType().getSimpleName();
            if (fieldType.equals("String")) {
                sql.append(" varchar(255), ");
            } else if (fieldType.equals("Integer")) {
                sql.append(" integer, ");
            } else if (fieldType.equals("boolean")) {
                sql.append(" boolean, ");
            } else if (fieldType.equals("Long")) {
                sql.append(" bigint, ");
            }

        }

        sql.deleteCharAt(sql.length() - 2);
        sql.append(");");

        System.out.println(sql.toString());
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();

            statement.executeUpdate(sql.toString());

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                }
            }
        }
    }

    public void save(String tableName, Object entity) throws IllegalAccessException {
        Class<?> classOfEntity = entity.getClass();
        // сканируем его поля
        // сканируем значения этих полей
        // генерируем insert into


        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(tableName).append(" ( ");

        Field fields[] = classOfEntity.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            sql.append(field.getName()).append(", ");
        }
        sql.deleteCharAt(sql.length() - 2);
        sql.append(")");


        sql.append(" VALUES ( ");
        for (Field field : fields) {
            field.setAccessible(true);
            sql.append("'").append(field.get(entity).toString()).append("'").append(", ");
        }
        sql.deleteCharAt(sql.length() - 2);
        sql.append(");");

        System.out.println(sql.toString());
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();

            statement.executeUpdate(sql.toString());

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                }
            }
        }
    }

    // User user = entityManager.findById("account", User.class, Long.class, 10L);
    public <T, ID> T findById(String tableName, Class<T> resultType, Class<ID> idType, ID idValue) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ").append(tableName).append(" WHERE id=?;");
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql.toString());
            statement.setObject(1, idValue);
            resultSet = statement.executeQuery();

            Object object = resultType.newInstance();

            if (resultSet.next()) {
                Field fields[] = resultType.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fieldType = field.getType().getSimpleName();
                    String fieldName = field.getName();
                    if (fieldType.equals("String")) {
                        field.set(object, resultSet.getString(fieldName));
                    } else if (fieldType.equals("Integer")) {
                        field.set(object, resultSet.getInt(fieldName));
                    } else if (fieldType.equals("boolean")) {
                        field.set(object, resultSet.getBoolean(fieldName));
                    } else if (fieldType.equals("Long")) {
                        field.set(object, resultSet.getLong(fieldName));
                    }
                }
                return (resultType.cast(object));
            }
            return null;

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                }
            }
        }
    }

}
