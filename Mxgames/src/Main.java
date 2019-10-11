import com.konzern.mxgames.AbstractGame;
import com.konzern.mxgames.IGame;
import com.konzern.mxgames.Matrix;
import com.konzern.mxgames.tictactoe.TikTocToe;

/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 1997-2017 Javabelazy and/or its affiliates. All rights reserved.
 *
 */

/**
 * @author konzernites
 * @since 1.0
 *
 */
public class Main extends AbstractGame {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 8683452581122892182L;

	/**
	 * @param args
	 * @throws IllegalAccessException
	 */
	public static void main(String[] args) throws IllegalAccessException {

		Main mainClass = new Main();
		mainClass.execute();

	}

	private void execute() throws IllegalAccessException {
		Matrix<Character> matrix = new Matrix<>(3, 3);
		IGame game = new TikTocToe(matrix);
		TikTocToe.isTwoPlayer = false;
		playGame(game);
		footer();
	}

	@Override
	public void instruction() {

	}

}
