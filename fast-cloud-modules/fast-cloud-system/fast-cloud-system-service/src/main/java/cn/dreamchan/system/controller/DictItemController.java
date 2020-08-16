package cn.dreamchan.system.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.dreamchan.common.core.biz.ResObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Arrays;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import cn.dreamchan.common.core.biz.R;
import cn.dreamchan.common.core.enums.EventLogEnum;
import cn.dreamchan.common.data.utils.ExcelUtils;
import cn.dreamchan.common.core.base.BaseController;
import cn.dreamchan.common.log.annotation.EventLog;
import cn.dreamchan.system.pojo.entity.*;
import cn.dreamchan.system.pojo.vo.*;
import cn.dreamchan.system.pojo.dto.*;
import cn.dreamchan.system.service.mapstruct.*;
import cn.dreamchan.system.service.DictItemService;


/**
 * 字典数据管理
 *
 * @author DreamChan
 */
@Api(tags = "字典数据管理")
@Slf4j
@RestController
@RequestMapping("/dictItem")
public class DictItemController extends BaseController<DictItemPageQueryParam, DictItemEntity> {

    @Autowired
    private DictItemService dictItemService;
    @Autowired
    private DictItemMapStruct dictItemMapStruct;

    /**
     * 字典数据 分页列表
     */
    @ApiOperation("字典数据分页列表")
    @PreAuthorize("hasPermission('/dictItem',  'system:dict:query')")
    @GetMapping(value = "/pageList")
    public ResObject pageList(@Valid DictItemPageQueryParam param) {

        Page<DictItemEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<DictItemEntity> pageList = dictItemService.page(page, this.getQueryWrapper(dictItemMapStruct, param));
        Page<DictItemVo> dictItemVoPage = dictItemMapStruct.toVoList(pageList);
        return R.success(dictItemVoPage);
    }

    /**
     * 获取字典数据
     */
    @ApiOperation("获取字典数据")
    @ApiImplicitParam(name = "dictItemId", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasPermission('/dictItem',  'system:dict:query')")
    @GetMapping("/{dictItemId}")
    public ResObject get(@PathVariable Long dictItemId) {
        DictItemEntity dictItem = dictItemService.getById(dictItemId);
        return R.success(dictItem);
    }

    /**
     * 新增字典数据
     */
    @ApiOperation("新增字典数据")
    @ApiImplicitParam(name = "DictItemEditParam ", value = "新增字典数据", dataType = "DictItemEditParam")
    @PreAuthorize("hasPermission('/dictItem',  'system:dict:add')")
    @EventLog(message = "新增字典数据", businessType = EventLogEnum.CREATE)
    @PostMapping
    public ResObject save(@Valid @RequestBody DictItemEditParam dictItemEditParam) {
        DictItemEntity dictItem = dictItemMapStruct.toEntity(dictItemEditParam);
        return R.toRes(dictItemService.save(dictItem));
    }

    /**
     * 修改字典数据
     */
    @ApiOperation("修改字典数据")
    @ApiImplicitParam(name = "DictItemEditParam ", value = "修改字典数据", dataType = "DictItemEditParam")
    @PreAuthorize("hasPermission('/dictItem',  'system:dict:edit')")
    @EventLog(message = "修改字典数据", businessType = EventLogEnum.UPDATE)
    @PutMapping
    public ResObject edit(@Valid @RequestBody DictItemEditParam dictItemEditParam) {
        DictItemEntity dictItem = dictItemMapStruct.toEntity(dictItemEditParam);
        return R.toRes(dictItemService.updateById(dictItem));
    }

    /**
     * 删除字典数据
     */
    @ApiOperation("删除字典数据")
    @ApiImplicitParam(name = "dictItemId", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasPermission('/dictItem',  'system:dict:delete')")
    @EventLog(message = "删除字典数据", businessType = EventLogEnum.DELETE)
    @DeleteMapping("/{dictItemIds}")
    public ResObject delete(@PathVariable Long[] dictItemIds) {
        DictItemEntity dictItemEntity = dictItemService.getById(dictItemIds[0]);
        return R.toRes(dictItemService.removeByIds(dictItemEntity, Arrays.asList(dictItemIds)));
    }

    /**
     * 导出字典数据
     */
    @ApiOperation("导出字典数据")
    @PreAuthorize("hasPermission('/dictItem',  'system:dict:export')")
    @SneakyThrows
    @EventLog(message = "导出字典数据", businessType = EventLogEnum.EXPORT)
    @PostMapping(value = "/exportXls")
    public void exportXls(DictItemPageQueryParam param, HttpServletResponse response) {
        List<DictItemEntity> list = dictItemService.list(this.getQueryWrapper(dictItemMapStruct, param));
        List<DictItemVo> dictItemVoList = dictItemMapStruct.toVoList(list);
        ExcelUtils.exportExcel(dictItemVoList, DictItemVo.class, "字典数据", new ExportParams(), response);
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictCode}")
    public ResObject dictType(@PathVariable String dictCode) {
        List<DictItemEntity> list = dictItemService.getItemByDictCode(dictCode);
        return R.success(list);
    }

}
