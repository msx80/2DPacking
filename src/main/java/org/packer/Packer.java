package org.packer;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class Packer {
	protected int stripWidth;
	protected List<? extends Rectangle> rectangles;
	
	public Packer(int stripWidth, List<? extends Rectangle> rectangles){
		this.stripWidth = stripWidth;
		this.rectangles = rectangles;
	}
	
	public static void pack(List<? extends Rectangle> rectangles, Algorithm algorithm, int stripWidth)
	{
		Packer packer;
		switch(algorithm){
		case FIRST_FIT_DECREASING_HEIGHT:
			packer = new PackerFFDH(stripWidth, rectangles);
			packer.pack();
			return;
		case NEXT_FIT_DECREASING_HEIGHT:
			packer = new PackerNFDH(stripWidth, rectangles);
			packer.pack();
			return;
		case BEST_FIT_DECREASING_HEIGHT:
			packer = new PackerBFDH(stripWidth, rectangles);
			packer.pack();
			return;
		default:
			throw new RuntimeException("No such algorithm");
		}
	}
	
	public abstract void pack();
	
	protected void sortByNonIncreasingHeight(List<? extends Rectangle> rectangles){
		Collections.sort(rectangles, new NonIncreasingHeightRectangleComparator());
	}
	protected void sortByNonIncreasingWidth(List<? extends Rectangle> rectangles){
		Collections.sort(rectangles, new NonIncreasingWidthRectangleComparator());
	}
	
	private class NonIncreasingHeightRectangleComparator implements Comparator<Rectangle>{
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return Integer.compare(o2.getHeight(), o1.getHeight());
		}		
	}
	private class NonIncreasingWidthRectangleComparator implements Comparator<Rectangle>{
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return Integer.compare(o2.getWidth(), o1.getWidth());
		}		
	}
	
	public enum Algorithm{
		FIRST_FIT_DECREASING_HEIGHT,
		NEXT_FIT_DECREASING_HEIGHT,
		BEST_FIT_DECREASING_HEIGHT
	}
	
	public static Algorithm[] getAllAlgorithms(){
		Algorithm[] algorithms = {Algorithm.FIRST_FIT_DECREASING_HEIGHT,
				Algorithm.NEXT_FIT_DECREASING_HEIGHT,
				Algorithm.BEST_FIT_DECREASING_HEIGHT};
		return algorithms;
	}
}
