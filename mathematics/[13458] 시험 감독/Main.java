import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n]; // 응시자 수 배열
		
		// 응시자 수 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		long cnt = 0; // 필요한 감독관 수
		for(int i = 0; i < n; i++) {
			// 총감독관 감시
			arr[i] -= b;
			cnt += 1;
			
			// 부감독관 감시
			if(arr[i] > 0) cnt += Math.ceil(arr[i] / (double)c);
			
			/*
			if(arr[i] > 0) {
				if(arr[i] % c == 0)
					cnt += (arr[i] / c);
				else
					cnt += (arr[i] / c + 1);
			}
			*/
			
		}
		
		System.out.println(cnt);
	}
}
