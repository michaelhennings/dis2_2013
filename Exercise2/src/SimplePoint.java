
public class SimplePoint<T> {
	T x;
	T y;
	
	public SimplePoint(T x, T y){
		this.x = x;
		this.y = y;
	}
	
	public T getX(){
		return x;
	}
	
	public T getY(){
		return y;
	}
}
