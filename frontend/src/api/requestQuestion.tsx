import { API_URL } from "./API_URL";

export const requestQuestion = async (questionId: number) =>{
    const requestUrl = `${API_URL}/questions/${questionId}`;

  const QuestionResponse = await fetch(requestUrl, {
    method: 'GET',
  });
return  QuestionResponse;
}