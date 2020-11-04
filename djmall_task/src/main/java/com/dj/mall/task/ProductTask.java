package com.dj.mall.task;

import com.dj.mall.model.util.HttpClientUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ProductTask {

    /**
     * 增量索引定时
     * @throws Exception
     */
    @Scheduled(cron = "0 0 0-4 * * ? ")
    public void addIndex() throws Exception {
        HttpClientUtil.sendHttpRequest("http://localhost:8079/solr/SolrCore_djmall/dataimport?command=delta-import",HttpClientUtil.HttpRequestMethod.GET,null);
    }

    /**
     * 重构索引定时
     * @throws Exception
     */
    @Scheduled(cron = "0 0 0-8 * * ? ")
    public void refactorindexes() throws Exception {
        HttpClientUtil.sendHttpRequest("http://localhost:8079/solr/SolrCore_djmall/dataimport?command=full-import",HttpClientUtil.HttpRequestMethod.GET,null);
    }

    /**
     * 测试
     */
/*    @Scheduled(cron = "* 0-2 * * * ?")
    public void test(){
        System.out.println(new Date().toString());
    }*/

}
