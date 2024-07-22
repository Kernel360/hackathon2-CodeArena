import { useState, useEffect } from 'react';
import QuestionPreviewList from './QuestionPreviewList';
import { QuestionPreviewData } from './QuestionPreview';
import SearchBar from './SearchBar';
import { PaginatedItems } from './Pagination';
import { requestQuestionListPaginated } from '@/api/requestQuestionList';

const PAGE_SIZE_DEFAULT = 5;

export const QuestionHome = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const [filter, setFilter] = useState('');
  const [sort, setSort] = useState('');
  const [questions, setQuestions] = useState<QuestionPreviewData[]>([]);
  const [currentPageNumber, setCurrentPageNumber] = useState(0);
  const [totalPageCount, setTotalPageCount] = useState(0);

  useEffect(() => {
    (async () => {
      const data = await requestQuestionListPaginated(
        currentPageNumber,
        PAGE_SIZE_DEFAULT,
        sort as any, // 정렬 조건
        filter, // 필터 조건
        searchQuery, // 검색 단어
      );
      if (data) {
        setQuestions(data.questionPreviews);
        setTotalPageCount(data.pagination.totalPage);
        setCurrentPageNumber(data.pagination.currentPage);
      }
    })();
  // }, [currentPageNumber]);
  }, []);

  const handleSearchClick = () => {
    setCurrentPageNumber(0);
  }

  const handlePageClick = (selectedItem: { selected: number }) => {
    setCurrentPageNumber(selectedItem.selected);
  };

  return (
    <div>
      <SearchBar
        searchQuery={searchQuery}
        onSearchChange={setSearchQuery}
        filter={filter}
        onFilterChange={setFilter}
        sort={sort}
        onSortChange={setSort}
      />
      <button onClick={(e) => handleSearchClick()} >검색하기</button>
      <QuestionPreviewList questions={questions} />
      <PaginatedItems 
        totalPageCount={totalPageCount}
        itemsPerPage={PAGE_SIZE_DEFAULT}
        handlePageClick={handlePageClick}
      />
    </div>
  );
};