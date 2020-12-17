package com.agilityio.spring5demo.startermongodb.autoconfigure

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mapstruct.Mapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.stereotype.Repository
import java.time.Instant


class FakeModelDTO(
    var id: String? = null,
    var name: String,
)

class FakeModel(
    @Id
    var id: String? = null,
    var name: String,

    @LastModifiedDate
    var modifiedDate: Instant? = null,

    @CreatedDate
    var createdDate: Instant? = null,

    @Version
    var version: Long? = null,
);

@Mapper
interface FakeModelMapper {
    fun convertToDto(model: FakeModel): FakeModelDTO
    fun convertFromDto(dto: FakeModelDTO): FakeModel
}

@NoRepositoryBean
interface AgMongoRepository<T> : MongoRepository<T, String>


@Repository
interface FakeModelRepository : AgMongoRepository<FakeModel>

/**
 * To run this test, makes sure activates the virtual environment, the
 * run `do-proj-mongo-start` to run a default local mongo server.
 * The mongo server is default to have "db" database with "user/pass" credential.
 */
@SpringBootTest
class AgStarterMongodbAutoConfigurationTest {

    @Autowired
    private lateinit var fakeModelRepository: FakeModelRepository

//    @Autowired
//    private lateinit var fakeModelMapper: FakeModelMapper

    @Test
    fun testSave() {
        createOne();
    }


    @Test
    fun testFindById() {
        val model = createOne();
        val res = fakeModelRepository.findById(model.id!!).orElse(null);
        assertNotNull(res);
        assertEquals(model.name, res.name);
    }

    @Test
    fun testFind() {
        fakeModelRepository.deleteAll();
        createOne();
        assertEquals(1, fakeModelRepository.findAll().size);
        createOne();
        assertEquals(2, fakeModelRepository.findAll().size);
    }

    private fun createOne(): FakeModel {
        val model = FakeModel(id = null, name = "Test")
        val res = fakeModelRepository.save(model)
        assertNotNull(res.id);
        assertNotNull(res.version);
        assertNotNull(res.createdDate);
        assertNotNull(res.modifiedDate);
        assertEquals(res.name, model.name);
        return res;
    }
}
