package xpu.lhl.dockerweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xpu.lhl.dockerweb.entity.ContainerInfo;

public interface ContainerInfoRepository extends JpaRepository<ContainerInfo, String> {

}
