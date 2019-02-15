package lambdaschool.gdp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GdpRepository extends JpaRepository<Gdp, Long>
{
    Gdp findGdpByCountry(String name);
    List<Gdp> findGdpByAmountOrderByAmount(Long amt);
}
