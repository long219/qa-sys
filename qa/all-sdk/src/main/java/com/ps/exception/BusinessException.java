package com.ps.exception;

import lombok.Data;

/**
 * @author 26498
 */
@Data
public class BusinessException extends RuntimeException{

   private String code;

   private String message;

   public BusinessException(String code, String message) {
      this.code = code;
      this.message = message;
   }

   public BusinessException(){

   }
}