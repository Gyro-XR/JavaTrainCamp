# 程序启动时，配置不同大小的堆内存，对压测结果的影响
## 结论前置：
程序启动时，增大堆内存的配置，能使运行过程中的GC次数明显减少；
## 64M堆内存
`java -jar -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx64m -Xms64m gateway-server-0.0.1-SNAPSHOT.jar`

可以看到，启动阶段就发生了一次FullGC
```
2021-04-05T19:54:29.940+0800: [GC (Allocation Failure) [PSYoungGen: 13696K->1154K(16896K)] 19358K->7387K(60928K), 0.0017117 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.0.4.RELEASE)

2021-04-05T19:54:29.978+0800: [GC (Allocation Failure) [PSYoungGen: 13442K->957K(16896K)] 19675K->7606K(60928K), 0.0020159 secs] [Times: user=0.13 sys=0.00, real=0.00 secs]
2021-04-05T19:54:30.011+0800: [GC (Allocation Failure) [PSYoungGen: 13245K->757K(16896K)] 19894K->7912K(60928K), 0.0019888 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T19:54:30.040+0800: [GC (Allocation Failure) [PSYoungGen: 13045K->714K(16896K)] 20200K->7989K(60928K), 0.0018321 secs] [Times: user=0.11 sys=0.02, real=0.00 secs]
2021-04-05T19:54:30.083+0800: [GC (Allocation Failure) [PSYoungGen: 13002K->1275K(16896K)] 20277K->8687K(60928K), 0.0025829 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T19:54:30.134+0800: [GC (Allocation Failure) [PSYoungGen: 13563K->1092K(16896K)] 20975K->8720K(60928K), 0.0015275 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
2021-04-05T19:54:30.170+0800: [GC (Allocation Failure) [PSYoungGen: 13380K->976K(16896K)] 21008K->8828K(60928K), 0.0022830 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T19:54:30.208+0800: [GC (Allocation Failure) [PSYoungGen: 13264K->1122K(16896K)] 21116K->9166K(60928K), 0.0015115 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T19:54:30.248+0800: [GC (Allocation Failure) [PSYoungGen: 13410K->1124K(16896K)] 21454K->9359K(60928K), 0.0022410 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T19:54:30.298+0800: [GC (Metadata GC Threshold) [PSYoungGen: 11313K->928K(17408K)] 19548K->9315K(61440K), 0.0019448 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T19:54:30.300+0800: [Full GC (Metadata GC Threshold) [PSYoungGen: 928K->0K(17408K)] [ParOldGen: 8387K->5749K(44032K)] 9315K->5749K(61440K), [Metaspace: 20455K->20455K(1067008K)], 0.0278030 secs] [Times: user=0.17 sys=0.00, real=0.03 secs]
2021-04-05T19:54:30.342+0800: [GC (Allocation Failure) [PSYoungGen: 13312K->378K(17408K)] 19061K->6128K(61440K), 0.0010912 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T19:54:30.358+0800: [GC (Allocation Failure) [PSYoungGen: 13690K->500K(17408K)] 19440K->6346K(61440K), 0.0018464 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T19:54:30.459+0800: [GC (Allocation Failure) [PSYoungGen: 13812K->1360K(17408K)] 19658K->7370K(61440K), 0.0025100 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T19:54:30.529+0800: [GC (Allocation Failure) [PSYoungGen: 14672K->1188K(17920K)] 20682K->8007K(61952K), 0.0023045 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
```
压测时：发生了1次FullGC，18次YoungGC
```
2021-04-05T20:04:54.505+0800: [GC (Metadata GC Threshold) [PSYoungGen: 13567K->1008K(17920K)] 34779K->22596K(61952K), 0.0029423 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:04:54.508+0800: [Full GC (Metadata GC Threshold) [PSYoungGen: 1008K->0K(17920K)] [ParOldGen: 21588K->16176K(44032K)] 22596K->16176K(61952K), [Metaspace: 33879K->33879K(1079296K)], 0.0645656 secs] [Times: user=0.36 sys=0.02, real=0.07 secs]
2021-04-05T20:04:54.629+0800: [GC (Allocation Failure) [PSYoungGen: 14336K->850K(17920K)] 30512K->17026K(61952K), 0.0033997 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:04:55.050+0800: [GC (Allocation Failure) [PSYoungGen: 15186K->512K(18432K)] 31362K->17056K(62464K), 0.0018128 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:04:55.593+0800: [GC (Allocation Failure) [PSYoungGen: 15872K->192K(18432K)] 32416K->16928K(62464K), 0.0026891 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:04:56.300+0800: [GC (Allocation Failure) [PSYoungGen: 15552K->192K(18432K)] 32288K->16968K(62464K), 0.0031233 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:04:56.956+0800: [GC (Allocation Failure) [PSYoungGen: 15552K->256K(18432K)] 32328K->17040K(62464K), 0.0026139 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:04:58.855+0800: [GC (Allocation Failure) [PSYoungGen: 15616K->224K(18944K)] 32400K->17016K(62976K), 0.0033293 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:04:59.513+0800: [GC (Allocation Failure) [PSYoungGen: 16096K->192K(18432K)] 32888K->16992K(62464K), 0.0034251 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:05:00.266+0800: [GC (Allocation Failure) [PSYoungGen: 16064K->256K(18944K)] 32864K->17064K(62976K), 0.0034395 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:05:01.186+0800: [GC (Allocation Failure) [PSYoungGen: 16640K->256K(18944K)] 33448K->17072K(62976K), 0.0024693 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:05:01.823+0800: [GC (Allocation Failure) [PSYoungGen: 16640K->256K(18944K)] 33456K->17080K(62976K), 0.0024910 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:05:02.391+0800: [GC (Allocation Failure) [PSYoungGen: 16640K->224K(18944K)] 33464K->17048K(62976K), 0.0031608 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:05:03.022+0800: [GC (Allocation Failure) [PSYoungGen: 16608K->256K(19456K)] 33432K->17088K(63488K), 0.0025237 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:05:03.590+0800: [GC (Allocation Failure) [PSYoungGen: 17664K->256K(19456K)] 34496K->17096K(63488K), 0.0022291 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:05:04.128+0800: [GC (Allocation Failure) [PSYoungGen: 17664K->288K(19456K)] 34504K->17136K(63488K), 0.0028736 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:05:04.687+0800: [GC (Allocation Failure) [PSYoungGen: 17696K->256K(19456K)] 34544K->17112K(63488K), 0.0022869 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:05:05.185+0800: [GC (Allocation Failure) [PSYoungGen: 17664K->256K(19456K)] 34520K->17120K(63488K), 0.0023213 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:05:05.698+0800: [GC (Allocation Failure) [PSYoungGen: 17664K->256K(19456K)] 34528K->17128K(63488K), 0.0026626 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:05:06.191+0800: [GC (Allocation Failure) [PSYoungGen: 17664K->256K(19968K)] 34536K->17128K(64000K), 0.0024796 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
```
压测结果：
```
C:\Users\86188>sb -u http://localhost:8088/api/hello -n 10000
Starting at 2021/4/5 20:04:54
[Press C to stop the test]
10000   (RPS: 843.8)
---------------Finished!----------------
Finished at 2021/4/5 20:05:06 (took 00:00:12.1163410)
Status 200:    10000

RPS: 766.5 (requests/second)
Max: 212ms
Min: 0ms
Avg: 0.1ms
```
## 128M堆内存
`java -jar -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx128m -Xms128m -XX:+UseParallelGC gateway-server-0.0.1-SNAPSHOT.jar`

