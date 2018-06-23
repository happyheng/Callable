package com.happyheng.service;

import com.happyheng.callable.RequestCallable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 * Created by happyheng on 2018/6/23.
 */
@Service
public class CallableService {

    private static final int FUTURE_TIMEOUT = 5000;

    // 使用先进先出的队列
    public final static BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
    // 执行callable的线程池
    public static ExecutorService executorService = new ThreadPoolExecutor(5,
            15 ,
            10,
            TimeUnit.SECONDS,
            queue);


    public List<String> getCallableData(List<String> requestUrlList) {

        if (CollectionUtils.isEmpty(requestUrlList)) {
            return new ArrayList<>();
        }

        List<FutureTask<String>> futureTaskList = new ArrayList<>();
        for (String url : requestUrlList) {
            RequestCallable callable  = new RequestCallable(url);
            FutureTask<String> futureTask = new FutureTask<>(callable);
            futureTaskList.add(futureTask);

            // 线程池开始获取数据
            executorService.submit(futureTask);
        }

        // 获取数据
        List<String> resultDataList = new ArrayList<>();
        for (FutureTask<String> futureTask : futureTaskList) {
            try {
                // 获取数据，注意有超时时间，如果超出，即获取不到数据
                String resultData = futureTask.get(FUTURE_TIMEOUT, TimeUnit.MILLISECONDS);
                resultDataList.add(resultData);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                System.out.println("超时");
                e.printStackTrace();
            }finally {
                // 最后一定要调用cancel方法，里面的参数 mayInterruptIfRunning 是是否在运行的时候也关闭，如果设置为true，那么在
                // 运行的时候也能关闭，之后的代码不会再执行。
                // 如果正在运行，暂停成功，会返回true，如果运行完了，那么不管 mayInterruptIfRunning 是什么值，都会返回false。
                futureTask.cancel(true);
            }
        }

        return resultDataList;
    }


}
