import { API_URL } from "./API_URL";

export async function requestLogout(
) {

  const requestUrl = `${API_URL}/user/logout`;

 
  const logOutResponse = await fetch(requestUrl, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    },
  });

}