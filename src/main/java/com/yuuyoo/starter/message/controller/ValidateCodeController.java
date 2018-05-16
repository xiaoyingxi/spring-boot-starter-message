package com.yuuyoo.starter.message.controller;

import com.yuuyoo.starter.message.validate.ValidateProcessorHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 生成校验码的请求处理器
 * @Description:
 * @Auther: dave
 * @Date: 2018/5/16 15:00
 */
@RestController
public class ValidateCodeController {

  @Autowired
  private ValidateProcessorHolder validateProcessorHolder;

  /**
   * 创建验证码，根据验证码类型不同，调用不同的 {@link ValidateProcessor}接口实现
   *
   * @param request
   * @param response
   * @param type
   * @throws Exception
   */
  @GetMapping("/code/{type}")
  public String createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
      throws Exception {
    return validateProcessorHolder.findValidateProcessor(type).create(new ServletWebRequest(request, response));
  }

  @GetMapping("/image/1")
  public void image(){
    System.out.println("image");
  }

  @GetMapping("/sms/1")
  public void sms(){
    System.out.println("sms");
  }

  @GetMapping("/email/1")
  public void email(){
    System.out.println("email");
  }
}
