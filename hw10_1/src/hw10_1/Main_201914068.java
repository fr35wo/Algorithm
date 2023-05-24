package hw10_1;

import java.util.Scanner;

public class Main_201914068 {
	public static void main(String args[]) {
		System.out.println("hw10_1:홍석현");
		System.out.println("인접리스트 - 직접 구현함");
		Scanner input = new Scanner(System.in);

		// (1) 정점 수를 입력받아 방향 그래프를 생성
		int numVertices = input.nextInt(); // 정점 수
		MyGraph graph = new MyGraph(numVertices);

		// (2) 사용자가 원하는 갯수의 간선을 입력받아 방향 그래프에 삽입
		int numEdges = input.nextInt(); // 간선 수
		for (int i = 0; i < numEdges; i++) {
			graph.insertEdge(input.nextInt(), input.nextInt());
		}

		// (3) 그래프 구현을 확인하기 위해 인접 정점 정보를 출력
		// graph.printAdjVertices();

		// (4) DAG를 위상 정렬
		graph.topologicalSort1();

		input.close();
	}
}

// 단순연결리스트 노드 구조
class Node {
	int vertex;
	Node link;

	public Node(int vertex) {
		this.vertex = vertex;
		this.link = null;
	}
}

// 인접 리스트로 방향 그래프를 구현하는 클래스
class MyGraph {
	private int n;// 정점 수
	private Node[] list;// 인접 리스트

	// 정점 수가 n이고, 간선은 없는 그래프를 생성
	public MyGraph(int n) {
		this.n = n;
		list = new Node[n]; // n개의 노드 가리킬 변수 생김
	}

	// 그래프에 간선 <v1, v2>를 삽입
	public void insertEdge(int v1, int v2) {
		// list[v1] 이 가리키는 단순 연결 리스트 맨 앞에 노드(v2) 삽입
		Node nn = new Node(v2); // 노드 만들고
		nn.link = list[v1]; // 연결
		list[v1] = nn;
	}

	// 그래프 구현을 확인하기 위해 인접 정점 정보를 출력
	public void printAdjVertices() {
		for (int i = 0; i < n; i++) {
			System.out.print(i + " : ");
			Node temp = list[i];
			while (temp != null) {
				System.out.print(temp.vertex + " ");
				temp = temp.link;
			}
			System.out.println();
		}
	}

	public void topologicalSort1() {

		int[] indegree = new int[n]; // 진입차수 저장 배열

		// 진입 차수 계산
		for (int i = 0; i < n; i++) {
			Node temp = list[i];
			while (temp != null) { // temp가 돌면서 진입간선 없는 정점 체크
				int vertex = temp.vertex;
				indegree[vertex]++; // 진입차수 증가
				temp = temp.link;
			}
		}
		int[] result = new int[n]; // 위상 정렬 결과 저장 배열
		int resultIndex = 0; // break위한 index

		// 진입 차수가 0인 정점을 찾아서 결과 배열에 추가
		for (int i = 0; i < n; i++) {
			if (indegree[i] == 0) {
				result[resultIndex++] = i;
			}
		}

		// 위상 정렬 수행
		for (int i = 0; i < n; i++) { // n은 정점수
			if (resultIndex == n) {
				break; // 모든 정점을 방문했으면 break
			}

			int vertex = result[i]; // 결과 배열에서 순서대로
			Node temp = list[vertex];
			while (temp != null) {
				int adjacentVertex = temp.vertex;
				indegree[adjacentVertex]--; // 정점 u와 u의 모든 진출 간선 제거

				if (indegree[adjacentVertex] == 0) { // u의 인접정점 중 진입 차수 0이면 결과 배열에 저장
					result[resultIndex++] = adjacentVertex;// 배열 result[] 에 정점들이 위상 정렬
				}

				temp = temp.link;
			}
		}

		// 위상 정렬 결과 출력
		for (int i = 0; i < n; i++) {
			System.out.print(result[i] + " ");
		}
		System.out.println();

	}
}
