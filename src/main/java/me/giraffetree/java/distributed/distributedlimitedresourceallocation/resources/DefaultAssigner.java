package me.giraffetree.java.distributed.distributedlimitedresourceallocation.resources;

import me.giraffetree.java.distributed.distributedlimitedresourceallocation.members.MemberMetadata;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author GiraffeTree
 * @date 2020/7/31
 */
public class DefaultAssigner implements Assigner {

    private List<MemberMetadata> members;
    private List<ResourceMetadata> resourceList;

    public DefaultAssigner(List<MemberMetadata> members, List<ResourceMetadata> resourceList) {
        this.members = members;
        this.resourceList = resourceList;
    }

    @Override
    public Map<MemberMetadata, Set<ResourceMetadata>> assign() {


        return null;
    }

}
