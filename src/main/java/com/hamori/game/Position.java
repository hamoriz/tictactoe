package com.hamori.game;

/**
 * The position in a game of Tic-tac-toe. A position can be seen as a mapping
 * from all {@link Field}s to a {@link FieldStatus}.
 * 
 * <p>
 * Instances of implementations of this interface are immutable.
 */
public interface Position {

	/**
	 * Returns the {@link FieldStatus} a {@link Field} has. The return value
	 * cannot be {@code null}.
	 * 
	 * @param field
	 *            the field to examine
	 * @return the status of the field
	 * @throws NullPointerException
	 *             if {@code field} is a {@code null} reference
	 */
	FieldStatus getFieldStatus(Field field);
}
