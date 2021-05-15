package cn.tedu.dao;

import cn.tedu.pojo.Emp;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

//接口的全类名:cn.tedu.dao.EmpMapper
public interface EmpMapper {
    /*查询emp表中的所有员工信息*/

    public List<Emp> findAll();
    /*新增员工信息(没有占位符)*/
    public void insert();
    /*新增员工信息:null,马云,教师,800(有占位符)*/
    public void insert2(Map map);
    //修改员工信息:将马玉的之为改为"CEO",薪资改为80000(有占位符)
    public void update2(Emp emp);

}
