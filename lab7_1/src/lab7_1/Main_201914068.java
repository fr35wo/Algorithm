package lab7_1;

public class Main_201914068 {
	public static void main(String[] args) {
		System.out.println("lab8_1 : 홍석현");

// 상호배타적 집합을 구현하는 MyDisjointSet 객체를 생성하고 테스트 연산을 수행
		MyDisjointSet set = new MyDisjointSet();
		set.test();
	}
}

// 트리를 이용하여 상호배타적 집합을 구현하는 클래스
class MyDisjointSet {
	private int n = 10; // 원소 개수(원소는 0, 1, 2, ..., n-1)

// private 인스턴스 변수 선언 - 트리 구현을 위한 자료구조
	private int[] p;

	public MyDisjointSet() { // 트리 구현을 위해 필요한 자료구조를 초기화
		p = new int[n];
	}

// 하나의 원소 x로 구성된 집합 생성
	public void makeSet(int x) {
		p[x] = x;
	}

// x의 대표 원소를 알아냄 - lab8_1에서는 구현하지 않음
	public int findSet(int x) {
		return 0;
	}

// x가 속한 집합과 y가 속한 집합을 합침 - lab8_1에서는 구현하지 않음
	public void union(int x, int y) {
	}

	public void test() {// 트리 구현을 테스트하기 위한 연산 수행
// 각 원소(0, 1, 2, ..., 9)마다 하나의 노드로 구성된 집합 생성(makeSet) - 총 10개의 집합 생성
		for (int i = 0; i < 10; i++) {
			makeSet(i);
		}
// 트리 구현이 제대로 되었는지 확인하기 위해 각 노드의 부모를 출력
		System.out.print("부모 = ");
		for (int i = 0; i < 10; i++) {

			System.out.print(p[i] + " ");

		}

	}
}