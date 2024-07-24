import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "../ui/button";
import { createRequestQuestion } from "@/api/requestQuestion";
import { useAuth } from "@/context/AuthContext";

export const QuestionDetailAdd = () =>{
    const [title, setTitle] = useState('');
  const [userName, setUserName] = useState('');
  const [content, setContent] = useState('');
  const navigate = useNavigate();
  const {user} = useAuth();

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    const newQuestion = {
      title,
      userName,
      content,
    };
    createRequestQuestion(newQuestion)
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
            value={user?.nickname}
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