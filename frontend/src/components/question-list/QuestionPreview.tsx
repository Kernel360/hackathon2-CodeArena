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

export default function QuestionPreview( {question}: props ) {
    return (
        <div>
            <div>
                {question.nickname} - {question.createdAt}
            </div>
            <div>{question.title}</div>
            <div>
                <button>{`Views ${question.views}`}</button>
                <button>{`Likes ${question.likes}`}</button>
                <button>{`Hates ${question.hates}`}</button>
                <button>Hates</button>
            </div>
        </div>
    );
}