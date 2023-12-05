import { useNavigate, useParams } from "react-router-dom";
import { createTodo, retrieveTodo, updateTodo } from "./api/todoApiService";
import { useAuth } from "./security/AuthContext";
import { useEffect, useState } from "react";
import { ErrorMessage, Field, Form, Formik } from "formik";

export default function TodoComponent() {
  const { id } = useParams();
  const { username } = useAuth();

  const [description, setDescription] = useState("");
  const [targetDate, setTargetDate] = useState("");

  const navigate = useNavigate();

  function retrieveTodoById() {
    if (id != -1) {
      retrieveTodo(username, id)
        .then((res) => {
          setDescription(res.data.description);
          setTargetDate(res.data.targetDate);
        })
        .catch((err) => console.error(JSON.stringify(err)));
    }
  }

  useEffect(() => {
    retrieveTodoById();
  }, [id]);

  function onSubmit(values) {
    const todo = {
      id,
      username,
      description: values.description,
      targetDate: values.targetDate,
      done: false,
    };
    if (id != "-1") {
      console.log("update");
      updateTodo(username, id, todo).then((res) => console.log(res.data));
    } else {
      console.log("creae");
      createTodo(username, todo);
    }
    navigate("/todos");
  }

  function validate(values) {
    let errors = {};

    if (values.description.length < 5) {
      errors.description = "Enter at least 5 characters";
    }
    return errors;
  }

  return (
    <div className="container">
      <h1>Enter Todo Details</h1>
      <div>
        <Formik
          initialValues={{ description, targetDate }}
          enableReinitialize={true}
          onSubmit={onSubmit}
          validate={validate}
          validateOnBlur={false}
          validateOnChange={false}
        >
          {(props) => (
            <Form>
              <ErrorMessage
                name="description"
                component="div"
                className="alert alert-warning"
              />
              <fieldset className="form-group">
                <label>Description</label>
                <Field
                  type="text"
                  className="form-control"
                  name="description"
                ></Field>
              </fieldset>
              <fieldset className="form-group">
                <label>Target Date</label>
                <Field
                  type="date"
                  className="form-control"
                  name="targetDate"
                ></Field>
              </fieldset>
              <div>
                <button className="btn btn-success m-5" type="submit">
                  Save
                </button>
              </div>
            </Form>
          )}
        </Formik>
      </div>
    </div>
  );
}
