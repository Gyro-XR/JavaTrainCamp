- 串行：+UseSerialGC
- 并行：+UseParallelGC
- CMS：+UseConcMarkSweepGC
- G1：+UseG1GC

`java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1g -Xms1g -XX:+UseSerialGC GCLogAnalysis`
![image](http://note.youdao.com/yws/public/resource/bc61e30ddac287c8f5d013680ad568fc/xmlnote/B014DCF38BFC47B9A41865413069E8BB/17503)

`java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1g -Xms1g -XX:+UseParallelGC GCLogAnalysis（默认使用自适应参数配置）`
![image](http://note.youdao.com/yws/public/resource/bc61e30ddac287c8f5d013680ad568fc/xmlnote/A17E04C844224758AEA64A7DCE39D22D/17507)

`java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xms1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseParallelGC GCLogAnalysis（关掉自适应参数配置）`
![image](https://note.youdao.com/yws/public/resource/bc61e30ddac287c8f5d013680ad568fc/xmlnote/FF8D514B54044C3EAF7B14CB5E04A369/17513)

`java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1g -Xms1g -XX:+UseConcMarkSweepGC GCLogAnalysis`
![image](https://note.youdao.com/yws/public/resource/bc61e30ddac287c8f5d013680ad568fc/xmlnote/7091B3FA935A43C6BEBC9885AA13DAFE/17520)

`java -XX:+PrintGC -XX:+PrintGCDateStamps -Xmx1g -Xms1g +UseG1GC GCLogAnalysis（简洁版）`
![image](http://note.youdao.com/yws/public/resource/bc61e30ddac287c8f5d013680ad568fc/xmlnote/3E918C1E4A5D44AAB91AD6F14E8DF91A/17524)
