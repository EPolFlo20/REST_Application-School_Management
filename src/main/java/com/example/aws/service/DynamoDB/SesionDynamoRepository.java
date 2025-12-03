package com.example.aws.service.DynamoDB;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.aws.model.Session;
import com.example.aws.repositories.SesionRepository;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;

@Service
public class SesionDynamoRepository implements SesionRepository {

    private final DynamoDbClient dynamo;
    private final String table = "sesiones-alumnos";
    private final String indexName = "alumnoId-index";

    public SesionDynamoRepository(DynamoDbClient dynamo) {
        this.dynamo = dynamo;
    }

    @Override
    public Optional<Session> findByAlumnoId(Long alumnoId) {

        Map<String, AttributeValue> keyCondition = Map.of(
                ":alumnoId", AttributeValue.builder().n(alumnoId.toString()).build());

        QueryRequest request = QueryRequest.builder()
                .tableName(table)
                .indexName(indexName)
                .keyConditionExpression("alumnoId = :alumnoId")
                .expressionAttributeValues(keyCondition)
                .limit(1)
                .build();

        QueryResponse response = dynamo.query(request);

        if (response.count() == 0)
            return Optional.empty();

        return Optional.of(mapToSession(response.items().get(0)));
    }

    @Override
    public Session save(Session session) {
        Map<String, AttributeValue> item = new HashMap<>();

        item.put("id", AttributeValue.builder().s(session.getId()).build());
        item.put("alumnoId", AttributeValue.builder().n(session.getAlumnoId().toString()).build());
        item.put("sessionString", AttributeValue.builder().s(session.getSessionString()).build());
        item.put("fecha", AttributeValue.builder().n(session.getFecha().toString()).build());
        item.put("active", AttributeValue.builder().bool(session.isActive()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(table)
                .item(item)
                .build();

        dynamo.putItem(request);
        return session;
    }

    private Session mapToSession(Map<String, AttributeValue> item) {
        Session session = new Session();
        session.setId(item.get("id").s());
        session.setAlumnoId(Long.parseLong(item.get("alumnoId").n()));
        session.setSessionString(item.get("sessionString").s());
        session.setFecha(Long.valueOf(item.get("fecha").n()));
        session.setActive(item.get("active").bool());
        return session;
    }

}
