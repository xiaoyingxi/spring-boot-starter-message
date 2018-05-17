package com.yuuyoo.starter.validate.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * @Description: 校验码处理器的管理类
 * @Auther: dave
 * @Date: 2018/5/16 13:03
 */
@Component
public class ValidateProcessorHolder {

  @Autowired
  private Map<String, ValidateCodeProcessor> validateProcessors;

  /**
   * @param type
   * @return
   */
  public ValidateCodeProcessor findValidateProcessor(ValidateType type) {
    return findValidateProcessor(type.toString().toLowerCase());
  }

  /**
   * @param type
   * @return
   */
  public ValidateCodeProcessor findValidateProcessor(String type) {
    String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
    ValidateCodeProcessor processor = validateProcessors.get(name);
    if (processor == null) {
      throw new ValidateException("验证码处理器" + name + "不存在");
    }
    return processor;
  }

}
