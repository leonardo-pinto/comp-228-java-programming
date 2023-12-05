import { apiClient } from "./httpClient";

export function executeBasicAuthentication(username, password) {
  return apiClient.post("/authenticate", {
    username,
    password,
  });
}
