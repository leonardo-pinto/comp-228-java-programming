import { useNavigate } from "react-router-dom";
import ErrorMessageComponent from "./ErrorMessageComponent";
import { useAuth } from "./security/AuthContext";
import { useState } from "react";

export default function LoginComponent() {
  const [username, setUsername] = useState("in28minutes");
  const [password, setPassword] = useState("");
  const [showErrorMessage, setShowErrorMessage] = useState(false);
  const navigate = useNavigate();

  const { login } = useAuth();

  function handleUsername(e) {
    setUsername(e.target.value);
  }

  function handlePassword(e) {
    setPassword(e.target.value);
  }

  function handleSubmit() {
    if (login(username, password)) {
      navigate(`/welcome/${username}`);
    } else {
      setShowErrorMessage(true);
    }
  }

  return (
    <div className="Login">
      {showErrorMessage && <ErrorMessageComponent />}

      <div className="LoginForm">
        <div>
          <label>User Name</label>
          <input
            type="text"
            name="username"
            value={username}
            onChange={handleUsername}
          />
        </div>

        <div>
          <label>Password</label>
          <input
            type="text"
            name="password"
            value={password}
            onChange={handlePassword}
          />
        </div>

        <div>
          <button type="button" name="login" onClick={handleSubmit}>
            Login
          </button>
        </div>
      </div>
    </div>
  );
}
