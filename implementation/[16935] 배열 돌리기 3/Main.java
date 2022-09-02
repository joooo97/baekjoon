import java.io.*;
import java.util.*;

public class Main {
	public static int[][] arr;
	
	// 연산 1: 상하 반전
	public static void cal1() {
		int n = arr.length;
		int m = arr[0].length;
		int[][] newArr = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				newArr[i][j] = arr[n-1-i][j];
			}
		}
		arr = newArr;
	}
	
	// 연산 2: 좌우 반전
	public static void cal2() {
		int n = arr.length;
		int m = arr[0].length;
		int[][] newArr = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				newArr[i][j] = arr[i][m-1-j];
			}
		}
		arr = newArr;
 	}
	
	// 연산 3: 오른쪽으로 90도 회전
	public static void cal3() {
		int n = arr.length;
		int m = arr[0].length;
		// 배열 크기 변경됨 (행, 열 범위 변경)
		int[][] newArr = new int[m][n];
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				newArr[i][j] = arr[n-1-j][i];
			}
		}
		arr = newArr;
	}
	
	// 연산 4: 왼쪽으로 90도 회전
	public static void cal4() {
		int n = arr.length;
		int m = arr[0].length;
		// 배열 크기 변경됨
		int[][] newArr = new int[m][n];
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				newArr[i][j] = arr[j][m-1-i];
			}
		}
		arr = newArr;
	}
	
	// 연산 5: 1은 2로, 2는 3으로, 3은 4로, 4는 1로 이동
	// - 위치: 1번(i, j), 2번(i, j+m/2) 3번(i+n/2, j+m/2), 4번(i+n/2, j)
	public static void cal5() {
		int n = arr.length;
		int m = arr[0].length;
		int[][] newArr = new int[n][m];
		
		for(int i = 0; i < n/2; i++) {
			for(int j = 0; j < m/2; j++) {
				newArr[i][j+m/2] = arr[i][j]; // 1 -> 2로 이동
				newArr[i+n/2][j+m/2] = arr[i][j+m/2]; // 2 -> 3으로 이동
				newArr[i+n/2][j] = arr[i+n/2][j+m/2]; // 3 -> 4로 이동
				newArr[i][j] = arr[i+n/2][j]; // 4 -> 1로 이동
			}
		}
		arr = newArr;
	}
	
	// 연산 6: 1은 4로, 4는 3으로, 3은 2로, 2는 1로 이동
	// - 위치: 1번(i, j), 2번(i, j+m/2) 3번(i+n/2, j+m/2), 4번(i+n/2, j)
	public static void cal6() {
		int n = arr.length;
		int m = arr[0].length;
		int[][] newArr = new int[n][m];
		
		for(int i = 0; i < n/2; i++) {
			for(int j = 0; j < m/2; j++) {
				newArr[i+n/2][j] = arr[i][j]; // 1 -> 4
				newArr[i+n/2][j+m/2] = arr[i+n/2][j]; // 4 -> 3
				newArr[i][j+m/2] = arr[i+n/2][j+m/2]; // 3 -> 2
				newArr[i][j] = arr[i][j+m/2]; // 2 -> 1
			}
		}
		arr = newArr;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		
		// 배열 입력 받기
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 연산 진행
		st = new StringTokenizer(br.readLine());
		while(r-- > 0) {
			int no = Integer.parseInt(st.nextToken());
			
			switch(no) {
			case 1:
				cal1();
				break;
			case 2:
				cal2();
				break;
			case 3:
				cal3();
				break;
			case 4:
				cal4();
				break;
			case 5:
				cal5();
				break;
			case 6:
				cal6();
				break;
			}
		}
		
		// 최종 연산 결과 출력
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0;  j < arr[0].length; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}