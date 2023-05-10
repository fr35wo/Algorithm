package hw9_1;

import java.util.Scanner;

public class Main_201914068 {
	public static void main(String[] args) {
		System.out.println("hw9_1 : 홍석현");

		Scanner scanner = new Scanner(System.in);

		int m = scanner.nextInt(); // 배낭 허용 무게

		int n = scanner.nextInt(); // 물건 개수

		int[] weights = new int[n];
		int[] values = new int[n];

		for (int i = 0; i < n; i++) {
			weights[i] = scanner.nextInt(); // 물건 무게
			values[i] = scanner.nextInt(); // 물건 가지
		}

		int[][] d = new int[n + 1][m + 1]; //문제 해결 위한 2차원 배열

		for (int i = 1; i <= n; i++) { //고려중인 물건
			for (int j = 1; j <= m; j++) { //고려중인 무게
				if (weights[i - 1] <= j) { //현재 무게가 배낭의 무게 제한보다 작으면
					//ex)물건 1~2중에 무게 7이하 최대가치 = d[2,7]
					//	 물건 1~2중에 무게 5이하 최대가치 + 35 = d[2,5] + 35 둘 중 최대가치 고름
					d[i][j] = Math.max(values[i - 1] + d[i - 1][j - weights[i - 1]], d[i - 1][j]);
				} else { //현재 물건 배낭에 못넣음
					d[i][j] = d[i - 1][j];
				}
			}
		}

		int maxValue = d[n][m];
		System.out.println("최대 가치= " + maxValue);


	}
}
