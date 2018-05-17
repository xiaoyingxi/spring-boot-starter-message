package com.yuuyoo.starter.validate.validate;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 抽象的图形验证码处理器
 * @Description:
 * @Auther: dave
 * @Date: 2018/5/16 14:33
 */
public abstract class AbstractValidateProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  protected ValidateCodeGenerator validateCodeGenerator;

  @Autowired
  private ValidateCodeRepository validateCodeRepository;


  @Override
  public String create(ServletWebRequest request) throws Exception {
    C validateCode = generate(request);
    save(request, validateCode);
    deal(request, validateCode);
    return validateCode.getCode();
  }

  /**
   * 生成校验码
   *
   * @param request
   * @return
   */
  @SuppressWarnings("unchecked")
  protected abstract C generate(ServletWebRequest request);

  /**
   * 保存校验码
   *
   * @param request
   * @param validateCode
   */
  private void save(ServletWebRequest request, C validateCode) {
    ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
    logger.info("保存的图形验证码"+code.getCode());
    validateCodeRepository.save(request, code, getValidateCodeType(request));
  }

  /**
   * 后续处理，由子类实现
   *
   * @param request
   * @param validateCode
   * @throws Exception
   */
  protected abstract void deal(ServletWebRequest request, C validateCode) throws Exception;

  /**
   * 根据请求的url获取校验码的类型
   *
   * @param request
   * @return
   */
  private ValidateType getValidateCodeType(ServletWebRequest request) {
    String type = StringUtils.substringBefore(getClass().getSimpleName(), "ValidateCodeProcessor");
    return ValidateType.valueOf(type.toUpperCase());
  }

  @SuppressWarnings("unchecked")
  @Override
  public void validate(ServletWebRequest request) {

    ValidateType codeType = getValidateCodeType(request);

    C codeInSession = (C) validateCodeRepository.get(request, codeType);

    String codeInRequest;
    try {
      codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
          codeType.getParamNameOnValidate());
    } catch (ServletRequestBindingException e) {
      throw new ValidateException("获取验证码的值失败");
    }

    if (StringUtils.isBlank(codeInRequest)) {
      throw new ValidateException(codeType + "验证码的值不能为空");
    }

    if (codeInSession == null) {
      throw new ValidateException(codeType + "验证码不存在");
    }

    if (codeInSession.isExpried()) {
      validateCodeRepository.remove(request, codeType);
      throw new ValidateException(codeType + "验证码已过期");
    }

    if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
      throw new ValidateException(codeType + "验证码不匹配");
    }

    validateCodeRepository.remove(request, codeType);

  }

}
