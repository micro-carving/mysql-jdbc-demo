import org.junit.Test;

import java.sql.*;

import static olin.util.JdbcUtil.release;

/**
 * 使用PreparedStatement对象连接
 */
public class JdbcUtilTest2 {
    private final String className="com.mysql.cj.jdbc.Driver";
    //private final String url="jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false";
    private final String url="jdbc:mysql://localhost:3306/test";
    private final String user="root";
    private final String password="root";
    private String sql = null;

    /**
     * 查询语句
     * @throws Exception
     */
    @Test
    public void testSelect() throws Exception{
        Class.forName(className);
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement = conn.prepareStatement("select * from stu_info");
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            String stu_num = rs.getString("stu_num");
            String stu_name = rs.getString("stu_name");
            int stu_age = rs.getInt("stu_age");
            System.out.println(stu_num + " " + stu_name + " " + stu_age);
        }
        //关闭对象
        release(rs,preparedStatement,conn);
    }

    /**
     * 添加语句
     * @throws Exception
     */
    @Test
    public void testInsert() throws Exception{
        Class.forName(className);
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement = conn.prepareStatement("insert into stu_info(stu_num, stu_name, stu_age)values(?,?, ?)");
        preparedStatement.setString(1,"A103");
        preparedStatement.setString(2,"小刚");
        preparedStatement.setInt(3,20);
        int is_success =  preparedStatement.executeUpdate();
        if (is_success > 0) {
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
        //关闭对象
        release(preparedStatement,conn);
    }

    /**
     * 修改语句
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception{
        Class.forName(className);
        Connection conn = DriverManager.getConnection(url,user,password);
        PreparedStatement preparedStatement = conn.prepareStatement("update stu_info set stu_name = ?, stu_age = ? where stu_num = ?");
        preparedStatement.setString(1, "小强");
        preparedStatement.setInt(2, 29);
        preparedStatement.setString(3, "A103");
        // 执行给定的SQL语句，这可能是INSERT ， UPDATE ，或DELETE语句
        int is_success =  preparedStatement.executeUpdate();
        if (is_success > 0) {
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
        //关闭对象
        release(preparedStatement,conn);
    }

    /**
     * 删除语句
     * @throws Exception
     */
    @Test
    public void testDelete() throws Exception{
        Class.forName(className);
        Connection conn = DriverManager.getConnection(url,user,password);
        PreparedStatement preparedStatement = conn.prepareStatement("delete from stu_info where stu_num = ?");
        preparedStatement.setString(1, "A103");
        int is_success =  preparedStatement.executeUpdate();
        if (is_success > 0) {
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
        // 关闭对象
        release(preparedStatement,conn);
    }
}
