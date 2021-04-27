package com.example.studySource.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleResponse {

    // 글 원래 id
    private Long id;

    private String title;

    private String content;

    private String writer;

    private LocalDateTime createdAt;

//  답글 기능을 만들기 위해 따로 만듦
}
