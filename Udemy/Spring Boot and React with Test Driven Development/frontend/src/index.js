import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import { UserSignupPage } from './UserSignupPage';

ReactDOM.render(<UserSignUpPage />, document.getElementById('root'));



serviceWorker.unregister();
