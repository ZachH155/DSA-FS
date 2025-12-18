package zh.dsa_fs;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;







@Controller
public class MainController {
    
    @GetMapping("/")
    public String index() {
        return "redirect:/enter-numbers";
    }
    

    //enter numbers page
    @GetMapping("/enter-numbers")
    public String renderEnterNumbers() {
        return "enter-numbers";
    }

    @PostMapping("/enter-numbers")
    public String postEnterNumbers(@RequestParam("numbers") String numbers, RedirectAttributes redirectAttributes) { 
        String[] numbersArrayS = null;
        ArrayList<Integer> numbersArrayI = new ArrayList<Integer>();

        numbersArrayS =  numbers.split(",");
        for (String string : numbersArrayS) {
            numbersArrayI.add(Integer.parseInt(string));
        }

        redirectAttributes.addAttribute("numbersArray", numbersArrayI);

        return "redirect:/process-numbers";
    }

    @PostMapping("/past-trees")
    public String redirectPreviousTrees() {
        return "redirect:/previous-trees";
    }


    //process-numbers page
    @GetMapping("/process-numbers")
    public String renderProcessNumbers(@RequestParam("numbersArray") ArrayList<Integer> numbersArray, Model model) {
        
        BSTNode root = new BSTNode(numbersArray.getFirst());

        for (Integer integer : numbersArray) {
            if (integer != root.getSelf()) {
                root.addRecursive(root, integer, root.getLevel());
            }
        }

        ArrayList<BSTNode> queue = new ArrayList<BSTNode>();
        ArrayList<int[]> binaryTreeArray = new ArrayList<int[]>();

        root.searchByLevel(root, queue, binaryTreeArray, false);
        

       model.addAttribute("BTA", binaryTreeArray);

        return "process-numbers";
    }

    @PostMapping("/back")
    public String back() {
        
        return "redirect:";
    }
    
    

    //previous trees page
    @GetMapping("/previous-trees")
    public String renderPreviousTrees() {
        return "previous-trees";
    }
    
    
    
}
    

