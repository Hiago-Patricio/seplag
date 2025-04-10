package gov.mt.seplag.gestao_servidores.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class FotoService {

    private final MinioClient minioClient;

    private static final String BUCKET_NAME = "fotos-pessoas";

    public void uploadFoto(MultipartFile file) {
        try {
            String objectName = file.getOriginalFilename();

            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(BUCKET_NAME)
                    .object(objectName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer upload da foto para o Min.IO: " + e.getMessage(), e);
        }
    }

    public String gerarLinkTemporario(String objectName) {
        try {
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(BUCKET_NAME)
                    .object(objectName)
                    .expiry((int) Duration.ofMinutes(5).toSeconds())
                    .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar link temporário para a fotografia: " + e.getMessage(), e);
        }
    }
}