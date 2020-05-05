package com.xuecheng.auth;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedis {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    //创建jwt令牌
    @Test
    public void testRedis(){
        //定义key
        String key = "user_token:abde2951-76ab-4a24-8f5e-08604bb46f97";
        //定义value
        Map<String,String> value = new HashMap<>();
        value.put("jwt","eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb21wYW55SWQiOm51bGwsInVzZXJwaWMiOm51bGwsInVzZXJfbmFtZSI6Iml0Y2FzdCIsInNjb3BlIjpbImFwcCJdLCJuYW1lIjpudWxsLCJ1dHlwZSI6bnVsbCwiaWQiOm51bGwsImV4cCI6MTU4MzUxNjU4OCwianRpIjoiNDUyZjhjZTMtMzgyNS00MTE0LWI3ZjUtMjYxN2E3ZDlmNDJiIiwiY2xpZW50X2lkIjoiWGNXZWJBcHAifQ.HAN5WwbMj6BLE2VghBFDIhaeC_sbYWVdxyK46E5yrNPb3P7DmXKkriFfWclQq9H-Re-iIiok7ue_B2GcwnWegwEl6-mh7ksXUH_sbS7qfet1aeWPqcEjZGodXp5hF1Qas4zCZxSbbXpQ5QANVtLpwE2Ej5-A8IRncNwba27yGd1rAyHBlJGySSNP4eAGFw3j6Dm7gjs0ikqQH8ss86bRauV_YX2stL_MHTvqk9G7RdMUhfcDyzIbxRBOkm3b6lJqA9xHUfAHDSsW1m6p4KeVKT65iYXdW_5pFpHmpJgGcVMFTiFZvdHi5RLmCb5fpQEXnLd8k6nM_woLzQ5dqnhUrw");
        value.put("refresh_token","eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb21wYW55SWQiOm51bGwsInVzZXJwaWMiOm51bGwsInVzZXJfbmFtZSI6Iml0Y2FzdCIsInNjb3BlIjpbImFwcCJdLCJhdGkiOiI0NTJmOGNlMy0zODI1LTQxMTQtYjdmNS0yNjE3YTdkOWY0MmIiLCJuYW1lIjpudWxsLCJ1dHlwZSI6bnVsbCwiaWQiOm51bGwsImV4cCI6MTU4MzUxNjU4OCwianRpIjoiN2RkYzY2M2MtYmEyNS00Y2I4LTllOTktNDQwODA4ZmRlMmEwIiwiY2xpZW50X2lkIjoiWGNXZWJBcHAifQ.m08v8cH9C_z-HUaEZXE_PJKXEHxnyvalTF8QLJTJtBI4CvDOZBdOgZBdMpxpea-q1YsArF4EBAmN8Ub4YwzlV9B6laSclQ9mNm2rlu6z7ZV4Dt-Eazr6O5yowPAb_EihQOHMu_FbL73WrBWIaNQvh1XgOSWVtkjEoxzwvz-ZOdW7MFLMbGvlcrry1S_EHTnYeJMUreoy56fgkSjhJFqw30QVE7iZSLhUR6zFINpcMZr0QiNNSxBMflWvN7VHaXhe5YJzyIFVdK5XoVzMtrMYMuArfhu6-K4qjpQ5UkuMspiwpM28Ly1U0UL4paBF2oAEkEuIDrC8CWkhgyw5CP5Lvw");
        String jsonString = JSON.toJSONString(value);
        //校验key是否存在，如果不存在则返回-2 如果存在返回过期时间
        Long expire = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
        System.out.println(expire);
        //存储数据
        stringRedisTemplate.boundValueOps(key).set(jsonString,30, TimeUnit.SECONDS);
        //获取数据
        String string = stringRedisTemplate.opsForValue().get(key);
        System.out.println(string);


    }


}
