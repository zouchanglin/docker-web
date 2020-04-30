package xpu.lhl.dockerweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import xpu.lhl.dockerweb.service.ContainerService;
import xpu.lhl.dockerweb.vo.ContainerVO;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/container")
public class ContainerController {
    @Autowired
    private ContainerService containerService;

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
}