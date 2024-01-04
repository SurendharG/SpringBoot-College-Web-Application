package com.simpleform0.Repositery;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.simpleform0.model.UserssModel;
public interface UserssRepository extends JpaRepository<UserssModel,Integer>{
	Optional<UserssModel>findByPersonalidAndPassword(Integer personalid,String password);
	Optional<UserssModel> findAllById(Integer personalid);
	@Query(value = "SELECT * FROM users_details WHERE personalid = ?1", nativeQuery = true)
	Optional<UserssModel> findByPersonalId(Integer personalId);
	@Query(value = "SELECT * FROM users_details WHERE personalid = ?1", nativeQuery = true)
	UserssModel forEdit(Integer personalId);
	@Modifying
	@Transactional
	@Query(value= "update users_details set username=?1, password=?2, access=?3 where personalid=?4", nativeQuery=true)
	int UpdateUser(String uname, String pword, String access, Integer pid);
	@Modifying
	@Transactional
	@Query(value= "delete from users_details where personalid=?1", nativeQuery=true)
	int deleteByIdown(Integer pid);
	@Query(value = "SELECT access FROM users_details WHERE personalid = ?1", nativeQuery = true)
	String findAccess(Integer personalId);

}
