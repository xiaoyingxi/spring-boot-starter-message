/**
 * 
 */
package com.yuuyoo.starter.validate.validate.image;

import com.yuuyoo.starter.validate.validate.AbstractValidateProcessor;
import com.yuuyoo.starter.validate.validate.ValidateException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.ServletWebRequest;
import sun.misc.BASE64Decoder;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

/**
 * 图片验证码处理器
 * 
 * @author
 *
 */
@Component("imageValidateCodeProcessor")
public class ImageValidateCodeProcessor extends AbstractValidateProcessor<ImageCode> {

	@Override
	protected ImageCode generate(ServletWebRequest request) {

		String length = request.getParameter("length");
		String width = request.getParameter("width");
		String height = request.getParameter("height");
		String device = request.getParameter("device");
		if(StringUtils.isEmpty(device)){
			throw new ValidateException("deviceId不能为空");
		}

		ResponseEntity res = validateCodeGenerator.image(
				(StringUtils.isEmpty(length)?null:Integer.parseInt(length)),
				(StringUtils.isEmpty(width)?null:Integer.parseInt(width)),
				(StringUtils.isEmpty(height)?null:Integer.parseInt(height)),
				device
		);
		return (ImageCode) res.getBody();
	}

	/**
	 * 发送图形验证码，将其写到响应中
	 */
	@Override
	protected void deal(ServletWebRequest request, ImageCode imageCode) throws Exception {
		BufferedImage image = decodeToImage(imageCode.getImage());
		ImageIO.write(image, "PNG", request.getResponse().getOutputStream());
	}

	public static BufferedImage decodeToImage(String imageString) {

		BufferedImage image = null;
		byte[] imageByte;
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			imageByte = decoder.decodeBuffer(imageString);
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
			image = ImageIO.read(bis);
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}

}
