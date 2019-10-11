/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 1997-2017 Javabelazy and/or its affiliates. All rights reserved.
 *
 */

/**
 * Dimensions : A class dedicated for dimension of matrix
 */
package com.konzern.mxgames;

/**
 * @author konzernites
 * @since 1.0
 *
 */
public class Dimensions {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 8683452581122892188L;

	private int row = 0;
	private int column = 0;

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

}
