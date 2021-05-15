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
import java.util.*;

public class MybatisTest02 {
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
        //Connection
        //4.通过namespace+id找到并执行SQL语句,返回处理后的结果
        //List<Emp> list = session.selectList("EmpMapper.findAll");
        //5.输出结果
        //for (Emp e:list) {
        // System.out.println(e);
        //}
    }

    @Test
    public void testInsert() {
        /*通过namespace+id找到并执行SQL语句,返回执行结果*/
        int rows = session.insert("EmpMapper.insert");
        System.out.println("影响行数为:" + rows);
        //提交事务
        //session.commit();
    }

    @Test
    public void testUpdate() {
        int rows = session.update("EmpMapper.update");
        System.out.println("影响的行数为" + rows);
    }

    @Test
    public void testDelete() {
        //通过namespace+id找到并执行Sql语句.
        int rows = session.delete("EmpMapper.delete");
        System.out.println("影响行数:" + rows);
    }


    /* 练习05:新增员工信息: null, 马云, 教师, 800 */
    @Test
    public void testInsert2() {
        //声明一个map集合,将SQL参数封装到map中
        Map map = new HashMap();
        map.put("name", "马云");
        map.put("job", "教师");
        map.put("salary", 800);
        //根据namespace+id找到SQL语句, 将map作为参数传过去, 并执行SQL语句
        int rows = session.insert("EmpMapper.insert2", map);
        System.out.println("影响行数: " + rows);
    }

    @Test
    public void testUpdate2() {
        Map<String, Object> map = new HashMap();
        map.put("name", "马云");
        map.put("job", "CEO");
        map.put("salary", 80000);
        int rows = session.update("EmpMapper.update2", map);
    }

    @Test
    public void testUpdate3() {
        //将SQL语句中的参数封装到POJO对象中
        Emp emp = new Emp(null, "马云", "教授", 8888.0);

        int rows = session.update("EmpMapper.update2", emp);
    }
    @Test
    public void deleteByID2(){
        session.delete("EmpMapper.delete2",16);
    }
    /*查询emp表中所有员工信息,动态显示要查询的列*/
    @Test
    public void testFindAll2(){
        Map map = new HashMap();
        map.put("colStr","id,name,job");
        List <Emp> list=session.selectList("EmpMapper.findAll2",map);
        for (Emp e:list) {
            System.out.println(e);
        }
    }
    @Test
    public void testFindBySal(){
        Map map = new HashMap();
        map.put("minSal",3000);
        map.put("maxSal",4500);
        List<Emp> list = session.selectList("EmpMapper.findBySal",map);
        for (Emp e: list){
            System.out.println(e);
        }
    }
    @Test
    public void testFindBySal2(){
        Map map = new HashMap();
        //map.put("minSal",3000);
        map.put("maxSal",5000);
        List<Emp> list = session.selectList("EmpMapper.findBySal2",map);
        for (Emp e: list){
            System.out.println(e);
        }
    }
    /*根据员工的id批量删除员工信息id数组int ids={1,3,5,7};*/
    @Test
    public void testDeleteByIds(){
        int[] ids = {1,3,5,7};//从页面上传过来的员工id数组

        session.delete("EmpMapper.deleteByIds",ids);
    }
    @Test
    public void updateByIds(){
        Map map = new HashMap();
        Double money=1000.0;
        int[] ids={2,4,6,8};//要更新的员工id数组
        map.put("ids",ids);//要账的薪资
        map.put("money",money);
        session.update("EmpMapper.updateByIds",map);
        
    }

}