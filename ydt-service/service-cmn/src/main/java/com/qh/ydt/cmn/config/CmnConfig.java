package com.qh.ydt.cmn.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


/**
 * CmnConfig 配置类，方便扫描mapper
 *
 * @author 清欢
 * @date 2023/05/23  17:28:37
 */
@Configuration
@MapperScan("com.qh.ydt.cmn.mapper")
public class CmnConfig {

}