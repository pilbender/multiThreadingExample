package net.raescott.multithreadingexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Richard Scott Smith <scott.smith@isostech.com>
 */
public class RunnableObject implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(RunnableObject.class);

	private int counter;

	public RunnableObject() {
		counter = 0;
	}
	
	@Override
	public void run() {
		try {
			increment();
		} catch (InterruptedException e) {
			logger.error(String.valueOf(e.getStackTrace()));
		}
	}

	public void increment() throws InterruptedException {
		Thread.sleep(1000);
		++counter;
	}

	public String toString() {
		return String.valueOf(counter);
	}
}
