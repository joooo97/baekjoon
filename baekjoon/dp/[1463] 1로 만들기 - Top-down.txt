import java.util.*;

public class Main {
	public static int[] d;
	
	public static int recur(int n) {
		// 재귀적인 점화식을 통해 답을 구할 수 없는 경우
		if(n == 1) return 0;
		
		// 이전에 n에 대해 구한 값이 있다면 그 값을 리턴
		if(d[n] > 0) return d[n];
		
		// 연산의 최소 횟수를 비교할 값 지정
		// 1을 빼는 연산은 항상 가능하므로 1을 빼는 경우의 연산 횟수로 지정
		d[n] = recur(n - 1) + 1;
		
		if(n % 3 == 0) {
			int temp = recur(n / 3) + 1;
			if(temp < d[n]) d[n] = temp;
		}
		
		if(n % 2 == 0) {
			int temp = recur(n / 2) + 1;
			if(temp < d[n]) d[n] = temp;
		}
		return d[n];
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		d = new int[n + 1];
		
		System.out.println(recur(n));
	}
}