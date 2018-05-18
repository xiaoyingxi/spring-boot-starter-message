package com.yuuyoo.starter.validate.validate;

import lombok.Data;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

/**
 * @Description: 校验码信息
 * @Auther: dave
 * @Date: 2018/5/16 13:06
 */
@Data
public class ValidateCode implements Serializable {

  private static final long serialVersionUID = 1588203828504660915L;

  private String code;

  private LocalDateTime expireTime;

  public ValidateCode(String code, int expireIn){
    this.code = code;
    this.expireTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(expireIn),
        TimeZone.getDefault().toZoneId());
  }

  public ValidateCode(String code, long time){
    this.code = code;
    this.expireTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time),
        TimeZone.getDefault().toZoneId());
  }

  public ValidateCode(String code, LocalDateTime expireTime){
    this.code = code;
    this.expireTime = expireTime;
  }

  public boolean alreadExpried() {
    return LocalDateTime.now().isAfter(expireTime);
  }
}
