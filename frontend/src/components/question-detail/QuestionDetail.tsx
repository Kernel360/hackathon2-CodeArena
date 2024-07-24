import { requestQuestion } from "@/api/requestQuestion";
import { useAuth } from "@/context/AuthContext";
import { Question } from "@/types/Question";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { Button } from "../ui/button";
import { Answer } from "./answers/Answer";

  
export const QuestionDetail = () => {
    const {user} = useAuth();
    const location = useLocation();
    const questionId = location.state?.question?.questionId;
    const [question, setQuestion] = useState<Question>();
    const navigate = useNavigate();
    useEffect(()=>{
        requestQuestion(questionId).then((res) => res.json()).then((data)=>setQuestion(data))
    },[])

    return (
        <div className="container mx-auto mt-10 p-5">
        <div className="bg-white shadow-md rounded-lg p-6">
            <h1 className="text-3xl font-bold mb-4">{question?.title}</h1>
            <div className="flex justify-between items-center mb-4">
                <div className="text-sm text-gray-600">
                    <span className="font-semibold">{question?.userName}</span> - {new Date(question?.createdAt||"").toLocaleDateString()}
                </div>
                <div className="text-sm text-gray-600">
                    조회수: {question?.views}
                </div>
            </div>
            <div className="text-gray-800 mb-6">
                {question?.content}
            </div>
            <div className="flex justify-end">
                {!user ? "d" :
                    <Button onClick={(()=>navigate(`/question/edit`,{state:question}))}>수정하기</Button>
                }
                <button
                    onClick={() => window.history.back()}
                    className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                >
                    뒤로가기
                </button>
            </div>
        </div>
        <hr className="mt-10 mb-10"/>
        <Answer/>
    </div>
    )
} 
