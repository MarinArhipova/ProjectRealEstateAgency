package com.company.repositories;

import com.company.dto.ProductDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

public class ProductsRepositoryImpl implements ProductsRepository {
    //language=SQL
    private static final String SQL_INSERT =
            "insert into product (product_id, img, title, price, category) values (?, ?, ?, ?, ?);";
//    //language=SQL
//    private static final String SQL_DELETE =
//            "delete from product where product_id = ?";
    //language=SQL
    private static final String SQL_SELECT_ALL_PRODUCTS =
            "select * from product where category=?";

    //language=SQL
    private static final String SQL_SELECT_ALL =
            "select * from product";
//    //language=SQL
//    private static final String SQL_SELECT_PRODUCTS_BY_USER_ID =
//            "select * from product where user_id = ?";
//    //language=SQL
//    private static final String SQL_SELECT_ONE =
//            "select * from product where id = ?";

    //language=SQL
    private static final String SQL_SELECT_COUNT_OF_ROOMS =
            "select * from product where countofrooms = ?";

    //language=SQL
    private static final String SQL_SELECT_GET_PRODUCT_BY_ID =
            "select * from product WHERE product_id = ?;";

    private JdbcTemplate jdbcTemplate;

    public ProductsRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

//    private RowMapper<Product> productRowMapper = (resultSet, i) -> Product.builder()
//            .id(resultSet.getLong("id"))
//            .img(resultSet.getString("img"))
//            .title(resultSet.getString("title"))
//            .countOfRooms(resultSet.getInt("countOfRooms"))
//            .price(resultSet.getString("price"))
//            .category(resultSet.getString("category"))
//            .build();

    private RowMapper<ProductDto> productDtoRowMapper = (resultSet, i) -> ProductDto.builder()
            .id(resultSet.getLong("product_id"))
            .img(resultSet.getString("img"))
            .title(resultSet.getString("title"))
            .price(resultSet.getString("price"))
            .category(resultSet.getString("category"))
            .build();

    @Override
    public List<ProductDto> findAllProducts(String category) {
        return jdbcTemplate.query(SQL_SELECT_ALL_PRODUCTS, productDtoRowMapper, category);
    }


//    @Override
//    public List<Product> findByUserId(Long id) {
//        return jdbcTemplate.query(SQL_SELECT_PRODUCTS_BY_USER_ID, productRowMapper, id);
//    }

//    @Override
//    public List<Product> findByCountOfRooms(Integer countOfRooms) {
//        return jdbcTemplate.query(SQL_SELECT_COUNT_OF_ROOMS, productRowMapper, countOfRooms);
//    }

    @Override
    public ProductDto findProductById(Long id) {
        return jdbcTemplate.query(SQL_SELECT_GET_PRODUCT_BY_ID, productDtoRowMapper, id).get(0);

    }

//    @Override
//    public Optional<Product> findOne(Long id) {
//        return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_ONE, productRowMapper, id));
//    }

    @Override
    public void save(ProductDto model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                    ps.setLong(1, model.getId());
                    ps.setString(2, model.getImg());
                    ps.setString(3, model.getTitle());
                    ps.setString(4, model.getPrice() );
                    ps.setString(5,model.getCategory());
                    return ps;
                }, keyHolder);

        model.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void delete(Long id) {

    }

//    @Override
//    public void delete(Long id) {
//        jdbcTemplate.update(
//                connection -> {
//                    PreparedStatement ps =
//                            connection.prepareStatement(SQL_DELETE);
//                    ps.setLong(1,id);
//                    return ps;
//                }
//        );
//    }

    @Override
    public void update(ProductDto model) {
    }

    @Override
    public List<ProductDto> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, productDtoRowMapper);
    }
}
