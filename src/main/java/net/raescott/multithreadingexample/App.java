package net.raescott.multithreadingexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

/**
 * Multi-Threading Example Project
 */
public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);

	private static final int ITERATIONS = 3;

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		BeanFactory beanFactory = applicationContext;

		AsynchronousBean asynchronousBean = (AsynchronousBean) beanFactory.getBean("asynchronousBean");
		AsynchronousBean asynchronousBean1 = (AsynchronousBean) beanFactory.getBean("asynchronousBean1");
		AsynchronousBean asynchronousBean2 = (AsynchronousBean) beanFactory.getBean("asynchronousBean2");
		Calendar calendar = Calendar.getInstance();


        logger.info("*** Multi-Threading Example Project ***");

		logger.info("Using the @Async Spring Bean managed annotation");
		logger.info("Each of the 3 methods has a 1 second delay so if it does not work,");
		logger.info("4 seconds will pass between time checks.  If it works, only 1 second will pass.");
		calendar.setTimeInMillis(System.currentTimeMillis());
		logger.info("Start time: {}", calendar.getTime());
		for (int i = 0; i < ITERATIONS; ++i) {
			asynchronousBean.increment();
			asynchronousBean1.increment();
			asynchronousBean2.increment();
			calendar.setTimeInMillis(System.currentTimeMillis());
			Thread.sleep(1000);
			logger.info("Time Check {}: {}", i, calendar.getTime());
			logger.info("asynchronousBean: {}", asynchronousBean);
			logger.info("asynchronousBean1: {}", asynchronousBean1);
			logger.info("asynchronousBean2: {}", asynchronousBean2);
		}

		logger.info("Using the old Runnable interface");

		RunnableObject runnableObject = new RunnableObject();
		RunnableObject runnableObject1 = new RunnableObject();
		RunnableObject runnableObject2 = new RunnableObject();

		calendar.setTimeInMillis(System.currentTimeMillis());
		logger.info("Start time: {}", calendar.getTime());
		for (int i = 0; i < ITERATIONS; ++i) {
			Thread thread = new Thread();
			new Thread(runnableObject).start();
			new Thread(runnableObject1).start();
			new Thread(runnableObject2).start();
			calendar.setTimeInMillis(System.currentTimeMillis());
			Thread.sleep(1000);
			logger.info("Time Check {}: {}", i, calendar.getTime());
			logger.info("runnableObject: {}", runnableObject);
			logger.info("runnableObject1: {}", runnableObject1);
			logger.info("runnableObject2: {}", runnableObject2);
		}

		logger.info("Using Wait and Notify, the old methodology");
		calendar.setTimeInMillis(System.currentTimeMillis());
		logger.info("Start time: {}", calendar.getTime());

		logger.info("Demonstration of the new Future Interface with out Spring");
		calendar.setTimeInMillis(System.currentTimeMillis());
		logger.info("Start time: {}", calendar.getTime());
		FutureObject futureObject = new FutureObject();
		//FutureTask<>
		for (int i = 0; i < ITERATIONS; ++i) {
			//futureObject.
			calendar.setTimeInMillis(System.currentTimeMillis());
			Thread.sleep(1000);
			logger.info("Time Check {}: {}", i, calendar.getTime());
			logger.info("FutureObject status: {}", futureObject.get());
		}

		logger.info("Countdown Latch");
		calendar.setTimeInMillis(System.currentTimeMillis());
		logger.info("Start time: {}", calendar.getTime());

		logger.info("Executor Framework");
		calendar.setTimeInMillis(System.currentTimeMillis());
		logger.info("Start time: {}", calendar.getTime());

		logger.info("*** Multi-Threading Example Project ***");
	}
}