启动阶段同样发生了一次FullGC
```
2021-04-05T20:07:54.301+0800: [GC (Allocation Failure) [PSYoungGen: 38104K->4797K(38400K)] 38136K->4837K(125952K), 0.0047580 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.0.4.RELEASE)

2021-04-05T20:07:54.388+0800: [GC (Allocation Failure) [PSYoungGen: 38077K->5110K(38400K)] 38117K->5630K(125952K), 0.0045217 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:07:54.488+0800: [GC (Allocation Failure) [PSYoungGen: 38390K->5106K(34304K)] 38910K->7121K(121856K), 0.0054461 secs] [Times: user=0.01 sys=0.01, real=0.01 secs]
2021-04-05T20:07:54.572+0800: [GC (Allocation Failure) [PSYoungGen: 34290K->4253K(36352K)] 36305K->8955K(123904K), 0.0048631 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:07:54.650+0800: [GC (Metadata GC Threshold) [PSYoungGen: 24120K->2783K(36352K)] 28821K->8610K(123904K), 0.0036007 secs] [Times: user=0.11 sys=0.00, real=0.00 secs]
2021-04-05T20:07:54.654+0800: [Full GC (Metadata GC Threshold) [PSYoungGen: 2783K->0K(36352K)] [ParOldGen: 5826K->6287K(87552K)] 8610K->6287K(123904K), [Metaspace: 20477K->20477K(1067008K)], 0.0288773 secs] [Times: user=0.16 sys=0.00, real=0.03 secs]
2021-04-05T20:07:54.738+0800: [GC (Allocation Failure) [PSYoungGen: 29184K->959K(30208K)] 35471K->7318K(117760K), 0.0018648 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:07:54.900+0800: [GC (Allocation Failure) [PSYoungGen: 30143K->3028K(35840K)] 36502K->9395K(123392K), 0.0030366 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:07:55.120+0800: [GC (Allocation Failure) [PSYoungGen: 31188K->2058K(35840K)] 37555K->10458K(123392K), 0.0052282 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
```
压测时，发生了1次FullGC，12次YoungGC
```
2021-04-05T20:09:25.639+0800: [GC (Metadata GC Threshold) [PSYoungGen: 12117K->848K(33280K)] 32884K->22135K(120832K), 0.0030441 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:09:25.643+0800: [Full GC (Metadata GC Threshold) [PSYoungGen: 848K->0K(33280K)] [ParOldGen: 21287K->16159K(87552K)] 22135K->16159K(120832K), [Metaspace: 33868K->33868K(1079296K)], 0.0633969 secs] [Times: user=0.25 sys=0.00, real=0.06 secs]
2021-04-05T20:09:25.951+0800: [GC (Allocation Failure) [PSYoungGen: 23040K->1186K(33280K)] 39199K->17353K(120832K), 0.0028927 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:09:27.293+0800: [GC (Allocation Failure) [PSYoungGen: 24226K->256K(33792K)] 40393K->16919K(121344K), 0.0048888 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
2021-04-05T20:09:28.522+0800: [GC (Allocation Failure) [PSYoungGen: 24320K->256K(33792K)] 40983K->16999K(121344K), 0.0024850 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:09:30.825+0800: [GC (Allocation Failure) [PSYoungGen: 24320K->160K(34304K)] 41063K->16951K(121856K), 0.0028440 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:09:32.125+0800: [GC (Allocation Failure) [PSYoungGen: 24736K->224K(33792K)] 41527K->17023K(121344K), 0.0024341 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:09:33.101+0800: [GC (Allocation Failure) [PSYoungGen: 24800K->160K(34816K)] 41599K->16967K(122368K), 0.0027353 secs] [Times: user=0.03 sys=0.02, real=0.00 secs]
2021-04-05T20:09:34.109+0800: [GC (Allocation Failure) [PSYoungGen: 25760K->256K(34304K)] 42567K->17063K(121856K), 0.0023667 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:09:35.083+0800: [GC (Allocation Failure) [PSYoungGen: 25856K->256K(35328K)] 42663K->17063K(122880K), 0.0020984 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:09:36.007+0800: [GC (Allocation Failure) [PSYoungGen: 27392K->288K(35328K)] 44199K->17095K(122880K), 0.0029832 secs] [Times: user=0.00 sys=0.01, real=0.00 secs]
2021-04-05T20:09:36.795+0800: [GC (Allocation Failure) [PSYoungGen: 27424K->256K(36352K)] 44231K->17063K(123904K), 0.0025116 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:09:37.608+0800: [GC (Allocation Failure) [PSYoungGen: 28928K->256K(35840K)] 45735K->17071K(123392K), 0.0022719 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
```
压测结果：
```
C:\Users\86188>sb -u http://localhost:8088/api/hello -n 10000
Starting at 2021/4/5 20:09:25
[Press C to stop the test]
10000   (RPS: 789.8)
---------------Finished!----------------
Finished at 2021/4/5 20:09:38 (took 00:00:12.9070226)
Status 200:    10000

RPS: 721.7 (requests/second)
Max: 219ms
Min: 0ms
Avg: 0.2ms
```
## 512M堆内存
`java -jar -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx128m -Xms128m -XX:+UseParallelGC gateway-server-0.0.1-SNAPSHOT.jar`

