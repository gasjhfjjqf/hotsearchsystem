package com.example.hotsearchsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class HotSearchService {
    private final String REDIS_KEY = "hot_ranking";

    @Autowired
    private StringRedisTemplate redisTemplate;

    //上报关键词
    public void reportKeyword(String keyword) {

        redisTemplate.opsForZSet().incrementScore(REDIS_KEY, keyword, 1);
    }

    //获取热搜榜
    public List<String> getTopKeywords(int topN) {
        Set<String> keywords = redisTemplate.opsForZSet()
                .reverseRange(REDIS_KEY, 0, topN - 1);
        return keywords == null ? Collections.emptyList() : new ArrayList<>(keywords);
    }

    //删除关键词
    public boolean deleteKeyword(String keyword) {
        Long removed = redisTemplate.opsForZSet().remove(REDIS_KEY, keyword);
        return removed != null && removed > 0;
    }
}
