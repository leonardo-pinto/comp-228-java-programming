import { Link, useParams } from "react-router-dom";

export default function WelcomeComponent() {
  const { username } = useParams();

  return (
    <div className="WelcomeComponent">
      <h1>Welcome {username}</h1>
      <div>
        Your todos - <Link to="/todos">Go Here</Link>
      </div>
    </div>
  );
}
