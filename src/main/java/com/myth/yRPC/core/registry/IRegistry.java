package com.myth.yRPC.core.registry;

import java.util.List;

/**
 * @description: 服务注册接口
 * @author: yuang gang
 * @create: 2019-03-07 15:44
 **/
public interface IRegistry {
    void registryServer(String className, Node node) throws Exception;
    void unRegistryServer(String className, Node node) throws Exception;
    List<Node> queryService(String className);
}
