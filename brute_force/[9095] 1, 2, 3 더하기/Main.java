import java.io.*;

public class Main {
	
	public static int n, cnt;
	
	public static void recur(int sum) {
		if(sum > n) return;
		
		if(sum == n) {
			cnt += 1;
			return;
		}
		
		for(int i = 1; i <= 3; i++) {
			recur(sum + i);
		}
	}
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		while(t-- > 0) {
			n = Integer.parseInt(br.readLine());
			cnt = 0;
			recur(0);
			System.out.println(cnt);
		}		
	}
	
}