package uz.pdp.wearhouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.wearhouse.entity.template.AbcEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbcEntity {
    @ManyToOne(optional = false)
    private Category category;
    @OneToOne
    private Attachments photo;
    private String code = UUID.randomUUID().toString();
    @ManyToOne(optional = false)
    private Measurement measurement;
}
