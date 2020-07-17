package me.giraffetree.java.distributed.distributedlimitedresourceallocation.resources;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author GiraffeTree
 * @date 2020/7/17
 */
public class ResourcesManager {

    private final static ConcurrentHashMap<String, ResourceMetadata> RESOURCE_CACHE
            = new ConcurrentHashMap<>(16);

    static {
        RESOURCE_CACHE.put("1", new ResourceMetadata("1"));
        RESOURCE_CACHE.put("2", new ResourceMetadata("2"));
        RESOURCE_CACHE.put("3", new ResourceMetadata("3"));
        RESOURCE_CACHE.put("4", new ResourceMetadata("4"));
        RESOURCE_CACHE.put("5", new ResourceMetadata("5"));
        RESOURCE_CACHE.put("6", new ResourceMetadata("6"));
        RESOURCE_CACHE.put("7", new ResourceMetadata("7"));
        RESOURCE_CACHE.put("8", new ResourceMetadata("8"));
        RESOURCE_CACHE.put("9", new ResourceMetadata("9"));
        RESOURCE_CACHE.put("10", new ResourceMetadata("10"));
        RESOURCE_CACHE.put("11", new ResourceMetadata("11"));
        RESOURCE_CACHE.put("12", new ResourceMetadata("12"));
        RESOURCE_CACHE.put("13", new ResourceMetadata("13"));
        RESOURCE_CACHE.put("14", new ResourceMetadata("14"));
        RESOURCE_CACHE.put("15", new ResourceMetadata("15"));
        RESOURCE_CACHE.put("16", new ResourceMetadata("16"));
    }

    /**
     * 获取所有资源
     *
     * @return 资源集合
     */
    public static Collection<ResourceMetadata> getAllResource() {
        return RESOURCE_CACHE.values();
    }

}
