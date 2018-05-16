package com.yuuyoo.starter.message.properties;

import lombok.Builder;
import lombok.Data;

/**
 * @Description: 短信验证码配置项
 * @Auther: dave
 * @Date: 2018/5/16 15:22
 */
@Data
public class SmsCodeProperties {

  /**
   * 验证码长度
   */
  private int length = 6;
  /**
   * 过期时间
   */
  private int expireIn = 60;
  /**
   * 要拦截的url，多个url用逗号隔开，ant pattern
   */
  private String url;

  public SmsCodeProperties() {

  }
}
