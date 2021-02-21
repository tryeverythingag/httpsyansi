package com.java.controller;

import com.java.pojo.admin.OneMenu;
import com.java.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author ron
 * @data 2019/11/29- 17:29
 */
@Controller
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    /**
     * 获取系统用户
     *
     * @return
     */
    @RequestMapping("/getSystemUser.do")
    public @ResponseBody
    List<Map<String, Object>> getSystemUser() {
        return authorityService.findSystemUser();
    }

    /**
     * 授权权限信息查询
     *
     * @param id
     * @return
     */
    @RequestMapping("/getAuthority.do")
    public @ResponseBody
    List<Map<String, Object>> getAuthority(@RequestParam(name = "id", defaultValue = "0") Long id) {
        return authorityService.findAuthority(id);
    }

    /**
     * 添加系统用户并且授权
     *
     * @param username
     * @param pwd
     * @param menuIds
     * @return
     * @throws Exception
     */
    @RequestMapping("/addSystemUser.do")
    public @ResponseBody
    boolean addSystemUser(String username, String pwd, String menuIds) throws Exception {
        System.out.println("username=" + username + ",pwd" + pwd + ",menuIds=" + menuIds);
        return authorityService.saveSystemUser(username, pwd, menuIds);
    }

    /**
     * 获取权限信息，通过关联关系描述
     *
     * @return
     */
    @RequestMapping("/findAuthorityByRelation.do")
    public @ResponseBody
    List<OneMenu> findAuthorityByRelation() {
        return authorityService.findAuthorityByRelation();
    }

    /**
     * 根据用户id查询出用户拥有的权限id
     *
     * @param userId
     * @return
     */
    @RequestMapping("/getOwnAuthorityId.do")
    public @ResponseBody
    List<Long> getOwnAuthorityId(Long userId) {
        return authorityService.findOwnAuthorityId(userId);
    }
}