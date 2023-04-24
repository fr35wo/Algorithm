package lab5_1;

import java.util.Arrays;
import java.util.Scanner;

public class Main_201914068 {

	public static void main(String[] args) {
		System.out.println("lab5_1:홍석현");

		// 사용자가 원하는 갯수의 정수값을 입력받음
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int[] array = new int[n];

		for (int i = 0; i < n; i++) {
			array[i] = input.nextInt();
		}
		
		Arrays.sort(array);
		
		for (int i = 0; i < n; i++) {
			System.out.print(array[i]);
			System.out.print(" ");
		}
		System.out.println();
		
		System.out.println(array[1-1]);
		System.out.println(array[(n/2)-1]);
		System.out.println(array[n-1]);
		
		input.close();
	}
}
