import java.io.*;

public class Main {
	public static int limit = 100000;
	public static int mod = 1000000009;
	public static long[][] d = new long[limit + 1][4];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// d[i]: i를 1, 2, 3의 합으로 나타내는 방법의 수
		// d[i] = d[i][1] + d[i][2] + d[i][3]
		//		= (d[i-1][2] + d[i-1][3]) + (d[i-2][1] + d[i-2][3]) + (d[i-3][1] + d[i-3][2])

		// 점화식이 성립하지 않는 부분은 따로 처리
		d[1][1] = d[2][2] = d[3][3] = 1;
		
		for(int i = 1; i <= limit; i++) {
			if(i > 1)
				d[i][1] = (d[i-1][2] + d[i-1][3]) % mod;
			
			if(i > 2)
				d[i][2] = (d[i-2][1] + d[i-2][3]) % mod;
			
			if(i > 3)
				d[i][3] = (d[i-3][1] + d[i-3][2]) % mod;
		}
		
		int t = Integer.parseInt(br.readLine());
		while(t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			sb.append((d[n][1] + d[n][2] + d[n][3]) % mod).append("\n");
		}
		System.out.println(sb);
	}
}