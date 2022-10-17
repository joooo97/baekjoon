import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int ans = 0;
		int start = 1;
		for(int len = 1; start <= n; len++) {
			int end = start * 10 - 1;
			if(n < end) end = n;
			
			ans += (end - start + 1) * len; 
			
			start *= 10;
		}
		System.out.println(ans);
	}

}