package net.javaguides.springboot.controller;



import java.util.List;
import java.util.Optional;
import java.util.Set;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.model.Address;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.AddressRepository;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@AllArgsConstructor
public class AddressController {

    private EmployeeRepository employeeRepository;

    private AddressRepository addressRepository;


    @GetMapping("/employee/{employeeId}/addresses")
    public String getAddressPage(@PathVariable Long employeeId, Model model) {
        Employee employee = employeeRepository.findById(employeeId).get();
        // .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
        model.addAttribute("employee", employee);
        return "add-address-form";
    }

    @PostMapping("/employee/{employeeId}/addresses")
    public String addAddress(@PathVariable Long employeeId, @ModelAttribute Address address) {
        Employee employee = employeeRepository.findById(employeeId).get();
        // .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
        address.setEmployee(employee);
        addressRepository.save(address);
//		return "redirect:/employee/" + employeeId + "/addresses";
        // return "list-addresses";
        return "redirect:/employee/{employeeId}/addresses";
    }

    @GetMapping("/employee/{employeeId}/addresslist")
    public ModelAndView showAddressList(@PathVariable("employeeId") Long employeeId) {
        ModelAndView mav = new ModelAndView("list-addresses");
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee != null) {
            List<Address> addresses = addressRepository.findByEmployee(employee);
            mav.addObject("employee", employee);
            mav.addObject("addresses", addresses);
        }
        return mav;
    }


}
