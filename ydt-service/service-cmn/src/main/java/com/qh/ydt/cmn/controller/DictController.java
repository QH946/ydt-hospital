package com.qh.ydt.cmn.controller;

import com.qh.ydt.cmn.service.DictService;
import com.qh.ydt.common_utils.result.Result;
import com.qh.ydt.model.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 数据字典接口
 *
 * @author 清欢
 * @date 2023/05/23  17:29:24
 */
@Slf4j
@Api(tags = "数据字典接口")
@RestController
@RequestMapping("/admin/cmn/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    /**
     * 根据parent_id查询子数据列表
     *
     * @param id
     * @return
     */

    @ApiOperation(value = "根据数据id查询子数据列表")
    @GetMapping("/findChildData/{id}")
    public Result findChildData(@PathVariable Long id) {
        List<Dict> list = dictService.findChildData(id);
        return Result.ok(list);
    }

    /**
     * 下载数据字典
     *
     * @param response
     * @return
     */
    @ApiOperation(value = "下载数据字典")
    @GetMapping("/exportDict")
    public Result exportDict(HttpServletResponse response) {
        dictService.exportDictData(response);
        return Result.ok();
    }

    /**
     * 上传数据字典
     *
     * @param file
     * @return
     */
    @ApiOperation(value = "上传数据字典")
    @PostMapping("/uploadDict")
    public void uploadDict(MultipartFile file) {
        dictService.uploadData(file);
    }

    /**
     * 根据dictCode和value进行查询
     *
     * @param dictCode
     * @param value
     * @return
     */
    @ApiOperation(value = "根据dictCode和value进行查询数据字典名称")
    @GetMapping("/getName/{dictCode}/{value}")
    public String getName(@PathVariable String dictCode, @PathVariable String value) {
        return dictService.getNameByDictCodeAndValue(dictCode, value);
    }

    /**
     * 根据value查询
     *
     * @param value
     * @return
     */
    @ApiOperation(value = "根据value查询数据字典名称")
    @GetMapping("/getName/{value}")
    public String getName(@PathVariable String value) {
        return dictService.getNameByValue(value);
    }

    /**
     * 根据dictCode查询id，再根据id查询子数据
     *
     * @param dictCode
     * @return
     */
    @ApiOperation(value = "根据dictCode查询到id，再根据id查询子数据")
    @GetMapping("/findByDictCode/{dictCode}")
    public Result findByDictCode(@PathVariable String dictCode) {
        List<Dict> list = dictService.findByDictCode(dictCode);
        return Result.ok(list);
    }
}