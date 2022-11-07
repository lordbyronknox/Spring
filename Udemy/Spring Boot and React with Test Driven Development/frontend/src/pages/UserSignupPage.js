import React from "react";

export class UserSignupPage extends React.Component {

    //state = a jsnon object where we can add any fields.
    state = {
        displayName: '',
        username: '',
        password: '',
        passwordRepeat: '',
    };

    //function for handling changes to the field.
    //changes made to input field (in browser) generates an 'event' object.
    onChangeDisplayName = (event) => {
        const value = event.target.value;   //get the value of the event.
        this.setState({ displayName: value }); //set the value to the display name field.
    };

    onChangeUserName = (event) => {
        const value = event.target.value;   //get the value of the event.
        this.setState({ username: value }); //set the value to the user name field.
    };

    onChangePassword = (event) => {
        const value = event.target.value;   //get the value of the event.
        this.setState({ password: value }); //set the value to the password field.
    };

    onChangePasswordRepeat = (event) => {
        const value = event.target.value;   //get the value of the event.
        this.setState({ passwordRepeat: value }); //set the value to the password field.
    };

    //function to be called when the button is clicked.
    onClickSignup = () => {
        const user = {
            username: this.state.username,
            displayName: this.state.displayName,
            password: this.state.password
        }
        this.props.actions.postSignup(user);    //provide the actions object as a property (using props) to this component, 
    };                                          // so we can execute the actions.postSignup function.

    render() {                    //render() = a function of  Component.
        return (                  //jsx: html is javascript code.
            <div>
                <h1>Sign Up</h1>
                <div>
                    <input placeholder="Your display name" value={this.state.displayName}
                    onChange={this.onChangeDisplayName}/>
                </div>
                <div>
                    <input placeholder="Your username" value={this.state.username} 
                    onChange={this.onChangeUserName}/>
                </div>
                <div>
                    <input placeholder="Your password" type="password" value={this.state.password}
                    onChange={this.onChangePassword}/>
                </div>
                <div>
                    <input placeholder="Repeat your password" type="password" value={this.state.passwordRepeat}
                    onChange={this.onChangePasswordRepeat}/>
                </div>
                <div>
                    <button onClick={this.onClickSignup}>Sign Up</button>
                </div>
            </div>                
        );
    }
}

//set default props for this component (UserSignupPage).
UserSignupPage.defaultProps = {
    actions: {              //if actions is 'false' (doesnt exist), then set it to an empty object.
        postSignup: () => new Promise((resolve, reject) => {
            resolve({}); //return empty object.
        })
    }
};

export default UserSignupPage;