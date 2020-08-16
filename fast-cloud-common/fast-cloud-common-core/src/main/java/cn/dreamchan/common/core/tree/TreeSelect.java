package cn.dreamchan.common.core.tree;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Treeselect 树结构实体类
 *
 * @author DreamChan
 */
@Data
public class TreeSelect implements Serializable {
    /**
     * 节点ID
     */
    private Long id;

    /**
     * 节点名称
     */
    private String label;

    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelect> children;

    public TreeSelect() {

    }

}
