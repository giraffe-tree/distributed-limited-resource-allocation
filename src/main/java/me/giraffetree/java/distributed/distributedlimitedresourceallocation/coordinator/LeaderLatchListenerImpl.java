package me.giraffetree.java.distributed.distributedlimitedresourceallocation.coordinator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;

@Slf4j
public class LeaderLatchListenerImpl implements LeaderLatchListener {

    private final String RESOURCES_CONTAINER = "/resources";
    private final CuratorFramework curatorFramework;
    private final CuratorCache curatorCache;

    public LeaderLatchListenerImpl(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
        this.curatorCache = CuratorCache.build(curatorFramework, RESOURCES_CONTAINER);
    }

    @Override
    public void isLeader() {
        // 启动 tcp server
        log.info("i am leader...");
        // watch 资源 list

        // watch server list

        // 检查是否需要重新分配

    }

    private void watchResources() {
        CuratorCacheListener listener = CuratorCacheListener.builder()
                .forCreates(node -> System.out.println(String.format("Node created: [%s]", node)))
                .forChanges((oldNode, node) -> System.out.println(String.format("Node changed. Old: [%s] New: [%s]", oldNode, node)))
                .forDeletes(oldNode -> System.out.println(String.format("Node deleted. Old value: [%s]", oldNode)))
                .forInitialized(() -> {
                    // 分配


                })
                .build();
        curatorCache.listenable().addListener(listener);
        curatorCache.start();
    }

    @Override
    public void notLeader() {
        log.info("i am follower...");
    }

}
