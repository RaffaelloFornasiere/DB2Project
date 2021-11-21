package it.polimi.db2.telecoApp.services.utilities;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import it.polimi.db2.telecoApp.services.models.User;

import java.io.IOException;

public class UserSerializer extends StdSerializer<User> {
    private static final long serialVersionUID = 1L;

    public UserSerializer(Class<User> t) {
        super(t);
    }
    public UserSerializer() {
        this(null);
    }

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("username", user.getUsername());
        jsonGenerator.writeStringField("name", user.getName());
        jsonGenerator.writeStringField("surname", user.getSurname());
        jsonGenerator.writeObjectField("birthdate", user.getBirthdate());
        jsonGenerator.writeStringField("address", user.getBillingAddress());
        jsonGenerator.writeObjectField("roles", user.getRoles());
        jsonGenerator.writeObjectField("password", user.getPassword());
        jsonGenerator.writeEndObject();
    }
}