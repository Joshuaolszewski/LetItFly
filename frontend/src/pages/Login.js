import React from "react";
import logo from "../mock_logo.jpg";
import { useState } from "react";
import { TextField, Button, Container, Stack } from "@mui/material";
import { useNavigate } from "react-router-dom";
import Paper from "@mui/material/Paper";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  function handleSubmit(event) {
    event.preventDefault();
    // fetch here and need role to figure out the page to navigate to
    console.log("hello");
    const loginInfo = { email, password };
    console.log(loginInfo);

    const proxy = process.env.REACT_APP_BACKEND_BASE_URL;

    fetch(`${proxy}/login`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(loginInfo),
    })
      .then((response) => {
        console.log(response);
        if (response.ok) {
          return response.json();
        } else if (response.status === 401) {
          console.log("Invalid username or password");
        } else if (response.status === 403) {
          console.log("Account is disabled");
        } else if (response.status === 423) {
          console.log("Account is locked");
        }
      })
      .then((tokenObject) => {
        console.log(tokenObject);
        const authenticationObject = tokenObject;
        console.log(authenticationObject.token);
        tokenObject.roleName === "ROLE_DRIVER"
          ? navigate("/driver", { state: { tokenObject } })
          : navigate("/customer", { state: { tokenObject } });
      })
      .catch((error) => {
        console.error("Login failed:", error);
      });
  }

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        height: "100vh",
        background: "goldenrod",
        backdropFilter: "blur(8px)",
      }}
    >
      <Paper
        elevation={3}
        style={{ padding: "20px", margin: "0 auto", maxWidth: "90vw" }}
      >
        <h2 style={{ textAlign: "center" }}>Login</h2>
        <form onSubmit={handleSubmit}>
          <TextField
            type="email"
            variant="outlined"
            color="primary"
            label="Email"
            onChange={(e) => setEmail(e.target.value)}
            value={email}
            fullWidth
            required
            sx={{ marginBottom: 4 }}
          />
          <TextField
            type="password"
            variant="outlined"
            color="primary"
            label="Password"
            onChange={(e) => setPassword(e.target.value)}
            value={password}
            fullWidth
            required
            sx={{ marginBottom: 4 }}
          />

          <Button
            variant="outlined"
            color="primary"
            type="submit"
            style={{ display: "block", margin: "0 auto", width: "200px" }}
          >
            Login
          </Button>
        </form>
      </Paper>
    </div>
  );
}

export default Login;
