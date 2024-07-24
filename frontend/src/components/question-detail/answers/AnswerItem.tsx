import { AnswerType } from "@/types/AnswerType";

export const AnswerItem = ({answerItem}:{answerItem:AnswerType}) =>{
    return (
        <>
            <div className="p-4 mb-4 bg-white shadow-md rounded-md">
                <div className="text-gray-500 text-sm">ID: {answerItem.answerId}</div>
                <div className="mt-2 text-lg font-semibold">{answerItem.content}</div>
                <div className="mt-4 text-gray-400 text-xs">수정된 날짜: {new Date(answerItem.modifiedAt).toLocaleString()}</div>
                <div className="mt-2 text-right text-blue-500 font-medium">{answerItem.nickname}</div>
            </div>
        </>
    )    
}