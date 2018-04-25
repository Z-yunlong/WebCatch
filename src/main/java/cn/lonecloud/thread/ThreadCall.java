package cn.lonecloud.thread;

import cn.lonecloud.bean.CompanyInfo;
import cn.lonecloud.utils.WaperUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @author lonecloud
 * @version v1.0
 * @date 下午7:58 2018/4/25
 */
public class ThreadCall implements Callable<List<CompanyInfo>> {

    private int start;

    private CountDownLatch latch;

    public ThreadCall(int start, CountDownLatch latch) {
        this.start = start;
        this.latch = latch;
    }

    @Override
    public List<CompanyInfo> call() throws Exception {
        List<CompanyInfo> list = new LinkedList<>();
        for (int i = start; i < 100; i++) {
            try {
                System.out.println("开始-------------------第" + i + "页");
                list.addAll(WaperUtils.catchWebByUrl("http://b2b.huangye88.com/shanghai/jiancai/pn" + i));
                System.out.println("结束-------------------第" + i + "页");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        latch.countDown();
        return list;
    }
}
