package org.bsuir.ivan.infrostructure;

import static java.lang.String.format;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import lombok.SneakyThrows;

public class WebApplicationContext{

    private static Map<String, Object> beans = new HashMap<>();
    private static void registerBean(Object bean){
        beans.put(bean.getClass().getName(), bean);
    }

    @SneakyThrows
    public static <T> T getBean(Class<T> beanClass){
        return  (T) beans.values()
                .stream()
                .filter(bean -> bean.getClass().isAssignableFrom(beanClass))
                .findFirst()
                .orElseThrow(() -> new NoSuchBeanException(format("No such bean with id [%s]", beanClass.getName())));
    }
}
