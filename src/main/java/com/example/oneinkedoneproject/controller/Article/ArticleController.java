package com.example.oneinkedoneproject.controller.Article;

import com.example.oneinkedoneproject.domain.User;
import com.example.oneinkedoneproject.dto.article.AddArticleRequestDto;
import com.example.oneinkedoneproject.dto.article.ArticleResponseDto;
import com.example.oneinkedoneproject.dto.article.UpdateArticleRequestDto;
import com.example.oneinkedoneproject.service.article.ArticleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Article", description = "게시글 API")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/api/article")
    public ResponseEntity<ArticleResponseDto> createArticle(@ModelAttribute AddArticleRequestDto addArticleRequestDto,
                                                            @AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.createArticle(addArticleRequestDto, user));
    }

    @GetMapping("/api/myAllArticles")
    public ResponseEntity<List<ArticleResponseDto>> readAllMyArticles(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(articleService.readMyAllArticles(user));
    }

    @GetMapping("/api/mainFeedArticles")
    public ResponseEntity<List<ArticleResponseDto>> readMainFeedArticles(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(articleService.readMainFeedArticles(user));
    }

    @GetMapping("/api/userAllArticles/{email}")
    public ResponseEntity<List<ArticleResponseDto>> readUserAllArticles(@PathVariable String email) throws Exception {
        String decodedEmail = URLDecoder.decode(email, StandardCharsets.UTF_8.toString());
        return ResponseEntity.ok(articleService.readUserAllArticles(decodedEmail));
    }

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
