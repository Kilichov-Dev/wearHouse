package uz.pdp.wearhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.wearhouse.entity.Attachments;

public interface AttachmentRepository extends JpaRepository<Attachments,Integer> {
}
