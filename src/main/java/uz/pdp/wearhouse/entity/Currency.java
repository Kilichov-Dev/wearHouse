package uz.pdp.wearhouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.wearhouse.entity.template.AbcEntity;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)

@Entity
public class Currency extends AbcEntity {

}
