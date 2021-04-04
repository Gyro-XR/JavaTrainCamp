## 单线程HttpServer01
![image](http://note.youdao.com/yws/public/resource/bc61e30ddac287c8f5d013680ad568fc/xmlnote/9055B49650FC481A9F340A9975603AB7/17550)

## 多线程HttpServer02
![image](http://note.youdao.com/yws/public/resource/bc61e30ddac287c8f5d013680ad568fc/xmlnote/F725914FD8ED4E5D874DE2F9664B0F56/17552)

相比单线程，rps有所提升

## 线程池HttpServer03
![image](http://note.youdao.com/yws/public/resource/bc61e30ddac287c8f5d013680ad568fc/xmlnote/C3C9958D5B4C4657AB300C9E78C3ED0A/17553)

相比多线程，线程池节省了线程的创建、销毁等开销，性能进一步提升

## netty
在本地运行报错，有待解决。。。

`四月 04, 2021 9:05:57 下午 io.netty.handler.logging.LoggingHandler channelRead
 信息: [id: 0x80c74a5f, L:/0:0:0:0:0:0:0:0:8808] READ: [id: 0x9144685b, L:/127.0.0.1:8808 - R:/127.0.0.1:7142]
 四月 04, 2021 9:05:57 下午 io.netty.bootstrap.AbstractBootstrap setChannelOption
 警告: Unknown channel option 'io.netty.channel.unix.UnixChannelOption#SO_REUSEPORT' for channel '[id: 0x9144685b, L:/127.0.0.1:8808 - R:/127.0.0.1:7142]'
 四月 04, 2021 9:05:57 下午 io.netty.handler.logging.LoggingHandler channelReadComplete
 信息: [id: 0x80c74a5f, L:/0:0:0:0:0:0:0:0:8808] READ COMPLETE`