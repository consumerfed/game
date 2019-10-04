/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 1997-2017 Javabelazy and/or its affiliates. All rights reserved.
 *
 */
/**
 * Matrix : The Foundation class for this project
 * 
 */
package com.konzern.mxgames;

import java.util.NoSuchElementException;

/**
 * @author konzernites
 * @since 1.0
 *
 */
public class Matrix<E> extends AbstractMatrix<E> {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 8683452581122892189L;

	private final int MAX_COLUMN;
	private final int MAX_ROW;
	private final int MAX_CAPACITY;
	public static final char EMPTY_DATA = '\0';
	public static boolean isEditable = false;

	private E elementData[][];
	private int capacity = 0;

	public Matrix(int row, int column) {
		this.MAX_COLUMN = column;
		this.MAX_ROW = row;
		this.MAX_CAPACITY = MAX_ROW * MAX_COLUMN;
		intialize();
	}

	@SuppressWarnings("unchecked")
	private void intialize() {
		this.elementData = (E[][]) new Object[MAX_ROW][MAX_COLUMN];
	}

	public Matrix() {
		this(2, 2);
	}

	public void add(int row, int column, E value) throws IllegalAccessException {
		if (row > MAX_ROW || column > MAX_COLUMN)
			throw new IllegalAccessException("Illegal capacity");
		else if(Matrix.isEditable && isElementExist(row, column)) 
			this.elementData[row][column] = value;
		else if (isFull() || isElementExist(row, column))
			throw new IllegalAccessException("Value already exist");
		else {
			capacity++;
			this.elementData[row][column] = value;
		}
	}

	public E[][] getMatrix() {
		if (isEmpty())
			throw new NoSuchElementException("Matrix is empty");
		else
			return this.elementData;
	}
	
	public E getValue(int row, int column) {
		if (isEmpty())
			return null;
		else if(isElementExist(row, column)) 
			return this.elementData[row][column];
		else
			return null;
	}

	public void remove(int row, int column) {
		if (isEmpty())
			throw new NoSuchElementException("Cannot remove value from an empty matrix");
		else {
			this.elementData[row][column] = null;
			capacity--;
		}
	}

	public void removeAll() {
		if (isEmpty())
			throw new NoSuchElementException("Cannot remove value from an empty matrix");
		else {
			// TODO remove all elements from matrix
		}
	}

	public void print() {
		if (isEmpty())
			throw new NoSuchElementException("Cannot display an empty matrix");
		else {
			for (int r = 0; r < MAX_ROW; r++) {
				for (int c = 0; c < MAX_COLUMN; c++) {
					if (null == elementData[r][c])
						System.out.print("  | ");
					else
						System.out.print(elementData[r][c] + " | ");
				}
				System.out.println("");
			}
		}
	}

	public Dimensions dimension() {
		Dimensions dimensions = new Dimensions();
		dimensions.setRow(MAX_ROW);
		dimensions.setColumn(MAX_COLUMN);
		return dimensions;
	}

	private boolean isEmpty() {
		if (capacity == 0)
			return true;
		else
			return false;
	}

	public boolean isElementExist(int row, int column) {
		if (isEmpty())
			return false;
		else if (null != elementData[row][column])
			return true;
		else
			return false;
	}

	private boolean isFull() {
		if (capacity >= MAX_CAPACITY)
			return true;
		else
			return false;
	}

	public int size() {
		return capacity;
	}

}
