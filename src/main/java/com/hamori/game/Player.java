package com.hamori.game;
/**
 * A player in the game of Tic-tac-toe. A move made by the player follows an
 * optimal strategy, i.e. the outcome of the game leads to the best possible
 * result for the player. Winning is better than drawing is better than losing.
 * 
 * <p>
 * The player adheres to the traditional rules of the game: Two players, X and
 * O, take turns and occupy one of the {@link Field}s that is still
 * {@link FieldStatus#EMPTY}. Player X starts the game. The player who succeeds
 * in occupying three respective fields in a horizontal, vertical, or diagonal
 * line wins the game. If the board is full, i.e. there are no empty fields
 * anymore, and none of the players managed to win, the game is drawn.
 * 
 * <p>
 * Instances should provide a void (no arguments) constructor.
 * 
 * <p>
 * Instances of implementations of this interface are immutable.
 */
public interface Player {

	/**
	 * Plays a move by following an optimal strategy for the given
	 * {@link Position}. For some positions there can be more than one best
	 * move. From the given {@code position} it follows if the game is still
	 * ongoing. If the game has not ended yet, it also follows if this player
	 * plays for X or O.
	 * 
	 * <p>
	 * This method never returns {@code null}.
	 * 
	 * @param position
	 *            The current position on the board
	 * @return the field on which the player makes its next move
	 * @throws NullPointerException
	 *             if {@code position} is a {@code null} reference
	 * @throws IllegalStateException
	 *             if {@code position} represents a game that is over, i.e. won
	 *             by one of the players or drawn.
	 */
	Field move(Position position);
}
