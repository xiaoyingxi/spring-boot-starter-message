package com.yuuyoo.starter.message.filter;

import com.yuuyoo.starter.message.properties.ValidateCodeProperties;
import com.yuuyoo.starter.message.validate.ValidateException;
import com.yuuyoo.starter.message.validate.ValidateProcessorHolder;
import com.yuuyoo.starter.message.validate.ValidateType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 验证过滤器.处理 图形验证码,手机验证码,邮件
 *
 * @Auther: dave
 * @Date: 2018/5/16 12:17
 */
@Component
public class ValidateFilter extends OncePerRequestFilter implements InitializingBean {

  /**
   * 系统配置信息
   */
  @Autowired
  private ValidateCodeProperties validateCodeProperties;
  /**
   * 系统中的校验码处理器
   */
  @Autowired
  private ValidateProcessorHolder validateProcessorHolder;

  /**
   * 存放所有需要校验验证码的url
   */
  private Map<String, ValidateType> urlMap = new HashMap<>();
  /**
   * 验证请求url与配置的url是否匹配的工具类
   */
  private AntPathMatcher pathMatcher = new AntPathMatcher();

  /**
   * 初始化要拦截的url配置信息
   */
  @Override
  public void afterPropertiesSet() throws ServletException {
    super.afterPropertiesSet();

    //urlMap.put("", ValidateType.IMAGE);
    addUrlToMap(validateCodeProperties.getImage().getUrl(), ValidateType.IMAGE);

    //urlMap.put("", ValidateType.SMS);
    addUrlToMap(validateCodeProperties.getSms().getUrl(), ValidateType.SMS);

    //urlMap.put("", ValidateType.EMAIL);
    addUrlToMap(validateCodeProperties.getEmail().getUrl(), ValidateType.EMAIL);
  }

  /**
   * 系统中配置的需要校验验证码的URL根据校验的类型放入map
   *
   * @param urlString
   * @param type
   */
  protected void addUrlToMap(String urlString, ValidateType type) {
    if (StringUtils.isNotBlank(urlString)) {
      String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
      for (String url : urls) {
        urlMap.put(url, type);
      }
    }
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {

    ValidateType type = getValidateType(request);
    if (type != null) {
      logger.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型" + type);
      try {
        validateProcessorHolder.findValidateProcessor(type)
            .validate(new ServletWebRequest(request, response));
        logger.info("验证码校验通过");
      } catch (ValidateException exception) {
        //authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
        return;
      }
    }

    chain.doFilter(request, response);
  }

  /**
   * 获取校验码的类型，如果当前请求不需要校验，则返回null
   *
   * @param request
   * @return
   */
  private ValidateType getValidateType(HttpServletRequest request) {
    ValidateType result = null;
    System.out.println(request.getMethod());
    //if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
      Set<String> urls = urlMap.keySet();
      for (String url : urls) {
        if (pathMatcher.match(url, request.getRequestURI())) {
          result = urlMap.get(url);
        }
      }
    //}
    return result;
  }
}
