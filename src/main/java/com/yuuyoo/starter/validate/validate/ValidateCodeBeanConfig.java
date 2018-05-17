package com.yuuyoo.starter.validate.validate;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 验证码相关的扩展点配置。配置在这里的bean，业务系统都可以通过声明同类型或同名的bean来覆盖安全
 * @Auther: dave
 * @Date: 2018/5/17 12:16
 */
@Configuration
public class ValidateCodeBeanConfig {

  @Bean
  @ConditionalOnMissingBean(ValidateCodeRepository.class)
  public ValidateCodeRepository redisValidateCodeRepository() {
    return new RedisValidateCodeRepository();
  }
}
