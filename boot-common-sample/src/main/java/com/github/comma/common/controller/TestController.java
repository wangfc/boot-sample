package com.github.comma.common.controller;

import com.github.comma.common.utils.ValidUtil;
import com.github.comma.common.vo.Response;
import com.github.comma.common.vo.in.TestIn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author wangfuchu
 * @desciption
 * @date 2019/7/29
 */
@RestController
@RequestMapping("test")
@Slf4j
public class TestController {

    @RequestMapping("hello")
    public Response hello(@Valid @RequestBody TestIn testIn , BindingResult bindingResult){
        ValidUtil.valid(bindingResult);
        return Response.success().data("hello") ;
    }
}
