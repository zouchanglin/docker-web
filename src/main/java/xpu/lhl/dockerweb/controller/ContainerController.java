package xpu.lhl.dockerweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import xpu.lhl.dockerweb.form.ContainerCommitForm;
import xpu.lhl.dockerweb.form.CreateContainerForm;
import xpu.lhl.dockerweb.service.ContainerService;
import xpu.lhl.dockerweb.vo.ContainerVO;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/container")
public class ContainerController {
    private final ContainerService containerService;

    @Autowired
    public ContainerController(ContainerService containerService) {
        this.containerService = containerService;
    }

    @GetMapping("/list")
    public ModelAndView listContainer(Map<String, Object> map){
        List<ContainerVO> containerVOList = containerService.getAllContainer();
        map.put("containers", containerVOList);
        return new ModelAndView("virtual/container/list", map);
    }

    @GetMapping("/status/{state}")
    public ModelAndView listContainerByState(@PathVariable String state, Map<String, Object> map){
        log.info("【ContainerController】listContainerByState {}", state);
        List<ContainerVO> containerVOList = containerService.getAllContainerByState(state);
        map.put("containers", containerVOList);
        String viewPath = String.format("virtual/container/%s-list", state);
        return new ModelAndView(viewPath, map);
    }

    @GetMapping("start")
    public ModelAndView startContainerById(String containerId, Map<String, String> map){
        map.put("url", "/container/status/running");
        if(containerService.startContainer(containerId)){
            map.put("msg", "Start this container success!");
            return new ModelAndView("common/success");
        }else{
            map.put("msg", "Start this container failed!");
            return new ModelAndView("common/error");
        }
    }

    @GetMapping("pause")
    public ModelAndView pauseContainerById(String containerId, Map<String, String> map){
        map.put("url", "/container/status/running");
        if(containerService.pauseContainer(containerId)){
            map.put("msg", "Pause this container success!");
            return new ModelAndView("common/success");
        }else{
            map.put("msg", "Pause this container failed!");
            return new ModelAndView("common/error");
        }
    }

    @GetMapping("stop")
    public ModelAndView stopContainerById(String containerId, Map<String, String> map){
        map.put("url", "/container/status/running");
        if(containerService.stopContainer(containerId)){
            map.put("msg", "Stop this container success!");
            return new ModelAndView("common/success");
        }else{
            map.put("msg", "Stop this container failed!");
            return new ModelAndView("common/error");
        }
    }

    @GetMapping("unpause")
    public ModelAndView unpauseContainerById(String containerId, Map<String, String> map){
        map.put("url", "/container/status/running");
        if(containerService.unpauseContainer(containerId)){
            map.put("msg", "Stop this container success!");
            return new ModelAndView("common/success");
        }else{
            map.put("msg", "Stop this container failed!");
            return new ModelAndView("common/error");
        }
    }

    @GetMapping("remove")
    public ModelAndView removeContainerById(String containerId, Map<String, String> map){
        map.put("url", "/container/status/running");
        if(containerService.removeContainer(containerId)){
            map.put("msg", "Stop this container success!");
            return new ModelAndView("common/success");
        }else{
            map.put("msg", "Stop this container failed!");
            return new ModelAndView("common/error");
        }
    }

    @PostMapping("create")
    public ModelAndView createContainer(CreateContainerForm createContainerForm,
                                        Map<String, String> map){
        // log.info("【ContainerController】createContainer createContainerForm = {}", createContainerForm);
        String containerId = containerService.createContainer(createContainerForm);
        map.put("url", "/container/status/create");
        if(containerId != null){
            map.put("msg", "创建容器成功，容器Id为 " + containerId);
            return new ModelAndView("common/success");
        }else{
            map.put("msg", "创建容器失败");
            return new ModelAndView("common/error");
        }
    }

    @PostMapping("create-and-run")
    public ModelAndView createAndRunContainer(CreateContainerForm createContainerForm,
                                        Map<String, String> map){
        String containerId = containerService.createContainer(createContainerForm);
        containerService.startContainer(containerId);
        map.put("url", "/container/status/running");
        if(containerService.startContainer(containerId)){
            map.put("msg", "Start this container success!");
            return new ModelAndView("common/success");
        }else{
            map.put("msg", "Start this container failed!");
            return new ModelAndView("common/error");
        }
    }

    @GetMapping("create-page")
    public ModelAndView getCreateContainerPage(String imageId, Map<String, String> map){
        map.put("imageId", imageId);
        return new ModelAndView("virtual/container/create");
    }

    @GetMapping("restart")
    public ModelAndView restartContainer(String containerId, Map<String, Object> map){
        map.put("url", "/container/status/running");
        if(containerService.restartContainer(containerId)){
            map.put("msg", "Restart this container success!");
            return new ModelAndView("common/success");
        }else{
            map.put("msg", "Restart this container failed!");
            return new ModelAndView("common/error");
        }
    }

    @GetMapping("commit-page")
    public ModelAndView getCommitContainerPage(String containerId, Map<String, String> map){
        map.put("containerId", containerId);
        Map<String, String> resultParamMap = containerService.getRepositoryInfo(containerId);
        map.put("containerId", containerId);
        map.put("containerName", resultParamMap.get("containerName"));
        map.put("serverAddress", resultParamMap.get("serverAddress"));
        map.put("namespace", resultParamMap.get("namespace"));
        map.put("author", resultParamMap.get("author"));
        return new ModelAndView("virtual/container/commit", map);
    }

    @PostMapping("commit")
    public ModelAndView commitContainer(ContainerCommitForm commitForm, Map<String, String> map){
        String newImageId = containerService.commitContainer(commitForm);
        map.put("url", "/images/list");
        if(newImageId != null){
            map.put("msg", "Container Commit Success!");
            return new ModelAndView("common/success", map);
        }else{
            map.put("msg", "Container Commit Failed!");
            return new ModelAndView("common/error", map);
        }
    }

    @GetMapping("kill")
    public ModelAndView killContainer(String containerId, Map<String, String> map){
        map.put("url", "/container/list");
        map.put("msg", "Kill Success");
        containerService.killContainer(containerId);
        return new ModelAndView("common/success", map);
    }
}