package com.yuuyoo.starter.validate.validate.email;

import com.alibaba.fastjson.JSON;
import com.yuuyoo.starter.validate.validate.AbstractValidateProcessor;
import com.yuuyoo.starter.validate.validate.ValidateException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.ServletWebRequest;
import java.io.IOException;

/**
 * 短信验证码处理器
 * 
 * @author
 *
 */
@Component("emailValidateCodeProcessor")
public class EmailValidateCodeProcessor extends AbstractValidateProcessor<EmailCode> {


	@Override
	protected EmailCode generate(ServletWebRequest request) throws IOException {
		String email = request.getParameter("email");
		if(StringUtils.isEmpty(email)){
			throw new ValidateException("email不能为空");
		}
		String resStr = validateCodeGenerator.email(email).getBody();
		EmailCode emailCode =JSON.parseObject(resStr,EmailCode.class);
		return emailCode;
	}

	@Override
	protected void deal(ServletWebRequest request, EmailCode validateCode) throws Exception {

	}


}
