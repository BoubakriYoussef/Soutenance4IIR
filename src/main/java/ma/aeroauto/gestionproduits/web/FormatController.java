package ma.aeroauto.gestionproduits.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.aeroauto.gestionproduits.entities.Format;
import ma.aeroauto.gestionproduits.entities.Process;
import ma.aeroauto.gestionproduits.repositories.FormatRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class FormatController {
    private FormatRepository formatRepository;

    //Methode qui renvoie une vue forme avec la pagination pageable
    @GetMapping(path = "/format")
    public String format(Model model,
                          @RequestParam(name="page",defaultValue="0")int page,
                          @RequestParam(name="size",defaultValue="5")int size,
                          @RequestParam(name="keyword",defaultValue="")String keyword){
        Page<Format> pageformes=formatRepository.findByTypeFormatContains(keyword, PageRequest.of(page,size));
        model.addAttribute("format",pageformes.getContent());
        model.addAttribute("pages",new int[pageformes.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "format";
    }
    @GetMapping("/deleteFormat")
    public String deleteFormat(Long id,String keyword,int page){
        formatRepository.deleteById(id);
        return "redirect:/format?page="+page+"&keyword="+keyword;
    }


    @GetMapping("/formFormat")
    public String formFormat(Model model){
        model.addAttribute("format",new Format());
        return "formFormat";
    }


    @PostMapping(path="/saveFormat")
    public String saveForme(Model model, @Valid Format format, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "formFormat";
        formatRepository.save(format);
        return "redirect:/format";
    }

    @GetMapping("/editFormat")
    public String editFormat(Model model, Long id){
        Format format = formatRepository.findById(id).orElse(null);
        if(format==null) throw new RuntimeException("Format Introuvable");
        model.addAttribute("format",format);
        return"editFormat";
    }

}
