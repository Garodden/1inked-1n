package com.example.oneinkedoneproject.controller;

import com.example.oneinkedoneproject.domain.User;
import com.example.oneinkedoneproject.dto.AddArticleRequestDto;
import com.example.oneinkedoneproject.dto.ArticleResponseDto;
import com.example.oneinkedoneproject.dto.UpdateArticleRequestDto;
import com.example.oneinkedoneproject.repository.article.ArticleRepository;
import com.example.oneinkedoneproject.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/api/article")
    public ResponseEntity<ArticleResponseDto> createArticle(@ModelAttribute AddArticleRequestDto addArticleRequestDto,
                                                            @AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.createArticle(addArticleRequestDto, user));
    }

    @GetMapping("/api/myAllArticle")
    public ResponseEntity<List<ArticleResponseDto>> readAllMyArticles(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(articleService.readMyAllArticles(user));
    }
//
//    @GetMapping("/api/mainFeedArticle")
//    public ResponseEntity<List<ArticleResponseDto>> readMainFeedArticles(@AuthenticationPrincipal User user) {
//        return ResponseEntity.ok(articleService.readMainFeedArticles(user));
//    }

    @PutMapping("/api/article/{articleId}")
    public ResponseEntity<ArticleResponseDto> updateArticle(
            @ModelAttribute UpdateArticleRequestDto updateArticleRequestDto, @PathVariable("articleId") String articleId) {
        return ResponseEntity.ok(articleService.updateArticle(articleId, updateArticleRequestDto));
    }

    @DeleteMapping("/api/article/{articleId}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("articleId") String articleId) {
        articleService.deleteArticle(articleId);
        return ResponseEntity.ok().build();
    }
}