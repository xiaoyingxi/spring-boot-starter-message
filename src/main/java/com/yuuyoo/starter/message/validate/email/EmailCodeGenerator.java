package com.yuuyoo.starter.message.validate.email;

import com.yuuyoo.starter.message.properties.ValidateCodeProperties;
import com.yuuyoo.starter.message.validate.ValidateCode;
import com.yuuyoo.starter.message.validate.ValidateCodeGenerator;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码生成器
 * 
 * @author zhailiang
 *
 */
@Component("emailValidateCodeGenerator")
@Data
public class EmailCodeGenerator implements ValidateCodeGenerator {

	@Autowired
	private ValidateCodeProperties validateCodeProperties;
	

	@Override
	public ValidateCode generate(ServletWebRequest request) {
		String code = RandomStringUtils.randomNumeric(validateCodeProperties.getSms().getLength());
		return new ValidateCode(code, validateCodeProperties.getSms().getExpireIn());
	}
	
	

}
