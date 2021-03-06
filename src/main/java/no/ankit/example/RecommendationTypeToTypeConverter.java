package no.ankit.example;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

public class RecommendationTypeToTypeConverter implements Converter<RecommendationType, String> {

    @Override
    public String convert(RecommendationType recommendationType) {
        return recommendationType.getType();
    }

    public JavaType getInputType(TypeFactory typeFactory) {
        return this._findConverterType(typeFactory).containedType(0);
    }

    public JavaType getOutputType(TypeFactory typeFactory) {
        return this._findConverterType(typeFactory).containedType(1);
    }

    private JavaType _findConverterType(TypeFactory tf) {
        JavaType thisType = tf.constructType(this.getClass());
        JavaType convType = thisType.findSuperType(Converter.class);
        if (convType != null && convType.containedTypeCount() >= 2) {
            return convType;
        } else {
            throw new IllegalStateException("Cannot find OUT type parameter for Converter of type " + this.getClass().getName());
        }
    }
}
