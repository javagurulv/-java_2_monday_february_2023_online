package lv.fitness_app.web_ui.controllers.rest;

import lv.fitness_app.core.requests.*;
import lv.fitness_app.core.responses.*;
import lv.fitness_app.core.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("/user")
public class UserRestController {

	@Autowired private GetUserService getUserService;
	@Autowired private AddUserService addUserService;
	@Autowired private UpdateUserService updateUserService;
	@Autowired private DeleteUserService deleteUserService;

	@GetMapping(path = "/{email}", produces = "application/json")
	public GetUserResponse getUser(@PathVariable String email) {
		GetUserRequest request = new GetUserRequest(email);
		return getUserService.execute(request);
	}

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public AddUserResponse addUser(@RequestBody AddUserRequest request) {
		return addUserService.execute(request);
	}

	@PutMapping(path = "/{email}",
			consumes = "application/json",
			produces = "application/json")
	public UpdateUserResponse updateUser(@RequestBody UpdateUserRequest request) {
		return updateUserService.execute(request);
	}

	@DeleteMapping(path = "/{email}", produces = "application/json")
	public DeleteUserResponse deleteUser(@PathVariable String email) {
		DeleteUserRequest request = new DeleteUserRequest(email);
		return deleteUserService.execute(request);
	}

}