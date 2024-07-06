package com.jfy.feign;

import com.jfy.pojo.Discuss;
import com.jfy.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "kfc-service",path = "/discuss")
public interface DiscussFeignService {
    @RequestMapping("/addDiscuss/{uid}/{pid}")
    void addDiscuss(@PathVariable("uid") Integer uid,@PathVariable("pid") Integer pid,@RequestBody Discuss discuss);


    @RequestMapping("/getDiscussByPid/{pid}")
    List<User> getDiscussByPid(@PathVariable("pid") Integer pid);

    @RequestMapping("/getDiscussByUid/{uid}")
    List<Discuss> getDiscussByUid(@PathVariable("uid") Integer uid);

    @RequestMapping("/deleteDiscuss/{pid}")
    boolean deleteDiscuss(@PathVariable("pid") Integer pid);
}
