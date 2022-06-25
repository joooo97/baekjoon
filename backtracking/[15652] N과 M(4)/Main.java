import java.io.*;
import java.util.*;

// - 중복 허용하여 M개 선택 (비내림차순)
// - 비내림차순이므로 순서, 선택 풀이 가능
// - 순서 풀이
//  -> 비내림차순이므로 start 변수 필요
//  -> 중복 허용하므로 check 배열 필요 없음
//  -> 중복 허용하므로 재귀 함수 호출 시 다음 수의 시작 범위는 현재 수 i부터 가능
public class Main {
	public static int n, m;
	public static int[] arr;
	public static StringBuilder sb = new StringBuilder();

	public static void recur(int cnt, int start) {
		if(cnt == m) {
			for(int i = 0; i < m; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = start; i <= n; i++) {
			// 현재 수 i를 선택하는 경우
			arr[cnt] = i;
			recur(cnt + 1, i); // 중복 허용: 다음 수는 i부터 가능
			
			// 현재 수 i를 선택하지 않는 경우 -> 다음 반복문
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[m];

		recur(0, 1);
		System.out.println(sb);
	}
}