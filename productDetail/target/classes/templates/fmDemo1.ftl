<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <#--类似于脚本片段  <%basePath="xxx">  -->
    <#assign path="http://localhost:7070/" />
</head>
<body>
    <p><a href="${path}/88081.html">点我吧</a></p>

    <p>主标题:${title}</p>
    <p>副标题:${subtitle}</p>
    <p>价格:${price}</p>
    <p>型号:${type}</p>
    <p>颜色:${color}</p>
    <p>数量:${num?default("商品买完了")}</p>

    <#list imgUrlList as temp>
        ${temp_index}  图片地址:${temp}
    </#list>

    <#if flag1>
        我是true
    </#if>
    <#if flag2>
        我是false所以我不显示
    </#if>
</body>
</html>