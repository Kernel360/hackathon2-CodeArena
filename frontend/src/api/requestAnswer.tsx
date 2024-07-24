import { API_URL } from "./API_URL";



export const requestAnswer = async (questionId:number) =>{
    const response = await fetch(`${API_URL}/questions/${questionId}/answers`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      }
    });
    return response;
  }
  

export const createRequestAnswer = async (newQuestion: any, questionId:string) =>{
  
  const response = await fetch(`${API_URL}/questions/${questionId}/answers`, {
    method: 'POST',
    credentials : 'include',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(newQuestion),
  });
  return response;
}


export const deleteRequestAnswerItem = async (questionId:number, answerId:number) =>{
  const response = await fetch(`${API_URL}/questions/${questionId}/answers/${answerId}`, {
    method: 'DELETE',
    credentials : 'include',
    headers: {
      'Content-Type': 'application/json',
    },
  });
  return response;
}
