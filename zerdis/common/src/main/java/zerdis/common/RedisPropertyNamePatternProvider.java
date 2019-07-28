package zerdis.common;

public class RedisPropertyNamePatternProvider {

    private static final String KEY_FORMAT = "%s|%s|%s";

    public static String generateKeyPattern(String application, String profile, String label) {
        return String.format(KEY_FORMAT + "*", application, profile, label);
    }

    public static String formatRedisKeyIntoPropertyName(String application, String profile, String label, String key) {
        String extractedPropertyName = key.replace(String.format(KEY_FORMAT, application, profile, label), "");
        //return extractedPropertyName.replace("_", ".");
        return extractedPropertyName;
    }
  
    public static String formatKey(String application, String profile, String label, String key) {
        return formatRedisKeyIntoPropertyName(application, profile, label, key);
    }
}
