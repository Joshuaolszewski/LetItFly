import React from "react";
import styled from "styled-components";
import { Link, useLocation } from "react-router-dom";
import logo from "../mock_logo.jpg";
import "../css/Home.css";

function Header({ userEmail }) {
  const location = useLocation();

  return (
    <header>
      <Link to={"/"}>
        <img
          className="header-left"
          src={logo}
          alt="Let It Fly Logo"
          height={100}
        />
      </Link>
      <div className="header-right">
        <div className="header-right-top">
          <p className="header-username">
            {userEmail ? `Hi ${userEmail}!` : "Hi username!"}
          </p>
        </div>

        <ul className="navbar-home">
          <Link to={"/register"}>
            <li>Settings</li>
          </Link>
          <Link to={"/"}>
            <li>Logout</li>
          </Link>
        </ul>
      </div>
    </header>
  );
}

const Nav = styled.div`
  background-color: violet;
  display: flex;
  height: 100%;
  border-bottom: 5px solid blue;
  /* justify-content: center; */
`;

export default Header;
