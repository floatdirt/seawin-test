package com.seawin.test.spring.test.core;

import org.junit.Test;

import com.seawin.test.spring.edu.jyu.bean.A;
import com.seawin.test.spring.edu.jyu.bean.B;
import com.seawin.test.spring.edu.jyu.core.BeanFactory;
import com.seawin.test.spring.edu.jyu.core.ClassPathXmlApplicationContext;

public class TestApplicationContext {

	@Test
	public void test() {
		BeanFactory ac = new ClassPathXmlApplicationContext("/applicationContext.xml");
		A a = (A) ac.getBean("A");
		A a1 = (A) ac.getBean("A");
		B b = (B) ac.getBean("B");
		B b1 = (B) ac.getBean("B");
		System.out.println(a.getB());
		System.out.println("a==a1 : "+(a==a1));
		System.out.println("b==b1 : "+(b==b1));
	}

    public static void main(String[] args) {
        BeanFactory factory = new ClassPathXmlApplicationContext(
            "classpath:applicationContext.xml");
        B b = (B) factory.getBean("B");
    }
}