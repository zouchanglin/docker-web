package xpu.lhl.dockerweb.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import xpu.lhl.dockerweb.entity.ContainerConfig;

public interface ContainerConfigRepository extends JpaRepository<ContainerConfig, String> {
}
