import { useState } from 'react';
import logo from '../assets/img/logo-black.jpg';
import RegisterForm from '../components/RegisterForm/RegisterForm';
import getRandomDoor from '../utils/getRandomDoor';
import '../components/LoginForm/LoginFormStyles.css';

function LoginPage() {
  const [showLogin, setShowLogin] = useState(true);

  return (
    <>
      <div className="img-container">
        <img className="left" src={getRandomDoor()} alt="" />

        <div className="right">
          <a href="/">
            <img id="logo" src={logo}></img>
          </a>
          <div className="form">
            <RegisterForm />
          </div>
        </div>
      </div>
    </>
  );
}

export default LoginPage;
