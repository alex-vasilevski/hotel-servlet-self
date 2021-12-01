package org.bsuir.ivan.transformers;

public class BasicTransformer implements Transformer {
    @Override
    public Object transform(Object object, Class<?> targetType) {
        return null;
    }

    @Override
    public boolean canTransform(Object obj, Class<?> targetType) {
        return false;
    }

    @Override
    public void registerTransformation(Class<?> target, Class<?> source) {

    }
}
