package com.tuoyou.tavern.rpc.libs.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tuoyou.tavern.protocol.common.annotation.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created by 刘悦之 on 2019/7/1.
 */
public interface ResourceParser {

    Logger logger = LoggerFactory.getLogger(ResourceParser.class);

    default String variable(String paramName) {
        return "${" + paramName + "}";
    }

    default boolean variableExists(String resource, String paramName) {
        return resource.contains(variable(paramName));
    }

    default String parseRequestBody(Method method, Object[] args){
        Parameter[] parameters = method.getParameters();
        int i = 0;
        for(Parameter parameter : parameters){
            if(parameter.getAnnotations().length == 0){
                Object requestBody = args[i];
                return JSON.toJSONString(requestBody);
            }
            i++;
        }
        return "";
    }

    default String parseUri(String resource, Method method, Object[] args) {
        try {
            Annotation[][] annotations = method.getParameterAnnotations();
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
                i++;

            }
            return uriPath;
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return "";
        }
    }

}
