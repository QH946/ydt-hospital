package com.qh.ydt.cmn.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qh.ydt.cmn.listener.DictListener;
import com.qh.ydt.cmn.mapper.DictMapper;
import com.qh.ydt.cmn.service.DictService;
import com.qh.ydt.model.model.cmn.Dict;
import com.qh.ydt.model.vo.cmn.DictEeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Autowired
    private DictListener dictListener;

    /**
     * 根据parent_id查询子数据列表
     *
     * @param id
     * @return
     */
    @Cacheable(value = "dict", keyGenerator = "keyGenerator")
    @Override
    public List<Dict> findChildData(Long id) {
        LambdaQueryWrapper<Dict> queryWrapper = new LambdaQueryWrapper<>();
        //根据数据id查询子数据列表
        queryWrapper.eq(Dict::getParentId, id);
        List<Dict> dictList = baseMapper.selectList(queryWrapper);
        for (Dict dict : dictList) {
            Long dictId = dict.getId();
            boolean hasChild = hasChild(dictId);
            //向dictList集合中设置是否有子节点
            dict.setHasChildren(hasChild);
        }
        return dictList;
    }

    /**
     * 下载数据字典
     *
     * @param response
     */
    @Override
    public void exportDictData(HttpServletResponse response) {
        try {
            //设置下载信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("数据字典", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            //查询数据库
            List<Dict> dictList = baseMapper.selectList(null);
            List<DictEeVo> dictVoList = new ArrayList<>(dictList.size());
            //Dict -- DictEeVo
            for (Dict dict : dictList) {
                DictEeVo dictVo = new DictEeVo();
                BeanUtils.copyProperties(dict, dictVo);
                dictVoList.add(dictVo);
            }
            //进行写操作
            EasyExcel.write(response.getOutputStream(), DictEeVo.class).sheet("数据字典").doWrite(dictVoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传数据字典
     *
     * @param file
     */
    @CacheEvict(value = "dict", allEntries = true)
    @Override
    public void uploadData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), DictEeVo.class, dictListener).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据dictcode和value查询
     *
     * @param dictCode
     * @param value
     * @return
     */
    @Override
    public String getNameByDictCodeAndValue(String dictCode, String value) {
        Dict dict = baseMapper.selectOne(new LambdaQueryWrapper<Dict>()
                .eq(Dict::getDictCode, dictCode)
                .eq(Dict::getValue, value));
        return dict.getName();
    }

    /**
     * 只根据value查询
     *
     * @param value
     * @return
     */
    @Override
    public String getNameByValue(String value) {
        Dict dict = new Dict();
        int valueNum = Integer.valueOf(value);
        if (valueNum <= 110000) {
            dict = baseMapper.selectOne(new LambdaQueryWrapper<Dict>()
                    .eq(Dict::getValue, value)
                    .eq(valueNum == 1, Dict::getDictCode, "")//value等于1的话，dictCode按空字符串查询
            );
        } else if (valueNum >= 110100) {
            dict = baseMapper.selectOne(new LambdaQueryWrapper<Dict>()
                    .eq(valueNum == 110100, Dict::getDictCode, "Beijing")
                    .eq(Dict::getValue, value)//大于110100只查value
            );
        }
        return dict.getName();
    }

    /**
     * 通过dictCode查询到id，在根据id查询到子数据
     *
     * @param dictCode
     * @return
     */
    @Override
    public List<Dict> findByDictCode(String dictCode) {
        //获取id
        Dict dict = baseMapper.selectOne(new LambdaQueryWrapper<Dict>().eq(Dict::getDictCode, dictCode));
        return this.findChildData(dict.getId());
    }

    /**
     * 判断id下面是否有子节点
     *
     * @param id
     * @return
     */
    public boolean hasChild(Long id) {
        LambdaQueryWrapper<Dict> queryWrapper = new LambdaQueryWrapper<>();
        //创建条件：根据id查询子数据
        queryWrapper.eq(Dict::getParentId, id);
        Integer count = baseMapper.selectCount(queryWrapper);
        //如果大于0就是true，反之false
        return count > 0;
    }

}
