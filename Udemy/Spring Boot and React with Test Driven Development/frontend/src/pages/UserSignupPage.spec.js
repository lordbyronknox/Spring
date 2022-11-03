import React from 'react';
import { render } from '@testing-library/react';  //for rendering the component
import '@testing-library/jest-dom/extend-expect'; //for additional extend functionality
import UserSignup, { UserSignupPage } from './UserSignupPage';

//javascript tests can be grouped using 'describe functions.
//(<descrition>, <function with test functions in it>)
describe('UserSignupPage', () => {
    describe('Layout', () => {                //this test block checks the existance of the required fields.
        it('has header of sign up', () => {   //it() function. it=component we are testing.
            const { container } = render(<UserSignupPage />);        //render returns query functions.
            const header = container.querySelector('h1');  //object destructure: finds the h1 element.
            expect(header).toHaveTextContent('Sign Up');   //check that 'header' has 'Sign up' text in it.
        })
    })
})