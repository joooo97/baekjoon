import java.io.*;
import java.util.*;

public class Main {
	public static int n;
	public static int[] arr;
	
	// 다음 순열의 존재 여부 반환
	public static boolean hasNextPermutation() {
		// 1. arr[i-1]로 시작하는 마지막 순열 찾기
		int i = n-1;
		while(i > 0 && arr[i-1] >= arr[i]) i -= 1;

		// 현재 순열은 다음 순열이 존재하지 않는 마지막 순열
		if(i <= 0) return false;
		
		// 2. 다음 순열 찾기 (~로 시작하는 첫 순열 찾기)
		// 1) arr[i-1]에 올 값인 arr[j] 찾기 (i <= j 이면서 arr[i-1] < arr[j]를 만족)
		int j = n-1;
		while(arr[i-1] >= arr[j]) j -= 1;
		
		// 2) arr[i-1]과 arr[j]를 교환하기
		swap(arr, i-1, j);
		
		// 3) 순열 뒤집기 (arr[i] ~ arr[n-1])
		j = n-1;
		while(i < j) {
			swap(arr, i, j);
			i += 1;
			j -= 1;
		}
		return true; // 다음 순열이 존재한다.
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 다음 순열 출력, 만약 사전순으로 마지막에 오는 순열인 경우에는 -1을 출력
		if(hasNextPermutation()) {
			StringBuilder sb = new StringBuilder();
			for(int i : arr) {
				sb.append(i).append(" ");
			}
			System.out.println(sb);
		} else {
			System.out.println(-1);
		}
	}
}
