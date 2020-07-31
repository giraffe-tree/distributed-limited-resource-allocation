package me.giraffetree.java.distributed.distributedlimitedresourceallocation.members;

import lombok.extern.slf4j.Slf4j;
import me.giraffetree.java.distributed.distributedlimitedresourceallocation.coordinator.GroupCoordinator;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.io.Closeable;
import java.io.IOException;

/**
 * 注册 member 节点
 * 监听 assign
 *
 * @author GiraffeTree
 * @date 2020/7/31
 */
@Slf4j
public class MemberRegister implements Closeable {

    private final static String MEMBERS_CONTAINER = "/members";
    private final static String ASSIGNED_CONTAINER = "/assigned";
    private final CuratorFramework curatorFramework;
    private final String path;
    private final CuratorCache cache;
    private final String nodeName;

    public MemberRegister(CuratorFramework curatorFramework, String nodeName) {
        this.curatorFramework = curatorFramework;
        this.nodeName = nodeName;
        this.path = MEMBERS_CONTAINER + "/" + nodeName;
        // 监听 /assigned
        this.cache = CuratorCache.build(curatorFramework, ASSIGNED_CONTAINER);
    }

    public void start() {
        Stat stat;
        try {
            stat = curatorFramework.checkExists().forPath(MEMBERS_CONTAINER);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (stat == null) {
            try {
                curatorFramework.createContainers(MEMBERS_CONTAINER);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String result;
        try {
            result = curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("member create {} {}", path, result);
        addListener();
        cache.start();
    }

    private void addListener() {
        CuratorCacheListener listener = CuratorCacheListener.builder()
                .forCreates(node -> System.out.println(String.format("Node created: [%s]", node)))
                .forChanges((oldNode, node) -> System.out.println(String.format("Node changed. Old: [%s] New: [%s]", oldNode, node)))
                .forDeletes(oldNode -> System.out.println(String.format("Node deleted. Old value: [%s]", oldNode)))
                .forInitialized(() ->
                        {
                            // 争夺 leader
                            GroupCoordinator groupCoordinator = new GroupCoordinator(curatorFramework, "/coordinator");
                            try {
                                groupCoordinator.start();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .build();
        cache.listenable().addListener(listener);
    }


    @Override
    public void close() throws IOException {
        cache.close();
    }
}
