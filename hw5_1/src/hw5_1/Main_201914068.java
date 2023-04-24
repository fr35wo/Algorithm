package hw5_1;

import java.util.Scanner;

public class Main_201914068 {
	public static void main(String[] args) {
		System.out.println("hw5_1:홍석현");

// 사용자가 원하는 갯수의 정수값을 입력받음
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int[] array = new int[n];

		for (int i = 0; i < n; i++) {
			array[i] = input.nextInt();
		}

		System.out.println(select(array, 0, n - 1, 1));
		System.out.println(select(array, 0, n - 1, n / 2));
		System.out.println(select(array, 0, n - 1, n));

		input.close();
	}

	// 배열 array[p...q]에서 i번째 작은 원소 찾기
	private static int select(int[] array, int p, int q, int i) {
		if (p == q)
			return array[p]; // 원소가 하나뿐인 경우 i는 반드시 1

		int s = partition(array, p, q); // quick sort의 partition 알고리즘, 기준값 위치 리턴
		int k = s - p + 1; // k번째 작은 원소

		if (i < k)
			return select(array, p, q - 1, i); // 작은 부분만 select 재귀
		else if (i == k)
			return array[s]; // i와 기준값 s가 같음
		else
			return select(array, s + 1, q, i - k); // 큰 부분만 select 재귀

	}

	private static int partition(int[] array, int left, int right) {
		int p = left;
		int q = right;
		int pivot = array[left]; // 부분리스트의 왼쪽 요소를 피벗으로 설정

		while (p < q) {

			// p가 q보다 크고, q가 pivot보다 작거나 같을 떄 까지 q 감소

			while (array[q] > pivot && p < q) {
				q--;
			}

			// q가 p보다 크고, p가 pivot보다 클때 까지 p를 증가

			while (array[p] <= pivot && p < q) {
				p++;
			}

			// 원소 교환
			swap(array, p, q);
		}

		// 처음 pivot이었던 array[left]와 p 원소 교환

		swap(array, left, p);

// 기준원소의 위치 리턴
		return p;
	}

	// array[i]와 array[j]를 교환하는 메소드
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
