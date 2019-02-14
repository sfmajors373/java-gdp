package lambdaschool.gdp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class GdpController
{
    private final GdpRepository gdprepos;
    private final RabbitTemplate rt;

    public GdpController(GdpRepository gdprepos, RabbitTemplate rt)
    {
        this.gdprepos = gdprepos;
        this.rt = rt;
    }

    @GetMapping("/names")
    public List<Gdp> all()
    {
        return gdprepos.findAll();
    }

    @GetMapping("/economy")
    public List<Gdp> findByEconomy(Long amt)
    {
        return gdprepos.findGdpByAmountOrderByAmount(amt);
    }

    @GetMapping("/total")
    public ObjectNode sumGdp()
    {
        List<Gdp> gdps = gdprepos.findAll();
        Long total = 0L;
        for (Gdp g: gdps)
        {
            total = total + g.getAmount();
        }

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode totalGdp = mapper.createObjectNode();
        totalGdp.put("id", 0);
        totalGdp.put("country", "Total");
        totalGdp.put("amount", total);
        return totalGdp;
    }
    @GetMapping("/gdp/{country}")
    public Gdp findByCountry(@PathVariable String country)
    {
        String mes = "Checked GDP for " + country;
        GdpLog message = new GdpLog(mes);
        rt.convertAndSend(WorldGdpApplication.QUEUE_NAME, message.toString());
        log.info("Message sent");

        return gdprepos.findGdpByCountry(country);
    }

    @PostMapping("/gdp")
    public List<Gdp> newGdp(@RequestBody List<Gdp> newGdps)
    {
        return gdprepos.saveAll(newGdps);
    }
}
