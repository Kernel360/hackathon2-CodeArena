import { useEffect, useState } from "react"
import { CommentItem } from "./CommentItem"
import { useLocation, useNavigate, useParams } from "react-router-dom"
import { createRequestComment, deleteRequestCommentItem, requestComment, updateRequestCommentItem } from "@/api/requestComment";
import { CommentRequestDto } from "@/types/CommentRequsetDto";
import { CommentResopnseType } from "@/types/CommentResponseType";
import { CommentAdd } from "./CommentAdd";
import { useAuth } from "@/context/AuthContext";


export const Comment = ({answerId} : {answerId : number}) =>{
    const param = useParams();
    const [commentList, setCommentList] = useState<CommentResopnseType[]>([]);
    const [questionId, setQuestionId] = useState(Number(param?.questionId) || -1);
    const [content, setContent] = useState("");
    const user = useAuth().user;
    useEffect(()=>{
        setQuestionId(Number(param?.questionId) || -1);
        if(questionId !== -1){
            const queryId : CommentRequestDto = {
                questionId: questionId,
                answerId: answerId
            }
            requestComment(queryId).then((res) => res.json()).then((data) => setCommentList(data))
        }
    },[])
    const contentHandler = (e:any) =>{
        setContent(e.target.value)
    }
    const addCommentHanler = () =>{
        const queryId : CommentRequestDto = {
            questionId: questionId,
            answerId: answerId
        }
        createRequestComment({content}, questionId, answerId)
        .then(()=>requestComment(queryId)
            .then((res) => res.json())
            .then((data) => setCommentList(data)))
        .then(()=>setContent(""));
    }
    const modifiedHandler = (e:any) =>{
        const newComment = {
            content : e.target.value
        }
        const commentId = e.target.id;
        updateRequestCommentItem(newComment,questionId,answerId, commentId).then(()=>window.location.reload())
    }
    const deleteCommentHandler = (e:any) =>{
        const queryId : CommentRequestDto = {
            questionId: questionId,
            answerId: answerId
        }
        const commentId = e.target.id;
        deleteRequestCommentItem(questionId, answerId, commentId)
        .then(()=>requestComment(queryId)
            .then((res) => res.json())
            .then((data) => setCommentList(data)))
        .then(()=>setContent(""));
    }
    return (
        <div>
            {user && 
            <>
                <hr />
                    <CommentAdd content={content} 
                    contentHandler ={contentHandler} 
                    addCommentHanler={addCommentHanler}
                   />
                <hr />
            </>
            }
            {commentList.map((comment,index) =>
                <CommentItem 
                comment = {comment} 
                key={index}
                modifiedHandler={modifiedHandler}
                deleteCommentHandler={deleteCommentHandler}/>
            )}
        </div>    
         
    )
}