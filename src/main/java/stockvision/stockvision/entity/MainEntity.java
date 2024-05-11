package stockvision.stockvision.entity;
//데이터베이스쪽에 있는 정보가 Entity

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import stockvision.stockvision.DTO.MainDTO;

@Entity
@Setter
@Getter
@Table(name = "stock") // 이 클래스는 'stock' 테이블과 매핑됨
public class MainEntity {
    @Id // 기본 키
    private String stock_name; // 정확한 필드 이름 사용

    @Column
    private String stock_code;

    //DTO를 Entity로
    public static MainEntity toMainEntity(MainDTO mainDTO){
        MainEntity mainEntity = new MainEntity();

        mainEntity.setStock_name(mainDTO.getStock_name());
        mainEntity.setStock_code(mainDTO.getStock_code());

        return mainEntity;

    }

}
