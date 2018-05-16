package com.yuuyoo.starter.message.validate.email;

import com.yuuyoo.starter.message.validate.ValidateCode;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * @Description:
 * @Auther: dave
 * @Date: 2018/5/16 17:49
 */
@Data
public class EmailCode extends ValidateCode {
  public EmailCode(String code, int expireIn) {
    super(code, expireIn);
  }

  public EmailCode(String code, LocalDateTime expireTime) {
    super(code, expireTime);
  }

  private String title;
  private String content;
  private String sender;
  private String recipient;
}
