package main;

import java.util.ArrayList;
import java.util.List;

public class main {

	public static void main(String[] args) {

		List<NodeData> nodeDataList = new ArrayList<NodeData>();
		nodeDataList.add(new NodeData(68, "1"));
		nodeDataList.add(new NodeData(19, "2"));
		nodeDataList.add(new NodeData(12, "3"));
		nodeDataList.add(new NodeData(9, "4"));
		nodeDataList.add(new NodeData(97, "5"));
		nodeDataList.add(new NodeData(85, "6"));
		nodeDataList.add(new NodeData(74, "7"));
		nodeDataList.add(new NodeData(63, "8"));
		nodeDataList.add(new NodeData(45, "9"));
		nodeDataList.add(new NodeData(42, "10"));
		nodeDataList.add(new NodeData(57, "11"));
		nodeDataList.add(new NodeData(18, "12"));
		nodeDataList.add(new NodeData(14, "13"));
		nodeDataList.add(new NodeData(17, "14"));
		nodeDataList.add(new NodeData(52, "15"));
		nodeDataList.add(new NodeData(30, "16"));
		nodeDataList.add(new NodeData(22, "17"));

		Btree btree = new Btree(5);
		for(NodeData nodeData : nodeDataList) {
			btree.add(nodeData);
			btree.printTree(btree.getRootNode());
			System.out.println();
			System.out.println();
		}
		
		btree.remove(new NodeData(85, "6"));
	}
}
