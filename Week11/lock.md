## 实现思路
1. 多个Java程序读写同一个Redis缓存，模拟分布式场景
2. 给共享的key设置锁：set keyName value NX PX expireTime
3. 其中一个程序先执行加锁操作，其他程序之后的写操作不执行
4. 拥有锁的程序，需要执行解锁操作，来主动释放锁，其他程序的写操作才生效
5. 其中，解锁操作通过lua脚本实现，保证操作的原子性
## 代码示例
### 加锁：
```
public static Boolean lock(Jedis jedis, String lockKey, String value, Integer expireTime)
{
    String result = jedis.set(lockKey, value, "NX", "PX", expireTime);
    return "OK".equals(result);
}
```
### 执行lua脚本：
```
public static Object execLuaScript(Jedis jedis, String key, String value, String fileName) throws IOException
{
    FileInputStream in = new FileInputStream(ClassLoader.getSystemResource(fileName).getPath());
    byte[] bytes = new byte[in.available()];
    in.read(bytes);
    String scriptStr = new String(bytes);
    return jedis.eval(scriptStr, Arrays.asList(key), Arrays.asList(value));
}
```
### lua脚本：
```
if redis.call("get", KEYS[1]) == ARGV[1] then
    return redis.call("del", KEYS[1])
else
    return 0
end
```
### 模拟加锁、解锁的Java程序
```
public class JedisDemo
{
    public static void main(String[] args) throws InterruptedException, IOException
    {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String key = "testJedis";
        String value = JedisDemo.class.getSimpleName();
        Integer expireTime = 200000;
        String fileName = "redis-unlock.lua";
        // 加锁
        Boolean result = JedisUtils.lock(jedis, key, value, expireTime);
        if (result)
        {
            System.out.println("lock success");
        }
        int i = 0;
        while (true)
        {
            Thread.sleep(2000);
            i++;
            System.out.println("第" + i + "次：");
            // 解锁
            if (i == 5)
            {
                Object evalResult = JedisUtils.execLuaScript(jedis, key, value, fileName);
                if ("1".equals(evalResult.toString()))
                {
                    System.out.println("unlock success");
                }
            }
            System.out.println(jedis.get(key));
        }
    }
}
```
### 模拟竞态关系的Java程序
```
public class JedisDemo1
{
    public static void main(String[] args) throws InterruptedException, IOException
    {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String key = "testJedis";
        String value = JedisDemo1.class.getSimpleName();
        String fileName = "redis-unlock.lua";
        int i = 0;
        while (true)
        {
            Thread.sleep(2000);
            i++;
            System.out.println("第" + i + "次：");
            System.out.println("unlock = " + (JedisUtils.execLuaScript(jedis, key, value, fileName).toString().equals("0") ? "failed" : "success"));

            System.out.println("first get = " + jedis.get(key));
            Long result = jedis.setnx(key, value);
            System.out.println("setnx = " + result);
            System.out.println("second get = " + jedis.get(key));
            System.out.println();
        }
    }
}
```
### 结果摘录
#### JedisDemo:
```
lock success
第1次：
JedisDemo
第2次：
JedisDemo
第3次：
JedisDemo
第4次：
JedisDemo
第5次：
unlock success
null
第6次：
JedisDemo1
第7次：
JedisDemo1
```
#### JedisDemo1:
```
第1次：
unlock = failed
first get = JedisDemo
setnx = 0
second get = JedisDemo

第2次：
unlock = failed
first get = JedisDemo
setnx = 0
second get = JedisDemo

第3次：
unlock = failed
first get = JedisDemo
setnx = 0
second get = JedisDemo

第4次：
unlock = failed
first get = null
setnx = 1
second get = JedisDemo1

第5次：
unlock = success
first get = null
setnx = 1
second get = JedisDemo1

第6次：
unlock = success
first get = null
setnx = 1
second get = JedisDemo1

第7次：
unlock = success
first get = null
setnx = 1
second get = JedisDemo1
```
从结果可以观察到：在JedisDemo主动释放锁之前，其他程序无法执行写操作