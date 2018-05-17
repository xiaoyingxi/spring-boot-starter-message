package com.yuuyoo.starter.validate.validate.email;

import com.yuuyoo.starter.validate.validate.AbstractValidateProcessor;
import com.yuuyoo.starter.validate.validate.ValidateException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码处理器
 * 
 * @author
 *
 */
@Component("emailValidateCodeProcessor")
public class EmailValidateCodeProcessor extends AbstractValidateProcessor<EmailCode> {


	@Override
	protected EmailCode generate(ServletWebRequest request) {
		String email = request.getParameter("email");
		String device = request.getParameter("device");
		if(StringUtils.isEmpty(device)){
			throw new ValidateException("deviceId不能为空");
		}
		return validateCodeGenerator.email(email,device).getBody();
	}

	@Override
	protected void deal(ServletWebRequest request, EmailCode validateCode) throws Exception {

	}


}
