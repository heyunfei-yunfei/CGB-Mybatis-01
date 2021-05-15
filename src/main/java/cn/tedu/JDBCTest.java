package cn.tedu;

import cn.tedu.pojo.Emp;
import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCTest {
    public static void main(String[] args) {
        List<Emp> empList = findAll();
        for (Emp e: empList) {
            System.out.println(e);
        }
    }

    private static List<Emp> findAll() {
        Connection conn=null;
        Statement stat=null;
        ResultSet res = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:///yonghedb?characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false","root","root");
             stat = conn.createStatement();
            String sql = "select * from emp";
            res = stat.executeQuery(sql);
            List<Emp> list = new ArrayList<>();
            while (res.next()){
                int id = res.getInt("id");
                String name = res.getString("name");
                String job = res.getString("job");
                Double salary = res.getDouble("salary");
//                Emp emp = new Emp(id, name, job, salary);
                Emp emp = new Emp();
                emp.setId(id);
                emp.setName(name);
                emp.setJob(job);
                emp.setSalary(salary);
                list.add(emp);

            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询失败");
        }finally {
            if(res!=null&&stat!=null&&conn!=null){
                try {
                    res.close();
                    stat.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return null;

    }
}
