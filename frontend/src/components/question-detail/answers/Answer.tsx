import { requestAnswer } from "@/api/requestAnswer";
import { AnswerType } from "@/types/AnswerType";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { AnswerItem } from "./AnswerItem";

export const Answer = () =>{
    const location = useLocation();
    const questionId = location.state?.question?.questionId;
    const [answer, setAnswer] = useState<AnswerType[]>([]);
    console.log(answer)
    useEffect(()=>{
        requestAnswer(questionId).then((res)=> res.json()).then((d)=> setAnswer(d))
    },[])
    return (
        <>
        {answer.map((data, index) =>
            <div key={index}>
                <AnswerItem answerItem={data} key={index}/>
            </div>
        )}
        </>
    )
}