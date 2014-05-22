package net.raescott.multithreadingexample;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Richard Scott Smith <scott.smith@isostech.com>
 */
public class FutureObject implements Future<Integer> {
	boolean cancel = false;
	boolean cancelled = false;
	boolean done = false;
	Integer counter = 0;

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return cancel;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public boolean isDone() {
		return done;
	}

	@Override
	public Integer get() throws InterruptedException, ExecutionException {
		return counter;
	}

	@Override
	public Integer get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return counter;
	}

	public Integer longRunningProcess(int iterations) throws InterruptedException {
		Integer counter = 0;
		for (int i = 0; i < iterations; ++i) {
			Thread.sleep(1000);
			counter = i;
		}
		return counter;
	}
}
