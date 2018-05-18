package com.yuuyoo.starter.validate.validate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 校验码生成器
 * @Auther: dave
 * @Date: 2018/5/16 13:04
 */
//@FeignClient(name = "yuuyoo-validate")
@FeignClient(name = "validate")
public interface ValidateCodeGenerator {

  /**
   * 请求生成图片校验码
   * @param length
   * @return
   */
  @RequestMapping(value = "/code/image", method = RequestMethod.GET)
  ResponseEntity<String> image(
      @RequestParam("length") Integer length,
      @RequestParam("width") Integer width,
      @RequestParam("height") Integer height,
      @RequestParam("device") String device
  );

  /**
   * 请求生成短信校验码
   * @param mobile
   * @return
   */
  @RequestMapping(value = "/code/sms", method = RequestMethod.GET)
  ResponseEntity<String> sms(
      @RequestParam("mobile") String mobile
  );

  /**
   * 请求生成邮件校验码
   * @param email
   * @return
   */
  @RequestMapping(value = "/code/email", method = RequestMethod.GET)
  ResponseEntity<String> email(
      @RequestParam("email") String email);
}
