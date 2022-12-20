import { useRef, useState, useEffect } from 'react';
import {
  faCheck,
  faTimes,
  faInfoCircle,
} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import axios from '../../api/axios';
import { AxiosError } from 'axios';

import './RegisterFormStyles.css';

const USER_REGEX = /.+@.+\..+/;
const PASSWORD_REGEX =
  /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;
const REGISTER_URL = '/auth/register';
const VERIFY_EMAIL_URL = '/user/verify-email';

const RegisterForm = () => {
  const userRef = useRef<HTMLInputElement>(null);
  const errorRef = useRef<HTMLParagraphElement>(null);

  const [email, setEmail] = useState('');
  const [validEmail, setValidEmail] = useState(false);
  const [userFocus, setUserFocus] = useState(false);

  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');

  const [verificationCode, setVerificationCode] = useState('');

  const [password, setPassword] = useState('');
  const [validPassword, setValidPassword] = useState(false);
  const [pwdFocus, setPasswordFocus] = useState(false);

  const [matchPassword, setMatchPassword] = useState('');
  const [validMatch, setValidMatch] = useState(false);
  const [matchFocus, setMatchFocus] = useState(false);

  const [errorMsg, setErrorMsg] = useState('');
  const [success, setSuccess] = useState(false);
  const [emailConfirmed, setEmailConfirmed] = useState(false);

  useEffect(() => {
    userRef.current?.focus();
  }, []);

  useEffect(() => {
    setValidEmail(USER_REGEX.test(email));
  }, [email]);

  useEffect(() => {
    setValidPassword(PASSWORD_REGEX.test(password));
    setValidMatch(password === matchPassword);
  }, [password, matchPassword]);

  useEffect(() => {
    setErrorMsg('');
  }, [email, password, matchPassword]);

  const handleSubmit = async (e: { preventDefault: () => void }) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        REGISTER_URL,
        JSON.stringify({ email, password, firstName, lastName }),
        {
          headers: { 'Content-Type': 'application/json' },
        }
      );

      setSuccess(true);
      setEmail('');
      setPassword('');
      setMatchPassword('');
      setFirstName('');
      setLastName('');
    } catch (err: unknown) {
      const error = err as AxiosError;

      if (error?.response?.status === 409) {
        setErrorMsg('Email is already registred');
        console.log('taken');
      } else {
        setErrorMsg('Registration Failed');
        console.log('failed');
      }
      errorRef.current?.focus();
    }
  };

  const handleVerificationCodeSubmit = async (e: {
    preventDefault: () => void;
  }) => {
    e.preventDefault();

    try {
      const response = await axios.put(
        VERIFY_EMAIL_URL,
        JSON.stringify({ email, verificationCode }),
        {
          headers: { 'Content-Type': 'application/json' },
        }
      );

      setEmailConfirmed(true);
      setEmail('');
      setVerificationCode('');
    } catch (err: unknown) {
      const error = err as AxiosError;

      if (error?.response?.status === 409) {
        setErrorMsg('Email is already registred');
        console.log('taken');
      } else if (error?.response?.status === 406) {
        setErrorMsg('Invalid Code');
        console.log('Invalid Code React');
      } else {
        setErrorMsg('Registration Failed');
        console.log('failed');
      }
      errorRef.current?.focus();
    }
  };

  return (
    <>
      {success && emailConfirmed ? (
        <section className="form-container">
          <h1>You were succefully registered!</h1>
          <br />
          <p style={{ fontSize: '20px' }}>
            <a href="/login"> Sign-In</a>
          </p>
        </section>
      ) : success ? (
        <section className="form-container">
          <p
            ref={errorRef}
            className={errorMsg ? 'error-msg' : 'offscreen'}
            aria-live="assertive"
          >
            {errorMsg}
          </p>
          <h1>Please, enter the code that you received in your email:</h1>
          <form onSubmit={handleVerificationCodeSubmit}>
            <label htmlFor="email">Email:</label>
            <input
              type="text"
              id="email"
              ref={userRef}
              onChange={(e) => setEmail(e.target.value)}
              required
              onFocus={() => setUserFocus(true)}
              onBlur={() => setUserFocus(false)}
            />
            <label htmlFor="verification-code">Verification Code:</label>
            <input
              type="text"
              id="verification-code"
              ref={userRef}
              onChange={(e) => setVerificationCode(e.target.value)}
              required
              onFocus={() => setUserFocus(true)}
              onBlur={() => setUserFocus(false)}
            />
            <button className="sign-up">Send</button>
          </form>
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
          <h1>Register</h1>
          <form onSubmit={handleSubmit}>
            <label htmlFor="first-name">First Name:</label>
            <input
              type="text"
              id="first-name"
              ref={userRef}
              onChange={(e) => setFirstName(e.target.value)}
              required
              onFocus={() => setUserFocus(true)}
              onBlur={() => setUserFocus(false)}
            />

            <label htmlFor="last-name">Last Name:</label>
            <input
              type="text"
              id="last-name"
              ref={userRef}
              onChange={(e) => setLastName(e.target.value)}
              required
              onFocus={() => setUserFocus(true)}
              onBlur={() => setUserFocus(false)}
            />

            <label htmlFor="email">
              Email:
              <FontAwesomeIcon
                icon={faCheck}
                className={validEmail ? 'valid' : 'hide'}
              />
              <FontAwesomeIcon
                icon={faTimes}
                className={validEmail || !email ? 'hide' : 'invalid'}
              />
            </label>
            <input
              type="text"
              id="email"
              ref={userRef}
              autoComplete="off"
              onChange={(e) => setEmail(e.target.value.toLowerCase())}
              required
              aria-invalid={validEmail ? false : true}
              aria-describedby="uid-note"
              onFocus={() => setUserFocus(true)}
              onBlur={() => setUserFocus(false)}
            />
            <p
              id="uid-note"
              className={
                userFocus && email && !validEmail ? 'instructions' : 'offscreen'
              }
            >
              <FontAwesomeIcon icon={faInfoCircle} />
              4 to 24 characters.
              <br />
              Must begin with a letter.
              <br />
              Letters, numbers, underscores, hyphens allowed.
            </p>

            <label htmlFor="password">
              Password:
              <FontAwesomeIcon
                icon={faCheck}
                className={validPassword ? 'valid' : 'hide'}
              />
              <FontAwesomeIcon
                icon={faTimes}
                className={validPassword || !password ? 'hide' : 'invalid'}
              />
            </label>
            <input
              type="password"
              id="password"
              onChange={(e) => setPassword(e.target.value)}
              value={password}
              required
              aria-invalid={validPassword ? 'false' : 'true'}
              aria-describedby="password-note"
              onFocus={() => setPasswordFocus(true)}
              onBlur={() => setPasswordFocus(false)}
            />
            <p
              id="password-note"
              className={
                pwdFocus && !validPassword ? 'instructions' : 'offscreen'
              }
            >
              <FontAwesomeIcon icon={faInfoCircle} />
              8 to 24 characters.
              <br />
              Must include uppercase and lowercase letters, a number and a
              special character.
              <br />
              Allowed special characters:
              <span aria-label="exclamation mark">!</span>
              <span aria-label="at symbol">@</span>
              <span aria-label="hashtag">#</span>
              <span aria-label="dollar sign">$</span>
              <span aria-label="percent">%</span>
            </p>

            <label htmlFor="confirm-password">
              Confirm Password:
              <FontAwesomeIcon
                icon={faCheck}
                className={validMatch && matchPassword ? 'valid' : 'hide'}
              />
              <FontAwesomeIcon
                icon={faTimes}
                className={validMatch || !matchPassword ? 'hide' : 'invalid'}
              />
            </label>
            <input
              type="password"
              id="confirm-password"
              onChange={(e) => setMatchPassword(e.target.value)}
              value={matchPassword}
              required
              aria-invalid={validMatch ? 'false' : 'true'}
              aria-describedby="confirm-note"
              onFocus={() => setMatchFocus(true)}
              onBlur={() => setMatchFocus(false)}
            />
            <p
              id="confirm-note"
              className={
                matchFocus && !validMatch ? 'instructions' : 'offscreen'
              }
            >
              <FontAwesomeIcon icon={faInfoCircle} />
              Password does't match.
            </p>

            <button
              className="sign-up"
              disabled={
                !validEmail || !validPassword || !validMatch ? true : false
              }
            >
              Sign Up
            </button>
          </form>
          <p>
            Already registered?&nbsp;
            <span className="line">
              <a href="/login">Sign-In here!</a>
            </span>
          </p>
        </section>
      )}
    </>
  );
};

export default RegisterForm;
