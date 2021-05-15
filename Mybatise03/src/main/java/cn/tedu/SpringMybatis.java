package cn.tedu;

import cn.tedu.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SpringMybatis {
    @Test
    public void test01() throws IOException {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(in);
        SqlSession session = fac.openSession();
        List <Emp> list = session.selectList("EmpMapper.findAll");
        for (Emp e: list) {
            System.out.println(e);
        }
    }
}
