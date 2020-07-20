package me.giraffetree.java.distributed.distributedlimitedresourceallocation.configuration;

import me.giraffetree.java.distributed.distributedlimitedresourceallocation.coordinator.GroupCoordinator;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author GiraffeTree
 * @date 2020/7/20
 */
@Configuration
public class CoordinatorConfig {

    /**
     * 自动调用 destroyMethod close
     *
     * @return CuratorFramework
     */
    @Bean(destroyMethod = "close")
    public CuratorFramework curatorFramework() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .retryPolicy(retryPolicy)
                .namespace("app")
                .build();
        client.start();
        return client;
    }

    @Bean(destroyMethod = "close")
    public GroupCoordinator groupCoordinator() {
        GroupCoordinator groupCoordinator = new GroupCoordinator(curatorFramework(), "/coordinator");
        try {
            groupCoordinator.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return groupCoordinator;
    }

}