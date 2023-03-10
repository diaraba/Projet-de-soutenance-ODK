package com.diaraba.projetDeSoutenance.controllers;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.diaraba.projetDeSoutenance.models.*;
import com.diaraba.projetDeSoutenance.payload.request.StructureRequest;
import com.diaraba.projetDeSoutenance.repository.*;
import com.diaraba.projetDeSoutenance.security.services.StructureService;
import com.diaraba.projetDeSoutenance.security.services.UtilisateurService;
import com.diaraba.projetDeSoutenance.utilis.ConfigImage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.diaraba.projetDeSoutenance.payload.request.LoginRequest;
import com.diaraba.projetDeSoutenance.payload.request.SignupRequest;
import com.diaraba.projetDeSoutenance.payload.response.JwtResponse;
import com.diaraba.projetDeSoutenance.payload.response.MessageResponse;
import com.diaraba.projetDeSoutenance.security.jwt.JwtUtils;
import com.diaraba.projetDeSoutenance.security.services.UserDetailsImpl;
import org.springframework.web.multipart.MultipartFile;

import static com.diaraba.projetDeSoutenance.utilis.constants.IMAGE_PATH;
import static org.springframework.data.repository.init.ResourceReader.Type.JSON;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    private StructureRepository structureRepository;
    @Autowired
    private StructureService structureService;
    @Autowired
    private StatutRepository statutRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles));
    }

  /*@PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    *//*if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }*//*

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }*/

    @PostMapping("creerUtilisateur")
    public ResponseEntity<?>  ajouterUtilisateur(@RequestBody SignupRequest signupRequest) throws JsonProcessingException {
        Utilisateurs utilisateurs = new Utilisateurs(signupRequest.getNomutilisateur(),new ArrayList<>());



        if (utilisateurRepository.existsByNomutilisateur(signupRequest.getNomutilisateur())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: NomUtilisateur existe d??j??!"));
        }

        else if (structureRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use___________!"));
        }

        else if (utilisateurRepository.existsByEmail(utilisateurs.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use*************!"));
        }else{

        Set<String> strRoles = signupRequest.getRole();

        Set<Role> roles = new HashSet<>();


        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_EMPLOI).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "emploi":
                        Role user = roleRepository.findByName(ERole.ROLE_EMPLOI).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(user);

                        break;
                    case "projet":
                        Role user1 = roleRepository.findByName(ERole.ROLE_PROJET).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(user1);
                        break;
                    case "structure":
                        Role structure = roleRepository.findByName(ERole.ROLE_STRUCTURE).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(structure);
                        break;
      /*  case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;*/
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }


            String activitesString = signupRequest.getActivites().toString();
            Gson gson = new Gson();

            //Activites[] activites = gson.fromJson(activitesString,Activites[].class);

            //signupRequest.setActivites(Arrays.asList(activites));

        utilisateurs.setActivitesU(signupRequest.getActivites());
        System.out.println(signupRequest.getRole());
        utilisateurs.setRoles(roles);
        utilisateurs.setEmail(signupRequest.getEmail());
        utilisateurs.setPassword(encoder.encode(signupRequest.getPassword()));

        return utilisateurService.creerUtilisateur(utilisateurs);
        }
    }


    //Mise ?? jour d'une structure donn??e****************************************












    @PutMapping("modifierUtilisateur/{id}")
    public ResponseEntity<?>  updateUtilisateur(@PathVariable Long id,@RequestBody SignupRequest signupRequest) {
        Utilisateurs utilisateurs = new Utilisateurs(signupRequest.getNomutilisateur(),new ArrayList<>());




       if (structureRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use___________!"));
        }

       else{

            Set<String> strRoles = signupRequest.getRole();

            Set<Role> roles = new HashSet<>();


            if (strRoles == null) {
                Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);

                            break;
                        case "user":
                            Role user = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(user);

                            break;
                        case "structure":
                            Role structure = roleRepository.findByName(ERole.ROLE_STRUCTURE).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(structure);
                            break;
      /*  case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;*/
                        default:
                            Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(userRole);
                    }
                });
            }
            if (signupRequest.getEmail() == null|| signupRequest.getEmail().trim().isEmpty()) {
                System.out.println(signupRequest.getEmail()+"mmmmmmmmmmm");
                utilisateurs.setEmail(utilisateurRepository.findByIduser(id).getEmail());
            }else{
                utilisateurs.setEmail(signupRequest.getEmail());
            }
            if (signupRequest.getNomutilisateur() == null || signupRequest.getNomutilisateur().trim().isEmpty()) {
                System.out.println(signupRequest.getNomutilisateur()+"eeeeee");
                utilisateurs.setNomutilisateur(utilisateurRepository.findByIduser(id).getNomutilisateur());

            }else{
                utilisateurs.setNomutilisateur(signupRequest.getNomutilisateur());
            }
            if (signupRequest.getActivites() == null) {
                utilisateurs.setActivitesU(utilisateurRepository.findByIduser(id).getActivitesU());
            }else{
                utilisateurs.setActivitesU(signupRequest.getActivites());
            }
            if (signupRequest.getRole() == null || signupRequest.getRole().isEmpty()) {
                utilisateurs.setRoles(utilisateurRepository.findByIduser(id).getRoles());
            }else{
                utilisateurs.setRoles(roles);
            }

           if (signupRequest.getPassword() == null || signupRequest.getPassword().isEmpty()) {
               utilisateurs.setPassword(encoder.encode(utilisateurRepository.findByIduser(id).getPassword()));
           }else{
               utilisateurs.setPassword(encoder.encode(signupRequest.getPassword()));
           }
           if (signupRequest.getRole() == null || signupRequest.getRole().isEmpty()) {
               utilisateurs.setPassword(encoder.encode(utilisateurRepository.findByIduser(id).getPassword()));
           }


            return utilisateurService.updateUtilisateur(id,utilisateurs);
        }
    }










    //CREER STRUCTURE

    @PostMapping("creerStructure")
    public ResponseEntity<?> ajouterStructure(@Param("objet") String objet,
                                              @Param("photodecouverture") MultipartFile photodecouverture) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StructureRequest structureRequest = mapper.readValue(objet, StructureRequest.class);
        Structure structure = new Structure(structureRequest.getAlias());
        structure.setEmail(structureRequest.getEmail());
        structure.setActivites(structureRequest.getActivites());
        structure.setPassword(encoder.encode(structureRequest.getPassword()));

        System.out.println(structureRequest.getActivites());

        System.out.println(structure.getActivites()+"secaind");
        if (structureRepository.existsByAlias(structureRequest.getAlias())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Alias existe d??j?? !"));
        }
        else if (structureRepository.existsByEmail(structureRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use___________!"));
        }

        else if (utilisateurRepository.existsByEmail(structure.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use*************!"));
        }else {

        Set<String> strStatuts = structureRequest.getStatut();

        Set<Statut> statuts = new HashSet<>();


        if (strStatuts == null) {
            Statut userStatut = statutRepository.findByName(EStatut.Public).orElseThrow(() -> new RuntimeException("Error: Statut is not found."));
            statuts.add(userStatut);
        } else {
            strStatuts.forEach(role -> {
                switch (role) {
                    case "prive":
                        Statut adminStatut = statutRepository.findByName(EStatut.Prive).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        statuts.add(adminStatut);

                        break;
      /*  case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;*/
                    case "public":
                        Statut userStatut = statutRepository.findByName(EStatut.Public).orElseThrow(() -> new RuntimeException("Error: Statut is not found."));
                        statuts.add(userStatut);
                }
            });
        }
        structure.setStatuts(statuts);


        Set<String> strRoles = structureRequest.getRole();

        Set<Role> roles = new HashSet<>();


        if (strRoles == null) {
            Role StructureRole = roleRepository.findByName(ERole.ROLE_STRUCTURE).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(StructureRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "user":
                        Role user = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(user);
                    case "structure":
                        Role StructureRole = roleRepository.findByName(ERole.ROLE_STRUCTURE).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(StructureRole);

                        break;
                }
            });
        }
        structure.setRoles(roles);

            String img = StringUtils.cleanPath(photodecouverture.getOriginalFilename());
            structure.setPhotodecouverture(img);
            String uploaDir = IMAGE_PATH;
            ConfigImage.saveimg(uploaDir, img, photodecouverture);

            return structureService.creerStructure(structure);
    }
    }



    //Mise ?? jour d'une structure donn??e****************************************



    @PutMapping("modifierStructure/{id}")
    public ResponseEntity<?> updateStructure(@PathVariable Long id, @RequestBody StructureRequest structureRequest) {
        Structure structure = new Structure(structureRequest.getAlias());
        structure.setEmail(structureRequest.getEmail());
        structure.setActivites(structureRequest.getActivites());
        structure.setPassword(encoder.encode(structureRequest.getPassword()));

        System.out.println(structureRequest.getActivites());

         if (utilisateurRepository.existsByEmail(structure.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use*************!"));
        }else {

            Set<String> strStatuts = structureRequest.getStatut();

            Set<Statut> statuts = new HashSet<>();


            if (strStatuts == null) {
                Statut userStatut = statutRepository.findByName(EStatut.Public).orElseThrow(() -> new RuntimeException("Error: Statut is not found."));
                statuts.add(userStatut);
            } else {
                strStatuts.forEach(role -> {
                    switch (role) {
                        case "prive":
                            Statut adminStatut = statutRepository.findByName(EStatut.Prive).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            statuts.add(adminStatut);

                            break;
      /*  case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;*/
                        case "public":
                            Statut userStatut = statutRepository.findByName(EStatut.Public).orElseThrow(() -> new RuntimeException("Error: Statut is not found."));
                            statuts.add(userStatut);
                    }
                });
            }
            structure.setStatuts(statuts);


            Set<String> strRoles = structureRequest.getRole();

            Set<Role> roles = new HashSet<>();


            if (strRoles == null) {
                Role StructureRole = roleRepository.findByName(ERole.ROLE_STRUCTURE).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(StructureRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "structure":
                            Role StructureRole = roleRepository.findByName(ERole.ROLE_STRUCTURE).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(StructureRole);

                            break;
                    }
                });
            }
            structure.setRoles(roles);
            return structureService.updateStructure(id,structure);
        }
    }


 /* @PostMapping("/signupStructure")
  public ResponseEntity<?> registerStructure(@Valid @RequestBody StructureRequest structureRequest) {
    if (userRepository.existsByUsername(structureRequest.getAlias())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(structureRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    Structure structure = new Structure(structureRequest.getAlias(),
            structureRequest.getEmail(),
            encoder.encode(structureRequest.getPassword()));

    Set<String> strStatuts = structureRequest.getStatut();
    Set<Statut> statuts = new HashSet<>();

    if (strStatuts == null) {
      Statut publicStatut = statutRepository.findByName(EStatut.Public)
              .orElseThrow(() -> new RuntimeException("Error: Statut is not found."));
      statuts.add(publicStatut);
    } else {
      strStatuts.forEach(statut -> {
        switch (statut) {
          case "prive":
            Statut priveStatut = statutRepository.findByName(EStatut.Prive)
                    .orElseThrow(() -> new RuntimeException("Error: Statut is not found."));
            statuts.add(priveStatut);

            break;
          default:
            Statut publicStatut = statutRepository.findByName(EStatut.Public)
                    .orElseThrow(() -> new RuntimeException("Error: Statut is not found."));
            statuts.add(publicStatut);
        }
      });
    }

    structure.setStatuts(statuts);
    structureRepository.save(structure);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  @PostMapping("/signinStructure")
  public ResponseEntity<?> authenticateStructure(@Valid @RequestBody LoginStructureRequest loginStructureRequest) {

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginStructureRequest.getAlias(), loginStructureRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils1.generateJwtToken(authentication);

    StructureDetailsImpl structureDetails = (StructureDetailsImpl) authentication.getPrincipal();
    List<String> statuts = structureDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
            structureDetails.getId(),
            structureDetails.getUsername(),
            structureDetails.getEmail(),
            statuts));
  }*/

}


