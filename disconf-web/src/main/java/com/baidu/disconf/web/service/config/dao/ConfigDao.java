package com.baidu.disconf.web.service.config.dao;

import java.util.List;

import com.baidu.disconf.core.common.constants.DisConfigTypeEnum;
import com.baidu.disconf.web.service.config.bo.Config;
import com.baidu.dsp.common.form.RequestListBase.Page;
import com.baidu.ub.common.db.DaoPageResult;
import com.baidu.unbiz.common.genericdao.dao.BaseDao;

/**
 * @author liaoqiqi
 * @version 2014-6-16
 */
public interface ConfigDao extends BaseDao<Long, Config> {

    /**
     * @param appId
     * @param envId
     * @param env
     * @param key
     * @param disConfigTypeEnum
     *
     * @return
     */
    Config getByParameter(Long appId, Long envId, String env, String key, DisConfigTypeEnum disConfigTypeEnum);

    /**
     * @param
     *
     * @return
     */
    List<Config> getConfByAppEnv(Long appId, Long envId);

    List<Config> getConfByApp(Long appId);

    /**
     * @param appId
     * @param envId
     * @param version
     *
     * @return
     */
    DaoPageResult<Config> getConfigList(Long appId, Long envId, String version, Page page);

    /**
     * @param configId
     *
     * @return
     */
    void updateValue(Long configId, String value);

    /**
     *
     */
    String getValue(Long configId);

    /**
     * @param appId
     * @param envId
     * @param version
     *
     * @return
     */
    List<Config> getConfigList(Long appId, Long envId, String version);

    /**
     * @param configId
     */
    void deleteItem(Long configId);
    
    /**
     * 查询配置元信息（即不包含配置值）
     * 当前供.NET客户端使用
     * @param appId
     * @param envId
     * @param version
     * @param name
     * @return
     */
    List<Config> getConfigMetas(Long appId, Long envId, String version, String name );
    
    /**
     * 返回配置项（不包含配置文件）
     * 当前供.NET客户端使用
     * @param appId
     * @param envId
     * @param version
     * @return
     */
    List<Config> getConfigItems(Long appId, Long envId, String version);
}
