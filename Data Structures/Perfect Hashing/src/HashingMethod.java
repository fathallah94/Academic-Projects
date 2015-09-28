import java.util.ArrayList;
import java.util.Random;

public class HashingMethod {
	
	public int[] createH(int b){
		Random rand = new Random();
		int[] h = new int[b];
		for(int i=0;i<b;i++)
			h[i]=rand.nextInt();
		return h;
	}
	
	public int HX (int[] h , int x){
		int res=0;
		for(int i=0;i<h.length;i++){
			int temp = (h[i]&x);
			int cnt=0;
			for(int j=0;j<32;j++){
				if(((1<<j)&temp)!=0)
					cnt++;
			}
			if(cnt%2==1)
				res = (res|(1<<i));
		}
		return res;
	}
	
}
