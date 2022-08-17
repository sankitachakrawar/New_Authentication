package com.example.demo.controllers;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.AssignPermission;
import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.IRoleDetailDto;
import com.example.demo.dto.RoleDto;
import com.example.demo.dto.RolePermissionDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.entities.RoleEntity;
import com.example.demo.exceptionHandling.ResourceNotFoundException;
import com.example.demo.services.PermissionServiceInterface;
import com.example.demo.services.RoleServiceInterface;
import com.example.demo.dto.ListResponseDto;
import com.example.demo.dto.RoleCandidateDto;

@RestController
@RequestMapping("/api")
public class RoleController {

	public RoleController() {

	}

	@Autowired
	private RoleServiceInterface roleServiceInterface;
	
	
	@PreAuthorize("hasRole('addRole')")
	@PostMapping("/role")
	public ResponseEntity<?> addRole(@RequestBody RoleDto roleDto, HttpServletRequest request) {
		
		this.roleServiceInterface.addRole(roleDto);
		
		return new ResponseEntity<>("Role added successfully",HttpStatus.OK);
	}

	@PreAuthorize("hasRole('editRole')")
	@PutMapping("/role/{id}")
	public ResponseEntity<?> editRole(@PathVariable(value = "id") Long roleId, @Valid @RequestBody RoleDto roleDto, HttpServletRequest request) {
			try {
			
		roleServiceInterface.updateRole(roleDto, roleId);

			return new ResponseEntity<>("Role updated successfully", HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<>("Role not found",HttpStatus.BAD_REQUEST);
			}
	}

	@PreAuthorize("hasRole('deleteRole')")
	@DeleteMapping("/role/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable(value = "id") Long roleId, HttpServletRequest request) {

		try {
			roleServiceInterface.deleteRole(roleId);
			return new ResponseEntity<>(new SuccessResponseDto("Success", "success", null), HttpStatus.OK);

		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "roleNotFound"), HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasRole('getRoleById')")
	@GetMapping("/role/{id}")
	public ResponseEntity<?> getRoleById(@PathVariable(value = "id") Long id) {

		

			RoleEntity roleEntity = roleServiceInterface.getRoleById(id);
			System.out.println("role>>"+roleEntity);
			return new ResponseEntity<>(new SuccessResponseDto("Success", "success", roleEntity), HttpStatus.OK);

		
	}

	 	//Pagination of role list
		@PreAuthorize("hasRole('getAllRoles')")
		@GetMapping("/roles")
		public ResponseEntity<?> getAllRoles(@RequestParam(defaultValue = "") String search,
				@RequestParam(defaultValue = "1") String pageNo, @RequestParam(defaultValue = "25") String size) {
			Page<IRoleDetailDto> jobs = roleServiceInterface.getAllRoles(search, pageNo, size);
			if (jobs.getTotalElements() != 0) {
				return new ResponseEntity<>(new SuccessResponseDto("Success", "success",
						new ListResponseDto(jobs.getContent(), jobs.getTotalElements())), HttpStatus.OK);
			}
			return new ResponseEntity<>(new ErrorResponseDto("Data Not Found", "dataNotFound"), HttpStatus.NOT_FOUND);
		}
		
		
		@PreAuthorize("hasRole('getRoleAndPermissionById')")
		@GetMapping("/permission/{id}")
		public ResponseEntity<?> getRoleAndPermissionById(@PathVariable(value = "id") Long roleId) {

			try {

				RolePermissionDto rolePermissionData = roleServiceInterface.getRoleAndPermissionById(roleId);
				
				return new ResponseEntity<>(new SuccessResponseDto("Success", "success", rolePermissionData), HttpStatus.OK);

			} catch (ResourceNotFoundException e) {

				return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "roleNotFound"), HttpStatus.NOT_FOUND);

			}

		}
		
