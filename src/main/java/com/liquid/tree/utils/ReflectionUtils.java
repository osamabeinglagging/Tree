package com.liquid.tree.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Stolen from JellyLab's Farmhelper and They Stole it from ShadyAddons
public class ReflectionUtils {
    public static boolean invoke(Object object, String methodName) {
        try {
            final Method method = object.getClass().getDeclaredMethod(methodName);
            method.setAccessible(true);
            method.invoke(object);
            return true;
        } catch(Exception ignored) {}
        return false;
    }
}
