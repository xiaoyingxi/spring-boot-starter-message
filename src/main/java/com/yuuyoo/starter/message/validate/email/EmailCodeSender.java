/**
 * 
 */
package com.yuuyoo.starter.message.validate.email;

/**
 * @author zhailiang
 *
 */
public interface EmailCodeSender {

	/**
	 * 邮件发送
	 * @param title 邮件标题
	 * @param content 邮件内容
	 * @param sender 邮件发送者
	 * @param recipient 邮件接收者
	 */
	void send(String title, String content, String sender, String recipient);

}
