import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
// import * as serviceWorker from './serviceWorker';
import { UserSignupPage } from './pages/UserSignupPage';
import * as apiCalls from './api/apiCalls';

const actions = {             //create object with 'postSignup' field.
  postSignup: apiCalls.signup //pass 'actions' obj as property to <UserSignupPage>
};

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <UserSignupPage actions={actions} />  
  </React.StrictMode>
);


// serviceWorker.unregister();
