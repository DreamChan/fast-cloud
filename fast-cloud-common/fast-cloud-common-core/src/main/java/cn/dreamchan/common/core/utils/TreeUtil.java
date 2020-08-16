package cn.dreamchan.common.core.utils;

import cn.dreamchan.common.core.tree.TreeSelect;
import cn.dreamchan.common.core.tree.TreeVoFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.util.CollectionUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 树形数据工具类
 *
 * @author DreamChan
 */
public class TreeUtil {

    /**
     * 构建TreeSelect
     * @param lists
     * @param <V>
     * @return
     */
    public static  <V extends TreeVoFeature<V>> List<TreeSelect> buildTreeSelect(List<V> lists) {
        return addChildren(lists);
    }

    /**
     * 递归TreeSelect
     * @param lists
     * @param <V>
     * @return
     */
    private static <V extends TreeVoFeature<V>> List<TreeSelect> addChildren(List<V> lists) {
        List<TreeSelect> treeSelects = Lists.newArrayList();
        lists.stream().forEach(c -> {
            TreeSelect treeSelect = new TreeSelect();
            treeSelect.setId(Long.valueOf(c.findNodeId()));
            treeSelect.setLabel(c.getTreeLabel());
            treeSelect.setChildren(addChildren(c.getChildrenList()));
            treeSelects.add(treeSelect);
        });
        return treeSelects;
    }

    /**
     * 构建树形结构
     * @param trees
     * @param rootNodeId
     * @param <V>
     * @return
     */
    public static <V extends TreeVoFeature<V>> List<V> build(List<V> trees, String rootNodeId) {
        if (CollectionUtils.isEmpty(trees)) {
            return new ArrayList<>();
        }
        //根节点
        List<V> roots = trees.stream().filter(node -> node.findParentNodeId().equals(rootNodeId)).collect(Collectors.toList());
        //其他节点
        List<V> others = trees.stream().filter(node -> node.findParentNodeId() != null).collect(Collectors.toList());
        return buildTree(roots, others);
    }

    /**
     * 生成树形结构
     * @param roots
     * @param others
     * @param <V>
     * @return
     */
    private static <V extends TreeVoFeature<V>> List<V> buildTree(List<V> roots, List<V> others) {
        if (!CollectionUtils.isEmpty(others)) {
            //声明一个map，用来过滤已操作过的数据
            Map<String, String> map = Maps.newConcurrentMap();
            //逻辑其实很简单,从根节点往下遍历,加上所有的根节点
            roots.forEach(beanTree -> addChildren(others, beanTree, map));
            return roots;
        }
        return Lists.newArrayList();
    }

    /**
     * 获取子节点列表
     * @param others
     * @param beanTree
     * @param map
     * @param <V>
     */
    private static <V extends TreeVoFeature<V>> void addChildren(List<V> others, V beanTree, Map<String, String> map) {
        List<V> childList = Lists.newArrayList();
        others.stream()
                //判断是否已经被处理过了
                .filter(c -> !map.containsKey(c.findNodeId()))
                //获取当前节点的子节点
                .filter(c -> c.findParentNodeId().equals(beanTree.findNodeId()))
                //子节点下所有的节点,也需要加进去
                .forEach(c -> {
                    map.put(c.findNodeId(), c.findParentNodeId());
                    addChildren(others, c, map);
                    childList.add(c);
                });
        beanTree.putChildrenList(childList);
    }
}
