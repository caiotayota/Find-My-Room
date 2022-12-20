import './App.css';
import RegisterPage from './pages/RegisterPage';
import { Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import LoginPage from './pages/LoginPage';
import Layout from './components/Layout/Layout';
import RequireAuth from './components/RequireAuth/RequireAuth';
import EmailVerificationPage from './pages/EmailVerificationPage';

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route path="login" element={<LoginPage />} />
          <Route path="register" element={<RegisterPage />} />
          <Route path="/" element={<Home />} />
          <Route
            path="/verification-code"
            element={<EmailVerificationPage />}
          />

          <Route element={<RequireAuth />}></Route>

          {/* TODO:  <Route path="*" element{<Missing/>} /> */}
        </Route>
      </Routes>
    </>
  );
}

export default App;
