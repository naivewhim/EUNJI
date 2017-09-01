package main;

import java.util.List;

import main.Node.SplitNode;

public class Btree {
    private Node rootNode;
    private int m;

    public Btree(int m) {
        this.m = m;
    }

    public Node getRootNode() {
        return rootNode;
    }

    public void printTree(Node rootNode) {
        rootNode.print();

        for (Node node : rootNode.getSubNodeList()) {
            printTree(node);
        }
    }

    public boolean add(NodeData nodeData) {

        if (rootNode == null) {
            rootNode = new Node(m);
            rootNode.add(nodeData);
            rootNode.setParentNode(rootNode);
            return true;
        }

        Node currentNode = rootNode;
        while (!currentNode.isLeaf()) {
            if (currentNode.isFull()) {
                SplitNode splitNode = currentNode.split(nodeData);

                if (currentNode.isRoot()) {
                    this.rootNode = new Node(m);
                    rootNode.add(splitNode.midNodeData);
                    splitNode.beforeNode.setParentNode(rootNode);
                    splitNode.afterNode.setParentNode(rootNode);

                    rootNode.getSubNodeList().add(splitNode.beforeNode);
                    rootNode.getSubNodeList().add(splitNode.afterNode);
                } else {
                    Node parentNode = currentNode.getParentNode();
                    int removeSubNodeIndex = parentNode.getSubNodeList().indexOf(currentNode);
                    parentNode.getSubNodeList().remove(removeSubNodeIndex);

                    int index = parentNode.add(splitNode.midNodeData);

                    splitNode.beforeNode.setParentNode(parentNode);
                    splitNode.afterNode.setParentNode(parentNode);
                    parentNode.getSubNodeList().add(index, splitNode.beforeNode);
                    parentNode.getSubNodeList().add(index + 1, splitNode.afterNode);
                }
            }

            currentNode = currentNode.findAddNode(nodeData);
        }

        if (currentNode.isFull()) {
            SplitNode splitNode = currentNode.split(nodeData);

            if (currentNode.isRoot()) {
                this.rootNode = new Node(m);
                rootNode.add(splitNode.midNodeData);
                splitNode.beforeNode.setParentNode(rootNode);
                splitNode.afterNode.setParentNode(rootNode);
                rootNode.getSubNodeList().add(splitNode.beforeNode);
                rootNode.getSubNodeList().add(splitNode.afterNode);
                rootNode.setParentNode(rootNode);
                this.add(nodeData);
                return true;
            }

            Node parentNode = currentNode.getParentNode();
            int index = parentNode.add(splitNode.midNodeData);
            parentNode.getSubNodeList().remove(index);
            splitNode.beforeNode.setParentNode(parentNode);
            splitNode.afterNode.setParentNode(parentNode);
            parentNode.getSubNodeList().add(index, splitNode.beforeNode);
            parentNode.getSubNodeList().add(index + 1, splitNode.afterNode);
            this.add(nodeData);
            return true;
        }

        currentNode.add(nodeData);
        return true;
    }

    public void remove(NodeData nodeData) {
        // 삭제할 데이터의 위치 찾기
        Node currentNode = rootNode;

        Node findNode;
        while ((findNode = currentNode.findRemoveNode(nodeData)) != null) {
            currentNode = findNode;
        }

        System.out.println("삭제할 데이터의 위치 찾기");
        currentNode.print();

        // leaf node 가 아니면 이동시킴
        // 자신의 데이터가 n번째에 있다면, n+1 번째 subnode 에 있는 0번째 인자와 바꿈.
        Node tempNode;
        if (!currentNode.isLeaf()) {
            tempNode = currentNode;
            int i = 0;
            while (!tempNode.isLeaf()) {
                for (i = 0; i < tempNode.getNodeDataList().size(); i++) {
                    if (tempNode.getNodeDataList().get(i).getKey() == nodeData.getKey()) {
                        tempNode = tempNode.getSubNodeList().get(i + 1);
                        break;
                    }
                }
            }

            System.out.println("leaf node 가 아니면 이동시킴");
            tempNode.print();

            NodeData changeNodeData = tempNode.getNodeDataList().get(0);
            NodeData temp = new NodeData(0, "");
            temp.setKey(changeNodeData.getKey());
            temp.setValue(changeNodeData.getValue());
            changeNodeData.setKey(nodeData.getKey());
            changeNodeData.setValue(nodeData.getValue());
            currentNode.getNodeDataList().get(i).setKey(temp.getKey());
            currentNode.getNodeDataList().get(i).setValue(temp.getValue());

            currentNode = tempNode;
            currentNode.getNodeDataList().remove(0);
        } else {
            currentNode.getNodeDataList().removeIf(
                    (NodeData tempNodeData) -> tempNodeData.getKey() == nodeData.getKey());
        }

        System.out.println("제거 후");
        this.printTree(rootNode);

        // 제거 후 노드의 총 데이터 개수가 절반보다 작으면 최소키값 유지 연산
        //		if(currentNode.getNodeDataList().size() < m/2) {
        //			rearrage(currentNode);
        //		}
    }

    public void rearrage(Node node) {
        // 1) borrow
        // 형제노드에 여유가 있을 때 *부모의 값 -> 삭제한 노드로 이동, 형제노드의 값 -> 부모로 이동
        List<Node> subNodeList = node.getParentNode().getSubNodeList();
        int meIndex = subNodeList.indexOf(node);
        int i;
        for (i = 0; i < subNodeList.size(); i++) {
            if (subNodeList.get(i).getNodeDataList().size() > m / 2) {
                break;
            }
        }

        // TODO :
        if (i != subNodeList.size()) {
            if (meIndex < i) {
                for (; meIndex < i; i++) {
                    List<NodeData> nodeDataList = node.getParentNode().getSubNodeList().get(
                            meIndex + 1).getNodeDataList();
                    node.getParentNode().getNodeDataList().add(nodeDataList.get(0));
                    nodeDataList.remove(0);
                }
            }
        } else {
            // 2) merge
            // 삭제한 노드에 부모데이터와 여유있는 형제 데이터를 삭제한 노드에 위치시킴
            // 부모를 하나 빼왔으므로, 부모의 개수도 체크한 후 만약 모자르면
            // 다시 재분배. 이 때는, 이동하는 자식의 노드 데이터위치도 변경시켜줘야 함.

        }
    }
}
