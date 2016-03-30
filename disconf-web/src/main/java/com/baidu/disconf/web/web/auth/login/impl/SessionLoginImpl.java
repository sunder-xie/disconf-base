package com.baidu.disconf.web.web.auth.login.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.baidu.disconf.web.service.user.bo.User;
import com.baidu.disconf.web.service.user.constant.UserConstant;
import com.baidu.disconf.web.service.user.dto.Visitor;
import com.baidu.disconf.web.web.auth.constant.LoginConstant;
import com.baidu.disconf.web.web.auth.login.SessionLogin;
import com.baidu.ub.common.commons.ThreadContext;
import com.github.knightliao.apollo.redis.RedisCacheManager;
import com.github.knightliao.apollo.utils.web.CookieUtils;

/**
 * @author liaoqiqi
 * @version 2014-2-4
 */
public class SessionLoginImpl implements SessionLogin {


    /**
     * 登录
     */
    @Override
    public void login(HttpServletRequest request, User user, int expireTime) {

        Visitor visitor = new Visitor();

        //
        //
        //
        visitor.setId(user.getId());
        visitor.setLoginUserId(user.getId());
        visitor.setLoginUserName(user.getName());
        visitor.setRoleId(user.getRoleId());
        visitor.setAppIds(user.getOwnApps());

        //
        // 更新session
        //
        updateSessionVisitor(request.getSession(), visitor);

    }


    /**
     * 更新Session中的Userid
     *
     * @param session
     * @param visitor
     */
    public void updateSessionVisitor(HttpSession session, Visitor visitor) {

        if (visitor != null) {
            // 更新
            session.setAttribute(UserConstant.USER_KEY, visitor);
        } else {

            // 删除
            session.removeAttribute(UserConstant.USER_KEY);
        }

        ThreadContext.putSessionVisitor(visitor);
    }

    /**
     * 登出
     */
    @Override
    public void logout(HttpServletRequest request) {

        // 更新session
        updateSessionVisitor(request.getSession(), null);

    }

}