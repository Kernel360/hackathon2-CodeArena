import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { ErrorPage } from "./ErrorPage";
import LoginCard from "./components/login/LoginCard";
import SignUpCard from "./components/signup/SignUpCard";
import { QuestionsPage } from "./components/QuestionsPage";
import LogoLogin from "./components/header";
import {QuestionDetail} from "./components/question-detail/QuestionDetail";
import { AuthProvider } from "./context/AuthContext";
import { QuestionDetailAdd } from "./components/question-detail/QuestionDetilAdd";
import { QuestionDetailEdit } from "./components/question-detail/QuestionDetailEdit";

const router = createBrowserRouter([
  {
    /** 
     * 로그인 화면
     */
    path: "/sign-in",
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
    path: "/sign-up",
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
    // 홈화면 
    path: "/",
    element: (
      <div>
        <QuestionsPage />
      </div>
    ),
    
    errorElement: <ErrorPage />,
  },
  {
    path:"/question/:questionId",
    element:(
    <QuestionDetail/>
  ),
    errorElement: <ErrorPage />,
},
  {
    path:"/questions/create",
    element:(
    <QuestionDetailAdd/>
  ),
    errorElement: <ErrorPage />,
},
{
  path:"/question/edit",
  element:(
  <QuestionDetailEdit/>
),
  errorElement: <ErrorPage />,
}
]);

function App() {
    return (
      <AuthProvider>
      <div className="App">
        <header className="App-header">
          <LogoLogin/>
          <div className="flex w-100 m-auto justify-center">
            <RouterProvider router={router} />
          </div>
        </header>
      </div>
      </AuthProvider>
    );
  }
export default App;