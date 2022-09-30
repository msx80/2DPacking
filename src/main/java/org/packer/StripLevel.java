package org.packer;


class StripLevel
{
	private int width, availableWidth, top;
	
	public StripLevel(int width, int top){
		this.width = width;
		this.availableWidth = width;
		this.top = top;
	}
	
	public boolean fitRectangle(Rectangle r){
		int leftOver = availableWidth - r.getWidth();
		if (leftOver >= 0){
			r.setLocation(width - availableWidth, top);
			this.availableWidth = leftOver;
			return true;
		}
		return false;
	}
	
	public int availableWidth(){
		return this.availableWidth;
	}
	
	public boolean canFit(Rectangle r){
		if (this.availableWidth - r.getWidth() >= 0) return true;
		return false;
	}
}