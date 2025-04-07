package gov.mt.seplag.gestao_servidores.configuration;

import io.github.cdimascio.dotenv.Dotenv;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.ArrayList;
import java.util.List;

public class EnvLoader {
    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    public static void load() {
        List<String> variables = new ArrayList<>();
        variables.add("SPRING_DATASOURCE_URL");
        variables.add("SPRING_DATASOURCE_USERNAME");
        variables.add("SPRING_DATASOURCE_PASSWORD");
        variables.add("MINIO_URL");
        variables.add("MINIO_ACCESS_KEY");
        variables.add("MINIO_SECRET_KEY");

        for (String variable : variables) {
            System.setProperty(variable, EnvLoader.get(variable));
        }
    }

    public static String get(String key) {
        return dotenv.get(key);
    }
}

