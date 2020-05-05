<!DOCTYPE html>
<html>
<head>
    <meta charset="utf‐8">
    <title>Hello World!</title>
</head>
<body>
Hello ${name}!
<br/>
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>金额</td>
        <td>出生日期</td>
    </tr>
    <#if stus??>
    <#list stus as stu>
    <tr>
        <td>${stu_index+1}</td>
        <td <#if stu.name=='小明'>style="background: red" </#if>>${stu.name}</td>
        <td>${stu.age}</td>
        <#--要么用gt、lt，要么用括号把表达式括起来-->
        <td <#if stu.money gt 300>style="background: red"</#if>>${stu.money}</td>
        <#--<td>${stu.birthday?date}</td>-->
        <td>${stu.birthday?string("yyyy-MM-dd")}</td>
    </tr>
    </#list><br/>
    学生的个数:${stus?size}
    </#if>
</table>
<br/>
遍历数据模型中的stuMap(map数据)<br/>
<#--()!''表示缺省值，如果没有就默认为‘’,当然也可以用if标签来实现-->
姓名:${(stuMap['stu1'].name)!''}<br/>
年龄:${stuMap['stu1'].age}<br/>
姓名:${stuMap.stu1.name}<br/>
年龄:${stuMap.stu1.age}<br/>
遍历map中的key,stuMap?keys就是key列表<br/>
<#list stuMap?keys as k>
    姓名:${stuMap[k].name}<br/>
    年龄:${stuMap[k].age}<br/>
</#list><br/>
<#--默认用 , 三位分隔，可以用 ?c 转换为字符串-->
${point?c}
<br/>
<#--assign的作用是定义一个变量,eval是把这个json变成java对象,很少用-->
<#assign text="{'bank':'工商银行','account':'10101920201920212'}" />
<#assign data=text?eval />
开户行：${data.bank} 账号：${data.account}
</body>
</html>