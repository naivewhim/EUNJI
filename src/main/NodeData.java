package main;

public class NodeData implements Comparable<NodeData> {
	int key;
	String value;
	
	public NodeData(int key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public void setKey(int key) {
		this.key = key;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public int getKey() {
		return this.key;
	}

	public String getValue() {
		return this.value;
	}

	@Override
	public int compareTo(NodeData nodeData) {
		if(key > nodeData.getKey()) {
			return 1;
		} else if (key < nodeData.getKey()) {
			return -1;
		}
		
		return 0;
	}
}
