import { Button } from "@/components/ui/button"
import { CommentResopnseType } from "@/types/CommentResponseType";
import { Dispatch, SetStateAction, useState } from "react"

export const CommentAdd = (content:any) =>{

    const changeHandler = (e:any) =>{
        content.contentHandler(e);
    }
    
    return (
        <div className="flex flex-row mt-2 mb-2">
            <input
            type='text'
            value={content.content}
            onChange={changeHandler}
            className='w-full px-3 py-2 border rounded'
            required
          />
            <Button onClick={content.addCommentHanler}>코멘트</Button>
        </div>
    )
}