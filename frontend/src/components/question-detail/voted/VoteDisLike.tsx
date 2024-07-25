import { AiOutlineDislike } from "react-icons/ai";

export const VoteDisLike = ({disLikeHandler} : {disLikeHandler:any}) =>{
    return (
        <>
            <AiOutlineDislike className="cursor-pointer hover:text-red-600" onClick={() => disLikeHandler()}/>
        </>
    )
}