package cn.tedu;

import cn.tedu.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest01 {
    public static void main(String[] args) throws Exception {
        //1.读取mybatis核心配置文件中的配置信息(mybatis-config.xml)
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //2.给予上面读取的配置信息获取SqlSessionFactory对象(工厂)
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.通过工厂对象打开与数据库的链接(即获取SqlSession对象)
        SqlSession session = factory.openSession();
        //Connection
        //4.通过namespace+id找到并执行SQL语句,返回处理后的结果
        List<Emp> list = session.selectList("EmpMapper.findAll");
        //5.输出结果
        for (Emp e:list) {
            System.out.println(e);
        }
    }
}
