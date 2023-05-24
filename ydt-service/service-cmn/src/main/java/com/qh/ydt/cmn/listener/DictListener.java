package com.qh.ydt.cmn.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qh.ydt.cmn.mapper.DictMapper;
import com.qh.ydt.model.model.cmn.Dict;
import com.qh.ydt.model.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 读取操作的监听器
 *
 * @author 清欢
 * @date 2023/05/23  17:33:44
 */
@Component
public class DictListener extends AnalysisEventListener<DictEeVo> {

    @Autowired
    private DictMapper dictMapper;

    //一行一行读取
    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        //调用方法添加数据库
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictEeVo, dict);
        QueryWrapper<Dict> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("id",dictEeVo.getId());
        Integer integer = dictMapper.selectCount(queryWrapper);
        if (integer > 0) {
            dictMapper.updateById(dict);
        }else {
            dictMapper.insert(dict);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}