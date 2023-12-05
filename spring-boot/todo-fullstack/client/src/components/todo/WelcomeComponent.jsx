import { Link, useParams } from "react-router-dom";
import { useState } from "react";
import { retrieveHelloWorldBean } from "./api/helloWorldApiService";
import { useAuth } from "./security/AuthContext";

export default function WelcomeComponent() {
  const { username } = useParams();
  const [message, setMessage] = useState(null);
  const { token } = useAuth();

  function callHelloWorldRestApi() {
    retrieveHelloWorldBean(token)
      .then((res) => setMessage(res.data))
      .catch((err) => console.error(JSON.stringify(err)));
  }

  return (
    <div className="WelcomeComponent">
      <h1>Welcome {username}</h1>
      <div>
        Your todos - <Link to="/todos">Go Here</Link>
      </div>
      <div>
        <button className="btn btn-success m-5" onClick={callHelloWorldRestApi}>
          Call Hello World
        </button>
      </div>
      <div className="text-info">{message}</div>
    </div>
  );
}
