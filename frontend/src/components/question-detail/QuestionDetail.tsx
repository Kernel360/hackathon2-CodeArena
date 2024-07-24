import { requestQuestion } from "@/api/requestQuestion";
import { Question } from "@/types/Question";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";

  
export const QuestionDetail:React.FC = () => {
    const location = useLocation();
    const locationState = location.state as { question?: Question };
    const initialQuestion: Question = locationState?.question ?? {
        title: "string",
        nickname: "string",
        createAt: "string",
        likes: 0,
        hates: 0,
        views: 0,
        contents: "string",
        questionId: 0
      };
    
      const [question, setQuestion] = useState<Question>(initialQuestion);
      useEffect(() =>{
        if(locationState?.question){
            requestQuestion(question.questionId);
        }
    },[])
    return (
        <>
            <div>{question?.title}</div>
            <div>
                <div>{question?.nickname}</div>
                <div>{question?.views}</div>
                <div>{question?.createAt}</div>
            </div>        
            <div>
                <div>{question?.likes}</div>
                <div>{question?.hates}</div>
            </div>
            <div>
                <div>{question?.views}</div>
            </div>
        </>
    )
} 

function uesState(arg0: { title: string; nickname: string; createAt: string; likes: number; hates: number; views: number; contents: string; questionId: number; }): [any, any] {
    throw new Error("Function not implemented.");
}
