package org.bsuir.ivan.transformers;

import org.bsuir.ivan.dao.Booking;

public interface Transformer {

     Object transform(Object object, Class<?> targetType);
     boolean canTransform(Object obj, Class<?> targetType);
     void registerTransformation(Class<?> target, Class<?> source);
}
