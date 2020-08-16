package cn.dreamchan.common.core.tree;

import java.util.List;

/**
 * 树状结构
 * @author DreamChan
 */
public interface TreeVoFeature<T extends TreeVoFeature> {
    /**
     * 设置子节点
     *
     * @param children 子节点
     */
    void putChildrenList(List<T> children);

    /**
     * 获取子节点
     *
     * @return 父级节点
     */
    List<T> getChildrenList();

    /**
     * 获取当前节点
     *
     * @return 当前节点标识
     */
    String findNodeId();

    /**
     * 获取父级节点
     *
     * @return 父级节点
     */
    String findParentNodeId();

    /**
     * 树形结构名称
     *
     * @return 树形结构名称
     */
    String getTreeLabel();
}
