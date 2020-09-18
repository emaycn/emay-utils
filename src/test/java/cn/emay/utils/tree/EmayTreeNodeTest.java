package cn.emay.utils.tree;

/**
 * @author Frank
 */
public class EmayTreeNodeTest {

    public static void main(String[] args) {
        EmayTreeNode<Long, String> root = new EmayTreeNode<>(0L);

        root.addChild(new EmayTreeNode<>(1L));

        root.getChild(1L).addChild(new EmayTreeNode<>(2L, "2"));

        System.out.println(root.getChild(1L).getChild(2L).getFruit());

    }

}
