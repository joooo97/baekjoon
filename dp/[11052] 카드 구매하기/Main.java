import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] p = new int[n + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		// d[n]: 카드 n개를 갖기 위해 지불해야 하는 금액의 최댓값
		int[] d = new int[n + 1]; 
		for(int i = 1; i <= n; i++) { // i: 배열 d의 인덱스
			for(int j = 1; j <= i; j++) { // j: 각 카드팩의 카드 개수
				d[i] = Math.max(d[i], d[i - j] + p[j]);
			}
		}
		System.out.println(d[n]);
	}
}