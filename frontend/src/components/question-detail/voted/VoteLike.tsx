import { AiOutlineLike } from "react-icons/ai";

export const VoteLike = ({likeHandler} :{likeHandler:any}) =>{
    return (
        <>
            <AiOutlineLike className="cursor-pointer hover:text-blue-600" onClick={() => likeHandler()}/>
        </>
    )
}