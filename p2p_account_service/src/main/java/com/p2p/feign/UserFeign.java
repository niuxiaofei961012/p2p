package com.p2p.feign;

import com.p2p.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("user-service")
public interface UserFeign {
    /**
     * 修改user信息
     * @param user
     * @return
     */
    @PostMapping("p2p/user/updateUserInfo")
    public boolean updateUserInfo(@RequestBody User user);
}
