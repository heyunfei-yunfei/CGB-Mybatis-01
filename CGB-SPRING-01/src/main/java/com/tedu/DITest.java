package com.tedu;

import com.tedu.pojo.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DITest {
    @Test
    public void testDI(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        User user = (User) ctx.getBean("user");
        System.out.println(user);
    }
}
