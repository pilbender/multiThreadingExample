package net.raescott.multithreadingexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;

/**
 * Multi-Threading Example Project
 */
public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		BeanFactory beanFactory = applicationContext;

		AsynchronousBean asynchronousBean = (AsynchronousBean) beanFactory.getBean("asynchronousBean");
		AsynchronousBean asynchronousBean1 = (AsynchronousBean) beanFactory.getBean("asynchronousBean1");
		AsynchronousBean asynchronousBean2 = (AsynchronousBean) beanFactory.getBean("asynchronousBean2");
		Calendar calendar = Calendar.getInstance();

        logger.info("*** Multi-Threading Example Project ***");
		calendar.setTimeInMillis(System.currentTimeMillis());
		logger.info("Start time: {}", calendar.getTime());
		for (int i = 0; i < 5; ++i) {
			asynchronousBean.increment();
			asynchronousBean1.increment();
			asynchronousBean2.increment();
			calendar.setTimeInMillis(System.currentTimeMillis());
			logger.info("Time Check {}: {}", i, calendar.getTime());
			logger.info("asynchronousBean: {}", asynchronousBean);
			logger.info("asynchronousBean1: {}", asynchronousBean1);
			logger.info("asynchronousBean2: {}", asynchronousBean2);
		}
		logger.info("*** Multi-Threading Example Project ***");
	}
}
