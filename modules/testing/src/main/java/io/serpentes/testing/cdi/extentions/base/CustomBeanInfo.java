package io.serpentes.testing.cdi.extentions.base;

import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.InjectionTarget;

public class CustomBeanInfo<I, C extends I>  {
    private final CustomBean<I, C> customBean;

    CustomBeanInfo(CustomBean<I,C> customBean){
        this.customBean = customBean;
    }

    public Bean<I> getBean(){
        return customBean;
    }

    public InjectionTarget<C> getInjectionTarget(){
        return customBean.getInjectionTarget();
    }
}
