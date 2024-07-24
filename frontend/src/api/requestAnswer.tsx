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
  