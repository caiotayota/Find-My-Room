import { AxiosError } from 'axios';
import { useRef, useState, useEffect, useContext } from 'react';
import AuthContext from '../../context/AuthProvider';
import axios from '../../api/axios';
import './LoginFormStyles.css';

const LOGIN_URL = '/auth/login';

const Login = () => {
  // const { setAuth } = useContext(AuthContext);
  const userRef = useRef<HTMLInputElement>(null);
  const errorRef = useRef<HTMLParagraphElement>(null);

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMsg, setErrorMsg] = useState('');
  const [success, setSuccess] = useState(false);

  useEffect(() => {
    userRef.current?.focus();
  }, []);

  useEffect(() => {
    setErrorMsg('');
  }, [email, password]);

  const handleSubmit = async (e: { preventDefault: () => void }) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        LOGIN_URL,
        JSON.stringify({ email, password }),
        {
          headers: { 'Content-Type': 'application/json' },
          //   withCredentials: true,
        }
      );
      console.log(JSON.stringify(response?.data));
      //console.log(JSON.stringify(response));
      const accessToken = response?.data?.accessToken;
      const roles = response?.data?.roles;
      //   setAuth({ email, password, roles, accessToken });
      setEmail('');
      setPassword('');
      setSuccess(true);
    } catch (err: unknown) {
      const error = err as AxiosError;

      if (error?.response?.status === 400) {
        setErrorMsg('Missing Username or Password');
      } else if (error?.response?.status === 401) {
        setErrorMsg('Unauthorized');
      } else {
        setErrorMsg('Login Failed');
      }
      errorRef.current?.focus();
    }
  };

  return (
    <>
      {success ? (
        <section className="form-container">
          <h1>You are logged in!</h1>
          <br />
          <p>
            <a href="/">Go to Home</a>
          </p>
        </section>
      ) : (
        <section className="form-container">
          <p
            ref={errorRef}
            className={errorMsg ? 'error-msg' : 'offscreen'}
            aria-live="assertive"
          >
            {errorMsg}
          </p>
          <h1>Sign In</h1>
          <form onSubmit={handleSubmit}>
            <label htmlFor="username">Username:</label>
            <input
              type="text"
              id="username"
              ref={userRef}
              autoComplete="off"
              onChange={(e) => setEmail(e.target.value)}
              value={email}
              required
            />

            <label htmlFor="password">Password:</label>
            <input
              className="input"
              type="password"
              id="password"
              onChange={(e) => setPassword(e.target.value)}
              value={password}
              required
            />
            <button className="input">Sign In</button>
          </form>
          <p>
            Need an Account? &nbsp;
            <span className="line">
              <a href="/register">Sign-Up today!</a>
            </span>
          </p>
        </section>
      )}
    </>
  );
};

export default Login;
