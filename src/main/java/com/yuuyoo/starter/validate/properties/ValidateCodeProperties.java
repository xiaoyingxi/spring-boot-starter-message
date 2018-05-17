package com.yuuyoo.starter.validate.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: 验证码配置
 * @Auther: dave
 * @Date: 2018/5/16 15:20
 */
@ConfigurationProperties(prefix = "validate")
@Data
@Component
public class ValidateCodeProperties {

  /**
   * 图片验证码配置
   */
  private ImageCodeProperties image =  new ImageCodeProperties();
  /**
   * 短信验证码配置
   */
  private SmsCodeProperties sms = new SmsCodeProperties();

  /**
   * 邮件验证码配置
   */
  private EmailCodeProperties email = new EmailCodeProperties();

}
