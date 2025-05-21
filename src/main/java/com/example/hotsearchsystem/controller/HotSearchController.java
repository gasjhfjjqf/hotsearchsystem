package com.example.hotsearchsystem.controller;

import com.example.hotsearchsystem.service.HotSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hot")
@Tag(name = "热搜接口", description = "微博热搜接口")
public class HotSearchController {

    @Autowired
    private HotSearchService hotSearchService;

    @PostMapping("/report")
    @Operation(summary = "上报关键词",description = "每次上报会使关键词热度 +1")
    public ResponseEntity<String> report(@RequestParam String keyword) {
        hotSearchService.reportKeyword(keyword);
        return ResponseEntity.ok("Reported: " + keyword);
    }

    @GetMapping("/top")
    @Operation(summary = "获取热搜排行榜", description = "按热度降序返回前limit个关键词")
    public ResponseEntity<List<String>> getTop(@RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(hotSearchService.getTopKeywords(limit));
    }

    @DeleteMapping
    @Operation(summary = "删除关键词", description = "根据关键词名称从热搜榜中删除")
    public ResponseEntity<String> delete(@RequestParam String keyword) {
        boolean success = hotSearchService.deleteKeyword(keyword);
        return success ? ResponseEntity.ok("删除成功") : ResponseEntity.badRequest().body("关键词不存在");
    }
}