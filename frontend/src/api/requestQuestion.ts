import { API_URL } from "./API_URL"

export const requestQuestion = (questionid : number) =>{
    const requestUrl = `${API_URL}/question/${questionid}`
    console.log(requestUrl)
}