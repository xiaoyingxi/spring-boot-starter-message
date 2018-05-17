/**
 * 
 */
package com.yuuyoo.starter.validate.validate.sms;


import com.yuuyoo.starter.validate.validate.AbstractValidateProcessor;
import com.yuuyoo.starter.validate.validate.ValidateCode;
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
@Component("smsValidateCodeProcessor")
public class SmsValidateCodeProcessor extends AbstractValidateProcessor<ValidateCode> {


	@Override
	protected ValidateCode generate(ServletWebRequest request) {
		String mobile = request.getParameter("mobile");
		String device = request.getParameter("device");
		if(StringUtils.isEmpty(device)){
			throw new ValidateException("deviceId不能为空");
		}
		return validateCodeGenerator.sms(mobile,device).getBody();
	}

	@Override
	protected void deal(ServletWebRequest request, ValidateCode validateCode) throws Exception {

	}

}
