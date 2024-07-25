import { Button } from "@/components/ui/button";
import { useAuth } from "@/context/AuthContext"
import { CommentResopnseType } from "@/types/CommentResponseType"
import { useState } from "react";

export const CommentItem = ({comment,modifiedHandler,deleteCommentHandler} : {comment : CommentResopnseType, modifiedHandler:any,deleteCommentHandler:any}) =>{
    const user = useAuth().user;
    const [commentContent, setCommentContent] = useState(comment.content);
    
    return (
        <div className="mt-2 mb-2">
            <div className="p-4 mb-4 bg-white shadow-md rounded-md">
                <div className="text-gray-500 text-sm">ID: {comment.commentId}</div>
                <input className="mt-2 text-lg font-semibold" onChange={(e:any)=>setCommentContent(e.target.value)} defaultValue={comment.content} disabled = {user?.nickname !== comment.nickname}/>
                <div className="mt-4 text-gray-400 text-xs">수정된 날짜: {new Date(comment.modifiedAt).toLocaleString()}</div>
                <div className="mt-2 text-right text-blue-500 font-medium">{comment.nickname}</div>
                {user?.nickname == comment.nickname ? 
                <div className="flex justify-end">
                    <Button className="w-[80px] h-[30px] mr-5" onClick={modifiedHandler} id={comment.commentId +""} value={commentContent}>수정하기</Button>
                    <Button className="w-[80px] h-[30px] " onClick={deleteCommentHandler} id={comment.commentId +""}>삭제하기</Button>
                </div> :
                "" }
            </div>
            <hr />
        </div>
    )
}