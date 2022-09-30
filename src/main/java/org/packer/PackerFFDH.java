package org.packer;

import java.util.ArrayList;
import java.util.List;

class PackerFFDH extends Packer
{
	private List<StripLevel> levels = new ArrayList<StripLevel>(1);
	int top = 0;
	
	public PackerFFDH(int stripWidth, List<? extends Rectangle> rectangles){
		super(stripWidth, rectangles);
	}
	
	@Override
	public void pack() 
	{
		this.sortByNonIncreasingHeight(rectangles);
		for (Rectangle r : rectangles){
			boolean fitsOnALevel = false;
			for (StripLevel level : levels){
				fitsOnALevel = level.fitRectangle(r);
				if (fitsOnALevel) break;
			}
			if (!fitsOnALevel){
				StripLevel level = new StripLevel(stripWidth, top);
				level.fitRectangle(r);
				levels.add(level);
				top += r.getHeight();
			}
		}
		
	}
}
