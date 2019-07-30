package com.github.comma.common.utils;

import com.github.comma.common.exception.ValidateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @author wangfuchu
 * @date 2019/6/21
 * @apiNote
 */
@Slf4j
public final class ValidUtil {

    private ValidUtil(){
        throw new IllegalStateException("can not be instantiation");
    }

    public static void valid(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder stringBuilder = new StringBuilder();
            List<ObjectError> list =bindingResult.getAllErrors();
            list.forEach( o -> stringBuilder.append(o.getDefaultMessage()).append(",")  );
            log.error("param error: {} ", stringBuilder);
            throw new ValidateException("参数错误");
        }
    }
}
