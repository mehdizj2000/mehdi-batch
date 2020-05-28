package au.com.jaycar.batch.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.jaycar.batch.domain.AddressInfo;

public interface AddressViewRepo extends JpaRepository<AddressInfo, String> {

}
