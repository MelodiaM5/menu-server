// package com.cicdlectures.menuserver.service;

// import java.util.HashSet;
// import java.util.Arrays;
// import java.util.List;

// import com.cicdlectures.menuserver.dto.MenuDto;
// import com.cicdlectures.menuserver.dto.DishDto;
// import com.cicdlectures.menuserver.model.Menu;
// import com.cicdlectures.menuserver.model.Dish;
// import com.cicdlectures.menuserver.repository.MenuRepository;
// import com.cicdlectures.menuserver.repository.DishRepository;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;

// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.when;

// import static org.junit.jupiter.api.Assertions.assertEquals;


// public class CreateMenuServiceTests {
//     private MenuRepository menuRepository;

//     private DishRepository dishRepository;

//     private CreateMenuService subject;

//     @BeforeEach
//     public void init() {
//         this.menuRepository = mock(MenuRepository.class);
//         this.dishRepository = mock(DishRepository.class);
//         this.subject = new CreateMenuService(this.menuRepository, this.dishRepository);
//     }

//     @Test
//     @DisplayName("Create a new menu")
//     public void createNewMenu() {
//         Iterable<Menu> storedMenus = Arrays.asList(
//            new Menu(
//                 Long.valueOf(1),
//                 "Christmas menu",
//                new HashSet<>(
//                 Arrays.asList(
//                 new Dish(Long.valueOf(1), "Turkey", null),
//                 new Dish(Long.valueOf(2), "Pecan Pie", null)
//                   )
//                 )
//             )
//         );
//         // configure le mock pour qu'il retourne une instance de menu
//         when(menuRepository.save(any(Menu.class))).thenReturn(storedMenus);

//         // On appelle le code a tester...

//         // On déclare un ArgumentCaptor<Menu> (qui sert a capturer un argument)
//         ArgumentCaptor<Menu> savedMenuCaptor = ArgumentCaptor.forClass(Menu.class);

//         // On vérifie que la méthode `save` du menu repository à été appelée une seule fois
//         // et on capture l'argument avec lequel elle a été appelée (le menu).
//         verify(menuRepository, times(1)).save(savedMenuCaptor.capture());

//         // On récupère la valeur capturée pour pouvoir faire des assertions dessus.
//         savedMenu = savedMenuCaptor.getValue()

//     }
// }
