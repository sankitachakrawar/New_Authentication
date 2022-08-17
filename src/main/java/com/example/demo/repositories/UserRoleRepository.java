package com.example.demo.repositories;

import java.util.ArrayList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.dto.RoleIdListDto;
import com.example.demo.entities.UserRoleEntity;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

	ArrayList<RoleIdListDto> findByPkUserId(Long userId, Class<RoleIdListDto> RoleIdListDto);

	Page<UserRoleEntity> findByOrderByPk(Pageable paging, Class<UserRoleEntity> class1);

	Page<UserRoleEntity> findByIsActiveContainingIgnoreCaseOrderByPk(String search, Pageable paging,
			Class<UserRoleEntity> class1);

	void deleteByPk(Long user_Id);



	//void deleteByPkUserId(Long userId);

	//ArrayList<IUserRoleDetailDto> findByPkUserIdAndPkUserIsActiveTrue(Long userId, Class<IUserRoleDetailDto> IUserRoleDetailDto);

	
	

}
