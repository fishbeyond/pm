package com.hs.whocan.json;

import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.service.ServiceInterface;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by yinwenbo on 14-5-3.
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JsonApiTestService implements ServiceInterface {

    private String name;
    private int rate;

    @Override
    public Object doService() {
        if ("exception".equals(name)) {
            throw new RuntimeException(name);
        }
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "JsonApiTestServiceInterface{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                '}';
    }
}
