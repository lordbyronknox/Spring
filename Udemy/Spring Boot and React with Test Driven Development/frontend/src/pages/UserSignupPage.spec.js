import React from 'react';
import { render, cleanup, fireEvent } from '@testing-library/react';  //for rendering the component
import '@testing-library/jest-dom/extend-expect'; //for additional extend functionality
import UserSignup, { UserSignupPage } from './UserSignupPage';

beforeEach(cleanup);    //calls the cleanup function before each test.

//javascript tests can be grouped using using 'describe' functions.
//(<descrition>, <function with test functions in it>)
describe('UserSignupPage', () => {
    describe('Layout', () => {                  //this test block checks the existance of the required fields.
        it('has header of sign up', () => {     //it() function. it=component we are testing.
            const { container } = render(<UserSignupPage />);        //render returns query functions.
            const header = container.querySelector('h1');  //object destructure: finds the h1 element.
            expect(header).toHaveTextContent('Sign Up');   //check that 'header' has 'Sign up' text in it.
        });

        it('has input for display name', () => { //2nd test.
            const { queryByPlaceholderText } = render(<UserSignupPage />);
            const displayNameInput = queryByPlaceholderText('Your display name');
            expect(displayNameInput).toBeInTheDocument();
        });

        it('has input for display username', () => { //3nd test.
            const { queryByPlaceholderText } = render(<UserSignupPage />);
            const usernameInput = queryByPlaceholderText('Your username');
            expect(usernameInput).toBeInTheDocument();
        });

        it('has input for password', () => { //4nd test.
            const { queryByPlaceholderText } = render(<UserSignupPage />);
            const passwordInput = queryByPlaceholderText('Your password');
            expect(passwordInput).toBeInTheDocument();
        });

        it('has password type for password input', () => { //5nd test.
            const { queryByPlaceholderText } = render(<UserSignupPage />);
            const passwordInput = queryByPlaceholderText('Your password');
            expect(passwordInput.type).toBe('password');
        });

        it('has input for password repeat', () => {
            const { queryByPlaceholderText } = render(<UserSignupPage />);
            const passwordRepeat = queryByPlaceholderText('Repeat your password');
            expect(passwordRepeat).toBeInTheDocument();
        });

        it('has password type for password repeat input', () => {
            const { queryByPlaceholderText } = render(<UserSignupPage />);
            const passwordRepeat = queryByPlaceholderText('Repeat your password');
            expect(passwordRepeat.type).toBe('password');
        });

        it('has submit button', () => {
            const { container } = render(<UserSignupPage />);
            const button = container.querySelector('button');
            expect(button).toBeInTheDocument();
        });
    });
    //second test group
    describe('Interactions', () => {

        //function to change/return new field value for target field.
        const changeEvent = (content) => { 
            return {target: {value: content} }
        };

        it('sets the display name value into state', () => {
            const { queryByPlaceholderText } = render(<UserSignupPage />);
            const displayNameInput = queryByPlaceholderText('Your display name');
        //simulate a user input with fireEvent.
            fireEvent.change(displayNameInput, changeEvent('my-display-name'));    //changes diplayNameInput's value to 'changeEvent's value.

            expect(displayNameInput).toHaveValue('my-display-name');
        });

        it('sets the username value into state', () => {
            const { queryByPlaceholderText } = render(<UserSignupPage />);
            const usernameInput = queryByPlaceholderText('Your username');
        //simulate a user input with fireEvent.
            fireEvent.change(usernameInput, changeEvent('my-user-name'));    //changes diplayNameInput's value to 'changeEvent's value.

            expect(usernameInput).toHaveValue('my-user-name');
        });

        it('sets the password value into state', () => {
            const { queryByPlaceholderText } = render(<UserSignupPage />);
            const passwordInput = queryByPlaceholderText('Your password');
        //simulate a user input with fireEvent.
            fireEvent.change(passwordInput, changeEvent('P4ssword'));    //changes diplayNameInput's value to 'changeEvent's value.

            expect(passwordInput).toHaveValue('P4ssword');
        });

        it('sets the password repeat value into state', () => {
            const { queryByPlaceholderText } = render(<UserSignupPage />);
            const passwordRepeat = queryByPlaceholderText('Repeat your password');
        //simulate a user input with fireEvent.
            fireEvent.change(passwordRepeat, changeEvent('P4ssword'));    //changes diplayNameInput's value to 'changeEvent's value.

            expect(passwordRepeat).toHaveValue('P4ssword');
        });

    });
});
