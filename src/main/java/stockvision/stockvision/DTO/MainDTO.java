package stockvision.stockvision.DTO;
//html쪽에 있는 정보가 DTO

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import stockvision.stockvision.entity.MainEntity;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MainDTO {
    private String stock_name;
    private String stock_code;


    //Entity를 DTO로
    public static MainDTO toMainDTO(MainEntity mainEntity){
        MainDTO mainDTO = new MainDTO();

        mainDTO.setStock_name(mainEntity.getStock_name());
        mainDTO.setStock_code(mainEntity.getStock_code());

        return mainDTO;
    }
}
