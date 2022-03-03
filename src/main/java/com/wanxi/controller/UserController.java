package com.wanxi.controller;


import com.wanxi.entity.LoginParams;
import com.wanxi.entity.User;
import com.wanxi.service.UserService;
import com.wanxi.commonresult.CommonResult;
import com.wanxi.service.WxUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

//String[] allowedHeaders:
//        跨域请求中允许的请求头中的字段类型，
//        该值对应跨域预请求 Response 头中的 'Access-Control-Allow-Headers' 字段值。
//        不设置确切值默认支持所有的header字段
//        （Cache-Controller、Content-Language、Content-Type、Expires、Last-Modified、Pragma）跨域访问。
//String allowCredentials:
//        该值对应的是是跨域请求 Response 头中的 'Access-Control-Allow-Credentials' 字段值。
//        浏览器是否将本域名下的 cookie 信息携带至跨域服务器中。
//        默认携带至跨域服务器中，但要实现 cookie 共享还需要前端在 AJAX 请求中打开 withCredentials 属性。
//https://zhuanlan.zhihu.com/p/66789473
@CrossOrigin
@Api(tags="user")
@RestController
@RequestMapping("user")
public class UserController {
    @Value("${jwt.tokenHead}")
    String tokenHead;
//    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private WxUserService wxuserService;
    private Jedis jedis = new Jedis();

//    private UserController(UserService service) {
//        this.userService = service;
//    }
    @ApiOperation("用户登录")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody LoginParams loginParams, HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, String> data = new HashMap<>();
        String token = null;
//        String checkCode = (String) session.getAttribute("verifyCodeValue");
        try {
            token = wxuserService.login(loginParams);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.validateFailed("用户名或密码错误");
        }
        if (StringUtils.isEmpty(token)){
            return CommonResult.validateFailed("用户名或密码错误");
        }
        data.put("tokenHead",tokenHead);
        data.put("access_token",token);


        // localStorage.setItem("Authorization","Bearer sdsdfdfds")
        // $ajax{data:{},type:"",header:{"Authorization":"Bearer sdsdfdfds"}}

//验证是否登录成功
//        CommonResult commonResult = userService.login(user);
//        Jedis jedis  = new Jedis("localhost");
//        jedis.set("commonresult", String.valueOf(commonResult));
//
//        if (commonResult.getMessage().equals("success")){
//            request.getSession().setAttribute("loginName",user.getUsername());
//
////            int i = 10/0;
//            commonResult.getMessage();
//        }

        //验证码校验
//        if (!checkCode.equals("")) {
//            if (!checkCode.equalsIgnoreCase(user.getCode())) {
//                commonresult.setData("codeErr");
//            }
//        }else{
//            commonresult.setMessage("codeNull");
//        }
        return CommonResult.success(data);
//        return commonResult;
    }
    //权限可读
    @PreAuthorize("hasAuthority('wx:product:read')")
    @PostMapping("/test")
    public CommonResult test(Authentication authentication){
        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Authentication authentication2 = securityContext.getAuthentication();
        return CommonResult.success(authentication);
    }

    //todo  注册
    //1 接受账号密码
    //2 查询是有存在重复账号
    //3 密码加密
    //4 保存到数据库
    @ApiOperation("用户列表")
    @PreAuthorize("hasAuthority('wx:product:read')")
    @RequestMapping("findAll")
    public CommonResult findAll(User user) {
//        int count;
//        CommonResult commonResult;
//        count = userService.getCount(user).getCount();
        //判断jedis中的count是否存在 减少sql查询
//        if (jedis.get("count")==null){
//            count = userService.getCount(user).getCount();
//            jedis.set("count",String.valueOf(count),"XX","EX",600);
//        }else {
//            count=Integer.valueOf(jedis.get("count"));
//        }
        //分页
//        PageHelper.startPage(user.getPage(),user.getLimit());
//        commonresult = userService.findAll(user);
//        commonresult.setCount(count);
        CommonResult commonResult = userService.findAll(user);


        return commonResult;
    }

    @ApiOperation("用户启用")
    @RequestMapping("enable")
    public CommonResult enable(User user) {
        CommonResult commonResult = userService.enable(user);
        return commonResult;
    }
    @ApiOperation("用户findById")
    @RequestMapping("findById")
    public CommonResult findById(User user){
        CommonResult commonResult = userService.findById(user);
        return commonResult;
    }
    @ApiOperation("用户删除")
    @RequestMapping("delete")
    public CommonResult delete(User user){
        CommonResult commonResult = userService.del(user);
        return  commonResult;
    }
    @ApiOperation("用户添加")
    @RequestMapping("add")
    public CommonResult add(User user){
        int count;
        count = userService.getCount(user).getCount();
        jedis.set("count",String.valueOf(count));
        jedis.expire("count",600);
        CommonResult commonResult = userService.add(user);
        return  commonResult;
    }
    @ApiOperation("用户编辑")
    @RequestMapping("update")
    public CommonResult edit(User user){
        CommonResult commonResult = userService.update(user);
        return commonResult;
    }
}
