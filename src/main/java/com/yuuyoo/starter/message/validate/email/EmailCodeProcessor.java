package com.yuuyoo.starter.message.validate.email;

import com.yuuyoo.starter.message.validate.AbstractValidateProcessor;
import com.yuuyoo.starter.message.validate.ValidateCode;
import com.yuuyoo.starter.message.validate.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码处理器
 * 
 * @author
 *
 */
@Component("emailValidateCodeProcessor")
public class EmailCodeProcessor extends AbstractValidateProcessor<ValidateCode> {

	/**
	 * 短信验证码发送器
	 */
	@Autowired
	private SmsCodeSender smsCodeSender;
	
	@Override
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
		String paramName = "mobile";
		String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
		smsCodeSender.send(mobile, validateCode.getCode());
	}

}
