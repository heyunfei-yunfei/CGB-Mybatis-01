package cn.tedu.dao;

import cn.tedu.pojo.Emp;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class EmpMapperImpl implements EmpMapper {
    private SqlSession session;
    //将session对象通过构造方法保存到类的内部
    public EmpMapperImpl(SqlSession session) {
        this.session = session;
    }
    /*查询所有员工的信息*/
    public List<Emp> findAll(){
        //获取当前这个类的父接口的全类名(=namespace)
        String name = this.getClass().getInterfaces()[0].getName();
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        String methodName = st[1].getMethodName();
        List<Emp> list = session.selectList(name + "." + methodName);
        return list;
    }
    public void insert(){
        String name = this.getClass().getInterfaces()[0].getName();
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        String methodName = st[1].getMethodName();
        session.insert(name+"."+methodName);
    }

    @Override
    public void insert2(Map map) {
        String name = this.getClass().getInterfaces()[0].getName();
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        String methodName = st[1].getMethodName();
        session.insert(name+"."+methodName,map);
    }

    @Override
    public void update2(Emp emp) {

    }


}
