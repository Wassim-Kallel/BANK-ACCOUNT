package fr.soat.bank.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
/**
 * class to serializer date
 * @author formation
 *
 */
public class DateSerializer extends JsonSerializer<Date>{
	 
    public void serialize(Date dt, JsonGenerator jsonGen, SerializerProvider serProv) 
                                            throws IOException, JsonProcessingException {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(dt);
        jsonGen.writeString(formattedDate);
    }
}