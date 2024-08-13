package com.bulletin.Controller;

import com.bulletin.dto.BulletinDTO;
import com.bulletin.dto.PageRequestDTO;
import com.bulletin.service.BulletinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/bulletin")
@Log4j2
@RequiredArgsConstructor
public class BulletinController {

    private final BulletinService service;

    @GetMapping("/")
    public String index() {

        return "redirect:/bulletin/list";

    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        log.info("list............." + pageRequestDTO);

        model.addAttribute("result", service.getList(pageRequestDTO));

    }

    @GetMapping("/register")
    public void register() {
        log.info("register get...");
    }

    @PostMapping("/register")
    public String registerPost(BulletinDTO dto, RedirectAttributes redirectAttributes) {

        log.info("dto..." + dto);

        Long bno = service.register(dto);

        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/bulletin/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(long bno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {

        log.info("bno: " + bno);

        BulletinDTO dto = service.read(bno);

        model.addAttribute("dto", dto);

    }

    @PostMapping("/remove")
    public String remove(long bno, RedirectAttributes redirectAttributes) {

        log.info("bno: " + bno);

        service.remove(bno);

        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/bulletin/list";

    }

    @PostMapping("/modify")
    public String modify(BulletinDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {

        log.info("post modify................................................................................................................");
        log.info("dto: " + dto);

        service.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("bno", dto.getBno());

        return "redirect:/bulletin/read";
    }

}
