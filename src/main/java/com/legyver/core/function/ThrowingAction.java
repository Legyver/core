package com.legyver.core.function;

import com.legyver.core.exception.CoreException;

/**
 * A no-arg function that throws an exception and returns a result
 * @param <R> the return type of the function
 */
@FunctionalInterface
public interface ThrowingAction<R> {
	/**
	 * Execute a function and returns a result
	 * @return the result
	 * @throws CoreException if an exception must be thrown
	 */
	R execute() throws CoreException;
}
