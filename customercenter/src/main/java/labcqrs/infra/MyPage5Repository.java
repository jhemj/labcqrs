package labcqrs.infra;

import java.util.List;
import labcqrs.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "myPage5s", path = "myPage5s")
public interface MyPage5Repository
    extends PagingAndSortingRepository<MyPage5, Long> {}
