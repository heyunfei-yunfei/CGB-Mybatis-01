package cn.tedu.dao;

import cn.tedu.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.List;
import java.util.Map;

//接口的全类名:cn.tedu.dao.EmpMapper
public interface EmpMapper {
    /*查询emp表中的所有员工信息*/
    @Select("select * from emp")
    public List<Emp> findAll();
    /*新增员工信息(没有占位符)*/
    @Insert("insert into emp value(null,'赵云云','高级java工程师',35000)")
    public void insert();
    /*新增员工信息:null,马云,教师,800(有占位符)*/
    @Insert("insert into emp value(null,@{name},#{job},#{salary})")
    public void insert2(Map map);
    //修改员工信息:将马玉的之为改为"CEO",薪资改为80000(有占位符)
    @Update("update emp set job=#{job},salary=#{salary} where name=#{name}")
    public void update2(Emp emp);

}
