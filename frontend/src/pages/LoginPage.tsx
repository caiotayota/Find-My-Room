import { useState } from 'react';
import LoginForm from '../components/LoginForm/LoginForm';
import logo from '../assets/img/logo-black.webp';
import getRandomDoor from '../utils/getRandomDoor';
import '../components/LoginForm/LoginFormStyles.css';

function LoginPage() {
  const [showLogin, setShowLogin] = useState(true);

  return (
    <>
      <div className="img-container">
        <img className="left" src={getRandomDoor()} alt="Random Door" />

        <div className="right">
          <a href="/">
            <img id="logo" src={logo} alt="logo"></img>
          </a>
          <div className="form">
            <LoginForm />
          </div>
        </div>
      </div>
    </>
  );
}

export default LoginPage;
