package au.com.jaycar.batch.repo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import au.com.jaycar.batch.domain.AddressInfo;

@DataJpaTest()
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
class AddressViewRepoTest {

    @Autowired
    private AddressViewRepo addressViewRepo;

    @Test
    void testFindAllPageable() {
	
	Page<AddressInfo> page = addressViewRepo.findAll(PageRequest.of(0, 50));
	assertNotNull(page);
	
	Page<AddressInfo> page2 = addressViewRepo.findAll(PageRequest.of(1, 50));
	assertNotNull(page2);
	
	Page<AddressInfo> page3 = addressViewRepo.findAll(PageRequest.of(2, 50));
	assertNotNull(page3);
	
	
    }

}
