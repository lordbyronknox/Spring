import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
// import * as serviceWorker from './serviceWorker';
import { UserSignupPage } from './pages/UserSignupPage';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <UserSignupPage />
  </React.StrictMode>
);


// serviceWorker.unregister();
