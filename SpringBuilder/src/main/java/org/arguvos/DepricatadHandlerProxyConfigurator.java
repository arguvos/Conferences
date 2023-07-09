package org.arguvos;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DepricatadHandlerProxyConfigurator implements ProxyConfigurator {
    @Override
    public Object replaceWithProxyIfNeeded(Object t, Class implClass) {
        if (implClass.isAnnotationPresent(Deprecated.class)) {
            if (implClass.getInterfaces().length == 0) {
                return Enhancer.create(implClass, new net.sf.cglib.proxy.InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return getInvocationHandlerLogic(t, method, args);
                    }
                });
            }
            return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    return getInvocationHandlerLogic(t, method, args);
                }
            });
        }
        return t;
    }

    private Object getInvocationHandlerLogic(Object t, Method method, Object[] args) throws Throwable {
        System.out.println("*** Вызван Depricated класс ***");
        return method.invoke(t, args);
    }
}
