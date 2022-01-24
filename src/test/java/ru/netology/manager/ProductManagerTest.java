package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repositiry.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    Product first = new Book(1, "Fight Club", 500, "Chuck Palahniuk");
    Product second = new Book(2, "1984", 550, "George Orwell");
    Product third = new Smartphone(3, "3310", 3000, "Nokia");
    Product fourth = new Smartphone(4, "Galaxy S22", 80000, "Samsung");
    Product fifth = new Smartphone(5, "Galaxy S21", 65000, "Samsung");
    Product sixth = new Product(1,"Uncorrect product", 800); //добавлен продукт, без указания типа, для отработки менеджера
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    @BeforeEach
    public void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
        manager.add(sixth);
    }

    @Test
    public void shouldSearchIfIncludeNameInBook() {
        Product[] expected = new Product[]{first};
        Product[] actual = manager.searchBy("Fight Club");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfNameNotIncludeInBook() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("NewText");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfIncludeNameInSmartphone() {
        Product[] expected = new Product[]{third};
        Product[] actual = manager.searchBy("3310");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfNameNotIncludeInSmartphone() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("NewModel");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfIncludeAuthorInBook() {
        Product[] expected = new Product[]{second};
        Product[] actual = manager.searchBy("George Orwell");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfManufactureIncludeInProduct() {
        Product[] expected = new Product[]{third};
        Product[] actual = manager.searchBy("3310");
        assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldSearchIfIncludeNotFullAuthorNameInBook() {
        Product[] expected = new Product[]{second};
        Product[] actual = manager.searchBy("Orwell");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGiveTwoProductsWithSameParams() {
        Product[] expected = new Product[]{fifth, fourth};
        Product[] actual = manager.searchBy("Samsung");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGiveAllProductsInAdded() {
        Product[] expected = new Product[]{sixth, fifth, fourth, third, second, first};
        Product[] actual = manager.getAll();
        assertArrayEquals(expected, actual);
    }


}