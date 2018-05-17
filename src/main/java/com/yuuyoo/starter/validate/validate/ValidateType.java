package com.yuuyoo.starter.validate.validate;

/**
 * @Description: 检验类型
 * @Auther: dave
 * @Date: 2018/5/16 12:25
 */
public enum ValidateType {

  /**
   * 短信验证码
   */
  SMS {
    @Override
    public String getParamNameOnValidate() {
      return "sms";
    }
  },
  /**
   * 图片验证码
   */
  IMAGE {
    @Override
    public String getParamNameOnValidate() {
      return  "image";
    }
  },

  /**
   * 邮件验证码
   */
  EMAIL {
    @Override
    public String getParamNameOnValidate() { return  "email"; }
  };

  /**
   * 校验时从请求中获取的参数的名字
   * @return
   */
  public abstract String getParamNameOnValidate();

}
