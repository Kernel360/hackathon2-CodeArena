import { useState, useEffect } from 'react';
import { requestQuestionListPaginated } from '@/api/requestQuestionList';
import QuestionPreviewCard, { QuestionPreviewData } from './question-list/QuestionPreviewCard';
import SearchBar from './question-list/SearchBar';
import { PaginatedItems } from './question-list/Pagination';
import { Button } from './ui/button';
import { useAuth } from '@/context/AuthContext';
import { useNavigate } from 'react-router-dom';

const PAGE_SIZE_DEFAULT = 5;
const PAGE_NUMBER_DEFAULT = 1;
const SORT_STRATEGY_DEFAULT = "createdAt";
const SEARCH_CATEGORY_DEFAULT = "title";

export const QuestionsPage = () => {
  const {user} = useAuth();

  const [searchQuery, setSearchQuery] = useState<string | null>(null);
  const [searchCategory, setSearchCategory] = useState(SEARCH_CATEGORY_DEFAULT);
  const [sortStrategy, setSortCategory] = useState(SORT_STRATEGY_DEFAULT);

  const [questions, setQuestions] = useState<QuestionPreviewData[]>([]);
  const [currentPageNumber, setCurrentPageNumber] = useState(0);
  const [totalPageCount, setTotalPageCount] = useState(0);
  const navigate = useNavigate();

  useEffect(() => {
    (async () => {
      const data = await requestQuestionListPaginated(
        currentPageNumber,
        PAGE_SIZE_DEFAULT,
        sortStrategy as any,  // 정렬 조건
        searchCategory,     // 검색 조건
        searchQuery,               // 검색 단어
      );
      if (data) {
        setQuestions(data.questionPreviews);
        setTotalPageCount(data.pagination.totalPage);
        setCurrentPageNumber(data.pagination.currentPage);
      }
    })();
  }, [currentPageNumber]);

  return (
    <div>
      <div className=' mt-10 flex justify-center'>
        <SearchBar
          searchQuery={searchQuery}
          onSearchChange={setSearchQuery}
          filter={searchCategory}
          onFilterChange={setSearchCategory}
          sort={sortStrategy}
          onSortChange={setSortCategory}
        />
        <Button type="submit" onClick={() => {
          (async () => {
            const data = await requestQuestionListPaginated(
              currentPageNumber,
              PAGE_SIZE_DEFAULT,
              sortStrategy as any,  // 정렬 조건
              searchCategory,     // 검색 조건
              searchQuery,               // 검색 단어
            );
            console.log(searchQuery, searchCategory);
            console.log(data);
            if (data) {
              setQuestions(data.questionPreviews);
              setTotalPageCount(data.pagination.totalPage);
              setCurrentPageNumber(data.pagination.currentPage);
            }
          })();
        }}>
          검색하기
        </Button>
      </div>
      {user &&
      <div className='flex w-full justify-end mt-5'>
        <Button onClick={()=>navigate("/questions/create")}>작성하기</Button>
      </div>
      }
      <div className=' mt-10 mb-10 w-full flex flex-col items-center space-y-2'>
        {questions.map((question, index) => (
          <QuestionPreviewCard key={index} question={question} />
        ))}
      </div>
      <PaginatedItems
        totalPageCount={totalPageCount}
        currentPageNumber={currentPageNumber}
        setCurrentPageNumber={setCurrentPageNumber}
      />
    </div>
  );
};