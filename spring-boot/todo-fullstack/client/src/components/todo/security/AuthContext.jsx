import { createContext, useState, useContext } from "react";
import { executeBasicAuthentication } from "../api/authService";
import { apiClient } from "../api/httpClient";

export const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export function AuthProvider({ children }) {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [username, setUsername] = useState(null);
  const [token, setToken] = useState(null);

  async function login(username, password) {
    try {
      const response = await executeBasicAuthentication(username, password)
        .then((res) => res)
        .catch((err) => console.error(err));
      if (response.status == 200) {
        setIsAuthenticated(true);
        setUsername(username);
        setToken(`Bearer ${response.data.token}`);

        apiClient.interceptors.request.use((config) => {
          config.headers.Authorization = `Bearer ${response.data.token}`;
          return config;
        });
        console.log(token);
        return true;
      } else {
        logout();
      }
    } catch (error) {
      logout();
    }
  }

  function logout() {
    setIsAuthenticated(false);
    setToken(null);
    setUsername(null);
  }

  return (
    <AuthContext.Provider
      value={{ isAuthenticated, login, logout, username, token }}
    >
      {children}
    </AuthContext.Provider>
  );
}
