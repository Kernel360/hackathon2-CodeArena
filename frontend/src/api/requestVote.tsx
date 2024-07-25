import { API_URL } from "./API_URL";

export const requsetMyVotedState = async (questionId:number) =>{
    const response = await fetch(`${API_URL}/questions/${questionId}/vote`, {
        method: 'GET',
        credentials : 'include',
        headers: {
          'Content-Type': 'application/json',
        }
      });
      return response;
}

export const updateVoteToQuestion = async (newVoteType:any ,questionId:number) =>{
    const response = await fetch(`${API_URL}/questions/${questionId}/vote?voteStatus=${newVoteType.voteStatusType}`, {
        method: 'PUT',
        credentials : 'include',
        headers: {
          'Content-Type': 'application/json',
        }
      });
      return response;
}
