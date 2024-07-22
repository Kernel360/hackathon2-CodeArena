import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { ErrorPage } from "./ErrorPage";
import LoginCard from "./components/login/LoginCard";
import SignUpCard from "./components/signup/SignUpCard";
import { QuestionHome } from "./components/question-list/Home";

const router = createBrowserRouter([
  {
    /** 
     * 로그인 화면
     */
    path: "/login",
    element:
        <div>
          <LoginCard/>
        </div>,
    errorElement: <ErrorPage/>,
  },
  /** 
   * 회원가입 화면
   */
  {
    path: "/signup",
    element:
        <div>
          <SignUpCard/>
        </div>,
    errorElement: <ErrorPage/>,
  },
  // ----------------------------------------------------
  // 이 아래 경로는 Session ID가 부여된 상태에서만 접근 가능합니다.
  // ----------------------------------------------------
  {
    // 홈화면 (로그인 후)
    path: "/",
    element: (
      <div>
        <QuestionHome />
      </div>
    ),
    errorElement: <ErrorPage />,
  },
]);

function App() {
    return (
      <div className="App">
        <header className="App-header">
          <RouterProvider router={router} />
        </header>
      </div>
    );
  }
export default App;