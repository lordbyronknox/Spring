import React from 'react';
import { render, cleanup, fireEvent, waitFor, waitForDomChange } from '@testing-library/react';  //for rendering the component
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
        //changes in a component occurs (eg <input>) it triggers an event. This event object       
        // carries the input value inside taget object's value field. 
        const changeEvent = (content) => {
            return { target: { value: content } }
        };

const mockAsyncdelayed = () => {
    return jest.fn().mockImplementation(() => {
        return new Promise((resolve, reject) => {
            setTimeout(() => {resolve({});
            }, 300)
        })
    })
};

        let button, displayNameInput, usernameInput, passwordInput, passwordRepeat;

        const setupForSubmit = (props) => {
            const rendered = render(
                <UserSignupPage {...props} />
            );

            const { container, queryByPlaceholderText } = rendered;

            displayNameInput = queryByPlaceholderText('Your display name');
            usernameInput = queryByPlaceholderText('Your username');
            passwordInput = queryByPlaceholderText('Your password');
            passwordRepeat = queryByPlaceholderText('Repeat your password');

            fireEvent.change(displayNameInput, changeEvent('my-display-name'));
            fireEvent.change(usernameInput, changeEvent('my-user-name'));
            fireEvent.change(passwordInput, changeEvent('P4ssword'));
            fireEvent.change(passwordRepeat, changeEvent('P4ssword'));

            button = container.querySelector('button');

            return rendered;
        }

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

        //test API call to backend when login is clicked.
        //In testing we dont actually send an http request to the backend - we send a 'moch' call.
        //We create a moch function using jest.
        it('it calls postsignup when the fields are valid and the actions are provided in props', () => {
            //declare a variable 'actions' and assign it the value of the mock function (json object).
            const actions = {
                //create a mock function that returns empty json object, as the response value doesnt matter
                //for this test, only that the response works.
                postSignup: jest.fn().mockResolvedValueOnce({})
            }

            setupForSubmit({ actions });    //call setupForSubmit() func and pass actions as a props.
            
            fireEvent.click(button);
            expect(actions.postSignup).toHaveBeenCalledTimes(1); //check mock function, to be fired once.
        });

        it('does not throw exception when clicking the button when actions not provided in props', () => {
            setupForSubmit();
            
            expect(() => fireEvent.click(button)).not.toThrow();
        });

        it('calls post with user body when the fields are valid', () => {
            const actions = {
                postSignup: jest.fn().mockResolvedValueOnce({})
            };
            setupForSubmit({ actions });    //call setupForSubmit() func and pass actions as a props.
            fireEvent.click(button);

            const expectedUserObject = {
                username: 'my-user-name',
                displayName: 'my-display-name',
                password: 'P4ssword',
            }
            expect(actions.postSignup).toHaveBeenCalledWith(expectedUserObject); //check mock function, to be fired once.
        });

        it('does not allow user to click Sign up button when there is an ongoing api call', () => {
            const actions = {
                postSignup: mockAsyncdelayed()
            };
            setupForSubmit({ actions });    //call setupForSubmit() func and pass actions as a props.
            fireEvent.click(button);    //1st Click
            fireEvent.click(button);    //2nd Click
            expect(actions.postSignup).toHaveBeenCalledTimes(1);
         });

         it('displays spinner when there is an ongoing api call', () => {
            const actions = {
                postSignup: mockAsyncdelayed()
            };
            const {queryByText} = setupForSubmit({ actions });    //call setupForSubmit() func and pass actions as a props.
            fireEvent.click(button);

            const spinner = queryByText('Loading...');  //'loading' - from bootstrap.
            expect(spinner).toBeInTheDocument();
         });

         //mark test function as 'async' to make 'await' available.
         it('hode spinner when api call finishes successfully', async () => {
            const actions = {
                postSignup: mockAsyncdelayed()
            };
            const {queryByText} = setupForSubmit({ actions });    //call setupForSubmit() func and pass actions as a props.
            fireEvent.click(button);

            const spinner = queryByText('Loading...');  //'loading' - from bootstrap.
            await waitFor(() => expect(spinner).not.toBeInTheDocument());
         });

         it('hode spinner when api call finishes with error', async () => {
            const actions = {
                postSignup: jest.fn().mockImplementation(() => {
                    return new Promise((resolve, reject) => {
                        setTimeout(() => {
                            reject({
                                response: {data: {}}
                            });
                        }, 300)
                    })
                })
            };
            const {queryByText} = setupForSubmit({ actions });    //call setupForSubmit() func and pass actions as a props.
            fireEvent.click(button);

            const spinner = queryByText('Loading...');  //'loading' - from bootstrap.
            await waitFor(() => expect(spinner).not.toBeInTheDocument());
         });

    });
});

console.error = () => {}; //over-ride the error with empty function.