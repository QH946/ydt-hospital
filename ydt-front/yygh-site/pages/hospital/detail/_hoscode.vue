<template>
  <!--header-->

  <div class="nav-container page-component">
    <!--左侧导航#start-->

    <div class="nav left-nav">
      <div class="nav-item">
        <span
          class="v-link clickable dark"
          :onclick="
            'javascript:window.location=\'/hospital/' + hospital.hoscode + '\''
          "
          >预约挂号</span
        >
      </div>

      <div class="nav-item selected">
        <span
          class="v-link selected dark"
          :onclick="
            'javascript:window.location=\'/hospital/detail' +
            hospital.hoscode +
            '\''
          "
          >医院详情</span
        >
      </div>

      <div class="nav-item">
        <span
          class="v-link clickable dark"
          :onclick="
            'javascript:window.location=\'/hospital/notice/' +
            hospital.hoscode +
            '\''
          "
          >预约须知</span
        >
      </div>

      <!-- <div class="nav-item">
        <span class="v-link clickable dark">停诊信息</span>
      </div>

      <div class="nav-item">
        <span class="v-link clickable dark">查询/取消</span>
      </div> -->
    </div>
    <!--左侧导航#end-->

    <!--右侧内容#start-->
     <!-- 右侧内容 #start -->
        
    <div class="page-container">
            
      <div class="hospital-detail">
                
        <div class="common-header">
                    
          <div class="title-wrapper">
            <span class="hospital-title">{{ hospital.hosname }}</span>
                        
            <div class="icon-wrapper">
              <span class="iconfont"></span> {{ hospital.param.hostypeString }}
            </div>
                      
          </div>
                  
        </div>
                
        <!-- <div class="info-wrapper">
          <img
            :src="'data:image/jpeg;base64,' + hospital.logoData"
            :alt="hospital.hosname"
            style="width: 80px;  height: 80px"
          /> -->
        <!-- <div class="content-wrapper">
            <div></div>
            <div></div>
            <div></div>
            <div>
              <div class="icon-text-wrapper">
                <span class="iconfont prefix-icon"></span>
                <span class="text"
                  ><p>{{ hospital.route }}</p> </span
                ><span class="iconfont right-icon"></span>/
              </div>
            </div>
          </div> -->
      </div>
      <div class="title mt40">医院路线</div>
      <div class="detail-content mt10">
        <p><span class="iconfont prefix-icon"> </span>{{ hospital.route }}</p>
      </div>
      <div class="title mt40">医院介绍</div>
      <div class="detail-content mt40">
        <p>{{ hospital.intro }}</p>
      </div>
    </div>
  </div>
  <!--右侧内容#end-->
  <!-- </div> -->
  <!--footer-->
</template>

<script>
import "~/assets/css/hospital_personal.css";
import "~/assets/css/hospital.css";
import "~/assets/css/_hoscode.css";
import hospitalApi from "@/api/hosp";
export default {
  data() {
    return {
      hoscode: null,
      hospital: {
        param: {},
      },
    };
  },

  created() {
    this.hoscode = this.$route.params.hoscode;
    this.init();
  },

  methods: {
    init() {
      hospitalApi.showBookingRuleDetail(this.hoscode).then((response) => {
        this.hospital = response.data.hospital;
      });
    },
  },
};
</script>
