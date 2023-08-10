package com.liy.admin.controller;

import com.liy.common.domain.Page;
import com.liy.common.domain.AjaxResult;
import com.liy.common.domain.dto.PageDto;
import com.liy.common.domain.dto.UserDto;
import com.liy.common.domain.dto.UserPageDto;
import com.liy.common.domain.vo.UserVo;
import com.liy.system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author LiY
 * 用户管理接口
 */

@Tag(name = "用户管理接口", description = "用户的CRUD")
@Slf4j
@RestController
@RequestMapping("/sys/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test(){
        return "测试成功";
    }


    /**
     * @description: 创建用户
     * @author: liy
     * @param:  UserDto
     * @return:  AjaxResult
     **/
    @Operation(summary = "创建用户")
    @PostMapping
    AjaxResult add(@RequestBody UserDto userDto){
        //检查用户信息是否重复：userName、nickName、email、phonenumber

        //插入用户
//        userService.add(userDto);
        return toSuccess();

    }

    @Operation(summary = "批量删除用户的信息")
    @DeleteMapping("{ids}")
    AjaxResult delete(@PathVariable Long[] ids){
        userService.removeBatchByIds(ids);
        return toSuccess();
    }

    @Operation(summary = "用户状态修改,停用/恢复")
    @PutMapping("/changeStatus")
    AjaxResult changeStatus(@RequestBody UserDto userDto){
        Integer i = userService.changeStatus(userDto);
        return toAjaxResult(i);
    }

    @Operation(summary = "重置密码")
    @PutMapping("/resetPwd")
    AjaxResult resetPwd(@RequestBody UserDto userDto){
        //加密

        //更新

        return toAjaxResult(1);
    }

    @Operation(summary = "条件获得用户分页", description = "根据用户名或昵称或空获得分页用户")
    @Parameters({
            @Parameter(name = "pageNum", description = "第几页", required = true, example = "1"),
            @Parameter(name = "pageSize", description = "每页共几条数据", required = true, example = "5"),
            @Parameter(name = "userName", description = "用户名称", example = "admin"),
            @Parameter(name = "nickName", description = "用户昵称", example = "admin"),
    })
    @ApiResponse(responseCode = "AjaxResult", description = "封装对象")
    @GetMapping("/list")
    AjaxResult list(UserPageDto userPageDto, @Validated PageDto pageDto){
        log.info(pageDto.toString());
        Page<UserVo> userVoPage = userService.listPageBy(userPageDto, pageDto.getPageNum(), pageDto.getPageSize());

        return toSuccess().put("users",userVoPage);
    }

    @Operation(summary = "查看具体用户信息", description = "查看具体用户信息")
    @GetMapping("/{id}")
    AjaxResult getItem(@PathVariable Long id){

        UserVo userVo = null;
        return toAjaxResult("userVo", userVo);
    }

    @Operation(summary = "修改指定用户信息")
    @PutMapping()
    AjaxResult edit(@RequestBody UserDto userDto){
        Integer i = userService.updateUserDto(userDto);
        return toAjaxResult(i);
    }






}
