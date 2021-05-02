package com.legyver.core.function;

import com.legyver.core.exception.CoreException;

/**
 * A no-arg function that throws an exception and returns nothing
 */
@FunctionalInterface
public interface ThrowingVoidAction {
	/**
	 * Execute a function and return nothing
	 * @throws CoreException if an exception must be thrown
	 */
	void execute() throws CoreException;
}
