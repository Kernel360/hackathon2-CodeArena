import { Button } from "@/components/ui/button";
import { useAuth } from "@/context/AuthContext";
import { AnswerType } from "@/types/AnswerType";
import { useLocation } from "react-router-dom";

export const AnswerItem = ({answerItem, deleteRequestAnswer}:{answerItem:AnswerType,deleteRequestAnswer:any}) =>{
    const {user} = useAuth();
    const location = useLocation();
    const questionId = location.state?.question?.questionId;
    const modifiedHandler = () =>{
        alert("아직 안했어용");
    }
    
    return (
        <>
            <div className="p-4 mb-4 bg-white shadow-md rounded-md">
                <div className="text-gray-500 text-sm">ID: {answerItem.answerId}</div>
                <div className="mt-2 text-lg font-semibold">{answerItem.content}</div>
                <div className="mt-4 text-gray-400 text-xs">수정된 날짜: {new Date(answerItem.modifiedAt).toLocaleString()}</div>
                <div className="mt-2 text-right text-blue-500 font-medium">{answerItem.nickname}</div>
                {user?.nickname == answerItem.nickname ? 
                <div className="flex justify-end">
                    <Button className="w-[80px] h-[30px] mr-5" onClick={modifiedHandler} id={answerItem.answerId +""}>수정하기</Button>
                    <Button className="w-[80px] h-[30px] " onClick={deleteRequestAnswer} id={answerItem.answerId +""}>삭제하기</Button>
                </div> :
                "" }
            </div>
        </>
    )    
}