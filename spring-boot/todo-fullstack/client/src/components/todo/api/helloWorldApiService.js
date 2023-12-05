import { apiClient } from "./httpClient";

export function retrieveHelloWorldBean(token) {
  return apiClient.get("hello-world",  {
    headers: {
      Authorization: token,
    },
  });
}