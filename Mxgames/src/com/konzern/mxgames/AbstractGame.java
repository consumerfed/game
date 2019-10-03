/**
 * AbstractGame : an abstract class for all games
 */
package com.konzern.mxgames;

/**
 * @author Konzernites
 *
 */
public abstract class AbstractGame {
	
	 public void playGame(IGame game) throws IllegalAccessException {
		  game.startGame();
		 }

}
