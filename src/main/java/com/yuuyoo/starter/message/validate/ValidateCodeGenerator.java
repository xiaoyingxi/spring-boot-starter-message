package com.yuuyoo.starter.message.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Description: 校验码生成器
 * @Auther: dave
 * @Date: 2018/5/16 13:04
 */
public interface ValidateCodeGenerator {

  /**
   * 生成校验码
   * @param request
   * @return ValidateCode
   */
  ValidateCode generate(ServletWebRequest request);

}
