package gov.mt.seplag.gestao_servidores.configuration;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinIOConfig {

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(System.getProperty("MINIO_URL"))
                .credentials(
                        System.getProperty("MINIO_ACCESS_KEY"),
                        System.getProperty("MINIO_SECRET_KEY")
                )
                .build();
    }
}
