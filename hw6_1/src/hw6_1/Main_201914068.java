package hw6_1;

import java.util.Scanner;

public class Main_201914068 {
	public static void main(String[] args) {
		System.out.println("lab6_1:홍석현");

// (1) 정수 키값을 저장할 공백 이진검색트리 tree를 생성
		MyBinarySearchTree tree = new MyBinarySearchTree();

// (2) 사용자가 원하는 갯수의 정수 키값을 입력받아 tree에 삽입한 후, tree 출력
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		for (int i = 0; i < n; i++) {
			tree.add(input.nextInt());
		}
		System.out.println(tree);

// (3) 정수 키값 x, y, z를 입력받아 각각 트리에 있는지 여부를 출력
		int x = input.nextInt();
		int y = input.nextInt();
		int z = input.nextInt();
		System.out.println(tree.contains(x) + " " + tree.contains(y) + " " + tree.contains(z));

// (4) tree의 최대 키값을 삭제하고, tree 출력
		tree.removeMax();
		System.out.println(tree);

		input.close();

//(5) tree에서 앞의 정수 키값 x, y, z를 삭제하고, tree 출력(키값이 없으면 삭제하지 않으면 됨) **** 선택 과제 
		if (tree.contains(x)) { // 입력받은 키 값 있으면 삭제
			tree.remove(x);
		}
		if (tree.contains(y)) {
			tree.remove(y);
		}
		if (tree.contains(z)) {
			tree.remove(z);
		}
		System.out.println(tree);
	}
}

// 정수 키값을 갖는 이진검색트리를 구현하는 클래스
class MyBinarySearchTree {
// 루트 노드를 가리키는 인스턴스 변수 root (알고리즘 연습을 위해 root 만 둘 것)
	private Node root = null;

// 노드의 구조를 정의하는 클래스 Node (알고리즘 연습을 위해 노드에 key, left, right 필드만 둘 것)
	private class Node {
		int key;
		Node left;
		Node right;
	}

// 키 값을 매개변수로 받아 그 키값이 존재하는지 여부(true/false)를 리턴
	public boolean contains(int key) {
		Node t = root;
		while (t != null) {
			if (t.key == key)
				return true;
			else if (t.key > key) {
				t = t.left;
			} else if (t.key < key) {
				t = t.right;
			}

		}
		return false;

	}

// 매개변수로 받은 키값을 트리에 삽입
	public void add(int key) {
		root = treeInsert(root, key);
	}

// t를 루트로 하는 트리에 key를 삽입하고, 삽입 후 루트 노드를 리턴하는 재귀 메소드
	private Node treeInsert(Node t, int key) {
		if (t == null) {
			Node r = new Node();
			r.key = key;
			return r;
		} else if (key < t.key) {
			t.left = treeInsert(t.left, key);
			return t;
		} else if (key > t.key) {
			t.right = treeInsert(t.right, key);
			return t;
		} else {
			return t; // 이미 존재하는 키값인 경우 삽입하지 않음
		}
	}

//매개변수 없이 최대 키값을 삭제
	public void removeMax() {
		if (root == null) { // 경우 1 루트가 널일때
			return;
		}

		if (root.right == null) { // 경우 2 오른쪽 자식 없으면 최대 키 값은 루트 이므로
			root = root.left;
			return;
		}

		Node parent = root; // 부모 노드
		Node max = root.right; // 최대 키 값 노드

		while (max.right != null) { // 최대 키값의 부모 노드 찾기
			parent = max;
			max = max.right;
		}
		parent.right = max.left; // 최대 키 값 부모노드랑 그 자식 노드 연결

	}

// 트리의 키값들을 중순위 순회하여 오름차순으로 나열한 하나의 문자열을 만들어 리턴
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer("tree = ");
		if (root != null) {
			inorder(result, root);
		}
		return result.toString();
	}

// t를 루트로 하는 트리를 중순위 순회
	private void inorder(StringBuffer result, Node t) {
		if (t != null) {
			inorder(result, t.left);
			result.append(t.key + " ");
			inorder(result, t.right);
		}
	}

	// 매개변수로 받은 키 값을 트리에서 삭제
	public void remove(int key) {
		if (key == root.key)
			root = null;
		else
			deleteNode(root, key);

	}

	public Node deleteNode(Node root, int key) {
		if (root == null) {

			return root;
		}

		if (key < root.key) // 키가 루트보다 작다면, 왼쪽 서브 트리에 있는 것
			root.left = deleteNode(root.left, key);
		else if (key > root.key) // 키가 루트보다 크다면, 오른쪽 서브 트리에 있는 것
			root.right = deleteNode(root.right, key);
		else {// 키가 루트와 같다면 이 노드가 바로 삭제할 노드
			if (root.right != null && root.left == null) // 1번, 2번의 경우 - 1. 단말 노드인 경우 / 2. 하나의 서브트리만 있는 경우
				return root.right; // 널 값이면 널 반환 / 오른쪽 있으면 오른쪽 반환해서 이전의 if, else if에서의 왼쪽이든 오른쪽 노드에 붙여주는 것
			else if (root.left != null && root.right == null)
				return root.left; // left가 널인 경우와 동일
			else if (root.right == null && root.right == null) {
				root = null;

				return root;
			}

			// 3번의 경우 - 3. 두개의 서브 트리가 있는 경우 (left, right 둘 다 null 아님
			Node temp = minValueNode(root.right); // 오른쪽 서브 트리에서 가장 작은 값(가장 왼쪽 노드)가 후계 노드

			root.key = temp.key; // 후계 노드 값 복사(삭제할 노드의 값을 후계 노드 값으로 변경
			root.right = deleteNode(root.right, temp.key); // 후계 노드 삭제 - 오른쪽 노드에게 가장 작은 값을 가졌던 맨 왼쪽 단말노드를 다시 deleteNode를
															// 호출해 삭제하라고 함
		}

		return root;

	}

	// 후계 노드 찾기 - 오른쪽 서브트리에서 가장 작은 값을 가지는 노드 반환
	public Node minValueNode(Node node) {
		Node currentNode = node;

		while (currentNode.left != null) {
			currentNode = currentNode.left; // 맨 왼쪽 단말 노드를 찾으러 내려감
		}
		return currentNode;
	}

}