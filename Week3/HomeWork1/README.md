# 作业1
整合第二周作业里的HTTPClient

关键代码：
```
private void handlerTest(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
    FullHttpResponse response = null;
    try {
        String value = HttpClientHelper.connect("http://localhost:8801");// value的值替换成调用HTTPClient方法的返回值

        response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
        response.headers().set("Content-Type", "application/json");
        response.headers().setInt("Content-Length", response.content().readableBytes());

    } catch (Exception e) {
        System.out.println("处理出错:"+e.getMessage());
        response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
    } finally {
        if (fullRequest != null) {
            if (!HttpUtil.isKeepAlive(fullRequest)) {
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            } else {
                response.headers().set(CONNECTION, KEEP_ALIVE);
                ctx.write(response);
            }
        }
    }
}
```

## 开始验证：

1、启动HttpServer01、NettyServer
```
 四月 05, 2021 11:51:24 上午 io.netty.handler.logging.LoggingHandler channelRegistered
 信息: [id: 0x3247b6d3] REGISTERED
 四月 05, 2021 11:51:24 上午 io.netty.handler.logging.LoggingHandler bind
 信息: [id: 0x3247b6d3] BIND: 0.0.0.0/0.0.0.0:8808
 四月 05, 2021 11:51:24 上午 io.netty.handler.logging.LoggingHandler channelActive
 信息: [id: 0x3247b6d3, L:/0:0:0:0:0:0:0:0:8808] ACTIVE
 开启netty http服务器，监听地址和端口为 http://127.0.0.1:8808/
```
2、浏览器访问http://127.0.0.1:8808/test  
3、执行结果：
```
四月 05, 2021 11:52:09 上午 org.apache.http.impl.execchain.RetryExec execute
信息: Retrying request to {}->http://localhost:8801
url = http://localhost:8801, res = hello,nio1
```
## 结果符合预期，通过httpClient，调用了HttpServer01