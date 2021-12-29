import java.io.*;
import java.util.*;

// N개의 카드를 구매하기 위해 지불해야 하는 금액의 최솟값
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] p = new int[n + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] d = new int[n + 1];
		for(int i = 1; i <= n; i++) { // d[i]: 카드 i개 구매 시 최소 금액
			d[i] = p[i]; // i개의 카드팩 하나의 금액으로 초기화
			for(int j = 1; j <= i; j++) { // p[j]: j개 카드 팩의 금액
				d[i] = Math.min(d[i], p[j] + d[i - j]);
			}
		}
		System.out.println(d[n]);
	}
}