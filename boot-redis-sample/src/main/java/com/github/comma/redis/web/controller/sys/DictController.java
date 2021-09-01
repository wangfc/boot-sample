package com.github.comma.redis.web.controller.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.comma.redis.common.Response;
import com.github.comma.redis.entity.SysDict;
import com.github.comma.redis.model.sys.dict.Query;
import com.github.comma.redis.model.sys.dict.Save;
import com.github.comma.redis.model.sys.dict.Update;
import com.github.comma.redis.service.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author wangfc
 * @description
 * @date 2019-09-13 11:11
 */
@RestController
@Api(tags = "字典管理")
@RequestMapping("sys/dict")
public class DictController {

    @Autowired
    private SysDictService sysDictService;


    @ApiOperation(value = "保存", httpMethod = "POST")
    @PostMapping("save")
    public Response save(@Valid @RequestBody Save save) {
        SysDict sysDict = new SysDict();
        BeanUtils.copyProperties(save, sysDict);
        int result = sysDictService.save(sysDict);
        if (result > 0) {
            return Response.success();
        } else {
            return Response.failure();
        }
    }

    @ApiOperation(value = "修改", httpMethod = "PUT")
    @PutMapping("update")
    public Response update(@Valid @RequestBody Update update) {
        SysDict sysDict = new SysDict();
        BeanUtils.copyProperties(update, sysDict);
        int result = sysDictService.update(sysDict);
        if (result > 0) {
            return Response.success();
        } else {
            return Response.failure();
        }
    }

    @ApiOperation(value = "删除", httpMethod = "DELETE")
    @DeleteMapping("delete/{id}")
    public Response delete(@PathVariable("id") Integer id) {
        int result = sysDictService.delete(id);
        if (result > 0) {
            return Response.success();
        } else {
            return Response.failure();
        }

    }

    @ApiOperation(value = "分页查询", httpMethod = "POST")
    @PostMapping("page")
    public Response page(@RequestBody Query query) {
        IPage<SysDict> page = sysDictService.page(query.getName(), query.getType(), query.getCode(), query.getPageNum(), query.getPageSize());
        return Response.success().data(page);

    }


    @ApiOperation(value = "列表查询", httpMethod = "POST")
    @PostMapping("list")
    public Response list(@RequestBody Query query) {
        List<SysDict> list = sysDictService.list(query.getName(), query.getType(), query.getCode());
        return Response.success().data(list);

    }
}