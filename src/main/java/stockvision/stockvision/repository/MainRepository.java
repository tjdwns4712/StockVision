package stockvision.stockvision.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import stockvision.stockvision.DTO.MainDTO;
import stockvision.stockvision.entity.MainEntity;

import java.util.Optional;

public interface MainRepository extends JpaRepository<MainEntity, String> {
    Optional<MainEntity> findById(String stock_name);
}
