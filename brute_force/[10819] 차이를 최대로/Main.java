import java.io.*;
import java.util.*;

public class Main {
	public static int n, ans;
	public static int[] arr;
	
	// 연산 결과 구하기
	// |A[0] - A[1]| + |A[1] - A[2]| + ... + |A[N-2] - A[N-1]|
	public static int getResult() {
		int sum = 0;
		for(int i = 0; i <= n-2; i++) {
			sum += Math.abs(arr[i] - arr[i+1]);
		}
		return sum;
	}
	
	// 다음 순열의 존재 여부를 반환
	public static boolean hasNextPermutation() {
		// 1. arr[i-1]로 시작하는 마지막 순열 찾기
		// arr[i-1] < arr[i]을 만족하는 가장 큰 i를 찾는다.
		int i = n-1;
		while(i > 0 && arr[i-1] >= arr[i]) i -= 1;
		
		// 현재 순열은 마지막 순열
		if(i <= 0) return false;
		
		// 2. 다음 순열 찾기
		// 1) arr[i-1]에 들어갈 값 찾기
		// - arr[i-1] 뒤에 있는 수들 중 arr[i-1] < arr[j]을 만족하는 가장 작은 arr[j]값을 찾는다.
		//   -> 즉, arr[i-1] < arr[j] (i <= j)를 만족하는 가장 큰 j를 찾는다.
		int j = n-1;
		while(arr[i-1] >= arr[j]) j -= 1;
		
		// 2) arr[i-1]과 arr[j]를 교환하기
		swap(i-1, j);
		
		// 3) arr[i] ~ arr[n-1]까지 뒤집기 (arr[i]와 arr[j]를 교환)
		j = n-1;
		while(i < j) {
			swap(i, j);
			i += 1;
			j -= 1;
		}
		
		return true; // 현재 순열은 다음 순열이 존재한다.
	}
	
	public static void swap(int i, int j) {
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
		
		// 주어진 배열의 모든 순열에 대해 연산을 진행하여 최댓값을 구하기
		
		Arrays.sort(arr); // 오름차순 정렬을 통해 첫 순열부터 확인해야 함
		
		// 모든 순열에 대해 확인하기
		do {
			ans = Math.max(ans, getResult()); // 최댓값 갱신
		} while(hasNextPermutation()); // 다음 순열이 존재하는 동안 반복

		System.out.println(ans);
	}
}
