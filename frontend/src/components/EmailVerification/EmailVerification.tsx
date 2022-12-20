import { useRef, useState, useEffect } from 'react';
import axios from '../../api/axios';

import { AxiosError } from 'axios';

const VERIFY_EMAIL_URL = '/user/verify-email';
export let emailConfirmed = false;

function EmailVerification() {
  const userRef = useRef<HTMLInputElement>(null);
  const errorRef = useRef<HTMLParagraphElement>(null);
  const [userFocus, setUserFocus] = useState(false);

  const query = new URLSearchParams(location.search);
  const username = query.get('username');
  const code = query.get('code');

  const [email, setEmail] = useState(username != null ? username : '');
  const [verificationCode, setVerificationCode] = useState(
    code != null ? code : ''
  );
  const [errorMsg, setErrorMsg] = useState('');

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

      emailConfirmed = true;
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
          defaultValue={email}
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
          defaultValue={verificationCode}
          onChange={(e) => setVerificationCode(e.target.value)}
          required
          onFocus={() => setUserFocus(true)}
          onBlur={() => setUserFocus(false)}
        />
        <button className="sign-up">Send</button>
      </form>
    </section>
  );
}

export default EmailVerification;
