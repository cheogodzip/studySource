package com.example.studySource.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewArticleRequest {

    private String title;

    private String content;

    private String writer;

    private String password;
}
