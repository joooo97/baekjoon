import java.io.*;

public class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] d = new int[n + 1];
		
		// d[i] = i를 1로 만드는 데 필요한 연산의 최소 횟수
		// d[1] = 0;
		for(int i = 2; i <= n; i++) {
			// 연산의 최소 횟수를 비교할 값 지정
			// 1을 빼는 연산은 항상 가능하므로 1을 빼는 경우의 연산 횟수로 지정
			d[i] = d[i - 1] + 1;
			
			if(i % 3 == 0)
				d[i] = Math.min(d[i], d[i / 3] + 1);
			
			if(i % 2 == 0)
				d[i] = Math.min(d[i], d[i / 2] + 1);
		}
		System.out.println(d[n]);
	}
}
