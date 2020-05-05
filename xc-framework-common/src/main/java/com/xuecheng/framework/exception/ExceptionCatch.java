package com.xuecheng.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.transform.Result;

/**
 * 统一异常捕获类
 */
@ControllerAdvice//控制器增强
public class ExceptionCatch {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);
    //定义map，配置异常类型所对应的异常代码,ImmutableMap数据不可更改，只读并且线程安全
    private static ImmutableMap<Class<? extends Throwable>,ResultCode> EXCEPTIONS;
    //定义map的builder对象，去构建ImmutableMap
    protected static ImmutableMap.Builder<Class<? extends Throwable>,ResultCode> builder = ImmutableMap.builder();
    //捕获自定义异常
    @ResponseBody//要把对象转成json返回客户端
    @ExceptionHandler(CustomException.class)
    public ResponseResult customException(CustomException e){
        e.printStackTrace();
        //记录日志
        LOGGER.error("catch exception:{}",e.getMessage());
        ResultCode resultCode = e.getResultCode();
        return new ResponseResult(resultCode);
    }

    //捕获Exception异常
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseResult exception(Exception e){
        e.printStackTrace();
        //记录日志
        LOGGER.error("catch exception:{}",e.getMessage());
        if(EXCEPTIONS==null){
            EXCEPTIONS = builder.build();//EXCEPTIONS构建成功
        }
        //从EXCEPTIONS中找异常类型所对应的错误代码，如果找到了将错误代码响应给客户，如果找不到给用户响应99999异常
        ResultCode resultCode = EXCEPTIONS.get(e.getClass());
        if (resultCode!=null){
            return new ResponseResult(resultCode);
        }else{
            //返回99999异常
            return new ResponseResult(CommonCode.SERVER_ERROR);
        }

    }
    static{
        //定义了异常类型所对应的错误代码，数据在构建器中还没添加到map中
        builder.put(HttpMessageNotReadableException.class, CommonCode.INVALID_PARAM);
    }
}
