package com.learningcenter.web;

import org.apache.curator.x.discovery.ServiceInstance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * 描述:
 *
 * @author Jason
 * @email 285290078@qq.com
 * @create 2018-06-13 21:30
 **/
public class LoadBalance {
    private int index;
    private List<ServiceInstance<Object>> services=new ArrayList<ServiceInstance<Object>>();



    public LoadBalance(Collection<ServiceInstance<Object>> serviceInstances) {
        this.services = (List<ServiceInstance<Object>>)serviceInstances;
    }

    public ServiceInstance choose(){
        index=new Random().nextInt(services.size());
        return services.get(index);
    }
}
