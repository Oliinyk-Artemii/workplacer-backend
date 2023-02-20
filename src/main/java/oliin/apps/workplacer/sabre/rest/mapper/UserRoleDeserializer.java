package oliin.apps.workplacer.sabre.rest.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import oliin.apps.workplacer.auth.domain.model.UserRole;

import java.io.IOException;

public class UserRoleDeserializer extends JsonDeserializer<UserRole> {

    @Override
    public UserRole deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, IOException {
        final ObjectCodec objectCodec = jsonParser.getCodec();
        final JsonNode node = objectCodec.readTree(jsonParser);
        final String type = node.asText().replace("-", "_");
        return UserRole.valueOf(type.toUpperCase());
    }
}