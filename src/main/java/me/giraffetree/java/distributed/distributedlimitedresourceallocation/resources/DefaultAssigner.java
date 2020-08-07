package me.giraffetree.java.distributed.distributedlimitedresourceallocation.resources;

import me.giraffetree.java.distributed.distributedlimitedresourceallocation.members.MemberMetadata;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.*;

/**
 * @author GiraffeTree
 * @date 2020/7/31
 */
@NotThreadSafe
public class DefaultAssigner implements Assigner {

    public DefaultAssigner() {
    }

    @Override
    public Map<MemberMetadata, Set<ResourceMetadata>> assign(List<MemberMetadata> members, List<ResourceMetadata> resourceList) {
        int memberSize = members.size();
        int resourceSize = resourceList.size();
        HashMap<MemberMetadata, Set<ResourceMetadata>> map = new HashMap<>(memberSize);
        int index = 0;
        int initialSize = resourceSize / memberSize + 1;
        for (ResourceMetadata resourceMetadata : resourceList) {
            int cur = index++;
            MemberMetadata memberMetadata = members.get(cur);
            Set<ResourceMetadata> set = map.computeIfAbsent(memberMetadata, k -> new HashSet<>(initialSize));
            set.add(resourceMetadata);
        }
        return map;
    }

}
