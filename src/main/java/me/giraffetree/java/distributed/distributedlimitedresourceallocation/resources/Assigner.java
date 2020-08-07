package me.giraffetree.java.distributed.distributedlimitedresourceallocation.resources;

import me.giraffetree.java.distributed.distributedlimitedresourceallocation.members.MemberMetadata;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author GiraffeTree
 * @date 2020/7/31
 */
public interface Assigner {

    Map<MemberMetadata, Set<ResourceMetadata>> assign(List<MemberMetadata> members, List<ResourceMetadata> resourceList);

}
