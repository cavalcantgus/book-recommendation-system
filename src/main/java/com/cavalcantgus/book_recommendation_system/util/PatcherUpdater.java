package com.cavalcantgus.book_recommendation_system.util;

import java.lang.reflect.Field;

import org.springframework.stereotype.Component;

@Component
public class PatcherUpdater {

	public <T> void applyPartialUpdate(T objSource, T objTarget) throws IllegalArgumentException, IllegalAccessException {
		Class<?> objClass = objSource.getClass();
		Field[] objFields = objClass.getDeclaredFields();
		
		for (Field field : objFields) {
			field.setAccessible(true);

			Object targetValue = field.get(objTarget);

			if (targetValue != null) {
				field.set(objSource, targetValue);
			}
		}
	}
}
