package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entities.UserRoleEntity;


public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

	//void findById(Long c_id, Class<RoleIdListDto> class1);

	//ArrayList<RoleIdListDto> findById(Long c_id, Class<RoleIdListDto> RoleIdListDto);

	//void deleteById(Long c_id);

	//void findById(Long c_id, Class<RoleIdListDto> class1);

	//ArrayList<IUserRoleDetailDto> findByPkUserIdAndPkUserIsActiveTrue(Long userId, Class<IUserRoleDetailDto> IUserRoleDetailDto);

}
