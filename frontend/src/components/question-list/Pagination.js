import React, { useEffect, useState } from 'react';
import ReactPaginate from 'react-paginate';

// Example items, to simulate fetching from another resources.
function Items({ currentItems }) {
  return (
    <>
      {currentItems &&
        currentItems.map((item) => (
          <div>
            <h3>Item #{item}</h3>
          </div>
        ))}
    </>
  );
}

export function PaginatedItems({ totalPageCount, itemsPerPage, handlePageClick }) {
  // Here we use item offsets; we could also use page offsets
  // following the API or data you're working with.
  return (
    <>
      <ReactPaginate
        breakLabel="..."
        nextLabel="next >"
        onPageChange={handlePageClick}
        pageRangeDisplayed={3}
        pageCount={totalPageCount}
        previousLabel="< previous"
        renderOnZeroPageCount={null}
      />
    </>
  );
}
