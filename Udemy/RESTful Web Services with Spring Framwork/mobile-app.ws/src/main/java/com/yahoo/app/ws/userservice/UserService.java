package com.yahoo.app.ws.userservice;

import com.yahoo.app.ws.ui.model.request.UserDetailsRequestModel;
import com.yahoo.app.ws.ui.model.response.UserRest;

public interface UserService {
	
	UserRest createUser(UserDetailsRequestModel userDetails);

}
