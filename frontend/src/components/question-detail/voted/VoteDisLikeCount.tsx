import { Question } from "@/types/Question"
import { useEffect } from "react"

export const VoteDisLikeCount = ({question}:{question:Question|undefined}) =>{
    useEffect(()=>{
        console.log(question)
    },[])
    return (
        <div className="flex items-center justify-center cursor-default ">{question?.hates}</div>
    )
}