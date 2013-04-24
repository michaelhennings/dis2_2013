public class WindowSystem extends GraphicsEventSystem {

	private int width, height;
	
	public WindowSystem(int width, int height) {
		super(width, height);
		this.width = width;
		this.height = height;
		this.setTitle("Desktop");
	}
	
	private SimplePoint<Integer> abstractToDesktopCoord(SimplePoint<Float> abstractCoord){
		int x = (int)(width * abstractCoord.getX());
		int y = (int)(height * abstractCoord.getY());
		return new SimplePoint<Integer>(x, y);
	}

	@Override
	public void handlePaint(){
		//TODO
		//Draw line gehört hier eigentlich nicht hin
		drawLine(0.2f, 0.3f, .8f, .7f);
	}
	
	public void drawLine(float StartX, float StartY, float EndX, float EndY){
		SimplePoint<Float> abstractStartPoint = 
				new SimplePoint<Float>(StartX, StartY);
		SimplePoint<Float> abstractEndPoint = 
				new SimplePoint<Float>(EndX, EndY);
		
		SimplePoint<Integer> desktopStartPoint = 
				abstractToDesktopCoord(abstractStartPoint);
		SimplePoint<Integer> desktopEndPoint = 
				abstractToDesktopCoord(abstractEndPoint);
		
		this.drawLine(desktopStartPoint.getX(), desktopStartPoint.getY(), 
				desktopEndPoint.getX(), desktopEndPoint.getY());
	}
	
	public void addWindow(SimpleWindow window){
		//TODO
	}
	
	public void removeWindow(SimpleWindow window){
		//TODO
	}
}
