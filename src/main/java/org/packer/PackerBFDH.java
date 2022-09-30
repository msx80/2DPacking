package org.packer;

import java.util.ArrayList;
import java.util.List;

public class PackerBFDH extends Packer {
	private List<StripLevel> levels;
	
	public PackerBFDH(int stripWidth, List<? extends Rectangle> rectangles){
		super(stripWidth, rectangles);
		this.levels = new ArrayList<StripLevel>();
	}
	@Override
	public void pack() {
		int top = 0;
		this.sortByNonIncreasingHeight(this.rectangles);
		for (Rectangle r : this.rectangles){
			StripLevel levelWithSmallestResidual = null;
			for (StripLevel level : this.levels){
				if (level.canFit(r)){
					if (levelWithSmallestResidual != null && levelWithSmallestResidual.availableWidth() > level.availableWidth()){
						levelWithSmallestResidual = level;
					}else if (levelWithSmallestResidual == null){
						levelWithSmallestResidual = level;
					}
				}
			}
			if (levelWithSmallestResidual == null){
				StripLevel level = new StripLevel(stripWidth, top);
				level.fitRectangle(r);
				this.levels.add(level);
				top += r.getHeight();
			}else{
				levelWithSmallestResidual.fitRectangle(r);
			}
			
		}
		
	}
}
