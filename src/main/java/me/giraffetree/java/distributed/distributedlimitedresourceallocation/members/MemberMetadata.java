package me.giraffetree.java.distributed.distributedlimitedresourceallocation.members;

import lombok.Data;

/**
 * @author GiraffeTree
 * @date 2020/7/31
 */
@Data
public class MemberMetadata {

    private String name;
    private String host;
    private Integer port;
    private Long sessionTimeout;

}
