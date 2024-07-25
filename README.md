# hackathon2-CodeArena
커널360 BE 2기 해커톤 4팀


## 주제
- 개발자 커뮤니티
  
## 기획 의도
- OKKY를 벤치마킹 하여 개발자들이 코딩에 대한 질문이 생겼을 때 질문할 수 있는 커뮤니티를 제작하고자 함.

- 해커톤
    1. 기초 쌓기
      - Spring Boot에 대한 기초를 쌓고 지식을 점검하기 위함.
       
    3. 기능 구현 중점
      - 짧은 시간안에 완벽하게 동작하는 기능들을 최대한 많이 구현 하고자 함.

## 구현 목표
    
### 1. 메인 페이지
    - 질문 리스트 조회
    - 검색 기능
    - 정렬 기능
    - 페이지네이션
### 2. 질문
    - 로그인한 작성자만 질문 작성, 수정, 삭제 가능
    - 삭제 시 답변/댓글 같이 삭제
    - 질문 상세 페이지 : 프리뷰 + 스크랩 기능
        - 답변, 댓글 출력
        - 좋아요/싫어요 버튼
### 3. 답변 
    - 로그인한 유저만 답변 작성, 수정, 삭제 가능
    - 답변 수정, 삭제는 작성자만 가능
    - 삭제 시 댓글 같이 삭제
    - 좋아요/싫어요 버튼
### 4. 댓글
    - 로그인한 유저만 댓글 작성, 수정, 삭제 가능
    - 댓글 수정, 삭제는 작성자만 가능
    - 삭제 시 댓글만 삭제

### 5. 회원 가입 및 로그인
    - 소셜 회원 가입 및 로그인은 제외
    - 직접 회원 가입 및 로그인만 허용
    - 비밀번호는 BCryptPasswordEncoder로 암호화하여 DB에 저장
    - 세션 사용해서 로그인 상태 저장

### 6. 마이페이지
    - 사용자 닉네임, 이메일
    - 사용자가 스크랩한 질문 리스트
    - 사용자가 작성한 질문 리스트
    - 사용자가 작성한 답변 리스트
    - 사용자가 작성한 댓글 리스트


## 기술 스택
### 1. Back-end
    - JAVA 17
    - Spring 3.3.x
### 2. Front-end
    - React
    - TypeScript
### 3. Database
    - H2 database

## API 명세서
\[API 명세서 노션 링크]: https://www.notion.so/03577b11e4dd49269522958844dc8bc0?v=c33977dc2d404739adc83391f744b82f&pvs=4

![api 명세서 1](https://github.com/user-attachments/assets/25f5dcf6-cdb4-4f8c-a8a6-be1812d42bef)

![api 명세서 2](https://github.com/user-attachments/assets/a4b71782-0f7c-41bc-916c-043d4166808e)

![api 명세서 3](https://github.com/user-attachments/assets/2d135e9d-028e-4bd7-97a5-70d69b2cca78)

![api 명세서 4](https://github.com/user-attachments/assets/ad9762bd-d0a0-44a6-8240-5cf15634f9ee)



## ERD

![CodeArena](https://github.com/user-attachments/assets/3f319c91-834e-4248-af42-70f745d2581a)


## 와이어 프레임
1. 질문지 리스트

![와이어1](https://github.com/user-attachments/assets/c6d2a86b-8e28-498a-8a5d-fb45bce1c510)


2. 상세 질문 내용

![와이어2](https://github.com/user-attachments/assets/8d4b156f-743f-4ef3-909d-313880b5bcc3)



