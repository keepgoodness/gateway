package com.repository.fixer.util;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CustomDoubleSerializer extends StdSerializer<Double> {

    public CustomDoubleSerializer() {
        this(null);
    }

    public CustomDoubleSerializer(Class<Double> t) {
        super(t);
    }

    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeNumber(String.format("%s", value));
    }
}