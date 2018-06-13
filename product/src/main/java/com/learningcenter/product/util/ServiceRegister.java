package com.learningcenter.product.util;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 注意：关闭防火墙
 */
@Component
public class ServiceRegister implements ApplicationRunner {
  @Value("${zookeeper.address}")
  private String zkAddress;

  public void run(ApplicationArguments args) throws Exception{
      CuratorFramework client = CuratorFrameworkFactory.newClient(zkAddress,new RetryOneTime(1000));
      //启动
      client.start(); 
      // 阻塞直到连上为止
      client.blockUntilConnected();      
       //服务实例,包含服务名，以及对应服务所在的ip和port
      ServiceInstance<Object> instance = ServiceInstance.builder().name("product").address("192.168.1.100").port(8080).build();
       //服务发现者
      ServiceDiscovery<Object> serviceDiscovery = ServiceDiscoveryBuilder.builder(Object.class).client(client).basePath("/soa").build();
      //服务发现者注册服务实例
      serviceDiscovery.registerService(instance);

      serviceDiscovery.start();
      System.out.println("service register ok");
  }
}
