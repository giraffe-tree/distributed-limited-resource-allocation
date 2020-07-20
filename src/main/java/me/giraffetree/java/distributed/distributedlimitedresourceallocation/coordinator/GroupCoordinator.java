package me.giraffetree.java.distributed.distributedlimitedresourceallocation.coordinator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;

/**
 * @author GiraffeTree
 * @date 2020/7/20
 */
public class GroupCoordinator {

    private final static String DEFAULT_PATH = "/coo";
    private final CuratorFramework curatorFramework;
    private LeaderLatch leaderLatch;
    private final String path;

    public GroupCoordinator(CuratorFramework curatorFramework, String path) {
        this.curatorFramework = curatorFramework;
        this.path = path;
        leaderLatch = new LeaderLatch(curatorFramework, path);
        leaderLatch.addListener(new LeaderLatchListenerImpl(curatorFramework));
    }

    public void start() throws Exception {
        leaderLatch.start();
    }


}

@Slf4j
class LeaderLatchListenerImpl implements LeaderLatchListener {

    private final CuratorFramework curatorFramework;

    public LeaderLatchListenerImpl(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }

    @Override
    public void isLeader() {
        // 启动 tcp server
        log.info("i am leader...");
        // watch 资源 list

        // watch server list

        // 检查是否需要重新分配

    }

    @Override
    public void notLeader() {
        log.info("i am follower...");
    }
}


