import java.io.*;

/*
 * 배열 d의 크기를 1001이 아닌 n+1로 선언하는 경우
 * - 주어진 n이 1일 경우 d[2] = 2; 부분에서 에러
 * - 배열 인덱스가 1까지이기 때문
 */
public class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] d = new int[1001];		
		
		// d[n] = 2 x n 크기의 직사각형을 채우는 방법의 수
		// d[n] = d[n-1] + d[n-2]
		d[1] = 1;
		d[2] = 2;
		for(int i = 3; i <= n; i++) {
			d[i] = (d[i - 1] + d[i - 2]) % 10007;
		}
		System.out.println(d[n]);
	}
}
