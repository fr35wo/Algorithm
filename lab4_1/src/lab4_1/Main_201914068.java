package lab4_1;

import java.util.Scanner;

public class Main_201914068 {

	public static void main(String[] args) {
		System.out.println("hw4_1:홍석현");

		// 사용자가 원하는 갯수의 정수값을 입력받음
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		String[] array = new String[n];

		for (int i = 0; i < n; i++) {
			array[i] = input.next();
		}

		// 퀵 정렬 수행
		quickSort(array, 0, array.length - 1);

		// 정렬 결과를 출력
		for (int i = 0; i < n; i++) {
			System.out.print(array[i] + " ");
		}

		input.close();
	}

// array의 원소들을 퀵 정렬하는 메소드

	public static void quickSort(String[] array, int p, int q) { //array[p..q]를 정렬한다
		if (p >= q) {
			return;
		}
		int pivot = partition(array, p, q); //분할
		quickSort(array, p, pivot - 1); //왼쪽 부분배열 정렬
		quickSort(array, pivot + 1, q); //오른쪽 부분배열 정렬
	}

//array[p]를 기준값으로 하여 array[p..q]의 원소들을 양쪽으로 재배치(기준값보다 작은 원소는 왼쪽, 나머지는 오른쪽에 배치)하고,
//기준값이 저장된 위치를 리턴한다.	

	private static int partition(String[] array, int left, int right) {

		int p = left;
		int q = right;
		String pivot = array[left]; // 부분리스트의 왼쪽 요소를 피벗으로 설정

		while (p < q) {

			
			 //p가 q보다 크고, q가 pivot보다 작거나 같을 떄 까지 q 감소
			 
			while (array[q].length() > pivot.length() && p < q) {
				q--;
			}

			
			 //q가 p보다 크고, p가 pivot보다 클때 까지 p를 증가
			 
			while (array[p].length() <= pivot.length() && p < q) {
				p++;
			}

			//원소 교환
			swap(array, p, q);
		}

		
		 //처음 pivot이었던 array[left]와 p 원소 교환
		 
		swap(array, left, p);

		// 기준원소의 위치 리턴
		return p;
	}

// array[i]와 array[j]를 교환하는 메소드
	private static void swap(String[] array, int i, int j) {
		String temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}