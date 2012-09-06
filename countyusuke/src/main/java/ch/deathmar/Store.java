package ch.deathmar;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;

import java.io.IOException;

public class Store {
   static Cache<String , Object> cache;
    static{
        try {
            cache = new DefaultCacheManager(Store.class.getResourceAsStream("/infinispan.xml")).getCache("xml-configured-cache");
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    public static void put(String str, Object obj){
        cache.put(str,obj);
    }
    public static Object get(String str){
        return cache.get(str);
    }
}
