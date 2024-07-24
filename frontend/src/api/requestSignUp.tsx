import { z } from "zod";
import { API_URL } from "./API_URL";
import { formSchemaSignUp } from "@/components/signup/SignUpForm";


export interface POST_SignUpRequestFormat {
  nickname: string;
  email: string;
  password: string;
}

export interface POST_SignUpResponseFormat {
    // ...
}

/*******************
 *    회원 가입
 *******************/
export async function requestSignUp(
    formData: z.infer<typeof formSchemaSignUp>
) {

  const requestUrl = `${API_URL}/user/signup`;

  const requestPayload: POST_SignUpRequestFormat = {
    nickname: formData.nickname,
    email: formData.email,
    password: formData.password,
  };
 
  const signUpResponse = await fetch(requestUrl, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(requestPayload),
  });

  return signUpResponse;
}