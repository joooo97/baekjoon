import java.io.*;

public class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] d = new int[11];

		// d[n] = n을 1, 2, 3의 합으로 나타내는 방법의 수
		// d[n] = d[n-1] + d[n-2] + d[n-3]
		d[1] = 1;
		d[2] = 2;
		d[3] = 4;
		for(int i = 4; i < 11; i++) {
			d[i] = d[i-1] + d[i-2] + d[i-3];
		}
		
		int t = Integer.parseInt(br.readLine());
		while(t-- > 0) {
			int n = Integer.parseInt(br.readLine());			
			sb.append(d[n]).append("\n");
		}
		
		System.out.println(sb);
	}
}