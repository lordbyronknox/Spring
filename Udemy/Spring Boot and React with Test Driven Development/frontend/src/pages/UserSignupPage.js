import React from "react";

export class UserSignupPage extends React.Component {

    //state = a jsnon object where we can add any fields.
    state = {
        displayName: '',
        username: '',
        password: '',
        passwordRepeat: '',
        pendingApiCall: false   //changed to true when 'pendingApiCall' is called
    };                          // by clicking button. when true, button is disabled.

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
        this.setState({pendingApiCall: true});
        this.props.actions.postSignup(user).then(response => {  //when postSignup request receives a 'success' response
            this.setState({pendingApiCall: false});             // the 'then()' func is triggered, which sets pendingApiCall to false.
        })
        .catch((error) => {this.setState({pendingApiCall: false});         //catch the error
    });                                              
    };
    
    render() {                    //render() = a function of  Component.
        return (                  //jsx: html is javascript code.
            <div className="container">
                <h1 className="text-center">Sign Up</h1>
                <div className="col-12 mb-3">
                    <label>Display name</label>
                    <input className="form-control"
                    placeholder="Your display name" 
                    value={this.state.displayName}
                    onChange={this.onChangeDisplayName}/>
                </div>
                <div className="col-12 mb-3">
                    <label>Username</label>
                    <input className="form-control"
                    placeholder="Your username" value={this.state.username} 
                    onChange={this.onChangeUserName}/>
                </div>
                <div className="col-12 mb-3">
                    <label>Password</label>
                    <input className="form-control" 
                    placeholder="Your password" type="password" value={this.state.password}
                    onChange={this.onChangePassword}/>
                </div>
                <div className="col-12 mb-3">
                    <label>Repeat Password</label>
                    <input className="form-control"
                    placeholder="Repeat your password" type="password" value={this.state.passwordRepeat}
                    onChange={this.onChangePasswordRepeat}/>
                </div>
                <div className="text-center">
                    <button 
                    className="btn btn-primary" 
                    onClick={this.onClickSignup}
                    disabled={this.state.pendingApiCall}
                    >
                    {this.state.pendingApiCall && (
                    <div className="spinner-border text-light spinner-border-sm mr-sm-1">
                        <span className="visually-hidden">Loading...</span>
                    </div>
                    )}
                    Sign Up
                    </button>
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