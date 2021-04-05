## 单线程HttpServer01
![image](http://note.youdao.com/yws/public/resource/bc61e30ddac287c8f5d013680ad568fc/xmlnote/9055B49650FC481A9F340A9975603AB7/17550)

## 多线程HttpServer02
![image](http://note.youdao.com/yws/public/resource/bc61e30ddac287c8f5d013680ad568fc/xmlnote/F725914FD8ED4E5D874DE2F9664B0F56/17552)

相比单线程，rps有所提升

## 线程池HttpServer03
![image](http://note.youdao.com/yws/public/resource/bc61e30ddac287c8f5d013680ad568fc/xmlnote/C3C9958D5B4C4657AB300C9E78C3ED0A/17553)

相比多线程，线程池节省了线程的创建、销毁等开销，性能进一步提升

## netty
相比上述其他Server，性能提升相当明显；  
netty数据零拷贝，IO消耗大幅减少；请求分发链路异步非阻塞，可以容纳更多请求；
```
C:\Users\86188>sb -u http://localhost:8808/test -c 10 -n 10000
Starting at 2021/4/5 20:34:42
[Press C to stop the test]
9999    (RPS: 3664)
---------------Finished!----------------
Finished at 2021/4/5 20:34:45 (took 00:00:02.8823871)
Status 200:    10000

RPS: 2614.6 (requests/second)
Max: 80ms
Min: 0ms
Avg: 0.1ms
```