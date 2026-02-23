package com.example.demo;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
  private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
  private static final ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

  // 并发数
  private static final int THREAD_COUNT = 5000;

  public static void main(String[] args) { 
    SpringApplication.run(DemoApplication.class, args);
    ExecutorService executorService = Executors.newFixedThreadPool(100); // 100 个线程池大小
    // 记录开始时间
    long startTime = System.nanoTime();
    // 启动 5000 个线程进行操作
    for (int i = 0; i < THREAD_COUNT; i++) {
      executorService.submit(() -> {
        try {
          // 写操作
          int key = (int) (Math.random() * 10000); // 随机生成一个 key
          map.put(key, "Value-" + key);

          // 读操作
          map.get(key);
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
    }
    // 关闭线程池并等待所有任务执行完毕
    executorService.shutdown();
    try {
      if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
        logger.warn("Thread pool did not terminate in time");
      }
    } catch (InterruptedException e) {
      logger.error("Interrupted while waiting for thread pool to terminate");
      Thread.currentThread().interrupt(); // 恢复中断状态
    }

    // 记录结束时间
    long endTime = System.nanoTime();

    // 计算并发性能
    long duration = endTime - startTime;
    logger.info("Execution time for 5000 concurrent operations: {} ms", duration / 1000000);

    // 输出 map 的大小，确认数据插入的数量
    logger.info("Map容量大小:{}", map.size());
  }
}
