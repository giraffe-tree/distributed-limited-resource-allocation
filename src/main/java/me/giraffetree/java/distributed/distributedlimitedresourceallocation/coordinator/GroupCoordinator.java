package me.giraffetree.java.distributed.distributedlimitedresourceallocation.coordinator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author GiraffeTree
 * @date 2020/7/20
 */
public class GroupCoordinator implements Closeable {

    private final static String DEFAULT_PATH = "/app";
    private final CuratorFramework curatorFramework;
    private LeaderLatch leaderLatch;
    private final String path;

    public GroupCoordinator(CuratorFramework curatorFramework) {
        this(curatorFramework, DEFAULT_PATH);
    }

    public GroupCoordinator(CuratorFramework curatorFramework, String path) {
        this.curatorFramework = curatorFramework;
        this.path = path;
        leaderLatch = new LeaderLatch(curatorFramework, path);
        leaderLatch.addListener(new LeaderLatchListenerImpl(curatorFramework));
    }

    public void start() throws Exception {
        leaderLatch.start();
    }

    @Override
    public void close() throws IOException {
        leaderLatch.close();
    }

}



