package com.cicdlectures.menuserver.controller;

import static org.assertj.core.api.Assertions.*;

import java.net.URL;
import java.util.List;
import java.util.HashSet;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;

import com.cicdlectures.menuserver.repository.MenuRepository;
import com.cicdlectures.menuserver.dto.DishDto;
import com.cicdlectures.menuserver.dto.MenuDto;
import com.cicdlectures.menuserver.model.Dish;
import com.cicdlectures.menuserver.model.Menu;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// Indique de relancer l'application à chaque test.
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MenuControllerIT {

  @LocalServerPort
  private int port;

  private URL getMenusURL() throws Exception {
    return new URL("http://localhost:" + port + "/menus");
  }

  // Injecte automatiquement l'instance du menu repository
  @Autowired
  private MenuRepository menuRepository;

  // Injecte automatiquement l'instance du TestRestTemplate
  @Autowired
  private TestRestTemplate template;

  @Test
  @DisplayName("lists all known menus")
  public void listsAllMenus() throws Exception {

    // Défini une liste de menus avec un menus.
    Iterable<Menu> newMenus = Arrays.asList(
      new Menu(
        null,
        "Christmas menu",
        new HashSet<>(
          Arrays.asList(
           new Dish(null, "Turkey", null),
           new Dish(null, "Pecan Pie", null)
          )
        )
      )
    );

    this.menuRepository.saveAll(newMenus);

    // Effectue une requête GET /menus
    ResponseEntity<MenuDto[]> response = this.template.getForEntity(getMenusURL().toString(), MenuDto[].class);

    //Parse le payload de la réponse sous forme d'array de MenuDto
    MenuDto[] gotMenus = response.getBody();

    //Recupere le status de la requête
    HttpStatus responseStatus = response.getStatusCode();

    //Verifie que la requête renvoie un code 200
    assertEquals(HttpStatus.OK, responseStatus);

    // On défini wantMenus, les résultats attendus
    Iterable<MenuDto> wantMenus = Arrays.asList(
      new MenuDto(
          Long.valueOf(1),
          "Christmas menu",
          new HashSet<>(
          Arrays.asList(
              new DishDto(Long.valueOf(1), "Turkey"),
              new DishDto(Long.valueOf(2), "Pecan Pie")
          )
          )
      )
      );
      
    //assertEquals(wantMenus. gotMenus[0]);
  }
}