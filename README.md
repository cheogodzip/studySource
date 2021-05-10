# studySource
공부 소스를 공유하는 사이트 제작

자바 11
그래들
2.4.4

* 설명.
    * ArticleController : 게시글 작성할 때 사용하는 컨트롤러
    * ArticleService : 컨트롤러의 서비스 로직 
    * ArticleResponse : 게시글을 조회할 때 반환하는 객체
    * ArticleIndexResponse : 페이지에 노출시킬 게시글 정보 객체
    *

* 게시글 삭제 시, 데이터베이스에서는 삭제 속성이 True로 바뀜. -> 실제로 삭제되지 않음
* 게시글 수정 시, updated_at에 시간 반영됨.

![img.png](img.png)