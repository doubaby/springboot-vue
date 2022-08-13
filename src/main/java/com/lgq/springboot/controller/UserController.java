package com.lgq.springboot.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lgq.springboot.common.Result;
import com.lgq.springboot.common.Status;
import com.lgq.springboot.dto.UserDto;
import com.lgq.springboot.pojo.User;
import com.lgq.springboot.service.UserDtoService;
import com.lgq.springboot.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LGQ
 * @createTime 2022/8/3 21:51
 * @description
 */
@SuppressWarnings("all")
@RequestMapping("/user")
@RestController
@MapperScan("com.lgq.springboot.mapper")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDtoService userDtoService;


    @GetMapping("/page")
    public Result pageSelect(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "username", defaultValue = "") String username,
                             @RequestParam(value = "email", defaultValue = "") String email) {
        Integer total = userService.selectTotal(username, email);
        List<User> userList = userService.pageSelect(pageNum, pageSize, username, email);
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("userList", userList);
        return Result.success(map);
    }

    //登录
    @PostMapping("/login")
    public Result login(@RequestBody UserDto userDto) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Status.CODE_400, "参数错误");
        }
        UserDto dto = userDtoService.login(userDto);
        return Result.success(dto);
    }

    //注册
    @PostMapping("/register")
    public Result register(@RequestBody UserDto userDto) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Status.CODE_400, "参数错误");
        }
        return Result.success(userDtoService.register(userDto));
    }

    //添加
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        return Result.success(userService.save(user));
    }


    //个人信息接口
    @GetMapping("/username/{username}")
    public Result person(@PathVariable String username) {
        QueryWrapper<UserDto> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return Result.success(userDtoService.getOne(queryWrapper));
    }

    //删除
    @DeleteMapping("/del/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        return Result.success(userService.removeById(id));
    }

    //修改
    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        return Result.success(userService.updateById(user));
    }

    @PutMapping("/updatedto")
    public Result update(@RequestBody UserDto userDto) {
        return Result.success(userDtoService.updateById(userDto));
    }

    //批量删除
    @PostMapping("/delBatch")
    public Result delVBatch(@RequestBody(required = false) List<Integer> ids) {
        return Result.success(userService.removeByIds(ids));
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        //查询所有员工信息
        List<User> list = userService.list();
        //在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //给字段设置别名
//        writer.addHeaderAlias("username","用户名");
//        writer.addHeaderAlias("age","年龄");
//        writer.addHeaderAlias("gender","性别");
//        writer.addHeaderAlias("email","邮箱");
//        writer.addHeaderAlias("create","创建时间");
//        writer.addHeaderAlias("avatarUrl","头像");

        //一次性写出list内的对象到excel，使用默认演示，强制输出标题
        writer.write(list, true);

        //设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
//        response.setHeader("Content-Disposition", "attachment;fileName" + fileName +".xlsx");

        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }

    //文件写入
    @PostMapping("/import")
    public Result imp(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<User> userList = reader.readAll(User.class);
        userService.saveBatch(userList);
        return Result.success(true);
    }

}
