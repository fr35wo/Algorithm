package lab6_1;

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

		input.close();
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
		while(t != null) {
			if(t.key == key)
				return true;
			else if(t.key > key) {
				t = t.left;
			}
			else if(t.key < key) {
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
}