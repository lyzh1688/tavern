package com.tuoyou.tavern.rpc.libs.core;

import com.tuoyou.tavern.protocol.common.annotation.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created by 刘悦之 on 2019/7/1.
 */
public class ResourcePathParser {

    static Logger logger = LoggerFactory.getLogger(ResourcePathParser.class);

    public static String variable(String paramName) {
        return "${" + paramName + "}";
    }

    public static boolean variableExists(String resource, String paramName) {
        return resource.contains(variable(paramName));
    }

    public static String parse(String resource, Method method, Object[] args) {
        try {
            Annotation[][] annotations = method.getParameterAnnotations();
            Parameter[] parameters = method.getParameters();
            int i = 0;
            String uriPath = resource;
            for (Annotation[] annoArray : annotations) {
                for (Annotation annotation : annoArray) {
                    if (annotation.annotationType().equals(PathParam.class)) {
                        PathParam pathParam = (PathParam) annotation;
                        if (variableExists(resource, pathParam.name())) {
                            String value = String.valueOf(args[i]);
                            uriPath = uriPath.replace(variable(pathParam.name()), value);
                        }
                    }
                }
            }
            return uriPath;
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return "";
        }
    }

}
