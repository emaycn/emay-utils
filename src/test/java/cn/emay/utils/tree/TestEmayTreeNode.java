package cn.emay.utils.tree;

public class TestEmayTreeNode {
	
	public static void main(String[] args) {
		EmayTreeNode<Long,String> root = new EmayTreeNode<Long, String>(0l);
		
		root.addChild(new EmayTreeNode<Long, String>(1l));
		
		root.getChild(1l).addChild(new EmayTreeNode<Long, String>(2l, "2"));
		
		
		System.out.println(root.getChild(1l).getChild(2l).getFruit());
		
	}

}
