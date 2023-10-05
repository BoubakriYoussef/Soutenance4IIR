    package ma.aeroauto.gestionproduits.web;

    import jakarta.servlet.http.HttpServletRequest;
    import lombok.AllArgsConstructor;
    import ma.aeroauto.gestionproduits.entities.*;
    import ma.aeroauto.gestionproduits.entities.Process;
    import ma.aeroauto.gestionproduits.repositories.*;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.PageRequest;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestParam;

    @Controller
    @AllArgsConstructor
    public class PieceController {
        @Autowired
        private PieceRepository pieceRepository;
        @Autowired
        private ProcessRepository processRepository;
        @Autowired
        private NuanceRepository nuanceRepository  ;
        @Autowired
        private MateriauRepository materiauRepository;
        @Autowired

        private FormatRepository formatRepository;



        @GetMapping(path = "/piece")
        public String piece(Model model,
                             @RequestParam(name="page",defaultValue="0")int page,
                             @RequestParam(name="size",defaultValue="5")int size,
                             @RequestParam(name="keyword",defaultValue="")String keyword){
            Page<Piece> pagepieces=pieceRepository.findByDescriptionContains(keyword, PageRequest.of(page,size));
            model.addAttribute("piece",pagepieces.getContent());
            model.addAttribute("pages",new int[pagepieces.getTotalPages()]);
            model.addAttribute("currentPage",page);
            model.addAttribute("keyword",keyword);
            return "piece";
        }



        @PostMapping("/savePiece")
        public String savePiece(Piece piece, HttpServletRequest request) {

            // Enregistrez la pièce dans la base de données
            pieceRepository.save(piece);
            return "redirect:/piece";
        }



        @GetMapping("/deletePiece")
        public String deletePiece(Long id,String keyword,int page){
            pieceRepository.deleteById(id);
            return "redirect:/piece?page="+page+"&keyword="+keyword;
        }

        @GetMapping("/formPiece")
        public String formPiece(Model model){
            model.addAttribute("piece",new Piece());
            model.addAttribute("processes", processRepository.findAll());
            model.addAttribute("nuances", nuanceRepository.findAll());
            model.addAttribute("materiaux", materiauRepository.findAll());
            model.addAttribute("formats", formatRepository.findAll());
            return "formPiece";
        }


        @GetMapping("/editPiece")
        public String editPiece(Model model, Long id){
            Piece piece = pieceRepository.findById(id).orElse(null);
            if(piece==null) throw new RuntimeException("Piece Introuvable");
            model.addAttribute("piece",piece);
            model.addAttribute("processes", processRepository.findAll());
            model.addAttribute("nuances", nuanceRepository.findAll());
            model.addAttribute("materiaux", materiauRepository.findAll());
            model.addAttribute("formats", formatRepository.findAll());
            return"editPiece";
        }
    }
