/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 1997-2021 Javabelazy and/or its affiliates. All rights reserved.
 *
 */
/**
 * Computer Moves for user move in tik toc toe
 * Developed by consumerfed kozhikode I T section
 * Version 1.0 
 * consfedkozhikode@gmail.com
 * 
 */
package com.konzern.mxgames.tictactoe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.konzern.mxgames.Matrix;

/**
 * @author konzernites
 * @since 1.0
 *
 */
public class ComputerMove {

	private static final long serialVersionUID = 8683452581122892181L;
	private int MATRIX_SIZE = 3;
	private static final char EMPTY_DATA = ' ';
	private Map<String, Integer> priorityPosition = null;
	private Matrix<Character> matrix = null;


	public ComputerMove(Matrix<Character> matrix) {
		this.matrix = matrix;
	}

	public Matrix<Character> computerMove(int rowMoved, int colMoved) throws IllegalAccessException {

		if (TikTocToe.moveCount <= 2) {
			matrix = generateFirstCompMove();
		} else {
			matrix = generateCompMove(rowMoved, colMoved);
		}
		return matrix;
	}

	private Matrix<Character> generateCompMove(int rowMoved, int colMoved) throws IllegalAccessException {
		priorityPosition = new HashMap<>();
		boolean isInserted = false;
		for (int row = 0; row < MATRIX_SIZE; row++) {
			for (int col = 0; col < MATRIX_SIZE; col++) {

				if (!matrix.isElementExist(row, col)) {
					isInserted = findPriority(row, col);
					if (isInserted)
						return matrix;
				}

			}
		}

		if (!isInserted) {
			String key = maxUsingCollectionsMaxAndLambda(priorityPosition);
			int r = Integer.parseInt(key.substring(0, 1));
			int c = Integer.parseInt(key.substring(1, 2));

			if (!matrix.isElementExist(r, c))
				matrix.add(r, c, TikTocToe.COMP_MOVE);

		}
		return matrix;
	}

	private Matrix<Character> generateFirstCompMove() throws IllegalAccessException {
		int row = 1;
		int col = 1;
		
		if(matrix.isElementExist(row, col)) {
			Random r = new Random();
			row = r.nextInt(3);
			col = r.nextInt(3);
			col = (row == 1 && col == 1) ? col + 1 : col;
			matrix.add(row, col, TikTocToe.COMP_MOVE);
			
		}else {
			matrix.add(1, 1, TikTocToe.COMP_MOVE);
		}
		
	
		return matrix;
	}

	private boolean findPriority(int row, int col) throws IllegalAccessException {
		boolean isInserted = true;
		if (diagonalOne(0, 0)) {
			isInserted = true;
		} else if (diagonalTwo(0, MATRIX_SIZE - 1)) {
			isInserted = true;
		} else if (rowCheck(row, col)) {
			matrix.add(row, col, TikTocToe.COMP_MOVE);
		} else if (colCheck(row, col)) {
			matrix.add(row, col, TikTocToe.COMP_MOVE);
		} else {
			isInserted = false;
		}
		return isInserted;
	}

	private boolean colCheck(int row, int col) {
		boolean isPrior = false;
		boolean isSafe = false;
		int loopCount = 1;
		int value = 0;

		int r = row;
		int c = col;
		while (loopCount < MATRIX_SIZE) {
			if (r + 1 == MATRIX_SIZE) {
				r = 0;
			} else {
				r++;
			}
			if (null != matrix.getValue(r, c) && TikTocToe.COMP_MOVE == matrix.getValue(r, c)) {
				isSafe = true;
				break;
			} else if (null != matrix.getValue(r, c) && TikTocToe.USER_MOVE == matrix.getValue(r, c)) {
				value++;
			}
			loopCount++;
		}

		if (!isSafe) {
			String key = String.valueOf(row + "" + col);
			priorityPosition.put(key, value);
		}

		if (value == 2 && !isSafe) {
			isPrior = true;
		}
		return isPrior;
	}

	private boolean rowCheck(int row, int col) {
		boolean isPrior = false;
		boolean isSafe = false;
		int loopCount = 1;
		int value = 0;

		int r = row;
		int c = col;
		while (loopCount < MATRIX_SIZE) {
			c = (c + 1 == MATRIX_SIZE) ? 0 : c + 1;
			if (null != matrix.getValue(r, c) && TikTocToe.COMP_MOVE == matrix.getValue(r, c)) {
				isSafe = true;
				break;
			} else if (null != matrix.getValue(r, c) && TikTocToe.USER_MOVE == matrix.getValue(r, c)) {
				value++;
			}
			loopCount++;
		}

		if (!isSafe) {
			String key = String.valueOf(row + "" + col);
			priorityPosition.put(key, value);
		}

		if (value == 2 && !isSafe) {
			isPrior = true;
		}
		return isPrior;
	}

	private boolean diagonalTwo(int row, int col) throws IllegalAccessException {
		boolean isPrior = false;
		boolean isSafe = false;
		int loopCount = 1;
		int value = 0;
		int r = 0;
		int c = 0;
		while (loopCount <= MATRIX_SIZE) {
			if (null != matrix.getValue(row, col) && TikTocToe.COMP_MOVE == matrix.getValue(row, col)) {
				isSafe = true;
				break;
			} else if (null != matrix.getValue(row, col) && TikTocToe.USER_MOVE == matrix.getValue(row, col)) {
				value++;
			}
			if (!matrix.isElementExist(row, col)) {
				r = row;
				c = col;
			}
			row++;
			col--;
			loopCount++;
		}

		if (!isSafe) {
			String key = String.valueOf(r + "" + c);
			priorityPosition.put(key, value);
		}

		if (value == 2 && !isSafe) {
			matrix.add(r, c, TikTocToe.COMP_MOVE);
			isPrior = true;
		}
		return isPrior;
	}

	private boolean diagonalOne(int row, int col) throws IllegalAccessException {
		boolean isPrior = false;
		boolean isSafe = false;
		int loopCount = 1;
		int value = 0;
		int r = 0;
		int c = 0;
		while (loopCount <= MATRIX_SIZE) {
			if (null != matrix.getValue(row, col) && TikTocToe.COMP_MOVE == matrix.getValue(row, col)) {
				isSafe = true;
				break;
			} else if (null != matrix.getValue(row, col) && TikTocToe.USER_MOVE == matrix.getValue(row, col)) {
				value++;
			}

			if (!matrix.isElementExist(row, col)) {
				r = row;
				c = col;
			}
			row++;
			col++;
			loopCount++;
		}

		if (!isSafe) {
			String key = String.valueOf(r + "" + c);
			priorityPosition.put(key, value);
		}

		if (value == 2 && !isSafe) {
			matrix.add(r, c, TikTocToe.COMP_MOVE);
			isPrior = true;
		}
		return isPrior;
	}

	private <K, V extends Comparable<V>> K maxUsingCollectionsMaxAndLambda(Map<K, V> map) {
		Entry<K, V> maxEntry = Collections.max(map.entrySet(),
				(Entry<K, V> e1, Entry<K, V> e2) -> e1.getValue().compareTo(e2.getValue()));
		return maxEntry.getKey();
	}

}
