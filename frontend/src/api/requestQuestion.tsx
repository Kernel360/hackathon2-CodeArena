import { API_URL } from "./API_URL";

export const requestQuestion = async (questionId: number) =>{
    const requestUrl = `${API_URL}/questions/${questionId}`;

  const QuestionResponse = await fetch(requestUrl, {
    method: 'GET',
    credentials : 'include',
  });
return  QuestionResponse;
}

export const createRequestQuestion = async (newQuestion: any) =>{
  const response = await fetch(`${API_URL}/questions/add`, {
    method: 'POST',
    credentials : 'include',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(newQuestion),
  });
  return response;
}




export const updateRequestQuestion = async (newQuestion: any, questionId:number) =>{
  const response = await fetch(`${API_URL}/questions/${questionId}`, {
    method: 'PUT',
    credentials : 'include',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(newQuestion),
  });
  return response;
}


