import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { Button } from "../ui/button";
import { createRequestQuestion, updateRequestQuestion } from "@/api/requestQuestion";
import { Question } from "@/types/Question";

export const QuestionDetailEdit = () =>{
    const location = useLocation();
  const question:Question = location.state;
  const [title, setTitle] = useState(question.title||"");   
  const [userName, setUserName] = useState(question.userName||"");
  const [content, setContent] = useState(question.content||"");
  const navigate = useNavigate();

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    const newQuestion = {
      title,
      userName,
      content,
    };
    updateRequestQuestion(newQuestion,question.questionId)
    .then((res)=>res.ok)
    .then(()=>navigate("/"))
    .catch((e)=>console.error(e))
  };

  return (
    <div className='w-[500px] mt-10 p-6 bg-white shadow-md rounded-md'>
      <h2 className='text-2xl font-bold mb-4'>새 질문 작성</h2>
      <form onSubmit={handleSubmit}>
        <div className='mb-4'>
          <label className='block text-gray-700'>제목</label>
          <input
            type='text'
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            className='w-full px-3 py-2 border rounded'
            required
          />
        </div>
        <div className='mb-4'>
          <label className='block text-gray-700'>작성자 이름</label>
          <input
            type='text'
            value={question.userName}
            className='w-full px-3 py-2 border rounded'
            readOnly
            disabled
          />
        </div>
        <div className='mb-4'>
          <label className='block text-gray-700'>내용</label>
          <textarea
            value={content}
            onChange={(e) => setContent(e.target.value)}
            className='w-full px-3 py-2 border rounded'
            rows={5}
            required
          ></textarea>
        </div>
        <div className='flex justify-end'>
          <Button type='submit'>질문 등록</Button>
        </div>
      </form>
    </div>
  );
}