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

	public abstract void instruction();

	protected void footer() {
		// TODO Auto-generated method stub
		System.out.println(" ***** GAME OVER ***** ");
		System.out.println(" Developed by consumerfed I T Section ");

	}
}
