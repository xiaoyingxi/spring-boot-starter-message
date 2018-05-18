/**
 * 
 */
package com.yuuyoo.starter.validate.validate.sms;


import com.alibaba.fastjson.JSON;
import com.yuuyoo.starter.validate.validate.AbstractValidateProcessor;
import com.yuuyoo.starter.validate.validate.ValidateCode;
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
@Component("smsValidateCodeProcessor")
public class SmsValidateCodeProcessor extends AbstractValidateProcessor<ValidateCode> {


	@Override
	protected ValidateCode generate(ServletWebRequest request) throws IOException {
		String mobile = request.getParameter("mobile");
		if(StringUtils.isEmpty(mobile)){
			throw new ValidateException("mobile不能为空");
		}
		String resStr = validateCodeGenerator.sms(mobile).getBody();
		ValidateCode validateCode = JSON.parseObject(resStr,ValidateCode.class);
		return validateCode;
	}

	@Override
	protected void deal(ServletWebRequest request, ValidateCode validateCode) throws Exception {

	}

}
