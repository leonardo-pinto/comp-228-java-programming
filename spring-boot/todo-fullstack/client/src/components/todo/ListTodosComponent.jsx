import { useEffect, useState } from "react";
import { deleteTodoById, retrieveTodosByUser } from "./api/todoApiService";
import { useAuth } from "./security/AuthContext";
import { useNavigate } from "react-router-dom";

export default function ListTodosComponent() {
  const [todos, setTodos] = useState([]);
  const [message, setMessage] = useState(null);
  const { username } = useAuth();

  const navigate = useNavigate();

  useEffect(() => {
    retrieveTodosByUser(username)
      .then((res) => setTodos(res.data))
      .catch((err) => console.error(JSON.stringify(err)));
  }, []);

  function deleteTodo(id) {
    deleteTodoById(username, id)
      .then(() => setMessage(`Todo with id ${id} successfully deleted!`))
      .catch((err) => console.error(JSON.stringify(err)));
  }

  function updateTodo(id) {
    navigate(`/todo/${id}`);
  }

  function addNewTodo() {
    navigate("/todo/-1");
  }

  return (
    <div className="container">
      <h1>Things You Want To Do</h1>
      {message && <div className="alert alert-warning">{message}</div>}
      <div>
        <table className="table">
          <thead>
            <tr>
              <th>Description</th>
              <th>Is Done?</th>
              <th>Target Date</th>
              <th>Delete</th>
              <th>Update</th>
            </tr>
          </thead>
          <tbody>
            {todos.map((todo) => {
              return (
                <tr key={todo.id}>
                  <td>{todo.id}</td>
                  <td>{todo.description}</td>
                  <td>{todo.done.toString()}</td>
                  <td>{todo.targetDate}</td>
                  <td>
                    <button
                      className="btn btn-warning"
                      onClick={() => deleteTodo(todo.id)}
                    >
                      Delete
                    </button>
                  </td>
                  <td>
                    <button
                      className="btn btn-success"
                      onClick={() => updateTodo(todo.id)}
                    >
                      Update
                    </button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
      <button className="btn btn-success" onClick={addNewTodo}>
        Add New Todo
      </button>
    </div>
  );
}
