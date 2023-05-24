import request from "@/utils/request";

const api_name_cmn = "/admin/cmn/dict";

export default {
  //根据dictCode查询id，再根据id查询子数据
  findByDictCode(dictCode) {
    return request({
      url: `${api_name_cmn}/findByDictCode/${dictCode}`,
      method: "get",
    });
  },

  //根据id查看数据字典信息，再根据parent_id查看是否有子节点
  findByParentId(id) {
    return request({
      url: `${api_name_cmn}/findChildData/${id}`,
      method: "get",
    });
  },

  //根据value查询数据字典名称
  findNameByValue(value) {
    return request({
      url: `${api_name_cmn}/getName/${value}`,
      method: "get",
    });
  },
};
