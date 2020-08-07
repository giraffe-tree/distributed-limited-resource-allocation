package me.giraffetree.java.distributed.distributedlimitedresourceallocation.coordinator;

import lombok.extern.slf4j.Slf4j;
import me.giraffetree.java.distributed.distributedlimitedresourceallocation.resources.Assigner;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class LeaderLatchListenerImpl implements LeaderLatchListener {

    /**
     * 资源容器
     */
    private final String RESOURCES_CONTAINER = "/resources";
    /**
     * 成员容器
     */
    private final String MEMBERS_CONTAINER = "/members";
    private final CuratorFramework curatorFramework;
    private final CuratorCache resourcesCache;
    private final CuratorCache membersCache;
    private CountDownLatch initialLatch;
    private Assigner assigner;

    public LeaderLatchListenerImpl(CuratorFramework curatorFramework, Assigner assigner) {
        this.curatorFramework = curatorFramework;
        this.resourcesCache = CuratorCache.build(curatorFramework, RESOURCES_CONTAINER);
        this.membersCache = CuratorCache.build(curatorFramework, MEMBERS_CONTAINER);
        this.initialLatch = new CountDownLatch(2);
        this.assigner = assigner;
    }

    @Override
    public void isLeader() {
        // 启动 tcp server
        log.info("i am leader...");

        // watch 资源 list
        watchResources();

        // watch server list
        watchMembers();

        // 检查是否需要重新分配
        try {
            initialLatch.await();
        } catch (InterruptedException e) {
            log.error("interrupted when initial listener");
            throw new RuntimeException(e);
        }
//        assigner.assign();

    }

    private void watchMembers() {
        CuratorCacheListener membersListener = CuratorCacheListener.builder()
                .forCreates(node -> log.info(String.format("member created: [%s]", node)))
                .forChanges((oldNode, node) -> log.info(String.format("member changed. Old: [%s] New: [%s]", oldNode, node)))
                .forDeletes(oldNode -> log.info(String.format("member deleted. Old value: [%s]", oldNode)))
                .forInitialized(() -> {
                    // 分配
                    log.info("members Cache initialized");
                    initialLatch.countDown();
                })
                .build();
        membersCache.listenable().addListener(membersListener);
        membersCache.start();
    }

    private void watchResources() {
        CuratorCacheListener listener = CuratorCacheListener.builder()
                .forCreates(node -> log.info(String.format("resource created: [%s]", node)))
                .forChanges((oldNode, node) -> log.info(String.format("resource changed. Old: [%s] New: [%s]", oldNode, node)))
                .forDeletes(oldNode -> log.info(String.format("resource deleted. Old value: [%s]", oldNode)))
                .forInitialized(() -> {
                    // 分配
                    log.info("Resources Cache initialized");
                    initialLatch.countDown();
                })
                .build();
        resourcesCache.listenable().addListener(listener);
        resourcesCache.start();
    }

    @Override
    public void notLeader() {
        log.info("i am follower...");
    }

}
