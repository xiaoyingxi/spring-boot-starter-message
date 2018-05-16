/**
 * 
 */
package com.yuuyoo.starter.message.validate.sms;

/**
 * @author zhailiang
 *
 */
public interface SmsCodeSender {
	
	/**
	 * @param mobile
	 * @param code
	 */
	void send(String mobile, String code);

}