		@GetMapping("/candidate/{id}")
		public ResponseEntity<?> getRoleAndCandidateById(@PathVariable(value = "id") Long id) {

			try {

				RoleCandidateDto roleCandidateData = roleServiceInterface.getRoleAndCandidateById(id);
				return new ResponseEntity<>(new SuccessResponseDto("Success", "success", roleCandidateData), HttpStatus.OK);

			} catch (ResourceNotFoundException e) {

				return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "roleNotFound"), HttpStatus.NOT_FOUND);

			}

		}
	
		//@PreAuthorize("hasRole('assignPermission')")
		@PostMapping("/assignPermission")
		public ResponseEntity<?> assignPermission(@Valid @RequestBody AssignPermission assignPermission,HttpServletRequest request){
		try {					
			roleServiceInterface.addPermissionToRole(assignPermission);
		return new ResponseEntity<>(new SuccessResponseDto("Permission assign to Role","PermissionassigntoRole",null),HttpStatus.OK);
				
		}catch(Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto("Permission not assign to Role","PermissionnotassigntoRole"),HttpStatus.BAD_REQUEST);
		}
	}
	
		

		@GetMapping("/permission/user/{id}")
		public ResponseEntity<?> getPermissionByUserId(@PathVariable ("id") Long user_Id){
			
			
			
			ArrayList<String> roleEntity=roleServiceInterface.getPermissionByUserId(user_Id);
			return new ResponseEntity<>(new SuccessResponseDto("Success", "success", roleEntity), HttpStatus.OK);
		}
}























//@PreAuthorize("hasRole('getSinglePermission')")
//@GetMapping("/role/permission/{id}")
//public ResponseEntity<PermissionEntity> getSinglePermission(@PathVariable Long id){
//	
//	return ResponseEntity.ok(this.roleServiceInterface.getPermissionById(id));
//	
//}




////@PreAuthorize("hasRole('AddPermissionToRole')")
//@PostMapping("/permission/{id}")
//public ResponseEntity<?> AddPermissionToRole(@PathVariable(value = "id") Long roleId, @Valid @RequestBody AddPermissionRequestDto permissions) {
//
//	try {
//
//		roleServiceInterface.addPermissionsToRole(roleId, permissions.getPermissions());
//		return new ResponseEntity<>(new SuccessResponseDto("Success", "success", null), HttpStatus.OK);
//
//	} catch (ResourceNotFoundException e) {
//
//		return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "roleNotFound"), HttpStatus.NOT_FOUND);
//
//	}
//
//}














////@PreAuthorize("hasRole('AllPermissionToRole')")
//@PostMapping("/roles/assignPermission")
//public ResponseEntity<?> AddPermissionToRole(@Valid @RequestBody AssignPermission assignPermission, HttpServletRequest request) {
//	try {
//		String actionName=assignPermission.getActionName();
//		String roleName=assignPermission.getRoleName();
//		
//		roleServiceInterface.addPermissionToRole(actionName, roleName);
//		return new ResponseEntity<>(new SuccessResponseDto("Permission Assign to Role", "PermissionAssigntoRole", assignPermission),
//				HttpStatus.CREATED);
//	}catch(Exception e) {
//		e.printStackTrace();
//		return new ResponseEntity<>(new ErrorResponseDto("Permission Not Assign to Role", "PermissionNotAssigntoRole"),
//				HttpStatus.NOT_ACCEPTABLE);
//	}
//	
//	
//
//}
//

////@PreAuthorize("hasRole('AllPermissionToRole')")
//@PostMapping("/roles/assignPermission")
//public ResponseEntity<?> AddPermissionToRole(@Valid @RequestBody AssignPermission assignPermission, HttpServletRequest request) {
//	try {
//		String actionName=assignPermission.getActionName();
//		String roleName=assignPermission.getRoleName();
//		
//		roleServiceInterface.addPermissionToRole(actionName, roleName);
//		return new ResponseEntity<>(new SuccessResponseDto("Permission Assign to Role", "PermissionAssigntoRole", assignPermission),
//				HttpStatus.CREATED);
//	}catch(Exception e) {
//		e.printStackTrace();
//		return new ResponseEntity<>(new ErrorResponseDto("Permission Not Assign to Role", "PermissionNotAssigntoRole"),
//				HttpStatus.NOT_ACCEPTABLE);
//	}
//
//}





//
////@PreAuthorize("hasRole('assignRole')")
//@PostMapping("/candidate/assignRole")
//public ResponseEntity<?> assignRole(@Valid @RequestBody AssignRole assignRole, HttpServletRequest request)
//				throws Exception {
//try {
//		String email = assignRole.getEmail();
//		String roleName = assignRole.getRoleName();
//		System.out.println(email);
//		System.out.println(roleName);
//		
//		roleServiceInterface.addRoleToCandidate(email, roleName);
//		return new ResponseEntity<>(new SuccessResponseDto("Role Assign to Candidate", "roleAssignToCandidate", assignRole),
//						HttpStatus.OK);
//		
//	} catch (Exception e) {
//		e.printStackTrace();
//		return new ResponseEntity<>(new ErrorResponseDto("Role Not Assign to Candidate", "roleNotAssignToCandidate"),
//						HttpStatus.BAD_REQUEST);
//	}
//		
//}	
