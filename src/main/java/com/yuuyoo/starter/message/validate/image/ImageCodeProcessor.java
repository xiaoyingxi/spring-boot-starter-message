/**
 * 
 */
package com.yuuyoo.starter.message.validate.image;

import com.yuuyoo.starter.message.validate.AbstractValidateProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import javax.imageio.ImageIO;

/**
 * 图片验证码处理器
 * 
 * @author
 *
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateProcessor<ImageCode> {

	/**
	 * 发送图形验证码，将其写到响应中
	 */
	@Override
	protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
		ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
	}

}
