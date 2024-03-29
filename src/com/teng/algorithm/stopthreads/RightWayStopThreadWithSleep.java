package com.teng.algorithm.stopthreads;

/**
 * 带有sleep中断线程的写法
 */
public class RightWayStopThreadWithSleep {
    public static void main(String[] args) {
        Runnable runnable = ()-> {
          // 打出100的倍数
            int num = 0;
            try {
                while (num <= 300 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
