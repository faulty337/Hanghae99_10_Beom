# 항해99 Spring 입문 주차 개인과제

전체 요구사항

> 
> 
> 1. 아래의 요구사항을 기반으로 Use Case 그려보기
>     - 손으로 그려도 됩니다.
>     - cf. [https://narup.tistory.com/70](https://narup.tistory.com/70)
> 2. 전체 게시글 목록 조회 API
>     - 제목, 작성자명, 작성 내용, 작성 날짜를 조회하기
>     - 작성 날짜 기 내림차순으로 정렬하기
> 3. 게시글 작성 API
>     - 제목, 작성자명, 비밀번호, 작성 내용을 저장하고
>     
>     - 저장된 게시글을 Client 로 반환하기
> 4. 선택한 게시글 조회 API
>     - 선택한 게시글의 제목, 작성자명, 작성 날짜, 작성 내용을 조회하기 
>     (검색 기능이 아닙니다. 간단한 게시글 조회만 구현해주세요.)
> 5. 선택한 게시글 수정 API
>     - 수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
>     - 제목, 작성자명, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기
> 6. 선택한 게시글 삭제 API
>     - 삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
>     - 선택한 게시글을 삭제하고 Client 로 성공했다는 표시 반환하기

## Use Case

![image](https://user-images.githubusercontent.com/37091532/204464946-6c84cef3-d850-4f34-ae5d-fc32820b872b.png)


## API 명세서
| Method | URL | Request | reponse | function |
| --- | --- | --- | --- | --- |
| GET | /memo | - | {{title, username, content, createDate}, …} | getWritingList() |
| POST | /memo | {title, username, password, content} | {title, username, content, createDate} | createWriting() |
| GET | /memo/{id} | {id} | {titl, username, createDate, content} | getWriting() |
| PUT | /memo/{id} | {id, {title, username, password, content}} | {title, username, content} | updateWriting() |
| DELETE | /memo/{id} | {id, password} | {WritingRequest, headers, httpStatus} | deleteWriting() |
