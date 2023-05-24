import request from "@/utils/request";

const api_name = "/admin/hosp/hospitalSet";

export default {
  //分页查询接口
  getHospSetList(current, limit, Obj) {
    //当前页，每页显示多少，搜索对象
    return request({
      url: `${api_name}/page/${current}/${limit}`,
      method: "post",
      data: Obj //使用json传递
    });
  },

  //根据id删除医院信息
  deleteHospSet(id) {
    return request({
      url: `${api_name}/${id}`,
      method: "delete"
    });
  },

  //批量删除医院信息
  deleteSetSelectedHospSet(idList) {
    return request({
      url: `${api_name}/deleteSelected`,
      method: "delete",
      data: idList
    });
  },

  //锁定或解锁医院信息
  lockHospSet(id, status) {
    return request({
      url: `${api_name}/changeHospStatus/${id}/${status}`,
      method: "put"
    });
  },

  //新增医院信息
  addHospSet(hospitalSet) {
    return request({
      url: `${api_name}/add`,
      method: "post",
      data: hospitalSet
    });
  },

  //根据id回显医院信息
  getHospSetById(id) {
    return request({
      url: `${api_name}/${id}`,
      method: "get"
    });
  },

  //更新医院信息
  updateHospSet(hospitalSet) {
    return request({
      url: `${api_name}/update`,
      method: "put",
      data: hospitalSet
    });
  }
};
