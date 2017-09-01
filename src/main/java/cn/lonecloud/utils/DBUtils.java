package cn.lonecloud.utils;

import cn.lonecloud.bean.Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

/**
 * 数据库DB层
 */
public class DBUtils {
    private static Connection connection = null;
    private static Properties properties = new Properties();
    private static String URL = null;
    private static String CLASSNAME = null;
    private static String USERNAME = null;
    private static String PWSSWORD = null;

    static {
        try {
            properties.load(DBUtils.class.getResourceAsStream("/jdbc.properties"));
            URL = properties.getProperty("jdbc.url");
            CLASSNAME = properties.getProperty("jdbc.class");
            USERNAME = properties.getProperty("jdbc.username");
            PWSSWORD = properties.getProperty("jdbc.password");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 获取Connection
     *
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        Class.forName(CLASSNAME);
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USERNAME, PWSSWORD);
        }
        return connection;
    }

    /**
     * 查询
     *
     * @param sql
     * @param params
     * @return
     * @throws Exception
     */
    public static ResultSet Query(String sql, Object... params) {
        ResultSet resultSet = null;
        try {
            PreparedStatement prepareStatement = getConnection().prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                prepareStatement.setObject(i + 1, params[i]);
            }
            resultSet = prepareStatement.executeQuery();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void execute(String sql, Object... params) {
        PreparedStatement prepareStatement;
        try {
            prepareStatement = getConnection().prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                prepareStatement.setObject(i + 1, params[i]);
            }
            prepareStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 简单bash插入<p>后期在进行优化</p>
     * @param tableName
     * @param projects
     */
    public static void bashInsert(String tableName, List<Project> projects) {
        for (Project p : projects) {
            execute("insert into " + tableName + "(id,name,pid) values(?,?,?)", p.getId(), p.getName(), p.getPid());
        }
    }
}
