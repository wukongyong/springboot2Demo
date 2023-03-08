package wkyyzl.cn.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wkyyzl.cn.service.DocumentService;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.function.BiConsumer;

@Slf4j
@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Autowired
    private ElasticsearchAsyncClient elasticsearchAsyncClient;

    @Override
    public IndexResponse createByFluentDSL(String idxName, String idxId, Object document) throws Exception {
        IndexResponse response = elasticsearchClient.index(idx -> idx
                .index(idxName)
                .id(idxId)
                .document(document));
        return response;
    }

    @Override
    public IndexResponse createByBuilderPattern(String idxName, String idxId, Object document) throws Exception {
        IndexRequest.Builder<Object> indexReqBuilder = new IndexRequest.Builder<>();

        indexReqBuilder.index(idxName);
        indexReqBuilder.id(idxId);
        indexReqBuilder.document(document);
        return elasticsearchClient.index(indexReqBuilder.build());
    }

    @Override
    public IndexResponse createByJson(String idxName, String idxId, String jsonContent) throws Exception {
        return elasticsearchClient.index(i -> i
                .index(idxName)
                .id(idxId)
                .withJson(new StringReader(jsonContent))
        );
    }

    @Override
    public void createAsync(String idxName, String idxId, Object document, BiConsumer<IndexResponse, Throwable> action) {
        elasticsearchAsyncClient.index(idx -> idx
                .index(idxName)
                .id(idxId)
                .document(document)
        ).whenComplete(action);
    }

    @Override
    public BulkResponse bulkCreate(String idxName, List<Object> documents) throws Exception {
        BulkRequest.Builder br = new BulkRequest.Builder();

        // TODO 可以将 Object定义为一个文档基类。比如 ESDocument类

        // 将每一个product对象都放入builder中
        //documents.stream()
        //        .forEach(esDocument -> br
        //                .operations(op -> op
        //                        .index(idx -> idx
        //                                .index(idxName)
        //                                .id(esDocument.getId())
        //                                .document(esDocument))));

        return elasticsearchClient.bulk(br.build());
    }

    @Override
    public Object getById(String idxName, String docId) throws IOException {
        GetResponse<Object> response = elasticsearchClient.get(g -> g
                        .index(idxName)
                        .id(docId),
                Object.class);
        return response.found() ? response.source() : null;
    }

    @Override
    public ObjectNode getObjectNodeById(String idxName, String docId) throws IOException {
        GetResponse<ObjectNode> response = elasticsearchClient.get(g -> g
                        .index(idxName)
                        .id(docId),
                ObjectNode.class);

        return response.found() ? response.source() : null;
    }

    @Override
    public Boolean deleteById(String idxName, String docId) throws IOException {
        DeleteResponse delete = elasticsearchClient.delete(d -> d
                .index(idxName)
                .id(docId));
        return delete.forcedRefresh();
    }

    @Override
    public BulkResponse bulkDeleteByIds(String idxName, List<String> docIds) throws Exception {
        BulkRequest.Builder br = new BulkRequest.Builder();

        // 将每一个对象都放入builder中
        docIds.stream().forEach(id -> br
                .operations(op -> op
                        .delete(d -> d
                                .index(idxName)
                                .id(id))));

        return elasticsearchClient.bulk(br.build());
    }
}
