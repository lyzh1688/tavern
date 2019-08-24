package com.tuoyou.tavern.common.core.exception;

import com.tuoyou.tavern.protocol.common.RetCode;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/11 <br>
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 全局异常.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public TavernResponse handleGlobalException(Exception e) {
        log.error("INTERNAL_SERVER_ERROR: {}",e.getMessage());
        return TavernResponse.builder()
                .retCode(RetCode.SYS_ERROR)
                .retMessage(e.getLocalizedMessage())
                .build();
    }

    /**
     * AUTH_FAILED
     *
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public TavernResponse handleAccessDeniedException(AccessDeniedException e) {
        log.error("AccessDeniedException: {}",e.getMessage());
        return TavernResponse.builder()
                .retCode(RetCode.AUTH_FAILED)
                .retMessage(e.getLocalizedMessage())
                .build();
    }

    /**
     * validation Exception
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TavernResponse handleBodyValidException(MethodArgumentNotValidException exception) {
        log.error("HttpStatus.BAD_REQUEST: {}",exception.getMessage());
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        return TavernResponse.builder()
                .retCode(RetCode.UN_SUPPORTED_METHOD)
                .retMessage(exception.getLocalizedMessage())
                .build();
    }
    
}
