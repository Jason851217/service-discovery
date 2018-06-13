package com.learningcenter.web;

import com.google.gson.Gson;
import com.learningcenter.web.vo.Response;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApplication.class, args);

        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.188.128:2181",new RetryOneTime(1000));
        //启动
        client.start();
        // 阻塞直到连上为止
        client.blockUntilConnected();

        //服务发现者
        ServiceDiscovery<Object> serviceDiscovery = ServiceDiscoveryBuilder.builder(Object.class).client(client).basePath("/soa").build();
        //获取product服务实例
        Collection<ServiceInstance<Object>> serviceInstances =  serviceDiscovery.queryForInstances("product");


        System.out.println(serviceInstances.size());

        serviceInstances.forEach((serviceInstance)->{
            String ip = serviceInstance.getAddress();
            int port = serviceInstance.getPort();
            System.out.println(ip+":"+port);
        });

        LoadBalance loadBalance =new LoadBalance(serviceInstances);
        ServiceInstance<Object> instance = loadBalance.choose();

        RestTemplate restTemplate =new RestTemplate();

        String body =  restTemplate.getForObject("http://"+instance.getAddress()+":"+instance.getPort()+"/product/1",String.class);

        Response response =new Gson().fromJson(body,Response.class);

        System.out.println(response.getData());

    }
}
