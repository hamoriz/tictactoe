package com.hamori.game;

/**
 * A field on a board on which the game of Tic-tac-toe is played. A board has
 * three rows and three columns, therefore it has nine different fields.
 */
public enum Field {
	/** The field in the top row and leftmost column */
	TOP_LEFT,

	/** The field in the top row and centre column */
	TOP_CENTRE,

	/** The field in the top row and rightmost column */
	TOP_RIGHT,

	/** The field in the centre row and leftmost column */
	CENTRE_LEFT,

	/** The field in the centre row and centre column */
	CENTRE_CENTRE,

	/** The field in the centre row and rightmost column */
	CENTRE_RIGHT,

	/** The field in the bottom row and leftmost column */
	BOTTOM_LEFT,

	/** The field in the bottom row and centre column */
	BOTTOM_CENTRE,

	/** The field in the bottom row and rightmost column */
	BOTTOM_RIGHT;
}
