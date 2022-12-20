import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { AuthProvider } from './context/AuthProvider';
import 'semantic-ui-css/semantic.min.css';
import './index.css';

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <Router>
      {/* <AuthProvider> */}
      <Routes>
        <Route path="/*" element={<App />} />
      </Routes>
      {/* </AuthProvider> */}
    </Router>
  </React.StrictMode>
);
