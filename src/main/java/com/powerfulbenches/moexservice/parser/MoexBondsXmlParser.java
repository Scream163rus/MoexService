package com.powerfulbenches.moexservice.parser;

import com.powerfulbenches.moexservice.dto.BondDto;
import com.powerfulbenches.moexservice.exception.BondsParseException;
import com.powerfulbenches.moexservice.model.Currency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.List;


@Service
@Slf4j
public class MoexBondsXmlParser implements Parser<BondDto> {

    @Override
    public List<BondDto> parse(String xml) {
        List<BondDto> bondDtos = new ArrayList<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            try (StringReader stringReader = new StringReader(xml)) {
                Document parse = db.parse(new InputSource(stringReader));
                NodeList list = parse.getElementsByTagName("row");
                for (int temp = 0; temp < list.getLength(); temp++) {
                    Node node = list.item(temp);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        String ticker = element.getAttribute("SECID");
                        String price = element.getAttribute("PREVADMITTEDQUOTE");
                        String name = element.getAttribute("SHORTNAME");
                        Currency currency = null;
                        switch (element.getAttribute("FACEUNIT")){
                            case "SUR" : currency = Currency.RUB;
                            break;
                            case "USD" : currency = Currency.USD;
                            break;
                            case "EUR" : currency = Currency.EUR;
                        }
                        if (!ticker.isEmpty() && !price.isEmpty() && !name.isEmpty() && currency != null) {
                            bondDtos.add(new BondDto(ticker, name, new BigDecimal(price), currency));
                        }
                    }
                }
            }
        }
        catch (Exception e){
            log.error("Xml parsing error, xml{}",xml, e);
            throw new BondsParseException(e.getMessage());
        }
        return bondDtos;
    }
}
