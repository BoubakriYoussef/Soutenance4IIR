package ma.aeroauto.gestionproduits.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.aeroauto.gestionproduits.entities.Nuance;
import ma.aeroauto.gestionproduits.repositories.NuanceRepository;
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
public class NuanceController {
    private NuanceRepository nuanceRepository;

        //Methode qui renvoie une vue forme avec la pagination pageable
        @GetMapping(path = "/nuance")
        public String nuance(Model model,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "5") int size,
                             @RequestParam(name = "keyword", defaultValue = "") String keyword) {
            Page<Nuance> pageNuance;
            if (keyword.isEmpty()) {
                pageNuance = nuanceRepository.findAll(PageRequest.of(page, size));
            } else {
                pageNuance = nuanceRepository.findBytypeNuance(keyword, PageRequest.of(page, size));
            }
            model.addAttribute("nuance", pageNuance.getContent());
            model.addAttribute("pages", new int[pageNuance.getTotalPages()]);
            model.addAttribute("currentPage", page);
            model.addAttribute("keyword", keyword);
            return "nuance";
        }

    @GetMapping("/deleteNuance")
        public String deleteNuance(Long id,String keyword,int page){
            nuanceRepository.deleteById(id);
            return "redirect:/nuance?page="+page+"&keyword="+keyword;
        }


        @GetMapping("/formNuance")
        public String formNuance(Model model){
            model.addAttribute("nuance",new Nuance());
            return "formNuance";
        }


        @PostMapping(path="/saveNuance")
        public String saveNuance(Model model, @Valid Nuance nuance, BindingResult bindingResult){
            if(bindingResult.hasErrors()) return "formNuance";
            nuanceRepository.save(nuance);
            return "redirect:/nuance";
        }

        @GetMapping("/editNuance")
        public String editNuance(Model model, Long id){
            Nuance nuance = nuanceRepository.findById(id).orElse(null);
            if(nuance==null) throw new RuntimeException("Nuance Introuvable");
            model.addAttribute("nuance",nuance);
            return"editNuance";
        }

    }


