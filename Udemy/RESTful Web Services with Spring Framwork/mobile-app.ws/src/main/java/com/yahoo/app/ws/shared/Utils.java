//Utility class that generates user id's.

package com.yahoo.app.ws.shared;

import java.util.UUID;

import org.springframework.stereotype.Service;

//@Service allows Utils to be injected into UserServiceImpl.
@Service
public class Utils {
	
	public String generateUserId()
	{
		return UUID.randomUUID().toString();
	}

}
