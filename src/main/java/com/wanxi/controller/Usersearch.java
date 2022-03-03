package com.wanxi.controller;

import com.wanxi.commonresult.CommonResult;
import com.wanxi.entity.UserEs;
import com.wanxi.mapper.UserDao;
import com.wanxi.mapper.UserRepository;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@RestController
@RequestMapping("es")
public class Usersearch {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    UserDao userDao;
    @Autowired
    UserRepository userRepository;
    @GetMapping("index")
    public void CreateUserIndex() {
        List<UserEs> userList = userDao.selectAll();
        userRepository.saveAll(userList);

    }
    @GetMapping("findByName")
    public CommonResult findByName(String username) {

        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("username", username))
                .build();
        SearchHits<UserEs> searchHits = elasticsearchRestTemplate.search(query, UserEs.class);
        System.out.println(searchHits);

        List<SearchHit<UserEs>> searchHitList = searchHits.getSearchHits();
        List<UserEs> userList = searchHitList.stream().map(SearchHit::getContent).collect(Collectors.toList());
        System.out.println("====" + userList.size());
        for (UserEs userEs : userList) {
            System.out.println(userEs);
        }
        return CommonResult.success(userList);
    }
    @GetMapping("findByNameHighlight")
    public  CommonResult findByNameHighlight(String username){
        HighlightBuilder.Field field = new HighlightBuilder.Field("username")
                .preTags("<font color='red'>")
                .postTags("</font>");
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("username", username))
                .withHighlightFields(field)
                .build();
        SearchHits<UserEs> searchHits = elasticsearchRestTemplate.search(query, UserEs.class);
        System.out.println(searchHits);


//        newCapicity = oldCapicity + oldCapicity<<5;
        List<SearchHit<UserEs>> searchHitList = searchHits.getSearchHits();
        List<UserEs> userList = new ArrayList(searchHitList.size());
        for (SearchHit<UserEs> searchHit : searchHitList) {
            UserEs userEs = searchHit.getContent();
            String highlightName = searchHit.getHighlightField("username").get(0);
            userEs.setUsername(highlightName);
            userList.add(userEs);
        }

        return CommonResult.success(userList);
    }
}