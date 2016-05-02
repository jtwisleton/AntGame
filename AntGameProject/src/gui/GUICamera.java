
package gui;


public class GUICamera {
	private int xPos;
	private int yPos;
        private float zoom;
	private float boardZoom;
	private int height;
	private int width;
        private float controlZoom;
	
	public GUICamera(int height, int width, float zoom, float controlZoom, int xOffset, int yOffset){
		this.height = height;
		this.width = width;
		this.boardZoom = zoom;
                this.controlZoom = controlZoom;
		xPos = xOffset; 
		yPos = yOffset; 
                this.zoom = boardZoom;
	}
	
	public int getXPos(){
		return (int) (xPos + (width / 2 * controlZoom));
	}
	
	public int getYPos(){
		return (int) (yPos + (height / 2 * controlZoom));
	}
	
	public float getZoom(){
		return zoom;
	}
	
	public void setXPos(int xPos){
		this.xPos = xPos;
	}
	
	public void setYPos(int yPos){
		this.yPos = yPos;
	}
	
	public void decrementXPos(){
            if(10 * controlZoom < 1){
                xPos -= 1;
            } else {
                xPos -= 10 * controlZoom;
            }
		
	}
	
	public void incrementXPos(){
            if(10 * controlZoom < 1){
                xPos += 1;
            } else {
                xPos += 10 * controlZoom;
            }
		
	}
	
	public void decrementYPos(){
            if(10 * controlZoom < 1){
                yPos -= 1;
            } else {
		yPos -= 10 * controlZoom;
            }
	}
	
	public void incrementYPos(){
            if(10 * controlZoom < 1){
                yPos += 1;
            } else {
                yPos += 10 * controlZoom;
            }
	}
	
	public void incrementZoom(){
            if(boardZoom < 16){
                boardZoom = boardZoom * 2;
                controlZoom = controlZoom / 2;
            }
	}
	
	public void decrementZoom(){
            if(boardZoom > 0.25){
                boardZoom = boardZoom / 2;
                controlZoom = controlZoom * 2;
            }
	}
        
        public void setMenuZoom(){
            zoom = controlZoom;
        }
        
        public void setBoardZoom(){
            zoom = boardZoom;
        }
        
        
	
}