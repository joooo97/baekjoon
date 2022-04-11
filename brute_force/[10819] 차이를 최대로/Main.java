import java.io.*;
import java.util.*;

public class Main {
	public static int n, ans;
	public static int[] A;
	
	public static int getValue(int[] arr) {
		int sum = 0;
		for(int i = 0; i <= n-2; i++) {
			sum += Math.abs(arr[i] - arr[i+1]);
		}
		return sum;
	}
	
	public static boolean hasNextPermutation(int[] arr) {
		// 1. arr[0] ... arr[i-1]로 시작하는 마지막 순열 찾기
		// arr[i-1] < arr[i]를 만족하는 가장 큰 i 찾기
		int i = n-1;
		while(i > 0 && arr[i-1] >= arr[i]) i -= 1;
		
		if(i <= 0) return false; // 현재 순열은 마지막 순열 (다음 순열 존재x)
		
		// 2. 다음 순열 찾기
		// 1) 다음으로 arr[i-1]에 들어갈 값 찾기
		// arr[i-1] < arr[j] (i <= j)을 만족하는 가장 큰 j 찾기
		int j = n-1;
		while(arr[i-1] >= arr[j]) j -= 1;
		
		// 2) arr[i-1]과 arr[j] 교환
		swap(arr, i-1, j);
		
		// 3) arr[i] ~ arr[n-1]까지 뒤집기 (arr[i]와 arr[j] 교환)
		j = n-1;
		while(i < j) {
			swap(arr, i, j);
			i += 1;
			j -= 1;
		}
		
		return true; // 현재 순열은 다음 순열이 존재
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		A = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A); // 오름차순 정렬을 통해 첫 순열부터 확인
		
		// 주어진 배열의 모든 순열에 대해 연산 진행하여 최댓값 구하기
		do {
			ans = Math.max(ans, getValue(A));
		} while(hasNextPermutation(A));
		
		System.out.println(ans);
	}
}