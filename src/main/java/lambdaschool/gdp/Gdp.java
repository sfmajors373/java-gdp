package lambdaschool.gdp;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Gdp
{
    private @Id @GeneratedValue Long id;
    private String country;
    private Long amount;

    public Gdp()
    {
        // default
    }

    public Gdp(String country, Long gdp)
    {
        this.country = country;
        this.amount = amount;
    }
}
