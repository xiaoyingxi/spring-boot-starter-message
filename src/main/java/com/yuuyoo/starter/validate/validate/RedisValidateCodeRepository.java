package com.yuuyoo.starter.validate.validate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.ServletWebRequest;
import java.util.concurrent.TimeUnit;

/**
 * 基于redis的验证码存取器，避免由于没有session导致无法存取验证码的问题
 * 
 * @author zhailiang
 *
 */
public class RedisValidateCodeRepository implements ValidateCodeRepository {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;


	@Override
	public void save(ServletWebRequest request, ValidateCode code, ValidateType type) {
		redisTemplate.opsForValue().set(buildKey(request, type), code, 30, TimeUnit.MINUTES);
	}


	@Override
	public ValidateCode get(ServletWebRequest request, ValidateType type) {
		Object value = redisTemplate.opsForValue().get(buildKey(request, type));
		if (value == null) {
			return null;
		}
		return (ValidateCode) value;
	}

	@Override
	public void remove(ServletWebRequest request, ValidateType type) {
		redisTemplate.delete(buildKey(request, type));
	}

	/**
	 * @param request
	 * @param type
	 * @return
	 */
	private String buildKey(ServletWebRequest request, ValidateType type) {
		String param = "";
		if(ValidateType.SMS.equals(type)){
			param = request.getParameter("mobile");
			if(StringUtils.isBlank(param)){
				throw new ValidateException("请在请求中携带mobile参数");
			}
		}

		if(ValidateType.IMAGE.equals(type)){
			param = request.getParameter("device");
			if(StringUtils.isBlank(param)){
				throw new ValidateException("请在请求中携带device参数");
			}
		}

		if(ValidateType.EMAIL.equals(type)){
			param = request.getParameter("email");
			if(StringUtils.isBlank(param)){
				throw new ValidateException("请在请求中携带email参数");
			}
		}

		return "code:" + type.toString().toLowerCase() + ":" + param;
	}

}
