package com.qh.ydt.cmn.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qh.ydt.model.model.cmn.Dict;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DictService extends IService<Dict> {

    //根据parent_id查询子数据列表
    List<Dict> findChildData(Long id);

    //下载数据字典
    void exportDictData(HttpServletResponse response);

    //上传数据
    void uploadData(MultipartFile file);

    //根据dictCode和value查询或只根据value查询
    String getNameByDictCodeAndValue(String dictCode, String value);

    //根据value查询
    String getNameByValue(String value);

    //根据dictCode查询到id，再根据id查到parent_id
    List<Dict> findByDictCode(String dictCode);
}
