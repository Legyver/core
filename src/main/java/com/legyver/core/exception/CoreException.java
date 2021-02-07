package com.legyver.core.exception;

/**
 * The CoreException for the Legyver libraries.
 * The idea is to simplify the API's by wrapping all thrown exceptions in a single Exception
 */
public class CoreException extends Exception {

	/**
	 * Construct a CoreException with a message
	 * @param aMessage the message
	 */
	public CoreException(final String aMessage) {
		super(aMessage);
	}

	/**
	 * Construct a CoreException without a message
	 * @param aThrowable the exception to wrap
	 */
	public CoreException(Throwable aThrowable) {
		super(aThrowable);
	}

	/**
	 * Construct a CoreException
	 * @param aMessage the message
	 * @param aThrowable the exception to wrap
	 */
	public CoreException(final String aMessage, Throwable aThrowable) {
		super(aMessage, aThrowable);
	}

	/**
	 * A function that throws a  CoreException
	 * @param <T> the type of the result of the function
	 */
	@FunctionalInterface
	public interface ThrowingFunction<T> {

		/**
		 * Execute a function
		 * @return the result of the function
		 * @throws CoreException if required
		 */
		T execute() throws CoreException;
	}

	/**
	 * A consumer that throws a CoreException
	 */
	@FunctionalInterface
	public interface ThrowingConsumer {

		/**
		 * Consume
		 * @throws CoreException if required
		 */
		void consume() throws CoreException;
	}

	/**
	 * A function that does not throw an exception
	 * @param <T> the function result type
	 */
	@FunctionalInterface
	public interface WrappingFunction<T> {

		/**
		 * Execute a function
		 * @return the result
		 */
		T execute();
	}

	/**
	 * A consumer that does not throw an exception
	 */
	@FunctionalInterface
	public interface WrappingConsumer {

		/**
		 * Consume operation without any arguments
		 */
		void consume();
	}

	/**
	 * Evaluate a function, catching any exception and wrapping it in a RuntimeException to allow for usage in lambdas
	 * @param executor the consumer to evaluate
	 * @param <T> the type of the result of the function
	 * @return the result
	 */
	public static <T> T wrap(ThrowingFunction<T> executor) {
		try {
			return executor.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Evaluate a consumer, catching any exception and wrapping it in a RuntimeException to allow for usage in lambdas
	 * @param consumer the consumer to evaluate
	 */
	public static void wrap(ThrowingConsumer consumer) {
		try {
			consumer.consume();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Undo the {@link #wrap(ThrowingFunction)} operation by catching the RuntimeException and translating it to the wrapped CoreException
	 * @param executor the WrappingFunction to execute
	 * @param <T> the type of the WrappingFunction
	 * @return the result of the function
	 * @throws CoreException if thrown by the WrappingFunction
	 */
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

	/**
	 * Undo the {@link #wrap(ThrowingConsumer)} operation by catching the RuntimeException and translating it to the wrapped CoreException
	 * @param consumer the WrappingConsumer to evaluate
	 * @throws CoreException if thrown by the WrappingConsumer
	 */
	public static void unwrap(WrappingConsumer consumer) throws CoreException {
		try {
			consumer.consume();
		} catch (RuntimeException e) {
			unwrapCoreException(e);
		}
	}
}
