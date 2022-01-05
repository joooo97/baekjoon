import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] a = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		// d[i]: i번째 수로 끝나는 증가하는 부분 수열의 길이
		int[] d = new int[n];
		for(int i = 0; i < n; i++) {
			d[i] = 1; // 부분 수열의 길이를 1로 초기화 (현재 i번째 수만 부분 수열에 존재)
			for(int j = 0; j < i; j++) { // 앞에 있는 수들에 대해 확인
				if(a[j] < a[i] && d[i] < d[j] + 1) 
					d[i] = d[j] + 1;
			}
		}
		
		// 가장 긴 증가하는 부분 수열의 길이 구하기
		int ans = 0;
		for(int i = 0; i < n; i++) {
			if(ans < d[i]) ans = d[i];
		}
		System.out.println(ans);
	}
}