package com.github.kboyle.valheimcharactereditor.commands;

import kboyle.oktane.core.BeanProvider;

import java.util.HashMap;

public class ValheimBeanProvider implements BeanProvider {
    private final HashMap<Class<?>, Object> beans;

    public ValheimBeanProvider() {
        beans = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(Class<T> clazz) {
        return (T) beans.get(clazz);
    }

    public ValheimBeanProvider put(Object bean) {
        beans.put(bean.getClass(), bean);
        return this;
    }
}
