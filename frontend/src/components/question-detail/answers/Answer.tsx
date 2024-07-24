import { requestAnswer } from "@/api/requestAnswer";
import { AnswerType } from "@/types/AnswerType";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { AnswerItem } from "./AnswerItem";

export const Answer = ({answer,deleteRequestAnswer}:{answer:AnswerType[],deleteRequestAnswer:any}) =>{
    const location = useLocation();
    const questionId = location.state?.question?.questionId;
    
    return (
        <>
        {answer.map((data, index) =>
            <div key={index}>
                <AnswerItem answerItem={data} key={index} deleteRequestAnswer={deleteRequestAnswer}/>
            </div>
        )}
        </>
    )
}