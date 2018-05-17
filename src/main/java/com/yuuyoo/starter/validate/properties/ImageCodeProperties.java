package com.yuuyoo.starter.validate.properties;

import lombok.Data;

/**
 * @Description: 图片验证码配置项
 * @Auther: dave
 * @Date: 2018/5/16 15:22
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties {

  /**
   * 图片宽
   */
  private int width = 67;
  /**
   * 图片高
   */
  private int height = 23;

  public ImageCodeProperties(int width, int height) {
    super();
    setLength(4);
    this.width = width;
    this.height = height;
  }

  public ImageCodeProperties() {
  }
}
