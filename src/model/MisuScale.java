/*
/*
 * MScale.java
 *
 * Copyright (C) 2009-2010 Claudio Zopfi
 * 
 * Licensed under CC Attribution-Noncommercial-Share Alike 3.0 Germany
 * 
 * See the file license.txt which came with this distribution
 * or http://creativecommons.org/licenses/by-nc-sa/3.0/de/deed.en
 * or http://c1audio.com/by-nc-sa/
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 * 
 */
package model;

public class MisuScale {
	int[] notes;
	int base;
	int range;
	int[] scale;

	public static final int[] DUR = { 2, 2, 2, 1, 2, 2, 1 };
	public static final int[] MOLL = { 2, 1, 2, 1, 2, 2, 1 };
	public static final int[] PENTA = { 2, 3, 4, 3 };
	public static final int[] HEXA = { 2, 3, 2, 2, 3 };
	public static final int[] HEPTA = { 2, 3, 1, 2, 1, 3 };
	public static final int[] PENTA_DUR = { 2, 2, 3, 2, 3 };
	public static final int[] PENTA_MOL = { 3, 2, 2, 3, 2 };
	public static final int[] PENTA_KUOMI = { 1, 4, 4, 1, 2 };
	public static final int[] PENTA_IWATO = { 1, 4, 1, 4, 2 };
	public static final int[] PENTA_PELOG = { 1, 2, 4, 1, 4 };
	public static final int[] PENTA_HYOJO = { 2, 3, 2, 2, 3 };
	public static final int[] PENTA_CHINA = { 4, 2, 1, 4, 1 };
	public static final int[] PENTA_EGYPT = { 2, 3, 2, 3, 2 };
	public static final int[][] scales = { DUR, MOLL, PENTA, HEXA, HEPTA,
			PENTA_DUR, PENTA_MOL, PENTA_KUOMI, PENTA_IWATO, PENTA_PELOG,
			PENTA_HYOJO, PENTA_CHINA, PENTA_EGYPT };


	public MisuScale(int base, int range, int[] scale) {
		super();
		this.base = base;
		this.range = range;
		this.scale = scale;
		this.init();
	}

	private void init() {
		this.notes = new int[this.scale.length * this.range];
		this.notes[0] = this.base;
		for (int i = 1; i < notes.length; i++) {
			this.notes[i] = this.notes[i - 1]
					+ this.scale[(i - 1) % this.scale.length];
		}
	}

	public int getNote(int i) {
		return this.notes[i];
	}

	public int getLenth() {
		return this.notes.length;
	}

	public void setBase(int b) {
		base = b;
		init();
	}

	public int getBase() {
		return base;
	}

	public void setScale(int val) {
		this.scale = scales[val];
		init();
	}

	public void setRange(int val) {
		this.range = val;
		init();
	}
}
