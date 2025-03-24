package com.example.study.service;

import java.io.IOException;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.auth.SimplePrivateKeySupplier;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.model.CreatePreauthenticatedRequestDetails;
import com.oracle.bmc.objectstorage.requests.CreatePreauthenticatedRequestRequest;
import com.oracle.bmc.objectstorage.requests.GetNamespaceRequest;
import com.oracle.bmc.objectstorage.responses.CreatePreauthenticatedRequestResponse;

@Service
public class OracleStorageService {
	private final ObjectStorageClient objectStorageClient;

    private String bucketName = "StudyBucket";

    @SuppressWarnings("deprecation")
	public OracleStorageService() throws IOException {
    	// 인증 파일 경로 설정
        String configFilePath = System.getProperty("user.home") + "/.oci/config";
        
        String privateKeyPath = System.getProperty("user.home") + "/.oci/private_key.pem";
        new SimplePrivateKeySupplier(privateKeyPath);

        // 인증 정보 제공자 생성 (config 파일 경로 사용)
        ConfigFileAuthenticationDetailsProvider provider = 
            new ConfigFileAuthenticationDetailsProvider(configFilePath, "DEFAULT");

        // ObjectStorage 클라이언트 생성
        this.objectStorageClient = new ObjectStorageClient(provider);
    }

    // presigned URL 생성
    public String createPresignedUrl(String objectName) {
    	try {
            // namespaceName을 가져옴
            String namespaceName = objectStorageClient.getNamespace(GetNamespaceRequest.builder().build()).getValue();

            // presigned URL 생성을 위한 요청 세부사항 설정
            CreatePreauthenticatedRequestDetails preauthenticatedRequestDetails =
                    CreatePreauthenticatedRequestDetails.builder()
                            .name("example-par-name") // 이름 추가
                            .accessType(CreatePreauthenticatedRequestDetails.AccessType.ObjectWrite) // ObjectWrite 작업
                            .timeExpires(Date.from(OffsetDateTime.now().plus(Duration.ofMinutes(3)).toInstant())) // 만료 시간 설정
                            .objectName(objectName)
                            .build();

            // presigned URL 생성 요청
            CreatePreauthenticatedRequestRequest request = CreatePreauthenticatedRequestRequest.builder()
                    .namespaceName(namespaceName) // namespaceName 추가
                    .bucketName(bucketName)
                    .createPreauthenticatedRequestDetails(preauthenticatedRequestDetails)
                    .build();
            
            // 요청 전송하여 presigned URL 생성
            CreatePreauthenticatedRequestResponse response = objectStorageClient.createPreauthenticatedRequest(request);
            String endpoint = "https://objectstorage.ap-chuncheon-1.oraclecloud.com";
            String accessUri = response.getPreauthenticatedRequest().getAccessUri().toString();
            String fullUrl = endpoint + accessUri;
            
            // 생성된 presigned URL 반환
            return fullUrl;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
