export interface Question {
    createAt: string;   // ISO 8601 형식의 날짜 문자열
    hates: number;     // 싫어요 수
    likes: number;     // 좋아요 수
    nickname: string;  // 닉네임
    questionId: number; // 질문 ID
    title: string;     // 질문 제목
    views: number;     // 조회수
    contents: string;
  }