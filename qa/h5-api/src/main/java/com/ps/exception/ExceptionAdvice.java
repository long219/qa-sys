package com.ps.exception;

import com.ps.domain.MessageVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionAdvice {

   /**
    *
    * @param e
    * @return Message 消息封装类
    * BusinessException  异常类
    */
   @ExceptionHandler(BusinessException.class)
   @ResponseBody
   public MessageVO businessException(BusinessException e){

      //当捕获到此异常时,返回MessageVO对象到前台-------所以加上@ResponseBody注解
      return new MessageVO(e.getCode(), e.getMessage());
   }
}