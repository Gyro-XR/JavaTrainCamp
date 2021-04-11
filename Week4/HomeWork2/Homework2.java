package java0.conc0303;

import java.util.concurrent.*;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 一个简单的代码参考：
 */
public class Homework03
{

    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        long start = System.currentTimeMillis();

        final int[] res = {0};

        // 在这里创建一个线程或线程池，
        //method1(res);
        //method2(res);
        //res[0] = method3();
        //res[0] = method4();
        //res[0] = method5();
        res[0] = method6();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + res[0]);

        System.out.println("主线程使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }

    private static int sum()
    {
        return fibo(36);
    }

    private static int fibo(int a)
    {
        if (a < 2)
        {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }

    /**
     * 方法一：
     * 1、实现Runnable接口，重写run()方法时，传入外部变量，接收sum()函数运行结果；
     * 2、用Thread包装，.start()启动新线程，执行run()方法
     * 3、.join()，让主线程等待，直到子线程执行结束
     *
     * @param result
     * @return
     * @throws InterruptedException
     */
    private static int method1(int[] result) throws InterruptedException
    {
        printMethodName();
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                result[0] = sum();
            }
        });
        System.out.println("当前子线程为：" + thread.getName());
        thread.start();
        thread.join();
        return result[0];
    }

    /**
     * 打印方法名，作为提示
     */
    private static void printMethodName()
    {
        // 1表示当前的方法堆栈，2表示上一级，以此类推
        System.out.println("当前执行方法：" + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    /**
     * 方法二：
     * 与方法一类似，差别在于：新建ThreadDemo类，继承Thread后，重写run()方法
     *
     * @param result
     * @return
     * @throws InterruptedException
     */
    private static int method2(int[] result) throws InterruptedException
    {
        printMethodName();
        class ThreadDemo extends Thread
        {

            @Override
            public void run()
            {
                new Runnable()
                {
                    @Override
                    public void run()
                    {
                        result[0] = sum();
                    }
                }.run();
            }
        }

        ThreadDemo thread = new ThreadDemo();
        System.out.println("当前子线程为：" + thread.getName());
        thread.start();
        thread.join();
        return result[0];
    }

    /**
     * 方法三：
     * Callable+Future+Excutors.newSingleThreadExecutor
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static int method3() throws ExecutionException, InterruptedException
    {
        printMethodName();
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<Integer> future = es.submit(getCallable());
        es.shutdown();
        return future.get();
    }

    /**
     * Callable实现
     *
     * @return
     */
    private static Callable<Integer> getCallable()
    {
        return new Callable<Integer>()
        {
            @Override
            public Integer call() throws Exception
            {
                return sum();
            }
        };
    }

    /**
     * 方法四：
     * Callable+Future+new ThreadPoolExecutor
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static int method4() throws ExecutionException, InterruptedException
    {
        printMethodName();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1));
        Future<Integer> future = executor.submit(getCallable());
        executor.shutdown();
        return future.get();
    }

    /**
     * 方法五：
     * Callable+FutureTask+Excutors.newSingleThreadExecutor
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static int method5() throws ExecutionException, InterruptedException
    {
        printMethodName();
        ExecutorService es = Executors.newSingleThreadExecutor();
        FutureTask<Integer> futureTask = new FutureTask<>(getCallable());
        es.submit(futureTask);
        es.shutdown();
        return futureTask.get();
    }

    /**
     * 方法六：
     * Callable+FutureTask+new ThreadPoolExecutor
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static int method6() throws ExecutionException, InterruptedException
    {
        printMethodName();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1));
        FutureTask<Integer> futureTask = new FutureTask<>(getCallable());
        executor.submit(futureTask);
        executor.shutdown();
        return futureTask.get();
    }


}
