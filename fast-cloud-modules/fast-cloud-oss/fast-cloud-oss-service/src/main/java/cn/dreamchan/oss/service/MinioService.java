package cn.dreamchan.oss.service;

import cn.dreamchan.oss.config.MinioConfigurationProperties;
import cn.dreamchan.oss.exception.MinioException;
import cn.dreamchan.oss.exception.MinioFetchException;
import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.PutObjectOptions;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * minio 服务
 * @author DreamChan
 */
@Service
public class MinioService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioConfigurationProperties configurationProperties;

    public List<Item> list() {
        Iterable<Result<Item>> myObjects = minioClient.listObjects(configurationProperties.getBucket(), "", false);
        return getItems(myObjects);
    }

    public List<Item> fullList() throws MinioException {
        try {
            Iterable<Result<Item>> myObjects = minioClient.listObjects(configurationProperties.getBucket());
            return getItems(myObjects);
        } catch (XmlParserException e) {
            throw new MinioException("Error while fetching files in Minio", e);
        }
    }

    public List<Item> list(Path path) {

        Iterable<Result<Item>> myObjects = minioClient.listObjects(configurationProperties.getBucket(), path.toString(), false);
        return getItems(myObjects);
    }

    public List<Item> getFullList(Path path) throws MinioException {
        try {
            Iterable<Result<Item>> myObjects = minioClient.listObjects(configurationProperties.getBucket(), path.toString());
            return getItems(myObjects);
        } catch (XmlParserException e) {
            throw new MinioException("Error while fetching files in Minio", e);
        }
    }

    private List<Item> getItems(Iterable<Result<Item>> myObjects) {
        return StreamSupport
                .stream(myObjects.spliterator(), true)
                .map(itemResult -> {
                    try {
                        return itemResult.get();
                    } catch (InvalidBucketNameException |
                            NoSuchAlgorithmException |
                            InsufficientDataException |
                            IOException |
                            InvalidKeyException |
                            XmlParserException |
                            InvalidResponseException|
                            ErrorResponseException |
                            InternalException e) {
                        throw new MinioFetchException("Error while parsing list of objects", e);
                    }
                })
                .collect(Collectors.toList());
    }

    public InputStream get(Path path) throws MinioException {
        try {
            return minioClient.getObject(configurationProperties.getBucket(), path.toString());
        } catch (XmlParserException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException  | ErrorResponseException | InternalException  | InvalidResponseException e) {
            throw new MinioException("Error while fetching files in Minio", e);
        }
    }

    public ObjectStat getMetadata(Path path) throws MinioException {
        try {
            return minioClient.statObject(configurationProperties.getBucket(), path.toString());
        } catch (XmlParserException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException |  ErrorResponseException | InternalException  | InvalidResponseException e) {
            throw new MinioException("Error while fetching files in Minio", e);
        }
    }

    public Map<Path, ObjectStat> getMetadata(Iterable<Path> paths) {
        return StreamSupport.stream(paths.spliterator(), false)
                .map(path -> {
                    try {
                        return new HashMap.SimpleEntry<>(path, minioClient.statObject(configurationProperties.getBucket(), path.toString()));
                    } catch (InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException |  XmlParserException | ErrorResponseException | InternalException | InvalidResponseException  e) {
                        throw new MinioFetchException("Error while parsing list of objects", e);
                    }
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void getAndSave(Path source, String fileName) throws MinioException {
        try {
            minioClient.getObject(configurationProperties.getBucket(), source.toString(), fileName);
        } catch (XmlParserException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException |  ErrorResponseException | InternalException  | InvalidResponseException e) {
            throw new MinioException("Error while fetching files in Minio", e);
        }
    }

    public void upload(Path source, InputStream file, Map<String, String> headers) throws
            MinioException {
        try {
            PutObjectOptions options = new PutObjectOptions(file.available(), -1);
            options.setHeaders(headers);
            minioClient.putObject(configurationProperties.getBucket(), source.toString(), file, options);
        } catch (XmlParserException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException |  ErrorResponseException | InternalException  | InvalidResponseException e) {
            throw new MinioException("Error while fetching files in Minio", e);
        }
    }

    public void upload(Path source, InputStream file) throws
            MinioException {
        try {
            minioClient.putObject(configurationProperties.getBucket(), source.toString(), file, new PutObjectOptions(file.available(),-1));
        } catch (XmlParserException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException |  ErrorResponseException | InternalException  | InvalidResponseException e) {
            throw new MinioException("Error while fetching files in Minio", e);
        }
    }

    public void upload(Path source, InputStream file, String contentType, Map<String, String> headers) throws
            MinioException {
        try {
            PutObjectOptions options = new PutObjectOptions(file.available(), -1);
            options.setContentType(contentType);
            options.setHeaders(headers);
            minioClient.putObject(configurationProperties.getBucket(), source.toString(), file, options);
        } catch (XmlParserException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException |  ErrorResponseException | InternalException  | InvalidResponseException e) {
            throw new MinioException("Error while fetching files in Minio", e);
        }
    }

    public void upload(Path source, InputStream file, String contentType) throws
            MinioException {
        try {
            PutObjectOptions options = new PutObjectOptions(file.available(), -1);
            options.setContentType(contentType);
            minioClient.putObject(configurationProperties.getBucket(), source.toString(), file, options);
        } catch (XmlParserException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException |  ErrorResponseException | InternalException  | InvalidResponseException e) {
            throw new MinioException("Error while fetching files in Minio", e);
        }
    }

    public void upload(Path source, File file,int partSize) throws
            MinioException {
        try {
            minioClient.putObject(configurationProperties.getBucket(), source.toString(), file.getAbsolutePath(),new PutObjectOptions(Files.size(file.toPath()),partSize));
        } catch (XmlParserException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException |  ErrorResponseException | InternalException  | InvalidResponseException e) {
            throw new MinioException("Error while fetching files in Minio", e);
        }
    }

    public void remove(Path source) throws MinioException {
        try {
            minioClient.removeObject(configurationProperties.getBucket(), source.toString());
        } catch (XmlParserException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException |  ErrorResponseException | InternalException  | InvalidResponseException e) {
            throw new MinioException("Error while fetching files in Minio", e);
        }
    }

}