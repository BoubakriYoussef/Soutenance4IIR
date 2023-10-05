package ma.aeroauto.gestionproduits.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.aeroauto.gestionproduits.entities.Process;
import ma.aeroauto.gestionproduits.repositories.ProcessRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class ProcessController {
    //Injection des dépendences
    private ProcessRepository processRepository;

    //Methode qui renvoie une vue processus avec la pagination pageable
    @GetMapping(path = "/index")
    public String process(Model model,
                           @RequestParam(name="page",defaultValue="0")int page,
                            @RequestParam(name="size",defaultValue="5")int size,
                            @RequestParam(name="keyword",defaultValue="")String keyword){
        Page<Process> pageprocess=processRepository.findByTypeProcessContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listeProcess",pageprocess.getContent());
        model.addAttribute("pages",new int[pageprocess.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "process";
    }

    //Code pour la méthode delete, l'id de la meth est totalement différent de l'id de Process
    @GetMapping("/delete")
    public String delete(Long id,String keyword,int page){
        processRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    //Renvoie Home s'il y a pas de pages demandées
    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }

    @GetMapping("/formProcess")
    public String formProcess(Model model){
        model.addAttribute("process",new Process());
        return "formProcess";
    }

    @PostMapping (path="/save")
    public String save(Model model, @Valid Process process, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "formProcess";
        processRepository.save(process);
        return "redirect:/index";
    }

    @GetMapping("/editProcess")
    public String editPatient(Model model, Long id){
        Process process = processRepository.findById(id).orElse(null);
                if(process==null) throw new RuntimeException("Process Introuvable");
                model.addAttribute("process",process);
                return"editProcess";
    }

}
