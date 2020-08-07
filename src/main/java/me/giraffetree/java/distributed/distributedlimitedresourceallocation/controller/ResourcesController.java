package me.giraffetree.java.distributed.distributedlimitedresourceallocation.controller;

import com.alibaba.fastjson.JSON;
import me.giraffetree.java.distributed.distributedlimitedresourceallocation.resources.ResourceMetadata;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GiraffeTree
 * @date 2020/8/5
 */
@RestController
@RequestMapping("resource")
public class ResourcesController {

    private CuratorFramework curatorFramework;

    @Autowired
    public ResourcesController(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }

    @PostMapping("/add")
    public Object addResource(
            @RequestParam String id, @RequestParam String name) {
        try {
            ResourceMetadata resourceMetadata = new ResourceMetadata(id, name);
            byte[] bytes = JSON.toJSONBytes(resourceMetadata);
            curatorFramework.createContainers("/resources");
            String path = curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath("/resources/" + id);
            System.out.println(path);
            Stat stat = curatorFramework.setData().forPath("/resources/" + id, bytes);
            System.out.println(stat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "OK";
    }


}
