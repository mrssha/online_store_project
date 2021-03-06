package store.service;

import store.dto.ProductDto;
import store.utils.LogMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.UncategorizedJmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class StandServiceImpl implements StandService {

    private ProductService productService;
    private JmsTemplate jmsTemplate;

    private static final Logger logger = Logger.getLogger(StandServiceImpl.class);

    private List<ProductDto> oldTopProducts;

    @PostConstruct
    public void init(){
        oldTopProducts = productService.getTopProducts();
    }


    public void updateStandIfTopChanged(){
        List<ProductDto> newTopList= productService.getTopProducts();
        try {
            if (!oldTopProducts.containsAll(newTopList)) {
                jmsTemplate.send(s -> s.createTextMessage("update list"));
                oldTopProducts = newTopList;
                logger.info(LogMessage.JMS_SUCCESS);
            }
            logger.debug(LogMessage.TOP_PRODUCTS_NO_CHANGES);
        }
        catch (UncategorizedJmsException e){
            logger.warn(LogMessage.JMS_FAILED);
        }
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
}
