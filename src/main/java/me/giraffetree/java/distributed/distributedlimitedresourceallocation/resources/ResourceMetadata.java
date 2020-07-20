package me.giraffetree.java.distributed.distributedlimitedresourceallocation.resources;

import lombok.Data;

/**
 * @author GiraffeTree
 * @date 2020/7/17
 */
@Data
public class ResourceMetadata {

    private String id;
    private String name;
    
    public ResourceMetadata(String id) {
        this.id = id;
        this.name = id;
    }

    public ResourceMetadata(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
