import { useEffect, useState } from "react";
import QuestionPreview, { QuestionPreviewData } from "./QuestionPreview";

export interface props {
    questions: QuestionPreviewData[];
}

export default function QuestionPreviewList({questions}: props) {

    useEffect(() => {

    }, []);

    return (
        <div>
            {questions.map((question, index) => (
                <QuestionPreview key={index} question={question} />
            ))}
        </div>
    );
}