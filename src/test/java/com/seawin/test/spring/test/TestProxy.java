package com.seawin.test.spring.test;

import org.junit.Test;

import com.seawin.test.spring.edu.jyu.core.BeanFactory;
import com.seawin.test.spring.edu.jyu.core.ClassPathXmlApplicationContext;
import com.seawin.test.spring.edu.jyu.dao.UserDao;


public class TestProxy {
	
	@Test
	public void testJDKProxy(){
		BeanFactory ac = new ClassPathXmlApplicationContext("/applicationContext.xml");
		UserDao userDao = (UserDao) ac.getBean("userDaoProxy");
		System.out.println(userDao.getClass());
		userDao.add("Jason");
		String user = userDao.getUser("132");
		System.out.println(user);
	}
}
