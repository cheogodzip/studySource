package com.example.studySource.model.response;

import com.example.studySource.model.network.Pagination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticlePageResponse {

    private List<ArticleInfoResponse> articleInfoResponses;

    private Pagination pagination;

    public static ArticlePageResponse ArticlePageResponse(List<ArticleInfoResponse> articleInfoResponses, Pagination pagination){
        return ArticlePageResponse.builder()
                .pagination(pagination)
                .articleInfoResponses(articleInfoResponses)
                .build();
    }
}
