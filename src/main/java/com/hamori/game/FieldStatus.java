package com.hamori.game;

/**
 * The status a {@link Field} can have. A field can be occupied by one of the
 * two players or be empty.
 */
public enum FieldStatus {
	/** Occupied by player X */
	X,

	/** Occupied by player O */
	O,

	/** Empty */
	EMPTY;
}
