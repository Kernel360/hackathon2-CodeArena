import { VoteLike } from "./voteLike"
import { VoteLikeCount } from "./voteLikeCount"
import { VoteDisLike } from "./VoteDisLike"
import { VoteDisLikeCount } from "./VoteDisLikeCount"
import { useEffect } from "react"
import { useAuth } from "@/context/AuthContext"
import { requsetMyVotedState, updateVoteToQuestion } from "@/api/requestVote"
import { VOTE_STATE } from "@/types/voteStateType"
import { Question } from "@/types/Question"

export const VoteStatus = ({question}:{question:Question|undefined}) =>{
    const user = useAuth().user;
    useEffect(()=>{
        (user !== null && question) && requsetMyVotedState(question.questionId)
    },[])
    const likeHandler = () =>{
        const newVoteType = {voteStatusType : VOTE_STATE.VOTE_STATUS_LIKE};
        (user !== null && question) && updateVoteToQuestion(newVoteType,question.questionId);
    }

    const disLikeHandler = () =>{
        const newVoteType = {voteStatusType : VOTE_STATE.VOTE_STATUS_HATE};
        (user !== null && question) &&  updateVoteToQuestion(newVoteType,question.questionId);
    }
    
    return (
        <div className="flex h-[20px] items-center">
            <div className="mr-2 ml-2">
                <VoteLike likeHandler={likeHandler}/>
            </div>
            <div className="mr-1 ml-1">
                <VoteLikeCount question={question}/>
            </div>
            <div className="mr-1 ml-1">
                <VoteDisLikeCount question={question}/>
            </div>
            <div className="mr-2 ml-2">
                <VoteDisLike disLikeHandler = {disLikeHandler}/>
            </div>
        </div>
    )
}