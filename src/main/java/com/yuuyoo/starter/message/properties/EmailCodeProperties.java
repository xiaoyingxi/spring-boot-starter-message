package com.yuuyoo.starter.message.properties;

import lombok.Builder;
import lombok.Data;

/**
 * @Description: 邮件验证码
 * @Auther: dave
 * @Date: 2018/5/16 15:33
 */
@Builder
@Data
public class EmailCodeProperties extends SmsCodeProperties {

  /**
   * 邮件主题
   */
  private String title = "this is default email topic";

  /**
   * 邮件内容
   */
  private String content = "this is default email content";

  /**
   * 发送者
   */
  private String sender = "ci@yuuyoo.com";

  public EmailCodeProperties(String title, String content, String sender) {
    this.title = title;
    this.content = content;
    this.sender = sender;
  }

  public EmailCodeProperties() {
  }
}
