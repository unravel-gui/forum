package com.kemorebi.forum.utils;

import com.github.dozermapper.core.Mapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DozerUtils 工具类
 */
@AllArgsConstructor
@Getter
public class DozerUtils {
    private Mapper mapper;

    public <T> T map(Object source, Class<T> destinationClass) {
        if (source == null) {
            return null;
        }
        return mapper.map(source, destinationClass);
    }

    /**
     *
     * @param source
     * @param destinationClass
     * @return
     * @param <T>
     */
    public <T> T map2(Object source, Class<T> destinationClass) {
        if (source == null) {
            try {
                return destinationClass.newInstance();
            } catch (Exception e) {

            }
        }
        return mapper.map(source, destinationClass);
    }

    public void map(Object source, Object destination) {
        if (source == null) {
            return;
        }
        mapper.map(source, destination);
    }

    public <T> T map(Object source, Class<T> destinationClass, String mapId) {
        if (source == null) {
            return null;
        }
        return mapper.map(source, destinationClass, mapId);
    }

    public void map(Object source, Object destination, String mapId) {
        if (source == null) {
            return;
        }
        mapper.map(source, destination, mapId);
    }

    /**
     * 将集合转成集合
     * List<A> -->  List<B>
     *
     * @param sourceList       源集合
     * @param destinationClass 待转类型
     * @param <T>
     * @return
     */
    public <T, E> List<T> mapList(Collection<E> sourceList, Class<T> destinationClass) {
        return mapPage(sourceList, destinationClass);
    }


    public <T, E> List<T> mapPage(Collection<E> sourceList, Class<T> destinationClass) {
        if (sourceList == null || sourceList.isEmpty() || destinationClass == null) {
            return Collections.emptyList();
        }
        List<T> destinationList = sourceList.stream()
                .filter(item -> item != null)
                .map((sourceObject) -> mapper.map(sourceObject, destinationClass))
                .collect(Collectors.toList());

        return destinationList;
    }

    public <T, E> Set<T> mapSet(Collection<E> sourceList, Class<T> destinationClass) {
        if (sourceList == null || sourceList.isEmpty() || destinationClass == null) {
            return Collections.emptySet();
        }
        return sourceList.stream().map((sourceObject) -> mapper.map(sourceObject, destinationClass)).collect(Collectors.toSet());
    }
}
