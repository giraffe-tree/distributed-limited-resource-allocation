package me.giraffetree.java.distributed.distributedlimitedresourceallocation.starter;

import me.giraffetree.java.distributed.distributedlimitedresourceallocation.members.MemberRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

/**
 * @author GiraffeTree
 * @date 2020/7/31
 */
@Service
public class ServiceStarter implements CommandLineRunner {

    @Autowired
    private MemberRegister memberRegister;

    @Override
    public void run(String... args) throws Exception {
        memberRegister.start();
    }
}
