interface SearchBarProps {
    searchQuery: string;
    onSearchChange: (value: string) => void;
    filter: string;
    onFilterChange: (value: string) => void;
    sort: string;
    onSortChange: (value: string) => void;
  }
  
  const SearchBar: React.FC<SearchBarProps> = ({ searchQuery, onSearchChange, filter, onFilterChange, sort, onSortChange }) => {
    return (
      <div>
        <select value={filter} onChange={(e) => onFilterChange(e.target.value)}>
          <option value="">검색 조건</option>
          <option value="title">제목</option>
          <option value="content">내용</option>
          <option value="nickname">닉네임</option>
          <option value="email">이메일</option>
        </select>
        <input
          type="text"
          placeholder="Search Question"
          value={searchQuery}
          onChange={(e) => onSearchChange(e.target.value)}
        />
        <select value={sort} onChange={(e) => onSortChange(e.target.value)}>
          <option value="">정렬 조건</option>
          <option value="views">조회수 순</option>
          <option value="likes">좋아요 순</option>
          <option value="hates">싫어요 순</option>
        </select>
      </div>
    );
  };
  
  export default SearchBar;