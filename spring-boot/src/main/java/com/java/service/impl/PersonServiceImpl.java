package com.java.service.impl;

import com.java.bean.Person;
import com.java.jpa.PersonRepository;
import com.java.service.PersonService;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.min.MinAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHitsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("personService")
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public long count() {
        return personRepository.count();
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void delete(Person person) {
        personRepository.delete(person);
    }

    @Override
    public Iterable<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> getByName(String name) {
        List<Person> personList = new ArrayList<>();
        MatchQueryBuilder queryBuilder2 = new MatchQueryBuilder("name", name);
        Iterable<Person> personIterable = personRepository.search(queryBuilder2);
        personIterable.forEach(personList::add);
        return personList;
    }

    /**
     * 1-精确查询
     */
    public void matchQueryTest() {
        //数字-单个
        //不分词查询 因为不分词，所以汉字只能查询一个字，英语是一个单词.
        QueryBuilder queryBuilder = QueryBuilders.termQuery("fieldName", "fieldValue");
        //分词查询，采用默认的分词器
        queryBuilder = QueryBuilders.matchQuery("fieldName", "fieldValue");

        //不分词查询，因为不分词，所以汉字只能查询一个字，英语是一个单词. //批量
        queryBuilder = QueryBuilders.termsQuery("fieldName", "fieldValue1", "fieldValue2...");
        //分词查询，采用默认的分词器
        queryBuilder = QueryBuilders.multiMatchQuery("fieldlValue", "fieldName1", "fieldName2", "fieldName3");
        //匹配所有文件，相当于就没有设置查询条件
        queryBuilder = QueryBuilders.matchAllQuery();
    }

    /**
     * 2-模糊查询
     * 数字-数字查询都为精确查询
     * 字符串
     * 模糊查询常见的5个方法如下
     */
    public void likeQueryTest() {
        //1.常用的字符串查询 //左右模糊
        QueryBuilders.queryStringQuery("fieldValue").field("fieldName");
        //2.常用的用于推荐相似内容的查询
        //QueryBuilders.moreLikeThisQuery(new String[] {"fieldName"}).addLikeText("pipeidhua");//如果不指定filedName，则默认全部，常用在相似内容的推荐上
        QueryBuilder queryBuilder = QueryBuilders.moreLikeThisQuery(new String[]{"fieldName"}, new String[]{"fieldValue"}, null);
        //3.前缀查询  如果字段没分词，就匹配整个字段前缀
        QueryBuilders.prefixQuery("fieldName", "fieldValue");
        //4.fuzzy query:分词模糊查询，通过增加fuzziness模糊属性来查询,如能够匹配hotelName为tel前或后加一个字母的文档，fuzziness 的含义是检索的term 前后增加或减少n个单词的匹配查询
        QueryBuilders.fuzzyQuery("hotelName", "tel").fuzziness(Fuzziness.ONE);
        //5.wildcard query:通配符查询，支持* 任意字符串；？任意一个字符
        QueryBuilders.wildcardQuery("fieldName", "ctr*");
        QueryBuilders.wildcardQuery("fieldName", "c?r?");
    }

    /**
     * 3-范围查询
     */
    public void rangeQueryTest() {
        //数字-闭区间查询
        QueryBuilder queryBuilder = QueryBuilders.rangeQuery("fieldName").from("fieldValue1").to("fieldValue2");
        //数字-开区间查询
        queryBuilder = QueryBuilders.rangeQuery("fieldName").from("fieldValue1", false).to("fieldValue2", false);
        queryBuilder = QueryBuilders.rangeQuery("fieldName").from("fieldValue1").to("fieldValue2").includeUpper(false).includeLower(false);//默认是true，也就是包含
        //数字-大于
        queryBuilder = QueryBuilders.rangeQuery("fieldName").gt("fieldValue");
        //数字-大于等于
        queryBuilder = QueryBuilders.rangeQuery("fieldName").gte("fieldValue");
        //数字-小于
        queryBuilder = QueryBuilders.rangeQuery("fieldName").lt("fieldValue");
        //数字-小于等于
        queryBuilder = QueryBuilders.rangeQuery("fieldName").lte("fieldValue");
        queryBuilder = QueryBuilders.existsQuery("fieldName");
    }

    /**
     * 4-组合查询/多条件查询/布尔查询
     */
    public void boolQueryTest() {
        QueryBuilders.boolQuery();
        QueryBuilders.boolQuery().must();       //文档必须完全匹配条件，相当于and
        QueryBuilders.boolQuery().mustNot();    //文档必须不匹配条件，相当于not
        QueryBuilders.boolQuery().should();     //至少满足一个条件，这个文档就符合should，相当于or
        QueryBuilder queryBuilder = QueryBuilders.rangeQuery("fieldName").lte("fieldValue");
        QueryBuilder qb1 = QueryBuilders.rangeQuery("fieldName").gt("fieldValue");
        QueryBuilder qb2 = QueryBuilders.boolQuery().must(queryBuilder).must(qb1);
    }

    /**
     * 聚合查询
     *
     * 聚合：就是一些桶和指标的组合
     * Buckets(桶)：满足特定条件的一个文档集合
     * Buckets(桶)：满足某个条件的文档集合
     * Metrics(指标)：为某个桶中的文档计算得到的统计信息
     * Metrics(指标)：为某个桶中的文档进行某种指标的计算
     *
     * https://blog.csdn.net/donghaixiaolongwang/article/details/58597058
     */
    public void aggregationTest() {
        //（1）统计某个字段的数量
        ValueCountAggregationBuilder field = AggregationBuilders.count("count_uid").field("uid");
        //（2）去重统计某个字段的数量（有少量误差）
        CardinalityAggregationBuilder cb = AggregationBuilders.cardinality("distinct_count_uid").field("uid");
        //（3）聚合过滤
        FilterAggregationBuilder fab = AggregationBuilders.filter("uid_filter", QueryBuilders.queryStringQuery("uid:001"));
        //（4）按某个字段分组
        TermsAggregationBuilder tb = AggregationBuilders.terms("group_name").field("name");
        //（5）求和
        SumAggregationBuilder sumBuilder = AggregationBuilders.sum("sum_price").field("price");
        //（6）求平均
        AvgAggregationBuilder ab = AggregationBuilders.avg("avg_price").field("price");
        //（7）求最大值
        MaxAggregationBuilder mb = AggregationBuilders.max("max_price").field("price");
        //（8）求最小值
        MinAggregationBuilder min = AggregationBuilders.min("min_price").field("price");
        //（9）按日期间隔分组
        DateHistogramAggregationBuilder dhb = AggregationBuilders.dateHistogram("dh").field("date");
        //（10）获取聚合里面的结果
        TopHitsAggregationBuilder thb = AggregationBuilders.topHits("top_result");
        //（11）嵌套的聚合
        NestedAggregationBuilder nb = AggregationBuilders.nested("negsted_path", "quests");
        //（12）反转嵌套
        AggregationBuilders.reverseNested("res_negsted").path("kps ");
    }

    @Override
    public Page<Person> pageQuery(Integer pageNo, Integer pageSize, String kw) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchPhraseQuery("name", kw)).withPageable(PageRequest.of(pageNo, pageSize)).build();
        return personRepository.search(searchQuery);
    }
}