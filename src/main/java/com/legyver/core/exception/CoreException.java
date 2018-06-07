package com.legyver.core.exception;

public class CoreException extends Exception {
	public CoreException(final String aMessage) {
		super(aMessage);
	}

	public CoreException(Throwable aThrowable) {
		super(aThrowable);
	}

	public CoreException(final String aMessage, Throwable aThrowable) {
		super(aMessage, aThrowable);
	}

	@FunctionalInterface
	public interface ThrowingFunction<T> {

		T execute() throws CoreException;
	}

	@FunctionalInterface
	public interface ThrowingConsumer {

		void consume() throws CoreException;
	}

	@FunctionalInterface
	public interface WrappingFunction<T> {

		T execute();
	}

	@FunctionalInterface
	public interface WrappingConsumer {

		void consume();
	}

	public static <T> T wrap(ThrowingFunction<T> executor) {
		try {
			return executor.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void wrap(ThrowingConsumer consumer) {
		try {
			consumer.consume();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T unwrap(WrappingFunction<T> executor) throws CoreException {
		try {
			return executor.execute();
		} catch (RuntimeException e) {
			return unwrapCoreException(e);
		}
	}

	private static <T> T unwrapCoreException(RuntimeException e) throws CoreException {
		Throwable cause = e.getCause();
		if (cause instanceof CoreException) {
			throw (CoreException) cause;
		} else {
			throw e;
		}
	}

	public static void unwrap(WrappingConsumer consumer) throws CoreException {
		try {
			consumer.consume();
		} catch (RuntimeException e) {
			unwrapCoreException(e);
		}
	}
}
