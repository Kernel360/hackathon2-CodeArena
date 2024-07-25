import { Button } from "@/components/ui/button";
import { useAuth } from "@/context/AuthContext";
import { AnswerType } from "@/types/AnswerType";
import { Comment } from "./comment/Comment";
import { useState } from "react";
import {  useParams } from "react-router-dom";
import { updateRequestAnswer } from "@/api/requestAnswer";

export const AnswerItem = ({answerItem, deleteRequestAnswer}:{answerItem:AnswerType,deleteRequestAnswer:any}) =>{
    const {user} = useAuth();
    const [answerContent, setAnswerContent] = useState(answerItem.content);
    const param = useParams();
    const modifiedHandler = () =>{
        const newContent = {
            userName : user?.nickname,
            content : answerContent
        }
        const questionId = param?.questionId ||"" ;
        updateRequestAnswer(newContent,questionId, answerItem.answerId).then(()=>window.location.reload())
    }
    
    return (
        <>
            <div className="p-4 mb-4 bg-white shadow-md rounded-md">
                <div className="text-gray-500 text-sm">ID: {answerItem.answerId}</div>
                <input className="mt-2 text-lg font-semibold" onChange={(e:any)=>setAnswerContent(e.target.value)} defaultValue={answerItem.content} disabled = {user?.nickname !== answerItem.nickname}/>
                <div className="mt-4 text-gray-400 text-xs">수정된 날짜: {new Date(answerItem.modifiedAt).toLocaleString()}</div>
                <div className="mt-2 text-right text-blue-500 font-medium">{answerItem.nickname}</div>
                {user?.nickname == answerItem.nickname ? 
                <div className="flex justify-end">
                    <Button className="w-[80px] h-[30px] mr-5" onClick={modifiedHandler} id={answerItem.answerId +""}>수정하기</Button>
                    <Button className="w-[80px] h-[30px] " onClick={deleteRequestAnswer} id={answerItem.answerId +""}>삭제하기</Button>
                </div> :
                "" }
            </div>
            
            <div className="w-full flex flex-col justify-end items-end">
                <div className="w-10/12">
                    {answerItem.answerId && 
                        <Comment answerId ={answerItem.answerId}/>
                    }
                </div>
            </div>
        </>
    )    
}