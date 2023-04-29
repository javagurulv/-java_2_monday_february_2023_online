package shop.core.support;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import shop.core.responses.CoreError;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Component
public class ErrorCodeUtil {

    private final Properties props;

    ErrorCodeUtil() throws IOException {
        Resource resource = new ClassPathResource("errorCodes.properties");
        this.props = PropertiesLoaderUtils.loadProperties(resource);
    }

    public String getDescription(String errorCode) {
        return this.props.getProperty(errorCode);
    }

    public String getDescription(String errorCode, List<Placeholder> placeholders) {
        String description = getDescription(errorCode);
        for (Placeholder placeholder : placeholders) {
            description = description.replace("{" + placeholder.removeData + "}", placeholder.placeData);
        }
        return description;
    }

    public CoreError errorBuild(String errorCode, List<Placeholder> placeholders) {
        String description = getDescription(errorCode, placeholders);
        return new CoreError(errorCode, description);
    }

    public CoreError errorBuild(String errorCode) {
        return new CoreError(errorCode, getDescription(errorCode));
    }
}
