package wkyyzl.cn.service;

import co.elastic.clients.elasticsearch._types.mapping.Property;
import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.elastic.clients.elasticsearch.indices.IndexSettings;
import co.elastic.clients.util.ObjectBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.function.Function;

public interface IndexService {

    /**
     * 新建索引，指定索引名称
     *
     * @param name
     * @throws IOException
     */
    void createIndex(String name) throws IOException;

    /**
     * 创建索引，指定索引名称和setting和mapping
     *
     * @param name
     *            - 索引名称
     * @param settingFn
     *            - 索引参数
     * @param mappingFn
     *            - 索引结构
     * @throws IOException
     */
    void createIndex(String name, Function<IndexSettings.Builder, ObjectBuilder<IndexSettings>> settingFn,
                     Function<TypeMapping.Builder, ObjectBuilder<TypeMapping>> mappingFn) throws IOException;

    /**
     * 删除索引
     *
     * @param name
     * @throws IOException
     */
    void deleteIndex(String name) throws IOException;

    /**
     * 修改索引字段信息 <br/>
     * 字段可以新增，已有的字段只能修改字段的 search_analyzer 属性。
     *
     * @param name
     *            - 索引名称
     * @param propertyMap
     *            - 索引字段，每个字段都有自己的property
     * @throws IOException
     */
    void updateIndexProperty(String name, HashMap<String, Property> propertyMap) throws IOException;

    /**
     * 查询索引列表
     *
     * @return
     * @throws IOException
     */
    GetIndexResponse getIndexList() throws IOException;

    /**
     * 查询索引详情
     *
     * @param name
     * @return
     * @throws IOException
     */
    GetIndexResponse getIndexDetail(String name) throws IOException;

    /**
     * 检查指定名称的索引是否存在
     *
     * @param name
     * @return - true：存在
     * @throws IOException
     */
    boolean indexExists(String name) throws IOException;

}
