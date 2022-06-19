import java.io.*;
import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] arr = new int[9];
		int sum = 0; // 9명의 난쟁이들의 키의 합
		
		for(int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			sum += arr[i];
		}
		
		Arrays.sort(arr);
		
		// 두 명의 난쟁이들을 지정하여 키의 합에서 두 난쟁이들의 키를 뺐을 때 100이 된다면 출력 후 종료
		for(int i = 0; i < 9; i++) { // 난쟁이 1
			for(int j = i + 1; j < 9; j++) { // 난쟁이 2
				if(sum - arr[i] - arr[j] == 100) {
					for(int k = 0; k < 9; k++) {
						if(k == i || k == j) continue;
						
						sb.append(arr[k]).append("\n");
					}
					System.out.println(sb);
					System.exit(0); // 출력 후 종료
				}
			}
		}
	}
}