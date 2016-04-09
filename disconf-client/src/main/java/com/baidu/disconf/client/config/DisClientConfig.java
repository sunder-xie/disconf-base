package com.baidu.disconf.client.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import com.baidu.disconf.client.config.inner.DisInnerConfigAnnotation;
import com.baidu.disconf.client.support.DisconfAutowareConfig;
import com.baidu.disconf.core.common.constants.Constants;

/**
 * Disconf Client的用户配置文件
 *
 * @author liaoqiqi
 * @version 2014-6-6
 */
public final class DisClientConfig {

    protected static final Logger LOGGER = LoggerFactory.getLogger(DisClientConfig.class);

    protected static final DisClientConfig INSTANCE = new DisClientConfig();

    public static DisClientConfig getInstance() {
        return INSTANCE;
    }


    private boolean isLoaded = false;

    private DisClientConfig() {

    }

    public synchronized boolean isLoaded() {
        return isLoaded;
    }

    /**
     * load config normal
     *
     * @throws Exception
     */
    public synchronized void loadConfig(Resource propertiesLocation) throws Exception {

        if (isLoaded) {
            return;
        }

        DisconfAutowareConfig.autowareConfigFromFileSystem(INSTANCE, propertiesLocation);

        isLoaded = true;
    }

    /**
     * 配置文件服务器 HOST
     */
    public static final String CONF_SERVER_HOST_NAME = "disconf.conf_server_host";
    @DisInnerConfigAnnotation(name = DisClientConfig.CONF_SERVER_HOST_NAME, defaultValue="disconf.iapi.ymatou.com")
    public String CONF_SERVER_HOST;

    private List<String> hostList;

    /**
     * app
     *
     * @author
     * @since 1.0.0
     */
    public static final String APP_NAME = "disconf.app";
    @DisInnerConfigAnnotation(name = DisClientConfig.APP_NAME)
    public String APP;

    /**
     * 版本
     *
     * @author
     * @since 1.0.0
     */
    public static final String VERSION_NAME = "disconf.version";
    @DisInnerConfigAnnotation(name = DisClientConfig.VERSION_NAME)
    public String VERSION = Constants.DEFAULT_VERSION;


    /**
     * 部署环境
     *
     * @author
     * @since 1.0.0
     */
    public static final String ENV_NAME = "disconf.env";
    @DisInnerConfigAnnotation(name = DisClientConfig.ENV_NAME)
    public String ENV = Constants.DEFAULT_ENV;

    /**
     * 是否从云端下载配置
     *
     * @author
     * @since 1.0.0
     */
    private static final String ENABLE_REMOTE_CONF_NAME = "disconf.enable.remote.conf";
    @DisInnerConfigAnnotation(name = DisClientConfig.ENABLE_REMOTE_CONF_NAME, defaultValue = "true")
    public boolean ENABLE_DISCONF = true;

    /**
     * 是否开启DEBUG模式: 默认不开启，
     * 1）true: 用于线下调试，当ZK断开与client连接后（如果设置断点，这个事件很容易就发生），ZK不会去重新建立连接。
     * 2）false: 用于线上，当ZK断开与client连接后，ZK会再次去重新建立连接。
     *
     * @author
     * @since 1.0.0
     */
    @DisInnerConfigAnnotation(name = "disconf.debug", defaultValue = "false")
    public boolean DEBUG = false;

    /**
     * 忽略哪些分布式配置
     *
     * @author
     * @since 1.0.0
     */
    @DisInnerConfigAnnotation(name = "disconf.ignore", defaultValue = "")
    public String IGNORE_DISCONF_LIST = "";
    private Set<String> ignoreDisconfKeySet = new HashSet<String>();

    /**
     * 获取远程配置 重试次数，默认是3次
     *
     * @author
     * @since 1.0.0
     */
    @DisInnerConfigAnnotation(name = "disconf.conf_server_url_retry_times", defaultValue = "3")
    public int CONF_SERVER_URL_RETRY_TIMES = 3;


    /**
     * 获取远程配置 重试时休眠时间，默认是2秒
     *
     * @author
     * @since 1.0.0
     */
    @DisInnerConfigAnnotation(name = "disconf.conf_server_url_retry_sleep_seconds", defaultValue = "2")
    public int confServerUrlRetrySleepSeconds = 2;
    
    
    /**
     * 用户指定的 下载文件夹, 远程文件下载后会放在这里
     *
     * @author
     * @since 1.0.0
     */
    @DisInnerConfigAnnotation(name = "disconf.user_define_download_dir")
    public String userDefineDownloadDir;

    
    public boolean unitTestMode = false;

    public List<String> getHostList() {
        return hostList;
    }

    public void setHostList(List<String> hostList) {
        this.hostList = hostList;
    }

    public Set<String> getIgnoreDisconfKeySet() {
        return ignoreDisconfKeySet;
    }

    public void setIgnoreDisconfKeySet(Set<String> ignoreDisconfKeySet) {
        this.ignoreDisconfKeySet = ignoreDisconfKeySet;
    }

}
