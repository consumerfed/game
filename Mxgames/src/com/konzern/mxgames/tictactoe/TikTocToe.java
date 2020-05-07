/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 1997-2020 Javabelazy and/or its affiliates.
 *  All rights reserved.
 *
 */
/**
 * Tic Tac Toe 
 * The main class
 * Developed by Konzern Team
 * 
 * The core of the projecta
 */
package com.konzern.mxgames.tictactoe;

import java.util.Scanner;

import com.konzern.mxgames.IGame;
import com.konzern.mxgames.Matrix;

/**
 * @author konzernites
 * @param <E>
 * @since 1.0
 * 
 *
 *
 */
public class TikTocToe implements IGame {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 8683452581122892185L;

	public static final int MATRIX_SIZE = 3;
	public static final int MAX_MOVE = 9;
	protected static int moveCount = 1;
	public static final char USER_MOVE = 'X';
	public static final char COMP_MOVE = 'O';
	private ComputerMove computerMove = null;
	private Matrix<Character> matrix = null;
	public Character winner = null;
	@SuppressWarnings("unused")
	private static final String techIssue = "EXPERIENCING SOME TECHNICAL ISSUE";
	public static boolean isTwoPlayer = false;

	public TikTocToe() {
		intialize();
	}

	@SuppressWarnings("unchecked")
	public TikTocToe(@SuppressWarnings("rawtypes") Matrix matrix) {
		this.matrix = matrix;
		intialize();
	}

	private void intialize() {
		computerMove = new ComputerMove(matrix);
	}

	private boolean checkWinner() {
		if (findWinner() == USER_MOVE) {
			return true;
		} else if (findWinner() == COMP_MOVE) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unused")
	private char findWinner() {
		Object[][] newMatrix = matrix.getMatrix();
		if (moveCount < 5 || moveCount > MAX_MOVE) {
			return Matrix.EMPTY_DATA;
		}
		if (null != matrix.getValue(0, 0)
				&& (TikTocToe.USER_MOVE == matrix.getValue(0, 0) || TikTocToe.COMP_MOVE == matrix.getValue(0, 0))) {
			winner = (Character) matrix.getValue(0, 0);
			if ((null != matrix.getValue(0, 1) && winner.equals(newMatrix[0][1]))
					&& (null != matrix.getValue(0, 2) && winner.equals(newMatrix[0][2]))) {
				return winner;
			} else if ((null != matrix.getValue(0, 1) && winner.equals(newMatrix[1][0]))
					&& (null != matrix.getValue(2, 0) && winner.equals(newMatrix[2][0]))) {
				return winner;
			} else if ((null != matrix.getValue(1, 1) && winner.equals(newMatrix[1][1]))
					&& (null != matrix.getValue(2, 2) && winner.equals(newMatrix[2][2]))) {
				return winner;
			}
		}
		if (null != matrix.getValue(1, 1)
				&& (TikTocToe.USER_MOVE == matrix.getValue(1, 1) || TikTocToe.COMP_MOVE == matrix.getValue(1, 1))) {
			winner = (Character) newMatrix[1][1];
			if ((null != matrix.getValue(1, 0) && winner.equals(newMatrix[1][0]))
					&& (null != matrix.getValue(1, 2) && winner.equals(newMatrix[1][2]))) {
				return winner;
			} else if ((null != matrix.getValue(0, 0) && winner.equals(newMatrix[0][0]))
					&& (null != matrix.getValue(1, 2) && winner.equals(newMatrix[1][2]))) {
				return winner;
			} else if ((null != matrix.getValue(0, 2) && winner.equals(newMatrix[0][2]))
					&& (null != matrix.getValue(2, 0) && winner.equals(newMatrix[2][0]))) {
				return winner;
			}
		}
		if (null != matrix.getValue(2, 2)
				&& (TikTocToe.USER_MOVE == matrix.getValue(2, 2) || TikTocToe.COMP_MOVE == matrix.getValue(2, 2))) {
			winner = (Character) newMatrix[2][2];
			if ((null != matrix.getValue(2, 0) && winner.equals(newMatrix[2][0]))
					&& (null != matrix.getValue(2, 1) && winner.equals(newMatrix[2][1]))) {
				return winner;
			} else if ((null != matrix.getValue(0, 2) && winner.equals(newMatrix[0][2]))
					&& (null != matrix.getValue(1, 2) && winner.equals(newMatrix[1][2]))) {
				return winner;
			}
		}
		return Matrix.EMPTY_DATA;
	}

	/**
	 * @param args
	 * @throws IllegalAccessException
	 */
	public static void main(String[] args) throws IllegalAccessException {
		System.out.println(" **** INSTRUCTION **** ");
		System.out.println(" User has to enter row and column ");
		System.out.println(" Postion starts from (0,0) to (2,2) ");
		TikTocToe t = new TikTocToe();
		t.startGame();
		System.out.println(" **** GAME OVER ***** ");
		System.out.println(" Developed by consumerfed I T section kozhikode ");

	}

	@SuppressWarnings("resource")
	public void startGame() throws IllegalAccessException {

		int rowMoved = 0;
		int colMoved = 0;

		while (moveCount < MAX_MOVE) {

			if (moveCount % 2 == 0) {

				if (isTwoPlayer) {
					getUserMove(rowMoved, colMoved, COMP_MOVE);
				} else {
					matrix = computerMove.computerMove(rowMoved, colMoved);
					System.out.println("** COMPUTER MOVE **");
					try {
						Thread.sleep(3121);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			} else {
				getUserMove(rowMoved, colMoved, USER_MOVE);
			}
			matrix.print();
			boolean isWon = checkWinner();
			if (isWon) {
				String name = winner.equals(TikTocToe.USER_MOVE) ? " USER " : " COMPUTER ";
				System.out.println("*** Congratulation *** ");
				System.out.println(name + " is the winner ");
				break;
			}
			moveCount++;
		}

	}

	@SuppressWarnings("resource")
	private void getUserMove(int rowMoved, int colMoved, char currentMove) throws IllegalAccessException {
		Scanner scanner = null;
		scanner = new Scanner(System.in);
		System.out.println(" *** THE USER :  " + currentMove + " MOVE *** ");
		System.out.println(" Enter the row (values from 0 to 2): ");
		int row = scanner.nextInt();
		validate(row);
		System.out.println(" Enter the col (values from 0 to 2): ");
		int col = scanner.nextInt();
		validate(col);
		matrix.add(row, col, currentMove);
		rowMoved = row;
		colMoved = col;
	}

	private void validate(int value) {
		if (value < 0 || value > 2) {
			throw new IllegalArgumentException(" This value is not permitted");
		}
	}
	
	public void instruction() {
		System.out.println(" **** INSTRUCTIONS **** ");
		System.out.println(" User has to enter row and column ");
		System.out.println(" Postion starts from (0,0) to (2,2) ");
	}

}
