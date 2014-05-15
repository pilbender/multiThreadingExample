package net.raescott.multithreadingexample;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
  * @author Richard Scott Smith <scott.smith@isostech.com>
  */
@Component
//@EnableAspectJAutoProxy
public class AsynchronousBean {

	private int counter;

	public AsynchronousBean() {
		counter = 0;
	}

	@Async
	public void increment() throws InterruptedException {
		Thread.sleep(1000);
		++counter;
	}

	public String toString() {
		return String.valueOf(counter);
	}
}
