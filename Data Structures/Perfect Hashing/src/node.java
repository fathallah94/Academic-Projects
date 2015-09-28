
public class node {
	public int value;
	public boolean dummy;
	public node(){
		dummy=true;
	}
	public void set(int x){
		value = x;
		dummy = false;
	}
	public void reset(){
		value=0;
		dummy=true;
	}
}
