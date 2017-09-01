package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node {
	private int m;
	private List<NodeData> nodeDataList = new ArrayList<>();
	private Node parentNode;
	private List<Node> subNodeList = new ArrayList<>();

	public Node(int m) {
		this.m = m;
	}
	
	public boolean isFull() {
		return nodeDataList.size() >= m-1;
	}
	
	public boolean isLeaf() {
		return subNodeList.size() == 0;
	}
	
	public boolean isRoot() {
		return parentNode == this;
	}

	public void setNodeDataList(List<NodeData> nodeDataList) {
		this.nodeDataList.addAll(nodeDataList);
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	public List<Node> getSubNodeList(){
		return this.subNodeList;
	}

	public List<NodeData> getNodeDataList() {
		return nodeDataList;
	}
	
	public Node getParentNode() {
		return parentNode;
	}
	
	public int add(NodeData nodeData) {
		nodeDataList.add(nodeData);
		Collections.sort(nodeDataList);
		
		return nodeDataList.indexOf(nodeData);
	}

	public class SplitNode {
		NodeData midNodeData;
		Node beforeNode = new Node(m);
		Node afterNode = new Node(m);
	}

	public SplitNode split(NodeData nodeData) {
		
		int index;
		
		List<NodeData> tempNodeDataList = new ArrayList<>();
		tempNodeDataList.addAll(getNodeDataList());
		tempNodeDataList.add(nodeData);
		Collections.sort(tempNodeDataList);

		if(tempNodeDataList.indexOf(nodeData) == m/2) {
			NodeData temp = new NodeData(nodeData.getKey(), nodeData.getValue());
			nodeData.setKey(getNodeDataList().get(m / 2 - 1).getKey());
			nodeData.setValue(getNodeDataList().get(m / 2 - 1).getValue());
			getNodeDataList().set(m/2 -1, temp);
			index = m/2 -1;
		} else {
			if(getNodeDataList().get(m / 2).compareTo(nodeData) > 0 ) {
				index = m/2 -1;
			} else {
				index = m/2;
			}
		}
		
		
		SplitNode splitNode = new SplitNode();

		splitNode.midNodeData = getNodeDataList().get(index);
		
		splitNode.beforeNode.setNodeDataList(getNodeDataList().subList(0, index));

		splitNode.afterNode.setNodeDataList(getNodeDataList().subList(index + 1, m - 1));

		if(!getSubNodeList().isEmpty()) {
			splitNode.beforeNode.getSubNodeList().addAll(getSubNodeList().subList(0, index + 1));
			splitNode.afterNode.getSubNodeList().addAll(getSubNodeList().subList(index + 1, m));
			
			for(int i=0; i<index + 1; i++) {
				getSubNodeList().get(i).setParentNode(splitNode.beforeNode);
			}
			
			for(int i=index + 1; i<m; i++) {
				getSubNodeList().get(i).setParentNode(splitNode.afterNode);
			}
		}
		
		return splitNode;
	}
	
	public Node findAddNode(NodeData nodeData) {
		for (int i = 0; i < nodeDataList.size(); i++) {
			int ret = nodeDataList.get(i).compareTo(nodeData);

			if (ret == 0) {
				return null;
			}

			if (ret > 0) {
				return subNodeList.get(i);
			}
		}

		return subNodeList.get(nodeDataList.size());
	}

	public Node findRemoveNode(NodeData nodeData) {
		for (int i = 0; i < nodeDataList.size(); i++) {
			int ret = nodeDataList.get(i).compareTo(nodeData);

			if (ret == 0) {
				return null;
			}

			if (ret > 0) {
				return subNodeList.get(i);
			}
		}

		return subNodeList.get(nodeDataList.size());
	}
	
	public void print() {
		for(NodeData nodeData : nodeDataList) {
			System.out.print("[key]: " + nodeData.getKey() + "  [value]: " + nodeData.getValue());
			System.out.print("  ||  ");
		}
		System.out.println();
	}
}
