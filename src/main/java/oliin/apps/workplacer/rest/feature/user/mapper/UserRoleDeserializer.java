package oliin.apps.workplacer.rest.feature.user.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import oliin.apps.workplacer.rest.feature.user.model.AuthorityType;

import java.io.IOException;

public class UserRoleDeserializer extends JsonDeserializer<AuthorityType> {

    @Override
    public AuthorityType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        final ObjectCodec objectCodec = jsonParser.getCodec();
        final JsonNode node = objectCodec.readTree(jsonParser);
        final String type = node.asText().replace("-", "_");
        return AuthorityType.valueOf(type.toUpperCase());
    }
}