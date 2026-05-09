public class CustomLinkedList<T> {
	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	private static class Node<T> {
		private T value;
		private Node<T> next;
		
		private Node(T value) {
			this.value = value;
		}
	}
	
	public void add(T value) {
		Node<T> newNode = new Node<>(value);
		
		if (head == null) {
			head = newNode;
			tail = newNode;
		}
		else {
			tail.next = newNode;
			tail = newNode;
		}
		
		size++;
	}
	
	public boolean remove(T value) {
		Node<T> current = head;
		Node<T> previous = null;
		
		while (current != null) {
			if (valuesAreEqual(current.value, value)) {
				if (previous == null) {
					head = current.next;
				}
				else {
					previous.next = current.next;
				}
				
				if (current == tail) {
					tail = previous;
				}
				
				size--;
				return true;
			}
			
			previous = current;
			current = current.next;
		}
		
		return false;
	}
	
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index " + index + " is outside the linked list size.");
		}
		
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		
		return current.value;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	private boolean valuesAreEqual(T first, T second) {
		if (first == null) {
			return second == null;
		}
		
		return first.equals(second);
	}
}
