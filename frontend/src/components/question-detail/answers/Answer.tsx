import { AnswerType } from "@/types/AnswerType";
import { AnswerItem } from "./AnswerItem";

export const Answer = ({answer,deleteRequestAnswer}:{answer:AnswerType[],deleteRequestAnswer:any}) =>{
    
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