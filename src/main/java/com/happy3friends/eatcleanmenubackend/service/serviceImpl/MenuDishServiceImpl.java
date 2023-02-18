package com.happy3friends.eatcleanmenubackend.service.serviceImpl;

import com.happy3friends.eatcleanmenubackend.dto.MenuDishDTO;
import com.happy3friends.eatcleanmenubackend.entity.DishEntity;
import com.happy3friends.eatcleanmenubackend.entity.MenuDishEntity;
import com.happy3friends.eatcleanmenubackend.entity.MenuEntity;
import com.happy3friends.eatcleanmenubackend.entity.UserDietaryInfoEntity;
import com.happy3friends.eatcleanmenubackend.exception.BadRequestException;
import com.happy3friends.eatcleanmenubackend.exception.NotFoundException;
import com.happy3friends.eatcleanmenubackend.mapper.MenuDishMapper;
import com.happy3friends.eatcleanmenubackend.repository.DishRepository;
import com.happy3friends.eatcleanmenubackend.repository.MenuDishRepository;
import com.happy3friends.eatcleanmenubackend.repository.MenuRepository;
import com.happy3friends.eatcleanmenubackend.repository.UserDietaryInfoRepository;
import com.happy3friends.eatcleanmenubackend.service.MenuDishService;
import com.happy3friends.eatcleanmenubackend.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MenuDishServiceImpl implements MenuDishService {

    @Autowired
    private MenuDishRepository menuDishRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserDietaryInfoRepository userDietaryInfoRepository;

    @Autowired
    private MenuDishMapper menuDishMapper;

    @Autowired
    private DishRepository dishRepository;

    @Override
    public List<MenuDishDTO> createMenuDishByUserIdAndMealDateAndMenuId(int userId, Date mealDate, int menuId) {
        UserDietaryInfoEntity userDietaryInfoEntity = userDietaryInfoRepository.findByUserId(userId);

        if (userDietaryInfoEntity == null)
            throw new NotFoundException("Can not create menu due to lack of user dietary information!");

        // Get the list of dishes that are suitable for needed calories consumed
        List<DishEntity> dishEntityList = new ArrayList<>();
        double caloriesConsumed = userDietaryInfoEntity.getCaloriesConsumed();
        int count = 3;
        double existedCalo = 0;

        while (count != 0) {
            double minCalo = ((caloriesConsumed - existedCalo) / count) - ((caloriesConsumed - existedCalo) / count) * 20 / 100;
            double maxCalo = ((caloriesConsumed - existedCalo) / count) + ((caloriesConsumed - existedCalo) / count) * 20 / 100;

            DishEntity dishEntity = dishRepository.randomDishByCaloriesBetween(minCalo, maxCalo);
            if (dishEntity == null) {
                dishEntity = dishRepository.randomDishByCaloriesBetween(minCalo - minCalo * 45 / 100, maxCalo);
                while (dishEntity == null) {
                    dishEntity = dishRepository.randomDishByCaloriesBetween(minCalo - minCalo * 60 / 100, maxCalo);
                }
            }
            dishEntityList.add(dishEntity);
            existedCalo += dishEntity.getCalories();
            count--;
        }
        dishEntityList = dishEntityList.stream().sorted(Comparator.comparingDouble(DishEntity::getCalories)).collect(Collectors.toList());

        List<MenuDishEntity> menuDishEntityList = new ArrayList<>();
        MenuDishEntity menu1 = new MenuDishEntity();
        menu1.setMenuId(menuId);
        menu1.setDishId(dishEntityList.get(0).getId());
        menu1.setMealDate(new java.sql.Date(mealDate.getTime()));
        menu1.setMealTime("Bữa sáng");
        menu1.setDishByDishId(dishEntityList.get(0));
        menuDishEntityList.add(menu1);

        MenuDishEntity menu2 = new MenuDishEntity();
        menu2.setMenuId(menuId);
        menu2.setDishId(dishEntityList.get(1).getId());
        menu2.setMealDate(new java.sql.Date(mealDate.getTime()));
        menu2.setMealTime("Bữa trưa");
        menu2.setDishByDishId(dishEntityList.get(1));
        menuDishEntityList.add(menu2);

        MenuDishEntity menu3 = new MenuDishEntity();
        menu3.setMenuId(menuId);
        menu3.setDishId(dishEntityList.get(2).getId());
        menu3.setMealDate(new java.sql.Date(mealDate.getTime()));
        menu3.setMealTime("Bữa tối");
        menu3.setDishByDishId(dishEntityList.get(2));
        menuDishEntityList.add(menu3);

        menuDishRepository.saveAll(menuDishEntityList);

        return menuDishEntityList.stream().map(e -> menuDishMapper.convertEntityToDTO(e)).collect(Collectors.toList());
    }

    @Override
    public List<MenuDishDTO> getMenuDishByMealDateAndUserId(String mealDate, int userId) {
        MenuEntity menuEntity = menuRepository.findByUserId(userId);

        if (menuEntity == null) {
            MenuEntity menu = new MenuEntity();
            menu.setUserId(userId);
            menuRepository.save(menu);
        }

        Date date = DateTimeUtil.convertStringToDate(mealDate);
        /*if (!DateTimeUtil.checkDateBetweenMinMax(menuEntity.getMenuPeriodStartDate(), menuEntity.getMenuPeriodEndDate(), date))
            throw new BadRequestException("MealDate: " + mealDate + " is out of subscription time!");*/

        List<MenuDishEntity> menuDishEntity = menuDishRepository.findByMealDateAndMenuId(new java.sql.Date(date.getTime()), menuEntity.getId());
        List<MenuDishDTO> result = null;
        if (menuDishEntity.size() == 0) {
            // Create dishes in menu for user
            result = createMenuDishByUserIdAndMealDateAndMenuId(userId, date, menuEntity.getId());
        } else {
            // Get dishes in menu for user
            result = menuDishEntity.stream().map(e -> menuDishMapper.convertEntityToDTO(e)).collect(Collectors.toList());
        }

        return result;
    }
}
