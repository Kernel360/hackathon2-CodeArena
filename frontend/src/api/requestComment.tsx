import { CommentRequestDto } from "@/types/CommentRequsetDto";
import { API_URL } from "./API_URL";



export const requestComment = async (queryId : CommentRequestDto) =>{
    const questionId = queryId.questionId;
    const answerId = queryId.answerId;
    const response = await fetch(`${API_URL}/questions/${questionId}/answers/${answerId}/comments`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      }
    });
    return response;
  }
  

export const createRequestComment = async (newQuestion: any, questionId:number, answerId:number) =>{
  const response = await fetch(`${API_URL}/questions/${questionId}/answers/${answerId}/comments`, {
    method: 'POST',
    credentials : 'include',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(newQuestion),
  });
  return response;
}


export const updateRequestCommentItem = async (newComment:any,questionId:number, answerId:number, commentId:number) =>{
  const response = await fetch(`${API_URL}/questions/${questionId}/answers/${answerId}/comments/${commentId}`, {
    method: 'PUT',
    credentials : 'include',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(newComment),
  });
  return response;
}
export const deleteRequestCommentItem = async (questionId:number, answerId:number, commentId:number) =>{
  const response = await fetch(`${API_URL}/questions/${questionId}/answers/${answerId}/comments/${commentId}`, {
    method: 'DELETE',
    credentials : 'include',
    headers: {
      'Content-Type': 'application/json',
    },
  });
  return response;
}
