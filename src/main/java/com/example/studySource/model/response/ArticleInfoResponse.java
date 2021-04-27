package com.example.studySource.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleInfoResponse {

    private Long id;

    private String title;

    private String writer;

    private LocalDateTime createdAt;
}