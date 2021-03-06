package cn.emay.utils.tree;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 树-节点<br/>
 *
 * @param <K> ID
 * @param <T> 果实
 * @author Frank
 */
public class EmayTreeNode<K, T> {

    /**
     * 节点ID，同级唯一
     */
    private final K id;

    /**
     * 节点果实
     */
    private T fruit;

    /**
     * 父节点
     */
    private EmayTreeNode<K, T> parent;

    /**
     * 子节点
     */
    private Map<K, EmayTreeNode<K, T>> children;

    /**
     * @param id 节点id
     */
    public EmayTreeNode(K id) {
        if (id == null) {
            throw new IllegalArgumentException("TreeNode's id will be not null !");
        }
        this.id = id;
    }

    /**
     * @param id    节点id
     * @param fruit 节点果实
     */
    public EmayTreeNode(K id, T fruit) {
        this(id);
        this.fruit = fruit;
    }

    /**
     * @param id      节点id
     * @param fruit   节点果实
     * @param parent  父节点
     * @param isCover 是否重复ID覆盖
     */
    public EmayTreeNode(K id, T fruit, EmayTreeNode<K, T> parent, boolean isCover) {
        this(id, fruit);
        if (parent != null) {
            parent.addChild(this, isCover);
        }
    }

    /**
     * 新增子节点
     *
     * @param child 子节点
     * @return 是否成功
     */
    public boolean addChild(EmayTreeNode<K, T> child) {
        return addChild(child, false);
    }

    /**
     * 新增子节点
     *
     * @param child   子节点
     * @param isCover 是否覆盖重复ID的子节点
     * @return 是否成功
     */
    public boolean addChild(EmayTreeNode<K, T> child, boolean isCover) {
        if (children == null) {
            children = new LinkedHashMap<>(8);
        }
        boolean isCo = !children.containsKey(child.id) || isCover;
        if (isCo) {
            children.put(child.id, child);
            child.parent = this;
            return true;
        }
        return false;
    }

    /**
     * 是否叶子
     *
     * @return 是否叶子
     */
    public boolean isLeaf() {
        return children == null;
    }

    /**
     * 删除子节点
     *
     * @param childId 子节点id
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
     * @return 子节点id
     */
    public K getId() {
        return id;
    }

    /**
     * 获取节点果实
     *
     * @return 子节点果实
     */
    public T getFruit() {
        return fruit;
    }

    /**
     * 放入节点果实
     *
     * @param fruit 子节点果实
     */
    public void setFruit(T fruit) {
        this.fruit = fruit;
    }

    /**
     * 获取父节点
     *
     * @return 父节点
     */
    public EmayTreeNode<K, T> getParent() {
        return parent;
    }

    /**
     * 获取所有子节点
     *
     * @return 所有子节点
     */
    public Map<K, EmayTreeNode<K, T>> getChildren() {
        return children;
    }

    /**
     * 获取子节点
     *
     * @param id 子节点ID
     * @return 子节点
     */
    public EmayTreeNode<K, T> getChild(K id) {
        return children == null ? null : children.get(id);
    }
}
