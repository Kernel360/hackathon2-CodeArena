import { QuestionPreviewData } from "@/components/question-list/QuestionPreview";
import { API_URL } from "./API_URL";
import { QUESTIONS_DUMMY_DATA } from "./DUMMY/questions";

export interface GET_paginationRequestFormat {
  pagination: {
    currentPage: number              // 현재 페이지
    pageSize: number,         // 한 페이지에 몇개가 들어가는지 
  },
  sort: {            // 기본 전략은 최신순 + 내림차순 입니다.
    target: "views" | "likes" | "hates"
  }
}

export interface GET_paginationResponseFormat {
  pagination: {
    currentPage: number,            // 몇번째 페이지인지
    totalPage: number,       // 전체 페이지 수
  },
  // 질문 내용 미리보기 UI를 위한 데이터입니다.
  questionPreviews: QuestionPreviewData[];
}



export async function requestQuestionListPaginated(
  page: number,
  pageSize: number,
  sort?: "views" | "likes" | "hates",
  filter?: string,
  searchQuery?: string,
) {

  let requestUrl = "";
  switch(filter) {
    case ("title"):
      requestUrl = `${API_URL}/questions?title=${searchQuery}`;
      break;
    case ("content"):
      requestUrl = `${API_URL}/questions?content=${searchQuery}`;
      break;
    case ("nickname"):
      requestUrl = `${API_URL}/questions?email=${searchQuery}`;
      break;
    case ("email"):
      requestUrl = `${API_URL}/questions?email=${searchQuery}`;
      break;
    default:
      requestUrl = `${API_URL}/questions`;
      break;
  }

  return QUESTIONS_DUMMY_DATA;

  const requestPayload: GET_paginationRequestFormat = {
    pagination: {
      currentPage: page,
      pageSize: pageSize,
    },
    sort: {
      target: sort ?? "views" // default to view
    },
  };

  /*
  const questionListReponse = await fetch(requestUrl, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(requestPayload),
  });


  // on error
  if (!questionListReponse.ok) {
    const errorData = await questionListReponse.json();
    alert(errorData);
    return ;
  }

  const body: GET_paginationResponseFormat = await questionListReponse.json();
  return body;
  */
}