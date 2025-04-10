package gov.mt.seplag.gestao_servidores.service;

import io.minio.*;
import io.minio.http.Method;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class MinIOService {
    @Value("${minio.url}")
    private String internalUrl;

    @Value("${minio.external-url}")
    private String externalUrl;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    private MinioClient minioClient;

    private static final String BUCKET_NAME = "fotos-pessoas";

    @PostConstruct
    public void initialize() {
        this.minioClient = MinioClient.builder()
                .endpoint(internalUrl)
                .credentials(accessKey, secretKey)
                .build();

        int maxRetries = 10;
        int delaySeconds = 5;
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build());
                if (!exists) {
                    minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
                }
                return;
            } catch (Exception e) {
                if (attempt == maxRetries) {
                    throw new RuntimeException("Falha ao inicializar MinIO após várias tentativas", e);
                }
                try {
                    Thread.sleep(delaySeconds * 1000L);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void uploadFoto(MultipartFile file, String objectName) {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(BUCKET_NAME)
                    .object(objectName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer upload da foto para o MinIO: " + e.getMessage(), e);
        }
    }

    public void deleteFoto(String objectName) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(BUCKET_NAME)
                    .object(objectName)
                    .build());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir a foto do MinIO: " + e.getMessage(), e);
        }
    }

    // Gera URL assinado válido por 5 minutos
    public String gerarLinkTemporario(String objectName) {
        try{
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(BUCKET_NAME)
                            .object(objectName)
                            .expiry((int) Duration.ofMinutes(5).toSeconds())
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar link temporário: " + e.getMessage(), e);
        }
    }
}