启动阶段同样发生了一次FullGC，但YoungGC次数明显减少
```
2021-04-05T20:13:33.057+0800: [GC (Allocation Failure) [PSYoungGen: 131584K->7927K(153088K)] 131584K->7943K(502784K), 0.0071462 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.0.4.RELEASE)

2021-04-05T20:13:33.426+0800: [GC (Metadata GC Threshold) [PSYoungGen: 131624K->11649K(153088K)] 131640K->11673K(502784K), 0.0118709 secs] [Times: user=0.09 sys=0.03, real=0.01 secs]
2021-04-05T20:13:33.438+0800: [Full GC (Metadata GC Threshold) [PSYoungGen: 11649K->0K(153088K)] [ParOldGen: 24K->11049K(349696K)] 11673K->11049K(502784K), [Metaspace: 20445K->20445K(1067008K)], 0.0268544 secs] [Times: user=0.16 sys=0.00, real=0.03 secs]
2021-04-05T20:13:34.087+0800: [GC (Allocation Failure) [PSYoungGen: 131584K->6834K(153088K)] 142633K->17956K(502784K), 0.0055883 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
2021-04-05T20:13:35.641+0800: [GC (Allocation Failure) [PSYoungGen: 138418K->13056K(153088K)] 149540K->24185K(502784K), 0.0102430 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
2021-04-05T20:13:36.269+0800: [GC (Allocation Failure) [PSYoungGen: 144640K->15269K(153088K)] 155769K->26406K(502784K), 0.0184068 secs] [Times: user=0.13 sys=0.00, real=0.02 secs]
```
压测时，发生了一次FullGC，YoungGC次数大大减少
```
2021-04-05T20:15:06.729+0800: [GC (Metadata GC Threshold) [PSYoungGen: 34543K->12245K(157184K)] 45681K->23390K(506880K), 0.0077934 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
2021-04-05T20:15:06.738+0800: [Full GC (Metadata GC Threshold) [PSYoungGen: 12245K->0K(157184K)] [ParOldGen: 11145K->16870K(349696K)] 23390K->16870K(506880K), [Metaspace: 33882K->33882K(1079296K)], 0.0393909 secs] [Times: user=0.05 sys=0.00, real=0.04 secs]
2021-04-05T20:15:13.713+0800: [GC (Allocation Failure) [PSYoungGen: 139264K->1216K(140800K)] 156134K->18094K(490496K), 0.0039261 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-04-05T20:15:18.408+0800: [GC (Allocation Failure) [PSYoungGen: 140480K->752K(154624K)] 157358K->17638K(504320K), 0.0035785 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
```
压测结果：
```
C:\Users\86188>sb -u http://localhost:8088/api/hello -n 10000
Starting at 2021/4/5 20:15:06
[Press C to stop the test]
10000   (RPS: 804.8)
---------------Finished!----------------
Finished at 2021/4/5 20:15:19 (took 00:00:12.4941067)
Status 200:    10000

RPS: 743.4 (requests/second)
Max: 192ms
Min: 0ms
Avg: 0.2ms
```