package com.baidu.disconf.web.web.role.controller;

import com.baidu.disconf.web.service.role.bo.Role;
import com.baidu.disconf.web.service.role.bo.RoleEnum;
import com.baidu.disconf.web.service.role.service.RoleMgr;
import com.baidu.disconf.web.service.user.service.UserMgr;
import com.baidu.dsp.common.constant.WebConstants;
import com.baidu.dsp.common.controller.BaseController;
import com.baidu.dsp.common.vo.JsonObjectBase;
import com.baidu.ub.common.commons.ThreadContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author liaoqiqi
 * @version 2014-6-16
 */
@Controller
@RequestMapping(WebConstants.API_PREFIX + "/role")
public class RoleController extends BaseController {

    protected static final Logger LOG = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleMgr roleMgr;
    
    @Autowired
    private UserMgr userMgr;


    /**
     * list
     *
     * @return
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public JsonObjectBase findAll(Long roleId) {

        List<Role> roles = roleMgr.findAll();
        if(RoleEnum.APP_ADMIN.getValue() == Integer.valueOf(userMgr.getCurVisitor().getRole())){
            roles.removeIf(r -> r.getId() == RoleEnum.ADMIN.getValue());//应用管理员不允许看到管理员
        }else if(RoleEnum.DBA.getValue() == Integer.valueOf(userMgr.getCurVisitor().getRole())){
            roles.removeIf(r -> r.getId() == RoleEnum.ADMIN.getValue()
                    || r.getId() == RoleEnum.APP_ADMIN.getValue()
            );//DBA只能设置普通用户和DBA
        }
        return buildSuccess(roles);
    }

}
