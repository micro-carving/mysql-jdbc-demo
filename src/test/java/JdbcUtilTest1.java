import org.junit.Test;

import java.sql.*;

import static olin.util.JdbcUtil.release;

/**
 * 使用statement对象连接
 */
public class JdbcUtilTest1 {
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
        //1、加载MySQL数据库驱动;
        Class.forName(className);
        //2、通过驱动管理器得到连接对象;
        Connection conn = DriverManager.getConnection(url, user, password);
        //3、通过连接对象创建Statement对象
        Statement st = conn.createStatement();
        //4、使用statement对象向数据库发送SQL,得到一个结果集
        sql ="select * from stu_info";
        // 执行给定的SQL语句，返回单个ResultSet对象。
        ResultSet rs =st.executeQuery(sql); //执行查询返回一个结果集(要取的数据)
        //有一个指针指向此结果集；指针：游标;通过移动游标，获取结果集合中的数据;
        //5、从结果集中取数据;
        //将游标移到结果集的第1行;
        while(rs.next()){
            String stu_num = rs.getString("stu_num");
            String stu_name = rs.getString("stu_name");
            int stu_age = rs.getInt("stu_age");
            System.out.println(stu_num + " " + stu_name + " " + stu_age);
        }
        //关闭对象
        release(rs,st,conn);
    }

    /**
     * 添加语句
     * @throws Exception
     */
    @Test
    public void testInsert() throws Exception{
        Class.forName(className);
        Connection conn = DriverManager.getConnection(url,user,password);
        Statement statement = conn.createStatement();
        sql = "insert into stu_info(stu_num, stu_name, stu_age)values('A103','小刚', 20)";
        // 执行给定的SQL语句，这可能是INSERT ， UPDATE ，或DELETE语句
        int is_success =  statement.executeUpdate(sql);
        if (is_success > 0) {
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
        //关闭对象
        release(statement,conn);
    }

    /**
     * 修改语句
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception{
        Class.forName(className);
        Connection conn = DriverManager.getConnection(url,user,password);
        Statement statement = conn.createStatement();
        sql = "update stu_info set stu_name = '小强', stu_age = 29 where stu_num = 'A103'";
        // 执行给定的SQL语句，这可能是INSERT ， UPDATE ，或DELETE语句
        int is_success =  statement.executeUpdate(sql);
        if (is_success > 0) {
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
        //关闭对象
        release(statement,conn);
    }

    /**
     * 删除语句
     * @throws Exception
     */
    @Test
    public void testDelete() throws Exception{
        Class.forName(className);
        Connection conn = DriverManager.getConnection(url,user,password);
        Statement statement = conn.createStatement();
        sql = "delete from stu_info where stu_num = 'A103'";
        int is_success =  statement.executeUpdate(sql);
        if (is_success > 0) {
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
        // 关闭对象
        release(statement,conn);
    }
}