package ma.aeroauto.gestionproduits.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.aeroauto.gestionproduits.entities.Format;
import ma.aeroauto.gestionproduits.entities.Materiau;
import ma.aeroauto.gestionproduits.entities.Process;
import ma.aeroauto.gestionproduits.repositories.FormatRepository;

import ma.aeroauto.gestionproduits.repositories.MateriauRepository;
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
public class MateriauController {
    private MateriauRepository materiauRepository;


    @GetMapping(path = "/materiau")
    public String materiau(Model model,
                         @RequestParam(name="page",defaultValue="0")int page,
                         @RequestParam(name="size",defaultValue="5")int size,
                         @RequestParam(name="keyword",defaultValue="")String keyword){
        Page<Materiau> pagemateriaux=materiauRepository.findByTypeMateriauContains(keyword, PageRequest.of(page,size));
        model.addAttribute("materiau",pagemateriaux.getContent());
        model.addAttribute("pages",new int[pagemateriaux.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "materiau";
    }
    @GetMapping("/deleteMateriau")
    public String deleteMateriau(Long id,String keyword,int page){
        materiauRepository.deleteById(id);
        return "redirect:/materiau?page="+page+"&keyword="+keyword;
    }


    @GetMapping("/formMateriau")
    public String formMateriau(Model model){
        model.addAttribute("materiau",new Materiau());
        return "formMateriau";
    }


    @PostMapping(path="/saveMateriau")
    public String saveMateriau(Model model, @Valid Materiau materiau, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "formMateriau";
        materiauRepository.save(materiau);
        return "redirect:/materiau";
    }

    @GetMapping("/editMateriau")
    public String editMateriau(Model model, Long id){
        Materiau materiau = materiauRepository.findById(id).orElse(null);
        if(materiau==null) throw new RuntimeException("Materiau Introuvable");
        model.addAttribute("materiau",materiau);
        return"editMateriau";
    }

}
