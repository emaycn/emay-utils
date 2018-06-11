package cn.emay.utils.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 树-节点<br/>
 * 
 * @author Frank
 *
 * @param <K>
 *            ID
 * @param <T>
 *            果实
 */
public class TreeNode<K, T> {

	/**
	 * 节点ID，同级唯一
	 */
	private K id;

	/**
	 * 节点果实
	 */
	private T fruit;

	/**
	 * 父节点
	 */
	private TreeNode<K, T> parent;

	/**
	 * 子节点
	 */
	private Map<K, TreeNode<K, T>> children;

	/**
	 * 
	 * @param id
	 *            节点id
	 */
	public TreeNode(K id) {
		if (id == null) {
			throw new IllegalArgumentException("TreeNode's id will be not null !");
		}
		this.id = id;
	}

	/**
	 * 
	 * @param id
	 *            节点id
	 * @param fruit
	 *            节点果实
	 */
	public TreeNode(K id, T fruit) {
		this(id);
		this.fruit = fruit;
	}

	/**
	 * 
	 * @param id
	 *            节点id
	 * @param fruit
	 *            节点果实
	 * @param parent
	 *            父节点
	 * @param isCover
	 *            是否重复ID覆盖
	 */
	public TreeNode(K id, T fruit, TreeNode<K, T> parent, boolean isCover) {
		this(id, fruit);
		if (parent != null) {
			parent.addChild(this, isCover);
		}
	}

	/**
	 * 新增子节点
	 * 
	 * @param child
	 * @return
	 */
	public boolean addChild(TreeNode<K, T> child) {
		return addChild(child, false);
	}

	/**
	 * 新增子节点
	 * 
	 * @param child
	 * @param isCover
	 *            是否覆盖重复ID的子节点
	 * @return
	 */
	public boolean addChild(TreeNode<K, T> child, boolean isCover) {
		if (children == null) {
			children = new HashMap<K, TreeNode<K, T>>();
		}
		if ((children.containsKey(child.id) && isCover) || (!children.containsKey(child.id))) {
			children.put(child.id, child);
			child.parent = this;
			return true;
		}
		return false;
	}

	/**
	 * 是否叶子
	 * 
	 * @return
	 */
	public boolean isLeaf() {
		return children == null;
	}

	/**
	 * 删除子节点
	 * 
	 * @param childId
	 */
	public void removeChild(K childId) {
		if (children == null) {
			return;
		}
		children.remove(childId);
	}

	/**
	 * 获取节点ID
	 * 
	 * @return
	 */
	public K getId() {
		return id;
	}

	/**
	 * 获取节点果实
	 * 
	 * @return
	 */
	public T getFruit() {
		return fruit;
	}

	/**
	 * 放入节点果实
	 * 
	 * @param fruit
	 */
	public void setFruit(T fruit) {
		this.fruit = fruit;
	}

	/**
	 * 获取父节点
	 * 
	 * @return
	 */
	public TreeNode<K, T> getParent() {
		return parent;
	}

	/**
	 * 获取所有子节点
	 * 
	 * @return
	 */
	public Map<K, TreeNode<K, T>> getChildren() {
		return children;
	}

	/**
	 * 获取子节点
	 * 
	 * @param id
	 *            子节点ID
	 * @return
	 */
	public TreeNode<K, T> getChild(K id) {
		return children == null ? null : children.get(id);
	}

}
