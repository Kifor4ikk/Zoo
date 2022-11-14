package com.epam.rd.tasks.zoo.repository.animal.bird.finche.bullfinch;

import com.epam.rd.tasks.zoo.animals.bird.finche.Bullfinch;
import com.epam.rd.tasks.zoo.repository.animal.bird.BirdMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BullfinchMapper extends BirdMapper {

    public Bullfinch fromRawToBullfinch(ResultSet birdRaw, Bullfinch bullfinch) throws ClassNotFoundException, SQLException {
        while(birdRaw.next()) {
            bullfinch = (Bullfinch) fromRawToAnimal(birdRaw, bullfinch);
            bullfinch = (Bullfinch) fromRawToBird(birdRaw, bullfinch);
            bullfinch.setColor(birdRaw.getString("color"));
        }
        return bullfinch;
    }


}
