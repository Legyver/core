package com.legyver.core.function;

import com.legyver.core.exception.CoreException;

/**
 * Version of {@link java.util.function.Consumer} that throws a CoreException
 * @param <T> the type of the argument
 */
@FunctionalInterface
public interface ThrowingConsumer<T extends Object> {

	/**
	 * Version of {@link java.util.function.Consumer} that throws a CoreException
	 * @param t the parameter to accept
	 * @throws CoreException if the consumer throws an exception
	 */
	void accept(T t) throws CoreException;
}
