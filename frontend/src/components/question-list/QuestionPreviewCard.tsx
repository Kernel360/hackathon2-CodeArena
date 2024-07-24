import {
    Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,
  } from "@/components/ui/card"
import { useNavigate } from "react-router-dom";

export interface props {
    question: QuestionPreviewData;
}

export interface QuestionPreviewData {
    questionId: string;
    title: string;
    nickname: string;
    createdAt: string;
    likes: number;
    hates: number;
    views: number;
}

export default function QuestionPreviewCard( {question}: props ) {
    const navigate = useNavigate();
    return (
        <div className="w-full m-auto flex justify-center cursor-pointer" onClick={()=>navigate(`/question/${question.questionId}`,{state:{question}})} >
            <Card className="w-full max-w-xl">
                <CardHeader>
                    <CardTitle>{question.title}</CardTitle>
                    <CardDescription>
                        {question.nickname} / created at {question.createdAt}
                    </CardDescription>
                </CardHeader>
                <CardContent>
                    <p>질문 내용 프리뷰...</p>
                </CardContent>
            </Card>
        </div>
    );
}