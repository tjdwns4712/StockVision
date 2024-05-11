package stockvision.stockvision.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stockvision.stockvision.DTO.MainDTO;
import stockvision.stockvision.entity.MainEntity;
import stockvision.stockvision.repository.MainRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MainService {
    private final MainRepository mainRepository;


    public MainDTO searchStockCode(MainDTO mainDTO) {
            System.out.println("코드조회 서비스 시작");
        Optional<MainEntity> stockCode = mainRepository.findById(mainDTO.getStock_name());
        if(stockCode.isPresent()){
            MainEntity mainEntity = stockCode.get();
            System.out.println(mainDTO.getStock_name() + "주식에 대한 코드 반환");
            return MainDTO.toMainDTO(mainEntity);
        }
        else{
            System.out.println(mainDTO.getStock_name() + "주식에 대한 코드 없음");
            throw new RuntimeException("No stock found with the name: " + mainDTO.getStock_name());
        }

    }
}
