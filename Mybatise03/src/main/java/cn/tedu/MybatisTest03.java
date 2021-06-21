package cn.tedu;

import cn.tedu.dao.EmpMapper;
import cn.tedu.dao.EmpMapperImpl;
import cn.tedu.pojo.Emp;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.*;

public class MybatisTest03 {
    SqlSession session = null;

    //也是junit中的注解,在调用@Test的方法之前,先调用这个注解下的方法;
    @Before
    public void beforeMethod() throws Exception {
        //1.读取mybatis核心配置文件中的配置信息(mybatis-config.xml)
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //2.给予上面读取的配置信息获取SqlSessionFactory对象(工厂)
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.通过工厂对象打开与数据库的链接(即获取SqlSession对象)
        session = factory.openSession(true);
        //true:表示自动提交业务,默认false表示手动提交
    }

    @Test
    public void testFinAll(){
        //获取EmpMapper接口的子类(由mybatis提供)的对象实例
        EmpMapper mapper = session.getMapper(EmpMapper.class);
        //明天带着模拟mybatis提供的接口子类代码
        List<Emp> list = mapper.findAll();
        for (Emp e:list) {
            System.out.println(e);
        }
    }

    @Test
    public void testFindById(){
        EmpMapper mapper = session.getMapper(EmpMapper.class);
        Emp emp = mapper.selectOne("cn.tedu.dao.EmpMapper.findById",1);
        System.out.println(emp);
    }
    @Test
    public void testFindAll2(){
        EmpMapperImpl mapper = new EmpMapperImpl(session);
        List<Emp> li = mapper.findAll();
        for (Emp e:li) {
            System.out.println(e);
        }
    }
    /*新增员工信息*/
    @Test
    public void testInsert(){
        //获取EmpMapper接口子类(mybatis负责提供)的对象实例
        EmpMapper mapper = session.getMapper(EmpMapper.class);
        mapper.insert();
    }

    @Test
    public void testInsert22(){
        EmpMapperImpl mapper = new EmpMapperImpl(session);
        mapper.insert();
    }
    @Test
    public void testInsert2(){
        EmpMapper mapper = session.getMapper(EmpMapper.class);
        Map map = new HashMap();
        map.put("name","马化腾");
        map.put("job","腾讯仿制");
        map.put("salary",25000);

        mapper.insert2(map);
    }
    @Test
    public void testUpdate2(){
        EmpMapper mapper = session.getMapper(EmpMapper.class);
        Emp emp = new Emp(null,"马化腾","经理",250.0);
        mapper.update2(emp);
    }


}