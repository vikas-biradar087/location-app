package com.psa.location_web_appp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;

import com.psa.location_web_appp.entity.Location;

//import com.psa.location_web_appp.entity.Location;
import com.psa.location_web_appp.repositories.LocationRepository;
import com.psa.location_web_appp.util.EmailUtil;
//import com.psa.location_web_appp.util.EmailUtil;


@Controller
public class LocationController {
	
	@Autowired
	private LocationRepository locationRepo;
	
//	@Autowired
//	private Location location;
	
	@RequestMapping("/showLocation")
	public String showLocation() {
		return "showLocation";
	}
	
	@RequestMapping("/saveLoc")
	public String saveLoc(@ModelAttribute("location") Location location,ModelMap modelMap) {
//		System.out.println(location.getId());
//		System.out.println(location.getCode());
//		System.out.println(location.getName());
//		System.out.println(location.getType());
		EmailUtil emailUtil=new EmailUtil();
		locationRepo.save(location);
		emailUtil.sendEmail("biradarvikas341@gmail.com","Test Email","Record Is Saved");
		modelMap.addAttribute("msg", "location Saved !!!");
		return "showLocation";
	}
	
//	@RequestMapping("/saveLoc")
//	public String saveLoc(@RequestParam("id") Long id,@RequestParam("code") String code,@RequestParam("name") String name,@RequestParam("type") String type,ModelMap modelMap) {
////		System.out.println(id);
////		System.out.println(code);
////		System.out.println(name);
////		System.out.println(type);
//	Location location=new Location();
//		location.setId(id);
//		location.setCode(code);
//		location.setName(name);
//		location.setType(type);
//	locationRepo.save(location);
////		
//		modelMap.addAttribute("msg", "location Saved !!!");
//		return "showLocation";
//	}
	
	@RequestMapping("/getLocation")
	public String getLocation(ModelMap modelMap) {
		List<Location> locations = locationRepo.findAll();
		modelMap.addAttribute("locations", locations);
		return "getLocation";
	}
	
	@RequestMapping("/displayLocations")
	public String displayLocations() {
		return "getLocation";
	}
	
	@RequestMapping("/deleteLocation")
	public String deleteLocation(@RequestParam("id") Long id,ModelMap modelMap) {
		locationRepo.deleteById(id);
		List<Location> locations = locationRepo.findAll();
		modelMap.addAttribute("locations", locations);
		
		return "getLocation";
	}
	
	@RequestMapping("/updateLocation")
	public String updateLocation(@RequestParam("id") Long id,ModelMap modelMap) {
		System.out.println(id);
		Optional<Location> findById = locationRepo.findById(id);
		Location location= findById.get();
		modelMap.addAttribute("id", location.getId());
		modelMap.addAttribute("id", location.getName());
		modelMap.addAttribute("id", location.getCode());
		modelMap.addAttribute("id", location.getType());
		return "updateLocation";
	}

}
