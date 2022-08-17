package com.example.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.demo.entities.UserRoleEntity;
import com.example.demo.repositories.UserRoleRepository;
import com.example.demo.services.UserRoleService;
import com.example.demo.utils.PaginationUsingFromTo;


@Service
public class UserRoleServiceImpl implements UserRoleService{

	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	
	
	@Override
	public Page<UserRoleEntity> getAllUserRoles(String search, String from, String to) {
		Pageable paging = new PaginationUsingFromTo().getPagination(from, to);
		if ((search == "") || (search == null) || (search.length() == 0)) {
			
			return userRoleRepository.findByOrderByPk(paging, UserRoleEntity.class);
		} else {
			
			return userRoleRepository.findByIsActiveContainingIgnoreCaseOrderByPk(search, paging, UserRoleEntity.class);
			
				
		}
	}



	@Override
	public void deleteUserRole(Long user_Id) {
		
		userRoleRepository.deleteByPk(user_Id);
		
	}

}
