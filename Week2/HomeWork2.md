# sb常用命令说明：
- -u指向要调用的目标url
- -c并发请求数（默认1）
- -n请求总数（默认100）
- -N运行秒数（已指定-n的情况下，会被忽略）
- -y延迟时间（单位：ms，默认0）（模拟业务场景用）
- -m使用其他的method（默认http GET方式请求）

- 其他进阶使用，可查阅文档：https://blog.csdn.net/qq_37411444/article/details/113522239

# 作业题
无并发：

![image](http://note.youdao.com/yws/public/resource/bc61e30ddac287c8f5d013680ad568fc/xmlnote/964C4B4CBC4F4790B49FFF000C032DFB/17528)

并发=10：

![image](https://note.youdao.com/yws/public/resource/bc61e30ddac287c8f5d013680ad568fc/xmlnote/FF942B0922064B5096C4C1225DA99975/17532)

并发=15：

![image](http://note.youdao.com/yws/public/resource/bc61e30ddac287c8f5d013680ad568fc/xmlnote/C35B5025975C47CBA58ADD5E495FFA40/17535)

并发=20：

![image](http://note.youdao.com/yws/public/resource/bc61e30ddac287c8f5d013680ad568fc/xmlnote/578AC53F507444B496C3D22F6E3AEDD9/17538)

并发=25：

![image](http://note.youdao.com/yws/public/resource/bc61e30ddac287c8f5d013680ad568fc/xmlnote/A1D9AFEA3C3446D785BAFAB7079D1FC9/17540)

# 结果对比分析
1、随着并发数的增加，RPS提升；

2、并发数不断增加，RPS呈现先增后减的趋势，且平均响应时间变大，表明当并发数超过系统处理瓶颈时，系统压力增大，性能变差；
