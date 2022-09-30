package org.packer;

import java.util.List;

class PackerNFDH extends Packer {
	private StripLevel currentLevel;
	
	public PackerNFDH(int stripWidth, List<? extends Rectangle> rectangles){
		super(stripWidth, rectangles);
	}

	@Override
	public void pack() {
		this.sortByNonIncreasingHeight(rectangles);
		int top = 0;
		for (Rectangle r : rectangles){
			if (currentLevel == null || !currentLevel.fitRectangle(r)){
				currentLevel = new StripLevel(this.stripWidth, top);
				currentLevel.fitRectangle(r);
				top += r.getHeight();
			}
		}
	}
}
