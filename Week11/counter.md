## 实现思路
1. redis.set(counter, 200)，初始化一个库存量
2. 多个Java程序连接该Redis，并执行decrby命令，模拟下订单操作（订单数随机生成）
3. 下订单操作通过lua脚本实现，保证操作的原子性
4. 库存不足、库存为0时，返回相应提示
## 代码示例
### 初始化库存量
```
public static void initial(Jedis jedis, String key)
{
    jedis.setnx(key, String.valueOf(500));
}
```
### lua脚本
```
if redis.call("get", KEYS[1]) == "0" then
    return 9999
else
    if (redis.call("get", KEYS[1]) - ARGV[1]) >= 0 then
        return redis.call("decrby", KEYS[1], ARGV[1])
    else
        return 9998
    end
end
```
### 执行lua脚本
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
### 模拟下订单的Java程序
```
public static void main(String[] args) throws IOException, InterruptedException
{
    Jedis jedis = new Jedis("127.0.0.1", 6379);
    String key = "counter";
    Random random = new Random();
    int value = 0;
    String fileName = "redis-counter.lua";
    JedisUtils.initial(jedis, key);

    boolean flag = true;
    while (flag)
    {
        value = random.nextInt(10) + 1;
        Object result = JedisUtils.execLuaScript(jedis, key, String.valueOf(value), fileName);

        System.out.println("本次下单 = " + value);
        if ("9999".equals(result.toString()))
        {
            System.out.println("卖完啦！！！库存 = " + jedis.get(key));
            flag = false;
        } else if ("9998".equals(result.toString()))
        {
            System.out.println("剩余库存不足，库存 = " + jedis.get(key));
        } else
        {
            System.out.println("下单成功，库存 = " + result);
        }
        Thread.sleep(2000);
    }
}
```
### 执行结果摘录
```
···
下单成功，库存 = 24
本次下单 = 1
下单成功，库存 = 15
本次下单 = 10
剩余库存不足，库存 = 9
本次下单 = 5
卖完啦！！！库存 = 0
```