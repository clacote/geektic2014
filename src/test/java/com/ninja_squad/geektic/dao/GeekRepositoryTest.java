package com.ninja_squad.geektic.dao;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.generator.ValueGenerators;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static org.assertj.core.api.Assertions.assertThat;

public class GeekRepositoryTest extends BaseDaoTest {

    @Autowired
    private GeekRepository geekRepository;

    @Before
    public void populateDatabase() {
        Operation operation = Operations.sequenceOf(
                deleteAllFrom("Geek"),
                insertInto("Geek")
                        .columns("username")
                        .withGeneratedValue("id", ValueGenerators.sequence().startingAt(-1).incrementingBy(-1))
                        .values("cyril")
                        .values("jb")
                        .build()
        );
        DbSetup dbSetup = new DbSetup(destination, operation);
        dbSetup.launch();
    }

    @Test
    public void testFindAll() {
        assertThat(geekRepository.findAll()).extracting("username").containsOnly("cyril", "jb");
    }
}
